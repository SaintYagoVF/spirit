package net.latinus.spirit.Entidades;

public class SpinCorporacion {

    private Integer idSpinCorp;

    private String nombreSpinCorp;


    public SpinCorporacion(Integer idSpinCorp, String nombreSpinCorp) {
        this.idSpinCorp = idSpinCorp;
        this.nombreSpinCorp = nombreSpinCorp;
    }

    public SpinCorporacion(){


    }

    public Integer getIdSpinCorp() {
        return idSpinCorp;
    }

    public void setIdSpinCorp(Integer idSpinCorp) {
        this.idSpinCorp = idSpinCorp;
    }

    public String getNombreSpinCorp() {
        return nombreSpinCorp;
    }

    public void setNombreSpinCorp(String nombreSpinCorp) {
        this.nombreSpinCorp = nombreSpinCorp;
    }
}
