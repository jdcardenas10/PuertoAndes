package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaArribosSalidas {

	@JsonProperty(value="arribos")
	private List<ArriboSimple> arribos;
	
	@JsonProperty(value="salidas")
	private List<SalidaSimple> salidas;
	
	public ListaArribosSalidas(@JsonProperty(value="arribos") List<ArriboSimple> arribos, @JsonProperty(value="salidas") List<SalidaSimple> salidas){
		this.arribos=arribos;
		this.salidas=salidas;
	}
	
	public List<ArriboSimple> getArribos() {
		return arribos;
	}
	public void setArribos(List<ArriboSimple> list) {
		this.arribos = list;
	}
	public List<SalidaSimple> getSalidas() {
		return salidas;
	}
	public void setSalidas(List<SalidaSimple> list) {
		this.salidas = list;
	}
	
}
