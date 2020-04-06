package net.latinus.spirit.Entidades;

public class SpinLote {

    private Integer idSpinLote;

    private String nombreSpinLote;


    public SpinLote(Integer idSpinLote, String nombreSpinLote) {
        this.idSpinLote = idSpinLote;
        this.nombreSpinLote = nombreSpinLote;
    }

    public SpinLote() {
    }

    public Integer getIdSpinLote() {
        return idSpinLote;
    }

    public void setIdSpinLote(Integer idSpinLote) {
        this.idSpinLote = idSpinLote;
    }

    public String getNombreSpinLote() {
        return nombreSpinLote;
    }

    public void setNombreSpinLote(String nombreSpinLote) {
        this.nombreSpinLote = nombreSpinLote;
    }
}
