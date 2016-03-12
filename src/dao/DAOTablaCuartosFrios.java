package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.CuartoFrio;


public class DAOTablaCuartosFrios {

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
	public DAOTablaCuartosFrios() {
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
	public ArrayList<CuartoFrio> darCuartosFrios() throws SQLException, Exception {
		ArrayList<CuartoFrio> areas = new ArrayList<CuartoFrio>();

		String sql = "select * from CUARTOS_FRIOS";
	
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString("ID"));
			Double largo=Double.parseDouble(rs.getString("LARGO"));
			Double ancho=Double.parseDouble(rs.getString("ANCHO"));
			Double alto=Double.parseDouble(rs.getString("ALTO"));
			Double areaEnFuncion=Double.parseDouble(rs.getString("AREA_EN_FUNCION"));
			Boolean libre=true;
			if(rs.getString("LIBRE").equals("N")){libre=false;}
			areas.add(new CuartoFrio(id,largo,ancho,alto,areaEnFuncion,libre));
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
	public CuartoFrio buscarCuartoFrioPorID(int idn) throws SQLException, Exception {
		CuartoFrio cuarto=null;

		String sql = "SELECT * from CUARTOS_FRIOS WHERE ID ="+idn;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			Double largo=Double.parseDouble(rs.getString("LARGO"));
			Double ancho=Double.parseDouble(rs.getString("ANCHO"));
			Double alto=Double.parseDouble(rs.getString("ALTO"));
			Double areaEnFuncion=Double.parseDouble(rs.getString("AREA_EN_FUNCION"));
			Boolean libre=true;
			if(rs.getString("LIBRE").equals("N")){libre=false;}
			cuarto=new CuartoFrio(id,largo,ancho,alto,areaEnFuncion,libre);
		}

		return cuarto;
	}

	/**
	 * Método que agrega el Area que entra como parámetro a la base de datos.
	 * @param Area - el Area a agregar. Area !=  null
	 * <b> post: </b> se ha agregado el Area a la base de datos en la transaction actual. pendiente que el Area master
	 * haga commit para que el Area baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Area a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addCuarto(CuartoFrio cuarto, int idBodega) throws SQLException, Exception {

		String libre="'S'";
		if (!cuarto.isLibre()){libre="'N'";}
		
		String sql = "INSERT INTO CUARTOS_FRIOS VALUES (";
		sql += cuarto.getId() + ", ";
		sql += idBodega + ", ";
		sql += cuarto.getLargo() + ", ";
		sql += cuarto.getAncho() + ", ";
		sql += cuarto.getAlto() + ", ";
		sql += cuarto.getAreaEnFuncion() + ", ";
		sql += libre + ")";
		
		
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
	public void updateCuartoFrio(CuartoFrio cuarto) throws SQLException, Exception {
		String libre="'S'";
		if (!cuarto.isLibre()){libre="'N'";}
		
		String sql = "UPDATE CUARTOS_FRIOS SET ";
		sql += "largo=" + cuarto.getLargo() + ", ";
		sql += "ancho=" + cuarto.getAncho()+", ";
		sql += "largo=" + cuarto.getAlto() + ", ";
		sql += "ancho=" + cuarto.getAreaEnFuncion()+", ";
		sql += "libre=" + libre;
		sql += " WHERE id = " + cuarto.getId();

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
	public void deleteCuarto(CuartoFrio cuarto) throws SQLException, Exception {

		String sql = "DELETE FROM CUARTOS_FRIOS";
		sql += " WHERE id = " + cuarto.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeUpdate();
	}
}
