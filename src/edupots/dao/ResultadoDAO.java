/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.dao;
import edupots.model.Resultado; 
import java.util.ArrayList;
/**
 *
 * @author camil
 */
public class ResultadoDAO extends ArchivoDAO{
    public boolean guardar(Resultado resultado) {
        if (resultado == null) return false;
     String linea = resultado.serializar(); 
     boolean r1 = agregarLinea(RUTA_RESULTADOS, linea); 
     boolean r2 = agregarLinea(RUTA_HISTORIAL, linea); 
     return r1 && r2; 
    }
    public ArrayList<Resultado> leerTodos() {
        ArrayList<Resultado> resultados = new ArrayList<>(); 
        ArrayList<String> lineas = leerLineas(RUTA_RESULTADOS); 
        
        for (String linea : lineas) { 
            Resultado r = Resultado.deserializar(linea); 
            if (r != null) { 
                resultados.add(r);
            } 
        } 
        return resultados; 
    }
    public ArrayList<Resultado> leerPorEstudiante(String idEstudiante) { 
        ArrayList<Resultado> todos = leerTodos();
        ArrayList<Resultado> filtro = new ArrayList<>(); 
        
        for (Resultado r : todos) {
            if (r.getIdEstudiante().equals(idEstudiante)) { 
                filtro.add(r); 
            } 
        } 
        return filtro;
    }
    public double calcularPromedio(String idEstudiante) { 
        ArrayList<Resultado> resultados = leerPorEstudiante(idEstudiante); 
        if (resultados.isEmpty()) return 0.0; 
        int suma = 0;
        for (Resultado r : resultados) { 
            suma += r.getPuntaje(); 
        } 
        return (double) suma / resultados.size(); 
    }
    public int obtenerMayorPuntaje(String idEstudiante) { 
        ArrayList<Resultado> resultados = leerPorEstudiante(idEstudiante); 
        int mayor = 0; 
        for (Resultado r : resultados) { 
            if (r.getPuntaje() > mayor) { 
                mayor = r.getPuntaje(); 
            } 
        } 
        return mayor; 
    }
    public int obtenerMenorPuntaje(String idEstudiante) {
        ArrayList<Resultado> resultados = leerPorEstudiante(idEstudiante); 
        if (resultados.isEmpty()) return 0;
        
        int menor = Integer.MAX_VALUE; 
        for (Resultado r : resultados) { 
            if (r.getPuntaje() < menor) {
                menor = r.getPuntaje(); 
            } 
        } 
        return menor; 
    }
    public int contarQuizzes(String idEstudiante) { 
        return leerPorEstudiante(idEstudiante).size();
    }
}
