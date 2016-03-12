package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class TipoMaquinaria {

	/**
	 * Id de la maquinaria
	 */
	@JsonProperty(value="id")
	private int id;

	/**
	 * Nombre de la empresa
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	public TipoMaquinaria(@JsonProperty(value="id")int id,@JsonProperty(value="nombre")String nombre){
		this.id=id;
		this.nombre=nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
