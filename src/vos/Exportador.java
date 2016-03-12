package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Exportador extends Usuario{

	/**
	 * el rut del exportador
	 */
	@JsonProperty(value="rut")
	private String rut;
	
	/**
	 * las facturas hechas al exportador
	 */
	@JsonProperty(value="facturas")
	private List<Factura> facturas;

	/**
	 * las cargas que ha exportado el exportador
	 */
	@JsonProperty(value="exportadas")
	private List<Carga> exportadas;
	
	/**
	 * metodo constructor de la clase exportador
	 * @param id el id del usuario
	 * @param nombre el nombre del usuario
	 * @param login el login del usuario
	 * @param clave la clave del usuario
	 * @param rut el rut del exportador
	 * @param facturas las facturas del exportador
	 * @param exportadas las cargas exportadas del exportador
	 */
	public Exportador(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="login") String login, @JsonProperty(value="clave")String clave, @JsonProperty(value="rut")String rut, @JsonProperty(value="facturas")List<Factura> facturas,  @JsonProperty(value="exportadas")List<Carga> exportadas) 
	{
		super(id,nombre,login,clave);
		this.rut=rut;
		this.facturas=facturas;
		this.exportadas=exportadas;
	}

	/**
	 * metodo que retorna el rut del exportador
	 * @return
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * metodo que cambia el rut del exportador
	 * @param rut
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}

	/**
	 * metodo que retorna las facturas del exportador
	 * @return
	 */
	public List<Factura> getFacturas() {
		return facturas;
	}

	/**
	 * metodo que cambia las facturas del exportador
	 * @param facturas
	 */
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	/**
	 * metodo que retorna las cargas exportadas del exportador
	 * @return
	 */
	public List<Carga> getExportadas() {
		return exportadas;
	}

	/**
	 * metodo que cambia las cargas exportadas del exportador
	 * @param exportadas
	 */
	public void setExportadas(List<Carga> exportadas) {
		this.exportadas = exportadas;
	}
	
	
}
