/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.dao;
import edupots.model.Pregunta;
import edupots.model.Quiz; 
import java.util.ArrayList;
/**
 *
 * @author camil
 */
public class QuizDAO extends ArchivoDAO {
    public ArrayList<Quiz> leerTodos() { 
        ArrayList<Quiz> quizzes = new ArrayList<>();
        ArrayList<String> lineas = leerLineas(RUTA_QUIZZES); 
        
        Quiz quizActual = null; 
        
        for (String linea : lineas) { 
            if (linea.startsWith("QUIZ|")) { quizActual = deserializarQuiz(linea);
            if (quizActual != null) { 
                quizzes.add(quizActual);
            }
            } else if (linea.startsWith("PREGUNTA|") && quizActual != null) { 
                Pregunta p = deserializarPregunta(linea);
                if (p != null) { 
                    quizActual.agregarPregunta(p);
                } 
            } 
        }
        if (quizzes.isEmpty()) { 
            quizzes = crearQuizzesPorDefecto();
            guardarTodos(quizzes); 
        }
        return quizzes; 
    }
    public Quiz buscarPorId(String idQuiz) { 
        ArrayList<Quiz> quizzes = leerTodos();
        for (Quiz q : quizzes) { 
            if (q.getIdQuiz().equals(idQuiz)) { 
                return q;
        }
        }
        return null;
    }
    public boolean guardarTodos(ArrayList<Quiz> quizzes) { 
        ArrayList<String> lineas = new ArrayList<>();
        for (Quiz q : quizzes) { 
            lineas.add(serializarQuiz(q));
            for (Pregunta p : q.getPreguntas()) { 
                lineas.add(serializarPregunta(p, q.getIdQuiz())); 
            } 
        } 
        return escribirLineas(RUTA_QUIZZES, lineas);
    }
    private String serializarQuiz(Quiz q) { 
        return "QUIZ|" 
                + q.getIdQuiz() + "|" 
                + q.getTitulo() + "|" 
                + q.getTema() + "|" 
                + q.getDescripcion() + "|"
                + q.getImagenRuta(); 
    }
    private String serializarPregunta(Pregunta p, String idQuiz) { 
        return "PREGUNTA|" 
                + p.getIdPregunta() + "|" 
                + idQuiz + "|" 
                + p.getEnunciado() + "|" 
                + p.getOpcionA() + "|" 
                + p.getOpcionB() + "|" 
                + p.getOpcionC() + "|" 
                + p.getOpcionD() + "|" 
                + p.getRespuestaCorrecta(); 
    }
    private Quiz deserializarQuiz(String linea) { 
        String[] p = linea.split("\\|"); 
        if (p.length < 6) return null; 
        return new Quiz(p[1], p[2], p[3], p[4], p[5]); 
    } 
    private Pregunta deserializarPregunta(String linea) {
        String[] p = linea.split("\\|");
        if (p.length < 9) return null; 
        return new Pregunta(p[1], p[3], p[4], p[5], p[6], p[7], p[8]); 
    }
    private ArrayList<Quiz> crearQuizzesPorDefecto() { 
        ArrayList<Quiz> quizzes = new ArrayList<>(); 
        quizzes.add(crearQuizSuma()); 
        quizzes.add(crearQuizMultiplicacion()); 
        quizzes.add(crearQuizDivision()); 
        quizzes.add(crearQuizGeometria()); 
        quizzes.add(crearQuizEcuaciones()); 
        quizzes.add(crearQuizCalculoMental()); 
        System.out.println("Quizzes por defecto creados."); 
        return quizzes; } 
// ----- QUIZ 1: SUMA ----- 
    private Quiz crearQuizSuma() { 
        Quiz q = new Quiz("Q001", "Quiz de Suma", 
                "Matemáticas", 
                "Practica operaciones de suma básica", 
                "recursos/imagenes/Icono_suma.png"); 
        
        q.agregarPregunta(new Pregunta("P001", "¿Cuánto es 15 + 23?",
                "35", "38", "40", "37", "B"));
        q.agregarPregunta(new Pregunta("P002", "¿Cuánto es 47 + 36?",
                "83", "73", "84", "82", "A")); 
        q.agregarPregunta(new Pregunta("P003", "¿Cuánto es 8 + 9 + 7?", 
                "23", "24", "25", "22", "B"));
        q.agregarPregunta(new Pregunta("P004", "¿Cuánto es 100 + 250?", 
                "300", "360", "350", "340", "C"));
        q.agregarPregunta(new Pregunta("P005", "¿Cuánto es 63 + 17?",
                "70", "80", "90", "75", "B"));
        q.agregarPregunta(new Pregunta("P006", "¿Cuánto es 34 + 48?", 
                "72", "82", "92", "62", "B")); 
        q.agregarPregunta(new Pregunta("P007", "¿Cuánto es 125 + 75?",
                "190", "210", "200", "220", "C")); 
        q.agregarPregunta(new Pregunta("P008", "¿Cuánto es 56 + 44?", 
                "90", "100", "110", "95", "B")); 
        q.agregarPregunta(new Pregunta("P009", "¿Cuánto es 77 + 13?",
                "80", "85", "90", "95", "C")); 
        q.agregarPregunta(new Pregunta("P010", "¿Cuánto es 250 + 150?", 
                "350", "380", "400", "420", "C"));
        return q; 
    } 
// ----- QUIZ 2: MULTIPLICACIÓN -----
    private Quiz crearQuizMultiplicacion() {
        Quiz q = new Quiz("Q002", "Quiz de Multiplicación", 
                "Matemáticas", 
                "Practica tablas de multiplicar",
                "recursos/imagenes/icono_multiplicacion.png"); 
        q.agregarPregunta(new Pregunta("P011", "¿Cuánto es 7 x 8?", 
                "54", "56", "63", "49", "B")); 
        q.agregarPregunta(new Pregunta("P012", "¿Cuánto es 9 x 6?", 
                "54", "56", "63", "45", "A"));
        q.agregarPregunta(new Pregunta("P013", "¿Cuánto es 12 x 5?", 
                "55", "65", "60", "70", "C"));
        q.agregarPregunta(new Pregunta("P014", "¿Cuánto es 4 x 4 x 2?", 
                "32", "28", "24", "36", "A"));
        q.agregarPregunta(new Pregunta("P015", "¿Cuánto es 11 x 11?", 
                "111", "121", "131", "101", "B"));
        q.agregarPregunta(new Pregunta("P016", "¿Cuánto es 6 x 7?",
                "36", "48", "42", "54", "C")); 
        q.agregarPregunta(new Pregunta("P017", "¿Cuánto es 8 x 9?",
                "63", "72", "81", "54", "B")); 
        q.agregarPregunta(new Pregunta("P018", "¿Cuánto es 5 x 5 x 4?", 
                "80", "100", "120", "60", "B")); 
        q.agregarPregunta(new Pregunta("P019", "¿Cuánto es 3 x 12?"
                , "30", "36", "42", "48", "B")); 
        q.agregarPregunta(new Pregunta("P020", "¿Cuánto es 15 x 4?",
                "55", "65", "60", "70", "C")); 
        return q; } 
// ----- QUIZ 3: DIVISIÓN ----- 
    private Quiz crearQuizDivision() {
        Quiz q = new Quiz("Q003", "Quiz de División", 
                "Matemáticas", 
                "Practica operaciones de división", 
                "recursos/imagenes/icono_division.png");
        q.agregarPregunta(new Pregunta("P021", "¿Cuánto es 48 ÷ 6?",
                "6", "8", "7", "9", "B")); 
        q.agregarPregunta(new Pregunta("P022", "¿Cuánto es 81 ÷ 9?",
                "7", "8", "9", "10", "C")); 
        q.agregarPregunta(new Pregunta("P023", "¿Cuánto es 100 ÷ 5?",
                "15", "20", "25", "30", "B")); 
        q.agregarPregunta(new Pregunta("P024", "¿Cuánto es 72 ÷ 8?", 
                "7", "8", "9", "10", "C")); 
        q.agregarPregunta(new Pregunta("P025", "¿Cuánto es 56 ÷ 7?", 
                "6", "7", "8", "9", "C")); 
        q.agregarPregunta(new Pregunta("P026", "¿Cuánto es 36 ÷ 4?", 
                "7", "8", "9", "10", "C")); 
        q.agregarPregunta(new Pregunta("P027", "¿Cuánto es 90 ÷ 10?",
                "7", "8", "9", "10", "C")); 
        q.agregarPregunta(new Pregunta("P028", "¿Cuánto es 64 ÷ 8?", 
                "6", "7", "8", "9", "C"));
        q.agregarPregunta(new Pregunta("P029", "¿Cuánto es 45 ÷ 5?",
                "7", "8", "9", "10", "C")); 
        q.agregarPregunta(new Pregunta("P030", "¿Cuánto es 120 ÷ 6?",
                "15", "20", "25", "30", "B")); 
        return q; } 
// ----- QUIZ 4: GEOMETRÍA -----
    private Quiz crearQuizGeometria() { 
        Quiz q = new Quiz("Q004", "Quiz de Geometría", 
                "Matemáticas", 
                "Aprende sobre figuras geométricas", 
                "recursos/imagenes/icono_geometria.png");
        q.agregarPregunta(new Pregunta("P031", "¿Cuántos lados tiene un triángulo?",
                "2", "3", "4", "5", "B")); 
        q.agregarPregunta(new Pregunta("P032", "¿Cuántos lados tiene un hexágono?", 
                "5", "6", "7", "8", "B")); 
        q.agregarPregunta(new Pregunta("P033", "¿Cómo se llama el punto medio de un círculo?", 
                "Radio", "Diámetro", "Centro", "Arco", "C")); 
        q.agregarPregunta(new Pregunta("P034", "Área del cuadrado con lado = 5. ¿Cuánto es?",
                "10", "20", "25", "30", "C")); 
        q.agregarPregunta(new Pregunta("P035", "Perímetro del cuadrado con lado = 4. ¿Cuánto es?",
                "8", "12", "16", "20", "C")); 
        q.agregarPregunta(new Pregunta("P036", "¿Cuántos vértices tiene un cubo?", 
                "6", "8", "10", "12", "B")); 
        q.agregarPregunta(new Pregunta("P037", "¿Cómo se llama un polígono de 8 lados?",
                "Heptágono", "Octágono", "Nonágono", "Decágono", "B"));
        q.agregarPregunta(new Pregunta("P038", "Área del rectángulo de base 6 y altura 4. ¿Cuánto es?",
                "10", "20", "24", "28", "C")); 
        q.agregarPregunta(new Pregunta("P039", "¿Cuántos ángulos tiene un triángulo?",
                "2", "3", "4", "5", "B")); 
        q.agregarPregunta(new Pregunta("P040", "La suma de ángulos interiores de un triángulo es:",
                "90°", "180°", "270°", "360°", "B")); 
        return q; 
    }
    // ----- QUIZ 5: ECUACIONES ----- 
    private Quiz crearQuizEcuaciones() { 
        Quiz q = new Quiz("Q005", "Quiz de Ecuaciones",
                "Matemáticas", 
                "Resuelve ecuaciones básicas", 
                "recursos/imagenes/icono_ecuaciones.png");
        q.agregarPregunta(new Pregunta("P041", "Si x + 5 = 12, ¿cuánto vale x?",
                "5", "6", "7", "8", "C")); 
        q.agregarPregunta(new Pregunta("P042", "Si x - 3 = 9, ¿cuánto vale x?",
                "6", "9", "12", "15", "C")); 
        q.agregarPregunta(new Pregunta("P043", "Si 2x = 16, ¿cuánto vale x?",
                "6", "7", "8", "9", "C"));
        q.agregarPregunta(new Pregunta("P044", "Si x / 4 = 5, ¿cuánto vale x?",
                "15", "20", "25", "30", "B")); 
        q.agregarPregunta(new Pregunta("P045", "Si x + 8 = 20, ¿cuánto vale x?",
                "10", "11", "12", "13", "C")); 
        q.agregarPregunta(new Pregunta("P046", "Si 3x = 21, ¿cuánto vale x?",
                "5", "6", "7", "8", "C")); 
        q.agregarPregunta(new Pregunta("P047", "Si x - 7 = 14, ¿cuánto vale x?", 
                "19", "21", "23", "25", "B")); 
        q.agregarPregunta(new Pregunta("P048", "Si 5x = 45, ¿cuánto vale x?",
                "7", "8", "9", "10", "C")); 
        q.agregarPregunta(new Pregunta("P049", "Si x + 15 = 30, ¿cuánto vale x?", 
                "10", "15", "20", "25", "B")); 
        q.agregarPregunta(new Pregunta("P050", "Si x / 3 = 7, ¿cuánto vale x?",
                "18", "21", "24", "27", "B")); return q; }
    // ----- QUIZ 6: CÁLCULO MENTAL ----- 
    private Quiz crearQuizCalculoMental() {
        Quiz q = new Quiz("Q006", "Quiz de Cálculo Mental",
                "Matemáticas", 
                "Desafía tu velocidad mental",
                "recursos/imagenes/icono_calculo.png");
        q.agregarPregunta(new Pregunta("P051", "¿Cuánto es el doble de 35?",
                "60", "65", "70", "75", "C")); 
        q.agregarPregunta(new Pregunta("P052", "¿Cuánto es la mitad de 84?", 
                "38", "40", "42", "44", "C")); 
        q.agregarPregunta(new Pregunta("P053", "¿Cuánto es 99 + 1?", 
                "99", "100", "101", "110", "B")); 
        q.agregarPregunta(new Pregunta("P054", "¿Cuánto es 50 x 2?", 
                "50", "100", "150", "200", "B")); 
        q.agregarPregunta(new Pregunta("P055", "¿Cuánto es 1000 - 250?", 
                "650", "700", "750", "800", "C"));
        q.agregarPregunta(new Pregunta("P056", "¿Cuánto es el triple de 12?",
                "24", "36", "48", "60", "B")); 
        q.agregarPregunta(new Pregunta("P057", "¿Cuántos minutos hay en 2 horas?", 
                "100", "110", "120", "130", "C")); 
        q.agregarPregunta(new Pregunta("P058", "¿Cuánto es 25 x 4?",
                "80", "90", "100", "110", "C")); 
        q.agregarPregunta(new Pregunta("P059", "¿Cuánto es 200 ÷ 4?",
                "40", "50", "60", "70", "B"));
        q.agregarPregunta(new Pregunta("P060", "¿Cuánto es 7 + 8 + 9 + 6?", 
                "28", "30", "32", "34", "B")); 
        return q; 
    } 
}




































