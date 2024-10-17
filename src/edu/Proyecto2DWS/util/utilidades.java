package edu.Proyecto2DWS.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import edu.Proyecto2DWS.controladores.inicioApp;

public class utilidades {

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


