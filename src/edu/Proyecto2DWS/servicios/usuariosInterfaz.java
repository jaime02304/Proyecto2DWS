package edu.Proyecto2DWS.servicios;

public interface usuariosInterfaz {

	/**
	 * Carga inicial donde recoge todos los datos de los usuarios
	 * 
	 * @author jpribio - 17/10/24
	 */
	public void cargaInicial();

	/**
	 * Metodo que da alta a los usuarios
	 * 
	 * @author jpribio - 17/10/24
	 */
	public void darAlta();

	/**
	 * Metodo que da de baja a un usuario
	 * 
	 * @author jpribio - 17/10/24
	 */
	public void eliminarUsu();

	/**
	 * Metodo que puede modificar segun el dni del usuario
	 * 
	 * @author jpribio - 17/10/24
	 */
	public void modificarUsuario();

}
