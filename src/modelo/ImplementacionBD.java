/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dam
 */
public class ImplementacionBD {
    private Connection conexion;
    private ResourceBundle fichConf;
    private String URL, DBROOT, DBROOTPASS;
    private PreparedStatement declaracion;
    
    private final String ALTA_UNIDAD_DIDACTICA = "INSERT INTO UnidadDidactica (acronimo, titulo, evaluacion, descripcion) VALUES (?,?,?,?)";
    
    public ImplementacionBD() {
        fichConf = ResourceBundle.getBundle("modelo.config");
        URL = fichConf.getString("URL");
        DBROOT = fichConf.getString("DBROOT");
        DBROOTPASS = fichConf.getString("DBROOTPASS");
    }
    
    private void openConnection() throws SQLException {
	conexion = DriverManager.getConnection(URL, DBROOT, DBROOTPASS);
    }
    
    private void closeConnection() {
	try {
            if (declaracion != null) {
                declaracion.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException evento) {
            evento.printStackTrace();
        }
    }
    
    public void altaUnidad(UnidadDidactica ud){
        ResultSet resultado;
        
        try {
            openConnection();
            
            declaracion = conexion.prepareStatement(ALTA_UNIDAD_DIDACTICA);
            declaracion.setString(1, UnidadDidactica.getAcronimo());
            declaracion.setString(2, UnidadDidactica.getTitulo());
            declaracion.setString(3, UnidadDidactica.getEvaluacion());
            declaracion.setString(4, UnidadDidactica.getDescripcion());
            
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
