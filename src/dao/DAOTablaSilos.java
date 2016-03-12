package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Silo;

public class DAOTablaSilos {

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Método constructor que crea DAOArea
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaSilos() {
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
	 * Método que, usando la conexión a la base de datos, saca todos los Areas de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM AreaS;
	 * @return Arraylist con los Areas de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Silo> darSilos() throws SQLException, Exception {
		ArrayList<Silo> areas = new ArrayList<Silo>();

		String sql = "select * from AREAS NATURAL JOIN SILOS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString("ID"));
			Double capacidad=Double.parseDouble(rs.getString("CAPACIDAD"));
			String nombre=rs.getString("NOMBRE");
			
			
			areas.add(new Silo(id, nombre, capacidad));
		}
		return areas;
	}


	/**
	 * Método que busca el/los Areas con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los Areas a buscar
	 * @return Arraylist con los Areas encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Silo buscarSiloPorID(int idn) throws SQLException, Exception {
		Silo area=null;

		String sql = "SELECT * from AREAS NATURAL JOIN SILOS WHERE ID ="+idn;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {

			int id = Integer.parseInt(rs.getString("ID"));
			Double capacidad=Double.parseDouble(rs.getString("CAPACIDAD"));
			String nombre=rs.getString("NOMBRE");
			
			
			area=new Silo(id, nombre, capacidad);
			}

		return area;
	}

	/**
	 * Método que agrega el Area que entra como parámetro a la base de datos.
	 * @param Area - el Area a agregar. Area !=  null
	 * <b> post: </b> se ha agregado el Area a la base de datos en la transaction actual. pendiente que el Area master
	 * haga commit para que el Area baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Area a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addArea(Silo area) throws SQLException, Exception {

		String sql0 = "INSERT INTO AREAS VALUES ("+area.getId()+")";
		String sql = "INSERT INTO SILOS VALUES (";
		sql += area.getId() + ",";
		sql += area.getNombre() + ",";
		sql += area.getCapacidad() + ")";
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt0 = conn.prepareStatement(sql0);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt0);
		recursos.add(prepStmt);
		prepStmt0.executeUpdate();
		prepStmt.executeUpdate();
	}

	/**
	 * Método que elimina el Area que entra como parámetro en la base de datos.
	 * @param Area - el Area a borrar. Area !=  null
	 * <b> post: </b> se ha borrado el Area en la base de datos en la transaction actual. pendiente que el Area master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Area.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteArea(Silo area) throws SQLException, Exception {

		String sql = "DELETE FROM SILOS";
		sql += " WHERE id = " + area.getId();
		
		String sql2 = "DELETE FROM AREAS";
		sql2 += " WHERE id = " + area.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		
		recursos.add(prepStmt);
		recursos.add(prepStmt2);
		
		prepStmt.executeUpdate();
		prepStmt2.executeUpdate();
	}
}
