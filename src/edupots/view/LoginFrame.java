/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.view;

import edupots.controller.LoginController;
import edupots.controller.LoginController.ResultadoLogin; 
import edupots.model.Usuario; 
import edupots.utils.AudioManager; 
import edupots.utils.ImageManager;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
import javax.swing.JPasswordField; 
import javax.swing.JTextField; 
import javax.swing.SwingConstants;
/**
 *
 * @author camil
 */
public class LoginFrame extends JFrame {
    
  // COMPONENTES
    private JPanel panelFondo; 
    private JPanel panelTarjeta; 
    private JLabel lblLogo; 
    private JLabel lblTitulo;
    private JLabel lblSubtitulo; 
    private JLabel lblCorreo; 
    private JLabel lblContrasena; 
    private JTextField txtCorreo;
    private JPasswordField txtContrasena; 
    private JButton btnAcceder;
    private JLabel lblInstitucion;

    private LoginController loginController;
    
// COLORES DEL TEMA EDUPOTS
    private static final Color COLOR_PRIMARIO = new Color(104, 194, 243); //Difuminado de arriba
    private static final Color COLOR_SECUNDARIO = new Color(52, 152, 219); //Difuminado de abajo
    private static final Color COLOR_ACENTO = new Color(245, 115, 54); //Difuminado del boton Izq
    private static final Color COLOR_FONDO = new Color(31, 164, 237);
    private static final Color COLOR_TARJETA = new Color(255, 255, 255); //Tarjeta donde va correo y contraseña
    private static final Color COLOR_TEXTO = new Color(44, 62, 80); //El texto 
    private static final Color COLOR_TEXTO_SUAVE = new Color(127, 140, 141);
    private static final Color COLOR_EXITO = new Color(39, 174, 96); 
    private static final Color COLOR_ERROR = new Color(192, 57, 43);
    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {
        loginController = new LoginController();
        initComponents(); 
        configurarVentana();
    }
    private void configurarVentana() { 
        setTitle("EduPots — Iniciar Sesión"); 
        setSize(480, 580); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null); 
        setResizable(false); 
    }
     private void initComponents() {
    panelFondo = new JPanel() { 
        @Override 
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); 
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            GradientPaint gp = new GradientPaint( 
                    0, 0, COLOR_PRIMARIO, 
                    0, getHeight(), COLOR_SECUNDARIO);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        } 
    };
    panelFondo.setLayout(null);
    panelFondo.setBounds(0, 0, 480, 580);
    
    lblLogo = new JLabel(); 
    lblLogo.setBounds(190, 30, 100, 100); 
    lblLogo.setHorizontalAlignment(SwingConstants.CENTER); 
    ImageIcon iconoLogo = ImageManager.cargarLogo(100, 100); 
    if (iconoLogo != null) { 
        lblLogo.setIcon(iconoLogo);
    }else {
        // Por si no hay imagen
        lblLogo.setText("🎓"); 
        lblLogo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60)); 
        lblLogo.setForeground(Color.WHITE); 
    }
    panelFondo.add(lblLogo);
    
    lblTitulo = new JLabel("EduPots");
    lblTitulo.setBounds(0, 135, 480, 45);
    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
    lblTitulo.setForeground(Color.WHITE);
    panelFondo.add(lblTitulo);
    
    lblSubtitulo = new JLabel("Sistema de Quizzes Interactivos");
    lblSubtitulo.setBounds(0, 180, 480, 25);
    lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
    lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
    lblSubtitulo.setForeground(new Color(255, 255, 255, 200)); 
    panelFondo.add(lblSubtitulo);
    
    panelTarjeta = new JPanel(); 
    panelTarjeta.setLayout(null);
    panelTarjeta.setBounds(40, 220, 400, 310); 
    panelTarjeta.setBackground(COLOR_TARJETA); 
    panelTarjeta.setBorder(BorderFactory.createEmptyBorder());
    
    panelTarjeta.setBorder(
            BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( 
                    new Color(200, 200, 200), 1), 
                    BorderFactory.createEmptyBorder(20, 30, 20, 30)));
    
    lblCorreo = new JLabel("Correo electrónico"); 
    lblCorreo.setBounds(30, 20, 340, 20);
    lblCorreo.setFont(new Font("Segoe UI", Font.BOLD, 13));
    lblCorreo.setForeground(COLOR_TEXTO); 
    panelTarjeta.add(lblCorreo);
    
    txtCorreo = new JTextField();
    txtCorreo.setBounds(30, 44, 340, 40); 
    txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
    txtCorreo.setBorder(BorderFactory.createCompoundBorder( 
            BorderFactory.createLineBorder( 
                    new Color(189, 195, 199), 1), 
            BorderFactory.createEmptyBorder(5, 10, 5, 10))); 
    txtCorreo.setText("estudiante@edupots.com"); 
    txtCorreo.setForeground(COLOR_TEXTO_SUAVE);
    
    txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override 
        public void focusGained(java.awt.event.FocusEvent e) {
            if (txtCorreo.getForeground() 
                    .equals(COLOR_TEXTO_SUAVE)) {
                txtCorreo.setText(""); 
                txtCorreo.setForeground(COLOR_TEXTO);
            } 
        } 
    });
    panelTarjeta.add(txtCorreo);
    
    lblContrasena = new JLabel("Contraseña"); 
    lblContrasena.setBounds(30, 100, 340, 20); 
    lblContrasena.setFont(new Font("Segoe UI", Font.BOLD, 13));
    lblContrasena.setForeground(COLOR_TEXTO);
    panelTarjeta.add(lblContrasena);
    
    txtContrasena = new JPasswordField();
    txtContrasena.setBounds(30, 124, 340, 40);
    txtContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
    txtContrasena.setBorder(BorderFactory.createCompoundBorder( 
            BorderFactory.createLineBorder(
                    new Color(189, 195, 199), 1), 
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
    txtContrasena.setText("1234");
    panelTarjeta.add(txtContrasena);
    
    btnAcceder = new JButton("ACCEDER") {
        @Override 
        protected void paintComponent(Graphics g) { 
            Graphics2D g2d = (Graphics2D) g; 
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gp = new GradientPaint( 
                    0, 0, COLOR_ACENTO, 
                    getWidth(), 0,
                    new Color(230, 126, 34));
            g2d.setPaint(gp); 
            g2d.fillRoundRect(0, 0, 
                    getWidth(), getHeight(), 10, 10);
            g2d.setColor(Color.WHITE);
            g2d.setFont(getFont());
            java.awt.FontMetrics fm = g2d.getFontMetrics(); 
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent() 
                    - fm.getDescent()) / 2;
            g2d.drawString(getText(), x, y); 
        } 
    };
    btnAcceder.setBounds(30, 185, 340, 48);
    btnAcceder.setFont(new Font("Segoe UI", Font.BOLD, 16));
    btnAcceder.setForeground(Color.WHITE); 
    btnAcceder.setBorderPainted(false);
    btnAcceder.setContentAreaFilled(false); 
    btnAcceder.setFocusPainted(false); 
    btnAcceder.setCursor( 
            new Cursor(Cursor.HAND_CURSOR));
    btnAcceder.addActionListener(e -> accionAcceder());
    panelTarjeta.add(btnAcceder);
    
    txtContrasena.addActionListener(e -> accionAcceder()); 
    txtCorreo.addActionListener(e -> accionAcceder());
    
    panelFondo.add(panelTarjeta);
    
    lblInstitucion = new JLabel( 
            "I.E. 5182 Señor de los Milagros"); 
    lblInstitucion.setBounds(0, 540, 480, 25); 
    lblInstitucion.setHorizontalAlignment(SwingConstants.CENTER);
    lblInstitucion.setFont(new Font("Segoe UI", Font.PLAIN, 11)); 
    lblInstitucion.setForeground( 
            new Color(255, 255, 255, 180)); 
    panelFondo.add(lblInstitucion);
    
    setContentPane(panelFondo);
    }
private void accionAcceder() {
      
    AudioManager.reproducirClick(); 
    
    String correo = txtCorreo.getText().trim(); 
    String contrasena = new String(txtContrasena.getPassword());
    
    ResultadoLogin resultado =
                loginController.iniciarSesion(correo, contrasena);
    
    if (resultado.isExitoso()) { 
        Usuario usuario = resultado.getUsuario();
        
        MenuPrincipalFrame menu = 
                new MenuPrincipalFrame(usuario, loginController);
        menu.setVisible(true);
        
        this.dispose();
        
        } else {
        JOptionPane.showMessageDialog( 
                this, 
                resultado.getMensaje(), 
                "Error de acceso", 
                JOptionPane.ERROR_MESSAGE);
        
        txtContrasena.setText(""); 
        txtContrasena.requestFocus();
    }
}
}
