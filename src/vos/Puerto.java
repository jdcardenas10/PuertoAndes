package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Puerto {

	/**
	 * nombre del silo
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * nombre del silo
	 */
	@JsonProperty(value="pais")
	private String pais;
	
	/**
	 * nombre del silo
	 */
	@JsonProperty(value="ciudad")
	private String ciudad;

	/**
	 * metodo constrctor de la clase puerto
	 * @param nombre el nombre del puerto
	 * @param pais el pais donde se localiza el puerto
	 * @param ciudad la ciudad donde se localiza el puerto
	 */
	public Puerto(@JsonProperty(value="nombre") String nombre,@JsonProperty(value="pais") String pais,@JsonProperty(value="ciudad") String ciudad)
	{
		super();
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
	}

	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * 
	 * @param pais
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * 
	 * @return
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * 
	 * @param ciudad
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
}
