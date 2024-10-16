package edu.Proyecto2DWS.servicios;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.Proyecto2DWS.util.sDto;

public class usuariosImplementacion implements usuariosInterfaz {


	public void cargaInicial(String fichero) {
		conexionInterfaz ci = new conexioConPostgresImplementacion();

		Connection conexion = null;
		Statement declaracionSQL = null; // Statement sirve para poder hacer la declaracion de la query
		ResultSet resultadoConsulta = null; // resultset sirve para conseguir el resultado de la consulta
		String query ="SELECT * FROM \"rs_motera\".\"usuarios\"";
		
		try {
			//De genera la conexion
			 conexion = ci.generaConexion(fichero);
			 
			//Se inicializa el statment y se hace con la conexion un createStatment
			declaracionSQL = conexion.createStatement();
			
			//Se ejecuta la query y se guarda en el resultset
			resultadoConsulta = declaracionSQL.executeQuery(query);
			
			//Con esto pasamos de resultset a DTO
			sDto.resultsetAUsuarioDto(resultadoConsulta);
			System.out.println("Carga inicial cargada completamente");	
			
			//Aqui cerramos todo lo que activamos al principio
			conexion.close();
			declaracionSQL.close();
			resultadoConsulta.close();
			
		} catch (SQLException e) {
			System.err.println("Carga inicial no valida" + e);

		}
	}

}
