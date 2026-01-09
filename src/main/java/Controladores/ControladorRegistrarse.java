package Controladores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import RestauranteBD.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControladorRegistrarse {
	
    @FXML
    private ImageView volver;
    @FXML
    private Label iniciarSesion;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidosField;
    @FXML
    private TextField contrasenaField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField adminField;
    @FXML
    private Button botonRegistrarse;
    
    @FXML
    private void registrarse(ActionEvent event) {
        String nombre = nombreField.getText();
        String contrasena = contrasenaField.getText();
        String apellidos = apellidosField.getText();
        String email = emailField.getText();
        String telefono = telefonoField.getText();
        String tipo = adminField.getText();
        
        
        //Código correspondiente a adminField por si se quiere registrar como administrador
        if (tipo.equals("")){ //Si no ha escrito nada, es porque quiere ser cliente
        	tipo="cliente";  	
        }else if(tipo.equals("root")){ //La contraseña para ser admin será root
            	tipo="administrador";
        }else{ //Si ha escrito algo pero no es root, se le avisará de su error
            mostrarAlerta("Contraseña de Administrador incorrecta");
            return;
        }
        
        if (nombre.isEmpty() || contrasena.isEmpty() || apellidos.isEmpty() || email.isEmpty()|| telefono.isEmpty()) {
            mostrarAlerta("Por favor, rellene todos los datos");
            return;
        }
        
        try (Connection connection = DBConnection.getConnection()) {
            String query ="INSERT INTO Usuario (nombre, apellido, telefono, email, contrasena, tipo) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidos);
            preparedStatement.setString(3, telefono);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, contrasena);
            preparedStatement.setString(6, tipo);
            int verificador = preparedStatement.executeUpdate();
            
            if (verificador > 0) {
                System.out.println("Usuario insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el usuario.");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el usuario: " + e.getMessage());
            e.printStackTrace();
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
    private void agrandarLabelIniciarSesion(MouseEvent event) {
    	iniciarSesion.setScaleX(1.2);
    	iniciarSesion.setScaleY(1.2);
    }

    @FXML
    private void restaurarLabelIniciarSesion(MouseEvent event) {
    	iniciarSesion.setScaleX(1.0);
    	iniciarSesion.setScaleY(1.0);
    }
    
    @FXML
    private void agrandarBoton(MouseEvent event) {
    	botonRegistrarse.setScaleX(1.1);
    	botonRegistrarse.setScaleY(1.1);
    }

    @FXML
    private void reducirBoton(MouseEvent event) {
    	botonRegistrarse.setScaleX(1.0);
    	botonRegistrarse.setScaleY(1.0);
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
    private void irAIniciarSesion(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VistaInicioSesion.fxml"));
            Parent nuevaVista = loader.load();
            Scene nuevaEscena = new Scene(nuevaVista);
            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ventanaActual.setScene(nuevaEscena);
            ventanaActual.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error al cargar la vista de inicio de sesion.");
        }
    }
}