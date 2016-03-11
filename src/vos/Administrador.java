package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Administrador extends Usuario{

	/**
	 * metodo constructor de la clase administrador
	 * @param id el id del usuario
	 * @param nombre el nombre del usuario
	 * @param login el login del usuario
	 * @param clave la clave del usuario
	 */
	public Administrador(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="login") String login, @JsonProperty(value="clave")String clave)  
	{
		super(id, nombre, login, clave);
	}

}
