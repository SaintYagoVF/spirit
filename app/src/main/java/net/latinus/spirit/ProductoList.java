package net.latinus.spirit;

public class ProductoList {

    public ProductoList(String nom,String prod, String prec){
        this.nombreProd = nom;

        this.cantProd = prod;

        this.precioProd = prec;


    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public String getCantProd() {
        return cantProd;
    }

    public void setCantProd(String cantProd) {
        this.cantProd = cantProd;
    }

    private String nombreProd,cantProd,precioProd;

    public String getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(String precioProd) {
        this.precioProd = precioProd;
    }
}
