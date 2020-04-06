package net.latinus.spirit.Entidades;

public class SpinListaPrecio {


    private Integer idSpinListaPrecio;

    private String nombreSpinListaPrecio;


    public SpinListaPrecio(Integer idSpinListaPrecio, String nombreSpinListaPrecio) {
        this.idSpinListaPrecio = idSpinListaPrecio;
        this.nombreSpinListaPrecio = nombreSpinListaPrecio;
    }

    public SpinListaPrecio() {
    }


    public Integer getIdSpinListaPrecio() {
        return idSpinListaPrecio;
    }

    public void setIdSpinListaPrecio(Integer idSpinListaPrecio) {
        this.idSpinListaPrecio = idSpinListaPrecio;
    }

    public String getNombreSpinListaPrecio() {
        return nombreSpinListaPrecio;
    }

    public void setNombreSpinListaPrecio(String nombreSpinListaPrecio) {
        this.nombreSpinListaPrecio = nombreSpinListaPrecio;
    }
}
