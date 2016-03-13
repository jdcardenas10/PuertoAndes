package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Entrega extends Operacion{

	
	/**
	 * metodo constructor de la clase entrega
	 * @param id el id de la operacion
	 * @param tipo el tipo de operacion
	 * @param fecha la fecha de la operacion
	 * @param carga la carga relacionada con la operacion
	 */
	public Entrega(@JsonProperty(value="id") int id,@JsonProperty(value="tipo") char tipo,@JsonProperty(value="fecha") Date fecha,@JsonProperty(value="carga") Carga carga) {
		super(id, tipo, fecha, carga);
	}
	
}
