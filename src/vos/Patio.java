package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Patio extends Area {
	

	/**
	 * dimension del patio
	 */
	@JsonProperty(value="dimension")
	private double dimension;
	
	@JsonProperty(value="tipoDeCarga")
	private TipoDeCarga tipoDeCarga;
	
	
	public Patio(@JsonProperty(value="id") int id, @JsonProperty(value="estado") char estado, @JsonProperty(value="tipo") char tipo, @JsonProperty(value="dimension") double dimensiones,@JsonProperty(value="tipoDeCarga") TipoDeCarga tipoDeCarga)
	{
		super(id,estado,tipo);
		this.dimension= dimensiones;
		this.tipoDeCarga=tipoDeCarga;
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


	public TipoDeCarga getTipoDeCarga() {
		return tipoDeCarga;
	}


	public void setTipoDeCarga(TipoDeCarga tipo) {
		this.tipoDeCarga = tipoDeCarga;
	}
	
	

}
