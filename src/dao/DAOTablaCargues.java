package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Cargue;

public class DAOTablaCargues {

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
	public DAOTablaCargues() {
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
	
	public void addCargues(int carga,int operador,int area,int buque) throws SQLException, Exception {

		String sql = "INSERT INTO Operaciones VALUES (";
		sql += "(SELECT MAX(ID) FROM OPERACIONES)+1" + ",";
		sql += "null" + ",'";
		sql += "C" + "',";
		sql += "CURRENT_DATE" + ",";
		sql += carga +")";
		
		String sql2="INSERT INTO Cargues VALUES ((SELECT MAX(ID) FROM OPERACIONES),"+operador+","+area+","+buque+")";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt);
		recursos.add(prepStmt2);
		prepStmt.executeUpdate();
		prepStmt2.executeUpdate();

	}
	
	
}
