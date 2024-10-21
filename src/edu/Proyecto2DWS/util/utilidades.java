package edu.Proyecto2DWS.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import edu.Proyecto2DWS.controladores.inicioApp;
import edu.Proyecto2DWS.servicios.conexioConPostgresImplementacion;
import edu.Proyecto2DWS.servicios.conexionConMariaDBImplementacion;
import edu.Proyecto2DWS.servicios.conexionInterfaz;

public class utilidades {
		conexionInterfaz ci = new conexionConMariaDBImplementacion();
	
	public long idAutonumericoUsu() {
		int tamanio = inicioApp.listaDeUsuarios.size();
		long id;

		if (tamanio <= 0) {
			id = 1;
		} else {
			id = inicioApp.listaDeUsuarios.get(tamanio - 1).getId_Usu() + 1;
		}
		return id;
	}
	
	/**
	 * Metodo que coge la primera posicion en una query que muestra los elementos de manera desdendente seguin el id y le suma 1 al id
	 * @author jaime - 17/10/24
	 * @return
	 */
	public long idAutonumericoClub() {
		Connection conexion = null;
		PreparedStatement declaraciones = null;
		ResultSet resultados = null;
		String queriConsultaString = "SELECT * FROM rs_motera.club ORDER BY id_club DESC";
		long id;
		try {
			conexion=ci.generaConexion();
			declaraciones=conexion.prepareStatement(queriConsultaString);
			resultados=declaraciones.executeQuery();
			id=resultados.getLong(1) + 1;
			conexion.close();
			declaraciones.close();
			resultados.close();
			return id;
			
		} catch (SQLException e) {
			return 0;
		}
	}
	
	
	
	
	public String encriptacion(String contrasenia) {
		 try {
			   // Crear un objeto MessageDigest para SHA-256
	            MessageDigest cambio = MessageDigest.getInstance("SHA-256");
	            
	            // Convertir la contraseña en bytes y calcular el hash
	            byte[] hash = cambio.digest(contrasenia.getBytes());
	            
	            // Codificar el hash en Base64 y devolverlo como String
	            return Base64.getEncoder().encodeToString(hash);
	            
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException("Error al encriptar la contraseña", e);
	        }
	    }
	}


