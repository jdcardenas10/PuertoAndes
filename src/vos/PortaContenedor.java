package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class PortaContenedor extends Buque{

	/**
	 * la capacidad del buque
	 */
	@JsonProperty(value="capacidad")
	private double capacidad;
	
	/**
	 * metodo constructor de la clase portacontenedor
	 * @param id el id del buque
	 * @param nombre el nombre del buque
	 * @param registro el registro del buque
	 * @param agente elñ nombre del agente del buque
	 * @param tiposDeCarga los tipos de carga del buque
	 * @param capacidad la capacidad del buque
	 */
	public PortaContenedor(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="registro") String registro, @JsonProperty(value="agente")String agente, @JsonProperty(value="tiposCarga")List<TipoDeCarga> tiposDeCarga,@JsonProperty(value="capacidad")double capacidad)  
	{
		super(id, nombre, registro, agente, tiposDeCarga);
		this.capacidad = capacidad;
	}

	/**
	 * metodo que retorna la capacidad del portacontenedor
	 * @return
	 */
	public double getCapacidad() {
		return capacidad;
	}
	
	/**
	 * metodo que cambia la capacidad del portacontenedor
	 * @param capacidad
	 */
	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}
	
	
	
	
}
