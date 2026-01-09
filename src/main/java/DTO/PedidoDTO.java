package DTO;


public class PedidoDTO {
    private int idPedido;
    private int idUsuario;
    private String direccion;
    private double total;

    // Constructor
    public PedidoDTO(int idPedido, int idUsuario, String direccion, double total) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.direccion = direccion;
        this.total = total;
    }

    // Getters y Setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido ID: " + idPedido + "\nUsuario ID: " + idUsuario + 
               "\nDirección: " + direccion + "\nTotal: " + total + "\n";
    }
}
