package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class TipoDeCarga {
	

	/**
	 * Id del tipo de carga
	 */
	@JsonProperty(value="id")
	private int id;

	/**
	 * Nombre del tipo de carga
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * metodo constructor de la clase tipo de carga
	 * @param id el id de la carga
	 * @param nombre el nombre de la carga
	 */
	public TipoDeCarga(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String nombre) 
	{
		super();
		this.id = id;
		this.nombre = nombre;
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
}
