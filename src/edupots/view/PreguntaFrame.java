/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.view;

import edupots.controller.QuizController;
import edupots.controller.QuizController.ResultadoRespuesta;
import edupots.model.Pregunta;
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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
/**
 *
 * @author camil
 */
public class PreguntaFrame extends JFrame {

    // =========================================================
    // COMPONENTES
    // =========================================================
    private JPanel       panelPrincipal;
    private JPanel       panelHeader;
    private JPanel       panelCentro;
    private JLabel       lblTituloQuiz;
    private JLabel       lblNumeroPregunta;
    private JProgressBar barraProgreso;
    private JLabel       lblEnunciado;
    private JRadioButton rbOpcionA;
    private JRadioButton rbOpcionB;
    private JRadioButton rbOpcionC;
    private JRadioButton rbOpcionD;
    private ButtonGroup  grupoOpciones;
    private JButton      btnResponder;

    // =========================================================
    // DATOS
    // =========================================================
    private Usuario        usuarioActual;
    private QuizController quizController;
    private JFrame         framePadre;

    // =========================================================
    // COLORES
    // =========================================================
    private static final Color COLOR_PRIMARIO   = new Color(41,  128, 185);
    private static final Color COLOR_SECUNDARIO = new Color(52,  152, 219);
    private static final Color COLOR_FONDO      = new Color(236, 240, 241);
    private static final Color COLOR_TEXTO      = new Color(44,  62,  80);
    private static final Color COLOR_VERDE      = new Color(39,  174, 96);
    private static final Color COLOR_OPCION     = new Color(248, 249, 250);
    private static final Color COLOR_OPCION_SEL = new Color(214, 234, 248);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================
    public PreguntaFrame(Usuario usuario,
                          QuizController controller,
                          JFrame padre) {
        this.usuarioActual  = usuario;
        this.quizController = controller;
        this.framePadre     = padre;
        initComponents();
        configurarVentana();
        mostrarPreguntaActual();
    }

    // =========================================================
    // CONFIGURACIÓN
    // =========================================================
    private void configurarVentana() {
        setTitle("EduPots — Quiz en Curso");
        // Ventana más alta para que el botón sea visible
        setSize(620, 700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // =========================================================
    // INICIALIZACIÓN DE COMPONENTES
    // =========================================================
    private void initComponents() {

        // ===== PANEL PRINCIPAL con BorderLayout =====
        panelPrincipal = new JPanel(new BorderLayout(0, 0));
        panelPrincipal.setBackground(COLOR_FONDO);

        // ===== HEADER (NORTE) =====
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
                new java.awt.Dimension(620, 100));

        lblTituloQuiz = new JLabel("Quiz");
        lblTituloQuiz.setBounds(20, 10, 500, 30);
        lblTituloQuiz.setFont(
                new Font("Segoe UI", Font.BOLD, 20));
        lblTituloQuiz.setForeground(Color.WHITE);
        panelHeader.add(lblTituloQuiz);

        lblNumeroPregunta = new JLabel("Pregunta 1 de 10");
        lblNumeroPregunta.setBounds(20, 42, 400, 22);
        lblNumeroPregunta.setFont(
                new Font("Segoe UI", Font.PLAIN, 13));
        lblNumeroPregunta.setForeground(
                new Color(255, 255, 255, 200));
        panelHeader.add(lblNumeroPregunta);

        barraProgreso = new JProgressBar(0, 100);
        barraProgreso.setBounds(20, 72, 575, 14);
        barraProgreso.setValue(0);
        barraProgreso.setStringPainted(false);
        barraProgreso.setForeground(new Color(243, 156, 18));
        barraProgreso.setBackground(
                new Color(255, 255, 255, 80));
        barraProgreso.setBorderPainted(false);
        panelHeader.add(barraProgreso);

        panelPrincipal.add(panelHeader, BorderLayout.NORTH);

        // ===== PANEL CENTRO (todo el contenido restante) =====
        // Usamos BorderLayout para que el botón siempre
        // quede pegado al sur y sea visible
        panelCentro = new JPanel(new BorderLayout(0, 10));
        panelCentro.setBackground(COLOR_FONDO);
        panelCentro.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 15, 20));

        // ----- TARJETA ENUNCIADO (NORTE del centro) -----
        JPanel panelEnunciado = new JPanel(null);
        panelEnunciado.setBackground(Color.WHITE);
        panelEnunciado.setPreferredSize(
                new java.awt.Dimension(580, 110));
        panelEnunciado.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220), 1));

        // Número de pregunta destacado
        JLabel lblNumDest = new JLabel("Pregunta");
        lblNumDest.setBounds(15, 10, 100, 18);
        lblNumDest.setFont(
                new Font("Segoe UI", Font.PLAIN, 11));
        lblNumDest.setForeground(
                new Color(127, 140, 141));
        panelEnunciado.add(lblNumDest);

        lblEnunciado = new JLabel();
        lblEnunciado.setBounds(15, 28, 545, 72);
        lblEnunciado.setFont(
                new Font("Segoe UI", Font.BOLD, 15));
        lblEnunciado.setForeground(COLOR_TEXTO);
        lblEnunciado.setVerticalAlignment(
                SwingConstants.TOP);
        lblEnunciado.setText(
                "<html><body style='width:530px'>"
                + "Cargando...</body></html>");
        panelEnunciado.add(lblEnunciado);

        panelCentro.add(panelEnunciado, BorderLayout.NORTH);

        // ----- PANEL OPCIONES (CENTRO del centro) -----
        JPanel panelOpciones = new JPanel(null);
        panelOpciones.setBackground(COLOR_FONDO);
        panelOpciones.setPreferredSize(
                new java.awt.Dimension(580, 320));

        grupoOpciones = new ButtonGroup();

        rbOpcionA = crearOpcion("A", 0);
        rbOpcionB = crearOpcion("B", 1);
        rbOpcionC = crearOpcion("C", 2);
        rbOpcionD = crearOpcion("D", 3);

        grupoOpciones.add(rbOpcionA);
        grupoOpciones.add(rbOpcionB);
        grupoOpciones.add(rbOpcionC);
        grupoOpciones.add(rbOpcionD);

        rbOpcionA.setBounds(0, 0,   580, 72);
        rbOpcionB.setBounds(0, 80,  580, 72);
        rbOpcionC.setBounds(0, 160, 580, 72);
        rbOpcionD.setBounds(0, 240, 580, 72);

        // Redibujar panel al seleccionar opción
        rbOpcionA.addActionListener(e -> panelOpciones.repaint());
        rbOpcionB.addActionListener(e -> panelOpciones.repaint());
        rbOpcionC.addActionListener(e -> panelOpciones.repaint());
        rbOpcionD.addActionListener(e -> panelOpciones.repaint());

        panelOpciones.add(rbOpcionA);
        panelOpciones.add(rbOpcionB);
        panelOpciones.add(rbOpcionC);
        panelOpciones.add(rbOpcionD);

        panelCentro.add(panelOpciones, BorderLayout.CENTER);

        // ----- BOTÓN RESPONDER (SUR del centro) -----
        // Al estar en el SUR del BorderLayout SIEMPRE
        // será visible sin importar el tamaño de pantalla
        btnResponder = new JButton("Confirmar Respuesta →") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(COLOR_VERDE);
                g2d.fillRoundRect(
                        0, 0, getWidth(), getHeight(),
                        12, 12);
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font(
                        "Segoe UI", Font.BOLD, 16));
                java.awt.FontMetrics fm =
                        g2d.getFontMetrics();
                int x = (getWidth()
                        - fm.stringWidth(getText())) / 2;
                int y = (getHeight()
                        + fm.getAscent()
                        - fm.getDescent()) / 2;
                g2d.drawString(getText(), x, y);
            }
        };
        btnResponder.setPreferredSize(
                new java.awt.Dimension(580, 55));
        btnResponder.setBorderPainted(false);
        btnResponder.setContentAreaFilled(false);
        btnResponder.setFocusPainted(false);
        btnResponder.setCursor(
                new Cursor(Cursor.HAND_CURSOR));
        btnResponder.addActionListener(
                e -> accionResponder());

        panelCentro.add(btnResponder, BorderLayout.SOUTH);

        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        setContentPane(panelPrincipal);
    }

    // =========================================================
    // CREAR OPCIÓN DE RADIO BUTTON
    // =========================================================
    private JRadioButton crearOpcion(String letra,
                                      int indice) {
        JRadioButton rb = new JRadioButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo según selección
                if (isSelected()) {
                    g2d.setColor(COLOR_OPCION_SEL);
                } else {
                    g2d.setColor(COLOR_OPCION);
                }
                g2d.fillRoundRect(
                        0, 0, getWidth(), getHeight(),
                        12, 12);

                // Borde
                if (isSelected()) {
                    g2d.setColor(COLOR_PRIMARIO);
                } else {
                    g2d.setColor(new Color(210, 210, 210));
                }
                g2d.drawRoundRect(
                        0, 0,
                        getWidth() - 1,
                        getHeight() - 1,
                        12, 12);

                // Círculo de letra
                g2d.setColor(isSelected()
                        ? COLOR_PRIMARIO
                        : new Color(180, 180, 180));
                g2d.fillOval(14, 16, 38, 38);

                // Letra dentro del círculo
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font(
                        "Segoe UI", Font.BOLD, 16));
                java.awt.FontMetrics fm =
                        g2d.getFontMetrics();
                int lx = 14 + (38
                        - fm.stringWidth(letra)) / 2;
                int ly = 16 + (38 + fm.getAscent()
                        - fm.getDescent()) / 2;
                g2d.drawString(letra, lx, ly);

                // Texto de la opción
                g2d.setColor(COLOR_TEXTO);
                g2d.setFont(new Font(
                        "Segoe UI", Font.PLAIN, 14));
                // Dibujar texto con límite de ancho
                String texto = getText();
                if (texto != null
                        && !texto.isEmpty()) {
                    g2d.drawString(texto, 62, 38);
                }
            }
        };
        rb.setOpaque(false);
        rb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rb.setFocusPainted(false);
        return rb;
    }

    // =========================================================
    // MOSTRAR PREGUNTA ACTUAL
    // =========================================================
    public void mostrarPreguntaActual() {
        if (!quizController.hayMasPreguntas()) {
            finalizarQuiz();
            return;
        }

        Pregunta p = quizController.getPreguntaActual();
        if (p == null) return;

        int num   = quizController.getNumeroPreguntaActual();
        int total = quizController.getTotalPreguntas();
        int prog  = quizController.getPorcentajeProgreso();

        // Actualizar header
        lblTituloQuiz.setText(
                quizController.getQuizActual().getTitulo());
        lblNumeroPregunta.setText(
                "Pregunta " + num + " de " + total
                + "   ✅ "
                + quizController.getRespuestasCorrectas()
                + "  correctas");
        barraProgreso.setValue(prog);

        // Actualizar enunciado
        lblEnunciado.setText(
                "<html><body style='width:530px'>"
                + p.getEnunciado()
                + "</body></html>");

        // Actualizar texto de cada opción
        rbOpcionA.setText(p.getOpcionA());
        rbOpcionB.setText(p.getOpcionB());
        rbOpcionC.setText(p.getOpcionC());
        rbOpcionD.setText(p.getOpcionD());

        // Limpiar selección anterior
        grupoOpciones.clearSelection();

        // Forzar redibujado
        panelCentro.revalidate();
        panelCentro.repaint();
    }

    // =========================================================
    // ACCIÓN: CONFIRMAR RESPUESTA
    // =========================================================
    private void accionResponder() {

        // Detectar opción seleccionada
        String respuesta = null;
        if (rbOpcionA.isSelected()) respuesta = "A";
        else if (rbOpcionB.isSelected()) respuesta = "B";
        else if (rbOpcionC.isSelected()) respuesta = "C";
        else if (rbOpcionD.isSelected()) respuesta = "D";

        // Validar que haya seleccionado algo
        if (respuesta == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "⚠️ Debes seleccionar una respuesta"
                    + " antes de continuar.",
                    "Respuesta requerida",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Enviar al controlador
        ResultadoRespuesta resultado =
                quizController.responderPregunta(respuesta);

        // Sonido
        if (resultado.isEsCorrecta()) {
            AudioManager.reproducirCorrecto();
        } else {
            AudioManager.reproducirIncorrecto();
        }

        // Abrir pantalla de feedback
        ResultadoPreguntaFrame feedback =
                new ResultadoPreguntaFrame(
                        resultado,
                        quizController,
                        usuarioActual,
                        framePadre,
                        this);
        feedback.setVisible(true);
        this.setVisible(false);
    }

    // =========================================================
    // FINALIZAR QUIZ
    // =========================================================
    private void finalizarQuiz() {
        AudioManager.reproducirFinal();
        edupots.model.Resultado resultado =
                quizController.finalizarQuiz();
        ResultadosFinalesFrame finales =
                new ResultadosFinalesFrame(
                        resultado,
                        usuarioActual,
                        framePadre);
        finales.setVisible(true);
        this.dispose();
    }

    // =========================================================
    // MÉTODO PÚBLICO: llamado desde ResultadoPreguntaFrame
    // =========================================================
    public void avanzarSiguientePregunta() {
        this.setVisible(true);
        mostrarPreguntaActual();
    }
}