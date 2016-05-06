package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaArribosSalidas {

	@JsonProperty(value="arribos")
	private List<Arribo> arribos;
	
	@JsonProperty(value="salidas")
	private List<Salida> salidas;
	
	public ListaArribosSalidas(@JsonProperty(value="arribos") List<Arribo> arribos, @JsonProperty(value="salidas") List<Salida> salidas){
		this.arribos=arribos;
		this.salidas=salidas;
	}
	
	public List<Arribo> getArribos() {
		return arribos;
	}
	public void setArribos(List<Arribo> arribos) {
		this.arribos = arribos;
	}
	public List<Salida> getSalidas() {
		return salidas;
	}
	public void setSalidas(List<Salida> salidas) {
		this.salidas = salidas;
	}
	
}
