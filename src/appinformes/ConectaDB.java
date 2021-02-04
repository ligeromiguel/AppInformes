/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appinformes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Miguel
 */
public class ConectaDB {
    
    // Atributos
    private Connection conexion;
    private String baseDatos = "jdbc:hsqldb:hsql://localhost:9001/SampleDB";
    private String usuario = "SA";
    private String clave = ""; 
    
    public ConectaDB(){
        
        try{
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            // Establece la conexion
            conexion = DriverManager.getConnection(baseDatos,usuario,clave);
            System.out.println("Conexión establecida.");
        }
        catch (ClassNotFoundException cnfe){
            
            System.err.println("Fallo al cargar JDBC");
            System.exit(1);
        }
        catch (SQLException sqle){
            
            System.err.println("No se pudo conectar a BD");
            System.exit(1);
        }
        catch (java.lang.InstantiationException sqlex){
            
            System.err.println("Imposible Conectar");
            System.exit(1);
        }
        catch (Exception ex){
            
            System.err.println("Imposible Conectar");
            System.exit(1);
        }
    }
    
    // Getter de Connection
    public Connection getConexion(){
        return conexion;
    }
            
}