package edu.Proyecto2DWS.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.Proyecto2DWS.Dtos.clubDtos;
import edu.Proyecto2DWS.controladores.inicioApp;
import edu.Proyecto2DWS.util.utilidades;

public class clubImplementacion implements clubInterfaz {

	conexionInterfaz ci = new conexionConMariaDBImplementacion();
	utilidades util = new utilidades();

	/*-----------------------------------------------------*/
	public void darAltaClub() {
		clubDtos club = DtoClub();
		Connection conexion = null;
		PreparedStatement declaracion = null;
		ResultSet resultado = null;
		String queryString = "INSERT INTO rs_motera.club (id_club, nombre_club, alias_nick_club, numero_de_usu, email_club, contrasenia_club) VALUES (?,?,?,?,?,?)";
		try {
			conexion = ci.generaConexion();
			declaracion = conexion.prepareStatement(queryString);
			declaracion.setLong(1, club.getIdClub());
			declaracion.setString(2, club.getNombreClub());
			declaracion.setString(3, club.getAliasNickClub());
			declaracion.setLong(4, club.getNumeroDeUsuarios());
			declaracion.setString(5, club.getEmailClub());
			declaracion.setString(6, club.getContraseniaClub());
			resultado = declaracion.executeQuery();

			conexion.close();
			declaracion.close();
			resultado.close();
			System.out.println("Se ha añadido el usuario correctamente");
		} catch (SQLException e) {
			System.err.println("Ha habido un error a la hora de añadir un nuevo club, intentelo de nuevo mas tarde" + e);
		}

	}

	/*-----------------------------------------------------*/
	public void eliminarClub() {
		// TODO Auto-generated method stub

	}

	/*-----------------------------------------------------*/
	public void modificarClub() {
		// TODO Auto-generated method stub

	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	private clubDtos DtoClub() {

		Connection conexion = null;
		PreparedStatement declaracion = null;
		ResultSet resultado = null;
		String queryString = "SELECT * FROM rs_motera.club WHERE nombre_club = ?";
		long id = util.idAutonumericoClub();
		String nombreClubString = "";
		try {
			do {
				System.out.println("Dame el nombre del club");
				nombreClubString = inicioApp.sc.next();
				conexion = ci.generaConexion();
				declaracion = conexion.prepareStatement(queryString);
				declaracion.setString(1, nombreClubString);
				resultado = declaracion.executeQuery();
			} while (resultado.next());
			conexion.close();
			declaracion.close();
			resultado.close();
		} catch (Exception e) {
			System.err.println(
					"Ha habido un error a la hora de comprobar si existe el mismo club en la base de datos, por favor vuelve mas tarde." + e);
		}

		System.out.println("Dame las siglas o un alias para referenciar al club:");
		String aliasNickString = inicioApp.sc.next();
		System.out.println("Dame el numero de usuarios que tiene el club:");
		long numeroUsuariLong = inicioApp.sc.nextLong();
		System.out.println("Dame el correo electronico del club:");
		String emailClubString = inicioApp.sc.next();
		System.out.println("Dame la contraseña para el club: ");
		String contraseniaClubString = inicioApp.sc.next();
		String encriptacionString = util.encriptacion(contraseniaClubString);
		clubDtos club = new clubDtos(id, nombreClubString, aliasNickString, numeroUsuariLong, emailClubString,
				encriptacionString);
		return club;

	}

}
