package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.PuertoAndesMaster;
import vos.Entrega;
import vos.Importador;

@Path("entregas")
public class EntregaServices {

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
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getEntregas() 
	{
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		List<Entrega> entregas;
		try {
			entregas = tm.darEntregas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(entregas).build();
	}
	
    /**
     * Método que expone servicio REST usando PUT que agrega los videos que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/videos
     * @param videos - videos a agregar. 
     * @return Json con el video que agrego o Json con el error que se produjo
     */
	@PUT
	@Path("/entrega/{idCarga}/{idTipoCarga}/{peso}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEntrega(@javax.ws.rs.PathParam("idCarga") int idCarga,@javax.ws.rs.PathParam("idTipoCarga")  int idTipoCarga, @javax.ws.rs.PathParam("peso") double peso) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		try {
			tm.addEntrega(idCarga, idTipoCarga,peso);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		//return Response.status(200).entity(entrega).build();
		return null;
	}
}
