/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorioagenda;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author jocel
 */
public class Evento {
    private int idEvento;
    private Contacto contacto;
    private LocalDate fecha;
    private LocalTime hora;
    private String descripcion;
    private String ubicacion;

    public Evento() {
    }

    public Evento(int idEvento, Contacto contacto, LocalDate fecha,
                  LocalTime hora, String descripcion, String ubicacion) {
        this.idEvento = idEvento;
        this.contacto = contacto;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
