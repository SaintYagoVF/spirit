package net.latinus.spirit.Entidades;

import java.io.Serializable;

public class Cliente implements Serializable {


    private Integer idCliente;

    private String identificacionCliente;

    private String nombreCliente;

    private String razonCliente;

    private String fechaCliente;

    private Double latitudCliente;

    private Double longitudCliente;




    private String direccionCliente;

    private String telefonoCliente;

    private String emailCliente;

    private String descripcionCliente;

    public Cliente(){

    }

    public Cliente(Integer idCliente, String identificacionCliente, String nombreCliente, String razonCliente, String fechaCliente, Double latitudCliente, Double longitudCliente, String direccionCliente, String telefonoCliente, String emailCliente, String descripcionCliente) {
        this.idCliente = idCliente;
        this.identificacionCliente = identificacionCliente;
        this.nombreCliente = nombreCliente;
        this.razonCliente = razonCliente;
        this.fechaCliente = fechaCliente;
        this.latitudCliente = latitudCliente;
        this.longitudCliente = longitudCliente;
        this.direccionCliente = direccionCliente;
        this.telefonoCliente = telefonoCliente;
        this.emailCliente = emailCliente;
        this.descripcionCliente = descripcionCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getDescripcionCliente() {
        return descripcionCliente;
    }

    public void setDescripcionCliente(String descripcionCliente) {
        this.descripcionCliente = descripcionCliente;
    }




    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdentificacionCliente() {
        return identificacionCliente;
    }

    public void setIdentificacionCliente(String identificacionCliente) {
        this.identificacionCliente = identificacionCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getRazonCliente() {
        return razonCliente;
    }

    public void setRazonCliente(String razonCliente) {
        this.razonCliente = razonCliente;
    }

    public String getFechaCliente() {
        return fechaCliente;
    }

    public void setFechaCliente(String fechaCliente) {
        this.fechaCliente = fechaCliente;
    }

    public Double getLatitudCliente() {
        return latitudCliente;
    }

    public void setLatitudCliente(Double latitudCliente) {
        this.latitudCliente = latitudCliente;
    }

    public Double getLongitudCliente() {
        return longitudCliente;
    }

    public void setLongitudCliente(Double longitudCliente) {
        this.longitudCliente = longitudCliente;
    }
}
