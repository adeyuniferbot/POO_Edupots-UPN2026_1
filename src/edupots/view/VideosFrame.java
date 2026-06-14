/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.view;

import edupots.model.Usuario;
import edupots.utils.AudioManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.net.URI;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
/**
 *
 * @author camil
 */
public class VideosFrame extends JFrame {

    // =========================================================
    // DATOS DE LOS VIDEOS
    // Formato: {emoji, título, descripción, URL}
    // =========================================================
    private static final String[][] VIDEOS = {
        {
            "➕",
            "Suma y Resta para Niños",
            "Aprende a sumar y restar de forma divertida",
            "https://youtu.be/oexd_Dfic_Q?si=17hSsrh6xs1Jou-3"
        },
        {
            "✖️",
            "Tablas de Multiplicar",
            "Memoriza las tablas con música y canciones",
            "https://youtu.be/YFtEaVw5k1A?si=YUBwElVGtFAVb4SE"
        },
        {
            "➗",
            "División para Primaria",
            "Aprende a dividir paso a paso fácilmente",
            "https://youtu.be/iA0fP4tL67s?si=kgfzQPuF73zr0PMw"
        },
        {
            "📐",
            "Figuras Geométricas",
            "Conoce los polígonos y sus propiedades",
            "https://youtu.be/SXONzObzFk0?si=jWX0-HlWkaBkMhCw"
        },
        {
            "🔢",
            "Ecuaciones Básicas",
            "Resuelve ecuaciones simples paso a paso",
            "https://youtu.be/NexxgFn2UXs?si=acFHJIhi69X5jyB9"
        },
        {
            "🧠",
            "Cálculo Mental Rápido",
            "Técnicas para calcular rápidamente",
            "https://youtu.be/YlBcuvihOtU?si=SxT6IgDw3vHzsJiU"
        }
    };

    // =========================================================
    // COMPONENTES
    // =========================================================
    private JPanel   panelPrincipal;
    private JPanel   panelHeader;
    private JPanel   panelVideos;
    private JButton  btnVolver;

    // =========================================================
    // DATOS
    // =========================================================
    private Usuario usuarioActual;
    private JFrame  framePadre;

    // =========================================================
    // COLORES
    // =========================================================
    private static final Color COLOR_PRIMARIO   = new Color(231, 76,  60);
    private static final Color COLOR_SECUNDARIO = new Color(192, 57,  43);
    private static final Color COLOR_FONDO      = new Color(236, 240, 241);
    private static final Color COLOR_TEXTO      = new Color(44,  62,  80);

    private static final Color[] COLORES_VIDEO = {
        new Color(41,  128, 185),
        new Color(39,  174, 96),
        new Color(231, 76,  60),
        new Color(142, 68,  173),
        new Color(243, 156, 18),
        new Color(26,  188, 156)
    };

    // =========================================================
    // CONSTRUCTOR
    // =========================================================
    public VideosFrame(Usuario usuario, JFrame padre) {
        this.usuarioActual = usuario;
        this.framePadre    = padre;
        initComponents();
        configurarVentana();
        cargarVideos();
    }

    private void configurarVentana() {
        setTitle("EduPots — Videos Educativos");
        setSize(640, 680);
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
                new java.awt.Dimension(640, 100));

        JLabel lblTitulo = new JLabel(
                "  Videos Educativos");
        lblTitulo.setBounds(20, 18, 500, 38);
        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        panelHeader.add(lblTitulo);

        JLabel lblSub = new JLabel(
                "Aprende matemáticas con videos de YouTube");
        lblSub.setBounds(20, 57, 460, 22);
        lblSub.setFont(
                new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(
                new Color(255, 255, 255, 200));
        panelHeader.add(lblSub);

        btnVolver = new JButton("← Volver");
        btnVolver.setBounds(520, 32, 100, 35);
        btnVolver.setFont(
                new Font("Segoe UI", Font.BOLD, 12));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBorderPainted(false);
        btnVolver.setBackground(COLOR_PRIMARIO);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(
                new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(
                e -> volverAlMenu());
        panelHeader.add(btnVolver);

        panelPrincipal.add(panelHeader, BorderLayout.NORTH);

        // ===== PANEL VIDEOS =====
        panelVideos = new JPanel(null);
        panelVideos.setBackground(COLOR_FONDO);
        panelVideos.setPreferredSize(
                new java.awt.Dimension(620, 600));

        JScrollPane scroll =
                new JScrollPane(panelVideos);
        scroll.setBorder(
                BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar()
                .setUnitIncrement(16);

        panelPrincipal.add(scroll, BorderLayout.CENTER);
        setContentPane(panelPrincipal);
    }

    // =========================================================
    // CARGAR TARJETAS DE VIDEO
    // =========================================================
    private void cargarVideos() {
        int y = 18;
        for (int i = 0; i < VIDEOS.length; i++) {
            JPanel tarjeta = crearTarjetaVideo(
                    VIDEOS[i], COLORES_VIDEO[i]);
            tarjeta.setBounds(18, y, 590, 88);
            panelVideos.add(tarjeta);
            y += 100;
        }
        panelVideos.setPreferredSize(
                new java.awt.Dimension(620, y + 20));
        panelVideos.revalidate();
    }

    // =========================================================
    // FÁBRICA DE TARJETA DE VIDEO
    // =========================================================
    private JPanel crearTarjetaVideo(String[] video,
                                      Color color) {
        JPanel tarjeta = new JPanel(null);
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220), 1));

        // Franja de color izquierda
        JPanel franja = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(color);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        franja.setBounds(0, 0, 8, 88);
        tarjeta.add(franja);

        // Emoji
        JLabel lblEmoji = new JLabel(video[0]);
        lblEmoji.setBounds(20, 18, 55, 52);
        lblEmoji.setHorizontalAlignment(
                SwingConstants.CENTER);
        lblEmoji.setFont(
                new Font("Segoe UI Emoji", Font.PLAIN, 36));
        tarjeta.add(lblEmoji);

        // Título
        JLabel lblTitulo = new JLabel(video[1]);
        lblTitulo.setBounds(88, 14, 360, 26);
        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(COLOR_TEXTO);
        tarjeta.add(lblTitulo);

        // Descripción
        JLabel lblDesc = new JLabel(video[2]);
        lblDesc.setBounds(88, 40, 360, 20);
        lblDesc.setFont(
                new Font("Segoe UI", Font.PLAIN, 12));
        lblDesc.setForeground(
                new Color(127, 140, 141));
        tarjeta.add(lblDesc);

        // URL (pequeña)
        JLabel lblUrl = new JLabel("youtube.com");
        lblUrl.setBounds(88, 60, 200, 18);
        lblUrl.setFont(
                new Font("Segoe UI", Font.PLAIN, 10));
        lblUrl.setForeground(
                new Color(149, 165, 166));
        tarjeta.add(lblUrl);

        // Botón Ver Video
        JButton btnVer = new JButton(" Ver Video") {
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
                g2d.setFont(new Font(
                        "Segoe UI", Font.BOLD, 12));
                java.awt.FontMetrics fm =
                        g2d.getFontMetrics();
                int x = (getWidth()
                        - fm.stringWidth(getText())) / 2;
                int y2 = (getHeight() + fm.getAscent()
                        - fm.getDescent()) / 2;
                g2d.drawString(getText(), x, y2);
            }
        };
        btnVer.setBounds(460, 24, 118, 40);
        btnVer.setBorderPainted(false);
        btnVer.setContentAreaFilled(false);
        btnVer.setFocusPainted(false);
        btnVer.setCursor(new Cursor(Cursor.HAND_CURSOR));

        String urlVideo = video[3];
        btnVer.addActionListener(
                e -> abrirVideo(urlVideo));
        tarjeta.add(btnVer);

        return tarjeta;
    }

    // =========================================================
    // ABRIR VIDEO EN NAVEGADOR
    // =========================================================
    private void abrirVideo(String url) {
        AudioManager.reproducirClick();
        try {
            if (Desktop.isDesktopSupported()
                    && Desktop.getDesktop().isSupported(
                            Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(
                        new URI(url));
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Tu sistema no soporta abrir\n"
                        + "el navegador automáticamente.\n\n"
                        + "Copia este enlace en tu navegador:\n"
                        + url,
                        "Abrir Video",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo abrir el video.\n"
                    + "Intenta copiar este enlace:\n" + url,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
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
