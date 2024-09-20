/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @author 2dam
 */
public class ImplementacionBD {
    private Connection conexion;
    private ResourceBundle fichConf;
    private String URL, DBROOT, DBROOTPASS;
    
    private final String altaUnidadDidactica = "INSERT INTO UnidadDidactica (acronimo, titulo, evaluacion, descripcion) VALUES (?,?,?,?)";
    
    public ImplementacionBD() {
        fichConf = ResourceBundle.getBundle("modelo.config");
        URL = fichConf.getString("URL");
        DBROOT = fichConf.getString("DBROOT");
        DBROOTPASS = fichConf.getString("DBROOTPASS");
    }
    
    private void openConnection() throws SQLException {
	conexion = DriverManager.getConnection(URL, DBROOT, DBROOTPASS);
    }
    
    public void altaUnidad(UnidadDidactica ud){
        
    }
}
