package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vos.Carga;
import vos.Descargue;
import vos.TipoDeCarga;

public class DAOTablaDescargues {
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
	public DAOTablaDescargues() {
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

	public ArrayList<Descargue> addDescarga(int buque) throws Exception{
		
		ArrayList<Descargue> descargues = new ArrayList<Descargue>();
		
		ArrayList<Carga> cargas = new ArrayList<Carga>();
		
		conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		conn.setAutoCommit(false);
		
		String sql = "SELECT * FROM CARGAS";
		sql +=" WHERE ID_BUQUE="+buque;
		sql +=" AND ID_PUERTO_DESTINO= 0";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {

			int idCarga =rs.getInt("ID");
			String nombreCarga=rs.getString("NOMBRE");
			Double peso=Double.parseDouble(rs.getString("PESO"));
			int dias=Integer.parseInt(rs.getString("DIAS_EN_PUERTO"));
			char estado=rs.getString("ESTADO").charAt(0);
			int tipoCarga=rs.getInt("ID_TIPO_CARGA");
			
			cargas.add(new Carga(idCarga,nombreCarga,peso,estado,dias,new TipoDeCarga(tipoCarga,null)));
		}
		rs.close();
		conn.setSavepoint();
		
		for(int i=0;i<cargas.size();i++)
		{
			String sql1 = "SELECT * FROM AREA";
			sql1 +=" WHERE TIPO_DE_CARGA="+cargas.get(i);
			sql1 +=" AND maximo_cargas>cantidad_cargas_actual";

			System.out.println("SQL stmt:" + sql1);

			PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
			recursos.add(prepStmt1);
			ResultSet rs1 = prepStmt1.executeQuery();
			while (rs1.next())
			{
				
			}
		}
		
		return descargues;
	}
}
