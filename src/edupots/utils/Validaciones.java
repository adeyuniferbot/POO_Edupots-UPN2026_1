/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.utils;

import java.util.regex.Pattern;
/**
 *
 * @author camil
 */
public class Validaciones {
    public static boolean noEstaVacio(String texto) { 
        return texto != null && !texto.trim().isEmpty();
    }
    
    public static boolean tieneMinCaracteres(String texto,
            int minCaracteres) { 
        if (texto == null) return false; 
        return texto.trim().length() >= minCaracteres; 
    }
    public static boolean noSuperaMaxCaracteres(String texto, 
            int maxCaracteres) { 
        if (texto == null) return true; 
        return texto.trim().length() <= maxCaracteres;
    }
    public static boolean esCorreoValido(String correo) { 
        if (!noEstaVacio(correo)) return false; 
        String patron = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"; 
        return Pattern.matches(patron, correo.trim()); 
    }
    public static boolean estaEnRango(int numero, int min, int max) {
        return numero >= min && numero <= max; 
    }
    public static boolean esNumeroEntero(String texto) { 
        if (!noEstaVacio(texto)) return false; 
        try { 
            Integer.parseInt(texto.trim());
            return true;
        } catch (NumberFormatException e) { 
            return false; 
        } 
    }
    public static boolean esContrasenaValida(String contrasena) {
        return noEstaVacio(contrasena) 
                && tieneMinCaracteres(contrasena, 4);
    }
    public static boolean esRespuestaValida(String respuesta) { 
        if (!noEstaVacio(respuesta)) return false; 
        String r = respuesta.trim().toUpperCase();
        return r.equals("A") || r.equals("B") 
                || r.equals("C") || r.equals("D"); 
    }
    public static String mensajeCampoVacio(String campo) { 
        return "El campo '" + campo + "' es obligatorio."; 
    }
    public static String mensajeCorreoInvalido() { 
        return "El correo no tiene un formato válido "
                + "(ejemplo: usuario@correo.com)."; 
    }
    public static String mensajeContrasenaMuyCorta() { 
        return "La contraseña debe tener al menos 4 caracteres."; 
    }
}
