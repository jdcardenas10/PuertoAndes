package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ArriboSimple {
	@JsonProperty(value="id")
	private int id;
	@JsonProperty(value="tipoArribo")
	private char tipoArribo;
	@JsonProperty(value="idAgente")
	private int idAgente;
	@JsonProperty(value="idMuelle")
	private int idMuelle;
	@JsonProperty(value="idBuque")
	private String idBuque;
	@JsonProperty(value="idCamion")
	private String idCamion;
	@JsonProperty(value="fecha")
	private String fecha;
	@JsonProperty(value="idOrigen")
	private String idOrigen;
	@JsonProperty(value="idDestino")
	private String idDestino;
	
	public ArriboSimple(@JsonProperty(value="id") int id, @JsonProperty(value="tipoArribo") char tipoArribo, @JsonProperty(value="idAgente") int idAgente,
			@JsonProperty(value="idMuelle") int idMuelle,@JsonProperty(value="idBuque")String idBuque,@JsonProperty(value="idCamion")String idCamion,
			@JsonProperty(value="fecha")String fecha,@JsonProperty(value="idOrigen")String idOrigen,@JsonProperty(value="idDestino")String idDestino){
		setId(id);
		setTipoArribo(tipoArribo);
		setIdAgente(idAgente);
		setIdMuelle(idMuelle);
		setIdBuque(idBuque);
		setIdCamion(idCamion);
		setFecha(fecha);
		setIdOrigen(idOrigen);
		setIdDestino(idDestino);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public char getTipoArribo() {
		return tipoArribo;
	}
	public void setTipoArribo(char tipoArribo) {
		this.tipoArribo = tipoArribo;
	}
	
	public int getIdAgente() {
		return idAgente;
	}
	public void setIdAgente(int idAgente) {
		this.idAgente = idAgente;
	}
	
	public int getIdMuelle() {
		return idMuelle;
	}
	public void setIdMuelle(int idMuelle) {
		this.idMuelle = idMuelle;
	}
	
	public String getIdBuque() {
		return idBuque;
	}
	public void setIdBuque(String idBuque) {
		this.idBuque = idBuque;
	}
	public String getIdCamion() {
		return idCamion;
	}
	public void setIdCamion(String idCamion) {
		this.idCamion = idCamion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getIdOrigen() {
		return idOrigen;
	}


	public void setIdOrigen(String idOrigen) {
		this.idOrigen = idOrigen;
	}


	public String getIdDestino() {
		return idDestino;
	}


	public void setIdDestino(String idDestino) {
		this.idDestino = idDestino;
	}
}
