package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Area {

	/**
	 * Id del area
	 */
	@JsonProperty(value="id")
	private int id;
	
	/**
	 * Id del area
	 */
	@JsonProperty(value="estado")
	private char estado;
	/**
	 * Id del area
	 */
	@JsonProperty(value="tipo")
	private char tipo;
	
	/**
	 * Id del area
	 */
	@JsonProperty(value="entradas")
	private List<Operacion> entradas;
	/**
	 * Id del area
	 */
	@JsonProperty(value="salidas")
	private  List<Operacion> salidas;
	
	/**
	 * metodo constructor de la clase area
	 * @param id el id del area
	 */
	public Area(@JsonProperty(value="id") int id, @JsonProperty(value="estado") char estado, @JsonProperty(value="tipo") char tipo,@JsonProperty(value="entradas") List<Operacion>entradas, @JsonProperty(value="salidas") List<Operacion>salidas)
	{
		this.id=id;
		this.estado=estado;
		this.tipo=tipo;
		this.entradas=entradas;
		this.salidas=salidas;
	}

	/**
	 * metodo que retorna el id del area
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * metodo que cambia el id del area
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public List<Operacion> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<Operacion> entradas) {
		this.entradas = entradas;
	}

	public List<Operacion> getSalidas() {
		return salidas;
	}

	public void setSalidas(List<Operacion> salidas) {
		this.salidas = salidas;
	}
	
	
}
