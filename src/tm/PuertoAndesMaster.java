package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dao.DAOTablaAdministradores;
import dao.DAOTablaEntregas;
import dao.DAOTablaExportadores;
import dao.DAOTablaImportadores;
import vos.Administrador;
import vos.Entrega;
import vos.Exportador;
import vos.Importador;
import vos.ListaArribosSalidas;


public class PuertoAndesMaster {

	/**
	 * Atributo estático que contiene el path relativo del archivo que tiene los datos de la conexión
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estático que contiene el path absoluto del archivo que tiene los datos de la conexión
	 */
	private  String connectionDataPath;

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;
	
	/**
	 * Conexión a la base de datos
	 */
	private Connection conn;


	/**
	 * Método constructor de la clase VideoAndesMaster, esta clase modela y contiene cada una de las 
	 * transacciones y la logia de negocios que estas conllevan.
	 * <b>post: </b> Se crea el objeto VideoAndesMaster, se inicializa el path absoluto de el archivo de conexión y se
	 * inicializa los atributos que se usan par la conexión a la base de datos.
	 * @param contextPathP - path absoluto en el servidor del contexto del deploy actual
	 */
	public PuertoAndesMaster(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}

	/*
	 * Método que  inicializa los atributos que se usan para la conexion a la base de datos.
	 * <b>post: </b> Se han inicializado los atributos que se usan par la conexión a la base de datos.
	 */
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que  retorna la conexión a la base de datos
	 * @return Connection - la conexión a la base de datos
	 * @throws SQLException - Cualquier error que se genere durante la conexión a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}

	////////////////////////////////////////
	///////Transacciones////////////////////
	////////////////////////////////////////

	public void consultarArribos(){
		
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Administrador> darAdministradores() throws Exception  {
		ArrayList<Administrador> admins;
		DAOTablaAdministradores dao=new DAOTablaAdministradores();
		
		try 
		{
			//////Transacción
			this.conn = darConexion();
			dao.setConn(conn);
			admins = dao.darAdministradores();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				dao.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return admins;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Importador> darImportadores() throws Exception {
		ArrayList<Importador> importadores;
		DAOTablaImportadores dao=new DAOTablaImportadores();
		
		try 
		{
			//////Transacción
			this.conn = darConexion();
			dao.setConn(conn);
			importadores = dao.darImportadores();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				dao.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return importadores;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Entrega> darEntregas() throws Exception {
		ArrayList<Entrega> entregas;
		DAOTablaEntregas dao=new DAOTablaEntregas();
		
		try 
		{
			//////Transacción
			this.conn = darConexion();
			dao.setConn(conn);
			entregas = dao.darEntregas();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				dao.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return entregas;
	}

	/**
	 * 
	 * @param entrega
	 * @throws Exception
	 */
	public void addEntrega(Entrega entrega, Importador importador) throws Exception {
		
		DAOTablaEntregas daoEntregas = new DAOTablaEntregas();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			daoEntregas.setConn(conn);
			daoEntregas.addEntrega(entrega,importador,null);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoEntregas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Exportador> darExportadores() throws Exception {
		ArrayList<Exportador> exportadores;
		DAOTablaExportadores dao=new DAOTablaExportadores();
		
		try 
		{
			//////Transacción
			this.conn = darConexion();
			dao.setConn(conn);
			exportadores = dao.darExportadores();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				dao.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return exportadores;
	}

	public ListaArribosSalidas darArribosYSalidas() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Exportador> buscarExportadorPor(String name) throws Exception {
		ArrayList<Exportador> exportadores;
		DAOTablaExportadores dao = new DAOTablaExportadores();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			dao.setConn(conn);
			exportadores = dao.buscarExportadorPor(name);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				dao.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return exportadores;
	}

	
}
