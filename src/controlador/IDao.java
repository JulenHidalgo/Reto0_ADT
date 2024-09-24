/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
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
    
    public void altaUnidadEnunciado(List<Integer> idUnidade, Integer idEnunciado);
    
    public List<UnidadDidactica> getUnidades();
    
    public List<Enunciado> getEnunciados();
    
    public List<Enunciado> getEnunciadosUnidad(Integer idUnidad);
    
    public List<ConvocatoriaExamen> getConvocatoriasEnunciado(Integer enunciado);
    
   public List<ConvocatoriaExamen> getConvocatoriasSinEnunciado();
   
   public void putEnunciadoInConvocatoria(Integer idEnunciado, String convocatoria);
    
}
