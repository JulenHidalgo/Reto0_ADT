/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import modelo.UnidadDidactica;
import modelo.ConvocatoriaExamen;
import modelo.Enunciado;

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
                    asignarConvocatoriaEnunciado(dao);
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
        System.out.println("3. ATRAS");
        opcion = utilidades.Utilidades.leerInt("Introduce una opcion");
        switch (opcion) {
            case 1:
                mostrarEnunciado(dao);
                break;
            case 2:
                mostrarConvocatoria(dao);
                break;
            case 3:
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
                en.toString();
            }
            Integer id = utilidades.Utilidades.leerInt("introduce el id de un enunciado");
            if (enunciados.containsKey(id)) {
                convocatorias = (ArrayList<ConvocatoriaExamen>) dao.getConvocatoriasEnunciado(id);
                for (ConvocatoriaExamen con : convocatorias) {
                    con.toString();
                }
            } else {
                System.out.println("no existen enunciados con ese id");
            }
        }
    }

    private static void crearEnunciado(IDao dao) {
        ArrayList<Integer> ids = new ArrayList<>();
        Enunciado enunciado = new Enunciado();
        Map<Integer, UnidadDidactica> unDidacticas = new HashMap<>();
        if (unDidacticas.isEmpty()) {
            System.out.println("no existen unidades didacticas  con ese id");
        } else {
            do {
                System.out.println("Estas son las  unidades didacticas para que escogas");
                for (UnidadDidactica und : unDidacticas.values()) {
                    und.toString();
                }
                Integer idUn = utilidades.Utilidades.leerInt("Escoge un id de la unidad didactica");
                ids.add(idUn);
            } while (utilidades.Utilidades.leerRespuesta("Quieres introducir mas ?"));

            System.out.println("Introduce el enunciado");
            enunciado.setDatos();
            dao.altaEnunciado(enunciado);

        }

    }

    private static void mostrarEnunciado(IDao dao) {
        Map<Integer, UnidadDidactica> unDidacticas = new HashMap<>();

        if (unDidacticas.isEmpty()) {
            System.out.println("no existen unidades didacticas");
        } else {
            System.out.println("Estas son las  unidades didacticas para que escogas");
            for (UnidadDidactica und : unDidacticas.values()) {
                und.toString();
            }
            Integer idUn = utilidades.Utilidades.leerInt("Escoge un id de la unidad didactica");
            Map<Integer, Enunciado> enunciados = new HashMap<>();
            enunciados = dao.getEnunciados();

            for (Enunciado en : enunciados.values()) {
                en.toString();
            }
        }

    }

    private static void asignarConvocatoriaEnunciado(IDao dao) {
        Map<Integer, Enunciado> enunciados = dao.getEnunciados();
        ArrayList<ConvocatoriaExamen> convocatorias = (ArrayList<ConvocatoriaExamen>) dao.getConvocatorias();
        if (enunciados.isEmpty() || convocatorias.isEmpty()) {
            System.out.println("no hay ningun enunciado o convocatoria");
        } else {
            System.out.println("Estos son los enunciados que hay ");
            for (Enunciado en : enunciados.values()) {
                en.toString();
            }
            Integer id = utilidades.Utilidades.leerInt("Introduce el id de un enunciado");

            System.out.println("Estas son las convocatorias");
            for (ConvocatoriaExamen con : convocatorias) {
                con.toString();
            }

            String convocatoria = utilidades.Utilidades.introducirCadena("Introduce una convocatoria");
        }
    }

}
