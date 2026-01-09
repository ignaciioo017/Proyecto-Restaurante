package Controladores;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControladorPrincipal {
    
	@FXML
    private ImageView IniciarSesion;
	@FXML
    private ImageView Registrarse;

	@FXML
	private void BotonInicioSesion(MouseEvent event) {
	        try {
	        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VistaInicioSesion.fxml"));
	            Parent nuevaVista = loader.load();
	            Scene nuevaEscena = new Scene(nuevaVista);
	            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            ventanaActual.setScene(nuevaEscena);
	            ventanaActual.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	            mostrarError("Error al cargar la vista de inicio de sesion.");
	            }
	        }
	@FXML
	private void BotonRegistrarse(MouseEvent event) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VistaRegistrarse.fxml"));
            Parent nuevaVista = loader.load();
            Scene nuevaEscena = new Scene(nuevaVista);
            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ventanaActual.setScene(nuevaEscena);
            ventanaActual.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarError("Error al cargar la vista de inicio de sesión.");
        }
    }

    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Error de inicio de sesión");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void agrandarImagenIS(MouseEvent event) {
        IniciarSesion.setFitHeight(66.0);
        IniciarSesion.setFitWidth(116.0);
    }

    public void restaurarImagenIS(MouseEvent event) {
        IniciarSesion.setFitHeight(56.0);
        IniciarSesion.setFitWidth(106.0);
    }
    
    public void agrandarImagenR(MouseEvent event) {
    	Registrarse.setFitHeight(64.0);
        Registrarse.setFitWidth(104.5);
    }

    public void restaurarImagenR(MouseEvent event) {
    	Registrarse.setFitHeight(60.0);
        Registrarse.setFitWidth(98.0);
            }
	}
	
	
	
