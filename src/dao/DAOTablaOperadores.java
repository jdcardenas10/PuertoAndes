package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Operador;



public class DAOTablaOperadores {

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Método constructor que crea DAOAgente
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaOperadores() {
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
	 * Método que, usando la conexión a la base de datos, saca todos los Agentes de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM AgenteS;
	 * @return Arraylist con los Agentes de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Operador> darOperadores() throws SQLException, Exception {
		ArrayList<Operador> agentes = new ArrayList<Operador>();

		String sql = "select * from OPERADORES NATURAL JOIN USUARIOS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			String login = rs.getString("LOGIN");
			String clave = rs.getString("CLAVE");
			agentes.add(new Operador(id, nombre, login,clave,null,null));
		}
		return agentes;
	}


	/**
	 * Método que busca el/los Agentes con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los Agentes a buscar
	 * @return Arraylist con los Agentes encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Operador buscarOperadorPorID(int idn) throws SQLException, Exception {
		Operador operador=null;

		String sql = "SELECT * from OPERADORES NATURAL JOIN USUARIOS WHERE ID ="+idn;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			String login = rs.getString("LOGIN");
			String clave = rs.getString("CLAVE");
			operador=new Operador(id, nombre, login,clave,null,null);
		}

		return operador;
	}

	/**
	 * Método que agrega el Agente que entra como parámetro a la base de datos.
	 * @param Agente - el Agente a agregar. Agente !=  null
	 * <b> post: </b> se ha agregado el Agente a la base de datos en la transaction actual. pendiente que el Agente master
	 * haga commit para que el Agente baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Agente a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addOperador(Operador operador) throws SQLException, Exception {

		String sql = "INSERT INTO Usuarios VALUES (";
		sql += operador.getId() + ",'";
		sql += operador.getNombre() + "','";
		sql += operador.getLogin() + "','";
		sql += operador.getClave() +"')";
		
		String sql2="INSERT INTO Operadores VALUES ("+operador.getId()+")";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt);
		recursos.add(prepStmt2);
		prepStmt.executeUpdate();
		prepStmt2.executeUpdate();

	}
	
	/**
	 * Método que actualiza el Agente que entra como parámetro en la base de datos.
	 * @param Agente - el Agente a actualizar. Agente !=  null
	 * <b> post: </b> se ha actualizado el Agente en la base de datos en la transaction actual. pendiente que el Agente master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Agente.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateAgente(Operador agente) throws SQLException, Exception {

		String sql = "UPDATE USUARIOS SET ";
		sql += "nombre='" + agente.getNombre() + "', ";
		sql += "login='" + agente.getLogin()+"', ";
		sql += "clave='" + agente.getClave()+"'";
		sql += " WHERE id = " + agente.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeUpdate();
	}

	/**
	 * Método que elimina el Agente que entra como parámetro en la base de datos.
	 * @param Agente - el Agente a borrar. Agente !=  null
	 * <b> post: </b> se ha borrado el Agente en la base de datos en la transaction actual. pendiente que el Agente master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Agente.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteAgente(Operador agente) throws SQLException, Exception {

		String sql = "DELETE FROM USUARIOS";
		sql += " WHERE id = " + agente.getId();
		
		String sql1 = "DELETE FROM OPERADORES";
		sql += " WHERE id = " + agente.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeUpdate();
		
		PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
		recursos.add(prepStmt1);
		prepStmt1.executeUpdate();
	}
}
