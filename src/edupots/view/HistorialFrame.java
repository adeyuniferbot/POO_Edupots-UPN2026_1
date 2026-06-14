/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.view;

import edupots.controller.ProgresoController;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author camil
 */
   public class HistorialFrame extends JFrame {

    // =========================================================
    // COMPONENTES
    // =========================================================
    private JPanel      panelPrincipal;
    private JPanel      panelHeader;
    private JPanel      panelStats;
    private JTable      tablaHistorial;
    private JScrollPane scrollTabla;
    private JButton     btnVolver;

    // =========================================================
    // DATOS
    // =========================================================
    private Usuario             usuarioActual;
    private JFrame              framePadre;
    private ProgresoController  progresoController;

    // =========================================================
    // COLORES
    // =========================================================
    private static final Color COLOR_PRIMARIO   = new Color(39,  174, 96);
    private static final Color COLOR_SECUNDARIO = new Color(46,  204, 113);
    private static final Color COLOR_FONDO      = new Color(236, 240, 241);
    private static final Color COLOR_TEXTO      = new Color(44,  62,  80);
    private static final Color COLOR_TABLA_CAB  = new Color(39,  174, 96);
    private static final Color COLOR_FILA_PAR   = new Color(232, 245, 233);
    private static final Color COLOR_FILA_IMPAR = Color.WHITE;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================
    public HistorialFrame(Usuario usuario, JFrame padre) {
        this.usuarioActual     = usuario;
        this.framePadre        = padre;
        this.progresoController = new ProgresoController();
        initComponents();
        configurarVentana();
        cargarDatos();
    }

    private void configurarVentana() {
        setTitle("EduPots — Historial de Quizzes");
        setSize(700, 650);
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

    // ===== PANEL NORTE (header + stats juntos) =====
    JPanel panelNorte = new JPanel(null);
    panelNorte.setBackground(COLOR_FONDO);
    panelNorte.setPreferredSize(
            new java.awt.Dimension(700, 225));

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
    panelHeader.setBounds(0, 0, 700, 100);

    JLabel lblTitulo = new JLabel(
            "  Historial de Quizzes");
    lblTitulo.setBounds(20, 18, 500, 38);
    lblTitulo.setFont(
            new Font("Segoe UI", Font.BOLD, 26));
    lblTitulo.setForeground(Color.WHITE);
    panelHeader.add(lblTitulo);

    JLabel lblSub = new JLabel(
            "Todos tus quizzes completados");
    lblSub.setBounds(20, 57, 400, 22);
    lblSub.setFont(
            new Font("Segoe UI", Font.PLAIN, 14));
    lblSub.setForeground(
            new Color(255, 255, 255, 200));
    panelHeader.add(lblSub);

    btnVolver = new JButton("← Volver");
    btnVolver.setBounds(580, 32, 100, 35);
    btnVolver.setFont(
            new Font("Segoe UI", Font.BOLD, 12));
    btnVolver.setForeground(Color.WHITE);
    btnVolver.setBackground(COLOR_PRIMARIO);
    btnVolver.setBorderPainted(false);
    btnVolver.setFocusPainted(false);
    btnVolver.setCursor(
            new Cursor(Cursor.HAND_CURSOR));
    btnVolver.addActionListener(e -> volverAlMenu());
    panelHeader.add(btnVolver);

    panelNorte.add(panelHeader);

    // ----- PANEL ESTADÍSTICAS -----
    panelStats = new JPanel(null);
    panelStats.setBounds(0, 105, 700, 115);
    panelStats.setBackground(COLOR_FONDO);

    JLabel lblStatsTit = new JLabel("Resumen General");
    lblStatsTit.setBounds(20, 6, 300, 24);
    lblStatsTit.setFont(
            new Font("Segoe UI", Font.BOLD, 15));
    lblStatsTit.setForeground(COLOR_TEXTO);
    panelStats.add(lblStatsTit);

    String[] estadisticas =
            progresoController.obtenerEstadisticas(
                    usuarioActual.getIdUsuario());

    String[][] datosStats = {
        {"📝", "Quizzes",   estadisticas[0]},
        {"📊", "Promedio",  estadisticas[1] + " pts"},
        {"⬆️", "Mayor",     estadisticas[2] + " pts"},
        {"⬇️", "Menor",     estadisticas[3] + " pts"}
    };

    Color[] coloresStats = {
        new Color(41,  128, 185),
        new Color(39,  174, 96),
        new Color(243, 156, 18),
        new Color(192, 57,  43)
    };

    int xStat = 15;
    for (int i = 0; i < datosStats.length; i++) {
        JPanel tarjeta = crearTarjetaStat(
                datosStats[i][0],
                datosStats[i][1],
                datosStats[i][2],
                coloresStats[i]);
        tarjeta.setBounds(xStat, 34, 158, 68);
        panelStats.add(tarjeta);
        xStat += 168;
    }

    panelNorte.add(panelStats);
    panelPrincipal.add(panelNorte, BorderLayout.NORTH);

    // ===== TABLA DE HISTORIAL =====
    String[] columnas = {
        "Quiz", "Fecha", "Puntaje",
        " Correctas", " Incorrectas", "Nivel"
    };

    DefaultTableModel modelo =
            new DefaultTableModel(columnas, 0) {
        @Override
        public boolean isCellEditable(
                int row, int col) {
            return false;
        }
    };

    tablaHistorial = new JTable(modelo) {
        @Override
        public java.awt.Component prepareRenderer(
                javax.swing.table.TableCellRenderer r,
                int row, int col) {
            java.awt.Component c =
                    super.prepareRenderer(r, row, col);
            if (!isRowSelected(row)) {
                c.setBackground(row % 2 == 0
                        ? COLOR_FILA_PAR
                        : COLOR_FILA_IMPAR);
            }
            return c;
        }
    };

    tablaHistorial.getTableHeader()
            .setBackground(COLOR_TABLA_CAB);
    tablaHistorial.getTableHeader()
            .setForeground(Color.BLACK);
    tablaHistorial.getTableHeader()
            .setFont(new Font("Segoe UI", Font.BOLD, 13));
    tablaHistorial.getTableHeader()
            .setReorderingAllowed(false);
    tablaHistorial.setFont(
            new Font("Segoe UI", Font.PLAIN, 13));
    tablaHistorial.setRowHeight(32);
    tablaHistorial.setShowGrid(false);
    tablaHistorial.setIntercellSpacing(
            new java.awt.Dimension(0, 0));
    tablaHistorial.setSelectionBackground(
            new Color(214, 234, 248));

    DefaultTableCellRenderer centro =
            new DefaultTableCellRenderer();
    centro.setHorizontalAlignment(SwingConstants.CENTER);
    for (int i = 2; i < columnas.length; i++) {
        tablaHistorial.getColumnModel()
                .getColumn(i).setCellRenderer(centro);
    }

    tablaHistorial.getColumnModel()
            .getColumn(0).setPreferredWidth(180);
    tablaHistorial.getColumnModel()
            .getColumn(1).setPreferredWidth(130);
    tablaHistorial.getColumnModel()
            .getColumn(2).setPreferredWidth(80);
    tablaHistorial.getColumnModel()
            .getColumn(3).setPreferredWidth(90);
    tablaHistorial.getColumnModel()
            .getColumn(4).setPreferredWidth(100);
    tablaHistorial.getColumnModel()
            .getColumn(5).setPreferredWidth(120);

    scrollTabla = new JScrollPane(tablaHistorial);
    scrollTabla.setBorder(
            BorderFactory.createEmptyBorder(
                    5, 15, 15, 15));
    scrollTabla.getVerticalScrollBar()
            .setUnitIncrement(16);

    panelPrincipal.add(
            scrollTabla, BorderLayout.CENTER);
    setContentPane(panelPrincipal);
}

    // =========================================================
    // CARGAR DATOS EN LA TABLA
    // =========================================================
    private void cargarDatos() {
        DefaultTableModel modelo =
                (DefaultTableModel) tablaHistorial.getModel();
        modelo.setRowCount(0); // Limpiar tabla

        Object[][] datos =
                progresoController.obtenerDatosParaTabla(
                        usuarioActual.getIdUsuario());

        if (datos.length == 0) {
            // Mostrar fila vacía informativa
            modelo.addRow(new Object[]{
                "Sin quizzes completados",
                "—", "—", "—", "—", "—"
            });
        } else {
            for (Object[] fila : datos) {
                modelo.addRow(fila);
            }
        }
    }

    // =========================================================
    // TARJETA DE ESTADÍSTICA
    // =========================================================
    private JPanel crearTarjetaStat(String emoji,
                                     String titulo,
                                     String valor,
                                     Color color) {
        JPanel t = new JPanel(null);
        t.setBackground(Color.WHITE);
        t.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(
                        0, 0, 0, 0)));

        JLabel lblEmoji = new JLabel(emoji);
        lblEmoji.setBounds(8, 8, 30, 28);
        lblEmoji.setFont(
                new Font("Segoe UI Emoji", Font.PLAIN, 20));
        t.add(lblEmoji);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setBounds(42, 8, 110, 20);
        lblTitulo.setFont(
                new Font("Segoe UI", Font.PLAIN, 11));
        lblTitulo.setForeground(
                new Color(127, 140, 141));
        t.add(lblTitulo);

        JLabel lblValor = new JLabel(valor);
        lblValor.setBounds(8, 34, 145, 26);
        lblValor.setFont(
                new Font("Segoe UI", Font.BOLD, 17));
        lblValor.setForeground(color);
        t.add(lblValor);

        return t;
    }

    // =========================================================
    // ACCIONES
    // =========================================================
    private void volverAlMenu() {
        AudioManager.reproducirClick();
        framePadre.setVisible(true);
        this.dispose();
    }
}
