package net.latinus.spirit.Entidades;

public class Precios {


    private Integer idProdPrecio;

    private String nombrePrecio;

    private Double valorPrecio;

    private Integer listaPrecio;

    public Precios(Integer idPrecio, String nombrePrecio, Double precio, Integer listaPrecio) {
        this.idProdPrecio = idPrecio;
        this.nombrePrecio = nombrePrecio;
        this.valorPrecio = precio;
        this.listaPrecio = listaPrecio;
    }


    public Precios() {
    }


    public Integer getIdProdPrecio() {
        return idProdPrecio;
    }

    public void setIdProdPrecio(Integer idPrecio) {
        this.idProdPrecio = idPrecio;
    }

    public String getNombrePrecio() {
        return nombrePrecio;
    }

    public void setNombrePrecio(String nombrePrecio) {
        this.nombrePrecio = nombrePrecio;
    }

    public Double getValorPrecio() {
        return valorPrecio;
    }

    public void setValorPrecio(Double precio) {
        this.valorPrecio = precio;
    }

    public Integer getListaPrecio() {
        return listaPrecio;
    }

    public void setListaPrecio(Integer listaPrecio) {
        this.listaPrecio = listaPrecio;
    }
}
