/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.utils;

import java.awt.Graphics2D; 
import java.awt.Image; 
import java.awt.RenderingHints; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import javax.imageio.ImageIO; 
import javax.swing.ImageIcon;

/**
 *
 * @author camil
 */
public class ImageManager {
    public static final String RUTA_BASE = "src/recursos/imagenes/";
    public static final String IMG_LOGO = RUTA_BASE + "Logo.png";
    public static final String IMG_FONDO_LOGIN = RUTA_BASE + "fondo_login.png"; 
    public static final String IMG_SUMA = RUTA_BASE + "Icono_suma.png";
    public static final String IMG_MULTIPLICACION = RUTA_BASE + "icono_multiplicacion.png";
    public static final String IMG_DIVISION = RUTA_BASE + "icono_division.png";
    public static final String IMG_GEOMETRIA = RUTA_BASE + "icono_geometria.png";
    public static final String IMG_ECUACIONES = RUTA_BASE + "icono_ecuaciones.png"; 
    public static final String IMG_CALCULO = RUTA_BASE + "icono_calculo.png"; 
    public static final String IMG_INSIGNIA_1 = RUTA_BASE + "insignia_primero.png";
    public static final String IMG_INSIGNIA_2 = RUTA_BASE + "insignia_cinco.png";//FALTA
    public static final String IMG_INSIGNIA_3 = RUTA_BASE + "insignia_cien.png"; //FALTA
    public static final String IMG_INSIGNIA_4 = RUTA_BASE + "insignia_perfecto.png"; 
    public static final String IMG_CORRECTO = RUTA_BASE + "correcto.png"; 
    public static final String IMG_INCORRECTO = RUTA_BASE + "incorrecto.png"; 
    
    public static ImageIcon cargarImagen(String ruta) { 
        try { 
            File archivo = new File(ruta); 
            if (!archivo.exists()) {
                System.out.println("Imagen no encontrada: " 
                        + ruta + " (continúa sin imagen)"); 
                return null; 
            } 
            BufferedImage imagen = ImageIO.read(archivo); 
            return new ImageIcon(imagen); 
        } catch (IOException e) { 
            System.out.println("Error al cargar imagen: " + ruta); 
            return null; 
        } 
    }
    public static ImageIcon cargarImagenRedimensionada(String ruta, 
            int ancho, 
            int alto) { 
        try { 
            File archivo = new File(ruta);
            if (!archivo.exists()) {
                System.out.println("Imagen no encontrada: " 
                        + ruta + " (continúa sin imagen)");
                return null; 
            }
            BufferedImage original = ImageIO.read(archivo);
            
            BufferedImage redimensionada = new BufferedImage( 
                    ancho, alto, BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D g2d = redimensionada.createGraphics();
            g2d.setRenderingHint( 
                    RenderingHints.KEY_INTERPOLATION, 
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
            g2d.setRenderingHint( 
                    RenderingHints.KEY_RENDERING, 
                    RenderingHints.VALUE_RENDER_QUALITY); 
            g2d.setRenderingHint( 
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(original, 0, 0, ancho, alto, null);
            g2d.dispose(); 
            return new ImageIcon(redimensionada); 
        } catch (IOException e) { 
            System.out.println("Error al redimensionar imagen: " + ruta); 
            return null; 
        } 
    }
    public static ImageIcon cargarLogo(int ancho, int alto) { 
        return cargarImagenRedimensionada(IMG_LOGO, ancho, alto);
    }
    public static ImageIcon cargarIconoQuiz(String idQuiz, 
            int ancho, int alto) { 
        String ruta; 
        switch (idQuiz) {
            case "Q001": ruta = IMG_SUMA; break; 
            case "Q002": ruta = IMG_MULTIPLICACION; break; 
            case "Q003": ruta = IMG_DIVISION; break; 
            case "Q004": ruta = IMG_GEOMETRIA; break; 
            case "Q005": ruta = IMG_ECUACIONES; break;
            case "Q006": ruta = IMG_CALCULO; break; 
            default: ruta = IMG_SUMA; break; 
        } 
        return cargarImagenRedimensionada(ruta, ancho, alto);
    }
    public static ImageIcon escalarIcono(ImageIcon icono, 
            int ancho, int alto) {
        if (icono == null) return null; 
        Image imagen = icono.getImage()
                .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagen); 
    }
}
