package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Operador extends Usuario{
	
	/**
	 * las facturas hechas al exportador
	 */
	@JsonProperty(value="cargues")
	private List cargues;
	
	/**
	 * las facturas hechas al exportador
	 */
	@JsonProperty(value="descargues")
	private List descargues;

	/**
	 * metodo constructor de la clase operador
	  * @param id el id del usuario
	 * @param nombre el nombre del usuario
	 * @param login el login del usuario
	 * @param clave la clave del usuario
	 * @param cargues los cargues del operador
	 * @param descargues los descargues del operador
	 */
	public Operador(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="login") String login, @JsonProperty(value="clave")String clave,@JsonProperty(value="cargues") List cargues, @JsonProperty(value="descargues")List descargues)
	{
		super(id, nombre, login, clave);
		this.cargues = cargues;
		this.descargues= descargues;
	}

	/**
	 * metodo que retorna los cargues del operador
	 * @return
	 */
	public List getCargues() {
		return cargues;
	}

	/**
	 * metodo que cambia los cargues de un operador
	 * @param cargues
	 */
	public void setCargues(List cargues) {
		this.cargues = cargues;
	}

	/**
	 * metodo que retorna los descargues de un operador
	 * @return
	 */
	public List getDescargues() {
		return descargues;
	}

	/**
	 * metodo que cambia los descargues de un operador
	 * @param descargues
	 */
	public void setDescargues(List descargues) {
		this.descargues = descargues;
	}
	
	

}
