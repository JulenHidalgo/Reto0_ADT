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
        int opcion ;
        do{
            System.out.println("1. CREAR");
            System.out.println("2. VISUALIZAR");
            System.out.println("3. ASIGNAR ");
            System.out.println("4. SALIR");
            opcion = utilidades.Utilidades.leerInt("Introduce una opcion");
            
            switch (opcion){
                case 1:
                    menuCrear(); 
                    break;
                case 2:
                    menuVisualizar();
                    break;
                case 3:
                    break;
                default :
                    System.out.println("Esa opcion no existe");
                    break;
            }
            
        }while(opcion!=4);      
    }

    private static void menuCrear() {
        int opcion ;
        System.out.println("1. UNIDAD DIDACTICA");
        System.out.println("2. CONVOCATORIA");
        System.out.println("3. ENUNCIADO ");
        System.out.println("4. ATRAS ");
        opcion = utilidades.Utilidades.leerInt("Introduce una opcion");
         switch (opcion){
                case 1:
                     crearUD();
                    break;
                case 2:
                    crearConvocatoria();
                    break;
                case 3:
                    crearEnunciado();
                    break;
                case 4:
                    break;
                 default:
                     System.out.println("No existe esa opcion");
            }
         
    }

    private static void menuVisualizar() {
         int opcion ;
        System.out.println("1. VISUALIZAR UN ENUNCIADO POR LA UNIDAD DIDACTICA");
        System.out.println("2. VISUALIZAR CONVOCATORIO POR EL ENUNCIADO");
        System.out.println("3. ATRAS");
        opcion = utilidades.Utilidades.leerInt("Introduce una opcion");
         switch (opcion){
                case 1:
                   
                    break;
                case 2:
                    mostrarConvocatoria();
                    break;
            }
        
    }

    private static void crearUD() {
        UnidadDidactica und = new UnidadDidactica();
        und.setDatos();
        
    }

    private static void crearConvocatoria() {
        ArrayList<Enunciado> enunciados = new ArrayList();
        ConvocatoriaExamen con = new ConvocatoriaExamen();
        con.setDatos();
        if(enunciados.isEmpty()){
            System.out.println("no hay ningun enunciados");
        }else{
           System.out.println("Estos son los enunciados que hay ");
           for(Enunciado en:enunciados){
               en.toString();
            } 
           con.setId(utilidades.Utilidades.leerInt("Introduce el id de un enunciado"));
        }
        
    }

    private static void mostrarConvocatoria() {
        Map<Integer,Enunciado> enunciados = new HashMap<>();
        ArrayList<ConvocatoriaExamen> convocatorias = new ArrayList();
        if(enunciados.isEmpty()){
            System.out.println("no hay ningun enunciados");
        }else{
           System.out.println("Estos son los enunciados que hay ");
           for(Enunciado en:enunciados.values()){
               en.toString();
            } 
           Integer id = utilidades.Utilidades.leerInt("introduce el id de un enunciado");
           if(enunciados.containsKey(id)){
               for(ConvocatoriaExamen con:convocatorias){
                   if(con.getId()==id)
                       con.toString();
               }
           }else{
               System.out.println("no existen enunciados con ese id");
           }
        }
    }

    private static void crearEnunciado() {
       Enunciado enunciado = new Enunciado();
       
    }
    
}

