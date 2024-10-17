package edu.Proyecto2DWS.servicios;

import java.sql.Connection;

/**
 * Interfaz con el metodo de conexion
 * @author jpribio - 10/10/2024
 */
public interface conexionInterfaz {

	/**
	 * Metodo que genera la conexion y la devuelve
	 * @return
	 */
	public Connection generaConexion();
}
