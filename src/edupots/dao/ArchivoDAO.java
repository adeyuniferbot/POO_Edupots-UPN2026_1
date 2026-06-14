/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.dao;

import java.io.*; 
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class ArchivoDAO {
    public static final String RUTA_USUARIOS = "data/usuarios.txt"; 
    public static final String RUTA_QUIZZES = "data/quizzes.txt";
    public static final String RUTA_RESULTADOS = "data/resultados.txt"; 
    public static final String RUTA_HISTORIAL = "data/historial.txt"; 
    public static final String RUTA_PROGRESO = "data/progreso.txt";
    
    public ArrayList<String> leerLineas(String rutaArchivo) { 
        ArrayList<String> lineas = new ArrayList<>();
        File archivo = new File(rutaArchivo); 

// Si el archivo no existe, lo crea vacío y devuelve lista vacía 
    if (!archivo.exists()) { 
        crearArchivoSiNoExiste(rutaArchivo);
        return lineas; 
    }
    try (BufferedReader br = new BufferedReader( 
            new FileReader(archivo))) { 
        String linea;
        while ((linea = br.readLine()) != null) { 
            linea = linea.trim();
            if (!linea.isEmpty() && !linea.startsWith("#")) {
                lineas.add(linea);
            } 
        } 
    } catch (IOException e) { 
        System.err.println("Error al leer archivo: " 
                + rutaArchivo + " → " + e.getMessage());
    } 
    return lineas;
    }
    public boolean escribirLineas(String rutaArchivo, 
            ArrayList<String> lineas) { 
        crearArchivoSiNoExiste(rutaArchivo);
        
        try (PrintWriter pw = new PrintWriter( 
                new FileWriter(rutaArchivo, false))) { 
            for (String linea : lineas) { 
                pw.println(linea);
            }
            return true; 
        } catch (IOException e) { 
            System.err.println("Error al escribir archivo: " 
                    + rutaArchivo + " → " + e.getMessage()); 
            return false; 
        } 
    }
    public boolean agregarLinea(String rutaArchivo, String linea) { 
        crearArchivoSiNoExiste(rutaArchivo); 
        
        try (PrintWriter pw = new PrintWriter( 
                new FileWriter(rutaArchivo, true))) { 
            pw.println(linea); 
            return true; 
        } catch (IOException e) { 
            System.err.println("Error al agregar línea: " 
                    + rutaArchivo + " → " + e.getMessage()); 
            return false; 
        } 
    }
    public void crearArchivoSiNoExiste(String rutaArchivo) { 
        File archivo = new File(rutaArchivo);
        try { 
            if (archivo.getParentFile() != null) { 
                archivo.getParentFile().mkdirs();
            }
            if (!archivo.exists()) { 
                archivo.createNewFile();
                System.out.println("Archivo creado: " + rutaArchivo); 
            }
            } catch (IOException e) { 
                    System.err.println("Error al crear archivo: " 
                    + rutaArchivo + " → " + e.getMessage());
                    }
        }
    public boolean archivoTieneDatos(String rutaArchivo) { 
        File archivo = new File(rutaArchivo); 
        return archivo.exists() && archivo.length() > 0; 
    }
    public void limpiarArchivo(String rutaArchivo) { 
        try (PrintWriter pw = new PrintWriter( 
                new FileWriter(rutaArchivo, false))) { 
            pw.print("");
    } catch (IOException e) { 
        System.err.println("Error al limpiar archivo: " 
                + rutaArchivo + " → " + e.getMessage());
        }
    } 
}
