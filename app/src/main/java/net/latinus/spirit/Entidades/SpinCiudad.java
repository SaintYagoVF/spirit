package net.latinus.spirit.Entidades;

public class SpinCiudad {

    private Integer idSpinCiudad;

    private String nombreSpinCiudad;

    public SpinCiudad(Integer idSpinCiudad, String nombreSpinCiudad) {
        this.idSpinCiudad = idSpinCiudad;
        this.nombreSpinCiudad = nombreSpinCiudad;
    }


    public SpinCiudad(){


    }


    public Integer getIdSpinCiudad() {
        return idSpinCiudad;
    }

    public void setIdSpinCiudad(Integer idSpinCiudad) {
        this.idSpinCiudad = idSpinCiudad;
    }

    public String getNombreSpinCiudad() {
        return nombreSpinCiudad;
    }

    public void setNombreSpinCiudad(String nombreSpinCiudad) {
        this.nombreSpinCiudad = nombreSpinCiudad;
    }
}
