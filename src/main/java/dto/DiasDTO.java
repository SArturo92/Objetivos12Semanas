/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;

/**
 *
 * @author Sergio Arturo
 */
public class DiasDTO {
    
    private int id;
    private String nombre;
    private LocalDate fecha;
    private boolean seleccionado;
    private boolean completado;

    
    public DiasDTO() {
    }

    public DiasDTO(int id, String nombre, boolean completado, boolean seleccionado, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.completado = completado;
        this.seleccionado = seleccionado;
        this.fecha = fecha;
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

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    




    
    
    
    
}
