package edu.Proyecto2DWS.controladores;

import edu.Proyecto2DWS.servicios.menuImplementacion;
import edu.Proyecto2DWS.servicios.menuInterfaz;
import edu.Proyecto2DWS.servicios.usuariosImplementacion;
import edu.Proyecto2DWS.servicios.usuariosInterfaz;

public class usuarioControladores {

	public void procedimientoUsuarios() {


		menuInterfaz mi = new menuImplementacion();
		usuariosInterfaz usu = new usuariosImplementacion();

		byte opcionMenusesPequenios;
		boolean cerrarMenu = false;

		do {
			System.out.println("------- Menu Usuario -------");
			switch (opcionMenusesPequenios = mi.menuUsuario()) {
			case 0:
				System.out.println("A continuacion volvera al menu principal");
				cerrarMenu=true;
				break;
			case 1:
				usu.darAlta();
				break;
			case 2:
				// Borrar usuario
				break;
			case 3:
				// Modificar usuario
				break;
			default:
				System.out.println("No has elegido ninguna opcion valida, vuelve a intentarlode nuevo.");
				break;
			}

		} while (!cerrarMenu);

	}

}
