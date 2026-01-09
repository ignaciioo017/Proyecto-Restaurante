package Controladores;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import DAO.MenuDAO;
import DTO.MenuDTO;
import RestauranteBD.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorFormularioMenu {
    @FXML
    private TextField campoNombre;

    @FXML
    private TextArea campoDescripcion;

    @FXML
    private TextField campoPrecio;
    @FXML
    private MenuDTO productoSeleccionado;  

    
    
    
    
 // Método para recibir el producto seleccionado en vistaGestionMenu
    public void setProductoSeleccionado(MenuDTO producto) {
        this.productoSeleccionado = producto;
        // Rellenamos los campos con los datos del producto
        campoNombre.setText(producto.getNombre());
        campoDescripcion.setText(producto.getDescripcion());
        campoPrecio.setText(String.valueOf(producto.getPrecio()));
    }
    
    
    @FXML
    private void guardarProducto() {
        String nombre = campoNombre.getText();
        String descripcion = campoDescripcion.getText();
        String precioTexto = campoPrecio.getText();

        if (nombre.isEmpty() || descripcion.isEmpty() || precioTexto.isEmpty()) {
            mostrarMensaje("Error", "Todos los campos son obligatorios.", AlertType.ERROR);
            return;
        }

        try {
            double precio = Double.parseDouble(precioTexto);

            if (productoSeleccionado != null) {
                // Modificar producto existente
                productoSeleccionado.setNombre(nombre);
                productoSeleccionado.setDescripcion(descripcion);
                productoSeleccionado.setPrecio(precio);

                try (Connection conexion = DBConnection.getConnection()) {
                    MenuDAO menuDAO = new MenuDAO(conexion);
                    menuDAO.actualizar(productoSeleccionado); // Método para actualizar producto en la base de datos
                    mostrarMensaje("Éxito", "Producto modificado con éxito.", AlertType.INFORMATION);
                } catch (SQLException e) {
                    e.printStackTrace();
                    mostrarMensaje("Error de base de datos", "Error al conectarse a la base de datos.", AlertType.ERROR);
                }
            } else {
                // Agregar nuevo producto
                MenuDTO nuevoMenu = new MenuDTO();
                nuevoMenu.setNombre(nombre);
                nuevoMenu.setDescripcion(descripcion);
                nuevoMenu.setPrecio(precio);

                try (Connection conexion = DBConnection.getConnection()) {
                    MenuDAO menuDAO = new MenuDAO(conexion);
                    menuDAO.agregar(nuevoMenu);
                    mostrarMensaje("Éxito", "Producto agregado con éxito.", AlertType.INFORMATION);
                } catch (SQLException e) {
                    e.printStackTrace();
                    mostrarMensaje("Error de base de datos", "Error al conectarse a la base de datos.", AlertType.ERROR);
                }
            }

            // Limpiar campos después de agregar o modificar
            campoNombre.setText("");
            campoDescripcion.setText("");
            campoPrecio.setText("");

        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "El precio debe ser un número válido.", AlertType.ERROR);
        }
    }

    
    
    // Método para mostrar mensajes de error
    private void mostrarMensaje(String titulo, String contenido, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
	
	
	
	//Método volver
    @FXML
    private void volver(ActionEvent event) {
        try {
            // Cargar la vista desde el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaGestionMenu.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error al cargar la vista.");
        }
    }
    
    
    //ALERTA
    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
