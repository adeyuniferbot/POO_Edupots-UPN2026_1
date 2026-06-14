/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.view;

import edupots.controller.QuizController;
import edupots.controller.QuizController.ResultadoRespuesta;
import edupots.model.Usuario;
import edupots.utils.AudioManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author camil
 */
public class ResultadoPreguntaFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ResultadoPreguntaFrame.class.getName());
//Esto son los componenetes
    private JPanel  panelPrincipal;
    private JPanel  panelHeader;
    private JPanel  panelContenido;
    private JLabel  lblIcono;
    private JLabel  lblTitulo;
    private JLabel  lblMensaje;
    private JLabel  lblRespuestaCorrecta;
    private JLabel  lblPuntos;
    private JButton btnSiguiente;
    
    private ResultadoRespuesta resultadoRespuesta;
    private QuizController     quizController;
    private Usuario            usuarioActual;
    private JFrame             framePadre;
    private PreguntaFrame      preguntaFrame;
    
    //Los colores
    private static final Color COLOR_CORRECTO   = new Color(39,  174, 96);
    private static final Color COLOR_INCORRECTO = new Color(192, 57,  43);
    private static final Color COLOR_FONDO      = new Color(236, 240, 241);
    private static final Color COLOR_TEXTO      = new Color(44,  62,  80);
    
    //El constructor
    public ResultadoPreguntaFrame(ResultadoRespuesta resultado,
                                   QuizController controller,
                                   Usuario usuario,
                                   JFrame padre,
                                   PreguntaFrame pregFrame) {
        this.resultadoRespuesta = resultado;
        this.quizController     = controller;
        this.usuarioActual      = usuario;
        this.framePadre         = padre;
        this.preguntaFrame      = pregFrame;
        initComponents();
        configurarVentana();
    }
    private void configurarVentana() {
        setTitle("EduPots — Resultado");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    private void initComponents() {
        boolean esCorrecta = resultadoRespuesta.isEsCorrecta();
        Color   colorTema  = esCorrecta
                ? COLOR_CORRECTO : COLOR_INCORRECTO;

        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(COLOR_FONDO);

        // ===== HEADER COLOREADO =====
        panelHeader = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color colorClaro = esCorrecta
                        ? new Color(46, 204, 113)
                        : new Color(231, 76, 60);
                GradientPaint gp = new GradientPaint(
                        0, 0, colorTema,
                        getWidth(), 0, colorClaro);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelHeader.setLayout(null);
        panelHeader.setPreferredSize(
                new java.awt.Dimension(500, 160));

        lblIcono = new JLabel(esCorrecta ? "" : "");
        lblIcono.setBounds(0, 15, 500, 70);
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        lblIcono.setFont(
                new Font("Segoe UI Emoji", Font.PLAIN, 55));
        panelHeader.add(lblIcono);

        // Título
        lblTitulo = new JLabel(esCorrecta
                ? "¡Respuesta Correcta!"
                : "Respuesta Incorrecta");
        lblTitulo.setBounds(0, 88, 500, 35);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelHeader.add(lblTitulo);

        // Puntos ganados
        lblPuntos = new JLabel(esCorrecta
                ? "+10 puntos" : "+0 puntos");
        lblPuntos.setBounds(0, 125, 500, 25);
        lblPuntos.setHorizontalAlignment(SwingConstants.CENTER);
        lblPuntos.setFont(
                new Font("Segoe UI", Font.BOLD, 15));
        lblPuntos.setForeground(
                new Color(255, 255, 255, 210));
        panelHeader.add(lblPuntos);

        panelPrincipal.add(panelHeader, BorderLayout.NORTH);

        // ===== PANEL CONTENIDO =====
        panelContenido = new JPanel(null);
        panelContenido.setBackground(COLOR_FONDO);

        // Tarjeta de respuesta correcta
        JPanel tarjeta = new JPanel(null);
        tarjeta.setBounds(30, 20, 435, 130);
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)));

        JLabel lblTitCard = new JLabel(
                esCorrecta
                ? " Excelente trabajo"
                : " La respuesta correcta era:");
        lblTitCard.setBounds(15, 12, 400, 24);
        lblTitCard.setFont(
                new Font("Segoe UI", Font.BOLD, 14));
        lblTitCard.setForeground(colorTema);
        tarjeta.add(lblTitCard);

        lblRespuestaCorrecta = new JLabel();
        lblRespuestaCorrecta.setBounds(15, 42, 400, 70);
        lblRespuestaCorrecta.setFont(
                new Font("Segoe UI", Font.PLAIN, 13));
        lblRespuestaCorrecta.setForeground(COLOR_TEXTO);
        lblRespuestaCorrecta.setVerticalAlignment(
                SwingConstants.TOP);

        if (esCorrecta) {
            lblRespuestaCorrecta.setText(
                    "<html><body style='width:380px'>"
                    + "Seleccionaste la opción correcta. "
                    + "¡Sigue así!</body></html>");
        } else {
            String correcta = resultadoRespuesta
                    .getRespuestaCorrectaTexto();
            lblRespuestaCorrecta.setText(
                    "<html><body style='width:380px'>"
                    + correcta + "</body></html>");
        }
        tarjeta.add(lblRespuestaCorrecta);
        panelContenido.add(tarjeta);

        // Marcador actual
        JPanel tarjetaMarcador = new JPanel(null);
        tarjetaMarcador.setBounds(30, 162, 435, 60);
        tarjetaMarcador.setBackground(Color.WHITE);
        tarjetaMarcador.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220), 1));

        int correctas   = quizController.getRespuestasCorrectas();
        int incorrectas = quizController.getRespuestasIncorrectas();
        int puntajeActual = correctas * 10;

        JLabel lblMarcador = new JLabel(
                "Puntaje actual:  " + puntajeActual + " pts: "
                + "  Correctas   " + correctas
                + "  Incorrectas   " + incorrectas);
        lblMarcador.setBounds(15, 0, 410, 60);
        lblMarcador.setFont(
                new Font("Segoe UI", Font.BOLD, 13));
        lblMarcador.setForeground(COLOR_TEXTO);
        tarjetaMarcador.add(lblMarcador);
        panelContenido.add(tarjetaMarcador);

        // ----- BOTÓN CONTINUAR -----
        boolean hayMas = quizController.hayMasPreguntas();
        String txtBtn = hayMas
                ? "Siguiente pregunta →"
                : "Ver Resultados Finales →";

        btnSiguiente = new JButton(txtBtn) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(colorTema);
                g2d.fillRoundRect(0, 0,
                        getWidth(), getHeight(), 10, 10);
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font(
                        "Segoe UI", Font.BOLD, 15));
                java.awt.FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth()
                        - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent()
                        - fm.getDescent()) / 2;
                g2d.drawString(getText(), x, y);
            }
        };
        btnSiguiente.setBounds(30, 238, 435, 52);
        btnSiguiente.setBorderPainted(false);
        btnSiguiente.setContentAreaFilled(false);
        btnSiguiente.setFocusPainted(false);
        btnSiguiente.setCursor(
                new Cursor(Cursor.HAND_CURSOR));
        btnSiguiente.addActionListener(
                e -> accionContinuar());
        panelContenido.add(btnSiguiente);

        panelPrincipal.add(
                panelContenido, BorderLayout.CENTER);
        setContentPane(panelPrincipal);
    }
    
    private void accionContinuar() {
        AudioManager.reproducirClick();

        if (quizController.hayMasPreguntas()) {
            // Volver a PreguntaFrame para la siguiente pregunta
            preguntaFrame.avanzarSiguientePregunta();
            this.dispose();
        } else {
            // Quiz terminado: ir a ResultadosFinalesFrame
            AudioManager.reproducirFinal();
            edupots.model.Resultado resultado =
                    quizController.finalizarQuiz();
            ResultadosFinalesFrame finales =
                    new ResultadosFinalesFrame(
                            resultado,
                            usuarioActual,
                            framePadre);
            finales.setVisible(true);
            preguntaFrame.dispose();
            this.dispose();
        }
    }
}
