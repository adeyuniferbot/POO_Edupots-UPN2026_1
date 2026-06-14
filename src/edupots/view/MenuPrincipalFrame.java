/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.view;

import edupots.controller.LoginController;
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
import java.awt.GridLayout;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 *
 * @author camil
 */
public class MenuPrincipalFrame extends javax.swing.JFrame {
    

 // =========================================================
    // COMPONENTES
    // =========================================================
    private JPanel  panelPrincipal;
    private JPanel  panelHeader;
    private JPanel  panelBotones;
    private JLabel  lblBienvenida;
    private JLabel  lblNombreUsuario;
    private JLabel  lblRol;
    private JLabel  lblLogo;

    // Botones del menú
    private JButton btnIniciarQuiz;
    private JButton btnHistorial;
    private JButton btnProgreso;
    private JButton btnVideos;
    private JButton btnTutorial;
    private JButton btnCreditos;
    private JButton btnCerrarSesion;
    
    // =========================================================
    // DATOS
    // =========================================================
    private Usuario         usuarioActual;
    private LoginController loginController;

    // COLORES
    private static final Color COLOR_PRIMARIO = new Color(104, 194, 243); //Difuminado de arriba
    private static final Color COLOR_SECUNDARIO = new Color(52, 152, 219); //Difuminado de abajo
    private static final Color COLOR_FONDO = new Color(254, 238, 231); //color del panel donde van los quizzes
    private static final Color COLOR_TARJETA = new Color(255, 255, 255); //Tarjeta donde va correo y contraseña
    private static final Color COLOR_TEXTO      = new Color(44, 62, 80);

    // Colores de botones del menú
    private static final Color BTN_QUIZ     = new Color(41,  128, 185);
    private static final Color BTN_HISTORIAL = new Color(39, 174, 96);
    private static final Color BTN_PROGRESO  = new Color(142, 68, 173);
    private static final Color BTN_VIDEOS    = new Color(231, 76, 60);
    private static final Color BTN_TUTORIAL  = new Color(243, 156, 18);
    private static final Color BTN_CREDITOS  = new Color(127, 140, 141);
    private static final Color BTN_SALIR     = new Color(44,  62, 80);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================
    public MenuPrincipalFrame(Usuario usuario,
                               LoginController loginController) {
        this.usuarioActual   = usuario;
        this.loginController = loginController;
        initComponents();
        configurarVentana();
    }

    // =========================================================
    // CONFIGURACIÓN DE LA VENTANA
    // =========================================================
    private void configurarVentana() {
        setTitle("EduPots — Menú Principal");
        setSize(600, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // =========================================================
    // INICIALIZACIÓN DE COMPONENTES
    // =========================================================
    private void initComponents() {

        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(COLOR_FONDO);

        // ----- PANEL HEADER -----
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
                new java.awt.Dimension(600, 140));

        // Logo en header
        lblLogo = new JLabel();
        lblLogo.setBounds(20, 30, 70, 70);
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon iconoLogo = ImageManager.cargarLogo(55, 55);
        if (iconoLogo != null) {
            lblLogo.setIcon(iconoLogo);
        } else {
            lblLogo.setText("🎓");
            lblLogo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 45));
            lblLogo.setForeground(Color.WHITE);
        }
        panelHeader.add(lblLogo);

        // Bienvenida
        lblBienvenida = new JLabel("¡Bienvenido/a!");
        lblBienvenida.setBounds(105, 25, 400, 30);
        lblBienvenida.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblBienvenida.setForeground(
                new Color(255, 255, 255, 200));
        panelHeader.add(lblBienvenida);

        // Nombre del usuario
        lblNombreUsuario = new JLabel(
                usuarioActual.getNombre());
        lblNombreUsuario.setBounds(105, 52, 400, 38);
        lblNombreUsuario.setFont(
                new Font("Segoe UI", Font.BOLD, 26));
        lblNombreUsuario.setForeground(Color.WHITE);
        panelHeader.add(lblNombreUsuario);

        // Rol del usuario
        lblRol = new JLabel(
                "Rol: " + usuarioActual.getRol());
        lblRol.setBounds(105, 92, 300, 22);
        lblRol.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblRol.setForeground(new Color(255, 255, 255, 180));
        panelHeader.add(lblRol);

        panelPrincipal.add(panelHeader, BorderLayout.NORTH);

        // ----- PANEL DE BOTONES -----
        panelBotones = new JPanel(null);
        panelBotones.setBackground(COLOR_FONDO);

        // Título sección
        JLabel lblMenu = new JLabel("¿Qué deseas hacer hoy?");
        lblMenu.setBounds(0, 18, 600, 30);
        lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
        lblMenu.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblMenu.setForeground(COLOR_TEXTO);
        panelBotones.add(lblMenu);

        // Crear botones del menú
        btnIniciarQuiz    = crearBotonMenu("  Iniciar Quiz",
                "Pon a prueba tus conocimientos", BTN_QUIZ);
        btnHistorial      = crearBotonMenu("  Historial",
                "Revisa tus quizzes anteriores", BTN_HISTORIAL);
        btnProgreso       = crearBotonMenu(" Mi Progreso",
                "Ve tu nivel e insignias", BTN_PROGRESO);
        btnVideos         = crearBotonMenu("  Videos Educativos",
                "Aprende con videos", BTN_VIDEOS);
        btnTutorial       = crearBotonMenu("  Tutorial",
                "Aprende a usar EduPots", BTN_TUTORIAL);
        btnCreditos       = crearBotonMenu("   Créditos",
                "Acerca de este proyecto", BTN_CREDITOS);
        btnCerrarSesion   = crearBotonMenu(" Cerrar Sesión",
                "Salir de la aplicación", BTN_SALIR);

        // Posicionar botones en cuadrícula 2x3 + 1 abajo
        int x1 = 20,  x2 = 305;
        int y1 = 60,  y2 = 170, y3 = 280;
        int w  = 265, h  = 90;

        btnIniciarQuiz.setBounds(x1, y1, w, h);
        btnHistorial.setBounds(x2, y1, w, h);
        btnProgreso.setBounds(x1, y2, w, h);
        btnVideos.setBounds(x2, y2, w, h);
        btnTutorial.setBounds(x1, y3, w, h);
        btnCreditos.setBounds(x2, y3, w, h);
        btnCerrarSesion.setBounds(20, 385, 560, 50);

        panelBotones.add(btnIniciarQuiz);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnProgreso);
        panelBotones.add(btnVideos);
        panelBotones.add(btnTutorial);
        panelBotones.add(btnCreditos);
        panelBotones.add(btnCerrarSesion);

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        setContentPane(panelPrincipal);

        // ----- ASIGNAR ACCIONES -----
        btnIniciarQuiz.addActionListener(e -> abrirListaQuizzes());
        btnHistorial.addActionListener(e -> abrirHistorial());
        btnProgreso.addActionListener(e -> abrirProgreso());
        btnVideos.addActionListener(e -> abrirVideos());
        btnTutorial.addActionListener(e -> abrirTutorial());
        btnCreditos.addActionListener(e -> abrirCreditos());
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
    }
    
    private JButton crearBotonMenu(String titulo,
                                    String descripcion,
                                    Color color) {
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo con gradiente
                GradientPaint gp = new GradientPaint(
                        0, 0, color,
                        0, getHeight(), color.darker());
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0,
                        getWidth(), getHeight(), 15, 15);

                // Título
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font(
                        "Segoe UI", Font.BOLD, 15));
                g2d.drawString(titulo, 15, 35);

                // Descripción
                g2d.setFont(new Font(
                        "Segoe UI", Font.PLAIN, 11));
                g2d.setColor(new Color(
                        255, 255, 255, 190));
                g2d.drawString(descripcion, 15, 58);
            }
        };
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // =========================================================
    // ACCIONES DE NAVEGACIÓN
    // =========================================================

    private void abrirListaQuizzes() {
        AudioManager.reproducirClick();
        // Verificar que sea estudiante para iniciar quiz
        if (!usuarioActual.getRol().equals("ESTUDIANTE")) {
            JOptionPane.showMessageDialog(this,
                    "Solo los estudiantes pueden resolver quizzes.",
                    "Acceso restringido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        ListaQuizzesFrame lista =
                new ListaQuizzesFrame(usuarioActual, this);
        lista.setVisible(true);
        this.setVisible(false);
    }

    private void abrirHistorial() {
        AudioManager.reproducirClick();
        HistorialFrame historial =
                new HistorialFrame(usuarioActual, this);
        historial.setVisible(true);
        this.setVisible(false);
    }

    private void abrirProgreso() {
    AudioManager.reproducirClick();
    ProgresoFrame progreso = new ProgresoFrame(
            usuarioActual, this);
    progreso.setVisible(true);
    this.setVisible(false);
}

    private void abrirVideos() {
        AudioManager.reproducirClick();
        VideosFrame videos =
                new VideosFrame(usuarioActual, this);
        videos.setVisible(true);
        this.setVisible(false);
    }

    private void abrirTutorial() {
        AudioManager.reproducirClick();
        TutorialFrame tutorial =
                new TutorialFrame(this);
        tutorial.setVisible(true);
        this.setVisible(false);
    }

    private void abrirCreditos() {
        AudioManager.reproducirClick();
        CreditosFrame creditos =
                new CreditosFrame(this);
        creditos.setVisible(true);
        this.setVisible(false);
    }

    private void cerrarSesion() {
        AudioManager.reproducirClick();
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro que deseas cerrar sesión?",
                "Cerrar Sesión",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {
            loginController.cerrarSesion();
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
            this.dispose();
        }
    }

    // =========================================================
    // GETTER
    // =========================================================
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}
