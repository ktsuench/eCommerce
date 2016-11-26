/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.KST.eCommerce;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Ken
 */
public class EcommerceGUIController implements Initializable {
    @FXML private Button btnStore;
    @FXML private Button btnCart;
    @FXML private Button btnLogin;
    @FXML private Label label;

    @FXML
    private void viewHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = null;

        if (event.getSource() == btnStore) {
            root = FXMLLoader.load(getClass().getResource("views/viewHome.fxml"));
        } else if (event.getSource() == btnCart) {
            root = FXMLLoader.load(getClass().getResource("views/viewCart.fxml"));
        } else if(event.getSource() == btnLogin) {
            root = FXMLLoader.load(getClass().getResource("views/viewLogin.fxml"));
        }
        
        if(root != null) {
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
