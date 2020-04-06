package net.latinus.spirit.Entidades;

public class SpinOrigenDocumento {


    private Integer idSpinOrigenDocumento;

    private String nombreSpinOrigenDocumento;

    public SpinOrigenDocumento(Integer idSpinOrigenDocumento, String nombreSpinOrigenDocumento) {
        this.idSpinOrigenDocumento = idSpinOrigenDocumento;
        this.nombreSpinOrigenDocumento = nombreSpinOrigenDocumento;
    }

    public SpinOrigenDocumento() {
    }

    public Integer getIdSpinOrigenDocumento() {
        return idSpinOrigenDocumento;
    }

    public void setIdSpinOrigenDocumento(Integer idSpinOrigenDocumento) {
        this.idSpinOrigenDocumento = idSpinOrigenDocumento;
    }

    public String getNombreSpinOrigenDocumento() {
        return nombreSpinOrigenDocumento;
    }

    public void setNombreSpinOrigenDocumento(String nombreSpinOrigenDocumento) {
        this.nombreSpinOrigenDocumento = nombreSpinOrigenDocumento;
    }
}
