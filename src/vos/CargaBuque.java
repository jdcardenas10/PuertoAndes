package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class CargaBuque {

	/**
	 * Id de la maquinaria
	 */
	@JsonProperty(value="idBuque")
	private int idBuque;

	/**
	 * la capacidad de la maquinaria
	 */
	@JsonProperty(value="idCarga")
	private int idCarga;
	
	public CargaBuque(@JsonProperty(value="idBuque") int idBuque, @JsonProperty(value="idCarga") int idCarga){
		this.idBuque=idBuque;
		this.idCarga=idCarga;
	}


	public int getIdBuque() {
		return idBuque;
	}


	public void setId(int idBuque) {
		this.idBuque = idBuque;
	}

	public int getIdCarga() {
		return idCarga;
	}


	public void setCapacidad(int idCarga) {
		this.idCarga = idCarga;
	}
}
