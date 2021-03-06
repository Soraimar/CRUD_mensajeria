package com.dto;

import java.sql.Timestamp;

public class MensajeDTO {

    private int idMensaje;
    private String mensaje;
    private int idUsuario;
    private String autorMensaje;
    private Timestamp fechaMensaje;
    private Timestamp fechaUltimaActualizacion;

    public MensajeDTO() {

    }

    public MensajeDTO(String mensaje, String autorMensaje, Timestamp fechaMensaje, Timestamp fechaUltimaActualizacion) {
        this.mensaje = mensaje;
        this.autorMensaje = autorMensaje;
        this.fechaMensaje = fechaMensaje;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public MensajeDTO(String mensaje, int idUsuario ){
        this.mensaje= mensaje;
        this.idUsuario= idUsuario;

    }

    public MensajeDTO(int idMensaje, int idUsuario ){
        this.idMensaje= idMensaje;
        this.idUsuario= idUsuario;

    }

    public MensajeDTO(String newMensaje, int idMensaje, int idUsuario) {
        this.mensaje= newMensaje;
        this.idMensaje= idMensaje;
        this.idUsuario= idUsuario;
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAutorMensaje() {
        return autorMensaje;
    }

    public void setAutorMensaje(String autorMensaje) {
        this.autorMensaje = autorMensaje;
    }

    public Timestamp getFechaMensaje() {
        return fechaMensaje;
    }

    public void setFechaMensaje(Timestamp fechaMensaje) {
        this.fechaMensaje = fechaMensaje;
    }

    public Timestamp getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(Timestamp fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }
}
