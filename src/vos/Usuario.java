package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usuario {

	/**
	 * Id del usuario
	 */
	@JsonProperty(value="id")
	private int id;

	/**
	 * Nombre del usuario
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * login del usuario
	 */
	@JsonProperty(value="login")
	private String login;
	

	/**
	 * nombre del agente maritimo
	 */
	@JsonProperty(value="clave")
	private String clave;
	
	/**
	 * metodo constructor de la clase usuario
	 * @param id el id del usuario
	 * @param nombre el nombre del usuario
	 * @param login el login del usuario
	 * @param clave la clave del usuario
	 */
	public Usuario(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="login") String login, @JsonProperty(value="clave")String clave) 
	{
		super();
		this.id = id;
		this.nombre = nombre;
		this.login= login;
		this.clave=clave;
	}

	/**
	 * metodo que retorna el id del usuario
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * metodo que cambia el id del usuario
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/***
	 * metodo que retorna el nombre del usuario
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * metodo que cambia el nombre del usuario
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * metodo que retorna el login del usuario
	 * @return
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * metodo que cambia el login del usuario
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * metodo que retorna la clave del usuario
	 * @return
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * metodo que cambia la clave del usuario
	 * @param clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
}
