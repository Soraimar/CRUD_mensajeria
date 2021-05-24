package src;

import java.sql.Timestamp;

public class Mensaje {

    private int idMensaje;
    private String mensaje;
    private String autorMensaje;
    private Timestamp fechaMensaje;
    private Timestamp fechaUltimaActualizacion;

    public Mensaje(String mensaje, String autorMensaje, Timestamp fechaMensaje, Timestamp fechaUltimaActualizacion) {
        this.mensaje = mensaje;
        this.autorMensaje = autorMensaje;
        this.fechaMensaje = fechaMensaje;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public Mensaje(String mensaje, String autorMensaje ){
        this.mensaje= mensaje;
        this.autorMensaje= autorMensaje;

    }


    public Mensaje() {

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
