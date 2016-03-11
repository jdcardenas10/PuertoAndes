package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Area {

	/**
	 * Id del area
	 */
	@JsonProperty(value="id")
	private int id;
	
	/**
	 * metodo constructor de la clase area
	 * @param id el id del area
	 */
	public Area(@JsonProperty(value="id") int id)
	{
		this.id=id;
	}

	/**
	 * metodo que retorna el id del area
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * metodo que cambia el id del area
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
}
