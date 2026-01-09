package Controladores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DAO.MenuDAO;
import DTO.MenuDTO;
import RestauranteBD.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControladorGestionMenu {

    @FXML
    private Label labelAdministrador;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private ImageView volver;
    @FXML
    private TableView<MenuDTO> tablaMenu; 

    @FXML
    private TableColumn<MenuDTO, String> colNombre; 
    @FXML
    private TableColumn<MenuDTO, String> colDescripcion; 
    @FXML
    private TableColumn<MenuDTO, Double> colPrecio; 

    private ObservableList<MenuDTO> listaMenu;

    private MenuDAO menuDAO;

    public ControladorGestionMenu() {
        try {
            menuDAO = new MenuDAO(DBConnection.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        // Configurar las columnas de la tabla
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colDescripcion.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
        colPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());
        
        // Cargar los datos en la tabla
        cargarMenu();
    }

    // Método para cargar los productos del menú desde la base de datos
    private void cargarMenu() {
        try {
            List<MenuDTO> productos = menuDAO.obtenerTodos();
            listaMenu = FXCollections.observableArrayList(productos);
            tablaMenu.setItems(listaMenu);
        } catch (SQLException e) {
            mostrarAlerta("Error al cargar los productos del menú.");
            e.printStackTrace();
        }
    }

    // Mostrar alerta
    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    
    // Método para abrir vista para agregar
    @FXML
    private void VistaBtnAgregar(ActionEvent event) {
        try {
            // Cargar la vista desde el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaFormularioMenu.fxml"));
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
    
    //metodo abrir vista para modificar
    @FXML
    private void modificarProducto() throws IOException {
        MenuDTO productoSeleccionado = tablaMenu.getSelectionModel().getSelectedItem(); // Obtén el producto seleccionado de la tabla

        if (productoSeleccionado != null) {
            // Cargar la vistaFormularioMenu
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaFormularioMenu.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la nueva vista
            ControladorFormularioMenu controlador = loader.getController();
            controlador.setProductoSeleccionado(productoSeleccionado);  // Pasar el producto a modificar

            // Mostrar la nueva vista
            Stage stage = (Stage) tablaMenu.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            mostrarMensaje("Error", "Por favor, selecciona un producto para modificar.", AlertType.ERROR);
        }
    }

    //metodo eliminar producto
    @FXML
    private void eliminarProducto() throws SQLException {
        MenuDTO productoSeleccionado = tablaMenu.getSelectionModel().getSelectedItem(); // Obtén el producto seleccionado de la tabla

        if (productoSeleccionado != null) {
            // Obtener la conexión y llamar al DAO para eliminar el producto
            try (Connection conexion = DBConnection.getConnection()) {
                MenuDAO menuDAO = new MenuDAO(conexion);
                menuDAO.eliminar(productoSeleccionado.getIdProducto());  // Eliminar producto usando su ID

                // Actualizar la tabla después de la eliminación
                tablaMenu.getItems().remove(productoSeleccionado);

                mostrarMensaje("Éxito", "Producto eliminado con éxito.", AlertType.INFORMATION);
            } catch (SQLException e) {
                e.printStackTrace();
                mostrarMensaje("Error de base de datos", "Error al eliminar el producto.", AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Error", "Por favor, selecciona un producto para eliminar.", AlertType.ERROR);
        }
    }

    //metodo mostrar errores
    public void mostrarMensaje(String titulo, String mensaje, AlertType tipo) {
        // Crear una alerta del tipo especificado (error, información, advertencia)
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);          // Título de la alerta
        alert.setHeaderText(null);       // No ponemos encabezado (puedes personalizarlo si lo deseas)
        alert.setContentText(mensaje);  // El mensaje que se mostrará en la alerta

        // Mostrar la alerta y esperar la respuesta del usuario
        alert.showAndWait();
    }


    
    // Método para establecer el nombre del administrador
    public void setNombreAdministrador(String nombre) {
        // Actualizar el texto del label con el nombre del administrador
        labelAdministrador.setText("       BIENVENIDO, " + nombre);
    }
    
    
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
	
	  
	    
	    
	    //Agrandar disminuir boton agregar
	    @FXML
	    private void agrandarBotonAgregar(MouseEvent event) {
	    	btnAgregar.setScaleX(1.1);
	    	btnAgregar.setScaleY(1.1);
	    }

	    @FXML
	    private void reducirBotonAgregar(MouseEvent event) {
	    	btnAgregar.setScaleX(1.0);
	        btnAgregar.setScaleY(1.0);
	    }
	    
	    

	    //Agrandar disminuir boton Modificar
	    @FXML
	    private void agrandarBotonModificar(MouseEvent event) {
	    	btnModificar.setScaleX(1.1);
	    	btnModificar.setScaleY(1.1);
	    }

	    @FXML
	    private void reducirBotonModificar(MouseEvent event) {
	    	btnModificar.setScaleX(1.0);
	        btnModificar.setScaleY(1.0);
	    }
	    
	    
	    
	    //Agrandar disminuir boton Eliminar
	    @FXML
	    private void agrandarBotonEliminar(MouseEvent event) {
	    	btnEliminar.setScaleX(1.1);
	    	btnEliminar.setScaleY(1.1);
	    }

	    @FXML
	    private void reducirBotonEliminar(MouseEvent event) {
	    	btnEliminar.setScaleX(1.0);
	    	btnEliminar.setScaleY(1.0);
	    }
	    
	    
	 
	    
	
	
	
}
