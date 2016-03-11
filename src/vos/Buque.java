package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Buque {

	/**
	 * Id del buque
	 */
	@JsonProperty(value="id")
	private int id;

	/**
	 * Nombre del buque
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * registro del buque
	 */
	@JsonProperty(value="registro")
	private String registro;
	

	/**
	 * nombre del agente maritimo
	 */
	@JsonProperty(value="agente")
	private String agente;
	
	/**
	 * los tipos de carga del buque
	 */
	@JsonProperty(value="tiposDeCarga")
	private List<TipoDeCarga> tiposCarga;
	
	/**
	 * mwtodo constructor de la clase buque
	 * @param id el id del buque
	 * @param nombre el nombre del buque
	 * @param registro el numero de registro del buque
	 * @param agente el nombre del agente maritimo del buque
	 */
	public Buque(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="registro") String registro, @JsonProperty(value="agente")String agente, @JsonProperty(value="tiposCarga")List<TipoDeCarga> tiposDeCarga) 
	{
		super();
		this.id = id;
		this.nombre = nombre;
		this.registro = registro;
		this.agente = agente;
		this.tiposCarga = tiposDeCarga;
	}

	/**
	 * metodo que retorna el id de la carga
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * metodo que cambia el id de la carga
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * metodo que retorna el nombre de la carga
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * metodo que cambia el nombre de la carga
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * metodo que retorna el registro del buque
	 * @return
	 */
	public String getRegistro() {
		return registro;
	}

	/**
	 * metodo que cambia el registro del buque
	 * @param registro
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
	}

	/**
	 * metodo que retorna el nombre del agente del buque
	 * @return
	 */
	public String getAgente() {
		return agente;
	}

	/**
	 * metodo que cambia el nombre del agente
	 * @param agente
	 */
	public void setAgente(String agente) {
		this.agente = agente;
	}
	
	/**
	 * metodo que retorna los tipos de carga del buque 
	 * @return
	 */
	public List<TipoDeCarga> getTiposCarga() {
		return tiposCarga;
	}

	/**
	 * metodo que cambia los tipos de carga
	 * @param tiposCarga
	 */
	public void setTiposCarga(List<TipoDeCarga> tiposCarga) {
		this.tiposCarga = tiposCarga;
	}
	
	
	
}
