package net.latinus.spirit.Entidades;

import java.io.Serializable;

public class RegistroCliente implements Serializable {


    private Integer tipoidRegCliente;


    private Integer idborrar;

    public Integer getIdborrar() {
        return idborrar;
    }

    public void setIdborrar(Integer idborrar) {
        this.idborrar = idborrar;
    }

    private String identificacionRegCliente;

    private String nombreRegCliente;

    private String razonRegCliente;

    private Integer corporacionidRegCliente;

    private Integer tipoclienteidRegCliente;

    private Integer tipoproveedoridRegCliente;

    private String codigoRegCliente;

    private Integer ciudadidRegCliente;

    private String direccionRegCliente;

    private Double latitudRegCliente;

    private Double longitudRegCliente;


    private String emailRegCliente;

    private String descripcionRegCliente;

    private Integer vendedoridRegCliente;


    public RegistroCliente(Integer tipoidRegCliente, String identificacionRegCliente, String nombreRegCliente, String razonRegCliente, Integer corporacionidRegCliente, Integer tipoclienteidRegCliente, Integer tipoproveedoridRegCliente, String codigoRegCliente, Integer ciudadidRegCliente, String direccionRegCliente, Double latitudRegCliente, Double longitudRegCliente, String emailRegCliente, String descripcionRegCliente, Integer vendedoridRegCliente) {
        this.tipoidRegCliente = tipoidRegCliente;
        this.identificacionRegCliente = identificacionRegCliente;
        this.nombreRegCliente = nombreRegCliente;
        this.razonRegCliente = razonRegCliente;
        this.corporacionidRegCliente = corporacionidRegCliente;
        this.tipoclienteidRegCliente = tipoclienteidRegCliente;
        this.tipoproveedoridRegCliente = tipoproveedoridRegCliente;
        this.codigoRegCliente = codigoRegCliente;
        this.ciudadidRegCliente = ciudadidRegCliente;
        this.direccionRegCliente = direccionRegCliente;
        this.latitudRegCliente = latitudRegCliente;
        this.longitudRegCliente = longitudRegCliente;
        this.emailRegCliente = emailRegCliente;
        this.descripcionRegCliente = descripcionRegCliente;
        this.vendedoridRegCliente = vendedoridRegCliente;
    }

    public RegistroCliente(){

    }

    public Integer getTipoidRegCliente() {
        return tipoidRegCliente;
    }

    public void setTipoidRegCliente(Integer tipoidRegCliente) {
        this.tipoidRegCliente = tipoidRegCliente;
    }

    public String getIdentificacionRegCliente() {
        return identificacionRegCliente;
    }

    public void setIdentificacionRegCliente(String identificacionRegCliente) {
        this.identificacionRegCliente = identificacionRegCliente;
    }

    public String getNombreRegCliente() {
        return nombreRegCliente;
    }

    public void setNombreRegCliente(String nombreRegCliente) {
        this.nombreRegCliente = nombreRegCliente;
    }

    public String getRazonRegCliente() {
        return razonRegCliente;
    }

    public void setRazonRegCliente(String razonRegCliente) {
        this.razonRegCliente = razonRegCliente;
    }

    public Integer getCorporacionidRegCliente() {
        return corporacionidRegCliente;
    }

    public void setCorporacionidRegCliente(Integer corporacionidRegCliente) {
        this.corporacionidRegCliente = corporacionidRegCliente;
    }

    public Integer getTipoclienteidRegCliente() {
        return tipoclienteidRegCliente;
    }

    public void setTipoclienteidRegCliente(Integer tipoclienteidRegCliente) {
        this.tipoclienteidRegCliente = tipoclienteidRegCliente;
    }

    public Integer getTipoproveedoridRegCliente() {
        return tipoproveedoridRegCliente;
    }

    public void setTipoproveedoridRegCliente(Integer tipoproveedoridRegCliente) {
        this.tipoproveedoridRegCliente = tipoproveedoridRegCliente;
    }

    public String getCodigoRegCliente() {
        return codigoRegCliente;
    }

    public void setCodigoRegCliente(String codigoRegCliente) {
        this.codigoRegCliente = codigoRegCliente;
    }

    public Integer getCiudadidRegCliente() {
        return ciudadidRegCliente;
    }

    public void setCiudadidRegCliente(Integer ciudadidRegCliente) {
        this.ciudadidRegCliente = ciudadidRegCliente;
    }

    public String getDireccionRegCliente() {
        return direccionRegCliente;
    }

    public void setDireccionRegCliente(String direccionRegCliente) {
        this.direccionRegCliente = direccionRegCliente;
    }

    public Double getLatitudRegCliente() {
        return latitudRegCliente;
    }

    public void setLatitudRegCliente(Double latitudRegCliente) {
        this.latitudRegCliente = latitudRegCliente;
    }

    public Double getLongitudRegCliente() {
        return longitudRegCliente;
    }

    public void setLongitudRegCliente(Double longitudRegCliente) {
        this.longitudRegCliente = longitudRegCliente;
    }

    public String getEmailRegCliente() {
        return emailRegCliente;
    }

    public void setEmailRegCliente(String emailRegCliente) {
        this.emailRegCliente = emailRegCliente;
    }

    public String getDescripcionRegCliente() {
        return descripcionRegCliente;
    }

    public void setDescripcionRegCliente(String descripcionRegCliente) {
        this.descripcionRegCliente = descripcionRegCliente;
    }

    public Integer getVendedoridRegCliente() {
        return vendedoridRegCliente;
    }

    public void setVendedoridRegCliente(Integer vendedoridRegCliente) {
        this.vendedoridRegCliente = vendedoridRegCliente;
    }
}



