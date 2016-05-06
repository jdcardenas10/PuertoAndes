package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vos.Arribo;
import vos.Salida;



public class DAOTablaArribos {

		/**
		 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
		 */
		private ArrayList<Object> recursos;

		/**
		 * Atributo que genera la conexión a la base de datos
		 */
		private Connection conn;

		/**
		 * Método constructor que crea DAOAdministrador
		 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
		 */
		public DAOTablaArribos() {
			recursos = new ArrayList<Object>();
		}

		/**
		 * Método que cierra todos los recursos que estan enel arreglo de recursos
		 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
		 */
		public void cerrarRecursos() {
			for(Object ob : recursos){
				if(ob instanceof PreparedStatement)
					try {
						((PreparedStatement) ob).close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
			}
		}

		/**
		 * Método que inicializa la connection del DAO a la base de datos con la conexión que entra como parámetro.
		 * @param con  - connection a la base de datos
		 */
		public void setConn(Connection con){
			this.conn = con;
		}


		/**
		 * Método que, usando la conexión a la base de datos, saca todos los Administradors de la base de datos
		 * <b>SQL Statement:</b> SELECT * FROM AdministradorS;
		 * @return Arraylist con los Administradors de la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		@SuppressWarnings("deprecation")
		public ArrayList<Arribo> consultarArribos() throws SQLException, Exception {
			ArrayList<Arribo> arribos = new ArrayList<Arribo>();

			String sql = "select * from ARRIBOS NATURAL JOIN OPERACIONES";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				
				int id = Integer.parseInt(rs.getString("ID"));
				char tipo = rs.getString("TIPO_ARRIBO").charAt(0);
				//String idAgente = rs.getString("ID_AGENTE");
				String idMuelle = rs.getString("ID_MUELLE");
				String idBuque = rs.getString("ID_BUQUE");
				String idOrigen = rs.getString("ID_ORIGEN");
				String idDestino = rs.getString("ID_DESTINO");
				String idCamion = rs.getString("ID_CAMION");
				String idFactura = rs.getString("ID_FACTURA");
				String fecha = rs.getString("FECHA");
				String idCarga = rs.getString("ID_CARGA");
				
				String sql1="SELECT * FROM MUELLES WHERE ID="+idMuelle;
				String sql2="SELECT * FROM BUQUES WHERE ID="+idBuque;
				String sql3="SELECT * FROM PUERTOS_EXTERNOS WHERE ID="+idOrigen;
				String sql4="SELECT * FROM PUERTOS_EXTERNOS WHERE ID="+idDestino;
				String sql5="SELECT * FROM CAMIONES WHERE ID="+idCamion;
				String sql6="SELECT * FROM FACTURAS WHERE ID="+idFactura;
				String sql7="SELECT * FROM CARGAS WHERE ID="+idCarga;
				
				
				
				
				arribos.add(new Arribo(id, 'N', new Date(fecha),null,tipo,null,null,null,null,null));
			}
			return arribos;
		}


		/**
		 * Método que busca el/los Administradors con el nombre que entra como parámetro.
		 * @param name - Nombre de el/los Administradors a buscar
		 * @return Arraylist con los Administradors encontrados
		 * @throws SQLException - Cualquier error que la base de datos arroje.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		@SuppressWarnings("deprecation")
		public Arribo buscarArribosPorID(int idn) throws SQLException, Exception {
			Arribo arribo = null;

			String sql = "select * from ARRIBOS NATURAL JOIN OPERACIONES WHERE ID ="+idn;

			System.out.println("SQL stmt:" + sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("ID"));
				char tipo = rs.getString("TIPO_ARRIBO").charAt(0);
				//String idAgente = rs.getString("ID_AGENTE");
				String idMuelle = rs.getString("ID_MUELLE");
				String idBuque = rs.getString("ID_BUQUE");
				String idOrigen = rs.getString("ID_ORIGEN");
				String idDestino = rs.getString("ID_DESTINO");
				String idCamion = rs.getString("ID_CAMION");
				String idFactura = rs.getString("ID_FACTURA");
				String fecha = rs.getString("FECHA");
				String idCarga = rs.getString("ID_CARGA");
				arribo =new Arribo(id, 'N', new Date(fecha),null,tipo,null,null,null,null,null);
			}

			return arribo;
		}

		public List<Arribo> obtenerArribosRFC7(int inicio, int fin, String nombre, int tipo, String barco, String param,
				String forma) throws SQLException {
			ArrayList<Arribo> lista=new ArrayList<Arribo>();
			
	        String sql="select v.* from(select * from operaciones o natural join salidas where fecha>='"+inicio+"' AND FECHA<='"+fin+"’ )v";
	        	   sql+=" join ";
	        	   sql+="(select * from(select c.* from buques C join TIPO_CARGAS_DE_BUQUES T on T.ID_BUQUE=C.ID where c.nombre='"+nombre+"' AND T.ID_TIPO_CARGA=TIPO)";
	        	   sql+=" natural join "+barco+")b on v.id_buque=b.id ORDER BY V."+param+" "+forma;
	        	   
	        	System.out.println("SQL stmt:" + sql);

	       		PreparedStatement prepStmt = conn.prepareStatement(sql);
	       		recursos.add(prepStmt);
	       		
	    		ResultSet rs = prepStmt.executeQuery();
	    		
	    		while(rs.next()){
	    			int id = Integer.parseInt(rs.getString("ID"));
	    			char tipo1 = rs.getString("TIPO_ARRIBO").charAt(0);
	    			String idAgente = rs.getString("ID_AGENTE");
	    			String idMuelle = rs.getString("ID_MUELLE");
	    			String idOrigen = rs.getString("ID_ORIGEN");
	    			String idDestino = rs.getString("ID_DESTINO");
	    			String idBuque = rs.getString("ID_BUQUE");
	    			String idCamion = rs.getString("ID_CAMION");
	    			String idFactura = rs.getString("ID_FACTURA");
	    			String fecha = rs.getString("FECHA");
	    			String fechaOrden = rs.getString("FECHA_ORDEN");
	    			String idCarga = rs.getString("ID_CARGA");
	    		}
	    		
	    		return lista;
		}

		public List<Arribo> obtenerArribosRFC8(String inicio, String fin,
				int idBuque, int tipoCarga) {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * Método que agrega el Administrador que entra como parámetro a la base de datos.
		 * @param Administrador - el Administrador a agregar. Administrador !=  null
		 * <b> post: </b> se ha agregado el Administrador a la base de datos en la transaction actual. pendiente que el Administrador master
		 * haga commit para que el Administrador baje  a la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Administrador a la base de datos
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
//		public void addAdministrador(Arribo arribo) throws SQLException, Exception {
//
//			String sql = "INSERT INTO Usuarios VALUES (";
//			sql += arribo.getId() + ",'";
//			sql += administrador.getNombre() + "','";
//			sql += administrador.getLogin() + "','";
//			sql += administrador.getClave() +"')";
//			
//			String sql2="INSERT INTO Administradores VALUES ("+administrador.getId()+")";
//
//			System.out.println("SQL stmt:" + sql);
//
//			PreparedStatement prepStmt = conn.prepareStatement(sql);
//			PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
//			recursos.add(prepStmt);
//			recursos.add(prepStmt2);
//			prepStmt.executeUpdate();
//			prepStmt2.executeUpdate();
//
//		}
}
