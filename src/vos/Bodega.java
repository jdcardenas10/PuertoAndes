package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Bodega extends Area{
	
	/**
	 * ancho de la bodega
	 */
	@JsonProperty(value="ancho")
	private double ancho;

	/**
	 * largo de la bodega
	 */
	@JsonProperty(value="largo")
	private double largo;
	
	/**
	 * si posee plataforma externa o no
	 */
	@JsonProperty(value="plataformaExterna")
	private boolean plataformaExterna;

	/**
	 * la separacion entre columnas
	 */
	@JsonProperty(value="separacion")
	private double separacion;
	
	/**
	 * los cuartos frios de la bodega
	 */
	@JsonProperty(value="cuartosFrios")
	private List<CuartoFrio> cuartosFrios;
	
	/**
	 * metodo constructor de la clase bodega
	 * @param id el id del area
	 * @param ancho el ancho de la bodega
	 * @param largo el largo de la bodega
	 * @param plataformaExterna si la bodega tiene plataforma externa o no
	 * @param separacion la separacion entre las columnas
	 */
	public Bodega(@JsonProperty(value="id") int id,@JsonProperty(value="ancho") double ancho,@JsonProperty(value="largo") double largo,@JsonProperty(value="plataformaExterna") boolean plataformaExterna,@JsonProperty(value="separacion") double separacion, @JsonProperty(value="cuartosFrios") List<CuartoFrio> cuartosFrios)
	{
		super(id);
		this.ancho=ancho;
		this.largo=largo;
		this.plataformaExterna=plataformaExterna;
		this.separacion=separacion;
		this.cuartosFrios= cuartosFrios;
	}

	/**
	 * metodo que retorna el ancho de la bodega
	 * @return
	 */
	public double getAncho() {
		return ancho;
	}

	/**
	 * metodo que cambia el ancho de la bodega
	 * @param ancho
	 */
	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	/**
	 * metodo que retorna el largo de la bodega
	 * @return
	 */
	public double getLargo() {
		return largo;
	}

	/**
	 * metodo que cambia el largo de la bodega
	 * @param largo
	 */
	public void setLargo(double largo) {
		this.largo = largo;
	}

	/**
	 * metodo que retona true si tiene plataforma externa o false de lo contrario
	 * @return
	 */
	public boolean isPlataformaExterna() {
		return plataformaExterna;
	}

	/**
	 * metodo que cambia el valor de la plataforma externa
	 * @param plataformaExterna
	 */
	public void setPlataformaExterna(boolean plataformaExterna) {
		this.plataformaExterna = plataformaExterna;
	}

	/**
	 * metodo que da la separacion de la bodega
	 * @return
	 */
	public double getSeparacion() {
		return separacion;
	}

	/**
	 * metodo que cambia la separacion de la bodega
	 * @param separacion
	 */
	public void setSeparacion(double separacion) {
		this.separacion = separacion;
	}
}
