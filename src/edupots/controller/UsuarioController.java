/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.controller;

import edupots.dao.UsuarioDAO; 
import edupots.model.Administrador; 
import edupots.model.Docente; 
import edupots.model.Estudiante; 
import edupots.model.Usuario; 
import java.util.ArrayList;
/**
 *
 * @author camil
 */
public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    
    public UsuarioController() { 
        this.usuarioDAO = new UsuarioDAO(); 
    }
    public ArrayList<Usuario> listarTodos() { 
        return usuarioDAO.leerTodos(); 
    }
    public ArrayList<Estudiante> listarEstudiantes() { 
        ArrayList<Estudiante> estudiantes = new ArrayList<>(); 
        for (Usuario u : usuarioDAO.leerTodos()) { 
            if (u instanceof Estudiante) { 
                estudiantes.add((Estudiante) u);
            } 
        } 
        return estudiantes; 
    }
    public ArrayList<Docente> listarDocentes() { 
        ArrayList<Docente> docentes = new ArrayList<>();
        for (Usuario u : usuarioDAO.leerTodos()) { 
            if (u instanceof Docente) { 
                docentes.add((Docente) u); 
            } 
        } 
        return docentes;
    }
    public String agregarUsuario(Usuario solicitante,
            Usuario nuevoUsuario) { 
        if (!esAdministrador(solicitante)) { 
            return "❌ Acceso denegado. Solo el administrador puede " 
                    + "gestionar usuarios.";
            } 
        String validacion = validarCamposUsuario(nuevoUsuario); 
        if (!validacion.equals("OK")) {
            return "❌ " + validacion;
        }
        if (usuarioDAO.buscarPorCorreo(nuevoUsuario.getCorreo()) != null) { 
            return "❌ El correo ya está registrado en el sistema.";
        }
        boolean guardado = usuarioDAO.agregar(nuevoUsuario); 
        if (guardado) {
            return "✅ Usuario '" + nuevoUsuario.getNombre()
                    + "' registrado correctamente.";
        } 
        return "❌ Error al guardar el usuario."; 
    }
    public String actualizarUsuario(Usuario solicitante,
            Usuario usuarioActualizado) { 
        if (!esAdministrador(solicitante)) {
            return "❌ Acceso denegado."; 
        } 
        String validacion = validarCamposUsuario(usuarioActualizado);
        if (!validacion.equals("OK")) { 
            return "❌ " + validacion;
        } 
        boolean actualizado = usuarioDAO.actualizar(usuarioActualizado); 
        if (actualizado) {
            return "✅ Usuario actualizado correctamente."; 
        } 
        return "❌ No se encontró el usuario para actualizar."; 
    }
    public String eliminarUsuario(Usuario solicitante, String correo) { 
        if (!esAdministrador(solicitante)) { 
            return "❌ Acceso denegado.";
        }
            if (solicitante.getCorreo().equalsIgnoreCase(correo)) { 
                return "❌ No puedes eliminar tu propio usuario.";
            }
            boolean eliminado = usuarioDAO.eliminar(correo);
            if (eliminado) { 
                return "✅ Usuario eliminado correctamente.";
            } 
            return "❌ No se encontró el usuario con ese correo."; 
        }
        public void actualizarPuntajeEstudiante(String idEstudiante, 
                int puntajeObtenido) {
            Usuario u = usuarioDAO.buscarPorId(idEstudiante); 
            if (u instanceof Estudiante) { 
                Estudiante e = (Estudiante) u;
                e.setPuntajeTotal(e.getPuntajeTotal() + puntajeObtenido); 
                e.setQuizzesCompletados(e.getQuizzesCompletados() + 1);
                usuarioDAO.actualizar(e); 
            } 
        }
        private boolean esAdministrador(Usuario usuario) { 
            return usuario != null 
                    && "ADMINISTRADOR".equals(usuario.getRol());
        }
        private String validarCamposUsuario(Usuario u) { 
            if (u == null) { 
                return "El usuario no puede ser nulo."; 
            } 
            if (u.getNombre() == null || u.getNombre().trim().isEmpty()) { 
                return "El nombre es obligatorio."; 
            } 
            if (u.getCorreo() == null || u.getCorreo().trim().isEmpty()) {
                return "El correo es obligatorio."; 
            } 
            if (!u.getCorreo().contains("@") || !u.getCorreo().contains(".")) { 
                return "El formato del correo no es válido."; 
            } 
            if (u.getContrasena() == null || u.getContrasena().trim().isEmpty()) { 
                return "La contraseña es obligatoria."; 
            } 
            if (u.getContrasena().length() < 4) { 
                return "La contraseña debe tener al menos 4 caracteres.";
            } 
            return "OK"; 
        }
        public String generarNuevoId(String rol) { 
            ArrayList<Usuario> todos = usuarioDAO.leerTodos(); 
            int contador = todos.size() + 1; 
            switch (rol.toUpperCase()) { 
                case "ADMINISTRADOR": return "ADM" + String.format("%03d", contador);
                case "DOCENTE": return "DOC" + String.format("%03d", contador);
                case "ESTUDIANTE": return "EST" + String.format("%03d", contador);
                default: return "USR" + String.format("%03d", contador); 
            } 
        }
}
