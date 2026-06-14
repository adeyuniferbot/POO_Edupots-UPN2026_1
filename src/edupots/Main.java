/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edupots;

import edupots.dao.ArchivoDAO; 
import edupots.view.LoginFrame;
import javax.swing.SwingUtilities; 
import javax.swing.UIManager;
/**
 *
 * @author camil
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try { 
            UIManager.setLookAndFeel( 
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { 
            System.out.println("Look and Feel por defecto.");
        }
        inicializarArchivos();
        SwingUtilities.invokeLater(() -> { 
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true); 
        }); 
    }
    private static void inicializarArchivos() { 
        ArchivoDAO dao = new ArchivoDAO(); 
        dao.crearArchivoSiNoExiste(ArchivoDAO.RUTA_USUARIOS); 
        dao.crearArchivoSiNoExiste(ArchivoDAO.RUTA_QUIZZES);
        dao.crearArchivoSiNoExiste(ArchivoDAO.RUTA_RESULTADOS);
        dao.crearArchivoSiNoExiste(ArchivoDAO.RUTA_HISTORIAL); 
        dao.crearArchivoSiNoExiste("data/progreso.txt"); 
        System.out.println("=== EduPots iniciado correctamente ==="); 
    }
}
