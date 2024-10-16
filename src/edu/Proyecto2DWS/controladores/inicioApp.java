package edu.Proyecto2DWS.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.Proyecto2DWS.Dtos.usuarioDtos;
import edu.Proyecto2DWS.servicios.menuImplementacion;
import edu.Proyecto2DWS.servicios.menuInterfaz;
import edu.Proyecto2DWS.servicios.usuariosImplementacion;
import edu.Proyecto2DWS.servicios.usuariosInterfaz;

public class inicioApp {

	public static List<usuarioDtos> listaDeUsuarios = new ArrayList<usuarioDtos>();
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		 String fichero = "C:\\Users\\jpribio\\DesarrolloWebServicio\\Proyecto2DWS\\src\\edu\\Proyecto2DWS\\util\\respuestaConexion.txt";

		 
		menuInterfaz mi = new menuImplementacion();
		usuariosInterfaz usu = new usuariosImplementacion();
		usuarioControladores usuC = new usuarioControladores();
		clubControladores clubC  = new clubControladores();
		
		//Cargas iniciales
		usu.cargaInicial(fichero);

		byte opcionMenu;
		byte opcionMenusesPequenios;
		boolean cerrarMenu = false;

		try {
			System.out.println("Bienvenido a la aplicacion del registro de Usuarios y Clubes");
			do {
				switch (opcionMenu = mi.menuPrincipal()) {
				case 0:
					cerrarMenu = true;
					break;
				case 1:
					usuC.procedimientoUsuarios();
					break;
				case 2:
					clubC.procedimientoClub();
					break;
				default:
					System.out.println(
							"No has elegido ninguna opcion correcta, por favor intente elegir una opcion valida");
					break;
				}

			} while (!cerrarMenu);
		} catch (Exception e) {
			System.err.println("Ha ocurrido un error en la aplicacion, vuelve a intentarlo mas tarde");
		}
	}

}
