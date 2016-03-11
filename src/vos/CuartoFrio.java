package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class CuartoFrio {

	/**
	 * Id del cuartoFrio
	 */
	@JsonProperty(value="id")
	private int id;
	
	/**
	 * ancho del cuarto frio
	 */
	@JsonProperty(value="ancho")
	private double ancho;

	/**
	 * largo de la bodega
	 */
	@JsonProperty(value="largo")
	private double largo;
	
	/**
	 * alto de la bodega
	 */
	@JsonProperty(value="alto")
	private double alto;
	
	/**
	 * el area en funcion de la bodega respectiva.
	 */
	@JsonProperty(value="areaEnFucnion")
	private double areaEnFuncion;
	
	/**
	 * si esta libreo o no
	 */
	@JsonProperty(value="libre")
	private boolean libre;

	/**
	 * metodo constructor de la clase cuarto frio
	 * @param id el id del cuarto frio
	 * @param ancho el ancho del cuarto frio
	 * @param largo el largo del cuarto frio
	 * @param alto el alto del cuarto frio
	 * @param areaEnFuncion el area que ocupa en la bodega
	 * @param libre si el cuarto esta libre o no 
	 */
	public CuartoFrio(@JsonProperty(value="id") int id,@JsonProperty(value="ancho") double ancho,@JsonProperty(value="largo") double largo,@JsonProperty(value="alto") double alto,@JsonProperty(value="areaEnFucnion") double areaEnFuncion, @JsonProperty(value="libre")boolean libre) 
	{
		super();
		this.id = id;
		this.ancho = ancho;
		this.largo = largo;
		this.alto = alto;
		this.areaEnFuncion = areaEnFuncion;
		this.libre = libre;
	}

	/**
	 * metodo que retorna el id del cuarto frio
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * metodo que cambia el id del cuarto frio
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * metodo que retorna el ancho del cuarto frio
	 * @return
	 */
	public double getAncho() {
		return ancho;
	}

	/**
	 * metodo que cambia el ancho del cuarto frio
	 * @param ancho
	 */
	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	/**
	 * metodo que retorna el largo del cuarto frio
	 * @return
	 */
	public double getLargo() {
		return largo;
	}

	/**
	 * metodo que cambia el largo del cuarto frio
	 * @param largo
	 */
	public void setLargo(double largo) {
		this.largo = largo;
	}

	/**
	 * metodo que retorna el alto del cuarto frio
	 * @return
	 */
	public double getAlto() {
		return alto;
	}

	/**
	 * 
	 * @param alto
	 */
	public void setAlto(double alto) {
		this.alto = alto;
	}

	/**
	 * 
	 * @return
	 */
	public double getAreaEnFuncion() {
		return areaEnFuncion;
	}

	/**
	 * 
	 * @param areaEnFuncion
	 */
	public void setAreaEnFuncion(double areaEnFuncion) {
		this.areaEnFuncion = areaEnFuncion;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isLibre() {
		return libre;
	}

	/**
	 * 
	 * @param libre
	 */
	public void setLibre(boolean libre) {
		this.libre = libre;
	}
	
	
}
