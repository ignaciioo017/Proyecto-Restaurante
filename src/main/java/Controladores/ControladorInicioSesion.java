package Controladores;

import RestauranteBD.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DTO.UsuarioDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControladorInicioSesion implements Initializable {

    @FXML
    private ImageView volver;
    @FXML
    private Label Registrarse;
    @FXML
    private TextField usuarioField;
    @FXML
    private TextField contrasenaField;
    @FXML
    private ComboBox<String> tipoComboBox;
    @FXML
    private Button botonIniciarSesion;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tipoComboBox.getItems().addAll("cliente", "administrador");
    }

    @FXML
    private void iniciarSesion(ActionEvent event) {
        String nombre = usuarioField.getText();
        String contrasena = contrasenaField.getText();
        String tipoSeleccionado = tipoComboBox.getValue();

        if (nombre.isEmpty() || contrasena.isEmpty() || tipoSeleccionado == null) {
            mostrarAlerta("Por favor, ingrese todos los campos y seleccione un tipo de usuario.");
            return;
        }

        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT nombre, tipo FROM usuario WHERE nombre = ? AND contrasena = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, contrasena);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String tipoEnBD = resultSet.getString("tipo");
                if (tipoSeleccionado.equals(tipoEnBD)) {
                    if ("cliente".equals(tipoEnBD)) {
                        abrirVistaPrincipalCliente(event);
                    } else if ("administrador".equals(tipoEnBD)) {
                        
                        String nombreAdministrador = resultSet.getString("nombre");
                        cargarVistaPrincipalAdmin(event, nombreAdministrador); 
                    }
                } else {
                    mostrarAlerta("El tipo seleccionado no coincide con el tipo registrado.");
                }
            } else {
                mostrarAlerta("Usuario o contraseña incorrectos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Error de conexión a la base de datos.");
        }
    }

    private void cargarVistaPrincipalAdmin(ActionEvent event, String nombreAdministrador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VistaPrincipalAdmin.fxml"));
            Parent nuevaVista = loader.load();

            ControladorPrincipalAdmin controladorAdmin = loader.getController();
            controladorAdmin.setNombreAdministrador(nombreAdministrador);             
            // Pasar el nombre del administrador
            controladorAdmin.setNombreAdministrador(nombreAdministrador); 
            
            Scene nuevaEscena = new Scene(nuevaVista);
            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ventanaActual.setScene(nuevaEscena);
            ventanaActual.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error al cargar la vista del administrador.");
        }
    }

    
    
    private void abrirVistaPrincipalCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VistaPrincipalCliente.fxml"));
            Parent nuevaVista = loader.load();
            Scene nuevaEscena = new Scene(nuevaVista);
            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ventanaActual.setScene(nuevaEscena);
            ventanaActual.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error al cargar la vista del cliente.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void volverAInicio(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VistaPrincipal.fxml"));
            Parent nuevaVista = loader.load();
            Scene nuevaEscena = new Scene(nuevaVista);
            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ventanaActual.setScene(nuevaEscena);
            ventanaActual.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error al cargar la vista principal.");
        }
    }

    @FXML
    private void agrandarImagen(MouseEvent event) {
        volver.setFitHeight(66.0);
        volver.setFitWidth(116.0);
    }

    @FXML
    private void restaurarImagen(MouseEvent event) {
        volver.setFitHeight(56.0);
        volver.setFitWidth(106.0);
    }
    
    @FXML
    private void irARegistrarse(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VistaRegistrarse.fxml"));
            Parent nuevaVista = loader.load();
            Scene nuevaEscena = new Scene(nuevaVista);
            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ventanaActual.setScene(nuevaEscena);
            ventanaActual.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error al cargar la vista de registro.");
        }
    }
    
    @FXML
    private void agrandarLabelRegistrarse(MouseEvent event) {
        Registrarse.setScaleX(1.2);
        Registrarse.setScaleY(1.2);
    }

    @FXML
    private void restaurarLabelRegistrarse(MouseEvent event) {
        Registrarse.setScaleX(1.0);
        Registrarse.setScaleY(1.0);
    }
    
    @FXML
    private void agrandarBoton(MouseEvent event) {
        botonIniciarSesion.setScaleX(1.1);
        botonIniciarSesion.setScaleY(1.1);
    }

    @FXML
    private void reducirBoton(MouseEvent event) {
        botonIniciarSesion.setScaleX(1.0);
        botonIniciarSesion.setScaleY(1.0);
    }


}



