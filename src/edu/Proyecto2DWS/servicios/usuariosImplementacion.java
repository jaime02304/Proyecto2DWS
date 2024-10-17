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
		String query = "INSERT INTO rs_motera.usuarios (id_usu,nombre_usu,apellidos_usu,dni,nombre_del_club,email_usu,contrasenia_usu) VALUES (?,?,?,?,?,?,?);";
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

			// Cierro todo
			conexion.close();
			declaracionSQLAlta.close();
			resultadoSet.close();

		} catch (SQLException e) {
			System.err.println("Ha ocurrido un error al insertar tus datos, por favor intentelo mas tarde.");
		}
	}

	/*-------------------------------------*/
	public void eliminarUsu() {
		Connection conexion = null;
		PreparedStatement declaracionSQLEliminar = null;
		ResultSet resultadoSet = null;
		String queryString = "DELETE FROM rs_motera.usuarios WHERE dni = ?";
		try {
			// Pide el DNI
			System.out.println("Dame el DNi");
			String dNIString = inicioApp.sc.next();
			// se instancia toda para ejecutar la consulta
			conexion = ci.generaConexion();
			declaracionSQLEliminar = conexion.prepareStatement(queryString);
			declaracionSQLEliminar.setString(1, dNIString);
			resultadoSet = declaracionSQLEliminar.executeQuery();

			// Cerrar todo
			conexion.close();
			declaracionSQLEliminar.close();
			resultadoSet.close();

		} catch (Exception e) {
			System.err.println("Ha ocurrido un error a la hora de borrar el usuario, intentelo mas tarde por favor");
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
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
					case 5:
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
			// TODO: handle exception
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
		boolean verificarUsuario = false;
		do {
			System.out.println("Dame su DNI: ");
			dNI = inicioApp.sc.next();
			for (usuarioDtos usu : inicioApp.listaDeUsuarios) {
				if (usu.getdNIUsu().equals(dNI)) {
					System.out.println("Ya existe un usuario con este DNI.");
					verificarUsuario = false;
				} else {
					verificarUsuario = true;
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
		try {
			String nombreClubString = "";
//			do {
//				System.out.println("¿Se encuentra en algun club? si/no");
//				String afirmacionString = inicioApp.sc.next();
//				if(afirmacionString.equals("si") & listaClub.size()>=1) {
//					System.out.println("¿En cual club se encuentra? Deme el nombre del club: ");
//					for(clubDtos club : listaClub) {
//						if(club.nombreClub.equals(nombreClubString)) {
//							nombreClubString=inicioApp.sc.next();
//						}else {
//							System.err.println("NO se ha encontrado ningun club con ese nombre, por favor intentelo de nuevo");
//							nombreClubString="a";
//						}
//					}
//				}else {
//					nombreClubString="";
//				}
//			} while (nombreClubString.equals("a"));
			return nombreClubString;
		} catch (Exception e) {
			System.err.println("ha ocurrido un error al buscar el nombre del club, por favor intentelo mas tarde");
			return "";
		}
	}

}
