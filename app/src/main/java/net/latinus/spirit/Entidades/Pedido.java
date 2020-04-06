package net.latinus.spirit.Entidades;

import java.io.Serializable;

public class Pedido implements Serializable {


    private Integer contadorPedido;


    private Integer idOficinaPedido;

    private Integer tipoDocumentoPedido;

    private String identificacionClientePedido;

    private String nombreClientePedido;

    private Integer formaPagoPedido;

    private Integer monedaPedido;

    private Integer origendocPedido;



    private Integer vendedorPedido;

    private Integer bodegaPedido;

    private Integer listaPrecioPedido;

    private String fechaPedido;

    public Integer getContadorPedido() {
        return contadorPedido;
    }

    public void setContadorPedido(Integer contadorPedido) {
        this.contadorPedido = contadorPedido;
    }

    private Double latitudPedido;

    private Double longitudPedido;

    private Integer usuarioPedido;

    private String observacionPedido;

    private Integer sriIvaPedido;



    private Double descuentoGlobalPedido;



    private Double subtotalglobalPedido;

    private Double descuentoglobalPedido;

    private Double ivaglobalPedido;

    private Double valoglobalPedido;


    public Pedido(Integer contadorPedido, Integer idOficinaPedido, Integer tipoDocumentoPedido, String identificacionClientePedido, String nombreClientePedido, Integer formaPagoPedido, Integer monedaPedido, Integer origendocPedido, Integer vendedorPedido, Integer bodegaPedido, Integer listaPrecioPedido, String fechaPedido, Double latitudPedido, Double longitudPedido, Integer usuarioPedido, String observacionPedido, Integer sriIvaPedido, Double descuentoGlobalPedido, Double subtotalglobalPedido, Double descuentoglobalPedido, Double ivaglobalPedido, Double valoglobalPedido) {
        this.contadorPedido = contadorPedido;
        this.idOficinaPedido = idOficinaPedido;
        this.tipoDocumentoPedido = tipoDocumentoPedido;
        this.identificacionClientePedido = identificacionClientePedido;
        this.nombreClientePedido = nombreClientePedido;
        this.formaPagoPedido = formaPagoPedido;
        this.monedaPedido = monedaPedido;
        this.origendocPedido = origendocPedido;
        this.vendedorPedido = vendedorPedido;
        this.bodegaPedido = bodegaPedido;
        this.listaPrecioPedido = listaPrecioPedido;
        this.fechaPedido = fechaPedido;
        this.latitudPedido = latitudPedido;
        this.longitudPedido = longitudPedido;
        this.usuarioPedido = usuarioPedido;
        this.observacionPedido = observacionPedido;
        this.sriIvaPedido = sriIvaPedido;
        this.descuentoGlobalPedido = descuentoGlobalPedido;
        this.subtotalglobalPedido = subtotalglobalPedido;
        this.descuentoglobalPedido = descuentoglobalPedido;
        this.ivaglobalPedido = ivaglobalPedido;
        this.valoglobalPedido = valoglobalPedido;
    }

    public Pedido() {
    }


    public Integer getIdOficinaPedido() {
        return idOficinaPedido;
    }

    public void setIdOficinaPedido(Integer idOficinaPedido) {
        this.idOficinaPedido = idOficinaPedido;
    }

    public Integer getTipoDocumentoPedido() {
        return tipoDocumentoPedido;
    }

    public void setTipoDocumentoPedido(Integer tipoDocumentoPedido) {
        this.tipoDocumentoPedido = tipoDocumentoPedido;
    }

    public String getIdentificacionClientePedido() {
        return identificacionClientePedido;
    }

    public void setIdentificacionClientePedido(String identificacionClientePedido) {
        this.identificacionClientePedido = identificacionClientePedido;
    }

    public String getNombreClientePedido() {
        return nombreClientePedido;
    }

    public void setNombreClientePedido(String nombreClientePedido) {
        this.nombreClientePedido = nombreClientePedido;
    }

    public Integer getFormaPagoPedido() {
        return formaPagoPedido;
    }

    public void setFormaPagoPedido(Integer formaPagoPedido) {
        this.formaPagoPedido = formaPagoPedido;
    }

    public Integer getMonedaPedido() {
        return monedaPedido;
    }

    public void setMonedaPedido(Integer monedaPedido) {
        this.monedaPedido = monedaPedido;
    }

    public Integer getOrigendocPedido() {
        return origendocPedido;
    }

    public void setOrigendocPedido(Integer origendocPedido) {
        this.origendocPedido = origendocPedido;
    }

    public Integer getVendedorPedido() {
        return vendedorPedido;
    }

    public void setVendedorPedido(Integer vendedorPedido) {
        this.vendedorPedido = vendedorPedido;
    }

    public Integer getBodegaPedido() {
        return bodegaPedido;
    }

    public void setBodegaPedido(Integer bodegaPedido) {
        this.bodegaPedido = bodegaPedido;
    }

    public Integer getListaPrecioPedido() {
        return listaPrecioPedido;
    }

    public void setListaPrecioPedido(Integer listaPrecioPedido) {
        this.listaPrecioPedido = listaPrecioPedido;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Double getLatitudPedido() {
        return latitudPedido;
    }

    public void setLatitudPedido(Double latitudPedido) {
        this.latitudPedido = latitudPedido;
    }

    public Double getLongitudPedido() {
        return longitudPedido;
    }

    public void setLongitudPedido(Double longitudPedido) {
        this.longitudPedido = longitudPedido;
    }

    public Integer getUsuarioPedido() {
        return usuarioPedido;
    }

    public void setUsuarioPedido(Integer usuarioPedido) {
        this.usuarioPedido = usuarioPedido;
    }

    public String getObservacionPedido() {
        return observacionPedido;
    }

    public void setObservacionPedido(String observacionPedido) {
        this.observacionPedido = observacionPedido;
    }

    public Integer getSriIvaPedido() {
        return sriIvaPedido;
    }

    public void setSriIvaPedido(Integer sriIvaPedido) {
        this.sriIvaPedido = sriIvaPedido;
    }

    public Double getDescuentoGlobalPedido() {
        return descuentoGlobalPedido;
    }

    public void setDescuentoGlobalPedido(Double descuentoGlobalPedido) {
        this.descuentoGlobalPedido = descuentoGlobalPedido;
    }

    public Double getSubtotalglobalPedido() {
        return subtotalglobalPedido;
    }

    public void setSubtotalglobalPedido(Double subtotalglobalPedido) {
        this.subtotalglobalPedido = subtotalglobalPedido;
    }

    public Double getDescuentoglobalPedido() {
        return descuentoglobalPedido;
    }

    public void setDescuentoglobalPedido(Double descuentoglobalPedido) {
        this.descuentoglobalPedido = descuentoglobalPedido;
    }

    public Double getIvaglobalPedido() {
        return ivaglobalPedido;
    }

    public void setIvaglobalPedido(Double ivaglobalPedido) {
        this.ivaglobalPedido = ivaglobalPedido;
    }

    public Double getValoglobalPedido() {
        return valoglobalPedido;
    }

    public void setValoglobalPedido(Double valoglobalPedido) {
        this.valoglobalPedido = valoglobalPedido;
    }
}
