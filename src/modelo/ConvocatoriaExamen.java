/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author 2dam
 */
public class ConvocatoriaExamen {
    
    private String convocatoria;
    private String descripcion;
    private LocalDate fecha;
    private String curso;
    private Integer id;
    
    public String getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    

    @Override
    public String toString() {
        return "ConvocatoriaExamen{" + "convocatoria=" + convocatoria + ", descripcion=" + descripcion + ", fecha=" + fecha + ", curso=" + curso + '}';
    }
    
    public void setDatos( ){
        this.convocatoria = utilidades.Utilidades.introducirCadena("introduce la convocatoria");
        this.descripcion = utilidades.Utilidades.introducirCadena("introduce una descripcion");
        this.fecha = utilidades.Utilidades.pidoFechaDMA("introduce la fecha");
        this.curso = utilidades.Utilidades.introducirCadena("introduce el curso");
    }
    
}
