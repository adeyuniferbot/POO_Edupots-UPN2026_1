/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.model;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Administrador extends Usuario {
// ========================================================= 
// ATRIBUTOS PROPIOS 
// =========================================================
    private String idAdministrador;
    private ArrayList<Usuario> usuariosGestionados;
// ========================================================= 
// CONSTRUCTORES 
// =========================================================
    public Administrador() { 
        super(); 
        this.usuariosGestionados = new ArrayList<>(); 
    }
    public Administrador(String idUsuario, String nombre, String correo, 
            String contrasena, String idAdministrador) { 
        super(idUsuario, nombre, correo, contrasena, "ADMINISTRADOR"); 
        this.idAdministrador = idAdministrador; 
        this.usuariosGestionados = new ArrayList<>(); 
    }

//===============================================
// IMPLEMENTACIÓN DE MÉTODOS ABSTRACTOS 
// =========================================================
    @Override 
    public boolean iniciarSesion() { 
        System.out.println("Administrador " + getNombre() + " ha iniciado sesión."); 
        return true; 
    }
    @Override 
    public void cerrarSesion() { 
        System.out.println("Administrador " + getNombre() + " ha cerrado sesión.");
    }
// ========================================================= 
// MÉTODOS PROPIOS DEL ADMINISTRADOR 
// =========================================================
    public void gestionarUsuarios(Usuario usuario) { 
        if (usuario != null) { 
// Verificar que el correo no esté duplicado 
    boolean existe = false; 
    for (Usuario u : usuariosGestionados) { 
        if (u.getCorreo().equalsIgnoreCase(usuario.getCorreo())) { 
            existe = true;
            break;
        }
    }
    if (!existe) { 
        usuariosGestionados.add(usuario); 
        System.out.println("Usuario '" + usuario.getNombre() 
                + "' agregado al sistema."); 
    } else { 
        System.out.println("Error: El correo ya está registrado.");
    } 
        } 
    }
    public boolean eliminarUsuario(String correo) { 
        for (int i = 0; i < usuariosGestionados.size(); i++) { 
            if (usuariosGestionados.get(i).getCorreo() 
                    .equalsIgnoreCase(correo)) { 
                usuariosGestionados.remove(i); 
                return true; 
            } 
        } 
        return false; 
    }
    @Override 
    public String toString() { 
        return "Administrador{" 
                + "id='" + getIdUsuario() + '\'' 
                + ", nombre='" + getNombre() + '\'' 
                + '}'; 
    }
// ========================================================= 
// GETTERS Y SETTERS 
// =========================================================
    public String getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(String idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public ArrayList getUsuariosGestionados() {
        return usuariosGestionados;
    }

    public void setUsuariosGestionados(ArrayList usuariosGestionados) {
        this.usuariosGestionados = usuariosGestionados;
    }
        }
