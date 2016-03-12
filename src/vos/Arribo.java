package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Arribo extends Operacion{

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
	 * el puerto de origen del buque que llega
	 */
	@JsonProperty(value="origen")
	private Puerto origen;
	
	/**
	 * el puerto destino de buque que llega
	 */
	@JsonProperty(value="destino")
	private Puerto destino;
	
	/**
	 * el camion utilizado si es que hubo
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
	 * @param origen puerto del que proviene el buque
	 * @param destino el puerto hacia donde va el buque
	 */
	public Arribo(@JsonProperty(value="id") int id,@JsonProperty(value="tipo") char tipo,@JsonProperty(value="fecha") Date fecha,@JsonProperty(value="carga") Carga carga, @JsonProperty(value="tipoArribo") char tipoArribo, @JsonProperty(value="muelle") Muelle muelle, @JsonProperty(value="buque") Buque buque, @JsonProperty(value="origen") Puerto origen
			,  @JsonProperty(value="destino") Puerto destino,  @JsonProperty(value="camion") Camion camion) {
		super(id, tipo, fecha, carga);
		this.tipoArribo=tipoArribo;
		this.muelle=muelle;
		this.buque=buque;
		this.origen=origen;
		this.destino=destino;
		this.camion=camion;
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


	/**
	 * 
	 * @return
	 */
	public Puerto getOrigen() {
		return origen;
	}


	/**
	 * 
	 * @param origen
	 */
	public void setOrigen(Puerto origen) {
		this.origen = origen;
	}


	/**
	 * 
	 * @return
	 */
	public Puerto getDestino() {
		return destino;
	}

	/**
	 * 
	 * @param destino
	 */

	public void setDestino(Puerto destino) {
		this.destino = destino;
	}


	public Camion getCamion() {
		return camion;
	}


	public void setCamion(Camion camion) {
		this.camion = camion;
	}
	
	
	
}
