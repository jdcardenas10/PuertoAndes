package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Operacion {

	/**
	 * Id de la operacion
	 */
	@JsonProperty(value="id")
	private int id;
	
	/**
	 * tipo de la operacion
	 */
	@JsonProperty(value="tipo")
	private char tipo;
	
	/**
	 * la fecha de la operacion
	 */
	@JsonProperty(value="fecha")
	private Date fecha;
	
	/**
	 * la carga involucrada en la operacion
	 */
	@JsonProperty(value="carga")
	private Carga carga;

	public Operacion(@JsonProperty(value="id") int id,@JsonProperty(value="tipo") char tipo,@JsonProperty(value="fecha") Date fecha,@JsonProperty(value="carga") Carga carga) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.fecha = fecha;
		this.carga = carga;
	}

	/**
	 * metodo que retorna el id de la operacion
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public char getTipo() {
		return tipo;
	}

	/**
	 * 
	 * @param tipo
	 */
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * 
	 * @param fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * 
	 * @return
	 */
	public Carga getCarga() {
		return carga;
	}

	/**
	 * 
	 * @param carga
	 */
	public void setCarga(Carga carga) {
		this.carga = carga;
	}
	
}
