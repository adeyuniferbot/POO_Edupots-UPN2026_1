/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.model;
import java.util.ArrayList;
/**
 *
 * @author camil
 */
public class Quiz {
    public static final int PUNTOS_POR_CORRECTA = 10;
    
    public static final int TOTAL_PREGUNTAS = 10;
    
    private String idQuiz; 
    private String titulo; 
    private String tema; 
    private String descripcion; 
    private int puntaje; 
    private String imagenRuta;
    
    private ArrayList<Pregunta> preguntas;
    
    public Quiz() { 
        this.preguntas = new ArrayList<>(); 
        this.puntaje = 0; 
    }
    public Quiz(String idQuiz, String titulo, String tema, 
            String descripcion, String imagenRuta) { 
        this.idQuiz = idQuiz; 
        this.titulo = titulo; 
        this.tema = tema; 
        this.descripcion = descripcion; 
        this.imagenRuta = imagenRuta; 
        this.puntaje = 0; 
        this.preguntas = new ArrayList<>(); 
    }
    public void iniciarQuiz() { 
        this.puntaje = 0; 
        System.out.println("Iniciando quiz: " + titulo); 
    }
    public int calcularPuntaje(int respuestasCorrectas) { 
        this.puntaje = respuestasCorrectas * PUNTOS_POR_CORRECTA; 
        return this.puntaje; 
    }
    public void agregarPregunta(Pregunta pregunta) { 
        if (pregunta != null) { 
            preguntas.add(pregunta); 
        } 
    }
    public Pregunta getPreguntaEnIndice(int indice) { 
        if (indice >= 0 && indice < preguntas.size()) { 
            return preguntas.get(indice); 
        } 
        return null; 
    }
    public boolean estaCompleto() { 
        return preguntas.size() == TOTAL_PREGUNTAS; 
    }
    @Override 
    public String toString() { 
        return "Quiz{" 
                + "id='" + idQuiz + '\'' 
                + ", titulo='" + titulo + '\'' 
                + ", tema='" + tema + '\'' 
                + ", preguntas=" + preguntas.size() 
                + '}'; 
    }
    public String getIdQuiz() { return idQuiz; } 
    public void setIdQuiz(String idQuiz) { this.idQuiz = idQuiz; } 
    
    public String getTitulo() { return titulo; } 
    public void setTitulo(String titulo) { this.titulo = titulo; } 
    
    public String getTema() { return tema; } 
    public void setTema(String tema) { this.tema = tema; } 
    
    public String getDescripcion() { return descripcion; } 
    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; } 
    
    public int getPuntaje() { return puntaje; } 
    public void setPuntaje(int puntaje) { this.puntaje = puntaje; } 
    
    public String getImagenRuta() { return imagenRuta; } 
    public void setImagenRuta(String imagenRuta) { 
        this.imagenRuta = imagenRuta; } 
    
    public ArrayList<Pregunta> getPreguntas() { return preguntas; } 
    public void setPreguntas(ArrayList<Pregunta> preguntas) { 
        this.preguntas = preguntas; 
    }
}
