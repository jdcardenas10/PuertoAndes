package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Carga {

	/**
	 * Id de la carga
	 */
	@JsonProperty(value="id")
	private int id;

	/**
	 * Nombre de la carga
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * peso de la carga
	 */
	@JsonProperty(value="peso")
	private double peso;
	

	/**
	 * Estado de la carga
	 */
	@JsonProperty(value="estado")
	private char estado;

	/**
	 * dias en el puerto
	 */
	@JsonProperty(value="diasEnPuerto")
	private int diasEnPuerto;
	
	/**
	 * tipo de carga de la carga
	 */
	@JsonProperty(value="tipoCarga")
	private TipoDeCarga tipoCarga;
	
	private int area;

	/**
	 * metodo constructor de la clase carga
	 * @param id el id de la carga
	 * @param nombre el nombre de la carga
	 * @param peso el peso de la carga
	 * @param estado el estado de la carga
	 * @param diasEnPuerto los dias en el puerto de la carga
	 */
	public Carga(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="peso") double peso, @JsonProperty(value="estado")char estado,@JsonProperty(value="diasEnPuerto") int diasEnPuerto,@JsonProperty(value="tipoCarga") TipoDeCarga tipoCarga) 
	{
		super();
		this.id = id;
		this.nombre = nombre;
		this.peso = peso;
		this.estado = estado;
		this.diasEnPuerto = diasEnPuerto;
		this.setTipoCarga(tipoCarga);
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
	 * metodo que retorna el peso de la carga
	 * @return
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * metodo que cambia el peso de la carga
	 * @param peso
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}

	/**
	 * metodo que retorna el estado de la carga
	 * @return
	 */
	public char getEstado() {
		return estado;
	}

	/**
	 * metodo que cambia el estado de la carga
	 * @param estado
	 */
	public void setEstado(char estado) {
		this.estado = estado;
	}

	/**
	 * metodo que retorna los dias en el puerto de la carga
	 * @return
	 */
	public int getDiasEnPuerto() {
		return diasEnPuerto;
	}

	/**
	 * metodo que cambia los dias en el puerto de la carga
	 * @param diasEnPuerto
	 */
	public void setDiasEnPuerto(int diasEnPuerto) {
		this.diasEnPuerto = diasEnPuerto;
	}

	public TipoDeCarga getTipoCarga() {
		return tipoCarga;
	}

	public void setTipoCarga(TipoDeCarga tipoCarga) {
		this.tipoCarga = tipoCarga;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}
	
}
