/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import java.util.Map;
import modelo.ConvocatoriaExamen;
import modelo.Enunciado;
import modelo.UnidadDidactica;

/**
 *
 * @author 2dam
 */
public interface IDao {
    
    public void altaUnidad(UnidadDidactica unidad);
    
    public void altaConvocatoria(ConvocatoriaExamen convocatoria);
    
    public void altaEnunciado(Enunciado enunciado);
    
    public void altaUnidadEnunciado(List<Integer> idUnidad);
    
    public void putEnunciadoInConvocatoria(String convocatoria);
    
    public List<UnidadDidactica> getUnidades();
    
    public List<ConvocatoriaExamen> getConvocatorias();
    
    //public List<Enunciado> getEnunciados();
     public Map<Integer, Enunciado> getEnunciados();
    
    public List<Enunciado> getEnunciadosUnidad(Integer idUnidad);
    
    public List<ConvocatoriaExamen> getConvocatoriasEnunciado(Integer Enunciado);
    
   public List<ConvocatoriaExamen> getConvocatoriasSinEnunciado();
   
   public void putEnunciadoInConvocatoria(Integer idEnunciado, String convocatoria);
    
}