/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appinformes;

import java.awt.TextField;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Miguel
 */
public class AppInformes extends Application {
    
    // Variables globales
    ConectaDB conexion = new ConectaDB(); 
    Connection cx = conexion.getConexion(); 
    
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
        
        //TextField para cuando se le pide parámetros
        TextField tintro = new TextField("Introduce ID de cliente.");
        
        //Cuando pulsa en MenuItem listadoFacturas genera un evento que llama a la vez a un metodo (idem con demás)
        invoiceList.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event){
                try{
                    listadoFacturas(cx);
                }
                catch(JRException ex){
                    
                    Logger.getLogger(AppInformes.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        totalSales.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event){
                
                try{
                    totalSales(cx);
                }
                catch(JRException ex){
                    
                    Logger.getLogger(AppInformes.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        invoiceClient.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                try{
                    invoiceClient(tintro);
                }
                catch(JRException ex){
                    Logger.getLogger(AppInformes.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        subreportList.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                
                try{
                    
                    subreportList(cx);
                }
                catch(JRException ex){
                    
                    Logger.getLogger(AppInformes.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        //Funcion de Salir
        exitapp.setOnAction(e -> {
            System.exit(0); 
        }); 

        
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
    
    //Todos los Métodos del Menú
    public void listadoFacturas(Connection cx) throws JRException{ 
        
        try{
            JasperReport jr = (JasperReport)JRLoader.loadObject(AppInformes.class.getResource("Facturas.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();
            //int nproducto = Integer.valueOf(tintro.getText());
            //parametros.put("ParamProducto", nproducto);
            //No le pasa parametros (null)
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, null, cx);
            //JasperViewer.viewReport(jp);
            JasperViewer jv = new JasperViewer(jp); 
            jv.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            jv.setTitle("Facturas");
            jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            jv.requestFocus();
            jv.setVisible(true);
        } 
        catch (JRException ex){
            
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void totalSales(Connection cx) throws JRException{
        
        try {
            JasperReport jr2 = (JasperReport)JRLoader.loadObject(AppInformes.class.getResource("Ventas_Totales.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();
            //int nproducto = Integer.valueOf(tintro.getText());
            //parametros.put("ParamProducto", nproducto);
            //No le pasa parametros (null)
            JasperPrint jp2 = (JasperPrint) JasperFillManager.fillReport(jr2, null, cx);
            //JasperViewer.viewReport(jp);
            JasperViewer jv2 = new JasperViewer(jp2); 
            jv2.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            jv2.setTitle("Ventas Totales");
            jv2.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            jv2.requestFocus();
            jv2.setVisible(true);
        } 
        catch (JRException ex) {
            
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
    public void invoiceClient(TextField tintro) throws JRException{ 
        
        try {
            JasperReport jr3 = (JasperReport)JRLoader.loadObject(AppInformes.class.getResource("Facturas_por_Cliente.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();
            int nproducto = Integer.valueOf(tintro.getText());
            parametros.put("ParamProducto", nproducto);
            JasperPrint jp3 = (JasperPrint) JasperFillManager.fillReport(jr3, parametros, cx);
            JasperViewer.viewReport(jp3);
            JasperViewer jv3 = new JasperViewer(jp3); 
            jv3.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            jv3.setTitle("Facturas por Cliente");
            jv3.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            jv3.requestFocus();
            jv3.setVisible(true);
        } 
        catch (JRException ex) {
            
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
    public void subreportList(Connection cx) throws JRException{ 
        
        try {
            JasperReport jr4 = (JasperReport)JRLoader.loadObject(AppInformes.class.getResource("SubinformeListadoFacturas.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();
            //int nproducto = Integer.valueOf(tintro.getText());
            //parametros.put("ParamProducto", nproducto);
            //No le pasa parametros (null)
            JasperPrint jp4 = (JasperPrint) JasperFillManager.fillReport(jr4, null, cx);
            // asperViewer.viewReport(jp);
            JasperViewer jv4 = new JasperViewer(jp4); 
            jv4.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            jv4.setTitle("Subinforme Listado Facturas.");
            jv4.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            jv4.requestFocus();
            jv4.setVisible(true);
        } 
        catch (JRException ex) {
            
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
}