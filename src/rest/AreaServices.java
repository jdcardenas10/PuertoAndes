package rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.PuertoAndesMaster;
import vos.Area;
import vos.Carga;
import vos.ConsultaAreas;
import vos.Movimiento;

@Path("areas")
public class AreaServices {

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
	

	/**
	 * Método que expone servicio REST usando GET que da todos los videos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos
	 * @return Json con todos los videos de la base de datos O json con 
     * el error que se produjo
	 */
	@GET
	@Path("/{estado}/{tipo}/{can}/{max}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAreas(@javax.ws.rs.PathParam("estado") String estado,@javax.ws.rs.PathParam("tipo") String tipo,@javax.ws.rs.PathParam("can") int cantidad,@javax.ws.rs.PathParam("max") int maximo) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		ConsultaAreas c = new ConsultaAreas(estado.charAt(0),tipo.charAt(0),cantidad,maximo);
		List<Area> areas;
		try {
			areas = tm.darAreas(c);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(areas).build();
	}
	
	/**
	 * 
	 * @param area
	 * @return
	 */
	@POST
	@Path("/cerrar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cerrarArea(@javax.ws.rs.PathParam("id") int area) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		List<Carga>a=new ArrayList<Carga>();
		try {
			a= tm.cerrarArea(area);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(a).build();
	}
	
}
