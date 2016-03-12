package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class PortaContenedor extends Buque{

	/**
	 * metodo constructor de la clase portacontenedor
	 * @param id el id del buque
	 * @param nombre el nombre del buque
	 * @param registro el registro del buque
	 * @param agente el� nombre del agente del buque
	 * @param tiposDeCarga los tipos de carga del buque
	 * @param capacidad la capacidad del buque
	 */
	public PortaContenedor(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="registro") String registro, @JsonProperty(value="agente")String agente, @JsonProperty(value="tiposCarga")List<TipoDeCarga> tiposDeCarga
			, @JsonProperty(value="capacidad")Double capacidad,@JsonProperty(value="usado")Double usado)  
	{
		super(id, nombre, registro, agente, tiposDeCarga,capacidad,usado);
		//this.capacidad = capacidad;
	}
	
}
