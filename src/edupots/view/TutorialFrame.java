/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.view;

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
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
/**
 *
 * @author camil
 */
public class TutorialFrame extends JFrame {

    // =========================================================
    // COMPONENTES
    // =========================================================
    private JPanel       panelPrincipal;
    private JPanel       panelHeader;
    private JPanel       panelContenido;
    private JPanel       panelBotones;
    private JLabel       lblNumeroPaso;
    private JLabel       lblEmoji;
    private JLabel       lblTituloPaso;
    private JLabel       lblDescripcion;
    private JProgressBar barraProgreso;
    private JButton      btnAnterior;
    private JButton      btnSiguiente;
    private JButton      btnOmitir;

    // =========================================================
    // DATOS DEL TUTORIAL
    // =========================================================
    private JFrame framePadre;
    private int    pasoActual = 0;

    // Contenido de cada paso del tutorial
    private static final String[][] PASOS = {
        {
            "👋",
            "¡Bienvenido a EduPots!",
            "EduPots es un sistema de quizzes interactivos\n"
            + "diseñado para estudiantes de primaria de la\n"
            + "I.E. 5182 Señor de los Milagros.\n\n"
            + "Aquí podrás reforzar tus conocimientos de\n"
            + "matemáticas de forma divertida y sencilla."
        },
        {
            "🔐",
            "Cómo Iniciar Sesión",
            "1. Ingresa tu correo electrónico registrado.\n"
            + "2. Escribe tu contraseña.\n"
            + "3. Haz clic en el botón 'ACCEDER'.\n\n"
            + "El sistema te llevará automáticamente al\n"
            + "Menú Principal según tu rol de usuario."
        },
        {
            "📚",
            "Cómo Elegir un Quiz",
            "1. En el Menú Principal, haz clic en 'Iniciar Quiz'.\n"
            + "2. Verás la lista de quizzes disponibles:\n"
            + "   • Suma  • Multiplicación  • División\n"
            + "   • Geometría  • Ecuaciones  • Cálculo Mental\n\n"
            + "3. Haz clic en 'Iniciar ' en el quiz que\n"
            + "   desees resolver."
        },
        {
            "✏️",
            "Cómo Responder Preguntas",
            "1. Lee el enunciado de la pregunta con atención.\n"
            + "2. Selecciona una de las 4 opciones (A, B, C, D)\n"
            + "   haciendo clic sobre ella.\n"
            + "3. Haz clic en 'Confirmar Respuesta →'.\n\n"
            + "No puedes avanzar sin seleccionar\n"
            + "   una opción primero."
        },
        {
            "📊",
            "Cómo Ver tus Resultados",
            "Después de cada respuesta verás:\n"
            + "   Si fue correcta (+10 puntos)\n"
            + "   Si fue incorrecta (La respuesta correcta es:)\n\n"
            + "Al terminar el quiz verás:\n"
            + "  • Puntaje total  • Correctas e incorrectas\n"
            + "  • Tiempo empleado  • Nivel alcanzado\n"
            + "  • Insignias nuevas obtenidas"
        },
        {
            "📈",
            "Cómo Revisar tu Progreso",
            "En el Menú Principal haz clic en 'Mi Progreso'.\n\n"
            + "Allí encontrarás:\n"
            + "  • Tu nivel actual (Principiante → Maestro)\n"
            + "  • Barra de avance al siguiente nivel\n"
            + "  • Puntaje acumulado y promedio\n"
            + "  • Todas tus insignias desbloqueadas\n\n"
            + "En 'Historial' puedes ver todos los quizzes\n"
            + "que has completado con sus estadísticas."
        },
        {
            "🎉",
            "¡Ya estás listo!",
            "Has completado el tutorial de EduPots.\n\n"
            + "Recuerda:\n"
            + "  🎯 Cada quiz tiene 10 preguntas\n"
            + "  💯 Máximo 100 puntos por quiz\n"
            + "  🏆 Desbloquea insignias completando logros\n"
            + "  📈 Sube de nivel acumulando puntos\n\n"
            + "¡Buena suerte en tus quizzes!"
        }
    };

    // =========================================================
    // COLORES
    // =========================================================
    private static final Color COLOR_PRIMARIO   = new Color(243, 156, 18);
    private static final Color COLOR_SECUNDARIO = new Color(230, 126, 34);
    private static final Color COLOR_FONDO      = new Color(236, 240, 241);
    private static final Color COLOR_TEXTO      = new Color(44,  62,  80);
    private static final Color COLOR_AZUL       = new Color(41,  128, 185);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================
    public TutorialFrame(JFrame padre) {
        this.framePadre = padre;
        initComponents();
        configurarVentana();
        mostrarPaso(0);
    }

    private void configurarVentana() {
        setTitle("EduPots — Tutorial");
        setSize(560, 580);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // =========================================================
    // INICIALIZACIÓN
    // =========================================================
    private void initComponents() {
        panelPrincipal = new JPanel(new BorderLayout(0, 0));
        panelPrincipal.setBackground(COLOR_FONDO);

        // ===== HEADER =====
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
                new java.awt.Dimension(560, 90));

        JLabel lblTitulo = new JLabel(
                "  Tutorial de EduPots");
        lblTitulo.setBounds(20, 15, 400, 35);
        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);
        panelHeader.add(lblTitulo);

        lblNumeroPaso = new JLabel("Paso 1 de 7");
        lblNumeroPaso.setBounds(20, 52, 200, 22);
        lblNumeroPaso.setFont(
                new Font("Segoe UI", Font.PLAIN, 13));
        lblNumeroPaso.setForeground(
                new Color(255, 255, 255, 200));
        panelHeader.add(lblNumeroPaso);

        // Barra de progreso del tutorial
        barraProgreso = new JProgressBar(0,
                PASOS.length - 1);
        barraProgreso.setBounds(20, 75, 520, 8);
        barraProgreso.setValue(0);
        barraProgreso.setForeground(Color.WHITE);
        barraProgreso.setBackground(
                new Color(255, 255, 255, 80));
        barraProgreso.setBorderPainted(false);
        panelHeader.add(barraProgreso);

        panelPrincipal.add(panelHeader, BorderLayout.NORTH);

        // ===== PANEL CONTENIDO =====
        panelContenido = new JPanel(null);
        panelContenido.setBackground(COLOR_FONDO);

        // Emoji grande
        lblEmoji = new JLabel();
        lblEmoji.setBounds(0, 25, 560, 85);
        lblEmoji.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmoji.setFont(
                new Font("Segoe UI Emoji", Font.PLAIN, 68));
        panelContenido.add(lblEmoji);

        // Tarjeta de contenido
        JPanel tarjeta = new JPanel(null);
        tarjeta.setBounds(30, 118, 495, 295);
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220), 1));

        lblTituloPaso = new JLabel();
        lblTituloPaso.setBounds(20, 15, 455, 34);
        lblTituloPaso.setFont(
                new Font("Segoe UI", Font.BOLD, 20));
        lblTituloPaso.setForeground(COLOR_PRIMARIO);
        tarjeta.add(lblTituloPaso);

        lblDescripcion = new JLabel();
        lblDescripcion.setBounds(20, 55, 455, 225);
        lblDescripcion.setFont(
                new Font("Segoe UI", Font.PLAIN, 13));
        lblDescripcion.setForeground(COLOR_TEXTO);
        lblDescripcion.setVerticalAlignment(
                SwingConstants.TOP);
        tarjeta.add(lblDescripcion);

        panelContenido.add(tarjeta);
        panelPrincipal.add(
                panelContenido, BorderLayout.CENTER);

        // ===== PANEL BOTONES =====
        panelBotones = new JPanel(null);
        panelBotones.setBackground(COLOR_FONDO);
        panelBotones.setPreferredSize(
                new java.awt.Dimension(560, 68));

        btnAnterior = new JButton("← Anterior");
        btnAnterior.setBounds(20, 12, 130, 42);
        btnAnterior.setFont(
                new Font("Segoe UI", Font.BOLD, 13));
        btnAnterior.setForeground(
                new Color(127, 140, 141));
        btnAnterior.setBorderPainted(false);
        btnAnterior.setBackground(COLOR_FONDO);
        btnAnterior.setFocusPainted(false);
        btnAnterior.setCursor(
                new Cursor(Cursor.HAND_CURSOR));
        btnAnterior.addActionListener(
                e -> navegarAnterior());
        panelBotones.add(btnAnterior);

        btnOmitir = new JButton("Omitir");
        btnOmitir.setBounds(200, 12, 100, 42);
        btnOmitir.setFont(
                new Font("Segoe UI", Font.PLAIN, 13));
        btnOmitir.setForeground(
                new Color(127, 140, 141));
        btnOmitir.setBorderPainted(false);
        btnOmitir.setBackground(COLOR_FONDO);
        btnOmitir.setFocusPainted(false);
        btnOmitir.setCursor(
                new Cursor(Cursor.HAND_CURSOR));
        btnOmitir.addActionListener(
                e -> omitirTutorial());
        panelBotones.add(btnOmitir);

        btnSiguiente = crearBotonNavegacion(
                "Siguiente →", COLOR_PRIMARIO);
        btnSiguiente.setBounds(318, 12, 222, 42);
        btnSiguiente.addActionListener(
                e -> navegarSiguiente());
        panelBotones.add(btnSiguiente);

        panelPrincipal.add(
                panelBotones, BorderLayout.SOUTH);
        setContentPane(panelPrincipal);
    }

    // =========================================================
    // MOSTRAR PASO
    // =========================================================
    private void mostrarPaso(int indice) {
        if (indice < 0 || indice >= PASOS.length) return;

        pasoActual = indice;
        String[] paso = PASOS[indice];

        // Actualizar header
        lblNumeroPaso.setText(
                "Paso " + (indice + 1)
                + " de " + PASOS.length);
        barraProgreso.setValue(indice);

        // Actualizar contenido
        lblEmoji.setText(paso[0]);
        lblTituloPaso.setText(paso[1]);

        // Convertir saltos de línea a HTML
        String html = "<html><body style='width:430px'>"
                + paso[2].replace("\n", "<br>")
                + "</body></html>";
        lblDescripcion.setText(html);

        // Actualizar botones
        btnAnterior.setEnabled(indice > 0);
        btnAnterior.setForeground(indice > 0
                ? COLOR_TEXTO
                : new Color(180, 180, 180));

        boolean esUltimo = (indice == PASOS.length - 1);
        btnSiguiente.setText(esUltimo
                ? " Finalizar" : "Siguiente →");
        btnOmitir.setVisible(!esUltimo);
    }

    // =========================================================
    // NAVEGACIÓN
    // =========================================================
    private void navegarSiguiente() {
        AudioManager.reproducirClick();
        if (pasoActual < PASOS.length - 1) {
            mostrarPaso(pasoActual + 1);
        } else {
            // Último paso: finalizar tutorial
            finalizarTutorial();
        }
    }

    private void navegarAnterior() {
        AudioManager.reproducirClick();
        if (pasoActual > 0) {
            mostrarPaso(pasoActual - 1);
        }
    }

    private void omitirTutorial() {
        AudioManager.reproducirClick();
        finalizarTutorial();
    }

    private void finalizarTutorial() {
        framePadre.setVisible(true);
        this.dispose();
    }

    // =========================================================
    // BOTÓN ESTILIZADO
    // =========================================================
    private JButton crearBotonNavegacion(String texto,
                                          Color color) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(color);
                g2d.fillRoundRect(0, 0,
                        getWidth(), getHeight(), 10, 10);
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font(
                        "Segoe UI", Font.BOLD, 14));
                java.awt.FontMetrics fm =
                        g2d.getFontMetrics();
                int x = (getWidth()
                        - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent()
                        - fm.getDescent()) / 2;
                g2d.drawString(getText(), x, y);
            }
        };
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}