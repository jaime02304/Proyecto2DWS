package edu.Proyecto2DWS.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.Proyecto2DWS.Dtos.usuarioDtos;
import edu.Proyecto2DWS.controladores.inicioApp;
import edu.Proyecto2DWS.util.sDto;
import edu.Proyecto2DWS.util.utilidades;

public class usuariosImplementacion implements usuariosInterfaz {
	utilidades util = new utilidades();
	conexionInterfaz ci = new conexioConPostgresImplementacion();
	menuInterfaz mi = new menuImplementacion();

	public void cargaInicial() {
		conexionInterfaz ci = new conexioConPostgresImplementacion();

		Connection conexion = null;
		Statement declaracionSQL = null; // Statement sirve para poder hacer la declaracion de la query
		ResultSet resultadoConsulta = null; // resultset sirve para conseguir el resultado de la consulta
		String query = "SELECT * FROM \"rs_motera\".\"usuarios\";";

		try {
			// De genera la conexion
			conexion = ci.generaConexion();

			// Se inicializa el statment y se hace con la conexion un createStatment
			declaracionSQL = conexion.createStatement();

			// Se ejecuta la query y se guarda en el resultset
			resultadoConsulta = declaracionSQL.executeQuery(query);

			// Con esto pasamos de resultset a DTO
			sDto.resultsetAUsuarioDto(resultadoConsulta);
			System.out.println("Carga inicial cargada completamente");

			// Aqui cerramos todo lo que activamos al principio
			conexion.close();
			declaracionSQL.close();
			resultadoConsulta.close();

		} catch (SQLException e) {
			System.err.println("Carga inicial no valida" + e);

		}
	}
	/*--------------------------------------------------------------------------------------------------------------------------*/

	public void darAlta() {

		usuarioDtos usuario = usuDto();
		inicioApp.listaDeUsuarios.add(usuario);

		// A partir de aqui empezaria con la creacion conexion
		Connection conexion = null;
		PreparedStatement declaracionSQLAlta = null;
		ResultSet resultadoSet = null;
		String query = "INSERT INTO rs_motera.usuarios (id_usu,nombre_usu,apellidos_usu,dni,nombre_del_club,email_usu,contrasenia_usu) VALUES (?,?,?,?,?,?,?)";
		try {
			// generar conexion
			conexion = ci.generaConexion();
			// Instanciar el prepareStatment con la query
			declaracionSQLAlta = conexion.prepareStatement(query);
			declaracionSQLAlta.setLong(1, usuario.getId_Usu());
			declaracionSQLAlta.setString(2, usuario.getNombreUsu());
			declaracionSQLAlta.setString(3, usuario.getApellidosUsu());
			declaracionSQLAlta.setString(4, usuario.getdNIUsu());
			declaracionSQLAlta.setString(5, usuario.getNombreClub());
			declaracionSQLAlta.setString(6, usuario.getEmailUsu());
			declaracionSQLAlta.setString(7, usuario.getContraseniaUsu());
			// ejecuto el resultset
			resultadoSet = declaracionSQLAlta.executeQuery();
			System.out.println("El usuario se ha añadido correctamente.");
			// Cierro todo
			conexion.close();
			declaracionSQLAlta.close();
			resultadoSet.close();

		} catch (SQLException e) {
			System.err.println("Ha ocurrido un error al insertar tus datos, por favor intentelo mas tarde." + e);
		}
	}

	/*-------------------------------------*/
	public void eliminarUsu() {
		Connection conexion = null;
		PreparedStatement declaracionSQLEliminar = null;
		ResultSet resultadoSet = null;
		String queriCondicion = "SELECT * FROM rs_motera.usuarios WHERE dni = ?";
		String queryString = "DELETE FROM rs_motera.usuarios WHERE dni = ?";
		try {
			// Pide el DNI
			System.out.println("Dame el DNi");
			String dNIString = inicioApp.sc.next();
			
			conexion = ci.generaConexion();
			declaracionSQLEliminar=conexion.prepareStatement(queriCondicion);
			declaracionSQLEliminar.setString(1, dNIString);
			resultadoSet = declaracionSQLEliminar.executeQuery();
			if(resultadoSet.next()) {
				declaracionSQLEliminar = conexion.prepareStatement(queryString);
				declaracionSQLEliminar.setString(1, dNIString);
				resultadoSet = declaracionSQLEliminar.executeQuery();
				System.out.println("Se ha eliminado al usuario correctamente.");
			}else {
				System.err.println("No se ha encontrao a ningun usuario  con este DNI");
			}
			
			// Cerrar todo
			conexion.close();
			declaracionSQLEliminar.close();
			resultadoSet.close();

		} catch (Exception e) {
			System.err.println("Ha ocurrido un error a la hora de borrar el usuario, intentelo mas tarde por favor" + e);
		}
	}

	/*-------------------------------------*/
	public void modificarUsuario() {
		Connection conexion = null;
		PreparedStatement declaracionSQLModificar = null;
		ResultSet resultadoSet = null;
		String queryCondicion = "SELECT * FROM rs_motera.usuarios WHERE dni = ?";
		String queryModificar;
		Boolean cerrarMenu = false;
		System.out.println("Necesito que me de su DNI para verificar al usuario: ");
		String dniString = inicioApp.sc.next();
		try {
			conexion = ci.generaConexion();
			declaracionSQLModificar = conexion.prepareStatement(queryCondicion);
			declaracionSQLModificar.setString(1, dniString);
			resultadoSet = declaracionSQLModificar.executeQuery();
			if (resultadoSet.next()) {
				do {
					switch (mi.menuUsuario()) {
					case 0:
						cerrarMenu = true;
						break;
					case 1:
						System.out.println("Dame el nuevo nombre para el usario: ");
						String nombreNuevo = inicioApp.sc.next();
						queryModificar = "UPDATE rs_motera.usuarios SET nombre_usu = ? WHERE dni = ?";
						declaracionSQLModificar.setString(1, nombreNuevo);
						declaracionSQLModificar.setString(2, dniString);
						System.out.println("El nombre se ha modificado correctamente");
						break;
					case 2:
						System.out.println("Dame el nuevo apellido (el primero) del usario: ");
						String apellido1 = inicioApp.sc.next();
						System.out.println("Dame el nuevo apellido (el seguno) del usario: ");
						String apellido2 = inicioApp.sc.next();
						queryModificar = "UPDATE rs_motera.usuarios SET apellidos_usu = ? WHERE dni = ?";
						declaracionSQLModificar.setString(1, apellido1.concat(" ").concat(apellido2));
						declaracionSQLModificar.setString(2, dniString);
						System.out.println("El apellido se ha modificado correctamente");
						break;
					case 3:
						System.out.println("Dame el nuevo nombre del club al que pertenece el usario: ");
						String nombreClubNuevo = inicioApp.sc.next();
						queryModificar = "UPDATE rs_motera.usuarios SET nombre_del_club = ? WHERE dni = ?";
						declaracionSQLModificar.setString(1, nombreClubNuevo);
						declaracionSQLModificar.setString(2, dniString);
						System.out.println("El nombre del club al que  pertenece el usuario se ha modificado correctamente");
						break;
					case 4:
						System.out.println("Dame el nuevo nombre para el usario: ");
						String emailNuevo = inicioApp.sc.next();
						queryModificar = "UPDATE rs_motera.usuarios SET email_usu = ? WHERE dni = ?";
						declaracionSQLModificar.setString(1, emailNuevo);
						declaracionSQLModificar.setString(2, dniString);
						System.out.println("El email se ha modificado correctamente");
						break;
					case 5:
						System.out.println("Dame el nuevo nombre para el usario: ");
						String contraString = inicioApp.sc.next();
						String codificada = util.encriptacion(contraString);
						queryModificar = "UPDATE rs_motera.usuarios SET contrasenia_usu = ? WHERE dni = ?";
						declaracionSQLModificar.setString(1, codificada);
						declaracionSQLModificar.setString(2, dniString);
						System.out.println("La contrasenia se ha modificado correctamente");
						break;
					default:
						System.err.println("No has elegido ninguna opcion");
						break;
					}
				} while (!cerrarMenu);

			} else {
				System.err.println("No hay ningun usuario con este DNI.");
			}
			
			//cerrar todo
			conexion.close();
			declaracionSQLModificar.close();
			resultadoSet.close();
		} catch (Exception e) {
			System.err.println("Ha habido un error al modificar un uruario, por favor intentelo de nuevo mas tarde" + e);
		}

	}
	/*---------------------------------------------------------------------------------------------------------------------*/

	/**
	 * Crea el usuario en formato Dto
	 * 
	 * @author jpribio - 17/10/24
	 * @return
	 */
	private usuarioDtos usuDto() {
		System.out.println("A continuacion daras alta a un nuevo usuario");

		// Datos usuario
		long id = util.idAutonumericoUsu();
		System.out.println("Dame el nombre del usuario:");
		String nombreString = inicioApp.sc.next();
		System.out.println("Dame el primer apellido: ");
		String apellido1 = inicioApp.sc.next();
		System.out.println("Dame el segundo apellido: ");
		String apellido2 = inicioApp.sc.next();
		String dNI;
		boolean verificarUsuario;
		do {
			verificarUsuario = true;
			System.out.println("Dame su DNI: ");
			dNI = inicioApp.sc.next();
			for (usuarioDtos usu : inicioApp.listaDeUsuarios) {
				if (usu.getdNIUsu().equals(dNI)) {
					System.out.println("Ya existe un usuario con este DNI.");
					verificarUsuario = false;
					break;
				} 
			}
		} while (!verificarUsuario);
		System.out.println("Dame su email: ");
		String emailString = inicioApp.sc.next();
		String nombreClubString = nombreClub();
		System.out.println("Dame una contraseña");
		String contrasenia = inicioApp.sc.next();
		String contraseniaEncriptada = util.encriptacion(contrasenia);

		usuarioDtos usu = new usuarioDtos(id, nombreString, apellido1.concat(" ").concat(apellido2), dNI,
				nombreClubString, emailString, contraseniaEncriptada);
		return usu;
	}

	/**
	 * Metodo que gestiona la parte de encontrar el nombre del club del usuario
	 * 
	 * @author jpribio - 17/10/24
	 * @return
	 */
	private String nombreClub() {
		Connection conexion = null;
		PreparedStatement declaracion = null;
		ResultSet resultadoSet = null;
		String queryString = "SELECT * FROM rs_motera.club WHERE nombre_usu = ?"; 
		String nombreClubString = "";
		System.out.println("¿Perteneces a algun club? si/no");
		String afirmacion = inicioApp.sc.next();
		if(afirmacion.equalsIgnoreCase("si")) {
			try {
				System.out.println("Dame el nombre del club al que perteneces");
				nombreClubString = inicioApp.sc.next();
				 conexion = ci.generaConexion();
				declaracion = conexion.prepareStatement(queryString);
				declaracion.setString(1, nombreClubString);
				resultadoSet = declaracion.executeQuery();
				
				conexion.close();
				declaracion.close();
				resultadoSet.close();
				
			} catch (SQLException e) {
				System.err.println("Ha ocurrido un error al buscar el nombre del club, por favor intentelo mas tarde" + e);
			}
		}else {
			nombreClubString=" ";
		}
		return nombreClubString;
	}

}
