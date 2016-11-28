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

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Kent Tsuenchy
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
        stage.getIcons().add(new Image("file:icon.png"));
        stage.show();
    }

    public static void showGUI(EcommercePlatform platform) {
        EcommerceGUI.platform = platform;
        
        launch();
    }
}
