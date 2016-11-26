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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Kent Tsuenchy
 */
public class EcommerceGUIController implements Initializable {

    @FXML
    private Button btnStore;
    @FXML
    private Button btnCart;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnItems;
    @FXML
    private Button btnLogout;
    @FXML
    private Label lblResult;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private AnchorPane itemList;

    @FXML
    private void viewHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = null;

        if (event.getSource() == btnStore) {
            root = FXMLLoader.load(getClass().getResource(EcommerceGUI.VIEW_STORE));
        } else if (event.getSource() == btnCart) {
            root = FXMLLoader.load(getClass().getResource(EcommerceGUI.VIEW_CART));
        } else if (event.getSource() == btnLogin) {
            root = FXMLLoader.load(getClass().getResource(EcommerceGUI.VIEW_LOGIN));
        }

        if (root != null) {
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void processPurchase() {
        EcommercePlatform platform = EcommerceGUI.platform;
        Session session = platform.getSession();

        if (session.getCart().getSizeOfCart() > 0) {
            lblResult.setStyle("-fx-text-fill:#00a405");
            lblResult.setText(session.purchase(platform.getPaymentProcessor()));
        } else {
            lblResult.setStyle("-fx-text-fill:#f10f0f");
            lblResult.setText("Nothing to purchase.");
        }

        lblResult.setVisible(true);
        itemList.getChildren().clear();
    }

    @FXML
    private void login(Event e) {
        EcommercePlatform platform = EcommerceGUI.platform;
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (platform.login(username, password)) {
            lblResult.setStyle("-fx-text-fill:#00a405");
            lblResult.setText("Logging in");

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(EcommerceGUI.VIEW_ITEMS))));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(EcommerceGUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            lblResult.setStyle("-fx-text-fill:#f10f0f");
            lblResult.setText("Wrong username/password.");
        }

        lblResult.setVisible(true);
    }

    @FXML
    private void logout(Event e) {
        Session session = EcommerceGUI.platform.getSession();

        if (session.logout()) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(EcommerceGUI.VIEW_STORE))));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(EcommerceGUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadItems() {
        AnchorPane itemContainer;
        Label itemTitle;
        Label itemDescription;
        Label itemPrice;
        Button cartAction;

        Session session = EcommerceGUI.platform.getSession();
        ArrayList<Item> items = null;

        if (btnCart != null) {
            items = EcommerceGUI.platform.listItems();
        } else if (btnStore != null) {
            items = EcommerceGUI.platform.getSession().getCart().getItems();
        } else if (btnLogout != null) {
            items = EcommerceGUI.platform.getSession().getItemStore();
        }

        double x = 20;
        double y = 20;

        for (Item i : items) {
            itemContainer = new AnchorPane();
            itemContainer.setStyle("-fx-background-color:" + EcommerceGUI.BACKGROUND);
            itemContainer.setMinWidth(520);
            itemContainer.setMinHeight(120);
            itemContainer.setLayoutX(x);
            itemContainer.setLayoutY(y);
            y += 140;

            itemTitle = new Label(i.getTitle());
            itemTitle.setStyle("-fx-text-fill:" + EcommerceGUI.FOREGROUND + ";-fx-padding:10;-fx-font-size:16;-fx-font-weight:bold");
            itemTitle.setMinWidth(410);
            itemTitle.setMinHeight(45);
            itemTitle.setLayoutX(10);
            itemTitle.setLayoutY(10);

            itemDescription = new Label(i.getDescription());
            itemDescription.setStyle("-fx-text-fill:" + EcommerceGUI.FOREGROUND + ";-fx-padding:10;-fx-alignment:TOP-LEFT");
            itemDescription.setMinWidth(410);
            itemDescription.setMinHeight(60);
            itemDescription.setLayoutX(10);
            itemDescription.setLayoutY(50);

            itemPrice = new Label(String.format("$%.2f", i.getPrice()));
            itemPrice.setStyle("-fx-text-fill:" + EcommerceGUI.FOREGROUND + ";-fx-padding:10;-fx-font-size:16;-fx-font-weight:bold;-fx-alignment:CENTER-RIGHT");
            itemPrice.setMinWidth(90);
            itemPrice.setMinHeight(45);
            itemPrice.setLayoutX(420);
            itemPrice.setLayoutY(10);

            cartAction = new Button();
            if (btnCart != null) {
                cartAction.setText("Add To Cart");
                cartAction.setOnMouseClicked((EventHandler) new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        session.addToCart(i);
                        btnCart.setText("Cart: " + session.getCartSize());
                    }
                });
            } else if (btnStore != null || btnLogout != null) {
                cartAction.setText("Remove");
                cartAction.setOnMouseClicked((EventHandler) new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();

                        try {
                            if (btnStore != null) {
                                session.removeFromCart(i);
                                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(EcommerceGUI.VIEW_CART))));
                            } else {
                                session.removeItemFromStore(i);
                                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(EcommerceGUI.VIEW_ITEMS))));
                            }
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(EcommerceGUIController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
            cartAction.setMinWidth(80);
            cartAction.setMinHeight(40);
            cartAction.setLayoutX(430);
            cartAction.setLayoutY(70);

            itemContainer.getChildren().add(itemTitle);
            itemContainer.getChildren().add(itemDescription);
            itemContainer.getChildren().add(itemPrice);
            itemContainer.getChildren().add(cartAction);

            itemList.getChildren().add(itemContainer);
        }

        Pane spacer = new Pane();
        spacer.setMinHeight(20);
        spacer.setMinWidth(450);
        spacer.setLayoutX(0);
        spacer.setLayoutY(y - 20);

        itemList.getChildren().add(spacer);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (btnCart != null) {
            btnCart.setText("Cart: " + EcommerceGUI.platform.getSession().getCartSize());
        }
        if (itemList != null) {
            loadItems();
        }
    }
}
