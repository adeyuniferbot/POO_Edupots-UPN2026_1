/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.view;

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
public class CreditosFrame extends JFrame {

    // =========================================================
    // COMPONENTES
    // =========================================================
    private JPanel  panelPrincipal;
    private JPanel  panelHeader;
    private JPanel  panelContenido;
    private JButton btnVolver;

    // =========================================================
    // DATOS
    // =========================================================
    private JFrame framePadre;

    // =========================================================
    // COLORES
    // =========================================================
    private static final Color COLOR_PRIMARIO   = new Color(44,  62,  80);
    private static final Color COLOR_SECUNDARIO = new Color(52,  73,  94);
    private static final Color COLOR_FONDO      = new Color(236, 240, 241);
    private static final Color COLOR_TEXTO      = new Color(44,  62,  80);
    private static final Color COLOR_ACENTO     = new Color(243, 156, 18);
    private static final Color COLOR_AZUL       = new Color(41,  128, 185);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================
    public CreditosFrame(JFrame padre) {
        this.framePadre = padre;
        initComponents();
        configurarVentana();
    }

    private void configurarVentana() {
        setTitle("EduPots — Créditos");
        setSize(540, 720);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // =========================================================
    // INICIALIZACIÓN
    // =========================================================
    private void initComponents() {
        panelPrincipal = new JPanel(new BorderLayout());
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
                new java.awt.Dimension(540, 150));

        // Logo
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(0, 12, 540, 72);
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icono = ImageManager.cargarLogo(58, 58);
        if (icono != null) {
            lblLogo.setIcon(icono);
        } else {
            lblLogo.setText("🎓");
            lblLogo.setFont(
                    new Font("Segoe UI Emoji", Font.PLAIN, 52));
            lblLogo.setForeground(Color.WHITE);
        }
        panelHeader.add(lblLogo);

        JLabel lblTitulo = new JLabel("EduPots");
        lblTitulo.setBounds(0, 88, 540, 38);
        lblTitulo.setHorizontalAlignment(
                SwingConstants.CENTER);
        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 30));
        lblTitulo.setForeground(Color.WHITE);
        panelHeader.add(lblTitulo);

        JLabel lblSub = new JLabel(
                "Sistema de Quizzes Interactivos");
        lblSub.setBounds(0, 126, 540, 22);
        lblSub.setHorizontalAlignment(SwingConstants.CENTER);
        lblSub.setFont(
                new Font("Segoe UI", Font.PLAIN, 13));
        lblSub.setForeground(
                new Color(255, 255, 255, 200));
        panelHeader.add(lblSub);

        panelPrincipal.add(panelHeader, BorderLayout.NORTH);

        // ===== CONTENIDO =====
        panelContenido = new JPanel(null);
        panelContenido.setBackground(COLOR_FONDO);

        int y = 18;

        // ----- INFORMACIÓN DEL PROYECTO -----
        y = agregarSeccion(panelContenido, "ℹ️ Información del Proyecto",
                new String[][]{
                    {"Proyecto",     "EduPots"},
                    {"Universidad",  "Universidad Privada del Norte"},
                    {"Institución",  "I.E. 5182 Señor de los Milagros"},
                    {"Curso", "Técnicas de Programación Orientada a Objetos"},
                    {"Año",          "2026"}
                }, y);

        y += 12;

        // ----- EQUIPO DE DESARROLLO -----
        JPanel tarjetaEquipo = crearTarjeta(20, y, 495, 155);
        y += 170;

        JLabel lblEquipoTit = new JLabel(
                "Equipo de Desarrollo");
        lblEquipoTit.setBounds(15, 12, 465, 26);
        lblEquipoTit.setFont(
                new Font("Segoe UI", Font.BOLD, 15));
        lblEquipoTit.setForeground(COLOR_ACENTO);
        tarjetaEquipo.add(lblEquipoTit);

        String[] integrantes = {
            "👤  Camila Nadine Montalvo Abrego",
            "👤  Víctor Ismael Guarnizo Briceño",
            "👤  Ademir Kleberson José Prudencio Huaman"
        };
        int yInt = 44;
        for (String integrante : integrantes) {
            JLabel lbl = new JLabel(integrante);
            lbl.setBounds(15, yInt, 465, 28);
            lbl.setFont(
                    new Font("Segoe UI", Font.PLAIN, 13));
            lbl.setForeground(COLOR_TEXTO);
            tarjetaEquipo.add(lbl);
            yInt += 34;
        }
        panelContenido.add(tarjetaEquipo);

        // ----- TECNOLOGÍAS -----
        JPanel tarjetaTech = crearTarjeta(20, y, 495, 130);
        y += 145;

        JLabel lblTechTit = new JLabel(
                " Tecnologías Utilizadas");
        lblTechTit.setBounds(15, 12, 465, 26);
        lblTechTit.setFont(
                new Font("Segoe UI", Font.BOLD, 15));
        lblTechTit.setForeground(COLOR_AZUL);
        tarjetaTech.add(lblTechTit);

        String[][] techs = {
            {"☕", "Java 17+",     "Lenguaje de programación"},
            {"🖥️", "Swing",        "Interfaz gráfica de escritorio"},
            {"🔧", "NetBeans IDE", "Entorno de desarrollo"},
            {"📦", "POO",          "ArrayList, HashMap, Herencia"}
        };

        int yTech = 42;
        for (String[] tech : techs) {
            JLabel lblEmoji = new JLabel(tech[0]);
            lblEmoji.setBounds(12, yTech, 25, 20);
            lblEmoji.setFont(new Font(
                    "Segoe UI Emoji", Font.PLAIN, 13));
            tarjetaTech.add(lblEmoji);

            JLabel lblNom = new JLabel(tech[1]);
            lblNom.setBounds(42, yTech, 130, 20);
            lblNom.setFont(new Font(
                    "Segoe UI", Font.BOLD, 12));
            lblNom.setForeground(COLOR_TEXTO);
            tarjetaTech.add(lblNom);

            JLabel lblDesc = new JLabel(tech[2]);
            lblDesc.setBounds(180, yTech, 300, 20);
            lblDesc.setFont(new Font(
                    "Segoe UI", Font.PLAIN, 12));
            lblDesc.setForeground(
                    new Color(127, 140, 141));
            tarjetaTech.add(lblDesc);

            yTech += 22;
        }
        panelContenido.add(tarjetaTech);

        // ----- BOTÓN VOLVER -----
        btnVolver = new JButton("← Volver al Menú") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(COLOR_PRIMARIO);
                g2d.fillRoundRect(0, 0,
                        getWidth(), getHeight(), 10, 10);
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font(
                        "Segoe UI", Font.BOLD, 15));
                java.awt.FontMetrics fm =
                        g2d.getFontMetrics();
                int x = (getWidth()
                        - fm.stringWidth(getText())) / 2;
                int by = (getHeight() + fm.getAscent()
                        - fm.getDescent()) / 2;
                g2d.drawString(getText(), x, by);
            }
        };
        btnVolver.setBounds(20, y, 495, 50);
        btnVolver.setBorderPainted(false);
        btnVolver.setContentAreaFilled(false);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e -> volverAlMenu());
        panelContenido.add(btnVolver);

        y += 65;
        panelContenido.setPreferredSize(
                new java.awt.Dimension(520, y));

        JScrollPane scroll = new JScrollPane(panelContenido);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        panelPrincipal.add(scroll, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
    }

    // =========================================================
    // MÉTODOS AUXILIARES
    // =========================================================
    private int agregarSeccion(JPanel panel,
                                String titulo,
                                String[][] filas,
                                int yInicio) {
        int altoTarjeta = 40 + filas.length * 32;
        JPanel tarjeta = crearTarjeta(
                20, yInicio, 495, altoTarjeta);

        JLabel lblTit = new JLabel(titulo);
        lblTit.setBounds(15, 12, 465, 26);
        lblTit.setFont(
                new Font("Segoe UI", Font.BOLD, 15));
        lblTit.setForeground(COLOR_ACENTO);
        tarjeta.add(lblTit);

        int yFila = 44;
        for (String[] fila : filas) {
            JLabel lbl = new JLabel(fila[0] + ":");
            lbl.setBounds(15, yFila, 150, 24);
            lbl.setFont(new Font(
                    "Segoe UI", Font.BOLD, 12));
            lbl.setForeground(
                    new Color(127, 140, 141));
            tarjeta.add(lbl);

            JLabel val = new JLabel(fila[1]);
            val.setBounds(170, yFila, 310, 24);
            val.setFont(new Font(
                    "Segoe UI", Font.PLAIN, 12));
            val.setForeground(COLOR_TEXTO);
            tarjeta.add(val);

            yFila += 32;
        }

        panel.add(tarjeta);
        return yInicio + altoTarjeta + 12;
    }

    private JPanel crearTarjeta(int x, int y,
                                 int w, int h) {
        JPanel t = new JPanel(null);
        t.setBounds(x, y, w, h);
        t.setBackground(Color.WHITE);
        t.setBorder(BorderFactory.createLineBorder(
                new Color(220, 220, 220), 1));
        return t;
    }

    // =========================================================
    // ACCIÓN
    // =========================================================
    private void volverAlMenu() {
        AudioManager.reproducirClick();
        framePadre.setVisible(true);
        this.dispose();
    }
}
