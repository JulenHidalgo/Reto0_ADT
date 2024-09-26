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
public class Enunciado {
    
    private Integer id;
    private String descripcion;
    private Dificultad nivel;
    private boolean disponible;
    private String ruta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Dificultad getNivel() {
        return nivel;
    }

    public void setNivel(Dificultad nivel) {
        this.nivel = nivel;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Enunciado{" + "id=" + id + ", acronimo=" + descripcion+ ", nivel=" + nivel + ", disponible=" + disponible + "ruta=" + ruta + '}';
    }
    
    public void setDatos( ){

        this.descripcion = utilidades.Utilidades.introducirCadena("introduce una descripcion");
        this.nivel = (Dificultad.valueOf(utilidades.Utilidades.introducirCadena("Introduce la dificultad")));
        this.disponible = utilidades.Utilidades.leerRespuesta("Esta disponible ?");    
        this.ruta = utilidades.Utilidades.introducirCadena("Introduce la ruta del archivo"); 
        
    }
    
}
