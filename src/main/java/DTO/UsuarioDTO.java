package DTO;

import RestauranteBD.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDTO {

	    private int id;
	    private String nombre;
	    private String apellido;
	    private String telefono;
	    private String email;
	    private String contrasena;
	    private String tipo;

	    // Constructor
	    public UsuarioDTO() {
	        this.id = id;
	        this.nombre = nombre;
	        this.apellido = apellido;
	        this.telefono = telefono;
	        this.email = email;
	        this.contrasena = contrasena;
	        this.tipo = tipo;
	    }

	    // Getters y setters
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getApellido() {
	        return apellido;
	    }

	    public void setApellido(String apellido) {
	        this.apellido = apellido;
	    }

	    public String getTelefono() {
	        return telefono;
	    }

	    public void setTelefono(String telefono) {
	        this.telefono = telefono;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getContrasena() {
	        return contrasena;
	    }

	    public void setContrasena(String contrasena) {
	        this.contrasena = contrasena;
	    }

	    public String getTipo() {
	        return tipo;
	    }

	    public void setTipo(String tipo) {
	        this.tipo = tipo;
	    }
	
	
	
	
}


