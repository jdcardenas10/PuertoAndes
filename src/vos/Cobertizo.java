package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Cobertizo extends Area {
	
	/**
	 * dimension del patio
	 */
	@JsonProperty(value="dimension")
	private double dimension;
	
	@JsonProperty(value="tipoDeCarga")
	private TipoDeCarga tipoDeCarga;
	
	
	public Cobertizo(@JsonProperty(value="id") int id, @JsonProperty(value="estado") char estado, @JsonProperty(value="tipo") char tipo, @JsonProperty(value="dimension") double dimensiones
			, @JsonProperty(value="tipoDeCarga") TipoDeCarga tipoDeCarga,@JsonProperty(value="entradas") List<Operacion>entradas, @JsonProperty(value="salidas") List<Operacion>salidas)
	{
		super(id,estado,tipo,entradas,salidas);
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
		this.tipoDeCarga = tipo;
	}
	
	


}
