package com;

public class UsuarioDTO {
    private int idUsuario;
    private String userName;
    private String correo;
    private String password;
    private String nombreCompleto;
    private String mensaje;
    private String token;
    private boolean login;

    public UsuarioDTO(){

    }

    public UsuarioDTO(String correo, String password, String nombreCompleto) {
        this.correo = correo;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
    }

    //constructor para borrar usuario
    public UsuarioDTO(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    //constructor para traer datos de inicio de sesión
    public UsuarioDTO(int idUsuario, String correo, String nombreCompleto) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.nombreCompleto = nombreCompleto;
    }

    //constructor para iniciar sesión
    public UsuarioDTO(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    //constructor para crear, editar usuario
    public UsuarioDTO(int idUsuario, String correo, String password, String nombreCompleto) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
