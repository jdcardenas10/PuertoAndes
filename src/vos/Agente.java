package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Agente extends Usuario{
	

	/**
	 * las llegadas de un agente
	 */
	@JsonProperty(value="arribos")
	private List<Arribo> arribos;
	
	/**
	 * las salidas de un agente
	 */
	@JsonProperty(value="salidas")
	private List<Salida> salidas;
	
	/**
	 * metodo constructor de la clase agente
	 * @param id el id del usuario
	 * @param nombre el nombre del usuario
	 * @param login el login del usuario
	 * @param clave la clave del usuario
	 * @param llegadas las llegadas del agente
	 * @param salidas las salidas del agente
	 */
	public Agente(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="login") String login, @JsonProperty(value="clave")String clave,@JsonProperty(value="arribos") List<Arribo> llegadas, @JsonProperty(value="salidas")List<Salida> salidas)
	{
		super(id, nombre, login, clave);
		this.arribos= llegadas;
		this.salidas= salidas;
	}

	/**
	 * metodo que retorna las llegadas de un agente
	 * @return
	 */
	public List<Arribo> getLlegadas() {
		return arribos;
	}

	/**
	 * metodo que cambia las llegadas de un agente
	 * @param llegadas
	 */
	public void setLlegadas(List<Arribo> llegadas) {
		this.arribos = llegadas;
	}

	/**
	 * metodo que retorna las salidas de un agente
	 * @return
	 */
	public List<Salida> getSalidas() {
		return salidas;
	}

	/**
	 * metodo que cambia las salidas de un agente
	 * @param salidas
	 */
	public void setSalidas(List<Salida> salidas) {
		this.salidas = salidas;
	}
	
	
}
