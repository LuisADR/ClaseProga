import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.StringTokenizer;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class BancoADjdbc {

	private BufferedReader archivoIn;
	private PrintWriter archivoOut;

	private Connection conexion;
	private Statement statement;

	private ClienteDP clientedp;

	public BancoADjdbc(){
			try{

				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/bancomer?user=root&password=Ocma_08Jvaa");

				System.out.println("Conexion Exitosa a la DB ...");

			} catch(ClassNotFoundException cnfe){
				System.out.println("Error1 al conectar: " + cnfe);
			} catch (InstantiationException ie){
				System.out.println("Error2 al conectar: " + ie);
			} catch (IllegalAccessException iae){
			System.out.println("Error3 al conectar: " + iae);
			} catch (SQLException sqle){
			System.out.println("Error4 al conectar: " + sqle);
		}
	}

	public String consultarClientes(){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM cliente";

		clientedp = new ClienteDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);
			while(tr.next()){
				clientedp.setNocta(tr.getString("nocta"));
				clientedp.setNombre(tr.getString("nombre"));
				clientedp.setTipo(tr.getString("tipo"));
				clientedp.setSaldo(Float.parseFloat(tr.getString("saldo")));
				clientedp.setFecha(tr.getString("fecha"));
				clientedp.setHora(tr.getString("hora"));

				datos = datos + clientedp.toString() + "\n";
			}

			//Cerramos la Conexion
			statement.close();
			System.out.println(strQuery);
		} catch(SQLException ioe) {
			datos = "Error: "+ ioe;
		}

		System.out.println(resultado);
		return datos;
	}

	public String consultarNocta(String cuenta){
		String datos = "NULL";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM cliente WHERE nocta = " + cuenta;

		clientedp = new ClienteDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);

			while(tr.next()){
				clientedp.setNocta(tr.getString("nocta"));
				clientedp.setNombre(tr.getString("nombre"));
				clientedp.setTipo(tr.getString("tipo"));
				clientedp.setSaldo(Float.parseFloat(tr.getString("saldo")));
				clientedp.setFecha(tr.getString("fecha"));
				clientedp.setHora(tr.getString("hora"));

				datos =  clientedp.toString() + "\n";
			}

			//Cerramos la Conexion
			statement.close();
			System.out.println(strQuery);
		} catch(SQLException ioe) {
			System.out.println(ioe);
		}

		System.out.println(resultado);
		return datos;
	}

	public String consultarTipo(String tipo){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM cliente WHERE tipo = '" + tipo + "'";
		System.out.println(strQuery);
		clientedp = new ClienteDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);
			while(tr.next()){
				clientedp.setNocta(tr.getString("nocta"));
				clientedp.setNombre(tr.getString("nombre"));
				clientedp.setTipo(tr.getString("tipo"));
				clientedp.setSaldo(Float.parseFloat(tr.getString("saldo")));

				datos = datos + clientedp.toString() + "\n";
			}

			//Cerramos la Conexion
			statement.close();
			System.out.println(strQuery);
		} catch(SQLException ioe) {
			datos = "Error: "+ ioe;
		}

		System.out.println(resultado);
		return datos;
	}

	public String capturar(String datos){
		String status = "";

		clientedp = new ClienteDP(datos);

		String strInsert = "INSERT INTO cliente VALUES("+ clientedp.toStringSQL() +")";

		try {

			System.out.println(strInsert);

			//Creamos la conexion
			statement =conexion.createStatement();

			//Realizamos el update
			statement.executeUpdate(strInsert);

			//Cerramos la conexion
			statement.close();

			System.out.println(clientedp.toStringSQL());
			status = "Captura exitosa";

		} catch(SQLException ioe) {
			status = "Error: "+ ioe;
		}

		return status;
	}
}
