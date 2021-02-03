/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appinformes;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Miguel
 */
public class AppInformes extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        //Declaración de las opciones del MenuBar
        VBox root = new VBox();
        Menu mReports = new Menu("Informes");
        Menu mExit = new Menu("Salir");
        
        //Items del Menú
        MenuItem invoiceList = new MenuItem("Listado Facturas");
        MenuItem totalSales = new MenuItem("Ventas Totales");
        MenuItem invoiceClient = new MenuItem("Factura por Cliente");
        MenuItem subreportList = new MenuItem("Subinforme Listado Facturas");
        MenuItem exitapp = new MenuItem("Salir");
        
        //Añadimos los items anteriores a nuestras opciones y creamos el MenuBar
        mReports.getItems().addAll(invoiceList, totalSales, invoiceClient, subreportList);
        mExit.getItems().addAll(exitapp);
        MenuBar mb = new MenuBar(mReports, mExit);
            
        //Creación de la Escena
        Scene scene = new Scene(root, 426, 252);
        ((VBox) scene.getRoot()).getChildren().addAll(mb);
        
        //Fondo de la App
        Image img = new Image(getClass().getResourceAsStream("AppBG.png"));
        ImageView im = new ImageView(img);
        ((VBox) scene.getRoot()).getChildren().addAll(im);
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("AppInformes");
        primaryStage.setScene(scene);
        primaryStage.show();  
    }
    
    
    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
    launch(args);
    }
    
    
}
