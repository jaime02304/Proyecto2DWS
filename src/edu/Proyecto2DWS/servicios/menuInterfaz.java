package edu.Proyecto2DWS.servicios;

/**
 * interfaz donde se encuentra los metodos de la cabecera menu
 * @author jpribio - 9/10/2024
 */
public interface menuInterfaz {
	
	/**
	 * Metodo que muestra el menu principal y devuelve la opcion del usuario
	 * @author jpribio - 9/10/2024
	 * @return
	 */
	public byte menuPrincipal();
	
	/**
	 * Metodo que muestra el menu de usuarios y devuelve la opcion del usuario
	 * @author jpribio - 9/10/2024
	 * @return
	 */
	public byte menuUsuario();
	
	/**
	 * Metodo que muestra el menu del club y devuelve la opcion del usuario
	 * @author jpribio - 9/10/2024
	 * @return
	 */
	public byte menuClub();
	
	
	/**
	 * Menu que enseña lo que se puede modificar del usuario y guarda la respuesta que de el usuario
	 * @author jpribio - 17/10/24
	 * @return
	 */
	public byte menuModificarUsuario();

}
