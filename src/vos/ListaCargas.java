package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaCargas {

	@JsonProperty(value="buque")
	private int buque;
	
	@JsonProperty(value="operador")
	private int operador;
	
	@JsonProperty(value="cargas")
	private List<Integer> cargas;
	
	public ListaCargas(@JsonProperty(value="buque") int buque, @JsonProperty(value="operador") int operador, @JsonProperty(value="cargas") List<Integer> cargas){
		this.buque=buque;
		this.operador=operador;
		this.cargas=cargas;
	}

	public int getBuque() {
		return buque;
	}

	public void setBuque(int buque) {
		this.buque = buque;
	}

	public int getOperador() {
		return operador;
	}

	public void setOperador(int operador) {
		this.operador = operador;
	}

	public List<Integer> getCargas() {
		return cargas;
	}

	public void setCargas(List<Integer> cargas) {
		this.cargas = cargas;
	}
	
	
}
