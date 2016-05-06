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
import dao.DAOTablaAreas;
import dao.DAOTablaBuques;
import dao.DAOTablaCargas;
import dao.DAOTablaCargues;
import dao.DAOTablaDescargues;
import dao.DAOTablaEntregas;
import dao.DAOTablaExportadores;
import dao.DAOTablaFacturas;
import dao.DAOTablaImportadores;
import dao.DAOTablaMovimientos;
import dao.DAOTablaMuelles;
import dao.DAOTablaSalidas;
import dao.DaoTablaTiposYBuques;
import vos.Administrador;
import vos.Area;
import vos.Carga;
import vos.CargaBuque;
import vos.ConsultaAreas;
import vos.Descargue;
import vos.Entrega;
import vos.Exportador;
import vos.Factura;
import vos.Importador;
import vos.ListaArribosSalidas;
import vos.Movimiento;
import vos.Muelle;
import vos.Operacion;
import vos.Salida;


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
	public void addEntrega(int idCarga, int idTipoCarga, double peso) throws Exception {

		DAOTablaEntregas daoEntregas = new DAOTablaEntregas();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			daoEntregas.setConn(conn);
			daoEntregas.addEntrega(idCarga, idTipoCarga,peso);
			conn.commit();
			System.out.println("a");

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

	public List<Exportador> buscarExportadorPor(String tipoExportador, int idBuque, int tipoCarga, String fecha1, String fecha2) throws Exception {
		ArrayList<Exportador> exportadores;
		DAOTablaExportadores dao = new DAOTablaExportadores();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			dao.setConn(conn);
			exportadores = dao.buscarExportadorPor(tipoExportador,idBuque,tipoCarga,fecha1,fecha2);

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

	public void addSalida(Salida salida, int idAgente) throws Exception {
		DAOTablaSalidas dao1 = new DAOTablaSalidas();
		DAOTablaMuelles dao2 = new DAOTablaMuelles();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			dao1.setConn(conn);
			dao2.setConn(conn);

			Muelle muelle=dao2.buscarMuellePorIDBuque(salida.getBuque().getId());

			if(muelle!=null)
			{
				salida.setMuelle(muelle);
				dao2.vaciarMuelle(muelle.getId());
				dao1.addSinFactura(salida, idAgente);
			}

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
				dao1.cerrarRecursos();
				dao2.cerrarRecursos();
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
	 * Método que modela la transacción que agrega un solo video a la base de datos.
	 * <b> post: </b> se ha agregado el video que entra como parámetro
	 * @param admi - el video a agregar. video != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addAdministrador(Administrador admi) throws Exception {
		DAOTablaAdministradores dao = new DAOTablaAdministradores();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			dao.setConn(conn);
			dao.addAdministrador(admi);
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
				dao.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public List<Factura> darFacturas() throws Exception{
		ArrayList<Factura> facturas;
		DAOTablaFacturas dao=new DAOTablaFacturas();

		try 
		{
			//////Transacción
			this.conn = darConexion();
			dao.setConn(conn);
			facturas = dao.darFacturas();

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
		return facturas;
	}

	public void addFactura(int idBuque) throws Exception {

		DAOTablaFacturas daoFacturas = new DAOTablaFacturas();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			daoFacturas.setConn(conn);
			daoFacturas.addFactura(idBuque);
			conn.commit();
			System.out.println("a");

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
				daoFacturas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}

	}

	public void addTipoCargaABarco(CargaBuque relacion) throws Exception {
		DaoTablaTiposYBuques dao = new DaoTablaTiposYBuques();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			dao.setConn(conn);
			dao.addTipoCarga(relacion.getIdCarga(), relacion.getIdBuque());
			conn.commit();
			System.out.println("a");

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

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Area> darAreas(ConsultaAreas c) throws Exception{
		ArrayList<Area> areas;
		DAOTablaAreas dao=new DAOTablaAreas();

		try 
		{
			//////Transacción
			this.conn = darConexion();
			dao.setConn(conn);
			areas = dao.darAreas(c);

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
		return areas;
	}

	/**
	 * 
	 * @param area
	 * @throws Exception
	 */
	public List<Carga> cerrarArea(int area) throws Exception{

		DAOTablaAreas dao = new DAOTablaAreas();
		ArrayList<Carga> a= new ArrayList<Carga>();
		try 
		{

			this.conn = darConexion();
			dao.setConn(conn);
			a= dao.cerrarArea(area);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
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
		return a;
	}
	public void RF10(int buque,int operador,List<Integer> cargas) throws SQLException{

		DAOTablaCargas dao1=new DAOTablaCargas();
		DAOTablaBuques dao2=new DAOTablaBuques();
		DaoTablaTiposYBuques dao3= new DaoTablaTiposYBuques();
		DAOTablaCargues dao4=new DAOTablaCargues();

		try {
			this.conn=darConexion();
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn.setAutoCommit(false);
			dao1.setConn(conn);
			dao2.setConn(conn);
			dao3.setConn(conn);
			dao4.setConn(conn);
			double total=0;
			for(int i=0;i<cargas.size();i++){
				Carga carga=dao1.buscarCargaPorID(cargas.get(i));
				if(carga.getEstado()=='A')
				{
					carga.setEstado('B');
					total+=carga.getPeso();
					if(dao3.existe(buque, carga.getTipoCarga().getId()).size()==0){dao3.addTipoCarga(carga.getTipoCarga().getId(), buque);}
					dao1.cargar(carga,buque);
					dao4.addCargues(carga.getId(), operador,carga.getArea(),buque);
				}
			}
			dao2.actualizarBuque(buque,total);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			conn.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("SQLException:" + e.getMessage());
			conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				dao1.cerrarRecursos();
				dao2.cerrarRecursos();
				dao3.cerrarRecursos();
				dao4.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void RF12(){

	}

	public List<Movimiento> RFC5(){
		DAOTablaMovimientos dao=new DAOTablaMovimientos();
		try {
			this.conn=darConexion();
			return dao.darMovimientos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Descargue> addDescarga(int buque) throws Exception {
		DAOTablaDescargues dao = new DAOTablaDescargues();
		ArrayList<Descargue> d = new ArrayList<Descargue>();
		try 
		{

			this.conn = darConexion();
			dao.setConn(conn);
			d=dao.addDescarga(buque);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
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
		return d;
	}
	
	/////////////////////////////////////////////////////
	/////////////Requerrimiento de consulta eficiente////
	/////////////////////////////////////////////////////
	
	public ListaArribosSalidas RFC7(int inicio, int fin, String nombre, int tipo, String barco, String param, String forma){
		return null;
	}
	public ListaArribosSalidas RFC8(){
		return null;
	}
	public List<Movimiento> RFC9(int valor, int tipo, int exportador){
		return null;
	}
	public List<Operacion> RFC10(int id1 ,int id2){
		return null;
	}
}
