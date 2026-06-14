/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.model;

/**
 *
 * @author camil
 */
public class Insignia {
    public static final String ID_PRIMER_QUIZ = "INS001"; 
    public static final String ID_CINCO_QUIZZES = "INS002"; 
    public static final String ID_CIEN_PUNTOS = "INS003"; 
    public static final String ID_PERFECTO = "INS004";
    
    private String idInsignia; 
    private String nombre; 
    private String descripcion; 
    private String emoji; 
    private boolean obtenida; 
    private String fechaObtencion;
    
    public Insignia() { 
        this.obtenida = false; 
    }
    public Insignia(String idInsignia, String nombre, 
            String descripcion, String emoji) { 
        this.idInsignia = idInsignia; 
        this.nombre = nombre; 
        this.descripcion = descripcion; 
        this.emoji = emoji; 
        this.obtenida = false; 
        this.fechaObtencion = ""; 
    }
    public boolean verificarLogro(int quizzesCompletados, 
            int puntajeAcumulado, 
            int puntajeUltimoQuiz) { 
        if (obtenida) return false;
        boolean cumpleCondicion = false; 
        
        switch (idInsignia) { 
            case ID_PRIMER_QUIZ: 
                cumpleCondicion = (quizzesCompletados >= 1);
                break; 
            case ID_CINCO_QUIZZES: 
                cumpleCondicion = (quizzesCompletados >= 5); 
                break; 
            case ID_CIEN_PUNTOS: 
                cumpleCondicion = (puntajeAcumulado >= 100); 
                break; 
            case ID_PERFECTO: cumpleCondicion = (puntajeUltimoQuiz == 100); 
            break; 
        }
        if (cumpleCondicion) { 
            this.obtenida = true; 
            this.fechaObtencion = java.time.LocalDate.now().toString(); 
        } 
        return cumpleCondicion; 
    }
    public static Insignia[] crearInsigniasBase() { 
        return new Insignia[]{ new Insignia(ID_PRIMER_QUIZ, 
                " Primer Quiz", 
                "Completaste tu primer quiz", 
                ""), 
            new Insignia(ID_CINCO_QUIZZES, 
                    " Coleccionista", 
                    "Completaste 5 quizzes", 
                    ""), 
            new Insignia(ID_CIEN_PUNTOS, 
                    " Centenario", 
                    "Acumulaste 100 puntos", 
                    ""), 
                    new Insignia(ID_PERFECTO, 
                            " Puntaje Perfecto", 
                            "Obtuviste 100/100 en un quiz", 
                            "") 
        }; 
    }
    @Override 
    public String toString() { 
        return emoji + " " + nombre 
                + (obtenida ? " [OBTENIDA]" : " [BLOQUEADA]"); 
    }
    public String getIdInsignia() { return idInsignia; } 
    public void setIdInsignia(String id) { this.idInsignia = id; } 
    
    public String getNombre() { return nombre; } 
    public void setNombre(String nombre) { this.nombre = nombre; } 
    
    public String getDescripcion() { return descripcion; } 
    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; 
    } 
    public String getEmoji() { return emoji; } 
    public void setEmoji(String emoji) { this.emoji = emoji; } 
    
    public boolean isObtenida() { return obtenida; } 
    public void setObtenida(boolean obtenida) { 
        this.obtenida = obtenida; 
    } 
    public String getFechaObtencion() { return fechaObtencion; } 
    public void setFechaObtencion(String fecha) { 
        this.fechaObtencion = fecha; 
    }
}
