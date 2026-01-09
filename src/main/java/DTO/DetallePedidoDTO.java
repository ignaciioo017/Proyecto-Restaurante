package DTO;

public class DetallePedidoDTO {
    private int idDetalle;
    private int idPedido;
    private int idProducto;
    private int cantidad;

    // Constructor
    public DetallePedidoDTO(int idDetalle, int idPedido, int idProducto, int cantidad) {
        this.idDetalle = idDetalle;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ID Detalle: " + idDetalle + ", ID Pedido: " + idPedido + 
               ", ID Producto: " + idProducto + ", Cantidad: " + cantidad;
    }
}

