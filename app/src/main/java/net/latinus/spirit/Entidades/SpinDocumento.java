package net.latinus.spirit.Entidades;

public class SpinDocumento {

    private Integer idSpinDocumento;

    private String nombreSpinDocumento;


    public SpinDocumento(Integer idSpinDocumento, String nombreSpinDocumento) {
        this.idSpinDocumento = idSpinDocumento;
        this.nombreSpinDocumento = nombreSpinDocumento;
    }


    public SpinDocumento() {
    }

    public Integer getIdSpinDocumento() {
        return idSpinDocumento;
    }

    public void setIdSpinDocumento(Integer idSpinDocumento) {
        this.idSpinDocumento = idSpinDocumento;
    }

    public String getNombreSpinDocumento() {
        return nombreSpinDocumento;
    }

    public void setNombreSpinDocumento(String nombreSpinDocumento) {
        this.nombreSpinDocumento = nombreSpinDocumento;
    }
}
