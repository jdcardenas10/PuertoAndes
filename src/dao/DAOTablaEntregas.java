package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.Carga;
import vos.Entrega;
import vos.Factura;
import vos.Importador;
import vos.TipoDeCarga;

public class DAOTablaEntregas {

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
	public DAOTablaEntregas() {
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
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<Entrega> darEntregas() throws Exception {
		ArrayList<Entrega> entregas = new ArrayList<Entrega>();

		String sql = "select * from ENTREGAS NATURAL JOIN OPERACIONES";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()){

			int id = Integer.parseInt(rs.getString("ID"));
			char tipo = rs.getString("TIPO").charAt(0);
			Date fecha=null;
			int idCarga=Integer.parseInt(rs.getString("ID_CARGA"));

			String sql2 = "select * from CARGAS WHERE ID="+idCarga;
			PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
			recursos.add(prepStmt2);
			ResultSet rs2 = prepStmt2.executeQuery();
			Carga carga=null;

			while (rs2.next())
			{
				String nombre2=rs2.getString("NOMBRE");
				Double peso=Double.parseDouble(rs2.getString("PESO"));
				char estado2=rs2.getString("ESTADO").charAt(0);
				int dias2=Integer.parseInt(rs2.getString("DIAS_EN_PUERTO"));
				int tipoCarga=Integer.parseInt(rs2.getString("ID_TIPO_CARGA"));

				String sql3 = "select * from TIPOS_DE_CARGAS WHERE ID="+tipoCarga;
				PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
				recursos.add(prepStmt3);
				ResultSet rs3 = prepStmt3.executeQuery();
				TipoDeCarga tipo2=null;
				while(rs3.next())
				{

					String nombre4=rs3.getString("NOMBRE");

					tipo2=new TipoDeCarga(tipoCarga,nombre4);
				}

				carga=new Carga(id,nombre2,peso,estado2,dias2,tipo2);
			}
			entregas.add(new Entrega(id,tipo,fecha,carga));
		}
		return entregas;	
	}

	public void addEntrega(int idEntrega, int idCarga, int idImportador,int factura) throws SQLException, Exception {

		/*String sql = "INSERT INTO OPERACIONES VALUES (";
		sql += idEntrega + ",'";
		sql += factura + "','";
		sql +=  "E','";
		sql +=  "')";
		
		sql += idCarga +"')";*/
		String sql2="INSERT INTO ENTREGAS VALUES ("
				+idEntrega+",'"
				+idImportador+"')";


		//System.out.println("SQL stmt:" + sql);

		//PreparedStatement prepStmt = conn.prepareStatement(sql);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		//recursos.add(prepStmt);
		recursos.add(prepStmt2);
		//prepStmt.executeUpdate();
		prepStmt2.executeUpdate();

	}

	public void deleteEntrega(Entrega entrega) throws SQLException, Exception {

		String sql = "DELETE FROM VIDEOS";
		sql += " WHERE id = " + entrega.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}
