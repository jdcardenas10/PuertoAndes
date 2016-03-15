package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import vos.Administrador;
import vos.Carga;
import vos.Factura;
import vos.Operacion;
import vos.TipoDeCarga;

public class DAOTablaFacturas {

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
	public DAOTablaFacturas() {
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

	
	public ArrayList<Factura> darFacturas() throws Exception {
		ArrayList<Factura> facturas = new ArrayList<Factura>();

		String sql = "select * from FACTURAS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString("ID"));
			double valor = Double.parseDouble(rs.getString("VALOR_TOTAL"));
			Date fecha=null;
			
			
			String sql1 = "select * from OPERACIONES WHERE ID_FACTURA="+id;
			PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
			recursos.add(prepStmt1);
			ResultSet rs1 = prepStmt1.executeQuery();
			ArrayList<Operacion> operaciones = new ArrayList<Operacion>();
			
			while(rs1.next())
			{
				int id1 = Integer.parseInt(rs1.getString("ID"));
				char tipo = rs1.getString("TIPO").charAt(0);
				Date fecha2 = null;
				String idC =rs1.getString("ID_CARGA");
				Carga carga=null;
				if(idC!=null)
				{
					int idCarga=Integer.parseInt(idC);

					String sql2 = "select * from CARGAS WHERE ID="+idCarga;
					PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
					recursos.add(prepStmt2);
					ResultSet rs2 = prepStmt2.executeQuery();
					while(rs2.next())
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
						TipoDeCarga tipo4=null;
						String nombre4=null;
						while(rs3.next()){
							nombre4=rs3.getString("NOMBRE");}
						prepStmt3.close();
						tipo4=new TipoDeCarga(tipoCarga,nombre4);

						carga=new Carga(id,nombre2,peso,estado2,dias2,tipo4);
					}
					prepStmt2.close();
				}
				operaciones.add(new Operacion(id1,tipo,fecha2,carga));				
			}
			facturas.add(new Factura(id,valor,fecha,operaciones));
		}
		return facturas;
	}


	public void addEntrega(int idBuque) {
		
		
	}
	
	

}
