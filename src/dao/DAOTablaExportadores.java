package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.Carga;
import vos.Exportador;
import vos.Factura;
import vos.Operacion;
import vos.TipoDeCarga;

public class DAOTablaExportadores {

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Método constructor que crea DAOExportadores
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaExportadores() {
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
	 * Método que, usando la conexión a la base de datos, saca todos los exportadores de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM exportadores;
	 * @return Arraylist con los exportadores de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<Exportador> darExportadores() throws SQLException, Exception {
		
		ArrayList<Exportador> exportadores = new ArrayList<Exportador>();
		String sql = "select * from EXPORTADORES NATURAL JOIN USUARIOS";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		//recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			String login = rs.getString("LOGIN");
			String clave = rs.getString("CLAVE");
			String rut = rs.getString("RUT");
			char naturaleza = rs.getString("NATURALEZA").charAt(0);
			
			String sql1 = "select * from CARGAS WHERE ID_IMPORTADOR="+id;
            PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
			//recursos.add(prepStmt1);
			ResultSet rs1 = prepStmt1.executeQuery();
			ArrayList<Carga> cargas=new ArrayList<Carga>();
			
			while(rs1.next()){
				
				int id1 =Integer.parseInt(rs1.getString("ID"));
				String nombre1=rs1.getString("NOMBRE");
				Double peso=Double.parseDouble(rs1.getString("PESO"));
				int dias=Integer.parseInt(rs1.getString("DIAS_EN_PUERTO"));
				char estado=rs1.getString(rs1.getString("ESTADO")).charAt(0);
				int tipoCarga=Integer.parseInt(rs1.getString("ID_TIPO_CARGA"));
				
				String sql2 = "select * from TIPOS_DE_CARGAS WHERE ID="+tipoCarga;
	            PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
				//recursos.add(prepStmt2);
				ResultSet rs2 = prepStmt2.executeQuery();
				TipoDeCarga tipo=null;
				
				String nombre2=rs2.getString("NOMBRE");
				rs2.close();
				prepStmt2.close();
				
				tipo=new TipoDeCarga(tipoCarga,nombre2);
				
				cargas.add(new Carga(id1,nombre1,peso,estado,dias,tipo));
				
			}
			rs1.close();
			prepStmt1.close();
	
			String sql3 = "select * from FACTURAS WHERE ID_EXPORTADOR="+id;
            PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
			//recursos.add(prepStmt3);
			ResultSet rs3 = prepStmt3.executeQuery();
			ArrayList<Factura> facturas=new ArrayList<Factura>();
			while(rs3.next()){
				
				int id1 =Integer.parseInt(rs3.getString("ID"));
				float valorTotal= Float.parseFloat(rs3.getString("VALOR_TOTAL"));
				//Date fecha=new Date(rs3.getString("FECHA"));
				
				String sql2 = "select * from OPERACIONES WHERE ID_FACTURA="+id1;
	            PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
				//recursos.add(prepStmt2);
				ResultSet rs2 = prepStmt2.executeQuery();
				ArrayList<Operacion> operaciones=new ArrayList<Operacion>();
				while(rs2.next()){
					
					int id2 =Integer.parseInt(rs2.getString("ID"));
					char tipo2=rs2.getString("TIPO").charAt(0);
					//Date fecha1=new Date(rs2.getString("FECHA"));
					String idCarga=rs2.getString("ID_CARGA");
					
					String sql4 = "select * from CARGAS WHERE ID="+idCarga;
		            PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
					//recursos.add(prepStmt4);
					ResultSet rs4 = prepStmt4.executeQuery();
					Carga carga=null;
					while(rs4.next()){
					String nombre2=rs4.getString("NOMBRE");
					Double peso=Double.parseDouble(rs4.getString("PESO"));
					char estado2=rs4.getString("ESTADO").charAt(0);
					int dias2=Integer.parseInt(rs4.getString("DIAS_EN_PUERTO"));
					
					carga=new Carga(id,nombre2,peso,estado2,dias2,null);
					}
					operaciones.add(new Operacion(id2,tipo2,null,carga));
				}
				rs2.close();
				prepStmt2.close();
				
				facturas.add(new Factura(id1,valorTotal,null,operaciones));
			}
			rs3.close();
			prepStmt3.close();
			exportadores.add(new Exportador(id, nombre, login,clave,rut,naturaleza, facturas,cargas));
		}
		rs.close();
		prepStmt.close();
		return exportadores;
	}


	/**
	 * Método que busca el/los exportadores con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los Agentes a buscar
	 * @return Arraylist con los Agentes encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	@SuppressWarnings("deprecation")
	public Exportador buscarExportadorPorID(int idn) throws SQLException, Exception {
		Exportador exportador=null;

		String sql = "SELECT * from EXPORTADORES NATURAL JOIN USUARIOS WHERE ID ="+idn;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {

			int id = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			String login = rs.getString("LOGIN");
			String clave = rs.getString("CLAVE");
			String rut = rs.getString("RUT");
			char naturaleza = rs.getString("NATURALEZA").charAt(0);
			
			String sql1 = "select * from CARGAS WHERE ID_IMPORTADOR="+id;
            PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
			//recursos.add(prepStmt1);
			ResultSet rs1 = prepStmt1.executeQuery();
			ArrayList<Carga> cargas=new ArrayList<Carga>();
			
			while(rs1.next()){
				
				int id1 =Integer.parseInt(rs1.getString("ID"));
				String nombre1=rs1.getString("NOMBRE");
				Double peso=Double.parseDouble(rs1.getString("PESO"));
				int dias=Integer.parseInt(rs1.getString("DIAS_EN_PUERTO"));
				char estado=rs1.getString(rs1.getString("ESTADO")).charAt(0);
				int tipoCarga=Integer.parseInt(rs1.getString("ID_TIPO_CARGA"));
				
				String sql2 = "select * from TIPOS_DE_CARGAS WHERE ID="+tipoCarga;
	            PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
				//recursos.add(prepStmt2);
				ResultSet rs2 = prepStmt2.executeQuery();
				TipoDeCarga tipo=null;
				
				String nombre2=rs2.getString("NOMBRE");
				rs2.close();
				prepStmt2.close();
				
				tipo=new TipoDeCarga(tipoCarga,nombre2);
				
				cargas.add(new Carga(id1,nombre1,peso,estado,dias,tipo));
				
			}
			rs1.close();
			prepStmt1.close();
	
			String sql3 = "select * from FACTURAS WHERE ID_EXPORTADOR="+id;
            PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
			//recursos.add(prepStmt3);
			ResultSet rs3 = prepStmt3.executeQuery();
			ArrayList<Factura> facturas=new ArrayList<Factura>();
			while(rs3.next()){
				
				int id1 =Integer.parseInt(rs3.getString("ID"));
				float valorTotal= Float.parseFloat(rs3.getString("VALOR_TOTAL"));
				//Date fecha=new Date(rs3.getString("FECHA"));
				
				String sql2 = "select * from OPERACIONES WHERE ID_FACTURA="+id1;
	            PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
				//recursos.add(prepStmt2);
				ResultSet rs2 = prepStmt2.executeQuery();
				ArrayList<Operacion> operaciones=new ArrayList<Operacion>();
				while(rs2.next()){
					
					int id2 =Integer.parseInt(rs2.getString("ID"));
					char tipo2=rs2.getString("TIPO").charAt(0);
					//Date fecha1=new Date(rs2.getString("FECHA"));
					String idCarga=rs2.getString("ID_CARGA");
					
					String sql4 = "select * from CARGAS WHERE ID="+idCarga;
		            PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
					//recursos.add(prepStmt4);
					ResultSet rs4 = prepStmt4.executeQuery();
					Carga carga=null;
					while(rs4.next()){
					String nombre2=rs4.getString("NOMBRE");
					Double peso=Double.parseDouble(rs4.getString("PESO"));
					char estado2=rs4.getString("ESTADO").charAt(0);
					int dias2=Integer.parseInt(rs4.getString("DIAS_EN_PUERTO"));
					
					carga=new Carga(id,nombre2,peso,estado2,dias2,null);
					}
					operaciones.add(new Operacion(id2,tipo2,null,carga));
				}
				rs2.close();
				prepStmt2.close();
				
				facturas.add(new Factura(id1,valorTotal,null,operaciones));
			}
			rs3.close();
			prepStmt3.close();
			exportador=new Exportador(id, nombre, login,clave,rut,naturaleza, facturas,cargas);
		}
		rs.close();
		prepStmt.close();

		return exportador;
	}

	/**
	 * Método que agrega el Agente que entra como parámetro a la base de datos.
	 * @param Agente - el Agente a agregar. Agente !=  null
	 * <b> post: </b> se ha agregado el Agente a la base de datos en la transaction actual. pendiente que el Agente master
	 * haga commit para que el Agente baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Agente a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addExportador(Exportador exportador) throws SQLException, Exception {

		String sql = "INSERT INTO Usuarios VALUES (";
		sql += exportador.getId() + ",'";
		sql += exportador.getNombre() + "','";
		sql += exportador.getLogin() + "','";
		sql += exportador.getClave() +"')";
		
		String sql2="INSERT INTO EXPORTADORES VALUES ("
				+exportador.getId()+",'"
			    +exportador.getRut()+"','"
		        +exportador.getNaturaleza()+"')";
		

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt);
		recursos.add(prepStmt2);
		prepStmt.executeUpdate();
		prepStmt2.executeUpdate();

	}
	
	/**
	 * Método que actualiza el Agente que entra como parámetro en la base de datos.
	 * @param Agente - el Agente a actualizar. Agente !=  null
	 * <b> post: </b> se ha actualizado el Agente en la base de datos en la transaction actual. pendiente que el Agente master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Agente.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateExportador(Exportador exportador) throws SQLException, Exception {

		String sql = "UPDATE USUARIOS SET ";
		sql += "nombre='" + exportador.getNombre() + "', ";
		sql += "login='" + exportador.getLogin()+"', ";
		sql += "clave='" + exportador.getClave()+"'";
		sql += " WHERE id = " + exportador.getId();
		
		String sql1 = "UPDATE IMPORTADORES SET ";
		sql1 += "rut='" + exportador.getRut() + "', ";
		sql1 += "naturaleza='" + exportador.getNaturaleza()+"', ";
		sql1 += " WHERE id = " + exportador.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
		recursos.add(prepStmt);
		recursos.add(prepStmt1);
		prepStmt.executeUpdate();
		prepStmt1.executeUpdate();
	}

	/**
	 * Método que elimina el Agente que entra como parámetro en la base de datos.
	 * @param Agente - el Agente a borrar. Agente !=  null
	 * <b> post: </b> se ha borrado el Agente en la base de datos en la transaction actual. pendiente que el Agente master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Agente.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteExportador(Exportador exportador) throws SQLException, Exception {

		String sql0 = "DELETE FROM USUARIOS";
		sql0 += " WHERE id = " + exportador.getId();
		
		String sql = "DELETE FROM IMPORTADORES";
		sql += " WHERE id = " + exportador.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt0 = conn.prepareStatement(sql0);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt0);
		recursos.add(prepStmt);
		prepStmt0.executeUpdate();
		prepStmt.executeUpdate();
	}

	public ArrayList<Exportador> buscarExportadorPor(String tipoExpo, int idBuque, int tipoCarga, String fecha1, String fecha2) throws Exception {
		
		ArrayList<Exportador> exportadores = new ArrayList<Exportador>();
		boolean otro=false;
		
		String sql="select id_exportador,tipo_exportador,rut,nombre_exportador,login,clave,id_buque,nombre_buque,id_carga,peso,estado,dias_en_puerto,nombre_carga, id_tipo_carga,nombre_tipo_carga,fecha ";
        sql += "from operaciones natural join arribos "; 
        sql += "natural join (select id as id_carga, id_exportador, peso, id_tipo_carga, estado,dias_en_puerto,nombre_tipo_carga, nombre as nombre_carga from cargas natural join (select id as id_tipo_carga, nombre as nombre_tipo_carga from tipos_de_cargas)) "; 
        sql += "natural join (select id as id_buque, nombre as nombre_buque from buques) ";
        sql += "natural join (select id as id_exportador, naturaleza as tipo_exportador, rut, nombre as nombre_exportador,login,clave from exportadores natural join usuarios) ";
        
        if(tipoExpo!=null)
        {
        	sql += "where tipo_exportador= '" + tipoExpo+"'"; 
        	otro =true;
        }
        if(idBuque!=0)
        {
        	if(otro=true)
        	{
        		sql += " AND " + "id_buque=" + idBuque;
        	}
        	else
        	{
        		sql += "where id_buque=" + idBuque;
        		otro=true;
        	}
        }
        if(tipoCarga!=0)
        {
        	if(otro=true)
        	{
        		sql += " AND " + "id_tipo_carga=" + tipoCarga;
        	}
        	else
        	{
        		sql += "where id_tipo_carga=" + tipoCarga;
        		otro=true;
        	}
        }
        
        if(!fecha1.equals("nada") && !fecha2.equals("nada"))
        {
        	if(otro=true)
        	{
        		sql += " AND " + "fecha between " + fecha1 + " AND "+fecha2;
        	}
        	else
        	{
        		sql +="where fecha between " + fecha1 + " AND "+fecha2;
        	}
        }
        else
        {
        	if(!fecha1.equals("nada"))
        	{
        		if(otro=true)
            	{
            		sql += " AND " + "fecha = " + fecha1;
            	}
            	else
            	{
            		sql +="where fecha=" + fecha1;
            	}
        	}
        	if(!fecha2.equals("nada"))
        	{
        		if(otro=true)
            	{
            		sql += " AND " + "fecha = " + fecha2;
            	}
            	else
            	{
            		sql +="where fecha=" + fecha2;
            	}
        	}
        }
        System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		int expoActual=0;
		String nombre="";
		String login="";
		String clave="";
		String rut="";
		char naturaleza=' ';
		ArrayList<Carga> cargas = new ArrayList<Carga>();
		
		boolean agregar=false;
		
		while(rs.next())
		{
			int id = Integer.parseInt(rs.getString("ID_EXPORTADOR"));
			
			if(id!=expoActual)
			{
				expoActual=id;
				
				if(expoActual!=0)
				{
					agregar=true;
				}
				
				nombre = rs.getString("NOMBRE_EXPORTADOR");
				login = rs.getString("LOGIN");
				clave = rs.getString("CLAVE");
				rut = rs.getString("RUT");
				naturaleza = rs.getString("TIPO_EXPORTADOR").charAt(0);
				cargas=new ArrayList<Carga>();
				
			}
			
			int idCarga =Integer.parseInt(rs.getString("ID_CARGA"));
			String nombreCarga=rs.getString("NOMBRE_CARGA");
			Double peso=Double.parseDouble(rs.getString("PESO"));
			int dias=Integer.parseInt(rs.getString("DIAS_EN_PUERTO"));
			char estado=rs.getString("ESTADO").charAt(0);
			int tipoCargaCarga=Integer.parseInt(rs.getString("ID_TIPO_CARGA"));
			String nombreTipoCarga = rs.getString("NOMBRE_TIPO_CARGA");
			
			TipoDeCarga t = new TipoDeCarga(tipoCargaCarga, nombreTipoCarga);
			cargas.add(new Carga(idCarga,nombreCarga,peso,estado,dias,t));
			
			
			if(agregar==true)
			{
				exportadores.add(new Exportador(id,nombre,login,clave,rut,naturaleza,null,cargas));
			}	
		}
        	
		return exportadores;
	}
}
