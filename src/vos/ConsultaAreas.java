package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ConsultaAreas {
		
	/**
	 * Id del area
	 */
	@JsonProperty(value="estado")
	private char estado;
	/**
	 * Id del area
	 */
	@JsonProperty(value="tipo")
	private char tipo;
	
	/**
	 * Id del area
	 */
	@JsonProperty(value="cantidadCargas")
	private int cantidadCargas;
	
	/**
	 * Id del area
	 */
	@JsonProperty(value="cantidadMaxima")
	private int cantidadMaxima;

	public ConsultaAreas(@JsonProperty(value="estado")char estado,@JsonProperty(value="tipo") char tipo, @JsonProperty(value="cantidadCargas")int cantidadCargas,
			@JsonProperty(value="cantidadMaxima")int cantidadMaxima) {
		super();
		this.estado = estado;
		this.tipo = tipo;
		this.cantidadCargas = cantidadCargas;
		this.cantidadMaxima = cantidadMaxima;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public int getCantidadCargas() {
		return cantidadCargas;
	}

	public void setCantidadCargas(int cantidadCargas) {
		this.cantidadCargas = cantidadCargas;
	}

	public int getCantidadMaxima() {
		return cantidadMaxima;
	}

	public void setCantidadMaxima(int cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}

	
	
}
