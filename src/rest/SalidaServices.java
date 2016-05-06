package rest;

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
import vos.ListaArribosSalidas;
import vos.SalidaIDAgente;

@Path("salidas")
public class SalidaServices {

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
	public Response getVideos() {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		ListaArribosSalidas lista;
		try {
			lista = tm.darArribosYSalidas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(lista).build();
	}
	
	/**
	 * Método que expone servicio REST usando GET que da todos los arribos y salidas eficiente.
	 * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos
	 * @return Json con todos los videos de la base de datos O json con 
     * el error que se produjo
	 */
	@GET
	@Path("/buscar/{inicio}/{fin}/{nombre}/{tipo}/{barco}/{param}/{forma}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getArribos_Salidas_RFC7(
			 @javax.ws.rs.PathParam("inicio") int inicio,@javax.ws.rs.PathParam("fin") int fin,@javax.ws.rs.PathParam("nombre") String nombre
			,@javax.ws.rs.PathParam("tipo") int tipo,@javax.ws.rs.PathParam("barco") String barco
			,@javax.ws.rs.PathParam("param") String param,@javax.ws.rs.PathParam("forma") String forma) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		ListaArribosSalidas lista;
		try {
			lista = tm.RFC7(inicio,fin,nombre,tipo,barco,param,forma);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(lista).build();
	}
	
	/**
     * Método que expone servicio REST usando PUT que agrega los videos que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/videos
     * @param videos - videos a agregar. 
     * @return Json con el video que agrego o Json con el error que se produjo
     */
	@PUT
	@Path("/agregar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addVideo(SalidaIDAgente salida) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		try {
			tm.addSalida(salida.getSalida(),salida.getIdAgente());
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(salida).build();
	}
}
