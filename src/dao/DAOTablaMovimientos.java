package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vos.Administrador;
import vos.Carga;
import vos.Movimiento;
import vos.TipoDeCarga;

public class DAOTablaMovimientos {

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
	public DAOTablaMovimientos() {
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
	
	public ArrayList<Movimiento> darMovimientos() throws SQLException{
		ArrayList<Movimiento> movimientos=new ArrayList<Movimiento>();
		String sql="SELECT * FROM MOVIMIENTOS NATURAL JOIN OPERACIONES";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString("ID"));
			int idOrigen = rs.getInt("ID_ORIGEN");
			int idDestino = rs.getInt("ID_DESTINO");
			String operador=rs.getString("ID_OPERADOR");
			String factura = rs.getString("ID_FACTURA");
			char tipo = rs.getString("TIPO").charAt(0);
			String fecha=rs.getString("FECHA");
			String idCarga=rs.getString("ID_CARGA");
			
			
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
			movimientos.add(new Movimiento(id, tipo, null, carga,idOrigen,idDestino));
		}
		return movimientos;
	}

	public List<Movimiento> obtenerMovimientosRFC9(int valor, int tipo, int exportador) throws SQLException {
		ArrayList<Movimiento> movimientos=new ArrayList<Movimiento>();
		String sql="select * from (select e1.* from(select * from facturas f where f.VALOR_TOTAL>"+valor+"AND f.id_exportador="+exportador+")e"
                   +"join (select * from operaciones natural join movimientos)e1 on e.id=e1.id_factura)e3"
                   +"NATURAL JOIN"
                   +"(select * from(select c.ID ID_CARGA,c.ID_TIPO_CARGA ID from cargas c where c.ID_TIPO_CARGA="+tipo+")NATURAL JOIN TIPOS_DE_CARGAS)";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString("ID"));
			int idOrigen = rs.getInt("ID_ORIGEN");
			int idDestino = rs.getInt("ID_DESTINO");
			String operador=rs.getString("ID_OPERADOR");
			String factura = rs.getString("ID_FACTURA");
			char tipo1 = rs.getString("TIPO").charAt(0);
			String fecha=rs.getString("FECHA");
			String idCarga=rs.getString("ID_CARGA");
			
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
			
			movimientos.add(new Movimiento(id, tipo1, null, carga,idOrigen,idDestino));
		}
		return movimientos;
	}

	public List<Movimiento> obtenerMovimientosRFC10(int id1, int id2) throws Exception {
		ArrayList<Movimiento> movimientos=new ArrayList<Movimiento>();
		String sql="SELECT * FROM ((SELECT * FROM AREAS WHERE ID IN ("+id1+","+id2+"))"
			   + "NATURAL JOIN (SELECT * FROM MOVIMIENTOS NATURAL JOIN OPERACIONES WHERE ID_ORIGEN IN "
			   +"("+id1+","+id2+") OR ID_DESTINO IN ("+id1+","+id2+")))";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString("ID"));
			int idOrigen = rs.getInt("ID_ORIGEN");
			int idDestino = rs.getInt("ID_DESTINO");
			String operador=rs.getString("ID_OPERADOR");
			String factura = rs.getString("ID_FACTURA");
			char tipo1 = rs.getString("TIPO").charAt(0);
			String fecha=rs.getString("FECHA");
			String idCarga=rs.getString("ID_CARGA");
			
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
			
			movimientos.add(new Movimiento(id, tipo1, null, carga,idOrigen,idDestino));
		}
		
		return movimientos;
	}
}
