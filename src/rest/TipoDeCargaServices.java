package rest;


import javax.servlet.ServletContext;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.PuertoAndesMaster;
import vos.TipoDeCarga;

@Path("tiposCargas")
public class TipoDeCargaServices {
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
//			@GET
//			@Produces({ MediaType.APPLICATION_JSON })
//			public Response getVideos() {
//				PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
//				List<Administrador> videos;
//				try {
//					videos = tm.darAdministradores();
//				} catch (Exception e) {
//					return Response.status(500).entity(doErrorMessage(e)).build();
//				}
//				return Response.status(200).entity(videos).build();
//			}
			
			
			/**
		     * Método que expone servicio REST usando PUT que agrega el video que recibe en Json
		     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
		     * @param admi - video a agregar
		     * @return Json con el video que agrego o Json con el error que se produjo
		     */
			@PUT
			@Path("/agregar/{buque}/{carga}")
			@Produces(MediaType.APPLICATION_JSON)
			public Response addAdministrador(@javax.ws.rs.PathParam("buque") int idBuque,@javax.ws.rs.PathParam("carga") int idCarga) {
				PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
				try {
					tm.addTipoCargaABarco(idBuque,idCarga);
				} catch (Exception e) {
					return Response.status(500).entity(doErrorMessage(e)).build();
				}
				return Response.status(200).entity(new TipoDeCarga(idBuque,null)).build();
			}
}
