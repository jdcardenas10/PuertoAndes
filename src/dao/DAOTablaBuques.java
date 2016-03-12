package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Buque;

public class DAOTablaBuques {

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Método constructor que crea DAOBuque
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaBuques() {
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
	 * Método que, usando la conexión a la base de datos, saca todos los Buques de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM Buques;
	 * @return Arraylist con los Buques de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Buque> darBuques() throws SQLException, Exception {
		ArrayList<Buque> buques = new ArrayList<Buque>();

		String sql = "select B.ID,B.NOMBRE,B.REGISTRO,A.NOMBRE AS AGENTE_MARITIMO,B.CAPACIDAD,B.UTILIZADO "
				+ "from BUQUES B join AGENTES_MARITIMOS A ON B.ID_AGENTE=A.ID";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre=rs.getString("NOMBRE");
			String registro=rs.getString("REGISTRO");
			String agente=rs.getString("AGENTE_MARITIMO");
			Double capacidad=Double.parseDouble(rs.getString("CAPACIDAD"));
			Double utilizado=Double.parseDouble(rs.getString("UTILIZADO"));
			
			buques.add(new Buque(id,nombre,registro,agente,null,capacidad,utilizado));
		}
		return buques;
	}


	/**
	 * Método que busca el/los Buques con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los Buques a buscar
	 * @return Arraylist con los Buques encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Buque buscarBuquePorID(int idn) throws SQLException, Exception {
		Buque buque=null;

		String sql = "select B.ID,B.NOMBRE,B.REGISTRO,A.NOMBRE AS AGENTE_MARITIMO,B.CAPACIDAD,B.UTILIZADO "
				+ "from BUQUES B join AGENTES_MARITIMOS A ON B.ID_AGENTE=A.ID WHERE B.ID ="+idn;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre=rs.getString("NOMBRE");
			String registro=rs.getString("REGISTRO");
			String agente=rs.getString("AGENTE_MARITIMO");
			Double capacidad=Double.parseDouble(rs.getString("CAPACIDAD"));
			Double utilizado=Double.parseDouble(rs.getString("UTILIZADO"));
			
			buque=new Buque(id,nombre,registro,agente,null,capacidad,utilizado);
		}

		return buque;
	}

	/**
	 * Método que agrega el Buque que entra como parámetro a la base de datos.
	 * @param Buque - el Buque a agregar. Buque !=  null
	 * <b> post: </b> se ha agregado el Buque a la base de datos en la transaction actual. pendiente que el Buque master
	 * haga commit para que el Buque baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Buque a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addBuque(Buque buque) throws SQLException, Exception {
		
		String sql0="select ID FROM AGENTES_MARITIMOS WHERE NOMBRE='"+buque.getAgente()+"'";
		PreparedStatement prepStmt0 = conn.prepareStatement(sql0);
		recursos.add(prepStmt0);
		ResultSet rs =prepStmt0.executeQuery();
		
		int id=0;
		
		while (rs.next()) {
			id = Integer.parseInt(rs.getString("ID"));
		}

		String sql = "INSERT INTO BUQUES VALUES (";
		sql += buque.getId() + ",'";
		sql += buque.getNombre() + "','";
		sql += buque.getRegistro() + "',";
		sql += id+ ",";
		sql += buque.getCapacidad() + ",";
		sql += buque.getUsado() + ")";
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeUpdate();

	}
	
	/**
	 * Método que actualiza el Buque que entra como parámetro en la base de datos.
	 * @param Buque - el Buque a actualizar. Buque !=  null
	 * <b> post: </b> se ha actualizado el Buque en la base de datos en la transaction actual. pendiente que el Buque master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Buque.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateBuque(Buque buque) throws SQLException, Exception {

		String sql = "UPDATE BUQUES SET ";
		sql += "capacidad=" + buque.getCapacidad() + ", ";
		sql += "utilizado=" + buque.getUsado()+", ";
		sql += " WHERE id = " + buque.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeUpdate();
	}

	/**
	 * Método que elimina el Buque que entra como parámetro en la base de datos.
	 * @param Buque - el Buque a borrar. Buque !=  null
	 * <b> post: </b> se ha borrado el Buque en la base de datos en la transaction actual. pendiente que el Buque master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Buque.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteBuque(Buque buque) throws SQLException, Exception {

		String sql = "DELETE FROM BUQUES";
		sql += " WHERE id = " + buque.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeUpdate();
	}
}
