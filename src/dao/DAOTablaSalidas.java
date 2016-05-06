package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vos.Salida;

public class DAOTablaSalidas {

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
	public DAOTablaSalidas() {
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
	public ArrayList<Salida> consultarArribos() throws SQLException, Exception {
		ArrayList<Salida> salidas = new ArrayList<Salida>();

		String sql = "select * from SALIDAS NATURAL JOIN OPERACIONES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString("ID"));
			char tipo = rs.getString("TIPO_ARRIBO").charAt(0);
			//String idAgente = rs.getString("ID_AGENTE");
			String idMuelle = rs.getString("ID_MUELLE");
			String idBuque = rs.getString("ID_BUQUE");
			String idCamion = rs.getString("ID_CAMION");
			String idFactura = rs.getString("ID_FACTURA");
			String fecha = rs.getString("FECHA");
			String idCarga = rs.getString("ID_CARGA");
			
			
			
			salidas.add(new Salida(id, 'N', new Date(fecha),null,tipo,null,null,null));
		}
		return salidas;
	}


	/**
	 * Método que busca el/los Administradors con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los Administradors a buscar
	 * @return Arraylist con los Administradors encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	@SuppressWarnings("deprecation")
	public Salida buscarPorID(int idn) throws SQLException, Exception {
		Salida salida = null;

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
			String idCamion = rs.getString("ID_CAMION");
			String idFactura = rs.getString("ID_FACTURA");
			String fecha = rs.getString("FECHA");
			String idCarga = rs.getString("ID_CARGA");
			salida =new Salida(id, 'N', new Date(fecha),null,tipo,null,null,null);
		}

		return salida;
	}

	/**
	 * Método que agrega el Administrador que entra como parámetro a la base de datos.
	 * @param Administrador - el Administrador a agregar. Administrador !=  null
	 * <b> post: </b> se ha agregado el Administrador a la base de datos en la transaction actual. pendiente que el Administrador master
	 * haga commit para que el Administrador baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Administrador a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void add(Salida salida,int idAgente,int factura) throws SQLException, Exception {

		String sql = "INSERT INTO Operaciones VALUES (";
		sql += salida.getId() + ",";
		sql += factura + ",'";
		sql += salida.getTipo() + "','";
		sql += "CURRENT_DATE"+"',";
		sql +=salida.getCarga().getId()+"')";
		
		String sql2="INSERT INTO SALIDAS VALUES ("
				+salida.getId()+","
		        +idAgente+","
		        +salida.getMuelle().getId()+","
		        +salida.getBuque().getId()+","
		        +salida.getTipoArribo()+","
		        +salida.getCamion()+",";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt);
		recursos.add(prepStmt2);
		prepStmt.executeUpdate();
		prepStmt2.executeUpdate();

	}
	
	/**
	 * Método que agrega el Administrador que entra como parámetro a la base de datos.
	 * @param Administrador - el Administrador a agregar. Administrador !=  null
	 * <b> post: </b> se ha agregado el Administrador a la base de datos en la transaction actual. pendiente que el Administrador master
	 * haga commit para que el Administrador baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Administrador a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addSinFactura(Salida salida,int idAgente) throws SQLException, Exception {

		String carga="null";
		if( salida.getCamion()!=null){carga=salida.getCamion().getId()+"";}
		
		String sql = "INSERT INTO Operaciones VALUES (";
		sql += salida.getId() + ",";
		sql += "null" + ",'";
		sql += salida.getTipo() + "',";
		sql += "CURRENT_DATE"+",";
		sql +=carga+")";
		
		String camion="null";
		if( salida.getCamion()!=null){camion=salida.getCamion().getId()+"";}
		
		String sql2="INSERT INTO SALIDAS VALUES (";
				sql2+=salida.getId()+",";
						sql2+=idAgente+",";
		                 sql2+=salida.getMuelle().getId()+",";
		                    sql2+=salida.getBuque().getId()+",'";
		                      sql2+=salida.getTipoArribo()+"',";
		                        sql2+=camion+")";

		System.out.println("SQL stmt:" + sql);
		System.out.println("SQL stmt:" + sql2);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt);
		recursos.add(prepStmt2);
		prepStmt.executeUpdate();
		prepStmt2.executeUpdate();

	}

	public List<Salida> obtenerSalidas(int inicio, int fin, String nombre, int tipo, String barco, String param,
			String forma) throws SQLException {
		
		ArrayList<Salida> lista=new ArrayList<Salida>();
		
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
    			String idBuque = rs.getString("ID_BUQUE");
    			String idCamion = rs.getString("ID_CAMION");
    			String idFactura = rs.getString("ID_FACTURA");
    			String fecha = rs.getString("FECHA");
    			String fechaOrden = rs.getString("FECHA_ORDEN");
    			String idCarga = rs.getString("ID_CARGA");
    		}
    		
    		return lista;
	}
}
