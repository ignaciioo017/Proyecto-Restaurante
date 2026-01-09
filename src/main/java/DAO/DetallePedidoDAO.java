package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.DetallePedidoDTO;
import RestauranteBD.DBConnection;

public class DetallePedidoDAO {

    private Connection connect() {
        try {
            // Cambiar por los datos de tu base de datos
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_base_datos", "usuario", "contraseña");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DetallePedidoDTO> obtenerDetalles() {
        List<DetallePedidoDTO> detalles = new ArrayList<>();

        String query = "SELECT * FROM Detalle_Pedido";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int idDetalle = rs.getInt("idDetalle");
                int idPedido = rs.getInt("idPedido");
                int idProducto = rs.getInt("idProducto");
                int cantidad = rs.getInt("cantidad");
                detalles.add(new DetallePedidoDTO(idDetalle, idPedido, idProducto, cantidad));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return detalles;
    }
    
    public List<DetallePedidoDTO> obtenerDetallesPorPedido(int idPedido) {
        List<DetallePedidoDTO> detalles = new ArrayList<>();
        String query = "SELECT * FROM Detalle_Pedido WHERE idPedido = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, idPedido);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idDetalle = resultSet.getInt("idDetalle");
                int idProducto = resultSet.getInt("idProducto");
                int cantidad = resultSet.getInt("cantidad");
                
                // Crear el DTO y agregarlo a la lista
                DetallePedidoDTO detalle = new DetallePedidoDTO(idDetalle, idPedido, idProducto, cantidad);
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detalles;
    }

}
