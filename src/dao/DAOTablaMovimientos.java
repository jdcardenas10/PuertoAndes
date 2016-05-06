package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vos.Administrador;
import vos.Movimiento;

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
			String idOrigen = rs.getString("ID_ORIGEN");
			String ldDestino = rs.getString("ID_DESTINO");
			String operador=rs.getString("ID_OPERADOR");
			String factura = rs.getString("ID_FACTURA");
			char tipo = rs.getString("TIPO").charAt(0);
			String fecha=rs.getString("FECHA");
			String idCarga=rs.getString("ID_CARGA");
			movimientos.add(new Movimiento(id, tipo, null, null));
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
			String idOrigen = rs.getString("ID_ORIGEN");
			String ldDestino = rs.getString("ID_DESTINO");
			String operador=rs.getString("ID_OPERADOR");
			String factura = rs.getString("ID_FACTURA");
			char tipo1 = rs.getString("TIPO").charAt(0);
			String fecha=rs.getString("FECHA");
			String idCarga=rs.getString("ID_CARGA");
			movimientos.add(new Movimiento(id, tipo1, null, null));
		}
		return movimientos;
	}
}
