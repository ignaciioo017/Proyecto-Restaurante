package Controladores;

import java.io.IOException;

import javafx.event.ActionEvent;
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

public class ControladorGestionReservas {
	
	@FXML
	private ImageView volver;
	
	//Métodos Imagen volver
		 @FXML
		    private void volverAInicio(MouseEvent event) {
		        try {
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VistaPrincipalAdmin.fxml"));
		            Parent nuevaVista = loader.load();
		            Scene nuevaEscena = new Scene(nuevaVista);
		            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
		            ventanaActual.setScene(nuevaEscena);
		            ventanaActual.show();
		        } catch (IOException e) {
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


	
	
	}

