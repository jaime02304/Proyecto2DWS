package edu.Proyecto2DWS.servicios;

import edu.Proyecto2DWS.controladores.inicioApp;

/**
 * Implementacion qque implementa la interfaz del menu
 * @author jpribio - 9/10/2024
 */
public class menuImplementacion implements menuInterfaz {

	public byte menuPrincipal() {
		System.out.println("Elija una opcion por favor");
		System.out.println("Opcion 0. Cerrar menu");
		System.out.println("Opcion 1. Menu Usuarios");
		System.out.println("Opcion 2. Menu Clubes");
		byte opcion = inicioApp.sc.nextByte();
		return opcion;
	}

	public byte menuUsuario() {
		System.out.println("Elija una opcion por favor");
		System.out.println("Opcion 0. Volver al menu principal");
		System.out.println("Opcion 1. Registrar usuario");
		System.out.println("Opcion 2. Eliminar Usuario");
		System.out.println("Opcion 3. Modificar Usuario");
		byte opcion = inicioApp.sc.nextByte();
		return opcion;
	}

	@Override
	public byte menuClub() {
		System.out.println("Elija una opcion por favor");
		System.out.println("Opcion 0. Volver al menu principal");
		System.out.println("Opcion 1. Registrar Club");
		System.out.println("Opcion 2. Eliminar Club");
		System.out.println("Opcion 3. Modificar Club");
		byte opcion = inicioApp.sc.nextByte();
		return opcion;
	}

}
