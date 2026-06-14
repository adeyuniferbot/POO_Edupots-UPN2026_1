/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.utils;

import java.io.File; 
import java.io.IOException; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author camil
 */
public class AudioManager {
    public static final String SONIDO_CLICK = "src/recursos/sonidos/Click.wav"; 
    public static final String SONIDO_CORRECTO = "src/recursos/sonidos/correcto.wav";
    public static final String SONIDO_INCORRECTO = "src/recursos/sonidos/Incorrecto.wav"; 
    public static final String SONIDO_FINAL = "src/recursos/sonidos/Final.wav";
    
    private static Clip clipActual = null;
    
    private static boolean audioHabilitado = true;
    
    public static void reproducir(String rutaSonido) { 
        if (!audioHabilitado) return;
        
        new Thread(() -> { 
            try { 
                File archivoSonido = new File(rutaSonido);
                if (!archivoSonido.exists()) { 
                    System.out.println("Sonido no encontrado: " 
                            + rutaSonido + " (continúa sin sonido)"); 
                    return; 
                }
                AudioInputStream audioStream = 
                        AudioSystem.getAudioInputStream(archivoSonido);
                
                Clip clip = AudioSystem.getClip();
                
                clip.open(audioStream);
                
                clip.setFramePosition(0); 
                clip.start();
                
                clipActual = clip;
                
                } catch (UnsupportedAudioFileException e) { 
                    System.out.println("Formato de audio no soportado: " 
                            + rutaSonido); 
                } catch (LineUnavailableException e) { 
                    System.out.println("Línea de audio no disponible."); 
                } catch (IOException e) { 
                    System.out.println("Error al leer el archivo de audio: " 
                            + rutaSonido); } 
        }).start(); 
    }
    //Aca van los metodos de CONTROL
    public static void detener() { 
        if (clipActual != null && clipActual.isRunning()) { 
            clipActual.stop(); 
            clipActual.close(); 
        } 
    }
       public static void setAudioHabilitado(boolean habilitado) { 
           audioHabilitado = habilitado; 
           if (!habilitado) { 
               detener();
           } 
       }
       public static boolean isAudioHabilitado() { 
           return audioHabilitado; 
       }
       public static void reproducirClick() { 
           reproducir(SONIDO_CLICK); 
       }
       public static void reproducirCorrecto() { 
           reproducir(SONIDO_CORRECTO); 
       }
       public static void reproducirIncorrecto() { 
           reproducir(SONIDO_INCORRECTO); 
       }
       public static void reproducirFinal() { 
           reproducir(SONIDO_FINAL); 
       }
}
