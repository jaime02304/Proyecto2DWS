package edu.Proyecto2DWS.Dtos;

/**
 * Clase donde se encuentra los atrbutos de los usuarios
 * 
 * @author jpribio - 10/10/2024
 */
public class usuarioDtos {

	long id_Usu;
	String nombreUsu = "aaaaa";
	String apellidosUsu = "aaaaa";
	String dNIUsu = "aaaaa";
	String nombreClub = "aaaaa";
	String emailUsu = "aaaaa";
	String contraseniaUsu = "aaaaa";

	public usuarioDtos(long id_Usu, String nombreUsu, String apellidosUsu, String dNIUsu, String nombreClub,
			String emailUsu, String contraseniaUsu) {
		super();
		this.id_Usu = id_Usu;
		this.nombreUsu = nombreUsu;
		this.apellidosUsu = apellidosUsu;
		this.dNIUsu = dNIUsu;
		this.nombreClub = nombreClub;
		this.emailUsu = emailUsu;
		this.contraseniaUsu = contraseniaUsu;
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

	public String getEmailUsu() {
		return emailUsu;
	}

	public void setEmailUsu(String emailUsu) {
		this.emailUsu = emailUsu;
	}

	public String getContraseniaUsu() {
		return contraseniaUsu;
	}

	public void setContraseniaUsu(String contraseniaUsu) {
		this.contraseniaUsu = contraseniaUsu;
	}

}
