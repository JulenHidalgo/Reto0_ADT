/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author 2dam
 */
public class UnidadDidactica {
    
    private Integer id;
    private String acronimo;
    private String titulo;
    private String evaluacion;
    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "UnidadDidactica{" + "id=" + id + ", acronimo=" + acronimo + ", titulo=" + titulo + ", evaluacion=" + evaluacion + ", descripcion=" + descripcion + '}';
    }
    

    public void setDatos( ){
        this.acronimo = utilidades.Utilidades.introducirCadena("introduce un acronimo");
        this.titulo = utilidades.Utilidades.introducirCadena("introduce un titulo");
        this.evaluacion = utilidades.Utilidades.introducirCadena("introduce una evaluacion");
        this.descripcion = utilidades.Utilidades.introducirCadena("introduce una descripcion");
    }

    
}
