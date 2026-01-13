/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patrones;

/**
 *
 * @author Sergio Arturo
 */
public class PatronFormularioNuevoAnio {
    
    
    private boolean nombreValido;
    private boolean objetivoValido;
    private boolean tacticasValidas;
    private boolean frecuenciaValida;

    public void setNombreValido(boolean v) {
        nombreValido = v;
    }

    public void setObjetivoValido(boolean v) {
        objetivoValido = v;
    }

    public void setTacticasValidas(boolean v) {
        tacticasValidas = v;
    }

    public void setFrecuenciaValida(boolean v) {
        frecuenciaValida = v;
    }

    
    public boolean esValido() {
        return nombreValido &&
               objetivoValido &&
               tacticasValidas &&
               frecuenciaValida;
    }

    @Override
    public String toString() {
        return "FormularioNuevoAnio{" + "nombreValido=" + nombreValido + ", objetivoValido=" + objetivoValido + ", tacticasValidas=" + tacticasValidas + ", frecuenciaValida=" + frecuenciaValida + '}';
    }
    
    
    
}
