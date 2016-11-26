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
import javafx.scene.layout.Pane;
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
        AnchorPane itemContainer;
        Label itemTitle;
        Label itemDescription;
        Label itemPrice;
        Button addToCart;

        Session session = EcommerceGUI.platform.getSession();
        ArrayList<Item> items = EcommerceGUI.platform.listItems();

        double x = 20;
        double y = 20;
        
        for (Item i : items) {
            itemContainer = new AnchorPane();
            itemContainer.setStyle("-fx-background-color:"+EcommerceGUI.BACKGROUND);
            itemContainer.setMinWidth(520);
            itemContainer.setMinHeight(120);
            itemContainer.setLayoutX(x);
            itemContainer.setLayoutY(y);
            y += 140;
            
            itemTitle = new Label(i.getTitle());
            itemTitle.setStyle("-fx-text-fill:"+EcommerceGUI.FOREGROUND+";-fx-padding:10;-fx-font-size:16;-fx-font-weight:bold");
            itemTitle.setMinWidth(410);
            itemTitle.setMinHeight(45);
            itemTitle.setLayoutX(10);
            itemTitle.setLayoutY(10);
            
            itemDescription = new Label(i.getDescription());
            itemDescription.setStyle("-fx-text-fill:"+EcommerceGUI.FOREGROUND+";-fx-padding:10;-fx-alignment:TOP-LEFT");
            itemDescription.setMinWidth(410);
            itemDescription.setMinHeight(60);
            itemDescription.setLayoutX(10);
            itemDescription.setLayoutY(50);
            
            itemPrice = new Label(String.format("$%.2f", i.getPrice()));
            itemPrice.setStyle("-fx-text-fill:"+EcommerceGUI.FOREGROUND+";-fx-padding:10;-fx-font-size:16;-fx-font-weight:bold;-fx-alignment:CENTER-RIGHT");
            itemPrice.setMinWidth(90);
            itemPrice.setMinHeight(45);
            itemPrice.setLayoutX(420);
            itemPrice.setLayoutY(10);
            
            addToCart = new Button("Add To Cart");
            addToCart.setMinWidth(80);
            addToCart.setMinHeight(40);
            addToCart.setLayoutX(430);
            addToCart.setLayoutY(70);
            addToCart.setOnMouseClicked((EventHandler) new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    session.addToCart(i);
                }
            });

            itemContainer.getChildren().add(itemTitle);
            itemContainer.getChildren().add(itemDescription);
            itemContainer.getChildren().add(itemPrice);
            itemContainer.getChildren().add(addToCart);

            this.itemList.getChildren().add(itemContainer);
        }

        Pane spacer = new Pane();
        spacer.setMinHeight(20);
        spacer.setMinWidth(450);
        spacer.setLayoutX(0);
        spacer.setLayoutY(y - 20);
                
        this.itemList.getChildren().add(spacer);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadItems();
    }
}
