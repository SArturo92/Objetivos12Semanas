/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.List;

/**
 *
 * @author Sergio Arturo
 */
public class AnioDTO {
    
    private long id;
    private String nombre;
    private String descripcion;
    private List evaluacionSemana;
    private List listaTacticasDTO;
    private boolean completado;

    
    public AnioDTO() {
    }

    public AnioDTO(long id, String nombre, String descripcion, List evaluacionSemana, List listaTacticasDTO, boolean completado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.listaTacticasDTO = listaTacticasDTO;
        this.completado = completado;
        this.evaluacionSemana = evaluacionSemana;
    }

    public AnioDTO(String nombre, String descripcion, List evaluacionSemana, List listaTacticasDTO, boolean completado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.listaTacticasDTO = listaTacticasDTO;
        this.completado = completado;
        this.evaluacionSemana = evaluacionSemana;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List getlistaTacticasDTO() {
        return listaTacticasDTO;
    }

    public void setlistaTacticasDTO(List listaTacticasDTO) {
        this.listaTacticasDTO = listaTacticasDTO;
    }
    
    public boolean isCompletado(){
        return completado;
    }
    
    public void setCompletado(boolean completado){
        this.completado = completado;
    }

    public List getEvaluacionSemana() {
        return evaluacionSemana;
    }

    public void setEvaluacionSemana(List evaluacionSemana) {
        this.evaluacionSemana = evaluacionSemana;
    }
    
    
    
    
}
