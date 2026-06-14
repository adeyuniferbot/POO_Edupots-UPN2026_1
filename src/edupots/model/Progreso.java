/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.model;

import java.util.ArrayList; 
import java.util.HashMap;
/**
 *
 * @author camil
 */
public class Progreso {
    public static final int NIVEL_PRINCIPIANTE = 1; 
    public static final int NIVEL_EXPLORADOR = 2; 
    public static final int NIVEL_EXPERTO = 3; 
    public static final int NIVEL_MAESTRO = 4;
    
    private String idProgreso; 
    private String idEstudiante; 
    private int nivel; 
    private int quizzesResueltos; 
    private double promedio; 
    private int puntajeAcumulado; 
    private int porcentajeAvance;
    
    private ArrayList<Insignia> insignias;
    
    private HashMap<String, Integer> estadisticasPorTema;
    
    public Progreso() { 
        this.nivel = NIVEL_PRINCIPIANTE; 
        this.quizzesResueltos = 0; 
        this.promedio = 0.0; 
        this.puntajeAcumulado = 0; 
        this.porcentajeAvance = 0; 
        this.insignias = new ArrayList<>(); 
        this.estadisticasPorTema = new HashMap<>(); 
    }
    public Progreso(String idProgreso, String idEstudiante) { 
        this(); 
        this.idProgreso = idProgreso; 
        this.idEstudiante = idEstudiante; 
    }
    public void actualizarProgreso() { 
// Calcular nivel basado en puntaje acumulado 
         if (puntajeAcumulado >= 300) { 
             nivel = NIVEL_MAESTRO;
        } else if (puntajeAcumulado >= 200) { 
            nivel = NIVEL_EXPERTO;
        } else if (puntajeAcumulado >= 100) { 
            nivel = NIVEL_EXPLORADOR;
        } else { 
            nivel = NIVEL_PRINCIPIANTE;
        } 
         switch (nivel) { 
             case NIVEL_PRINCIPIANTE: 
                 porcentajeAvance = (int) ((puntajeAcumulado / 100.0) * 100);
                 break; 
             case NIVEL_EXPLORADOR: 
                 porcentajeAvance = (int) (((puntajeAcumulado - 100) / 100.0) * 100); 
                 break; case NIVEL_EXPERTO: 
                     porcentajeAvance = (int) (((puntajeAcumulado - 200) / 100.0) * 100);
                     break; 
                 case NIVEL_MAESTRO: 
                     porcentajeAvance = 100; 
                     break; 
         }
         if (porcentajeAvance < 0) porcentajeAvance = 0; 
         if (porcentajeAvance > 100) porcentajeAvance = 100; 
    }
    public String getNombreNivel() { 
        switch (nivel) { 
            case NIVEL_PRINCIPIANTE: return "⭐ Principiante"; 
            case NIVEL_EXPLORADOR: return "⭐⭐ Explorador"; 
            case NIVEL_EXPERTO: return "⭐⭐⭐ Experto"; 
            case NIVEL_MAESTRO: return "⭐⭐⭐⭐ Maestro Matemático"; 
            default: return "Principiante"; 
        } 
    }
    public void registrarQuizPorTema(String tema) { 
        if (tema != null && !tema.isEmpty()) { 
// getOrDefault devuelve 0 si el tema no existe aún en el mapa 
              int actual = estadisticasPorTema.getOrDefault(tema, 0); 
              estadisticasPorTema.put(tema, actual + 1); 
        } 
    }
    public String serializar() { 
        return idProgreso + "|"
                + idEstudiante + "|"
                + nivel + "|" 
                + quizzesResueltos + "|" 
                + promedio + "|" 
                + puntajeAcumulado + "|" 
                + porcentajeAvance;
    }
    public static Progreso deserializar(String linea) { 
        String[] p = linea.split("\\|"); 
        if (p.length < 7) return null; 
        Progreso prog = new Progreso(); 
        prog.setIdProgreso(p[0]); 
        prog.setIdEstudiante(p[1]); 
        prog.setNivel(Integer.parseInt(p[2])); 
        prog.setQuizzesResueltos(Integer.parseInt(p[3])); 
        prog.setPromedio(Double.parseDouble(p[4])); 
        prog.setPuntajeAcumulado(Integer.parseInt(p[5])); 
        prog.setPorcentajeAvance(Integer.parseInt(p[6])); 
        return prog; 
    }
    @Override 
    public String toString() { 
        return "Progreso{" 
                + "nivel=" + getNombreNivel() 
                + ", quizzes=" + quizzesResueltos 
                + ", puntajeAcumulado=" + puntajeAcumulado 
                + ", avance=" + porcentajeAvance + "%" 
                + '}'; 
    }
    public String getIdProgreso() { return idProgreso; }
    public void setIdProgreso(String id) { this.idProgreso = id; } 
    
    public String getIdEstudiante() { return idEstudiante; } 
    public void setIdEstudiante(String id) { this.idEstudiante = id; } 
    
    public int getNivel() { return nivel; } 
    public void setNivel(int nivel) { this.nivel = nivel; } 
    
    public int getQuizzesResueltos() { return quizzesResueltos; }
    public void setQuizzesResueltos(int q) { this.quizzesResueltos = q; } 
    
    public double getPromedio() { return promedio; } 
    public void setPromedio(double promedio) { this.promedio = promedio; } 
    
    public int getPuntajeAcumulado() { return puntajeAcumulado; } 
    public void setPuntajeAcumulado(int p) { this.puntajeAcumulado = p; } 
    
    public int getPorcentajeAvance() { return porcentajeAvance; } 
    public void setPorcentajeAvance(int p) { this.porcentajeAvance = p; } 
    
    public ArrayList<Insignia> getInsignias() { return insignias; } 
    public void setInsignias(ArrayList<Insignia> insignias) { 
        this.insignias = insignias; 
    } 
    
    public HashMap<String, Integer> getEstadisticasPorTema() { 
        return estadisticasPorTema; 
    } 
    public void setEstadisticasPorTema(HashMap<String, Integer> mapa) { 
        this.estadisticasPorTema = mapa; 
    }
}
