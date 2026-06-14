/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.model;

import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;

/**
 *
 * @author camil
 */
public class Resultado {
    private String idResultado; 
    private String idEstudiante;
    private String nombreEstudiante;
    private String idQuiz; 
    private String tituloQuiz; 
    private int puntaje; 
    private int correctas; 
    private int incorrectas;
    private long tiempoSegundos; 
    private String fecha; 
    private String nivelAlcanzado;
    
    public Resultado() { 
// Captura la fecha/hora actual automáticamente 
this.fecha = LocalDateTime.now() 
        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")); 
    }
    public Resultado(String idResultado, String idEstudiante, 
            String nombreEstudiante, String idQuiz, 
            String tituloQuiz, int puntaje, 
            int correctas, int incorrectas, long tiempoSegundos) { 
        this.idResultado = idResultado; 
        this.idEstudiante = idEstudiante; 
        this.nombreEstudiante = nombreEstudiante; 
        this.idQuiz = idQuiz; 
        this.tituloQuiz = tituloQuiz; 
        this.puntaje = puntaje; 
        this.correctas = correctas; 
        this.incorrectas = incorrectas; 
        this.tiempoSegundos = tiempoSegundos; 
        this.fecha = LocalDateTime.now() 
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")); 
        this.nivelAlcanzado = calcularNivel(puntaje); 
    }
    public static String calcularNivel(int puntajeObtenido) { 
        if (puntajeObtenido <= 30) { 
            return "Principiante"; 
        } else if (puntajeObtenido <= 60) { 
            return "Explorador"; 
        } else if (puntajeObtenido <= 90) { 
            return "Experto"; 
        } else { 
            return "Maestro Matemático"; 
        } 
    }
    public String mostrarResultado() { 
        return "=== RESULTADO ===\n" 
                + "Quiz: " + tituloQuiz + "\n" 
                + "Puntaje: " + puntaje + "/100\n" 
                + "Correctas: " + correctas + "\n" 
                + "Incorrectas: " + incorrectas + "\n" 
                + "Tiempo: " + formatearTiempo() + "\n" 
                + "Nivel: " + nivelAlcanzado + "\n" 
                + "Fecha: " + fecha; 
    }
    public String formatearTiempo() { 
        long minutos = tiempoSegundos / 60; 
        long segundos = tiempoSegundos % 60; 
        return minutos + "m " + segundos + "s"; 
    }
    public String serializar() { 
        return idResultado + "|" 
                + idEstudiante + "|" 
                + nombreEstudiante + "|" 
                + idQuiz + "|" 
                + tituloQuiz + "|" 
                + puntaje + "|" 
                + correctas + "|" 
                + incorrectas + "|" 
                + tiempoSegundos + "|" 
                + fecha + "|" 
                + nivelAlcanzado;
    }
    public static Resultado deserializar(String linea) { 
        String[] partes = linea.split("\\|"); 
        if (partes.length < 11) return null; 
        
        Resultado r = new Resultado(); 
        r.setIdResultado(partes[0]); 
        r.setIdEstudiante(partes[1]); 
        r.setNombreEstudiante(partes[2]); 
        r.setIdQuiz(partes[3]); 
        r.setTituloQuiz(partes[4]); 
        r.setPuntaje(Integer.parseInt(partes[5])); 
        r.setCorrectas(Integer.parseInt(partes[6])); 
        r.setIncorrectas(Integer.parseInt(partes[7])); 
        r.setTiempoSegundos(Long.parseLong(partes[8])); 
        r.setFecha(partes[9]); 
        r.setNivelAlcanzado(partes[10]); 
        return r; 
    }
    @Override 
    public String toString() { 
        return "Resultado{" 
                + "quiz='" + tituloQuiz + '\'' 
                + ", puntaje=" + puntaje 
                + ", fecha='" + fecha + '\'' 
                + '}'; 
    }
    public String getIdResultado() { return idResultado; } 
    public void setIdResultado(String idResultado) { 
        this.idResultado = idResultado; 
    } 
    public String getIdEstudiante() { return idEstudiante; } 
    public void setIdEstudiante(String idEstudiante) { 
        this.idEstudiante = idEstudiante; 
    } 
    public String getNombreEstudiante() { return nombreEstudiante; } 
    public void setNombreEstudiante(String nombreEstudiante) { 
        this.nombreEstudiante = nombreEstudiante; 
    } 
    public String getIdQuiz() { return idQuiz; } 
    public void setIdQuiz(String idQuiz) { 
        this.idQuiz = idQuiz; 
   } 
    public String getTituloQuiz() { return tituloQuiz; } 
    public void setTituloQuiz(String tituloQuiz) { 
        this.tituloQuiz = tituloQuiz; 
    } 
    public int getPuntaje() { return puntaje; } 
    public void setPuntaje(int puntaje) { 
        this.puntaje = puntaje; 
    } 
    public int getCorrectas() { return correctas; } 
    public void setCorrectas(int correctas) { 
        this.correctas = correctas; 
    } 
    public int getIncorrectas() { return incorrectas; } 
    public void setIncorrectas(int incorrectas) { 
        this.incorrectas = incorrectas; 
    } 
    public long getTiempoSegundos() { return tiempoSegundos; } 
    public void setTiempoSegundos(long tiempoSegundos) { 
        this.tiempoSegundos = tiempoSegundos; 
    } 
    public String getFecha() { return fecha; } 
    public void setFecha(String fecha) { 
        this.fecha = fecha; 
    } 
    public String getNivelAlcanzado() { return nivelAlcanzado; } 
    public void setNivelAlcanzado(String nivelAlcanzado) { 
        this.nivelAlcanzado = nivelAlcanzado; 
    }
}


