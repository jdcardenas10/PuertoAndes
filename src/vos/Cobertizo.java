package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Cobertizo extends Area {
	
	/**
	 * dimension del patio
	 */
	@JsonProperty(value="dimension")
	private double dimension;
	
	private TipoDeCarga tipo;
	
	
	public Cobertizo(@JsonProperty(value="id") int id, @JsonProperty(value="dimension") double dimensiones
			, @JsonProperty(value="tipo") TipoDeCarga tipo)
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


	public TipoDeCarga getTipo() {
		return tipo;
	}


	public void setTipo(TipoDeCarga tipo) {
		this.tipo = tipo;
	}
	
	


}
