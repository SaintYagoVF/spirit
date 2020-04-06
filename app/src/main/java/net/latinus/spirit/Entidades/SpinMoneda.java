package net.latinus.spirit.Entidades;

public class SpinMoneda {


    private Integer idSpinMoneda;

    private String nombreSpinMoneda;


    public SpinMoneda(Integer idSpinMoneda, String nombreSpinMoneda) {
        this.idSpinMoneda = idSpinMoneda;
        this.nombreSpinMoneda = nombreSpinMoneda;
    }

    public SpinMoneda() {
    }

    public Integer getIdSpinMoneda() {
        return idSpinMoneda;
    }

    public void setIdSpinMoneda(Integer idSpinMoneda) {
        this.idSpinMoneda = idSpinMoneda;
    }

    public String getNombreSpinMoneda() {
        return nombreSpinMoneda;
    }

    public void setNombreSpinMoneda(String nombreSpinMoneda) {
        this.nombreSpinMoneda = nombreSpinMoneda;
    }
}
