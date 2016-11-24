package com.KST.eCommerce;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Kent Tsuenchy
 */
public class XMLHandler {

    private final Document doc;
    private final NodeList users;
    private int userIndex = -1;
    private int itemIndex = -1;
    private Element user;
    private NodeList items;
    private Element item;

    public XMLHandler(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory docBuilderFact = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFact.newDocumentBuilder();
        this.doc = docBuilder.parse(xmlFile);

        this.doc.getDocumentElement().normalize();
        this.users = this.doc.getElementsByTagName("user");
        getUser();
        getItems();
        getItem();
    }

    private void getUser() {
        boolean foundEl = false;
        
        while (!foundEl && this.userIndex++ < this.users.getLength() - 1) {
            if (this.users.item(this.userIndex).getNodeType() == Node.ELEMENT_NODE) {
                this.user = (Element) this.users.item(this.userIndex);
                foundEl = true;
            }
        }
    }

    private void getItems() {
        NodeList itemsList = this.user.getElementsByTagName("items");
        this.items = (NodeList) itemsList.item(0);
    }

    private void getItem() {
        boolean foundEl = false;
        
        while (!foundEl && this.itemIndex++ < this.items.getLength() - 1) {
            if (this.items.item(this.itemIndex).getNodeType() == Node.ELEMENT_NODE) {
                this.item = (Element) this.items.item(this.itemIndex);
                foundEl = true;
            }
        }
    }

    public boolean nextUser() {
        boolean notEnd = notAtEndOfNodeList(this.users, this.userIndex);

        if (notEnd) {
            getUser();
            getItems();
            nextItem();
        } else {
            this.userIndex = -1;
        }

        return notEnd;
    }

    public boolean nextItem() {
        boolean notEnd = notAtEndOfNodeList(this.items, this.itemIndex);

        if (notEnd) {
            getItem();
            
            /**
             * Hot Fix
             * Unsure what extra nodes in items NodeList are.
             * Needs to check again to see if at end of list.
             * Assumes that the last few nodes in list does not contain
             * an actual item.
             */
            notEnd = notAtEndOfNodeList(this.items, this.itemIndex);
            
            if(!notEnd) {
                this.itemIndex = -1;
            }
        } else {
            this.itemIndex = -1;
        }

        return notEnd;
    }

    private boolean notAtEndOfNodeList(NodeList parent, int index) {
        return index < parent.getLength() - 1;
    }

    public int getId() {
        return Integer.parseInt(this.user.getAttribute("id"));
    }

    private Element getTagValue(Element el, String tagName) {
        return (Element) el.getElementsByTagName(tagName).item(0);
    }

    public String getName() {
        return getTagValue(this.user, "name").getTextContent();
    }

    public String getPassword() {
        return getTagValue(this.user, "password").getTextContent();
    }

    public int getItemId() {
        return Integer.parseInt(this.item.getAttribute("id"));
    }
    
    public String getItemTitle() {
        return getTagValue(this.item, "title").getTextContent();
    }

    public String getItemDescription() {
        return getTagValue(this.item, "description").getTextContent();
    }

    public double getItemPrice() {
        return Double.parseDouble(getTagValue(this.item, "price").getTextContent());
    }

    public static void outputXMLFileContent(File xml) throws ParserConfigurationException, SAXException, IOException {
        XMLHandler xmlHandler = new XMLHandler(xml);

        do {
            System.out.println("-------------------------------------------");
            System.out.println(xmlHandler.getId()+" "+xmlHandler.getName());
            System.out.println(xmlHandler.getPassword());

            do {
                System.out.println("\t"+xmlHandler.getItemId()+" "+xmlHandler.getItemTitle());
                System.out.println("\t"+xmlHandler.getItemDescription());
                System.out.println("\t"+xmlHandler.getItemPrice()+"\n");
            } while (xmlHandler.nextItem());
        } while (xmlHandler.nextUser());
    }
}
