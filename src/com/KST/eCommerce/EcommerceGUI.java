/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.KST.eCommerce;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 *
 * @author Kent Tsuenchy
 */
public class EcommerceGUI extends javax.swing.JFrame {
    private static final String VIEW_HOME = "viewhome";
    private static final String VIEW_CART = "viewCart";
    private static final String VIEW_LOGIN = "viewLogin";
    private static final String VIEW_ITEMS = "viewItems";
    private static final String VIEW_ADD_ITEM = "viewAddItem";
    
    private javax.swing.JPanel container;
    private javax.swing.JPanel viewHome;
    private javax.swing.JPanel viewCart;
    private javax.swing.JPanel viewLogin;
    private javax.swing.JPanel viewItems;
    private javax.swing.JPanel viewAddItem;    
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelBody;
    private javax.swing.JButton buttonCart;
    private javax.swing.JButton buttonLogin;
    private javax.swing.JLabel labelLogo;
    
    
    /**
     * Creates new form EcommerceGUI
     */
    public EcommerceGUI() {
        initComponents();
    }
    
    private void initComponents() {
        this.container = new javax.swing.JPanel();
        this.viewHome = new javax.swing.JPanel();
        this.viewCart = new javax.swing.JPanel();
        this.viewLogin = new javax.swing.JPanel();
        this.viewItems = new javax.swing.JPanel();
        this.viewAddItem = new javax.swing.JPanel();
        this.panelHeader = new javax.swing.JPanel();
        this.panelBody = new javax.swing.JPanel();
        this.buttonCart = new javax.swing.JButton();
        this.buttonLogin = new javax.swing.JButton();
        this.labelLogo = new javax.swing.JLabel();

        // Set up frame properties
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("eCommerce");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(java.awt.Color.black);
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(600, 600));
        setMinimumSize(new java.awt.Dimension(600, 600));
        setName("eCommerceGUI"); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(600, 600));

        // Set up layout for container of views
        container.setLayout(new java.awt.CardLayout());

        // Setting up items in panels
        buttonCart.setText("Cart");
        buttonCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCartMouseClicked(evt);
            }
        });

        buttonLogin.setText("Login");
        buttonLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonLoginMouseClicked(evt);
            }
        });

        labelLogo.setFont(new java.awt.Font("Tahoma", 0, 18));
        labelLogo.setForeground(new java.awt.Color(255, 255, 255));
        labelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLogo.setText("eCommerce");

        // Set up header of views
        panelHeader.setBackground(java.awt.SystemColor.textHighlight);
        panelHeader.setForeground(java.awt.SystemColor.window);
        panelHeader.setToolTipText("");
        
        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
                .addComponent(buttonLogin)
                .addGap(18, 18, 18)
                .addComponent(buttonCart)
                .addGap(27, 27, 27))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLogin)
                    .addComponent(buttonCart))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        // Set up body of views
        panelBody.setPreferredSize(new java.awt.Dimension(600, 552));

        javax.swing.GroupLayout panelBodyLayout = new javax.swing.GroupLayout(panelBody);
        panelBody.setLayout(panelBodyLayout);
        panelBodyLayout.setHorizontalGroup(
            panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelBodyLayout.setVerticalGroup(
            panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        // Set up each view
        setupHomeView();
        setupCartView();
        setupLoginView();
        setupItemsView();
        setupAddItemView();
        
        // Set up container of views
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
    }

    /* Set up each view in its own separate method */
    //<editor-fold defaultstate="collapsed" desc=" Setting up gui views ">
    private void setupHomeView() {
        javax.swing.GroupLayout viewHomeLayout = new javax.swing.GroupLayout(viewHome);
        viewHome.setLayout(viewHomeLayout);
        viewHomeLayout.setHorizontalGroup(
            viewHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBody, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );
        viewHomeLayout.setVerticalGroup(
            viewHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewHomeLayout.createSequentialGroup()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(panelBody, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
        );

        container.add(viewHome, EcommerceGUI.VIEW_HOME);
    }
    
    private void setupCartView() {
        javax.swing.GroupLayout viewCartLayout = new javax.swing.GroupLayout(viewCart);
        viewCart.setLayout(viewCartLayout);
        viewCartLayout.setHorizontalGroup(
            viewCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        viewCartLayout.setVerticalGroup(
            viewCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        container.add(this.viewCart, EcommerceGUI.VIEW_CART);
    }
    
    private void setupLoginView() {
        javax.swing.GroupLayout viewLoginLayout = new javax.swing.GroupLayout(viewLogin);
        viewLogin.setLayout(viewLoginLayout);
        viewLoginLayout.setHorizontalGroup(
            viewLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        viewLoginLayout.setVerticalGroup(
            viewLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        container.add(viewLogin, EcommerceGUI.VIEW_LOGIN);
        
    }
    
    private void setupItemsView() {
        javax.swing.GroupLayout viewItemsLayout = new javax.swing.GroupLayout(viewItems);
        viewItems.setLayout(viewItemsLayout);
        viewItemsLayout.setHorizontalGroup(
            viewItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        viewItemsLayout.setVerticalGroup(
            viewItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        container.add(viewItems, EcommerceGUI.VIEW_ITEMS);
    }
    
    private void setupAddItemView() {
        javax.swing.GroupLayout viewAddItemLayout = new javax.swing.GroupLayout(viewAddItem);
        viewAddItem.setLayout(viewAddItemLayout);
        viewAddItemLayout.setHorizontalGroup(
            viewAddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        viewAddItemLayout.setVerticalGroup(
            viewAddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        container.add(viewAddItem, EcommerceGUI.VIEW_ADD_ITEM);
    }
    //</editor-fold>
    
    /* Button events (example: "MouseClicked" */
    //<editor-fold defaultstate="collapsed" desc=" Button events ">
    private void buttonLoginMouseClicked(java.awt.event.MouseEvent evt) {
        CardLayout cl = (CardLayout) container.getLayout();
        cl.show(container, EcommerceGUI.VIEW_LOGIN);
    }
    
    private void buttonCartMouseClicked(java.awt.event.MouseEvent evt) {
        CardLayout cl = (CardLayout) container.getLayout();
        cl.show(container, EcommerceGUI.VIEW_CART);
    }//</editor-fold>

    public void centreWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }
                    
    public void showGUI(ArrayList<Item> items) {
        EcommerceGUI reference = this;
        
        /* Set the System default look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EcommerceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EcommerceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EcommerceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EcommerceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                reference.centreWindow();
                reference.setVisible(true);
                
                /* Find out how to list items as strings */
                //reference.listItems.setModel();
            }
        });
    }             
}
