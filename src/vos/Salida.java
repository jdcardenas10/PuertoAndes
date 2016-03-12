package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Salida extends Operacion{

	/**
	 * el tipo de arribo que es,maritimo o terrestre
	 */
	@JsonProperty(value="tipoArribo")
	private char tipoArribo;
	
	/**
	 * el muelle al que se arriba
	 */
	@JsonProperty(value="muelle")
	private Muelle muelle;
	
	/**
	 * el barco que llega
	 */
	@JsonProperty(value="buque")
	private Buque buque;
	
	/**
	 * el camion que sale, si hubo
	 */
	@JsonProperty(value="camion")
	private Camion camion;
	
	
	
	/**
	 * metodo constructor de la clase arribo
	 * @param id el id de la operacion
	 * @param tipo el tipo de operacion
	 * @param fecha la fecha de la operacion
	 * @param carga la carga asociada a la operacion
	 * @param tipoArribo el tipo de arribo que es, terrestre o maritimo
	 * @param muelle el buelle al que arriba el buque
	 * @param buque el buque que arriba
	 */
	public Salida(@JsonProperty(value="id") int id,@JsonProperty(value="tipo") char tipo,@JsonProperty(value="fecha") Date fecha,@JsonProperty(value="carga") Carga carga, @JsonProperty(value="tipoArribo") char tipoArribo, @JsonProperty(value="muelle") Muelle muelle
			, @JsonProperty(value="buque") Buque buque, @JsonProperty(value="camion") Camion camion) {
		super(id, tipo, fecha, carga);
		this.tipoArribo=tipoArribo;
		this.muelle=muelle;
		this.buque=buque;
		this.setCamion(camion);
	}

	/**
	 * 
	 * @return
	 */
	public char getTipoArribo() {
		return tipoArribo;
	}

	/**
	 * 
	 * @param tipoArribo
	 */
	public void setTipoArribo(char tipoArribo) {
		this.tipoArribo = tipoArribo;
	}

	/**
	 * 
	 * @return
	 */
	public Muelle getMuelle() {
		return muelle;
	}

	/**
	 * 
	 * @param muelle
	 */
	public void setMuelle(Muelle muelle) {
		this.muelle = muelle;
	}

	/**
	 * 
	 * @return
	 */
	public Buque getBuque() {
		return buque;
	}

	/**
	 * 
	 * @param buque
	 */
	public void setBuque(Buque buque) {
		this.buque = buque;
	}

	public Camion getCamion() {
		return camion;
	}

	public void setCamion(Camion camion) {
		this.camion = camion;
	}
	
	
}
