/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.IDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dam
 */
public class ImplementacionBD implements IDao {

    private Connection conexion;
    private final ResourceBundle fichConf;
    private final String URL;
    private final String DBROOT;
    private final String DBROOTPASS;
    private PreparedStatement declaracion;

    private final String ALTA_UNIDAD_DIDACTICA = "INSERT INTO UnidadDidactica (acronimo, titulo, evaluacion, descripcion) VALUES (?,?,?,?)";
    private final String ALTA_CONVOCATORIA_EXAMEN = "INSERT INTO ConvocatoriaExamen (convocatoria, descripcion, fecha, curso) VALUES (?,?,?,?)";
    private final String ALTA_ENUNCIADO = "INSERT INTO Enunciado (descripcion, nivel, disponible) VALUES (?,?,?)";
    private final String ALTA_UNIDAD_ENUNCIADO = "INSERT INTO UnidadEnunciado (id_unidad, id_enunciado) VALUES (?,?)";
    private final String SELECT_UNIDADES = "SELECT * FROM UnidadDidactica";
    private final String SELECT_ENUNCIADOS = "SELECT * FROM Enunciados";
    private final String SELECT_ENUNCIADOS_UNIDAD = "SELECT * FROM Enunciado WHERE id = (SELECT id_enunciado FROM UnidadEnunciado WHERE id_unidad = ? )";
    private final String SELECT_CONVOCATORIAS_ENUNCIADO = "SELECT * FROM ConvocatoriaExamen WHERE id = ?";
    private final String SELECT_CONVOCATORIAS_SIN_ENUNCIADO = "SELECT * FROM ConvocatoriaExamen WHERE id = null";
    private final String PUT_ENUNCIADO_IN_CONVOCATORIA = "UPDATE ConvocatoriaExamen SET id = ? WHERE Convocatoria = ?";

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

    @Override
    public void altaUnidad(UnidadDidactica ud) {

        try {
            openConnection();

            declaracion = conexion.prepareStatement(ALTA_UNIDAD_DIDACTICA);
            declaracion.setString(1, ud.getAcronimo());
            declaracion.setString(2, ud.getTitulo());
            declaracion.setString(3, ud.getEvaluacion());
            declaracion.setString(4, ud.getDescripcion());

            declaracion.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

    }

    @Override
    public void altaConvocatoria(ConvocatoriaExamen conv) {

        try {
            openConnection();

            declaracion = conexion.prepareStatement(ALTA_CONVOCATORIA_EXAMEN);
            declaracion.setString(1, conv.getConvocatoria());
            declaracion.setString(2, conv.getDescripcion());
            declaracion.setDate(3, (Date) conv.getFecha());
            declaracion.setString(4, conv.getCurso());

            declaracion.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void altaEnunciado(Enunciado en) {

        try {
            openConnection();

            declaracion = conexion.prepareStatement(ALTA_ENUNCIADO);
            declaracion.setString(1, en.getDescripcion());
            declaracion.setString(2, en.getNivel().toString());
            declaracion.setBoolean(3, en.isDisponible());

            declaracion.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

    }

    @Override
    public void altaUnidadEnunciado(List<Integer> idUnidade, Integer idEnunciado) {

        try {
            openConnection();

            for (Integer i : idUnidade) {

                declaracion = conexion.prepareStatement(ALTA_UNIDAD_ENUNCIADO);
                declaracion.setString(1, i.toString());
                declaracion.setString(2, idEnunciado.toString());

                declaracion.execute();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

    }

    @Override
    public List<UnidadDidactica> getUnidades() {
        ResultSet resultado;
        List<UnidadDidactica> unidades = new ArrayList<>();
        UnidadDidactica unidad;

        try {
            openConnection();
            declaracion = conexion.prepareStatement(SELECT_UNIDADES);
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                resultado.next();
                unidad = new UnidadDidactica();
                unidad.setId(Integer.parseInt(resultado.getString("id")));
                unidad.setAcronimo(resultado.getString("acronimo"));
                unidad.setTitulo(resultado.getString("titulo"));
                unidad.setEvaluacion(resultado.getString("evaluacion"));
                unidad.setDescripcion(resultado.getString("descripcion"));
                unidades.add(unidad);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return unidades;

    }

    @Override
    public Map<Integer, Enunciado> getEnunciados() {
        ResultSet resultado;
        Map<Integer, Enunciado> enunciados = new HashMap<>();
        Enunciado enunciado;

        try {
            openConnection();
            declaracion = conexion.prepareStatement(SELECT_ENUNCIADOS);
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                resultado.next();
                enunciado = new Enunciado();
                enunciado.setId(Integer.parseInt(resultado.getString("id")));
                enunciado.setDescripcion(resultado.getString("descripcion"));
                enunciado.setNivel(Dificultad.valueOf(resultado.getString("nivel")));
                enunciado.setDisponible(resultado.getBoolean("disponible"));
                enunciados.put(enunciado.getId(),enunciado);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return enunciados;
    }

    @Override
    public List<Enunciado> getEnunciadosUnidad(Integer idUnidad) {
        ResultSet resultado;
        List<Enunciado> enunciados = new ArrayList<>();
        Enunciado enunciado;

        try {
            openConnection();
            declaracion = conexion.prepareStatement(SELECT_ENUNCIADOS_UNIDAD);
            declaracion.setInt(1, idUnidad);
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                resultado.next();
                enunciado = new Enunciado();
                enunciado.setId(Integer.parseInt(resultado.getString("id")));
                enunciado.setDescripcion(resultado.getString("descripcion"));
                enunciado.setNivel(Dificultad.valueOf(resultado.getString("nivel")));
                enunciado.setDisponible(resultado.getBoolean("disponible"));
                enunciados.add(enunciado);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return enunciados;
    }

    @Override
    public List<ConvocatoriaExamen> getConvocatoriasEnunciado(Integer enunciado) {
        ResultSet resultado;
        List<ConvocatoriaExamen> convocatorias = new ArrayList<>();
        ConvocatoriaExamen convocatoria;

        try {
            openConnection();
            declaracion = conexion.prepareStatement(SELECT_CONVOCATORIAS_ENUNCIADO);
            declaracion.setInt(1, enunciado);
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                resultado.next();
                convocatoria = new ConvocatoriaExamen();
                convocatoria.setConvocatoria(resultado.getString("convocatoria"));
                convocatoria.setDescripcion(resultado.getString("descripcion"));
                convocatoria.setFecha(resultado.getDate("fecha"));
                convocatoria.setCurso(resultado.getString("curso"));
                convocatorias.add(convocatoria);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return convocatorias;
    }

    @Override
    public List<ConvocatoriaExamen> getConvocatoriasSinEnunciado() {
        ResultSet resultado;
        List<ConvocatoriaExamen> convocatorias = new ArrayList<>();
        ConvocatoriaExamen convocatoria;

        try {
            openConnection();
            declaracion = conexion.prepareStatement(SELECT_CONVOCATORIAS_SIN_ENUNCIADO);
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                resultado.next();
                convocatoria = new ConvocatoriaExamen();
                convocatoria.setConvocatoria(resultado.getString("convocatoria"));
                convocatoria.setDescripcion(resultado.getString("descripcion"));
                convocatoria.setFecha(resultado.getDate("fecha"));
                convocatoria.setCurso(resultado.getString("curso"));
                convocatorias.add(convocatoria);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return convocatorias;
    }

    @Override
    public void putEnunciadoInConvocatoria(Integer idEnunciado, String convocatoria) {
        try {
            openConnection();

            declaracion = conexion.prepareStatement(PUT_ENUNCIADO_IN_CONVOCATORIA);
            declaracion.setInt(1, idEnunciado);
            declaracion.setString(2, convocatoria);

            declaracion.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
    }
}
