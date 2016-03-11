package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Agente extends Usuario{
	

	/**
	 * las llegadas de un agente
	 */
	@JsonProperty(value="llegadas")
	private List llegadas;
	
	/**
	 * las salidas de un agente
	 */
	@JsonProperty(value="salidas")
	private List salidas;
	
	/**
	 * metodo constructor de la clase agente
	 * @param id el id del usuario
	 * @param nombre el nombre del usuario
	 * @param login el login del usuario
	 * @param clave la clave del usuario
	 * @param llegadas las llegadas del agente
	 * @param salidas las salidas del agente
	 */
	public Agente(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="login") String login, @JsonProperty(value="clave")String clave,@JsonProperty(value="llegadas") List llegadas, @JsonProperty(value="salidas")List salidas)
	{
		super(id, nombre, login, clave);
		this.llegadas= llegadas;
		this.salidas= salidas;
	}

	/**
	 * metodo que retorna las llegadas de un agente
	 * @return
	 */
	public List getLlegadas() {
		return llegadas;
	}

	/**
	 * metodo que cambia las llegadas de un agente
	 * @param llegadas
	 */
	public void setLlegadas(List llegadas) {
		this.llegadas = llegadas;
	}

	/**
	 * metodo que retorna las salidas de un agente
	 * @return
	 */
	public List getSalidas() {
		return salidas;
	}

	/**
	 * metodo que cambia las salidas de un agente
	 * @param salidas
	 */
	public void setSalidas(List salidas) {
		this.salidas = salidas;
	}
	
	
}
