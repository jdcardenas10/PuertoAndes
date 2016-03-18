package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Muelle;

public class DAOTablaMuelles {

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
	public DAOTablaMuelles() {
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
	public ArrayList<Muelle> darMuelles() throws SQLException, Exception {
		ArrayList<Muelle> muelles = new ArrayList<Muelle>();

		String sql = "select * from MUELLES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			
			muelles.add(new Muelle(id, nombre));
		}
		return muelles;
	}


	/**
	 * Método que busca el/los Administradors con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los Administradors a buscar
	 * @return Arraylist con los Administradors encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Muelle buscarMuellePorIDBuque(int idn) throws SQLException, Exception {
		Muelle r = null;

		String sql = "SELECT * from MUELLES WHERE ID_BUQUE ="+idn;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			
			r=new Muelle(id, nombre);
		}

		return r;
	}

	/**
	 * Método que agrega el Administrador que entra como parámetro a la base de datos.
	 * @param Administrador - el Administrador a agregar. Administrador !=  null
	 * <b> post: </b> se ha agregado el Administrador a la base de datos en la transaction actual. pendiente que el Administrador master
	 * haga commit para que el Administrador baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Administrador a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addMulle(Muelle muelle) throws SQLException, Exception {

		String sql = "INSERT INTO MUELLES VALUES (";
		sql += muelle.getId() + ",'";
		sql += muelle.getNombre() + "')";
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeUpdate();

	}
	
	/**
	 * Método que actualiza el Administrador que entra como parámetro en la base de datos.
	 * @param Administrador - el Administrador a actualizar. Administrador !=  null
	 * <b> post: </b> se ha actualizado el Administrador en la base de datos en la transaction actual. pendiente que el Administrador master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Administrador.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void setMuelle(int id,int idBuque) throws SQLException, Exception {

		
		String sql = "UPDATE MUELLES SET ";
		sql += "ID_BUQUE=" + idBuque;
		sql += " WHERE id = " + id;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeUpdate();
	}
	
	/**
	 * Método que actualiza el Administrador que entra como parámetro en la base de datos.
	 * @param Administrador - el Administrador a actualizar. Administrador !=  null
	 * <b> post: </b> se ha actualizado el Administrador en la base de datos en la transaction actual. pendiente que el Administrador master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Administrador.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void vaciarMuelle(int id) throws SQLException, Exception {

		
		String sql = "UPDATE MUELLES SET ";
		sql += "ID_BUQUE=null";
		sql += " WHERE id = " + id;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeUpdate();
	}

	/**
	 * Método que elimina el Administrador que entra como parámetro en la base de datos.
	 * @param Administrador - el Administrador a borrar. Administrador !=  null
	 * <b> post: </b> se ha borrado el Administrador en la base de datos en la transaction actual. pendiente que el Administrador master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Administrador.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteMuelle(Muelle muelle) throws SQLException, Exception {

		String sql = "DELETE FROM MUELLES";
		sql += " WHERE id = " + muelle.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeUpdate();
	}
}
