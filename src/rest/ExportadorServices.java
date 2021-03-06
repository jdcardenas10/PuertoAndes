package rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.PuertoAndesMaster;
import vos.Exportador;

@Path("exportadores")
public class ExportadorServices {

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
	public Response getExportadores() {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		List<Exportador> exportadores;
		try {
			exportadores = tm.darExportadores();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(exportadores).build();
	}
	
	/**
     * Método que expone servicio REST usando GET que busca el video con el nombre que entra como parámetro
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/name/"name para la busqueda"
     * @param name - Nombre del video a buscar que entra en la URL como parámetro 
     * @return Json con el/los videos encontrados con el nombre que entra como parámetro o json con 
     * el error que se produjo
     */
	@GET
	@Path("/buscarExportador/{tipoExpo}/{idBuque}/{tipoCarga}/{fecha1}/{fecha2}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getExportadoresPor(@javax.ws.rs.PathParam("tipoExpo") String tipoExpo,@javax.ws.rs.PathParam("idBuque") int idBuque,@javax.ws.rs.PathParam("tipoCarga") int tipoCarga,@javax.ws.rs.PathParam("fecha1") String fecha1,@javax.ws.rs.PathParam("fecha2") String fecha2) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		List<Exportador> exportadores= new ArrayList<Exportador>();
		try {
			exportadores = tm.buscarExportadorPor(tipoExpo,idBuque,tipoCarga,fecha1,fecha2);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(exportadores).build();
	}
}
