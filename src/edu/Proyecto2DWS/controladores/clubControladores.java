package edu.Proyecto2DWS.controladores;

import edu.Proyecto2DWS.servicios.clubImplementacion;
import edu.Proyecto2DWS.servicios.clubInterfaz;
import edu.Proyecto2DWS.servicios.menuImplementacion;
import edu.Proyecto2DWS.servicios.menuInterfaz;
import edu.Proyecto2DWS.servicios.usuariosImplementacion;
import edu.Proyecto2DWS.servicios.usuariosInterfaz;

public class clubControladores {

	public void procedimientoClub() {
		menuInterfaz mi = new menuImplementacion();
		clubInterfaz clubInterfaz = new clubImplementacion();

		byte opcionMenusesPequenios;
		boolean cerrarMenu = false;

		do {
			System.out.println("------- Menu Club -------");
			switch (opcionMenusesPequenios = mi.menuClub()) {
			case 0:
				System.out.println("A continuacion volvera al menu principal");
				break;
			case 1:
				clubInterfaz.darAltaClub();
				break;
			case 2:
				// Borrar Club
				break;
			case 3:
				// Modificar Club
				break;
			default:
				System.out.println("No has elegido ninguna opcion valida, vuelve a repetirlo.");
				break;

			}

		} while (!cerrarMenu);
	}

}
