package edu.Proyecto2DWS.util;

import java.sql.ResultSet;

import edu.Proyecto2DWS.Dtos.usuarioDtos;
import edu.Proyecto2DWS.controladores.inicioApp;

/**
 * Clase de utilidad que tiene el pado a Dtos
 */
public class sDto {

	/**
	 * Este es el metodo que pasa de resultset a Dto
	 * @param resultadoResultSet
	 */
	public static void resultsetAUsuarioDto(ResultSet resultadoResultSet) {

		try {
			while (resultadoResultSet.next() /*Aqui lee el contenido y devuelve true si hay contenido*/) {
				//Se crea un nuevo Dto y se añade a la lista
				inicioApp.listaDeUsuarios.add(new usuarioDtos(resultadoResultSet.getLong("id_usu"),
						resultadoResultSet.getString("nombre_usu"), resultadoResultSet.getString("apellidos_usu"),
						resultadoResultSet.getString("dni"), resultadoResultSet.getString("nombre_del_club")));
			}

		} catch (Exception e) {
			System.err.println("[ERROR-ADto-resultsALibrosDto] Error al pasar el result set a lista de usuarioDtos" + e);

		}
	}

}
