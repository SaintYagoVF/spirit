package net.latinus.spirit.Entidades;

public class SpinTipoProv {


    private Integer idSpinTipoProv;

    private String nombreSpinTipoProv;


    public SpinTipoProv(Integer idSpinTipoProv, String nombreSpinTipoProv) {
        this.idSpinTipoProv = idSpinTipoProv;
        this.nombreSpinTipoProv = nombreSpinTipoProv;
    }


    public SpinTipoProv(){

    }


    public Integer getIdSpinTipoProv() {
        return idSpinTipoProv;
    }

    public void setIdSpinTipoProv(Integer idSpinTipoProv) {
        this.idSpinTipoProv = idSpinTipoProv;
    }

    public String getNombreSpinTipoProv() {
        return nombreSpinTipoProv;
    }

    public void setNombreSpinTipoProv(String nombreSpinTipoProv) {
        this.nombreSpinTipoProv = nombreSpinTipoProv;
    }
}
