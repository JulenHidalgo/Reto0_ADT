/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.UnidadDidactica;
import modelo.ConvocatoriaExamen;
import modelo.Enunciado;
import modelo.ImplementacionBD;

/**
 *
 * @author 2dam
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion;
        IDao dao = (IDao) new ImplementacionBD();

        do {
            System.out.println("1. CREAR");
            System.out.println("2. VISUALIZAR");
            System.out.println("3. ASIGNAR ");
            System.out.println("4. SALIR");
            opcion = utilidades.Utilidades.leerInt("Introduce una opcion");

            switch (opcion) {
                case 1:
                    menuCrear(dao);
                    break;
                case 2:
                    menuVisualizar(dao);
                    break;
                case 3:
                    asignarConvocatoriaEnunciado(dao, null);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Esa opcion no existe");
                    break;
            }

        } while (opcion != 4);
    }

    private static void menuCrear(IDao dao) {
        int opcion;
        System.out.println("1. UNIDAD DIDACTICA");
        System.out.println("2. CONVOCATORIA");
        System.out.println("3. ENUNCIADO ");
        System.out.println("4. ATRAS ");
        opcion = utilidades.Utilidades.leerInt("Introduce una opcion");
        switch (opcion) {
            case 1:
                crearUD(dao);
                break;
            case 2:
                crearConvocatoria(dao);
                break;
            case 3:
                crearEnunciado(dao);
                break;
            case 4:
                break;
            default:
                System.out.println("No existe esa opcion");
        }

    }

    private static void menuVisualizar(IDao dao) {
        int opcion;
        System.out.println("1. VISUALIZAR UN ENUNCIADO POR LA UNIDAD DIDACTICA");
        System.out.println("2. VISUALIZAR CONVOCATORIO POR EL ENUNCIADO");
        System.out.println("3. VISUALIZAR EL ARCHIVO DEL ENUNCIADO");
        System.out.println("4. ATRAS");
        opcion = utilidades.Utilidades.leerInt("Introduce una opcion");
        switch (opcion) {
            case 1:
                mostrarEnunciado(dao);
                break;
            case 2:
                mostrarConvocatoria(dao);
                break;
            case 3:
                abrirEnunciado(dao);
                break;
            case 4:
                break;
            default:
                System.out.println("No existe esa opcion");
        }

    }

    private static void crearUD(IDao dao) {
        UnidadDidactica und = new UnidadDidactica();
        und.setDatos();
        dao.altaUnidad(und);

    }

    private static void crearConvocatoria(IDao dao) {
        ConvocatoriaExamen con = new ConvocatoriaExamen();
        con.setDatos();
        dao.altaConvocatoria(con);
    }

    private static void mostrarConvocatoria(IDao dao) {
        Map<Integer, Enunciado> enunciados = new HashMap<>();
        enunciados = dao.getEnunciados();
        ArrayList<ConvocatoriaExamen> convocatorias;
        if (enunciados.isEmpty()) {
            System.out.println("no hay ningun enunciados");
        } else {
            System.out.println("Estos son los enunciados que hay ");
            for (Enunciado en : enunciados.values()) {
                System.out.println(en.toString());
            }
            Integer id = utilidades.Utilidades.leerInt("introduce el id de un enunciado");
            if (enunciados.containsKey(id)) {
                convocatorias = (ArrayList<ConvocatoriaExamen>) dao.getConvocatoriasEnunciado(id);
                for (ConvocatoriaExamen con : convocatorias) {
                    System.out.println(con.toString());
                }
            } else {
                System.out.println("no existen enunciados con ese id");
            }
        }
    }

    private static void crearEnunciado(IDao dao) {

        ArrayList<Integer> ids = new ArrayList<>();
        Enunciado enunciado = new Enunciado();
        Map<Integer, UnidadDidactica> unDidacticas = dao.getUnidades();
        if (unDidacticas.isEmpty()) {
            System.out.println("no existen unidades didacticas  con ese id");
        } else {
            do {
                System.out.println("Estas son las  unidades didacticas para que escogas");
                for (UnidadDidactica und : unDidacticas.values()) {
                    System.out.println(" " + und.toString());
                }
                Integer idUn = utilidades.Utilidades.leerInt("Escoge un id de la unidad didactica");
                ids.add(idUn);
            } while (utilidades.Utilidades.leerRespuesta("Quieres introducir mas ?"));

            System.out.println("Introduce el enunciado");
            enunciado.setDatos();
            dao.altaEnunciado(enunciado);
            Map<Integer, Enunciado> enunciados = dao.getEnunciados();
            dao.altaUnidadEnunciado(ids, dao.getUltimoId());
            asignarConvocatoriaEnunciado(dao, dao.getUltimoId());
        }
    }

    private static void mostrarEnunciado(IDao dao) {
        Map<Integer, UnidadDidactica> unDidacticas = dao.getUnidades();

        if (unDidacticas.isEmpty()) {
            System.out.println("no existen unidades didacticas");
        } else {
            System.out.println("Estas son las  unidades didacticas para que escogas");
            for (UnidadDidactica und : unDidacticas.values()) {
                System.out.println(und.toString());
            }
            Integer idUn = utilidades.Utilidades.leerInt("Escoge un id de la unidad didactica");
            ArrayList<Enunciado> enunciados = (ArrayList<Enunciado>) dao.getEnunciadosUnidad(idUn);

            for (Enunciado en : enunciados) {
                System.out.println(en.toString());
            }
        }

    }

    private static void asignarConvocatoriaEnunciado(IDao dao, Integer idEnunciado) {
        Map<Integer, Enunciado> enunciados = dao.getEnunciados();
        ArrayList<ConvocatoriaExamen> convocatorias = (ArrayList<ConvocatoriaExamen>) dao.getConvocatoriasSinEnunciado();
        if (idEnunciado == null) {
            if (enunciados.isEmpty() || convocatorias.isEmpty()) {
                System.out.println("no hay ningun enunciado o convocatoria");
            } else {

                System.out.println("Estas son las convocatorias");
                for (ConvocatoriaExamen con : convocatorias) {
                    System.out.println(con.toString());
                }

                String convocatoria = utilidades.Utilidades.introducirCadena("Introduce una convocatoria");

                System.out.println("Estos son los enunciados que hay ");
                for (Enunciado en : enunciados.values()) {
                    System.out.println(en.toString());
                }
                Integer id = utilidades.Utilidades.leerInt("Introduce el id de un enunciado");

                dao.putEnunciadoInConvocatoria(id, convocatoria);
            }
        } else {
            if (!convocatorias.isEmpty()) {
                System.out.println("Estas son las convocatorias");
                for (ConvocatoriaExamen con : convocatorias) {
                    System.out.println(con.toString());
                }

                String convocatoria = utilidades.Utilidades.introducirCadena("Introduce una convocatoria");
                dao.putEnunciadoInConvocatoria(idEnunciado, convocatoria);
            } else {
                System.out.println("no hay ninguna convocatoria");
            }
        }

    }

    private static void abrirEnunciado(IDao dao) {
        Map<Integer, Enunciado> enunciados = new HashMap<>();
        Integer id;
        String ruta;
        
        enunciados = dao.getEnunciados();
        if (enunciados.isEmpty()) {
            System.out.println("no hay ningun enunciados");
        } else {
            System.out.println("Estos son los enunciados que hay ");
            for (Enunciado en : enunciados.values()) {
                System.out.println(en.toString());
            }
            id = utilidades.Utilidades.leerInt("introduce el id de un enunciado");
            
            ruta = enunciados.get(id).getRuta();
            
            ProcessBuilder processBuilder = new ProcessBuilder("Notepad.exe",ruta);
            try {
                processBuilder.start();
                        
                        } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
