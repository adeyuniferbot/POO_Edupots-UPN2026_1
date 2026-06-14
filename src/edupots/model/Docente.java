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
public class Docente extends Usuario {
// ========================================================= 
// ATRIBUTOS PROPIOS 
// =========================================================
    private String idDocente; 
    private String especialidad;
    
    private ArrayList<Quiz> quizzesCreados;
// ========================================================= 
// CONSTRUCTORES 
// =========================================================
    public Docente() { 
        super(); 
        this.quizzesCreados = new ArrayList<>(); 
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    public Docente(String idUsuario, String nombre, String correo, 
            String contrasena, String idDocente, String especialidad) { 
        super(idUsuario, nombre, correo, contrasena, "DOCENTE"); 
        this.idDocente = idDocente; 
        this.especialidad = especialidad; 
        this.quizzesCreados = new ArrayList<>(); 
    }
// ========================================================= 
// IMPLEMENTACIÓN DE MÉTODOS ABSTRACTOS 
// =========================================================
    @Override 
    public boolean iniciarSesion() { 
        System.out.println("Docente " + getNombre() + " ha iniciado sesión."); 
        return true; 
    } 
    @Override 
    public void cerrarSesion() { 
        System.out.println("Docente " + getNombre() + " ha cerrado sesión."); 
    }
// ========================================================= 
// MÉTODOS PROPIOS DEL DOCENTE 
// =========================================================
    public void crearQuiz(Quiz quiz) { 
        if (quiz != null) { 
            quizzesCreados.add(quiz); 
            System.out.println("Quiz '" + quiz.getTitulo() 
                    + "' creado por " + getNombre()); 
        } 
    }
    public Progreso visualizarProgreso(Estudiante estudiante) { 
        if (estudiante == null) return null; 
        Progreso p = new Progreso(); 
        p.setQuizzesResueltos(estudiante.getQuizzesCompletados()); 
        p.setPromedio(estudiante.calcularPromedio()); 
        p.actualizarProgreso(); 
        return p; 
    }
    @Override 
    public String toString() { 
        return "Docente{" 
                + "id='" + getIdUsuario() + '\'' 
                + ", nombre='" + getNombre() + '\'' 
                + ", especialidad='" + especialidad + '\'' 
                + '}'; 
    }
// ========================================================= 
// GETTERS Y SETTERS 
// =========================================================
    public String getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(String idDocente) {
        this.idDocente = idDocente;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public ArrayList<Quiz> getQuizzesCreados() { return quizzesCreados; } 
    public void setQuizzesCreados(ArrayList<Quiz> quizzesCreados) { 
        this.quizzesCreados = quizzesCreados; 
    }
}
