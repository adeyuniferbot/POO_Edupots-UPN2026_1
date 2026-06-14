/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.view;

import edupots.controller.QuizController;
import edupots.model.Quiz;
import edupots.model.Usuario;
import edupots.utils.AudioManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Cursor;
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
public class InstruccionesFrame extends JFrame {

    // =========================================================
    // COMPONENTES
    // =========================================================
    private JPanel  panelPrincipal;
    private JPanel  panelHeader;
    private JPanel  panelContenido;
    private JButton btnComenzar;
    private JButton btnVolver;

    // =========================================================
    // DATOS
    // =========================================================
    private Usuario        usuarioActual;
    private String         idQuiz;
    private JFrame         framePadre;
    private QuizController quizController;

    // =========================================================
    // COLORES
    // =========================================================
    private static final Color COLOR_PRIMARIO   = new Color(41, 128, 185);
    private static final Color COLOR_SECUNDARIO = new Color(52, 152, 219);
    private static final Color COLOR_FONDO      = new Color(236, 240, 241);
    private static final Color COLOR_TEXTO      = new Color(44, 62, 80);
    private static final Color COLOR_VERDE      = new Color(39, 174, 96);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================
    public InstruccionesFrame(Usuario usuario, String idQuiz,
                               JFrame padre,
                               QuizController controller) {
        this.usuarioActual  = usuario;
        this.idQuiz         = idQuiz;
        this.framePadre     = padre;
        this.quizController = controller;
        initComponents();
        configurarVentana();
    }

    private void configurarVentana() {
        setTitle("EduPots — Instrucciones");
        setSize(550, 580);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(COLOR_FONDO);

        // ----- HEADER -----
        panelHeader = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, COLOR_PRIMARIO,
                        getWidth(), 0, COLOR_SECUNDARIO);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelHeader.setLayout(null);
        panelHeader.setPreferredSize(
                new java.awt.Dimension(550, 100));

        JLabel lblTitulo = new JLabel("  Instrucciones");
        lblTitulo.setBounds(20, 20, 400, 38);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        panelHeader.add(lblTitulo);

        // Nombre del quiz
        Quiz quiz = quizController.obtenerQuizPorId(idQuiz);
        String nombreQuiz = (quiz != null)
                ? quiz.getTitulo() : "Quiz";
        JLabel lblQuiz = new JLabel("Quiz: " + nombreQuiz);
        lblQuiz.setBounds(20, 60, 400, 22);
        lblQuiz.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblQuiz.setForeground(new Color(255, 255, 255, 200));
        panelHeader.add(lblQuiz);

        panelPrincipal.add(panelHeader, BorderLayout.NORTH);

        // ----- CONTENIDO -----
        panelContenido = new JPanel(null);
        panelContenido.setBackground(COLOR_FONDO);

        // Tarjeta de instrucciones
        JPanel tarjeta = new JPanel(null);
        tarjeta.setBounds(30, 20, 480, 340);
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createLineBorder(
                new Color(220, 220, 220), 1));

        JLabel lblTitCard = new JLabel("Antes de comenzar...");
        lblTitCard.setBounds(20, 15, 440, 28);
        lblTitCard.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblTitCard.setForeground(COLOR_TEXTO);
        tarjeta.add(lblTitCard);

        // Instrucciones individuales
        String[][] instrucciones = {
            {"🎯", "El quiz tiene 10 preguntas de opción múltiple."},
            {"✅", "Cada respuesta correcta vale 10 puntos (máx. 100)."},
            {"⏱️", "Se registra el tiempo que tardas en completarlo."},
            {"📊", "Al final verás tu puntaje, nivel e insignias."},
            {"❌", "No puedes avanzar sin seleccionar una respuesta."},
            {"💾", "Tus resultados se guardan automáticamente."}
        };

        int yInstr = 55;
        for (String[] instr : instrucciones) {
            JLabel lblEmoji = new JLabel(instr[0]);
            lblEmoji.setBounds(15, yInstr, 35, 30);
            lblEmoji.setFont(
                    new Font("Segoe UI Emoji", Font.PLAIN, 18));
            tarjeta.add(lblEmoji);

            JLabel lblTexto = new JLabel(instr[1]);
            lblTexto.setBounds(55, yInstr, 400, 30);
            lblTexto.setFont(
                    new Font("Segoe UI", Font.PLAIN, 13));
            lblTexto.setForeground(COLOR_TEXTO);
            tarjeta.add(lblTexto);

            yInstr += 40;
        }

        panelContenido.add(tarjeta);

        // ----- BOTONES -----
        btnComenzar = new JButton("¡Comenzar Quiz!") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(COLOR_VERDE);
                g2d.fillRoundRect(0, 0,
                        getWidth(), getHeight(), 5, 5);
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Segoe UI", Font.BOLD, 16));
                java.awt.FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth()
                        - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent()
                        - fm.getDescent()) / 2;
                g2d.drawString(getText(), x, y);
            }
        };
        btnComenzar.setBounds(30, 375, 480, 52);
        btnComenzar.setBorderPainted(false);
        btnComenzar.setContentAreaFilled(false);
        btnComenzar.setFocusPainted(false);
        btnComenzar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnComenzar.addActionListener(e -> comenzarQuiz());
        panelContenido.add(btnComenzar);

        btnVolver = new JButton("← Volver a la lista");
        btnVolver.setBounds(30, 440, 480, 40);
        btnVolver.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnVolver.setForeground(new Color(127, 140, 141));
        btnVolver.setBorderPainted(false);
        btnVolver.setBackground(COLOR_FONDO);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e -> volverALista());
        panelContenido.add(btnVolver);

        panelPrincipal.add(panelContenido, BorderLayout.CENTER);
        setContentPane(panelPrincipal);
    }

    // =========================================================
    // ACCIONES
    // =========================================================
    private void comenzarQuiz() {
        AudioManager.reproducirClick();
        boolean iniciado = quizController.iniciarQuiz(
                idQuiz, usuarioActual);
        if (iniciado) {
            PreguntaFrame pregunta = new PreguntaFrame(
                    usuarioActual, quizController, framePadre);
            pregunta.setVisible(true);
            this.dispose();
        }
    }

    private void volverALista() {
        AudioManager.reproducirClick();
        ListaQuizzesFrame lista = new ListaQuizzesFrame(
                usuarioActual, framePadre);
        lista.setVisible(true);
        this.dispose();
    }
}