/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.controller;
import edupots.dao.UsuarioDAO; 
import edupots.model.Usuario;
/**
 *
 * @author camil
 */
public class LoginController {
    private UsuarioDAO usuarioDAO; 
    private Usuario usuarioActual;
    
    public LoginController() { 
        this.usuarioDAO = new UsuarioDAO();
        this.usuarioActual = null;
    }
    public ResultadoLogin iniciarSesion(String correo, 
            String contrasena) { 
        if (correo == null || correo.trim().isEmpty()) { 
            return new ResultadoLogin(false, 
                    "El correo no puede estar vacío.", null);
                    }
        if (contrasena == null || contrasena.trim().isEmpty()) { 
            return new ResultadoLogin(false, 
                    "La contraseña no puede estar vacía.", null);
        }
            if (!correo.contains("@") || !correo.contains(".")) { 
                return new ResultadoLogin(false, 
                        "El formato del correo no es válido.", null);
            }
                Usuario usuario = usuarioDAO.buscarPorCorreo(correo.trim()); 
                if (usuario == null) { 
                    return new ResultadoLogin(false, 
                            "El correo no está registrado en el sistema.", null);
                } 
                if (!usuario.validarCredenciales(correo.trim(), contrasena)) { 
                    return new ResultadoLogin(false, 
                            "La contraseña es incorrecta.", null); 
                }
                this.usuarioActual = usuario; 
                usuario.iniciarSesion(); 
                
                return new ResultadoLogin(true, 
                        "Bienvenido, " + usuario.getNombre() + ".",
                        usuario); 
    }
    public void cerrarSesion() { 
        if (usuarioActual != null) { 
            usuarioActual.cerrarSesion(); 
            usuarioActual = null; 
        } 
    }
    public boolean haySesionActiva() { 
        return usuarioActual != null; 
    }
    public Usuario getUsuarioActual() { 
        return usuarioActual; 
    } 
    public String getRolUsuarioActual() {
        if (usuarioActual == null) return "";
        return usuarioActual.getRol(); 
    }
    public static class ResultadoLogin { 
        private boolean exitoso; 
        private String mensaje; 
        private Usuario usuario; 
        
        public ResultadoLogin(boolean exitoso, 
                String mensaje, 
                Usuario usuario) { 
            this.exitoso = exitoso; 
            this.mensaje = mensaje; 
            this.usuario = usuario; 
        } 
        public boolean isExitoso() { return exitoso; } 
        public String getMensaje() { return mensaje; } 
        public Usuario getUsuario() { return usuario; } 
    }
}
    

