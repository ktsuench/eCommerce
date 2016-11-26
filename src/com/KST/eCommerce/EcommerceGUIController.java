/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.KST.eCommerce;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Ken
 */
public class EcommerceGUIController implements Initializable {

    @FXML
    private Button btnStore;
    @FXML
    private Button btnCart;
    @FXML
    private Button btnLogin;
    @FXML
    private AnchorPane itemList;

    @FXML
    private void viewHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = null;

        if (event.getSource() == this.btnStore) {
            root = FXMLLoader.load(getClass().getResource("views/viewHome.fxml"));
        } else if (event.getSource() == this.btnCart) {
            root = FXMLLoader.load(getClass().getResource("views/viewCart.fxml"));
        } else if (event.getSource() == this.btnLogin) {
            root = FXMLLoader.load(getClass().getResource("views/viewLogin.fxml"));
        }

        if (root != null) {
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    private void loadItems() {
        Scene scene = null;
        AnchorPane itemContainer;
        Label itemTitle;
        Label itemDescription;
        Label itemPrice;
        Button addToCart;

        Session session = EcommerceGUI.platform.getSession();
        ArrayList<Item> items = EcommerceGUI.platform.listItems();

        for (Item i : items) {
            itemContainer = new AnchorPane();
            
            itemTitle = new Label(i.getTitle());
            
            itemDescription = new Label(i.getDescription());
            
            itemPrice = new Label(String.format("$#.##", i.getPrice()));
            
            addToCart = new Button();
            addToCart.setOnMouseClicked((EventHandler) new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    session.addItemToStore(i);
                }
            });

            itemContainer.getChildren().add(itemTitle);
            itemContainer.getChildren().add(itemDescription);
            itemContainer.getChildren().add(itemPrice);
            itemContainer.getChildren().add(addToCart);

            this.itemList.getChildren().add(itemContainer);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadItems();
    }
}
