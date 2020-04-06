package net.latinus.spirit.Entidades;

public class UnionCR {


    private String identificacionClienteUnion;


    private String nombreClienteUnion;


    public UnionCR(String idClienteUnion, String nombreClienteUnion) {
        this.identificacionClienteUnion = idClienteUnion;
        this.nombreClienteUnion = nombreClienteUnion;
    }

    public UnionCR(){

    }

    public String getIdentificacionClienteUnion() {
        return identificacionClienteUnion;
    }

    public void setIdentificacionClienteUnion(String identificacionClienteUnion) {
        this.identificacionClienteUnion = identificacionClienteUnion;
    }

    public String getNombreClienteUnion() {
        return nombreClienteUnion;
    }

    public void setNombreClienteUnion(String nombreClienteUnion) {
        this.nombreClienteUnion = nombreClienteUnion;
    }
}
