package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Maquinaria {


	/**
	 * Id de la maquinaria
	 */
	@JsonProperty(value="id")
	private int id;

	/**
	 * tipo de maquinaria
	 */
	@JsonProperty(value="tipo")
	private String tipo;
	

	/**
	 * la capacidad de la maquinaria
	 */
	@JsonProperty(value="capacidad")
	private double capacidad;

	/**
	 * la cantidad de maquinaria
	 */
	@JsonProperty(value="cantidad")
	private int cantidad;
	

	/**
	 * si la maquinaria esta libre o no
	 */
	@JsonProperty(value="libre")
	private boolean libre;

	/**
	 * metodo constructor de la clase maquinaria
	 * @param id el id de la maquinaria
	 * @param tipo el tipo de maquinaria
	 * @param capacidad la capacidad de la maquinaria
	 * @param cantidad la cantidad de maquinaria
	 * @param libre si esta libre o no
	 */
	public Maquinaria(@JsonProperty(value="id") int id,@JsonProperty(value="tipo") String tipo,@JsonProperty(value="capacidad") double capacidad,@JsonProperty(value="cantidad") int cantidad,@JsonProperty(value="libre")boolean libre) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.capacidad = capacidad;
		this.cantidad = cantidad;
		this.libre = libre;
	}

	/**
	 * metodo que retorna el id de la maquinaria
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * metodo que cambia el id de la maquinaria
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * 
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * 
	 * @return
	 */
	public double getCapacidad() {
		return capacidad;
	}

	/**
	 * 
	 * @param capacidad
	 */
	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}

	/**
	 * 
	 * @return
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * 
	 * @param cantidad
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isLibre() {
		return libre;
	}

	/**
	 * 
	 * @param libre
	 */
	public void setLibre(boolean libre) {
		this.libre = libre;
	}
	
	
}
