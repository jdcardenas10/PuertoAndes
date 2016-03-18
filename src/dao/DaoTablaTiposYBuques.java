package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Administrador;
import vos.TipoDeCarga;

public class DaoTablaTiposYBuques {

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
	public DaoTablaTiposYBuques() {
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
	 * Método que busca el/los Administradors con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los Administradors a buscar
	 * @return Arraylist con los Administradors encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<TipoDeCarga> TipoDeCargaPorBuque(int idn) throws SQLException, Exception {
		ArrayList<TipoDeCarga> tipos = new ArrayList<TipoDeCarga>();

		String sql = "SELECT * FROM TIPO_CARGAS_DE_BUQUES B JOIN TIPOS_DE_CARGAS C ON B.ID_TIPO_CARGA=C.ID WHERE ID_BUQUE ="+idn;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			tipos.add(new TipoDeCarga(id, nombre));
		}

		return tipos;
	}

	/**
	 * Método que agrega el Administrador que entra como parámetro a la base de datos.
	 * @param Administrador - el Administrador a agregar. Administrador !=  null
	 * <b> post: </b> se ha agregado el Administrador a la base de datos en la transaction actual. pendiente que el Administrador master
	 * haga commit para que el Administrador baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Administrador a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addTipoCarga(int tipoCarga, int buque) throws SQLException, Exception {

		String sql = "INSERT INTO TIPO_CARGAS_DE_BUQUES VALUES (";
		sql += buque + ",";
		sql += tipoCarga + ")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		//PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt);
		//recursos.add(prepStmt2);
		prepStmt.executeUpdate();
		//prepStmt2.executeUpdate();

	}

	/**
	 * Método que elimina el Administrador que entra como parámetro en la base de datos.
	 * @param Administrador - el Administrador a borrar. Administrador !=  null
	 * <b> post: </b> se ha borrado el Administrador en la base de datos en la transaction actual. pendiente que el Administrador master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Administrador.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteTipoCargaBarco(int buque, int carga) throws SQLException, Exception {

		String sql = "DELETE FROM TIPO_CARGAS_DE_BUQUES";
		sql += " WHERE id_buque= "+buque+" AND "+"id_tipo_carga";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeUpdate();
	}
}
