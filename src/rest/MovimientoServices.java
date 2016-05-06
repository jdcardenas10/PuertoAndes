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
import vos.Movimiento;

@Path("movimientos")
public class MovimientoServices {

	// Servicios REST tipo GET:


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
			@Path("/buscar/{valor}/{tipo}/{exportador}")
			@Produces({ MediaType.APPLICATION_JSON })
			public Response getMovimientosRFC9() {
				PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
				List<Movimiento> videos;
				try {
					videos = tm.RFC9();
				} catch (Exception e) {
					return Response.status(500).entity(doErrorMessage(e)).build();
				}
				return Response.status(200).entity(videos).build();
			}

}
