package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class EmpresaTerrestre {

	/**
	 * Id de la maquinaria
	 */
	@JsonProperty(value="id")
	private int id;

	/**
	 * Nombre de la empresa
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * la lista de camiones
	 */
	@JsonProperty(value="camiones")
	private List<Camion> camiones;
	
	public EmpresaTerrestre(@JsonProperty(value="id") int id, @JsonProperty(value="nombre") String nombre,@JsonProperty(value="camiones") List<Camion> camiones){
		this.id=id;
		this.nombre=nombre;
		this.camiones=camiones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Camion> getCamiones() {
		return camiones;
	}

	public void setCamiones(List<Camion> camiones) {
		this.camiones = camiones;
	}
	
}
