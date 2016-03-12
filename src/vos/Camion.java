package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Camion {

	/**
	 * Id de la maquinaria
	 */
	@JsonProperty(value="id")
	private int id;

	/**
	 * la capacidad de la maquinaria
	 */
	@JsonProperty(value="capacidad")
	private double capacidad;
	
	public Camion(@JsonProperty(value="id") int id, @JsonProperty(value="capacidad") Double capacidad){
		this.id=id;
		this.capacidad=capacidad;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public double getCapacidad() {
		return capacidad;
	}


	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}

}
