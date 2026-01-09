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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControladorPrincipalCliente {

    @FXML
    private ImageView salir;
	@FXML
	private Text Usuario;
	@FXML
	private Button botonReservar;
	@FXML
	private Button botonPedido;
	
    @FXML
    private void agrandarReservar(MouseEvent event) {
    	botonReservar.setScaleX(1.1);
    	botonReservar.setScaleY(1.1);
    }

    @FXML
    private void reducirReservar(MouseEvent event) {
    	botonReservar.setScaleX(1.0);
    	botonReservar.setScaleY(1.0);
    }
    
    @FXML
    private void agrandarPedido(MouseEvent event) {
    	botonPedido.setScaleX(1.1);
    	botonPedido.setScaleY(1.1);
    }

    @FXML
    private void reducirPedido(MouseEvent event) {
    	botonPedido.setScaleX(1.0);
    	botonPedido.setScaleY(1.0);
    }
    @FXML
    private void agrandarImagen(MouseEvent event) {
        salir.setFitHeight(46.0);
        salir.setFitWidth(44.0);
    }

    @FXML
    private void restaurarImagen(MouseEvent event) {
    	salir.setFitHeight(37.0);
        salir.setFitWidth(35.0);
    }
    
    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    @FXML
    private void hacerPedido(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VistaClientePedido.fxml"));
            Parent nuevaVista = loader.load();
            Scene nuevaEscena = new Scene(nuevaVista);
            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ventanaActual.setScene(nuevaEscena);
            ventanaActual.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error al cargar la vista para hacer pedidos.");
        }
    }
    
    @FXML
    private void hacerReserva(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VistaClienteReserva.fxml"));
            Parent nuevaVista = loader.load();
            Scene nuevaEscena = new Scene(nuevaVista);
            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ventanaActual.setScene(nuevaEscena);
            ventanaActual.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error al cargar la vista para hacer reservas.");
        }
    }
    
    @FXML
    private void cerrarSesion(MouseEvent event) {
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
    
}
