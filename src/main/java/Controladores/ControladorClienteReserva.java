package Controladores;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControladorClienteReserva {
	@FXML
	private ImageView volver;
	@FXML
	private Button botonGuardar;
	
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
    private void agrandarBoton(MouseEvent event) {
    	botonGuardar.setScaleX(1.1);
    	botonGuardar.setScaleY(1.1);
    }

    @FXML
    private void reducirBoton(MouseEvent event) {
    	botonGuardar.setScaleX(1.0);
    	botonGuardar.setScaleY(1.0);
    }
    
    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    @FXML
    private void irAVistaPrincipalCliente(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VistaPrincipalCliente.fxml"));
            Parent nuevaVista = loader.load();
            Scene nuevaEscena = new Scene(nuevaVista);
            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ventanaActual.setScene(nuevaEscena);
            ventanaActual.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error al cargar la vista principal del cliente.");
        }
    }
    
}
