package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.PedidoDTO;
import RestauranteBD.DBConnection;

public class PedidoDAO {

    // Método para obtener todos los pedidos
    public List<PedidoDTO> obtenerPedidos() {
        List<PedidoDTO> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedidos";

        try (Connection conexion = DBConnection.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idPedido = rs.getInt("idPedido");
                int idUsuario = rs.getInt("idUsuario");
                String direccion = rs.getString("direccion");
                double total = rs.getDouble("total");

                PedidoDTO pedido = new PedidoDTO(idPedido, idUsuario, direccion, total);
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pedidos;
    }
}

