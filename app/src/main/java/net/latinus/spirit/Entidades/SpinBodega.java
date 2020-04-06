package net.latinus.spirit.Entidades;

public class SpinBodega {


    private Integer idSpinBodega;

    private String nombreSpinBodega;


    public SpinBodega(Integer idSpinBodega, String nombreSpinBodega) {
        this.idSpinBodega = idSpinBodega;
        this.nombreSpinBodega = nombreSpinBodega;
    }

    public SpinBodega() {
    }

    public Integer getIdSpinBodega() {
        return idSpinBodega;
    }

    public void setIdSpinBodega(Integer idSpinBodega) {
        this.idSpinBodega = idSpinBodega;
    }

    public String getNombreSpinBodega() {
        return nombreSpinBodega;
    }

    public void setNombreSpinBodega(String nombreSpinBodega) {
        this.nombreSpinBodega = nombreSpinBodega;
    }
}
