package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.PuertoAndesMaster;
import vos.ListaArribosSalidas;
import vos.Movimiento;

@Path("RFC8")
public class RFC8Services {

	/**
	 * Atributo que usa la anotación @Context para tener el ServletContext de la conexión actual.
	 */
	@Context
	private ServletContext context;

	/**
	 * Método que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	
	@GET
	@Path("/{fecha1}/{fecha2}/{buque}/{tipoCarga}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getArribosSalidasRFC8(@javax.ws.rs.PathParam("fecha1") String inicio,@javax.ws.rs.PathParam("fecha2") String fin,@javax.ws.rs.PathParam("buque") int idBuque,@javax.ws.rs.PathParam("tipoCarga")int tipoC) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		ListaArribosSalidas operaciones;
		try {
			operaciones = tm.RFC8(inicio,fin,idBuque,tipoC);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(operaciones).build();
	}
}
