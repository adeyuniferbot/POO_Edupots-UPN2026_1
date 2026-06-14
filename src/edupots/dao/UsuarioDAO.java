/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.dao;

import edupots.model.Administrador; 
import edupots.model.Docente; 
import edupots.model.Estudiante; 
import edupots.model.Usuario; 
import java.util.ArrayList;
/**
 *
 * @author camil
 */
public class UsuarioDAO extends ArchivoDAO{
    public ArrayList<Usuario> leerTodos() { 
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<String> lineas = leerLineas(RUTA_USUARIOS); 
        
        for (String linea : lineas) { 
            Usuario u = deserializarUsuario(linea);
            if (u != null) { 
                usuarios.add(u);
            } 
        } 
        if (usuarios.isEmpty()) { 
            usuarios = crearUsuariosPorDefecto();
            guardarTodos(usuarios); 
        }
        return usuarios;
    }
    public Usuario buscarPorCorreo(String correo) { 
        ArrayList<Usuario> usuarios = leerTodos();
    for (Usuario u : usuarios) { 
        if (u.getCorreo().equalsIgnoreCase(correo)) { 
            return u;
    } 
  }
    return null; 
    }
    public Usuario buscarPorId(String idUsuario) { 
        ArrayList<Usuario> usuarios = leerTodos();
        for (Usuario u : usuarios) { 
            if (u.getIdUsuario().equals(idUsuario)) { 
                return u;
        } 
    }
        return null; 
    }
    public boolean guardarTodos(ArrayList<Usuario> usuarios) {
        ArrayList<String> lineas = new ArrayList<>(); 
        for (Usuario u : usuarios) { 
            lineas.add(serializarUsuario(u)); 
        } 
        return escribirLineas(RUTA_USUARIOS, lineas); 
    }
    public boolean agregar(Usuario nuevoUsuario) { 
        if (buscarPorCorreo(nuevoUsuario.getCorreo()) != null) { 
            System.out.println("Error: El correo ya está registrado.");
            return false;
        }
        return agregarLinea(RUTA_USUARIOS, 
                serializarUsuario(nuevoUsuario)); 
    }
    public boolean actualizar(Usuario usuarioActualizado) { 
        ArrayList<Usuario> usuarios = leerTodos();
        boolean encontrado = false;
        
        for (int i = 0; i < usuarios.size(); i++) { 
            if (usuarios.get(i).getIdUsuario() 
                    .equals(usuarioActualizado.getIdUsuario())) { 
                usuarios.set(i, usuarioActualizado);
                encontrado = true; 
                break; 
            } 
        }
        if (encontrado) {
            return guardarTodos(usuarios); 
        }
        return false; 
    }
    public boolean eliminar(String correo) {
    ArrayList<Usuario> usuarios = leerTodos();

    boolean eliminado = usuarios.removeIf(
            u -> u.getCorreo().equalsIgnoreCase(correo));

    if (eliminado) {
        return guardarTodos(usuarios);
    }

    return false;
}
    private String serializarUsuario(Usuario u) { 
        StringBuilder sb = new StringBuilder();
        sb.append(u.getIdUsuario()).append("|") 
                .append(u.getNombre()).append("|") 
                .append(u.getCorreo()).append("|") 
                .append(u.getContrasena()).append("|")
                .append(u.getRol()).append("|");
        
        if (u instanceof Estudiante) { 
            Estudiante e = (Estudiante) u;
            sb.append(e.getIdEstudiante()).append("|") 
                    .append(e.getGrado()).append("|") 
                    .append(e.getPuntajeTotal()).append("|") 
                    .append(e.getQuizzesCompletados());
            
            } else if (u instanceof Docente) { 
                Docente d = (Docente) u;
                sb.append(d.getIdDocente()).append("|") 
                        .append(d.getEspecialidad());
                
                } else if (u instanceof Administrador) {
                    Administrador a = (Administrador) u;
                    sb.append(a.getIdAdministrador());
                }
        return sb.toString(); 
    }
    private Usuario deserializarUsuario(String linea) { 
        String[] p = linea.split("\\|");
        if (p.length < 5) return null; 
        String idUsuario = p[0];
        String nombre = p[1]; 
        String correo = p[2];
        String contrasena = p[3];
        String rol = p[4]; 
        switch (rol.toUpperCase()) {
            case "ESTUDIANTE": if (p.length >= 9) { Estudiante e = new Estudiante( idUsuario, nombre, correo, contrasena, p[5], p[6]);
            e.setPuntajeTotal(Integer.parseInt(p[7]));
            e.setQuizzesCompletados(Integer.parseInt(p[8])); 
            return e; 
            }
            
            return new Estudiante(idUsuario, nombre, 
                    correo, contrasena, "EST000", "Sin grado");
            
            case "DOCENTE": 
                if (p.length >= 7) { 
                    return new Docente(idUsuario, nombre, correo, 
                            contrasena, p[5], p[6]);
                }
                    return new Docente(idUsuario, nombre, correo,
                            contrasena, "DOC000", "Sin especialidad"); 
            case "ADMINISTRADOR": 
                if (p.length >= 6) { 
                    return new Administrador(idUsuario, nombre, 
                            correo, contrasena, p[5]);
                } 
                return new Administrador(idUsuario, nombre, 
                        correo, contrasena, "ADM000");
            default: 
                return null; 
        } 
    }
    private ArrayList<Usuario> crearUsuariosPorDefecto() { 
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        // Administrador 
        usuarios.add(new Administrador( 
                "U001", 
                "Administrador EduPots",
                "admin@edupots.com",
                "1234", 
                "ADM001"));
        
        // Docente
        usuarios.add(new Docente( 
                "U002", 
                "Docente EduPots", 
                "docente@edupots.com", 
                "1234", 
                "DOC001", 
                "Matemáticas"));
        
        // Estudiante 
        usuarios.add(new Estudiante( 
                "U003",
                "Estudiante EduPots", 
                "estudiante@edupots.com",
                "1234", 
                "EST001",
                "3ro Primaria"));
        
        System.out.println("Usuarios por defecto creados."); 
        return usuarios; 
    }
}






