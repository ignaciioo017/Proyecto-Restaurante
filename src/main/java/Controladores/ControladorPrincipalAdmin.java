package Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

import DAO.DetallePedidoDAO;
import DAO.PedidoDAO;
import DTO.DetallePedidoDTO;
import DTO.PedidoDTO;


public class ControladorPrincipalAdmin {

    @FXML
    private Button botonMenu;
    @FXML
    private Button botonReserva;
    @FXML
    private Button botonPedido;
    @FXML
    private Label labelNombreAdministrador;
    @FXML
    private ImageView salir;
    @FXML
    private TextArea textAreaPedidos;
    
    
    @FXML
    public void mostrarPedidos() {
        // Crear instancias de los DAOs para obtener los datos
        PedidoDAO pedidoDAO = new PedidoDAO();
        DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAO();
        
        // Obtener la lista de pedidos
        List<PedidoDTO> pedidos = pedidoDAO.obtenerPedidos();

        // StringBuilder para construir el contenido del TextArea
        StringBuilder contenido = new StringBuilder();

        // Recorrer todos los pedidos
        for (PedidoDTO pedido : pedidos) {
            // Agregar información del pedido
            contenido.append("Pedido ID: ").append(pedido.getIdPedido())
                     .append(", Usuario ID: ").append(pedido.getIdUsuario())
                     .append(", Dirección: ").append(pedido.getDireccion())
                     .append(", Total: ").append(pedido.getTotal())
                     .append("\n");

            // Obtener los detalles de este pedido
            List<DetallePedidoDTO> detalles = detallePedidoDAO.obtenerDetallesPorPedido(pedido.getIdPedido());

            // Recorrer los detalles y agregar al contenido
            for (DetallePedidoDTO detalle : detalles) {
                contenido.append("  Detalle - Producto ID: ").append(detalle.getIdProducto())
                         .append(", Cantidad: ").append(detalle.getCantidad())
                         .append("\n");
            }

            // Añadir una línea separadora entre pedidos
            contenido.append("--------------------------------------------------\n");
        }

        // Establecer el contenido en el TextArea
        textAreaPedidos.setText(contenido.toString());
    }

    
    @FXML
    public void initialize() {
        mostrarPedidos();
    }


    
    
	@FXML
    private void vistaGestionMenu(ActionEvent event) {
        try {
            // Cargar la nueva vista desde el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VistaGestionMenu.fxml"));
            Parent nuevaVista = loader.load();
            
            
            
            // Crear una nueva escena para la vista
            Scene nuevaEscena = new Scene(nuevaVista);
            
            // Obtener la ventana actual (Stage) desde el evento
            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // Cambiar la escena de la ventana
            ventanaActual.setScene(nuevaEscena);
            ventanaActual.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error.");
        }
    }
	
	@FXML
    private void vistaPedidosFinalizados(ActionEvent event) {
        try {
            // Cargar la nueva vista desde el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VistaPedidosFinalizados.fxml"));
            Parent nuevaVista = loader.load();
            
            // Crear una nueva escena para la vista
            Scene nuevaEscena = new Scene(nuevaVista);
            
            // Obtener la ventana actual (Stage) desde el evento
            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // Cambiar la escena de la ventana
            ventanaActual.setScene(nuevaEscena);
            ventanaActual.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la nueva vista.");
        }
    }
	
	@FXML
    private void vistaGestionReserva(ActionEvent event) {
        try {
            // Cargar la nueva vista desde el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VistaGestionReserva.fxml"));
            Parent nuevaVista = loader.load();
            
            // Crear una nueva escena para la vista
            Scene nuevaEscena = new Scene(nuevaVista);
            
            // Obtener la ventana actual (Stage) desde el evento
            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // Cambiar la escena de la ventana
            ventanaActual.setScene(nuevaEscena);
            ventanaActual.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la nueva vista.");
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
	        }
	    }
	 
	 @FXML
	    private void agrandarImagenSalir(MouseEvent event) {
	        salir.setFitHeight(37.2);
	        salir.setFitWidth(90.0);
	    }

	    @FXML
	    private void restaurarImagenSalir(MouseEvent event) {
	        salir.setFitHeight(31.0);
	        salir.setFitWidth(75.0);
	    }




    // Método para establecer el nombre del administrador
    public void setNombreAdministrador(String nombre) {
        // Actualizar el texto del label con el nombre del administrador
        labelNombreAdministrador.setText("       BIENVENIDO, " + nombre);
    }
	
    @FXML
    private void agrandarBotonMenu(MouseEvent event) {
        botonMenu.setScaleX(1.1);
        botonMenu.setScaleY(1.1);
    }

    @FXML
    private void reducirBotonMenu(MouseEvent event) {
        botonMenu.setScaleX(1.0);
        botonMenu.setScaleY(1.0);
    }
    
    @FXML
    private void agrandarBotonReserva(MouseEvent event) {
        botonReserva.setScaleX(1.1);
        botonReserva.setScaleY(1.1);
    }

    @FXML
    private void reducirBotonReserva(MouseEvent event) {
        botonReserva.setScaleX(1.0);
        botonReserva.setScaleY(1.0);
    }
    
    @FXML
    private void agrandarBotonPedido(MouseEvent event) {
        botonPedido.setScaleX(1.1);
        botonPedido.setScaleY(1.1);
    }

    @FXML
    private void reducirBotonPedido(MouseEvent event) {
        botonPedido.setScaleX(1.0);
        botonPedido.setScaleY(1.0);
    }
    
    
	
	
	
}
