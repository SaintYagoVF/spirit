package net.latinus.spirit.Entidades;

import java.io.Serializable;

public class ValoresPedido implements Serializable {


    private String nombreValorPedido;

    private Integer loteValorPedido;

    private Integer cantidadValorPedido;

    private Double precioValorPedido;

    private Double totalValorPedido;


    public ValoresPedido(String nombreValorPedido, Integer loteValorPedido, Integer cantidadValorPedido, Double precioValorPedido, Double totalValorPedido) {
        this.nombreValorPedido = nombreValorPedido;
        this.loteValorPedido = loteValorPedido;
        this.cantidadValorPedido = cantidadValorPedido;
        this.precioValorPedido = precioValorPedido;
        this.totalValorPedido = totalValorPedido;
    }

    public ValoresPedido() {
    }

    public String getNombreValorPedido() {
        return nombreValorPedido;
    }

    public void setNombreValorPedido(String nombreValorPedido) {
        this.nombreValorPedido = nombreValorPedido;
    }

    public Integer getLoteValorPedido() {
        return loteValorPedido;
    }

    public void setLoteValorPedido(Integer loteValorPedido) {
        this.loteValorPedido = loteValorPedido;
    }

    public Integer getCantidadValorPedido() {
        return cantidadValorPedido;
    }

    public void setCantidadValorPedido(Integer cantidadValorPedido) {
        this.cantidadValorPedido = cantidadValorPedido;
    }

    public Double getPrecioValorPedido() {
        return precioValorPedido;
    }

    public void setPrecioValorPedido(Double precioValorPedido) {
        this.precioValorPedido = precioValorPedido;
    }

    public Double getTotalValorPedido() {
        return totalValorPedido;
    }

    public void setTotalValorPedido(Double totalValorPedido) {
        this.totalValorPedido = totalValorPedido;
    }
}
