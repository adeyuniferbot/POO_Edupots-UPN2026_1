/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.view;

import edupots.controller.ProgresoController;
import edupots.controller.UsuarioController;
import edupots.model.Insignia;
import edupots.model.Resultado;
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
import java.util.ArrayList;
import javax.swing.BorderFactory;
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
public class ResultadosFinalesFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ResultadosFinalesFrame.class.getName());
    
    // LOS COMPONENTES
    private JPanel  panelPrincipal;
    private JPanel  panelHeader;
    private JPanel  panelContenido;
    private JButton btnVolverInicio;
    private JButton btnVerProgreso;

    // ACA SON LOS DATOS
    private Resultado           resultado;
    private Usuario             usuarioActual;
    private JFrame              framePadre;
    private ProgresoController  progresoController;
    private UsuarioController   usuarioController;

    // LOS COLORES
    private static final Color COLOR_PRIMARIO   = new Color(41,  128, 185);
    private static final Color COLOR_SECUNDARIO = new Color(52,  152, 219);
    private static final Color COLOR_FONDO      = new Color(236, 240, 241);
    private static final Color COLOR_TEXTO      = new Color(44,  62,  80);
    private static final Color COLOR_ORO        = new Color(243, 156, 18);
    private static final Color COLOR_VERDE      = new Color(39,  174, 96);

    // ESTE ES EL CONSTRUCTOR
    public ResultadosFinalesFrame(Resultado resultado,
                                   Usuario usuario,
                                   JFrame padre) {
        this.resultado          = resultado;
        this.usuarioActual      = usuario;
        this.framePadre         = padre;
        this.progresoController = new ProgresoController();
        this.usuarioController  = new UsuarioController();

        // Actualizar puntaje del estudiante en archivo
        usuarioController.actualizarPuntajeEstudiante(
                usuario.getIdUsuario(),
                resultado.getPuntaje());

        initComponents();
        configurarVentana();
    }

    private void configurarVentana() {
        setTitle("EduPots — Resultados Finales");
        setSize(560, 700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
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
                new java.awt.Dimension(560, 155));

        JLabel lblIcono = new JLabel("🏆");
        lblIcono.setBounds(0, 10, 560, 60);
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        lblIcono.setFont(
                new Font("Segoe UI Emoji", Font.PLAIN, 48));
        panelHeader.add(lblIcono);

        JLabel lblTitulo = new JLabel("¡Quiz Completado!");
        lblTitulo.setBounds(0, 72, 560, 36);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        panelHeader.add(lblTitulo);

        JLabel lblNombre = new JLabel(
                resultado.getTituloQuiz());
        lblNombre.setBounds(0, 110, 560, 24);
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre.setFont(
                new Font("Segoe UI", Font.PLAIN, 15));
        lblNombre.setForeground(
                new Color(255, 255, 255, 200));
        panelHeader.add(lblNombre);

        panelPrincipal.add(panelHeader, BorderLayout.NORTH);

        // ===== CONTENIDO SCROLLEABLE =====
        panelContenido = new JPanel(null);
        panelContenido.setBackground(COLOR_FONDO);
        panelContenido.setPreferredSize(
                new java.awt.Dimension(540, 600));

        int y = 18;

        // ----- PUNTAJE GRANDE -----
        JPanel tarjetaPuntaje = crearTarjeta(25, y, 505, 100);
        y += 115;

        JLabel lblPuntajeTxt = new JLabel("Puntaje Total");
        lblPuntajeTxt.setBounds(15, 10, 475, 22);
        lblPuntajeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        lblPuntajeTxt.setFont(
                new Font("Segoe UI", Font.PLAIN, 13));
        lblPuntajeTxt.setForeground(new Color(127, 140, 141));
        tarjetaPuntaje.add(lblPuntajeTxt);

        JLabel lblPuntaje = new JLabel(
                resultado.getPuntaje() + " / 100");
        lblPuntaje.setBounds(15, 28, 475, 55);
        lblPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblPuntaje.setFont(
                new Font("Segoe UI", Font.BOLD, 42));
        lblPuntaje.setForeground(COLOR_ORO);
        tarjetaPuntaje.add(lblPuntaje);
        panelContenido.add(tarjetaPuntaje);

        // ----- ESTADÍSTICAS -----
        JPanel tarjetaStats = crearTarjeta(25, y, 505, 130);
        y += 145;

        JLabel lblStatsTit = new JLabel("Detalle del Quiz");
        lblStatsTit.setBounds(15, 10, 475, 22);
        lblStatsTit.setFont(
                new Font("Segoe UI", Font.BOLD, 14));
        lblStatsTit.setForeground(COLOR_TEXTO);
        tarjetaStats.add(lblStatsTit);

        agregarFila(tarjetaStats, " Respuestas correctas:",
                resultado.getCorrectas() + " de 10", 38,
                COLOR_VERDE);
        agregarFila(tarjetaStats, " Respuestas incorrectas:",
                resultado.getIncorrectas() + " de 10", 68,
                COLOR_PRIMARIO);
        agregarFila(tarjetaStats, " Tiempo empleado:",
                resultado.formatearTiempo(), 98,
                COLOR_PRIMARIO);
        panelContenido.add(tarjetaStats);

        // ----- NIVEL ALCANZADO -----
        JPanel tarjetaNivel = crearTarjeta(25, y, 505, 75);
        y += 90;

        JLabel lblNivelTit = new JLabel("Nivel alcanzado:");
        lblNivelTit.setBounds(15, 10, 200, 22);
        lblNivelTit.setFont(
                new Font("Segoe UI", Font.BOLD, 14));
        lblNivelTit.setForeground(COLOR_TEXTO);
        tarjetaNivel.add(lblNivelTit);

        JLabel lblNivel = new JLabel(
                resultado.getNivelAlcanzado());
        lblNivel.setBounds(15, 36, 475, 28);
        lblNivel.setFont(
                new Font("Segoe UI", Font.BOLD, 18));
        lblNivel.setForeground(COLOR_ORO);
        tarjetaNivel.add(lblNivel);
        panelContenido.add(tarjetaNivel);

        // ----- INSIGNIAS NUEVAS -----
        ArrayList<Insignia> nuevas =
                progresoController.actualizarTrasQuiz(
                        usuarioActual.getIdUsuario(),
                        resultado.getPuntaje());

        if (!nuevas.isEmpty()) {
            JPanel tarjetaInsignias =
                    crearTarjeta(25, y, 505, 85);
            y += 100;

            JLabel lblInsTit = new JLabel(
                    " ¡Insignias desbloqueadas!");
            lblInsTit.setBounds(15, 10, 475, 24);
            lblInsTit.setFont(
                    new Font("Segoe UI", Font.BOLD, 14));
            lblInsTit.setForeground(COLOR_ORO);
            tarjetaInsignias.add(lblInsTit);

            StringBuilder sbIns = new StringBuilder();
            for (Insignia ins : nuevas) {
                sbIns.append(ins.getEmoji())
                     .append(" ")
                     .append(ins.getNombre())
                     .append("   ");
            }

            JLabel lblInsignias = new JLabel(
                    sbIns.toString().trim());
            lblInsignias.setBounds(15, 38, 475, 30);
            lblInsignias.setFont(
                    new Font("Segoe UI", Font.PLAIN, 13));
            lblInsignias.setForeground(COLOR_TEXTO);
            tarjetaInsignias.add(lblInsignias);
            panelContenido.add(tarjetaInsignias);
        }

        // ----- BOTONES -----
        btnVolverInicio = crearBotonFinal(
                "  Volver al Inicio",
                COLOR_PRIMARIO, 25, y);
        y += 62;
        btnVerProgreso = crearBotonFinal(
                " Ver Mi Progreso",
                COLOR_VERDE, 25, y);

        btnVolverInicio.addActionListener(
                e -> volverAlInicio());
        btnVerProgreso.addActionListener(
                e -> verProgreso());

        panelContenido.add(btnVolverInicio);
        panelContenido.add(btnVerProgreso);

        panelContenido.setPreferredSize(
                new java.awt.Dimension(540, y + 80));

        JScrollPane scroll = new JScrollPane(panelContenido);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        panelPrincipal.add(scroll, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
    }
     
      private JPanel crearTarjeta(int x, int y, int w, int h) {
        JPanel t = new JPanel(null);
        t.setBounds(x, y, w, h);
        t.setBackground(Color.WHITE);
        t.setBorder(BorderFactory.createLineBorder(
                new Color(220, 220, 220), 1));
        return t;
    }

    private void agregarFila(JPanel panel, String label,
                              String valor, int y,
                              Color colorValor) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(15, y, 280, 24);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setForeground(COLOR_TEXTO);
        panel.add(lbl);

        JLabel val = new JLabel(valor);
        val.setBounds(295, y, 190, 24);
        val.setFont(new Font("Segoe UI", Font.BOLD, 13));
        val.setForeground(colorValor);
        panel.add(val);
    }

    private JButton crearBotonFinal(String texto,
                                     Color color,
                                     int x, int y) {
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
                        "Segoe UI", Font.BOLD, 15));
                java.awt.FontMetrics fm = g2d.getFontMetrics();
                int bx = (getWidth()
                        - fm.stringWidth(getText())) / 2;
                int by = (getHeight() + fm.getAscent()
                        - fm.getDescent()) / 2;
                g2d.drawString(getText(), bx, by);
            }
        };
        btn.setBounds(x, y, 505, 50);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
    //Las acciones
     private void volverAlInicio() {
        AudioManager.reproducirClick();
        framePadre.setVisible(true);
        this.dispose();
    }

    private void verProgreso() {
    AudioManager.reproducirClick();
    ProgresoFrame progreso = new ProgresoFrame(
            usuarioActual, framePadre);
    progreso.setVisible(true);
    this.dispose();
}
}
