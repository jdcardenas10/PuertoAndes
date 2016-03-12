package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.Carga;
import vos.Entrega;
import vos.Importador;
import vos.TipoDeCarga;


public class DAOTablaImportadores {

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Método constructor que crea DAOAgente
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaImportadores() {
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
	 * Método que, usando la conexión a la base de datos, saca todos los Agentes de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM AgenteS;
	 * @return Arraylist con los Agentes de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<Importador> darImportadores() throws SQLException, Exception {
		ArrayList<Importador> importadores = new ArrayList<Importador>();

		String sql = "select * from IMPORTADORES NATURAL JOIN USUARIOS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			String login = rs.getString("LOGIN");
			String clave = rs.getString("CLAVE");
			String registro = rs.getString("REGISTRO_ADUANA");
			char tipo0 = rs.getString("TIPO").charAt(0);
			
			String sql1 = "select * from CARGAS WHERE ID_IMPORTADOR="+id;
            PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
			recursos.add(prepStmt1);
			ResultSet rs1 = prepStmt.executeQuery();
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
				recursos.add(prepStmt2);
				ResultSet rs2 = prepStmt.executeQuery();
				TipoDeCarga tipo=null;
				
				String nombre2=rs2.getString("NOMBRE");
				
				tipo=new TipoDeCarga(tipoCarga,nombre2);
				
				cargas.add(new Carga(id1,nombre1,peso,estado,dias,tipo));
				
			}
			String sql3 = "select * from ENTREGAS NATURAL JOIN OPERACIONES WHERE ID_IMPORTADOR="+id;
            PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
			recursos.add(prepStmt3);
			ResultSet rs3 = prepStmt.executeQuery();
			ArrayList<Entrega> entregas=new ArrayList<Entrega>();
			while(rs3.next()){
				int id1 =Integer.parseInt(rs1.getString("ID"));
				char tipo=rs1.getString("TIPO").charAt(0);
				Date fecha=new Date(rs1.getString("FECHA"));
				int idCarga=Integer.parseInt(rs1.getString("ID_CARGA"));
				
				String sql2 = "select * from CARGAS WHERE ID="+idCarga;
	            PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
				recursos.add(prepStmt2);
				ResultSet rs2 = prepStmt.executeQuery();
				Carga carga=null;
				
				String nombre2=rs2.getString("NOMBRE");
				Double peso=Double.parseDouble(rs2.getString("PESO"));
				char estado2=rs2.getString("NOMBRE").charAt(0);
				int dias2=Integer.parseInt(rs2.getString("DIAS_EN_PUERTO"));
				
				carga=new Carga(id,nombre2,peso,estado2,dias2,null);
				
				entregas.add(new Entrega(id1,tipo,fecha,carga));
				
			}
			importadores.add(new Importador(id, nombre, login,clave,tipo0, registro, entregas,cargas));
		}
		return importadores;
	}


	/**
	 * Método que busca el/los Agentes con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los Agentes a buscar
	 * @return Arraylist con los Agentes encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	@SuppressWarnings("deprecation")
	public Importador buscarImportadorPorID(int idn) throws SQLException, Exception {
		Importador importador=null;

		String sql = "SELECT * from IMPORTADORES NATURAL JOIN USUARIOS WHERE ID ="+idn;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			String login = rs.getString("LOGIN");
			String clave = rs.getString("CLAVE");
			String registro = rs.getString("REGISTRO_ADUANA");
			char tipo0 = rs.getString("TIPO").charAt(0);
			
			String sql1 = "select * from CARGAS WHERE ID_IMPORTADOR="+id;
            PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
			recursos.add(prepStmt1);
			ResultSet rs1 = prepStmt.executeQuery();
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
				recursos.add(prepStmt2);
				ResultSet rs2 = prepStmt.executeQuery();
				TipoDeCarga tipo=null;
				
				String nombre2=rs2.getString("NOMBRE");
				
				tipo=new TipoDeCarga(tipoCarga,nombre2);
				
				cargas.add(new Carga(id1,nombre1,peso,estado,dias,tipo));
				
			}
			String sql3 = "select * from ENTREGAS NATURAL JOIN OPERACIONES WHERE ID_IMPORTADOR="+id;
            PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
			recursos.add(prepStmt3);
			ResultSet rs3 = prepStmt.executeQuery();
			ArrayList<Entrega> entregas=new ArrayList<Entrega>();
			while(rs3.next()){
				int id1 =Integer.parseInt(rs1.getString("ID"));
				char tipo=rs1.getString("TIPO").charAt(0);
				Date fecha=new Date(rs1.getString("FECHA"));
				int idCarga=Integer.parseInt(rs1.getString("ID_CARGA"));
				
				String sql2 = "select * from CARGAS WHERE ID="+idCarga;
	            PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
				recursos.add(prepStmt2);
				ResultSet rs2 = prepStmt.executeQuery();
				Carga carga=null;
				
				String nombre2=rs2.getString("NOMBRE");
				Double peso=Double.parseDouble(rs2.getString("PESO"));
				char estado2=rs2.getString("NOMBRE").charAt(0);
				int dias2=Integer.parseInt(rs2.getString("DIAS_EN_PUERTO"));
				
				carga=new Carga(id,nombre2,peso,estado2,dias2,null);
				
				entregas.add(new Entrega(id1,tipo,fecha,carga));
				
			}
			importador=new Importador(id, nombre, login,clave,tipo0, registro, entregas,cargas);
		}

		return importador;
	}

	/**
	 * Método que agrega el Agente que entra como parámetro a la base de datos.
	 * @param Agente - el Agente a agregar. Agente !=  null
	 * <b> post: </b> se ha agregado el Agente a la base de datos en la transaction actual. pendiente que el Agente master
	 * haga commit para que el Agente baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Agente a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addAgente(Importador importador) throws SQLException, Exception {

		String sql = "INSERT INTO Usuarios VALUES (";
		sql += importador.getId() + ",'";
		sql += importador.getNombre() + "','";
		sql += importador.getLogin() + "','";
		sql += importador.getClave() +"')";
		
		String sql2="INSERT INTO IMPORTADORES VALUES ("
				+importador.getId()+",'"
			    +importador.getRegistro()+"','"
		        +importador.getTipo()+"')";
		

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
	public void updateImportador(Importador importador) throws SQLException, Exception {

		String sql = "UPDATE USUARIOS SET ";
		sql += "nombre='" + importador.getNombre() + "', ";
		sql += "login='" + importador.getLogin()+"', ";
		sql += "clave='" + importador.getClave()+"'";
		sql += " WHERE id = " + importador.getId();
		
		String sql1 = "UPDATE IMPORTADORES SET ";
		sql1 += "registro_aduana='" + importador.getRegistro() + "', ";
		sql1 += "tipo='" + importador.getLogin()+"', ";
		sql1 += " WHERE id = " + importador.getId();

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
	public void deleteImportador(Importador importador) throws SQLException, Exception {

		String sql0 = "DELETE FROM USUARIOS";
		sql0 += " WHERE id = " + importador.getId();
		
		String sql = "DELETE FROM IMPORTADORES";
		sql += " WHERE id = " + importador.getId();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt0 = conn.prepareStatement(sql0);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt0);
		recursos.add(prepStmt);
		prepStmt0.executeUpdate();
		prepStmt.executeUpdate();
	}
}
