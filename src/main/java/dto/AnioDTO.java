/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Sergio Arturo
 */
public class AnioDTO {
    
    private long id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String diaRendicionCuentas;
    private List<String> evaluacionSemana;
    private List<TacticaDTO> listaTacticasDTO;
    private boolean completado;
    private boolean activo;

    
    public AnioDTO() {
    }

    public AnioDTO(long id, String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, String diaRendicionCuentas, List<String> evaluacionSemana, List<TacticaDTO> listaTacticasDTO, boolean completado, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.diaRendicionCuentas = diaRendicionCuentas;
        this.evaluacionSemana = evaluacionSemana;
        this.listaTacticasDTO = listaTacticasDTO;
        this.completado = completado;
        this.activo = activo;
    }

    public AnioDTO(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, String diaRendicionCuentas, List<String> evaluacionSemana, List<TacticaDTO> listaTacticasDTO, boolean completado, boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.diaRendicionCuentas = diaRendicionCuentas;
        this.evaluacionSemana = evaluacionSemana;
        this.listaTacticasDTO = listaTacticasDTO;
        this.completado = completado;
        this.activo = activo;
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

    
    public List<String> getEvaluacionSemana() {
        return evaluacionSemana;
    }

    public void setEvaluacionSemana(List<String> evaluacionSemana) {
        this.evaluacionSemana = evaluacionSemana;
    }

    public List<TacticaDTO> getListaTacticasDTO() {
        return listaTacticasDTO;
    }

    public void setListaTacticasDTO(List<TacticaDTO> listaTacticasDTO) {
        this.listaTacticasDTO = listaTacticasDTO;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDiaRendicionCuentas() {
        return diaRendicionCuentas;
    }

    public void setDiaRendicionCuentas(String diaRendicionCuentas) {
        this.diaRendicionCuentas = diaRendicionCuentas;
    }
    

    
    
    
    
}
