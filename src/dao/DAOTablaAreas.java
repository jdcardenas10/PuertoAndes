package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;

import vos.Area;
import vos.Bodega;
import vos.Carga;
import vos.Cobertizo;
import vos.ConsultaAreas;
import vos.Operacion;
import vos.Patio;
import vos.Silo;
import vos.TipoDeCarga;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 * @author José Daniel
 */
public class DAOTablaAreas {

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
	public DAOTablaAreas() {
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
	public ArrayList<Area> darAreas(ConsultaAreas consulta) throws SQLException, Exception {
		
		ArrayList<Area> areas = new ArrayList<Area>();
		
		boolean masDatos=false;
		
		String sql = "select * from AREAS";
		if(consulta.getEstado()!=' ')
		{
			sql+=" WHERE ESTADO= '"+consulta.getEstado()+"'";
			masDatos=true;
		}
		if(consulta.getTipo()!=' ')
		{
			if(masDatos==true)
			{
				sql+=" AND TIPO= '"+consulta.getTipo()+"'";
			}
			else
			{
				sql+=" WHERE TIPO= '"+consulta.getTipo()+"'";
				masDatos=true;
			}
		}
		if(consulta.getCantidadCargas()!=-1)
		{
			if(masDatos==true)
			{
				sql+=" AND CANTIDAD_CARGAS_ACTUAL= "+consulta.getCantidadCargas();
			}
			else
			{
				sql+=" WHERE CANTIDAD_CARGAS_ACTUAL= "+consulta.getCantidadCargas();
				masDatos=true;
			}
		}
		if(consulta.getCantidadMaxima()!=-1)
		{
			if(masDatos==true)
			{
				sql+=" AND MAXIMO_CARGAS= "+consulta.getCantidadMaxima();
			}
			else
			{
				sql+=" WHERE MAXIMO_CARGAS= "+consulta.getCantidadMaxima();
				masDatos=true;
			}
		}

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{

			int id = Integer.parseInt(rs.getString("ID"));
			char estado = rs.getString("ESTADO").charAt(0);
			char tipo= rs.getString("TIPO").charAt(0);

			ArrayList<Operacion> entradas=new ArrayList<Operacion>();
			ArrayList<Operacion> salidas=new ArrayList<Operacion>();


			String sql1 = "select * from OPERACIONES NATURAL JOIN CARGUES WHERE ID_AREA="+id;
			PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
			recursos.add(prepStmt1);
			ResultSet rs1 = prepStmt1.executeQuery();
			while (rs1.next())
			{

				int idC= rs1.getInt("ID");
				char tipoC=rs1.getString("TIPO").charAt(0);
				int idCarga=rs1.getInt("ID_CARGA");

				String sql11 = "select * from CARGAS WHERE ID="+idCarga;
				PreparedStatement prepStmt11 = conn.prepareStatement(sql11);
				recursos.add(prepStmt11);
				ResultSet rs11 = prepStmt11.executeQuery();
				Carga carga=null;
				while(rs11.next())
				{

					String nombre1=rs11.getString("NOMBRE");
					Double peso=Double.parseDouble(rs11.getString("PESO"));
					int dias=Integer.parseInt(rs11.getString("DIAS_EN_PUERTO"));
					char estadoCarga=rs11.getString("ESTADO").charAt(0);
					int tipoCarga=Integer.parseInt(rs11.getString("ID_TIPO_CARGA"));

					String sql12 = "select * from TIPOS_DE_CARGAS WHERE ID="+tipoCarga;
					PreparedStatement prepStmt12 = conn.prepareStatement(sql12);
					recursos.add(prepStmt12);
					ResultSet rs12 = prepStmt12.executeQuery();
					TipoDeCarga tipoDeCarga;
					String nombre2=null;
					while(rs12.next())
					{
						nombre2=rs12.getString("NOMBRE");
					}
					prepStmt12.close();
					tipoDeCarga= new TipoDeCarga(tipoCarga,nombre2);

					carga= new Carga(idCarga,nombre1,peso,estadoCarga,dias,tipoDeCarga);
				}
				prepStmt11.close();
				salidas.add(new Operacion(idC,tipoC,null,carga));
			}
			prepStmt1.close();

			String sql21 = "select * from OPERACIONES NATURAL JOIN DESCARGUES WHERE ID_AREA="+id;
			PreparedStatement prepStmt21 = conn.prepareStatement(sql21);
			recursos.add(prepStmt21);
			ResultSet rs21 = prepStmt21.executeQuery();
			while (rs21.next())
			{

				int idC= rs21.getInt("ID");
				char tipoC=rs21.getString("TIPO").charAt(0);
				int idCarga=rs21.getInt("ID_CARGA");

				String sql211 = "select * from CARGAS WHERE ID="+idCarga;
				PreparedStatement prepStmt211 = conn.prepareStatement(sql211);
				recursos.add(prepStmt211);
				ResultSet rs211 = prepStmt211.executeQuery();
				Carga carga=null;
				while(rs211.next())
				{

					String nombre1=rs211.getString("NOMBRE");
					Double peso=Double.parseDouble(rs211.getString("PESO"));
					int dias=Integer.parseInt(rs211.getString("DIAS_EN_PUERTO"));
					char estadoCarga=rs211.getString("ESTADO").charAt(0);
					int tipoCarga=Integer.parseInt(rs211.getString("ID_TIPO_CARGA"));

					String sql212 = "select * from TIPOS_DE_CARGAS WHERE ID="+tipoCarga;
					PreparedStatement prepStmt212 = conn.prepareStatement(sql212);
					recursos.add(prepStmt212);
					ResultSet rs212 = prepStmt212.executeQuery();
					TipoDeCarga tipoDeCarga;
					String nombre2=null;
					while(rs212.next())
					{
						nombre2=rs212.getString("NOMBRE");
					}
					prepStmt212.close();					
					tipoDeCarga= new TipoDeCarga(tipoCarga,nombre2);

					carga= new Carga(idCarga,nombre1,peso,estadoCarga,dias,tipoDeCarga);
				}
				prepStmt211.close();
				entradas.add(new Operacion(idC,tipoC,null,carga));
			}
			prepStmt21.close();

			if(tipo=='B')
			{
				String sql2 = "SELECT * from BODEGAS WHERE ID="+id;

				System.out.println("SQL stmt2:" + sql2);

				PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
				recursos.add(prepStmt2);
				ResultSet rs2 = prepStmt2.executeQuery();
				while (rs2.next())
				{
					double ancho=rs2.getDouble("ANCHO");
					double largo=rs2.getDouble("LARGO");
					boolean plataformaExterna=false;
					if(rs2.getString("PLATAFORMA_EXTERNA").charAt(0)=='S')
					{
						plataformaExterna=true;
					}

					int tipoCarga=rs2.getInt("ID_TIPO_CARGA");

					String sql3 = "select * from TIPOS_DE_CARGAS WHERE ID="+tipoCarga;
					PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
					recursos.add(prepStmt3);
					ResultSet rs3 = prepStmt3.executeQuery();
					TipoDeCarga tipoDeCarga=null;
					String nombre=null;
					while(rs3.next())
					{
						nombre=rs3.getString("NOMBRE");
					}
					prepStmt3.close();
					tipoDeCarga=new TipoDeCarga(tipoCarga,nombre);
					double separacion=rs2.getDouble("ANCHO");

					Bodega b = new Bodega(id,estado,tipo,ancho,largo,plataformaExterna,tipoDeCarga,separacion,null,entradas,salidas);
					areas.add(b);
				}
				prepStmt2.close();

			}
			else if(tipo=='C')
			{
				String sql2 = "SELECT * from COBERTIZOS WHERE ID="+id;

				System.out.println("SQL stmt2:" + sql2);

				PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
				recursos.add(prepStmt2);
				ResultSet rs2 = prepStmt2.executeQuery();
				while (rs2.next())
				{
					double dimensiones=rs2.getDouble("DIMENSIONES");
					int tipoCarga=rs2.getInt("ID_TIPO_CARGA");

					String sql3 = "select * from TIPOS_DE_CARGAS WHERE ID="+tipoCarga;
					PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
					recursos.add(prepStmt3);
					ResultSet rs3 = prepStmt3.executeQuery();
					TipoDeCarga tipoDeCarga=null;
					String nombre=null;
					while(rs3.next())
					{
						nombre=rs3.getString("NOMBRE");
					}
					prepStmt3.close();
					tipoDeCarga=new TipoDeCarga(tipoCarga,nombre);

					Cobertizo c = new Cobertizo(id,estado,tipo,dimensiones,tipoDeCarga,entradas,salidas);
					areas.add(c);
				}
				prepStmt2.close();
			}
			else if(tipo=='P')
			{
				String sql2 = "SELECT * from PATIOS WHERE ID="+id;

				System.out.println("SQL stmt2:" + sql2);

				PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
				recursos.add(prepStmt2);
				ResultSet rs2 = prepStmt2.executeQuery();
				while (rs2.next())
				{
					double dimensiones=rs2.getDouble("DIMENSIONES");
					int tipoCarga=rs2.getInt("ID_TIPO_CARGA");

					String sql3 = "select * from TIPOS_DE_CARGAS WHERE ID="+tipoCarga;
					PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
					recursos.add(prepStmt3);
					ResultSet rs3 = prepStmt3.executeQuery();
					TipoDeCarga tipoDeCarga=null;
					String nombre=null;
					while(rs3.next())
					{
						nombre=rs3.getString("NOMBRE");
					}
					prepStmt3.close();
					tipoDeCarga=new TipoDeCarga(tipoCarga,nombre);

					Patio p = new Patio(id,estado,tipo,dimensiones,tipoDeCarga,entradas,salidas);
					areas.add(p);
				}
				prepStmt2.close();
			}

			else if(tipo=='S')
			{
				String sql2 = "SELECT * from SILOS WHERE ID="+id;

				System.out.println("SQL stmt2:" + sql2);

				PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
				recursos.add(prepStmt2);
				ResultSet rs2 = prepStmt2.executeQuery();
				while (rs2.next())
				{
					String nombre=rs2.getString("NOMBRE");
					double capacidad=rs2.getDouble("CAPACIDAD");

					Silo s = new Silo(id,estado,tipo,nombre,capacidad,entradas,salidas);
					areas.add(s);
				}
				prepStmt2.close();
			}
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
	public Area buscarAreaPorID(int idn) throws SQLException, Exception {
		Area area=null;

		String sql = "SELECT * from AREAS WHERE ID ="+idn;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			char estado = rs.getString("ESTADO").charAt(0);
			char tipo= rs.getString("TIPO").charAt(0);

			area=new Area(id,estado,tipo,null,null);
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
	public void addArea(Area area) throws SQLException, Exception {

		String sql = "INSERT INTO AREAS VALUES (";
		sql += area.getId() + ")";

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
	public void deleteArea(Area area) throws SQLException, Exception {

		String sql = "DELETE FROM AREAS";
		sql += " WHERE id = " + area.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeUpdate();
	}

	@SuppressWarnings("static-access")
	public ArrayList<Carga> cerrarArea(int area) throws Exception {

		ArrayList<Carga> cargas = new ArrayList<Carga>();
		int tipo=0;
		
		conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		conn.setAutoCommit(false);
		
		//se hace una lista con todas las cargas del area
		
		String sql = "SELECT * FROM CARGAS";
		sql +=" WHERE ID_AREA="+area;

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
			tipo=tipoCarga;
			
			cargas.add(new Carga(idCarga,nombreCarga,peso,estado,dias,new TipoDeCarga(tipoCarga,null)));
		}
		rs.close();
		conn.setSavepoint();
		
		//se agregan las cargas a las areas que cumplen con las condiciones
		
		String sql1 = "SELECT * FROM AREAS";
		sql1 +=" WHERE ID NOT IN "+area;
		sql1 +=" AND maximo_cargas>cantidad_cargas_actual";
		sql1 +=" AND tipo_de_carga="+tipo;

		System.out.println("SQL stmt:" + sql1);

		PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
		recursos.add(prepStmt1);
		ResultSet rs1 = prepStmt1.executeQuery();
		
		ArrayList<Integer> cantidades = new ArrayList<Integer>();
		ArrayList<Integer> areas= new ArrayList<Integer>();
		
		while (rs1.next()) {
			
			int cuantasVacias = rs1.getInt("maximo_cargas") - rs1.getInt("cantidad_cargas_actual");
			
			for(int i=0;i<cuantasVacias;i++)
			{
				Carga c = cargas.remove(0);
				//cambiar carga de area
				
				String sql2 = "UPDATE CARGAS";
				sql2+=" SET ID_AREA="+rs1.getInt("ID");
				sql2+=" WHERE ID="+c.getId();

				System.out.println("SQL stmt:" + sql2);

				PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
				recursos.add(prepStmt2);
				prepStmt2.executeUpdate();
				prepStmt2.close();
				
				//crear movimiento
				
				String sql3 = "select max(id) as num from operaciones";

				System.out.println("SQL stmt:" + sql3);

				PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
				recursos.add(prepStmt3);
				ResultSet rs3 = prepStmt3.executeQuery();
				
				while(rs3.next())
				{
					int id = rs3.getInt("num")+1;
					
					String sql31 = "INSERT INTO OPERACIONES VALUES ";
					sql31 += "('"+id+ "',";
					sql31 += "null,'";
					sql31 +=  "M','";
					sql31 +=  "12/12/12 11:11:11,0','";
					sql31 += c.getId()+"')";

					System.out.println("SQL stmt:" + sql31);

					PreparedStatement prepStmt31 = conn.prepareStatement(sql31);
					recursos.add(prepStmt31);
					prepStmt31.executeUpdate();
					prepStmt31.close();
					
					String sql32 = "INSERT INTO MOVIMIENTOS VALUES ";
					sql32 += "('"+id+ "','";
					sql32 += area+"','";
					sql32 +=  rs1.getInt("ID")+"','";
					sql32 +=  "517')";

					System.out.println("SQL stmt:" + sql32);

					PreparedStatement prepStmt32 = conn.prepareStatement(sql32);
					recursos.add(prepStmt32);
					prepStmt32.executeUpdate();
					prepStmt32.close();	
					
				}
				
				areas.add(rs1.getInt("ID"));
				cantidades.add(cuantasVacias);
			}
			conn.setSavepoint();
			
			for(int i=0;i<cantidades.size();i++)
			{
				//cambiar la cantidad de cargas en las areas
				String sql4 = "UPDATE AREAS";
				sql4 +=" SET cantidad_cargas_actual="+cantidades.get(i);
				sql4 +=" WHERE ID="+areas.get(i);
				
				System.out.println("SQL stmt:" + sql4);

				PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
				recursos.add(prepStmt4);
				prepStmt4.executeUpdate();
				prepStmt4.close();
			}
	
		}
		
		//cambiar estado del area
		String sql5 = "UPDATE AREAS";
		sql5 +=" SET ESTADO='C'";
		sql5 +=" WHERE ID="+area;


		System.out.println("SQL stmt:" + sql5);

		PreparedStatement prepStmt5 = conn.prepareStatement(sql5);
		recursos.add(prepStmt5);
		prepStmt5.executeUpdate();
		
		return cargas;
	}
}
