package src;

public class Usuario {
    private int idUsuario;
    private String correo;
    private String clave;
    private String nombreCompleto;

    public Usuario(){

    }

    public Usuario(String correo, String clave, String nombreCompleto) {
        this.correo = correo;
        this.clave = clave;
        this.nombreCompleto = nombreCompleto;
    }

    //constructor para borrar usuario
    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    //constructor para traer datos de inicio de sesión
    public Usuario(int idUsuario, String correo, String nombreCompleto) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.nombreCompleto = nombreCompleto;
    }

    //constructor para iniciar sesión
    public Usuario(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }

    //constructor para crear, editar usuario
    public Usuario(int idUsuario, String correo, String clave, String nombreCompleto) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.clave = clave;
        this.nombreCompleto = nombreCompleto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
