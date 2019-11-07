import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.StringTokenizer;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BancoADjdbc {

	private BufferedReader archivoIn;
	private PrintWriter archivoOut;

	private Connection conexion;
	private Statement statement;

	private DateFormat dateFormat, horaFormat;
	private Date date;


	public BancoADjdbc(){

		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		horaFormat = new SimpleDateFormat("HH:mm:ss");

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

	public String consultaGeneral(){
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

	public String consultaDep(){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM depositos";

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);
			while(tr.next()){
				datos = datos + tr.getString("nocta") + "_";
				datos = datos + tr.getString("nombre") + "_";
				datos = datos + tr.getString("saldoAnt") + "_";
				datos = datos + tr.getString("deposito") + "_";
				datos = datos + tr.getString("saldoN") + "_";
				datos = datos + tr.getString("fecha") + "_";
				datos = datos + tr.getString("hora") + "\n";

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

	public String consultaRet(){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM retiros";

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);
			while(tr.next()){
				datos = datos + tr.getString("nocta") + "_";
				datos = datos + tr.getString("nombre") + "_";
				datos = datos + tr.getString("saldoAnt") + "_";
				datos = datos + tr.getString("retiro") + "_";
				datos = datos + tr.getString("saldoN") + "_";
				datos = datos + tr.getString("fecha") + "_";
				datos = datos + tr.getString("hora") + "\n";

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

	public String capturar(String tabla, String datos){
		String status = "";
		String strInsert = "";

	 	strInsert = "INSERT INTO cliente VALUES("+ datos +")";

		if(tabla.equals("cliente")) {
			clientedp = new ClienteDP(datos);
			strInsert = "INSERT INTO cliente VALUES("+ clientedp.toString() +")";
		}

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

	public String updateSaldo(){
		String strUpdate = "";
		String status = "";

		strUpdate = "UPDATE cliente SET saldo = " + clientedp.getSaldo() + " WHERE nocta = " + clientedp.getNocta();

		try {
			statement = conexion.createStatement();

			statement.executeUpdate(strUpdate);

			statement.close();

			status = "Datos actualizados";

		} catch(SQLException ioe) {
			status = "Error al aztualizar";
		}

		return status;
	}

	public String depositar(float cantidad){
		String datos = "";
		float saldo;

		if(clientedp.getTipo().equals("HIPOTECA") || clientedp.getTipo().equals("CREDITO")){
			clientedp.setOperacion(-cantidad);
			saldo = clientedp.getSaldo() - cantidad;

			clientedp.setSaldo(saldo);
		}

		if(clientedp.getTipo().equals("AHORRO") || clientedp.getTipo().equals("INVERSION")){
			clientedp.setOperacion(cantidad);
			saldo = clientedp.getSaldo() + cantidad;

			clientedp.setSaldo(saldo);
		}

		System.out.println(clientedp.toStringOperacion());
		datos = capturar("depositos", clientedp.toStringOperacion());
		datos = datos + "\n" + updateSaldo();

		return datos;
	}

	public String retiro(float cantidad){
		String datos = "";
		float saldo;

		if(clientedp.getTipo().equals("HIPOTECA")){
			return "No se puede realizar retiros";
		}

		if(clientedp.getTipo().equals("CREDITO")){
			clientedp.setOperacion(cantidad);
			saldo = clientedp.getSaldo() + cantidad;

			clientedp.setSaldo(saldo);
		}

		if(clientedp.getTipo().equals("AHORRO") || clientedp.getTipo().equals("INVERSION")){
			clientedp.setOperacion(-cantidad);
			saldo = clientedp.getSaldo() - cantidad;

			clientedp.setSaldo(saldo);
		}

		System.out.println(clientedp.toStringOperacion());
		datos = capturar("retiros", toStringOperacion());
		datos = datos + "\n" + updateSaldo();

		return datos;
	}

	public String trans (String nocta, float cantidad){
		ClienteDP retiro = new ClienteDP(clientedp.toString());
		ClienteDP deposito = new ClienteDP(consultarNocta(nocta));
		date = new Date();
		String strTrans = "";
		String respuesta = "";

		if(retiro.getTipo().equals("HIPOTECA")) return "No se pueden hacer retiros";
		retiro.setOperacion(-cantidad);
		deposito.setOperacion(cantidad);

		//Concatenamos informacion
		strTrans = retiro.toStringTrans() + "," + deposito.toStringTrans() + ",'" +
		 			  	 dateFormat.format(date) + "', '" + horaFormat.format(date) + "'";

		//Insertamos la informacion a la tabla
		respuesta = capturar("transferencias", strTrans);
		//Actualizamos datos

		System.out.println(strTrans);
		System.out.println(deposito.toString());

		return "";
	}
}
