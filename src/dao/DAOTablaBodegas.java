package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Bodega;
import vos.CuartoFrio;
import vos.TipoDeCarga;


public class DAOTablaBodegas {

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
	public DAOTablaBodegas() {
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
	public ArrayList<Bodega> darBodegas() throws SQLException, Exception {
		ArrayList<Bodega> areas = new ArrayList<Bodega>();

		String sql = "select * from AREAS NATURAL JOIN BODEGAS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString("ID"));
			char estado = rs.getString("ESTADO").charAt(0);
			char tipo= rs.getString("TIPO").charAt(0);
			
			Double ancho=Double.parseDouble(rs.getString("ANCHO"));
			Double largo=Double.parseDouble(rs.getString("LARGO"));
			Boolean plataformaExterna=false;
			if(rs.getString("PLATAFORMA_EXTERNA").equals("S")){plataformaExterna=true;}
			
			int tipoCarga=Integer.parseInt(rs.getString("ID_TIPO_CARGA"));
			String sql1 = "select * from TIPOS_DE_CARGAS WHERE ID="+tipoCarga;
			TipoDeCarga tipoDeCarga=null;
			PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
			recursos.add(prepStmt1);
			ResultSet rs1 = prepStmt1.executeQuery();
			while(rs1.next()){
				int id1=Integer.parseInt(rs1.getString("ID"));
				String nombre=rs1.getString("NOMBRE");
				tipoDeCarga=new TipoDeCarga(id1,nombre);
			}
			Double separacion=Double.parseDouble(rs.getString("SEPARACION"));
			
			String sql2="SELECT * FROM CUARTOS_FRIOS WHERE ID_BODEGA="+id;
			PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
			recursos.add(prepStmt2);
			ResultSet rs2 = prepStmt2.executeQuery();
			
			ArrayList<CuartoFrio> cuartos=new ArrayList<CuartoFrio>();
			while(rs2.next()){
				int id3 = Integer.parseInt(rs.getString("ID"));
				Double largo3=Double.parseDouble(rs.getString("LARGO"));
				Double ancho3=Double.parseDouble(rs.getString("ANCHO"));
				Double alto=Double.parseDouble(rs.getString("ALTO"));
				Double areaEnFuncion=Double.parseDouble(rs.getString("AREA_EN_FUNCION"));
				Boolean libre=true;
				if(rs.getString("LIBRE").equals("N")){libre=false;}
				cuartos.add(new CuartoFrio(id3,largo3,ancho3,alto,areaEnFuncion,libre));
			}
			
			areas.add(new Bodega(id, estado,tipo,ancho, largo, plataformaExterna, tipoDeCarga,separacion, cuartos));
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
	public Bodega buscarAreaPorID(int idn) throws SQLException, Exception {
		Bodega area=null;

		String sql = "SELECT * from AREAS NATURAL JOIN BODEGAS WHERE ID ="+idn;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			char estado = rs.getString("ESTADO").charAt(0);
			char tipo= rs.getString("TIPO").charAt(0);
			
			Double ancho=Double.parseDouble(rs.getString("ANCHO"));
			Double largo=Double.parseDouble(rs.getString("LARGO"));
			Boolean plataformaExterna=false;
			if(rs.getString("PLATAFORMA_EXTERNA").equals("S")){plataformaExterna=true;}
			
			int tipoCarga=Integer.parseInt(rs.getString("ID_TIPO_CARGA"));
			String sql1 = "select * from TIPOS_DE_CARGAS WHERE ID="+tipoCarga;
			TipoDeCarga tipoDeCarga=null;
			PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
			recursos.add(prepStmt1);
			ResultSet rs1 = prepStmt1.executeQuery();
			while(rs1.next()){
				int id1=Integer.parseInt(rs1.getString("ID"));
				String nombre=rs1.getString("NOMBRE");
				tipoDeCarga=new TipoDeCarga(id1,nombre);
			}
			Double separacion=Double.parseDouble(rs.getString("SEPARACION"));
			
			String sql2="SELECT * FROM CUARTOS_FRIOS WHERE ID_BODEGA="+id;
			PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
			recursos.add(prepStmt2);
			ResultSet rs2 = prepStmt2.executeQuery();
			
			ArrayList<CuartoFrio> cuartos=new ArrayList<CuartoFrio>();
			while(rs2.next()){
				int id3 = Integer.parseInt(rs.getString("ID"));
				Double largo3=Double.parseDouble(rs.getString("LARGO"));
				Double ancho3=Double.parseDouble(rs.getString("ANCHO"));
				Double alto=Double.parseDouble(rs.getString("ALTO"));
				Double areaEnFuncion=Double.parseDouble(rs.getString("AREA_EN_FUNCION"));
				Boolean libre=true;
				if(rs.getString("LIBRE").equals("N")){libre=false;}
				cuartos.add(new CuartoFrio(id3,largo3,ancho3,alto,areaEnFuncion,libre));
			}
			
			area=new Bodega(id,estado,tipo, ancho, largo, plataformaExterna, tipoDeCarga,separacion, cuartos);
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
	public void addArea(Bodega area) throws SQLException, Exception {

		String sql0="INSERT INTO AREAS VALUES("+area.getId()+")";
		
		String plataforma="'N'";
		if(area.isPlataformaExterna()){plataforma="'S'";}
		
		String sql = "INSERT INTO BODEGAS VALUES (";
		sql += area.getId() + ",";
		sql += area.getAncho() + ",";
		sql += area.getLargo() + ",";
		sql += plataforma + ",";
		sql += area.getTipoDeCarga().getId() + ",";
		sql += area.getSeparacion() + ")";
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		PreparedStatement prepStmt0 = conn.prepareStatement(sql0);
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
	public void deleteArea(Bodega area) throws SQLException, Exception {

		String sql0 = "DELETE FROM CUARTOS_FRIOS";
		sql0 += " WHERE ID_BODEGA = " + area.getId();
		
		String sql = "DELETE FROM BODEGAS";
		sql += " WHERE id = " + area.getId();
		
		String sql2 = "DELETE FROM AREAS";
		sql2 += " WHERE id = " + area.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt0 = conn.prepareStatement(sql0);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt0);
		recursos.add(prepStmt);
		recursos.add(prepStmt2);
		prepStmt0.executeUpdate();
		prepStmt.executeUpdate();
		prepStmt2.executeUpdate();
	}
}
