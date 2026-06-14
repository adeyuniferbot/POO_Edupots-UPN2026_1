/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.controller;

import edupots.dao.QuizDAO;
import edupots.dao.ResultadoDAO;
import edupots.model.Pregunta;
import edupots.model.Quiz;
import edupots.model.Resultado;
import edupots.model.Usuario;
import java.util.ArrayList;
/**
 *
 * @author camil
 */
public class QuizController {

    // =========================================================
    // ATRIBUTOS DE ESTADO DEL QUIZ EN CURSO
    // =========================================================
    private QuizDAO      quizDAO;
    private ResultadoDAO resultadoDAO;

    private Quiz     quizActual;
    private Usuario  estudianteActual;
    private int      indicePreguntaActual;
    private int      respuestasCorrectas;
    private int      respuestasIncorrectas;
    private long     tiempoInicio;

    /** Almacena las respuestas dadas por el estudiante */
    private ArrayList<String> respuestasDadas;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================
    public QuizController() {
        this.quizDAO      = new QuizDAO();
        this.resultadoDAO = new ResultadoDAO();
        this.respuestasDadas = new ArrayList<>();
        reiniciarEstado();
    }

    // =========================================================
    // OBTENER QUIZZES
    // =========================================================

    /**
     * Devuelve todos los quizzes disponibles en el sistema.
     *
     * @return Lista de quizzes
     */
    public ArrayList<Quiz> obtenerTodosLosQuizzes() {
        return quizDAO.leerTodos();
    }

    /**
     * Busca un quiz específico por su ID.
     *
     * @param idQuiz ID del quiz
     * @return Quiz encontrado o null
     */
    public Quiz obtenerQuizPorId(String idQuiz) {
        return quizDAO.buscarPorId(idQuiz);
    }

    // =========================================================
    // INICIAR QUIZ
    // =========================================================
    public boolean iniciarQuiz(String idQuiz, Usuario estudiante) {
        quizActual = quizDAO.buscarPorId(idQuiz);

        if (quizActual == null) {
            System.err.println("Quiz no encontrado: " + idQuiz);
            return false;
        }

        if (quizActual.getPreguntas().isEmpty()) {
            System.err.println("El quiz no tiene preguntas.");
            return false;
        }

        this.estudianteActual      = estudiante;
        this.indicePreguntaActual  = 0;
        this.respuestasCorrectas   = 0;
        this.respuestasIncorrectas = 0;
        this.tiempoInicio          = System.currentTimeMillis();
        this.respuestasDadas.clear();

        quizActual.iniciarQuiz();
        return true;
    }

    // =========================================================
    // RESPONDER PREGUNTA
    // =========================================================

    /**
     * Procesa la respuesta dada por el estudiante para la
     * pregunta actual. Avanza al siguiente índice.
     *
     * @param respuesta Letra respondida: "A", "B", "C" o "D"
     * @return ResultadoRespuesta con si fue correcta y el feedback
     */
    public ResultadoRespuesta responderPregunta(String respuesta) {

        // VALIDACIÓN: No permitir respuesta vacía
        if (respuesta == null || respuesta.trim().isEmpty()) {
            return new ResultadoRespuesta(false, false,
                    "Debes seleccionar una respuesta.",
                    null, null);
        }

        // VALIDACIÓN: Verificar que hay pregunta actual
        if (quizActual == null || indicePreguntaActual
                >= quizActual.getPreguntas().size()) {
            return new ResultadoRespuesta(false, false,
                    "No hay pregunta activa.",
                    null, null);
        }

        Pregunta pregunta = quizActual
                .getPreguntaEnIndice(indicePreguntaActual);

        boolean esCorrecta = pregunta.validarRespuesta(respuesta);
        respuestasDadas.add(respuesta);

        if (esCorrecta) {
            respuestasCorrectas++;
        } else {
            respuestasIncorrectas++;
        }

        // Avanzar al siguiente índice
        indicePreguntaActual++;

        return new ResultadoRespuesta(
                true,
                esCorrecta,
                esCorrecta
                        ? "✅ ¡Respuesta Correcta! Has ganado 10 puntos."
                        : "❌ Respuesta Incorrecta. La respuesta correcta era: "
                          + pregunta.getTextoRespuestaCorrecta(),
                pregunta,
                pregunta.getTextoRespuestaCorrecta()
        );
    }

    // =========================================================
    // FINALIZAR QUIZ
    // =========================================================

    /**
     * Finaliza el quiz, calcula el puntaje y guarda el resultado.
     *
     * @return Objeto Resultado con todos los datos finales
     */
    public Resultado finalizarQuiz() {
        if (quizActual == null || estudianteActual == null) {
            return null;
        }

        // Calcular tiempo empleado en segundos
        long tiempoFin      = System.currentTimeMillis();
        long tiempoSegundos = (tiempoFin - tiempoInicio) / 1000;

        // Calcular puntaje final
        int puntajeFinal = quizActual
                .calcularPuntaje(respuestasCorrectas);

        // Crear objeto resultado
        String idResultado = "R" + System.currentTimeMillis();
        Resultado resultado = new Resultado(
                idResultado,
                estudianteActual.getIdUsuario(),
                estudianteActual.getNombre(),
                quizActual.getIdQuiz(),
                quizActual.getTitulo(),
                puntajeFinal,
                respuestasCorrectas,
                respuestasIncorrectas,
                tiempoSegundos
        );

        // Guardar en archivo
        resultadoDAO.guardar(resultado);

        return resultado;
    }

    // =========================================================
    // NAVEGACIÓN DE PREGUNTAS
    // =========================================================

    /**
     * Devuelve la pregunta actual del quiz en curso.
     *
     * @return Pregunta actual o null si el quiz terminó
     */
    public Pregunta getPreguntaActual() {
        if (quizActual == null) return null;
        return quizActual.getPreguntaEnIndice(indicePreguntaActual);
    }

    /**
     * Verifica si quedan más preguntas por responder.
     *
     * @return true si aún hay preguntas pendientes
     */
    public boolean hayMasPreguntas() {
        if (quizActual == null) return false;
        return indicePreguntaActual
                < quizActual.getPreguntas().size();
    }

    /**
     * Devuelve el número de la pregunta actual (1-based para mostrar).
     *
     * @return Número de pregunta actual
     */
    public int getNumeroPreguntaActual() {
        return indicePreguntaActual + 1;
    }

    /**
     * Devuelve el total de preguntas del quiz actual.
     *
     * @return Total de preguntas
     */
    public int getTotalPreguntas() {
        if (quizActual == null) return 0;
        return quizActual.getPreguntas().size();
    }

    /**
     * Calcula el porcentaje de progreso dentro del quiz.
     *
     * @return Porcentaje de 0 a 100
     */
    public int getPorcentajeProgreso() {
        if (getTotalPreguntas() == 0) return 0;
        return (indicePreguntaActual * 100) / getTotalPreguntas();
    }

    // =========================================================
    // REINICIAR ESTADO
    // =========================================================

    /**
     * Limpia el estado del controlador para un nuevo quiz.
     */
    public void reiniciarEstado() {
        this.quizActual            = null;
        this.estudianteActual      = null;
        this.indicePreguntaActual  = 0;
        this.respuestasCorrectas   = 0;
        this.respuestasIncorrectas = 0;
        this.tiempoInicio          = 0;
        if (this.respuestasDadas != null) {
            this.respuestasDadas.clear();
        }
    }

    // =========================================================
    // GETTERS
    // =========================================================

    public Quiz    getQuizActual()            { return quizActual; }
    public int     getRespuestasCorrectas()   { return respuestasCorrectas; }
    public int     getRespuestasIncorrectas() { return respuestasIncorrectas; }
    public int     getIndiceActual()          { return indicePreguntaActual; }

    // =========================================================
    // CLASE INTERNA: ResultadoRespuesta
    // Encapsula el resultado de responder una pregunta
    // =========================================================
    public static class ResultadoRespuesta {

        private boolean operacionExitosa;
        private boolean esCorrecta;
        private String  mensaje;
        private Pregunta pregunta;
        private String  respuestaCorrectaTexto;

        public ResultadoRespuesta(boolean operacionExitosa,
                                  boolean esCorrecta,
                                  String mensaje,
                                  Pregunta pregunta,
                                  String respuestaCorrectaTexto) {
            this.operacionExitosa       = operacionExitosa;
            this.esCorrecta             = esCorrecta;
            this.mensaje                = mensaje;
            this.pregunta               = pregunta;
            this.respuestaCorrectaTexto = respuestaCorrectaTexto;
        }

        public boolean isOperacionExitosa()    { return operacionExitosa; }
        public boolean isEsCorrecta()          { return esCorrecta; }
        public String  getMensaje()            { return mensaje; }
        public Pregunta getPregunta()          { return pregunta; }
        public String  getRespuestaCorrectaTexto() {
            return respuestaCorrectaTexto;
        }
    }
}