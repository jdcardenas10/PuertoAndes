package vos;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Cargue extends Operacion{

	/**
	 * el area de la cual vienen las cargas
	 */
	@JsonProperty(value="area")
	private Area area;
	
	/**
	 * el barco al cual se le va a hacer el cargue
	 */
	@JsonProperty(value="buque")
	private Buque buque;
	
	/**
	 * la maquinaria involucrada en el cargue y descargue
	 */
	@JsonProperty(value="maquinarias")
	private List<Maquinaria> maquinarias;
	
	/**
	 * metodo constructor de la clase cargue
	 * @param id el id de la operacion
	 * @param tipo el tipo de operacion
	 * @param fecha la fecha de la operacion
	 * @param carga la carga asociada a la operacion
	 * @param area el area asociada a la operacion
	 * @param buque el buque asociado a la operacion
	 * @param maquinarias las maquinarias asociadas a la operacion
	 */
	public Cargue(@JsonProperty(value="id") int id,@JsonProperty(value="tipo") char tipo,@JsonProperty(value="fecha") Date fecha,@JsonProperty(value="carga") Carga carga, @JsonProperty(value="area") Area area, @JsonProperty(value="buque") Buque buque, @JsonProperty(value="maquinarias") List<Maquinaria> maquinarias) {
		super(id, tipo, fecha, carga);
		this.area=area;
		this.buque=buque;
		this.maquinarias=maquinarias;
	}

	/**
	 * 
	 * @return
	 */
	public Area getArea() {
		return area;
	}
	
	/**
	 * 
	 * @param area
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * 
	 * @return
	 */
	public Buque getBuque() {
		return buque;
	}

	/**
	 * 
	 * @param buque
	 */
	public void setBuque(Buque buque) {
		this.buque = buque;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Maquinaria> getMaquinarias() {
		return maquinarias;
	}

	/**
	 * 
	 * @param maquinarias
	 */
	public void setMaquinarias(List<Maquinaria> maquinarias) {
		this.maquinarias = maquinarias;
	}
}
