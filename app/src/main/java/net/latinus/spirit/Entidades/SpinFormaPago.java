package net.latinus.spirit.Entidades;

public class SpinFormaPago {


    private Integer idSpinFormaPago;

    private String nombreSpinFormaPago;

    public SpinFormaPago(Integer idSpinFormaPago, String nombreSpinFormaPago) {
        this.idSpinFormaPago = idSpinFormaPago;
        this.nombreSpinFormaPago = nombreSpinFormaPago;
    }

    public SpinFormaPago() {
    }

    public Integer getIdSpinFormaPago() {
        return idSpinFormaPago;
    }

    public void setIdSpinFormaPago(Integer idSpinFormaPago) {
        this.idSpinFormaPago = idSpinFormaPago;
    }

    public String getNombreSpinFormaPago() {
        return nombreSpinFormaPago;
    }

    public void setNombreSpinFormaPago(String nombreSpinFormaPago) {
        this.nombreSpinFormaPago = nombreSpinFormaPago;
    }
}
