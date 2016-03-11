package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Patio extends Area {
	

	/**
	 * dimension del patio
	 */
	@JsonProperty(value="dimension")
	private double dimension;
	
	
	public Patio(@JsonProperty(value="id") int id, @JsonProperty(value="id") double dimensiones)
	{
		super(id);
		this.dimension= dimensiones;
	}


	/**
	 * metodo que retorna las dimensiones del patio
	 * @return
	 */
	public double getDimension() {
		return dimension;
	}

	/**
	 * metodo que cambia las dimensiones del patio
	 * @param dimension
	 */
	public void setDimension(double dimension) {
		this.dimension = dimension;
	}
	
	

}
