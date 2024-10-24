package edu.Proyecto2DWS.servicios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * Implementacion que implementa la interfaz de conexion
 * 
 * @author jpribio - 10/10/2024
 */

public class conexionConMariaDBImplementacion implements conexionInterfaz {

	@Override
	public Connection generaConexion() {
		// Cambiar la ruta
		String fichero = "C:\\Users\\jpribio\\DesarrolloWebServicio\\Proyecto2DWS\\src\\edu\\Proyecto2DWS\\util\\respuestaConexion.txt";
		LocalDateTime fechaInstante = LocalDateTime.now();

		Connection conexion = null;
		String[] parametrosDeConexxion = configuraConexion();// Aqui se pone la url,el usuario y la contraseña

		if (!parametrosDeConexxion[2].isEmpty()) {
			try {
				PrintWriter escritor = new PrintWriter(new FileWriter(fichero, true));
				// Esto se necesita para utilizar el driver de postgres y el texto se puede
				// sacar con ayuda de internet
				Class.forName("org.mariadb.jdbc.Driver");

				// Con esto se hace la conexion a la BDD
				conexion = DriverManager.getConnection(parametrosDeConexxion[0], parametrosDeConexxion[1],
						parametrosDeConexxion[2]);

				// Ahora se valida para saber que no da errores
				boolean esValida = conexion.isValid(50000); // isValid da true si se conecta en menos del tiempo
															// necesario

				// Si da falso porque no se haya conectado
				if (esValida == false) {
					conexion = null;
					System.out.println("No hay conexion");
				}

				// Aqui pondra si es valida es verdadero escribe la primera opcion sin embargo
				// si es false se pondra la 2º opcion
				escritor.println((esValida
						? "[INFORMACIÓN-ConexionPostgresqlImplementacion-generaConexion] Conexión a MariaDB válida"
								+ fechaInstante
						: "[ERROR-ConexionPostgresqlImplementacion-generaConexion] Conexión a MariaDB no válida"
								+ fechaInstante));
				escritor.close();
				// Esta parte es por si da un error de cada clase
			} catch (ClassNotFoundException cnfe) {
				System.err.println(
						"[ERROR-ConexionConMariaDBlImplementacion-configuracionConexion] Error en registro driver Mariadb: "
								+ parametrosDeConexxion[0] + "): " + cnfe);
				conexion = null;
			} catch (SQLException sqle) {
				System.err.println(
						"[ERROR-ConexionConMariaDBlImplementacion-configuracionConexion] Error en conexión a MariaDb ("
								+ parametrosDeConexxion[0] + "): " + sqle);
				conexion = null;
			} catch (IOException ioe) {
				System.err.println(
						"[ERROR-ConexionConMariaDBlImplementacion-configuracionConexion] Error en la escritura a fichero ("
								+ parametrosDeConexxion[0] + "): " + ioe);
				conexion = null;
			}
		}
		return conexion;
	}

	/**
	 * Este metodo privado realiza la conexio de los parametros y obtiene un arra de
	 * strring y obtiene la url,el usuario y la contraseña
	 * 
	 * @return
	 */
	private String[] configuraConexion() {
		String usuario = "", contrasenia = "", puerto = "", host = "", db = "", url = "";
		String[] parametrosConfiguracion = { "", "", "" };// usr,usuario,contraseña

		Properties propiedades = new Properties(); // Aqui se instancia una clase propierties

		try {
			// Aqui se coge los dato del fichero de propiedades
			// cambiar la ruta
			propiedades.load(new FileInputStream(
					"C:\\Users\\jpribio\\DesarrolloWebServicio\\Proyecto2DWS\\src\\edu\\Proyecto2DWS\\util\\datos.properties"));

			// aqui se coge las variables y se le asigna los datos que se encuentran en el
			// fichero de propiedades
			usuario = propiedades.getProperty("usuario");
			contrasenia = propiedades.getProperty("contrasenia");
			puerto = propiedades.getProperty("puerto");
			host = propiedades.getProperty("host");
			db = propiedades.getProperty("db");
			url = propiedades.getProperty("url");

			// Aqui escogemos lo que nos interesa y le damos el valor necesario en las
			// posiciones deseadas para despues hacer la conexion
			parametrosConfiguracion[0] = url;
			parametrosConfiguracion[1] = usuario;
			parametrosConfiguracion[2] = contrasenia;

		} catch (FileNotFoundException e) {

			System.err.println(
					"[ERROR-ConexionConMariaDBlImplementacion-configuracionConexion] - Error al acceder al fichero propiedades de conexion.");

			// Como ha dado un error damos un valor por defecto para que podamos controlarlo
			// luego
			parametrosConfiguracion[0] = "";
			parametrosConfiguracion[1] = "";
			parametrosConfiguracion[2] = "";
		} catch (IOException e) {
			System.err.println(
					"[ERROR-ConexionConMariaDBlImplementacion-configuracionConexion] - Error al acceder al fichero propiedades de conexion.");
			parametrosConfiguracion[0] = "";
			parametrosConfiguracion[1] = "";
			parametrosConfiguracion[2] = "";
		}
		return parametrosConfiguracion;
	}

}
