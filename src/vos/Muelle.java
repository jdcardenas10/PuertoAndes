package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Muelle {

	/**
	 * Id del muelle
	 */
	@JsonProperty(value="id")
	private int id;

	/**
	 * Nombre del muelle
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * metodo constructor de la clase muelle
	 * @param id el id del muelle
	 * @param nombre el nombre del muelle
	 */
	public Muelle(@JsonProperty(value="id") int id, @JsonProperty(value="nombre") String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
