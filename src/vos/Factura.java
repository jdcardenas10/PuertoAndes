package vos;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Factura {

	/**
	 * Id de la operacion
	 */
	@JsonProperty(value="id")
	private int id;
	
	/**
	 * tipo de la operacion
	 */
	@JsonProperty(value="valor")
	private double valor;
	
	/**
	 * la fecha de la operacion
	 */
	@JsonProperty(value="fecha")
	private Date fecha;
	
	/**
	 * la lista de operaciones a facturar
	 */
	@JsonProperty(value="operaciones")
	private List<Operacion> operaciones;

	/**
	 * metodo constructor de la clase factura
	 * @param id el id de la factura
	 * @param valor el valor total de la factura
	 * @param fecha la fecha de la factura
	 * @param operaciones las operaciones de la factura
	 */
	public Factura(@JsonProperty(value="id") int id,@JsonProperty(value="valor") double valor,@JsonProperty(value="fecha") Date fecha,@JsonProperty(value="operaciones") List<Operacion> operaciones) {
		super();
		this.id = id;
		this.valor = valor;
		this.fecha = fecha;
		this.operaciones = operaciones;
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * 
	 * @param valor
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * 
	 * @param fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * 
	 * @return
	 */
	public List<Operacion> getOperaciones() {
		return operaciones;
	}

	/**
	 * 
	 * @param operaciones
	 */
	public void setOperaciones(List<Operacion> operaciones) {
		this.operaciones = operaciones;
	}
}
