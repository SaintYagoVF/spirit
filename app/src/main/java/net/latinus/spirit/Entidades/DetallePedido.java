package net.latinus.spirit.Entidades;

public class DetallePedido {

    private Integer pedidoIdPedidoDetalle;

    private Integer documentoPedidoDetalle;

    private String nombreProductoPedidoDetalle;

    private Integer lotePedidoDetalle;

    private String descripcionProductoPedidoDetalle;

    private String identificacionPedidoDetalle;

    private Double cantidadProductoPedidoDetalle;

    private Double precioProductoPedidoDetalle;

    private Double descuentoProductoPedidoDetalle;

    private Double valorPedidoDetalle;

    private Double ivaValorPedidoDetalle;

    public DetallePedido(Integer pedidoIdPedidoDetalle, Integer documentoPedidoDetalle, String nombreProductoPedidoDetalle, Integer lotePedidoDetalle, String descripcionProductoPedidoDetalle, String identificacionPedidoDetalle, Double cantidadProductoPedidoDetalle, Double precioProductoPedidoDetalle, Double descuentoProductoPedidoDetalle, Double valorPedidoDetalle, Double ivaValorPedidoDetalle) {
        this.pedidoIdPedidoDetalle = pedidoIdPedidoDetalle;
        this.documentoPedidoDetalle = documentoPedidoDetalle;
        this.nombreProductoPedidoDetalle = nombreProductoPedidoDetalle;
        this.lotePedidoDetalle = lotePedidoDetalle;
        this.descripcionProductoPedidoDetalle = descripcionProductoPedidoDetalle;
        this.identificacionPedidoDetalle = identificacionPedidoDetalle;
        this.cantidadProductoPedidoDetalle = cantidadProductoPedidoDetalle;
        this.precioProductoPedidoDetalle = precioProductoPedidoDetalle;
        this.descuentoProductoPedidoDetalle = descuentoProductoPedidoDetalle;
        this.valorPedidoDetalle = valorPedidoDetalle;
        this.ivaValorPedidoDetalle = ivaValorPedidoDetalle;
    }

    public DetallePedido() {
    }


    public Integer getPedidoIdPedidoDetalle() {
        return pedidoIdPedidoDetalle;
    }

    public void setPedidoIdPedidoDetalle(Integer pedidoIdPedidoDetalle) {
        this.pedidoIdPedidoDetalle = pedidoIdPedidoDetalle;
    }

    public Integer getDocumentoPedidoDetalle() {
        return documentoPedidoDetalle;
    }

    public void setDocumentoPedidoDetalle(Integer documentoPedidoDetalle) {
        this.documentoPedidoDetalle = documentoPedidoDetalle;
    }

    public String getNombreProductoPedidoDetalle() {
        return nombreProductoPedidoDetalle;
    }

    public void setNombreProductoPedidoDetalle(String nombreProductoPedidoDetalle) {
        this.nombreProductoPedidoDetalle = nombreProductoPedidoDetalle;
    }

    public Integer getLotePedidoDetalle() {
        return lotePedidoDetalle;
    }

    public void setLotePedidoDetalle(Integer lotePedidoDetalle) {
        this.lotePedidoDetalle = lotePedidoDetalle;
    }

    public String getDescripcionProductoPedidoDetalle() {
        return descripcionProductoPedidoDetalle;
    }

    public void setDescripcionProductoPedidoDetalle(String descripcionProductoPedidoDetalle) {
        this.descripcionProductoPedidoDetalle = descripcionProductoPedidoDetalle;
    }

    public String getIdentificacionPedidoDetalle() {
        return identificacionPedidoDetalle;
    }

    public void setIdentificacionPedidoDetalle(String identificacionPedidoDetalle) {
        this.identificacionPedidoDetalle = identificacionPedidoDetalle;
    }

    public Double getCantidadProductoPedidoDetalle() {
        return cantidadProductoPedidoDetalle;
    }

    public void setCantidadProductoPedidoDetalle(Double cantidadProductoPedidoDetalle) {
        this.cantidadProductoPedidoDetalle = cantidadProductoPedidoDetalle;
    }

    public Double getPrecioProductoPedidoDetalle() {
        return precioProductoPedidoDetalle;
    }

    public void setPrecioProductoPedidoDetalle(Double precioProductoPedidoDetalle) {
        this.precioProductoPedidoDetalle = precioProductoPedidoDetalle;
    }

    public Double getDescuentoProductoPedidoDetalle() {
        return descuentoProductoPedidoDetalle;
    }

    public void setDescuentoProductoPedidoDetalle(Double descuentoProductoPedidoDetalle) {
        this.descuentoProductoPedidoDetalle = descuentoProductoPedidoDetalle;
    }

    public Double getValorPedidoDetalle() {
        return valorPedidoDetalle;
    }

    public void setValorPedidoDetalle(Double valorPedidoDetalle) {
        this.valorPedidoDetalle = valorPedidoDetalle;
    }

    public Double getIvaValorPedidoDetalle() {
        return ivaValorPedidoDetalle;
    }

    public void setIvaValorPedidoDetalle(Double ivaValorPedidoDetalle) {
        this.ivaValorPedidoDetalle = ivaValorPedidoDetalle;
    }
}
