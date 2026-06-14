/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.view;

import edupots.controller.QuizController;
import edupots.model.Quiz;
import edupots.model.Usuario;
import edupots.utils.AudioManager;
import edupots.utils.ImageManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
/**
 *
 * @author camil
 */
public class ListaQuizzesFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ListaQuizzesFrame.class.getName());
 private JPanel     panelPrincipal;
    private JPanel     panelHeader;
    private JPanel     panelQuizzes;
    private JScrollPane scrollQuizzes;
    private JButton    btnVolver;

    private Usuario          usuarioActual;
    private JFrame           framePadre;
    private QuizController   quizController;
    
    private static final Color COLOR_PRIMARIO   = new Color(41, 128, 185);
    private static final Color COLOR_SECUNDARIO = new Color(52, 152, 219);
    private static final Color COLOR_FONDO      = new Color(236, 240, 241);
    private static final Color COLOR_TEXTO      = new Color(44, 62, 80);
    
     private static final Color[] COLORES_QUIZ = {
        new Color(41,  128, 185),  // Suma es azul
        new Color(39,  174, 96),   // Multiplicación es verde
        new Color(231, 76,  60),   // División es rojo
        new Color(142, 68,  173),  // Geometría es morado
        new Color(243, 156, 18),   // Ecuaciones es naranja
        new Color(26,  188, 156)   // Cálculo Mental es turquesa
    };
     
      public ListaQuizzesFrame(Usuario usuario, JFrame padre) {
        this.usuarioActual = usuario;
        this.framePadre    = padre;
        this.quizController = new QuizController();
        initComponents();
        configurarVentana();
        cargarQuizzes();
    }

    private void configurarVentana() {
        setTitle("EduPots — Seleccionar Quiz");
        setSize(650, 700);
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
                new java.awt.Dimension(650, 100));

        JLabel lblTitulo = new JLabel(" Quizzes Disponibles");
        lblTitulo.setBounds(20, 18, 500, 38);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        panelHeader.add(lblTitulo);

        JLabel lblSub = new JLabel(
                "Selecciona un quiz para comenzar");
        lblSub.setBounds(20, 57, 400, 22);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(new Color(255, 255, 255, 200));
        panelHeader.add(lblSub);

        btnVolver = new JButton("← Volver");
        btnVolver.setBounds(530, 30, 100, 35);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBackground(new Color(255, 255, 255, 50));
        btnVolver.setBorderPainted(false);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e -> volverAlMenu());
        panelHeader.add(btnVolver);

        panelPrincipal.add(panelHeader, BorderLayout.NORTH);
        
        panelQuizzes = new JPanel(null);
        panelQuizzes.setBackground(COLOR_FONDO);
        panelQuizzes.setPreferredSize(
                new java.awt.Dimension(620, 550));

        scrollQuizzes = new JScrollPane(panelQuizzes);
        scrollQuizzes.setBorder(
                BorderFactory.createEmptyBorder());
        scrollQuizzes.getVerticalScrollBar()
                .setUnitIncrement(16);

        panelPrincipal.add(scrollQuizzes, BorderLayout.CENTER);
        setContentPane(panelPrincipal);
    }

    private void cargarQuizzes() {
        ArrayList<Quiz> quizzes =
                quizController.obtenerTodosLosQuizzes();

        int y = 20;
        int colorIdx = 0;

        for (Quiz quiz : quizzes) {
            JPanel tarjeta = crearTarjetaQuiz(
                    quiz, COLORES_QUIZ[colorIdx
                            % COLORES_QUIZ.length]);
            tarjeta.setBounds(20, y, 590, 120);
            panelQuizzes.add(tarjeta);
            y += 135;
            colorIdx++;
        }

        // Actualizar tamaño del panel
        panelQuizzes.setPreferredSize(
                new java.awt.Dimension(620, y + 20));
        panelQuizzes.revalidate();
    }
    
    private JPanel crearTarjetaQuiz(Quiz quiz, Color color) {
        JPanel tarjeta = new JPanel(null);
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)));

        // Franja de color izquierda
        JPanel franja = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(color);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        franja.setBounds(0, 0, 8, 120);
        tarjeta.add(franja);

        // Icono del quiz
        JLabel lblIcono = new JLabel();
        lblIcono.setBounds(20, 25, 65, 65);
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icono = ImageManager.cargarIconoQuiz(
                quiz.getIdQuiz(), 55, 55);
        if (icono != null) {
            lblIcono.setIcon(icono);
        } else {
            lblIcono.setText(obtenerEmojiQuiz(quiz.getIdQuiz()));
            lblIcono.setFont(
                    new Font("Segoe UI Emoji", Font.PLAIN, 38));
        }
        tarjeta.add(lblIcono);

        // Título del quiz
        JLabel lblTitulo = new JLabel(quiz.getTitulo());
        lblTitulo.setBounds(100, 18, 360, 30);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblTitulo.setForeground(COLOR_TEXTO);
        tarjeta.add(lblTitulo);

        // Descripción
        JLabel lblDesc = new JLabel(quiz.getDescripcion());
        lblDesc.setBounds(100, 48, 360, 22);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDesc.setForeground(new Color(127, 140, 141));
        tarjeta.add(lblDesc);

        // Info: 10 preguntas
        JLabel lblInfo = new JLabel(" 10 preguntas  •  100 puntos");
        lblInfo.setBounds(100, 70, 280, 20);
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblInfo.setForeground(new Color(149, 165, 166));
        tarjeta.add(lblInfo);

        // Botón Iniciar
        JButton btnIniciar = new JButton("Iniciar ") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(color);
                g2d.fillRoundRect(0, 0,
                        getWidth(), getHeight(), 8, 8);
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Segoe UI", Font.BOLD, 13));
                java.awt.FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth()
                        - fm.stringWidth(getText())) / 2;
                int y2 = (getHeight() + fm.getAscent()
                        - fm.getDescent()) / 2;
                g2d.drawString(getText(), x, y2);
            }
        };
        btnIniciar.setBounds(470, 35, 110, 42);
        btnIniciar.setBorderPainted(false);
        btnIniciar.setContentAreaFilled(false);
        btnIniciar.setFocusPainted(false);
        btnIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIniciar.addActionListener(
                e -> iniciarQuiz(quiz.getIdQuiz()));
        tarjeta.add(btnIniciar);

        return tarjeta;
    }
 private void iniciarQuiz(String idQuiz) {
        AudioManager.reproducirClick();
        InstruccionesFrame instrucciones =
                new InstruccionesFrame(
                        usuarioActual, idQuiz,
                        framePadre, quizController);
        instrucciones.setVisible(true);
        this.dispose();
    }

    private void volverAlMenu() {
        AudioManager.reproducirClick();
        framePadre.setVisible(true);
        this.dispose();
    }

    private String obtenerEmojiQuiz(String idQuiz) {
        switch (idQuiz) {
            case "Q001": return "➕";
            case "Q002": return "✖️";
            case "Q003": return "➗";
            case "Q004": return "📐";
            case "Q005": return "🔢";
            case "Q006": return "";
            default:     return "📝";
        }
    }
}
