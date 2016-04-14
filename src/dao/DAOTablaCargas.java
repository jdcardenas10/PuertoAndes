package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Carga;
import vos.TipoDeCarga;

public class DAOTablaCargas {


	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Método constructor que crea DAOCargas
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaCargas() {
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
	public ArrayList<Carga> darCargas() throws SQLException, Exception {

		String sql = "SELECT C.*,T.NOMBRE AS TIPO FROM CARGAS C JOIN TIPOS_DE_CARGAS T ON C.ID_TIPO_CARGA=T.ID";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		ArrayList<Carga> cargas=new ArrayList<Carga>();
		while(rs.next()){
			int id1 =Integer.parseInt(rs.getString("ID"));
			String nombre1=rs.getString("NOMBRE");
			Double peso=Double.parseDouble(rs.getString("PESO"));
			int dias=Integer.parseInt(rs.getString("DIAS_EN_PUERTO"));
			char estado=rs.getString(rs.getString("ESTADO")).charAt(0);
			int tipoCarga=Integer.parseInt(rs.getString("ID_TIPO_CARGA"));
			String nombre2=rs.getString("TIPO");
			int area=Integer.parseInt(rs.getString("ID_AREA"));

			TipoDeCarga tipo=new TipoDeCarga(tipoCarga,nombre2);
			
			Carga carga=new Carga(id1,nombre1,peso,estado,dias,tipo);
			carga.setArea(area);
			cargas.add(carga);
		}
		return cargas;
	}


	/**
	 * Método que busca el/los Areas con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los Areas a buscar
	 * @return Arraylist con los Areas encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Carga buscarCargaPorID(int idn) throws SQLException, Exception {
		Carga carga=null;

		String sql = "SELECT C.*,T.NOMBRE AS TIPO FROM CARGAS C JOIN TIPOS_DE_CARGAS T ON C.ID_TIPO_CARGA=T.ID WHERE C.ID="+idn;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next()){
			int id1 =Integer.parseInt(rs.getString("ID"));
			String nombre1=rs.getString("NOMBRE");
			Double peso=Double.parseDouble(rs.getString("PESO"));
			int dias=Integer.parseInt(rs.getString("DIAS_EN_PUERTO"));
			char estado=rs.getString(rs.getString("ESTADO")).charAt(0);
			int tipoCarga=Integer.parseInt(rs.getString("ID_TIPO_CARGA"));
			String nombre2=rs.getString("TIPO");
			int area=Integer.parseInt(rs.getString("ID_AREA"));

			TipoDeCarga tipo=new TipoDeCarga(tipoCarga,nombre2);
			
			carga=new Carga(id1,nombre1,peso,estado,dias,tipo);
			carga.setArea(area);
		}
		return carga;
	}

	/**
	 * Método que agrega el Area que entra como parámetro a la base de datos.
	 * @param Area - el Area a agregar. Area !=  null
	 * <b> post: </b> se ha agregado el Area a la base de datos en la transaction actual. pendiente que el Area master
	 * haga commit para que el Area baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Area a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addCarga(Carga carga) throws SQLException, Exception {

		String sql = "INSERT INTO AREAS VALUES (";
		sql += carga.getId() + ")";
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
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
	public void deleteCarga(Carga carga) throws SQLException, Exception {

		String sql = "DELETE FROM Cargas";
		sql += " WHERE id = " + carga.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeUpdate();
	}

	public void cargar(Carga carga, int buque) throws SQLException {
		String sql = "UPDATE CARGAS SET ";
		sql += "ID_AREA=null, ";
		sql += "ID_BUQUE=" + buque;
		sql += " WHERE id = " + carga.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeUpdate();
		
	}
}
