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
public class Estudiante extends Usuario {
// ========================================================= 
// ATRIBUTOS PROPIOS 
// =========================================================
    private String idEstudiante; 
    private String grado; 
    private int puntajeTotal; 
    private int quizzesCompletados;
    
    private ArrayList<Resultado> listaResultados;
private ArrayList<Insignia> insigniasObtenidas;
// ========================================================= 
// CONSTRUCTORES 
// =========================================================
    public Estudiante() { 
        super(); 
        this.listaResultados = new ArrayList<>(); 
        this.insigniasObtenidas = new ArrayList<>(); 
        this.puntajeTotal = 0; 
        this.quizzesCompletados = 0; 
        
    }
    public Estudiante(String idUsuario, String nombre, String correo, 
            String contrasena, String idEstudiante, String grado) { 
        super(idUsuario, nombre, correo, contrasena, "ESTUDIANTE"); 
        this.idEstudiante = idEstudiante; 
        this.grado = grado; this.puntajeTotal = 0; 
        this.quizzesCompletados = 0; 
        this.listaResultados = new ArrayList<>(); 
        this.insigniasObtenidas = new ArrayList<>(); 
        
    }
// ========================================================= 
// IMPLEMENTACIÓN DE MÉTODOS ABSTRACTOS 
// =========================================================
@Override 
    public boolean iniciarSesion() { 
        System.out.println("Estudiante " + getNombre() + " ha iniciado sesión."); 
        return true; 
    }
@Override 
     public void cerrarSesion() { 
         System.out.println("Estudiante " + getNombre() + " ha cerrado sesión.");
}
// ========================================================= 
// MÉTODOS PROPIOS DEL ESTUDIANTE 
// =========================================================
     public void resolverQuiz(Resultado resultado) { 
         if (resultado != null) { 
             listaResultados.add(resultado);
             puntajeTotal += resultado.getPuntaje(); 
             quizzesCompletados += 1; 
         }
     }
     public Resultado visualizarResultados() { 
         if (!listaResultados.isEmpty()) { 
             return listaResultados.get(listaResultados.size() - 1); 
         } 
         return null; 
     }
     public void agregarInsignia(Insignia insignia) { 
         boolean yaLaTiene = false;
         for (Insignia i : insigniasObtenidas) { 
             if (i.getIdInsignia().equals(insignia.getIdInsignia())) { 
                 yaLaTiene = true;
                 break; 
             }
         }
         if (!yaLaTiene) { 
             insigniasObtenidas.add(insignia); 
         } 
     }
     public double calcularPromedio() { 
         if (quizzesCompletados == 0) return 0.0; 
         return (double) puntajeTotal / quizzesCompletados; 
     }
     @Override 
     public String toString() { 
         return "Estudiante{" 
                 + "id='" + getIdUsuario() + '\'' 
                 + ", nombre='" + getNombre() + '\'' 
                 + ", grado='" + grado + '\'' 
                 + ", puntajeTotal=" + puntajeTotal 
                 + ", quizzesCompletados=" + quizzesCompletados 
                 + '}'; 
     }
// ========================================================= 
// GETTERS Y SETTERS 
// =========================================================
     public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public int getQuizzesCompletados() {
        return quizzesCompletados;
    }

    public void setQuizzesCompletados(int quizzesCompletados) {
        this.quizzesCompletados = quizzesCompletados;
    }
    public ArrayList<Resultado> getListaResultados() { return listaResultados; } 
    public void setListaResultados(ArrayList<Resultado> listaResultados) { 
        this.listaResultados = listaResultados; 
    } 
    public ArrayList<Insignia> getInsigniasObtenidas() { 
        return insigniasObtenidas; 
    } 
    public void setInsigniasObtenidas(ArrayList<Insignia> insigniasObtenidas) { 
        this.insigniasObtenidas = insigniasObtenidas; 
    } 
}