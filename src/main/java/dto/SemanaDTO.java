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
public class SemanaDTO {
    
    
    private int id;
    private String nombre;
    private String rendicionCuentas;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<DiasDTO> listaDias;
    private float porcentaje;
    private boolean completado;
    

    public SemanaDTO() {
    }

    public SemanaDTO(int id, String nombre, String rendicionCuentas, LocalDate fechaInicio, LocalDate fechaFin, List<DiasDTO> listaDias, float porcentaje, boolean completado) {
        this.id = id;
        this.nombre = nombre;
        this.rendicionCuentas = rendicionCuentas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.listaDias = listaDias;
        this.porcentaje = porcentaje;
        this.completado = completado;
    }

    public SemanaDTO(String nombre, String rendicionCuentas, LocalDate fechaInicio, LocalDate fechaFin, List<DiasDTO> listaDias, float porcentaje, boolean completado) {
        this.nombre = nombre;
        this.rendicionCuentas = rendicionCuentas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.listaDias = listaDias;
        this.porcentaje = porcentaje;
        this.completado = completado;
    }


    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRendicionCuentas() {
        return rendicionCuentas;
    }

    public void setRendicionCuentas(String rendicionCuentas) {
        this.rendicionCuentas = rendicionCuentas;
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

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public List<DiasDTO> getListaDias() {
        return listaDias;
    }

    public void setListaDias(List<DiasDTO> listaDias) {
        this.listaDias = listaDias;
    }
    
    
    
    
}
