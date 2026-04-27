-UPN;
import javax.swing.*;
import java.awt.event.*;

public class EduPots {
    public static void main(String[] args) {

        JFrame ventana = new JFrame("EduPots - Acceso");
        ventana.setSize(350, 220);
        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titulo = new JLabel("Iniciar sesión");
        titulo.setBounds(130, 10, 150, 20);
        ventana.add(titulo);

        JLabel usuarioLbl = new JLabel("Usuario:");
        usuarioLbl.setBounds(30, 50, 100, 20);
        ventana.add(usuarioLbl);

        JTextField usuarioTxt = new JTextField();
        usuarioTxt.setBounds(120, 50, 150, 20);
        ventana.add(usuarioTxt);

        JLabel passLbl = new JLabel("Contraseña:");
        passLbl.setBounds(30, 80, 100, 20);
        ventana.add(passLbl);

        JPasswordField passTxt = new JPasswordField();
        passTxt.setBounds(120, 80, 150, 20);
        ventana.add(passTxt);

        JButton loginBtn = new JButton("Ingresar");
        loginBtn.setBounds(120, 120, 100, 30);
        ventana.add(loginBtn);

        JLabel resultado = new JLabel("");
        resultado.setBounds(80, 160, 200, 20);
        ventana.add(resultado);

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String usuario = usuarioTxt.getText();
                String clave = new String(passTxt.getPassword());

                if (usuario.equals("admin") && clave.equals("1234")) {
                    resultado.setText("Acceso permitido");
                } else {
                    resultado.setText("Usuario o contraseña incorrectos");
                }
            }
        });

        ventana.setVisible(true);
    }
}

