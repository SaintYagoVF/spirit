package net.latinus.spirit.Entidades;

public class Stock {

    private Integer idStock;

    private Integer idBodegaStock;

    private Integer idProductoStock;

    private Integer idLoteStock;

    private String anioStock;

    private String mesStock;

    private Double cantidadStock;

    private Double reservaStock;

    private Double transitoStock;

    private String fechaStock;

    private String nombreStock;

    public String getNombreStock() {
        return nombreStock;
    }

    public void setNombreStock(String nombreStock) {
        this.nombreStock = nombreStock;
    }

    public Stock(Integer idStock, Integer idBodegaStock, Integer idProductoStock, Integer idLoteStock, String anioStock, String mesStock, Double cantidadStock, Double reservaStock, Double transitoStock, String fechaStock, String nombreStock) {
        this.idStock = idStock;
        this.idBodegaStock = idBodegaStock;
        this.idProductoStock = idProductoStock;
        this.idLoteStock = idLoteStock;
        this.anioStock = anioStock;
        this.mesStock = mesStock;
        this.cantidadStock = cantidadStock;
        this.reservaStock = reservaStock;
        this.transitoStock = transitoStock;
        this.fechaStock = fechaStock;
        this.nombreStock = nombreStock;
    }

    public Stock(){


    }


    public Integer getIdBodegaStock() {
        return idBodegaStock;
    }

    public void setIdBodegaStock(Integer idBodegaStock) {
        this.idBodegaStock = idBodegaStock;
    }

    public Integer getIdLoteStock() {
        return idLoteStock;
    }

    public void setIdLoteStock(Integer idLoteStock) {
        this.idLoteStock = idLoteStock;
    }

    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
    }

    public Integer getIdProductoStock() {
        return idProductoStock;
    }

    public void setIdProductoStock(Integer idProductoStock) {
        this.idProductoStock = idProductoStock;
    }

    public String getAnioStock() {
        return anioStock;
    }

    public void setAnioStock(String anioStock) {
        this.anioStock = anioStock;
    }

    public String getMesStock() {
        return mesStock;
    }

    public void setMesStock(String mesStock) {
        this.mesStock = mesStock;
    }

    public Double getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Double cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Double getReservaStock() {
        return reservaStock;
    }

    public void setReservaStock(Double reservaStock) {
        this.reservaStock = reservaStock;
    }

    public Double getTransitoStock() {
        return transitoStock;
    }

    public void setTransitoStock(Double transitoStock) {
        this.transitoStock = transitoStock;
    }

    public String getFechaStock() {
        return fechaStock;
    }

    public void setFechaStock(String fechaStock) {
        this.fechaStock = fechaStock;
    }
}
