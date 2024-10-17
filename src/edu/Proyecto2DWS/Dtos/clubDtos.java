package edu.Proyecto2DWS.Dtos;

/**
 * Clase donde se encuentra los dtos de los clubs
 * 
 * @author jaime - 17/10/24
 */
public class clubDtos {

	long idClub;
	String nombreClub = "aaaaa";
	long numeroDeUsuarios = -1;
	String emailClub = "aaaaa";
	String contraseniaClub = "aaaaa";
	String aliasNickClub = "aaaaa";

	public long getIdClub() {
		return idClub;
	}

	public void setIdClub(long idClub) {
		this.idClub = idClub;
	}

	public String getNombreClub() {
		return nombreClub;
	}

	public void setNombreClub(String nombreClub) {
		this.nombreClub = nombreClub;
	}

	public long getNumeroDeUsuarios() {
		return numeroDeUsuarios;
	}

	public void setNumeroDeUsuarios(long numeroDeUsuarios) {
		this.numeroDeUsuarios = numeroDeUsuarios;
	}

	public String getEmailClub() {
		return emailClub;
	}

	public void setEmailClub(String emailClub) {
		this.emailClub = emailClub;
	}

	public String getContraseniaClub() {
		return contraseniaClub;
	}

	public void setContraseniaClub(String contraseniaClub) {
		this.contraseniaClub = contraseniaClub;
	}

	public String getAliasNickClub() {
		return aliasNickClub;
	}

	public void setAliasNickClub(String aliasNickClub) {
		this.aliasNickClub = aliasNickClub;
	}

	public clubDtos(long idClub, String nombreClub,String aliasNickClub, long numeroDeUsuarios, String emailClub, String contraseniaClub) {
		super();
		this.idClub = idClub;
		this.nombreClub = nombreClub;
		this.numeroDeUsuarios = numeroDeUsuarios;
		this.emailClub = emailClub;
		this.contraseniaClub = contraseniaClub;
		this.aliasNickClub = aliasNickClub;
	}

	public clubDtos() {
		super();
	}

}
