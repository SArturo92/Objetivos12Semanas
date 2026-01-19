/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dto.AnioDTO;
import excepciones.DAOException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import patrones.LocalDateAdapter;

/**
 *
 * @author Sergio Arturo
 */
public class Anio12SemanasLocalDAO implements IAnio12SemanasDAO{

    
    private final Gson GSON =
            new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .setPrettyPrinting().create();

    private final Path DATA_PATH = Path.of(
            System.getProperty("user.home"),
            "appdata",
            "Roaming",
            "Objetivos12Semanas",
            "objetivos.json"
    );
    
    
    
    @Override
    public List<AnioDTO> cargarTodos() throws DAOException{
        try {
            if (Files.notExists(DATA_PATH)) {
                return new ArrayList<>();
            }

            String json = Files.readString(DATA_PATH);

            Type type = new TypeToken<List<AnioDTO>>(){}.getType();
            List<AnioDTO> lista = GSON.fromJson(json, type);

            return lista != null ? lista : new ArrayList<>();

            }
        
        catch (IOException e) {
            throw new DAOException("Error cargando Anios", e);
        }
    
        
    }
    
    
    @Override
    public AnioDTO cargarAnio() throws DAOException {
        return cargarTodos().stream()
                .filter(AnioDTO::isActivo)
                .findFirst()
                .orElse(null);

    }

    @Override
    public AnioDTO guardarAnio(AnioDTO anio) throws DAOException {
         try {
            List<AnioDTO> lista = cargarTodos();

            if (anio.getId() == 0) {
                anio.setId(System.nanoTime());
            }

            lista.add(anio);

            Files.createDirectories(DATA_PATH.getParent());
            Files.writeString(DATA_PATH, GSON.toJson(lista));

            return anio;

        }
         catch (IOException e) {
            throw new DAOException("Error guardando Anio", e);
        }
    }

    @Override
    public AnioDTO editarAnio(AnioDTO anio) throws DAOException {
        try {
            List<AnioDTO> lista = cargarTodos();

            boolean encontrado = false;

            for (int i = 0; i < lista.size(); i++) {
                AnioDTO actual = lista.get(i);

                if (actual.getId() == anio.getId()) {
                    lista.set(i, anio);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                throw new DAOException(
                    "No se encontró Anio con id: " + anio.getId()
                );
            }

            Files.createDirectories(DATA_PATH.getParent());
            Files.writeString(DATA_PATH, GSON.toJson(lista));

            return anio;

        } catch (IOException e) {
            throw new DAOException("Error editando Anio", e);
        }    
    
    }

    @Override
    public void eliminarAnio(AnioDTO anioEliminar) throws DAOException {
        try {
            List<AnioDTO> lista = cargarTodos();

            boolean eliminado = lista.removeIf(anio -> anio.getId() == anioEliminar.getId());

            if (!eliminado) {
                throw new DAOException("No se encontró Anio con id: " + anioEliminar.getId());
            }

            Files.createDirectories(DATA_PATH.getParent());
            Files.writeString(DATA_PATH, GSON.toJson(lista));

        }
            
        catch (IOException e) {
            throw new DAOException("Error eliminando Anio", e);
        }
    }
    
}
