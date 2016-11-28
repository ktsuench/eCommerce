/*
 * The MIT License
 *
 * Copyright 2016 Kent Tsuenchy, Kureishi Shivanand, and Tejveer Jajj.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
import javafx.scene.control.TextArea;
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
    private Button btnAddItem;
    @FXML
    private Button btnLogout;
    @FXML
    private Label lblResult;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TextField txtPrice;
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
        } else if (event.getSource() == btnItems) {
            root = FXMLLoader.load(getClass().getResource(EcommerceGUI.VIEW_ITEMS));
        } else if (event.getSource() == btnAddItem) {
            root = FXMLLoader.load(getClass().getResource(EcommerceGUI.VIEW_ADD_ITEM));
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
    private void addItem(Event e) {
        EcommercePlatform platform = EcommerceGUI.platform;
        Session session = platform.getSession();
        String title = txtTitle.getText();
        String description = txtDescription.getText();
        String price = txtPrice.getText();
        Double dPrice;

        if (title.isEmpty() || description.isEmpty()) {
            lblResult.setStyle("-fx-text-fill:#f10f0f");
            lblResult.setText("Failed to add item. Missing title and/or description.");
        } else {
            try {
                dPrice = Double.parseDouble(price);

                if (dPrice < 0.01) {
                    lblResult.setStyle("-fx-text-fill:#f10f0f");
                    lblResult.setText("Failed to add item. Provide a valid price.");
                } else {
                    int id = ((Seller) session.getUser()).createUniqueItemId();
                    Item item = new Item(id, title, description, dPrice);

                    session.addItemToStore(item);

                    lblResult.setStyle("-fx-text-fill:#00a405");
                    lblResult.setText("Added Item.");

                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(EcommerceGUI.VIEW_ITEMS))));
                        stage.show();
                        
                        lblResult = (Label) stage.getScene().lookup("#lblResult");
                        lblResult.setStyle("-fx-text-fill:#00a405");
                        lblResult.setText("Added " + item.getTitle() + ".");
                    } catch (IOException ex) {
                        Logger.getLogger(EcommerceGUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (NumberFormatException ex) {
                lblResult.setStyle("-fx-text-fill:#f10f0f");
                lblResult.setText("Failed to add item. Provide a valid price.");

                //Logger.getLogger(EcommerceGUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        lblResult.setVisible(true);
    }

    @FXML
    private void login(Event e) {
        EcommercePlatform platform = EcommerceGUI.platform;
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (platform.login(username, password)) {
            lblResult.setStyle("-fx-text-fill:#00a405");
            lblResult.setText("Logging in.");

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(EcommerceGUI.VIEW_ITEMS))));
                stage.show();
                
                lblResult = (Label) stage.getScene().lookup("#lblResult");
                lblResult.setStyle("-fx-text-fill:#00a405");
                lblResult.setText(username + " is logged in.");
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
        User user = session.getUser();
        
        if (session.logout()) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(EcommerceGUI.VIEW_STORE))));
                stage.show();
                
                lblResult = (Label) stage.getScene().lookup("#lblResult");
                lblResult.setStyle("-fx-text-fill:#00a405");
                lblResult.setText(user.getName() + " has logged out.");
            } catch (IOException ex) {
                Logger.getLogger(EcommerceGUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            lblResult.setStyle("-fx-text-fill:#f10f0f");
            lblResult.setText("Failed to logout. Try again later.");
        }
        
        lblResult.setVisible(true);
    }

    private void loadItems() {
        AnchorPane itemContainer;
        Label itemTitle;
        Label itemDescription;
        Label itemPrice;
        Button cartAction;

        Session session = EcommerceGUI.platform.getSession();
        ArrayList<Item> items;

        if (btnCart != null) {
            items = EcommerceGUI.platform.viewStore();
        } else if (btnStore != null) {
            items = EcommerceGUI.platform.viewCart();
        } else if (btnLogout != null) {
            items = EcommerceGUI.platform.viewStoreInventory();
        } else {
            items = new ArrayList();
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
                        if(session.addToCart(i)) {
                            btnCart.setText("Cart: " + session.getCartSize());
                            lblResult.setStyle("-fx-text-fill:#00a405");
                            lblResult.setText("Added " + i.getTitle() + ".");
                        } else {
                            lblResult.setStyle("-fx-text-fill:#f10f0f");
                            lblResult.setText("Failed to add " + i.getTitle() + ".");
                        }
                        
                        lblResult.setVisible(true);
                    }
                });
            } else if (btnStore != null || btnLogout != null) {
                cartAction.setText("Remove");
                cartAction.setOnMouseClicked((EventHandler) new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();

                        try {
                            String color = "";
                            String result = "";
                            
                            if (btnStore != null) {
                                if (session.removeFromCart(i)) {
                                    color = "#00a405";
                                    result = "Removed " + i.getTitle() + " from cart.";
                                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(EcommerceGUI.VIEW_CART))));
                                } else {
                                    color = "#f10f0f";
                                    result = "Failed to remove " + i.getTitle() + " from cart.";
                                }
                            } else {
                                if (session.removeItemFromStore(i)) {
                                    color = "#00a405";
                                    result = "Removed " + i.getTitle() + " from store.";
                                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(EcommerceGUI.VIEW_ITEMS))));
                                } else {
                                    color = "#f10f0f";
                                    result = "Failed to remove " + i.getTitle() + " from store.";
                                }
                            }
                            stage.show();
                            
                            lblResult = (Label) stage.getScene().lookup("#lblResult");
                            lblResult.setStyle("-fx-text-fill:" + color);
                            lblResult.setText(result);
                            
                        } catch (IOException ex) {
                            lblResult.setStyle("-fx-text-fill:#f10f0f");
                            lblResult.setText("Failed to remove " + i.getTitle() + ".");
                            
                            Logger.getLogger(EcommerceGUIController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        lblResult.setVisible(true);
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
