/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.view;

import edupots.controller.ProgresoController;
import edupots.model.Insignia;
import edupots.model.Progreso;
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
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
/**
 *
 * @author camil
 */
public class ProgresoFrame extends JFrame {

    // =========================================================
    // COMPONENTES
    // =========================================================
    private JPanel      panelPrincipal;
    private JPanel      panelHeader;
    private JPanel      panelContenido;
    private JProgressBar barraProgreso;
    private JButton     btnVolver;

    // =========================================================
    // DATOS
    // =========================================================
    private Usuario            usuarioActual;
    private JFrame             framePadre;
    private ProgresoController progresoController;

    // =========================================================
    // COLORES
    // =========================================================
    private static final Color COLOR_PRIMARIO   = new Color(142, 68,  173);
    private static final Color COLOR_SECUNDARIO = new Color(155, 89,  182);
    private static final Color COLOR_FONDO      = new Color(236, 240, 241);
    private static final Color COLOR_TEXTO      = new Color(44,  62,  80);
    private static final Color COLOR_ORO        = new Color(243, 156, 18);
    private static final Color COLOR_VERDE      = new Color(39,  174, 96);

    // =========================================================
    // CONSTRUCTORES
    // =========================================================
    public ProgresoFrame(Usuario usuario, JFrame padre) {
        this.usuarioActual     = usuario;
        this.framePadre        = padre;
        this.progresoController = new ProgresoController();
        initComponents();
        configurarVentana();
    }

    // Constructor alternativo desde ResultadosFinalesFrame
    public ProgresoFrame(Usuario usuario,
                          JFrame padre,
                          boolean sinPadre) {
        this(usuario, padre);
    }

    private void configurarVentana() {
        setTitle("EduPots — Mi Progreso");
        setSize(560, 700);
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
                new java.awt.Dimension(560, 100));

        JLabel lblTitulo = new JLabel(" Mi Progreso");
        lblTitulo.setBounds(20, 18, 400, 38);
        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        panelHeader.add(lblTitulo);

        JLabel lblSub = new JLabel(
                usuarioActual.getNombre());
        lblSub.setBounds(20, 57, 400, 22);
        lblSub.setFont(
                new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(
                new Color(255, 255, 255, 200));
        panelHeader.add(lblSub);

        btnVolver = new JButton("← Volver");
        btnVolver.setBounds(440, 32, 100, 35);
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

        // ===== CONTENIDO SCROLLEABLE =====
        panelContenido = new JPanel(null);
        panelContenido.setBackground(COLOR_FONDO);

        // Obtener progreso del controlador
        Progreso progreso = progresoController
                .obtenerProgreso(
                        usuarioActual.getIdUsuario());

        int y = 18;

        // ----- NIVEL ACTUAL -----
        JPanel tarjetaNivel = crearTarjeta(
                20, y, 510, 110);
        y += 125;

        JLabel lblNivelTit = new JLabel("Nivel Actual");
        lblNivelTit.setBounds(15, 10, 480, 22);
        lblNivelTit.setFont(
                new Font("Segoe UI", Font.PLAIN, 12));
        lblNivelTit.setForeground(
                new Color(127, 140, 141));
        tarjetaNivel.add(lblNivelTit);

        JLabel lblNivel = new JLabel(
                progreso.getNombreNivel());
        lblNivel.setBounds(15, 30, 480, 36);
        lblNivel.setFont(
                new Font("Segoe UI", Font.BOLD, 24));
        lblNivel.setForeground(COLOR_ORO);
        tarjetaNivel.add(lblNivel);

        // Barra de progreso
        barraProgreso = new JProgressBar(0, 100);
        barraProgreso.setValue(
                progreso.getPorcentajeAvance());
        barraProgreso.setBounds(15, 72, 480, 22);
        barraProgreso.setStringPainted(true);
        barraProgreso.setString(
                progreso.getPorcentajeAvance()
                + "% hacia el siguiente nivel");
        barraProgreso.setForeground(COLOR_ORO);
        barraProgreso.setBackground(
                new Color(220, 220, 220));
        barraProgreso.setFont(
                new Font("Segoe UI", Font.PLAIN, 11));
        barraProgreso.setBorderPainted(false);
        tarjetaNivel.add(barraProgreso);
        panelContenido.add(tarjetaNivel);

        // ----- ESTADÍSTICAS -----
        JPanel tarjetaStats = crearTarjeta(
                20, y, 510, 150);
        y += 165;

        JLabel lblStatsTit = new JLabel(
                "Estadísticas Generales");
        lblStatsTit.setBounds(15, 12, 480, 24);
        lblStatsTit.setFont(
                new Font("Segoe UI", Font.BOLD, 15));
        lblStatsTit.setForeground(COLOR_TEXTO);
        tarjetaStats.add(lblStatsTit);

        agregarFilaProgreso(tarjetaStats,
                " Quizzes completados:",
                String.valueOf(
                        progreso.getQuizzesResueltos()),
                42, COLOR_PRIMARIO);
        agregarFilaProgreso(tarjetaStats,
                " Puntaje acumulado:",
                progreso.getPuntajeAcumulado() + " pts",
                78, COLOR_ORO);
        agregarFilaProgreso(tarjetaStats,
                " Promedio por quiz:",
                String.format("%.1f pts",
                        progreso.getPromedio()),
                114, COLOR_VERDE);
        panelContenido.add(tarjetaStats);

        // ----- NIVELES EXPLICADOS -----
        JPanel tarjetaNiveles = crearTarjeta(
                20, y, 510, 175);
        y += 190;

        JLabel lblNivTit = new JLabel(
                "Sistema de Niveles");
        lblNivTit.setBounds(15, 12, 480, 24);
        lblNivTit.setFont(
                new Font("Segoe UI", Font.BOLD, 15));
        lblNivTit.setForeground(COLOR_TEXTO);
        tarjetaNiveles.add(lblNivTit);

        String[][] niveles = {
            {"⭐",     "Principiante",
                        "0 – 99 puntos acumulados",   "1"},
            {"⭐⭐",   "Explorador",
                        "100 – 199 puntos acumulados", "2"},
            {"⭐⭐⭐", "Experto",
                        "200 – 299 puntos acumulados", "3"},
            {"⭐⭐⭐⭐","Maestro Matemático",
                        "300+ puntos acumulados",      "4"}
        };

        int yNiv = 42;
        for (String[] n : niveles) {
            boolean esActual = n[3].equals(
                    String.valueOf(progreso.getNivel()));
            JPanel filaNivel = new JPanel(null);
            filaNivel.setBounds(15, yNiv, 480, 28);
            filaNivel.setBackground(esActual
                    ? new Color(253, 243, 219)
                    : Color.WHITE);
            filaNivel.setBorder(esActual
                    ? BorderFactory.createLineBorder(
                            COLOR_ORO, 1)
                    : null);

            JLabel e = new JLabel(n[0]);
            e.setBounds(5, 2, 60, 24);
            e.setFont(new Font(
                    "Segoe UI Emoji", Font.PLAIN, 13));
            filaNivel.add(e);

            JLabel nom = new JLabel(n[1]
                    + (esActual ? "  ← TÚ" : ""));
            nom.setBounds(68, 2, 160, 24);
            nom.setFont(new Font("Segoe UI",
                    esActual ? Font.BOLD : Font.PLAIN,
                    12));
            nom.setForeground(esActual
                    ? COLOR_ORO : COLOR_TEXTO);
            filaNivel.add(nom);

            JLabel desc = new JLabel(n[2]);
            desc.setBounds(240, 2, 235, 24);
            desc.setFont(
                    new Font("Segoe UI", Font.PLAIN, 11));
            desc.setForeground(
                    new Color(127, 140, 141));
            filaNivel.add(desc);

            tarjetaNiveles.add(filaNivel);
            yNiv += 32;
        }
        panelContenido.add(tarjetaNiveles);

        // ----- INSIGNIAS -----
        JPanel tarjetaIns = crearTarjeta(
                20, y, 510, 180);
        y += 195;

        JLabel lblInsTit = new JLabel(
                " Mis Insignias");
        lblInsTit.setBounds(15, 12, 480, 24);
        lblInsTit.setFont(
                new Font("Segoe UI", Font.BOLD, 15));
        lblInsTit.setForeground(COLOR_TEXTO);
        tarjetaIns.add(lblInsTit);

        ArrayList<Insignia> insignias =
                progreso.getInsignias();

        if (insignias.isEmpty()) {
            JLabel lblSinIns = new JLabel(
                    "Completa quizzes para desbloquear insignias");
            lblSinIns.setBounds(15, 45, 480, 24);
            lblSinIns.setFont(
                    new Font("Segoe UI", Font.ITALIC, 13));
            lblSinIns.setForeground(
                    new Color(127, 140, 141));
            tarjetaIns.add(lblSinIns);
        } else {
            int xIns = 15;
            int yIns = 42;
            int col  = 0;
            for (Insignia ins : insignias) {
                JPanel tarjetaUna =
                        crearTarjetaInsignia(ins);
                tarjetaUna.setBounds(xIns, yIns, 230, 58);
                tarjetaIns.add(tarjetaUna);
                col++;
                if (col % 2 == 0) {
                    xIns = 15;
                    yIns += 65;
                } else {
                    xIns = 260;
                }
            }
        }
        panelContenido.add(tarjetaIns);

        panelContenido.setPreferredSize(
                new java.awt.Dimension(540, y + 20));

        JScrollPane scroll =
                new JScrollPane(panelContenido);
        scroll.setBorder(
                BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar()
                .setUnitIncrement(16);
        panelPrincipal.add(
                scroll, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
    }

    // =========================================================
    // MÉTODOS AUXILIARES DE UI
    // =========================================================
    private JPanel crearTarjeta(int x, int y,
                                 int w, int h) {
        JPanel t = new JPanel(null);
        t.setBounds(x, y, w, h);
        t.setBackground(Color.WHITE);
        t.setBorder(BorderFactory.createLineBorder(
                new Color(220, 220, 220), 1));
        return t;
    }

    private void agregarFilaProgreso(JPanel panel,
                                      String label,
                                      String valor,
                                      int y,
                                      Color colorValor) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(15, y, 280, 28);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setForeground(COLOR_TEXTO);
        panel.add(lbl);

        JLabel val = new JLabel(valor);
        val.setBounds(295, y, 200, 28);
        val.setFont(new Font("Segoe UI", Font.BOLD, 14));
        val.setForeground(colorValor);
        panel.add(val);
    }

    private JPanel crearTarjetaInsignia(Insignia ins) {
        JPanel t = new JPanel(null);
        t.setBackground(ins.isObtenida()
                ? new Color(253, 243, 219)
                : new Color(245, 245, 245));
        t.setBorder(BorderFactory.createLineBorder(
                ins.isObtenida()
                        ? COLOR_ORO
                        : new Color(200, 200, 200), 1));

        JLabel lblEmoji = new JLabel(ins.getEmoji());
        lblEmoji.setBounds(8, 8, 35, 40);
        lblEmoji.setFont(
                new Font("Segoe UI Emoji", Font.PLAIN, 24));
        t.add(lblEmoji);

        JLabel lblNombre = new JLabel(ins.getNombre());
        lblNombre.setBounds(48, 8, 175, 20);
        lblNombre.setFont(new Font("Segoe UI",
                Font.BOLD, 11));
        lblNombre.setForeground(ins.isObtenida()
                ? COLOR_ORO : new Color(150, 150, 150));
        t.add(lblNombre);

        JLabel lblDesc = new JLabel(ins.getDescripcion());
        lblDesc.setBounds(48, 26, 175, 20);
        lblDesc.setFont(
                new Font("Segoe UI", Font.PLAIN, 10));
        lblDesc.setForeground(
                new Color(127, 140, 141));
        t.add(lblDesc);

        JLabel lblEstado = new JLabel(
                ins.isObtenida() ? " Obtenida"
                        : " Bloqueada");
        lblEstado.setBounds(48, 42, 120, 14);
        lblEstado.setFont(
                new Font("Segoe UI", Font.PLAIN, 10));
        lblEstado.setForeground(ins.isObtenida()
                ? COLOR_VERDE
                : new Color(150, 150, 150));
        t.add(lblEstado);

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
