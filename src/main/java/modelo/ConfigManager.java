/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import patrones.IPatronObservador;
import util.AppConfig;

/**
 *
 * @author Sergio Arturo
 */
public final class ConfigManager {
    

    private static final Gson GSON =
            new GsonBuilder().setPrettyPrinting().create();

    private static final Path CONFIG_PATH = Path.of(
            System.getProperty("user.home"),
            "appdata",
            "Roaming",
            "Objetivos12Semanas",
            "config.json"
    );
   
    

    private static AppConfig config;

    private static final Set<IPatronObservador> observers = new HashSet<>();

    
    
    public static void addObserver(IPatronObservador o) {
        observers.add(o);
    }

    public static void removeObserver(IPatronObservador o) {
        observers.remove(o);
    }

    private static void notifyObservers() {
        for (IPatronObservador o : new HashSet<>(observers)) {
            o.onColorSaved();
        }
    }

    private ConfigManager() {
    
    }

    
    //Cargar o crear config
    public static void load() {
        try {
            if (Files.notExists(CONFIG_PATH)) {
                Files.createDirectories(CONFIG_PATH.getParent());
                config = new AppConfig();
                save();
                return;
            }

            String json = Files.readString(CONFIG_PATH);
            config = GSON.fromJson(json, AppConfig.class);

        } catch (IOException e) {
            e.printStackTrace();
            config = new AppConfig(); // fallback seguro
        }
    }

    //Guardar config
    public static void save() {
        try {
            String json = GSON.toJson(config);
            Files.writeString(CONFIG_PATH, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Accesores útiles
    public static Color getPrimaryColor() {
        return Color.decode(config.ui.primaryColor);
    }

    public static void setPrimaryColor(Color color) {
        config.ui.primaryColor = toHex(color);
        notifyObservers();
        //save();
    }
    
    public static Color getSecondaryColor() {
        return Color.decode(config.ui.secondaryColor);
    }

    public static void setSecondaryColor(Color color) {
        config.ui.secondaryColor = toHex(color);
        notifyObservers();
        //save();
    }
    
    public static Color getTextColor() {
        return Color.decode(config.ui.textColor);
    }

    public static void setTextColor(Color color) {
        config.ui.textColor = toHex(color);
        //save();
    }
    
    
    public static Color getBackgroundColor() {
        return Color.decode(config.ui.backgroundColor);
    }

    public static void setBackgroundColor(Color color) {
        config.ui.backgroundColor = toHex(color);
        //save();
    }
    
    
    public static Color getHoverColor() {
        return Color.decode(config.ui.hoverColor);
    }

    public static void setHoverColor(Color color) {
        config.ui.hoverColor = toHex(color);
        //save();
    }
    
    public static Color getBackgroundOptionColor(){
        return Color.decode(config.ui.backgroundOptionColor);
    }
    
    
    
    public static Color getDeleteColor(){
        return Color.decode(config.ui.deleteColor);
    }
    
    public static Color getAcceptColor(){
        return Color.decode(config.ui.acceptColor);
    }
    
    public static Color getWarningColor(){
        return Color.decode(config.ui.warningColor);
    }
    
    public static Color getUnAvailableColor(){
        return Color.decode(config.ui.unAvailableColor);
    }
    

    public static String toHex(Color c) {
        if (c == null) {
            return config.ui.secondaryColor; // o el color por defecto que tú decidas
        }

        return String.format(
            "#%02x%02x%02x",
            c.getRed(),
            c.getGreen(),
            c.getBlue()
        );
    }
    
    
}

    

