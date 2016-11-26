/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.KST.eCommerce;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

/**
 *
 * @author Ken
 */
public class EcommerceGUI extends Application {
    public final static String BACKGROUND = "#4286f4";
    public final static String FOREGROUND = "#fff";
    
    public final static String VIEW_STORE = "views/viewHome.fxml";
    public final static String VIEW_CART = "views/viewCart.fxml";
    public final static String VIEW_LOGIN = "views/viewLogin.fxml";
    public final static String VIEW_ITEMS = "views/viewItems.fxml";
    public final static String VIEW_ADD_ITEM = "views/viewAddItem.fxml";
    
    public static EcommercePlatform platform;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/viewHome.fxml"));
        stage.setScene(new Scene(root));
        
        stage.setTitle("eCommerce");
        stage.show();
    }

    public static void showGUI(EcommercePlatform platform) {
        EcommerceGUI.platform = platform;
        
        launch();
    }
}
