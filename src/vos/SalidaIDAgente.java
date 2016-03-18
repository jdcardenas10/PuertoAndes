package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class SalidaIDAgente {

	@JsonProperty(value="salida")
	private Salida salida;
	
	@JsonProperty(value="idAgente")
	private int idAgente;
	
	public SalidaIDAgente(@JsonProperty(value="salida") Salida salida,@JsonProperty(value="idAgente")int idAgente) {

		this.setSalida(salida);
		this.setIdAgente(idAgente);
	}

	public Salida getSalida() {
		return salida;
	}

	public void setSalida(Salida salida) {
		this.salida = salida;
	}

	public int getIdAgente() {
		return idAgente;
	}

	public void setIdAgente(int idAgente) {
		this.idAgente = idAgente;
	}
}
