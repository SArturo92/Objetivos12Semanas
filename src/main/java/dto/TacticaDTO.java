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
public class TacticaDTO {
    
    private long id;
    private String nombre;
    private List DiasDTO;
    private boolean completado;

    
    public TacticaDTO() {
    }

    public TacticaDTO(long id, String nombre, List DiasDTO, boolean completado) {
        this.id = id;
        this.nombre = nombre;
        this.DiasDTO = DiasDTO;
        this.completado = completado;
    }

    public TacticaDTO(String nombre, List DiasDTO, boolean completado) {
        this.nombre = nombre;
        this.DiasDTO = DiasDTO;
        this.completado = completado;
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

    public List getDiasDTO() {
        return DiasDTO;
    }

    public void setDiasDTO(List DiasDTO) {
        this.DiasDTO = DiasDTO;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
    
    
    
    
    
    
    
}
