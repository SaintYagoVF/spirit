package net.latinus.spirit.Entidades;

public class SpinOficina {

    private Integer idSpinOficina;

    private String nombreSpinOficina;

    public SpinOficina(Integer idSpinOficina, String nombreSpinOficina) {
        this.idSpinOficina = idSpinOficina;
        this.nombreSpinOficina = nombreSpinOficina;
    }


    public SpinOficina(){

    }

    public Integer getIdSpinOficina() {
        return idSpinOficina;
    }

    public void setIdSpinOficina(Integer idSpinOficina) {
        this.idSpinOficina = idSpinOficina;
    }

    public String getNombreSpinOficina() {
        return nombreSpinOficina;
    }

    public void setNombreSpinOficina(String nombreSpinOficina) {
        this.nombreSpinOficina = nombreSpinOficina;
    }
}
