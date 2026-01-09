package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DTO.MenuDTO;
import RestauranteBD.DBConnection;
import javafx.scene.control.Menu;

public class MenuDAO {
    private Connection conexion;

    public MenuDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Leer todos los productos del menú
    public List<MenuDTO> obtenerTodos() throws SQLException {
        List<MenuDTO> menuItems = new ArrayList<>();
        String sql = "SELECT * FROM Menu";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                MenuDTO menu = new MenuDTO();
                menu.setIdProducto(rs.getInt("idProducto"));
                menu.setNombre(rs.getString("nombre"));
                menu.setDescripcion(rs.getString("descripcion"));
                menu.setPrecio(rs.getDouble("precio"));
                menuItems.add(menu);
            }
        }
        return menuItems;
    }

    // Agregar un producto al menú
    public void agregar(MenuDTO menu) throws SQLException {
        String sql = "INSERT INTO Menu (nombre, descripcion, precio) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, menu.getNombre());
            stmt.setString(2, menu.getDescripcion());
            stmt.setDouble(3, menu.getPrecio());
            stmt.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM menu WHERE idProducto = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, id); // Pasamos el ID del producto a eliminar
            stmt.executeUpdate();
        }
    }

    
    public void actualizar(MenuDTO menu) throws SQLException {
        String query = "UPDATE menu SET nombre = ?, descripcion = ?, precio = ? WHERE idProducto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, menu.getNombre());
            stmt.setString(2, menu.getDescripcion());
            stmt.setDouble(3, menu.getPrecio());
            stmt.setInt(4, menu.getIdProducto());
            stmt.executeUpdate();
        }
    }


    
}

