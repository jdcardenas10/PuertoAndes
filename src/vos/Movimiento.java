package vos;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Movimiento extends Operacion {

	/**
	 * las entregas que se le han realizado al imporrtador
	 */
	@JsonProperty(value="origen")
	private int origen;

	/**
	 * las cargas que ha importado el importador
	 */
	@JsonProperty(value="destino")
	private int destino;
	
	public Movimiento(int id, char tipo, Date fecha, Carga carga,@JsonProperty(value="origen")int id1,@JsonProperty(value="destino")int id2) {
		super(id, tipo, fecha, carga);
		origen=id1;
		destino=id2;
	}

	public int getOrigen() {
		return origen;
	}

	public void setOrigen(int origen) {
		this.origen = origen;
	}

	public int getDestino() {
		return destino;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}
	
	

}
