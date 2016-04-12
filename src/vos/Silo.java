package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Silo extends Area{
	
	/**
	 * nombre del silo
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * capacidad del silo
	 */
	@JsonProperty(value="capacidad")
	private double capacidad;

	/**
	 * metodo constructor de la clase silo
	 * @param id, el id del silo
	 * @param nombre, el nombre del silo
	 * @param capacidad,  la capacidad del silo
	 */
	public Silo(@JsonProperty(value="id") int id, @JsonProperty(value="estado") char estado, @JsonProperty(value="tipo") char tipo, @JsonProperty(value="nombre") String nombre,@JsonProperty(value="capacidad") double capacidad,@JsonProperty(value="entradas") List<Operacion>entradas, @JsonProperty(value="salidas") List<Operacion>salidas)

	{
		super(id,estado,tipo,entradas,salidas);
		this.nombre = nombre;
		this.capacidad=capacidad;
	}

	/**
	 * metodo que retorna el nombre del silo
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * metodo que cambia el nombre del silo
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * metodo que da la capacidad del silo
	 * @return
	 */
	public double getCapacidad() {
		return capacidad;
	}

	/**
	 * metodo que cambia la capadicad del silo
	 * @param capacidad
	 */
	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}
	
	
}
