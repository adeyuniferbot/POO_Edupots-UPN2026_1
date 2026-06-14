/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.model;

/**
 *
 * @author camil
 */
public abstract class Usuario {
// =============== 
// ATRIBUTOS PRIVADOS (Encapsulamiento) 
// =================
    public String idUsuario; 
    public String nombre; 
    public String correo; 
    public String contrasena; 
    public String rol;
// ========================================================= 
// CONSTRUCTORES 
// =========================================================
    /** * Constructor vacío necesario para operaciones de lectura desde archivo. 
     */ 
    public Usuario() {
    } 
    /** 
     * Constructor completo. * 
     * @param idUsuario Identificador único del usuario 
     * @param nombre Nombre completo 
     * @param correo Correo electrónico (usado para login) 
     * @param contrasena Contraseña del usuario 
     * @param rol Rol: "ADMINISTRADOR", "DOCENTE" o "ESTUDIANTE" 
     */ 
    public Usuario(String idUsuario, String nombre, String correo, 
            String contrasena, String rol) { 
        this.idUsuario = idUsuario; 
        this.nombre = nombre; 
        this.correo = correo; 
        this.contrasena = contrasena; 
        this.rol = rol; 
    }
// ========================================================= 
// MÉTODOS ABSTRACTOS (Polimorfismo obligatorio) 
// =========================================================

/** * Cada subclase define su propio comportamiento al iniciar sesión. * 
 * @return true si el inicio de sesión es válido 
 */
public abstract boolean iniciarSesion(); 
/** 
* Cada subclase define su propio comportamiento al cerrar sesión. 
*/ 
public abstract void cerrarSesion();
// ========================================================= 
// MÉTODOS CONCRETOS 
// =========================================================
/** * Valida si las credenciales ingresadas coinciden con las del usuario. 
 * 
 * @param correoIngresado Correo ingresado en el login 
 * @param contrasenaIngresada Contraseña ingresada en el login 
 * @return true si las credenciales son correctas 
 */ 
public boolean validarCredenciales(String correoIngresado, 
        String contrasenaIngresada) { 
    return this.correo.equalsIgnoreCase(correoIngresado) 
            && this.contrasena.equals(contrasenaIngresada); 
}
/** 
 * Representación en texto del usuario (útil para depuración). 
 */ 
@Override 
public String toString() { 
    return "Usuario{" 
            + "id='" + idUsuario + '\'' 
            + ", nombre='" + nombre + '\'' 
            + ", correo='" + correo + '\'' 
            + ", rol='" + rol + '\'' 
            + '}'; 
}
// ========================================================= 
// GETTERS Y SETTERS 
// =========================================================
        public String getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(String idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getContrasena() {
            return contrasena;
        }

        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }

        public String getRol() {
            return rol;
        }

        public void setRol(String rol) {
            this.rol = rol;
        }
}
