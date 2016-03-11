package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Usuario;


public class DAOTablaUsuarios {

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Método constructor que crea DAOUsuario
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaUsuarios() {
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
	 * Método que, usando la conexión a la base de datos, saca todos los Usuarios de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM UsuarioS;
	 * @return Arraylist con los Usuarios de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Usuario> darUsuarios() throws SQLException, Exception {
		ArrayList<Usuario> Usuarioes = new ArrayList<Usuario>();

		String sql = "select * from USUARIOS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			String login = rs.getString("LOGIN");
			String clave = rs.getString("CLAVE");
			Usuarioes.add(new Usuario(id, nombre, login,clave));
		}
		return Usuarioes;
	}


	/**
	 * Método que busca el/los Usuarios con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los Usuarios a buscar
	 * @return Arraylist con los Usuarios encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Usuario buscarUsuariosPorID(int idn) throws SQLException, Exception {
	    Usuario usuario=null;

		String sql = "SELECT * from USUARIOS WHERE ID ="+idn;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			String login = rs.getString("LOGIN");
			String clave = rs.getString("CLAVE");
			usuario=new Usuario(id,nombre,login,clave);
		}

		return usuario;
	}

	/**
	 * Método que agrega el Usuario que entra como parámetro a la base de datos.
	 * @param Usuario - el Usuario a agregar. Usuario !=  null
	 * <b> post: </b> se ha agregado el Usuario a la base de datos en la transaction actual. pendiente que el Usuario master
	 * haga commit para que el Usuario baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Usuario a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addUsuario(Usuario Usuario) throws SQLException, Exception {

		String sql = "INSERT INTO Usuarios VALUES (";
		sql += Usuario.getId() + ",'";
		sql += Usuario.getNombre() + "','";
		sql += Usuario.getLogin() + "','";
		sql += Usuario.getClave() +"')";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		
		prepStmt.executeQuery();

	}
	
	/**
	 * Método que actualiza el Usuario que entra como parámetro en la base de datos.
	 * @param Usuario - el Usuario a actualizar. Usuario !=  null
	 * <b> post: </b> se ha actualizado el Usuario en la base de datos en la transaction actual. pendiente que el Usuario master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Usuario.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateUsuario(Usuario Usuario) throws SQLException, Exception {

		String sql = "UPDATE USUARIOS SET ";
		sql += "nombre='" + Usuario.getNombre() + "', ";
		sql += "login='" + Usuario.getLogin()+"', ";
		sql += "clave='" + Usuario.getClave()+"'";
		sql += " WHERE id = " + Usuario.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Método que elimina el Usuario que entra como parámetro en la base de datos.
	 * @param Usuario - el Usuario a borrar. Usuario !=  null
	 * <b> post: </b> se ha borrado el Usuario en la base de datos en la transaction actual. pendiente que el Usuario master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Usuario.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteUsuario(Usuario usuario) throws SQLException, Exception {

		String sql = "DELETE FROM Usuarios";
		sql += " WHERE id = " + usuario.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}
