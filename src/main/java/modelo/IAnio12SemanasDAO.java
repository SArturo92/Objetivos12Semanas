/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo;

import dto.AnioDTO;
import excepciones.DAOException;
import java.util.List;

/**
 *
 * @author Sergio Arturo
 */
public interface IAnio12SemanasDAO {
    
    
    public List<AnioDTO> cargarTodos() throws DAOException;
    
    public AnioDTO cargarAnio() throws DAOException;
    
    public AnioDTO guardarAnio(AnioDTO anio) throws DAOException;
    
    public AnioDTO editarAnio(AnioDTO anio) throws DAOException;
    
    public void eliminarAnio(AnioDTO anio) throws DAOException;
    
}
