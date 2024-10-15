package edu.Proyecto2DWS.Dtos;

/**
 * Clase donde se encuentra los atrbutos de los usuarios
 * @author jpribio - 10/10/2024
 */
public class usuarioDtos {
	
	long id_Usu;
	String nombreUsu="aaaaa";
	String apellidosUsu="aaaaa";
	String dNIUsu="aaaaa";
	String nombreClub="aaaaa";
	
	public usuarioDtos(long id_Usu, String nombreUsu, String apellidosUsu, String dNIUsu, String nombreClub) {
		super();
		this.id_Usu = id_Usu;
		this.nombreUsu = nombreUsu;
		this.apellidosUsu = apellidosUsu;
		this.dNIUsu = dNIUsu;
		this.nombreClub = nombreClub;
	}

	public usuarioDtos() {
		super();
	}

	public long getId_Usu() {
		return id_Usu;
	}

	public void setId_Usu(long id_Usu) {
		this.id_Usu = id_Usu;
	}

	public String getNombreUsu() {
		return nombreUsu;
	}

	public void setNombreUsu(String nombreUsu) {
		this.nombreUsu = nombreUsu;
	}

	public String getApellidosUsu() {
		return apellidosUsu;
	}

	public void setApellidosUsu(String apellidosUsu) {
		this.apellidosUsu = apellidosUsu;
	}

	public String getdNIUsu() {
		return dNIUsu;
	}

	public void setdNIUsu(String dNIUsu) {
		this.dNIUsu = dNIUsu;
	}

	public String getNombreClub() {
		return nombreClub;
	}

	public void setNombreClub(String nombreClub) {
		this.nombreClub = nombreClub;
	}
	
	
	

}
