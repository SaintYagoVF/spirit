package net.latinus.spirit.Entidades;

public class SpinTipoDocumento {


    private Integer idSpinTipoDocumento;

    private String nombreSpinTipoDocumento;


    public SpinTipoDocumento(Integer idSpinTipoDocumento, String nombreSpinTipoDocumento) {
        this.idSpinTipoDocumento = idSpinTipoDocumento;
        this.nombreSpinTipoDocumento = nombreSpinTipoDocumento;
    }

    public SpinTipoDocumento() {
    }


    public Integer getIdSpinTipoDocumento() {
        return idSpinTipoDocumento;
    }

    public void setIdSpinTipoDocumento(Integer idSpinTipoDocumento) {
        this.idSpinTipoDocumento = idSpinTipoDocumento;
    }

    public String getNombreSpinTipoDocumento() {
        return nombreSpinTipoDocumento;
    }

    public void setNombreSpinTipoDocumento(String nombreSpinTipoDocumento) {
        this.nombreSpinTipoDocumento = nombreSpinTipoDocumento;
    }
}
