package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Importador extends Usuario {

	
	/**
	 * el tipo de importador, frecuente o no.
	 */
	@JsonProperty(value="tipo")
	private char tipo;

	/**
	 * el registro del importador
	 */
	@JsonProperty(value="registro")
	private String registro;
	
	/**
	 * las entregas que se le han realizado al imporrtador
	 */
	@JsonProperty(value="entregas")
	private List entregas;

	/**
	 * las cargas que ha importado el importador
	 */
	@JsonProperty(value="importadas")
	private List<Carga> importadas;
		

	/**
	 * metodo constructor de la clase importador
	 * @param id el id del usuario
	 * @param nombre el nombre del usuario
	 * @param login el login del usuario
	 * @param clave la clave del usuario
	 * @param tipo el tipo de importador
	 * @param registro el registro del importador
	 * @param entregas las entregas que ha realizado el importador
	 * @param importadas las cargas impostadas del importador
	 */
	public Importador(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="login") String login, @JsonProperty(value="clave")String clave,  @JsonProperty(value="tipo")char tipo,  @JsonProperty(value="registro")String registro, @JsonProperty(value="entregas")List entregas,  @JsonProperty(value="importadas")List<Carga> importadas) 
	{
		super(id,nombre,login,clave);
		this.tipo=tipo;
		this.registro=registro;
		this.entregas=entregas;
		this.importadas=importadas;
	}


	/**
	 * metodo que retorna el tipo de importador
	 * @return
	 */
	public char getTipo() {
		return tipo;
	}

	/**
	 * metodo que camia el tipo de importador
	 * @param tipo
	 */
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	/**
	 * metodo que retorna el registro del importador
	 * @return
	 */
	public String getRegistro() {
		return registro;
	}
	
	/**
	 * metodo que cambia el registro del importador
	 * @param registro
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
	}

	/**
	 * metodo que retorna las entregas hechas a un importador
	 * @return
	 */
	public List getEntregas() {
		return entregas;
	}

	/**
	 * metodo que cambia las entregas del importador
	 * @param entregas
	 */
	public void setEntregas(List entregas) {
		this.entregas = entregas;
	}

	/**
	 * metodo que retorna las cargas importadas del importador
	 * @return
	 */
	public List<Carga> getImportadas() {
		return importadas;
	}

	/**
	 * metodo que cambia las cargas importadas del importador
	 * @param importadas
	 */
	public void setImportadas(List<Carga> importadas) {
		this.importadas = importadas;
	}
	
}
