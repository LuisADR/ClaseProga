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
				DriverManager.getConnection("jdbc:mysql://localhost/bancomer?user=root&password=Ocma_08Jvaa");

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

	public String capturar (String datos) {

		String resultado="";
		String strInsert="";
		String ncta, name, tcta;
		int cantidad;

		StringTokenizer st= new StringTokenizer(datos, "_");
		ncta=st.nextToken();
		name=st.nextToken();
		tcta=st.nextToken();
		cantidad=Integer.parseInt(st.nextToken());

		//strInsert= "INSERT INTO Cliente VALUES('99','CARLOS GUTIERREZ','AHORRO', 9900)";
		strInsert= "INSERT INTO Cliente Values ('"+ncta+"','"+name+"','"+tcta+"',"+cantidad+")";
		try {

			//1. Abrir el archivo o BD
			statement = conexion.createStatement();
			//archivoOut=new PrintWriter(new FileWriter("Clientes.txt", true));

			//2.Escribir o almacenar los datos en el archivo o tabla correspondiente
			//archivoOut.println(datos);
			statement.executeUpdate(strInsert);

			//3. Cerrar el archivo o BD
			//archivoOut.close();
			//resultado="Datos capturas: "+ datos;
			statement.close();
		}


		catch(SQLException ioe) {
			resultado= "Error: "+ ioe;
		}

		return "Datos a capturar: "+datos;
	}

	public String consultarClientes() {

		String datos="";
		String strQuery;
		ResultSet tr;
		String resultado="";
		String strInsert="";
		String ncta, name, tcta;
		int cantidad;

		strQuery= "SELECT * FROM  Cliente ";

		try {
			//1. Abrir el archivo o BD
			//archivoIn= new BufferedReader(new FileReader ("Clientes.txt"));
			statement= conexion.createStatement();

			//2.Procesar datos
			//while(archivoIn.ready()) {
				//datos= datos + archivoIn.readLine() + "\n";
			//}
			tr= statement.executeQuery(strQuery);
			while(tr.next()){
				ncta= tr.getString("nocta");
				name=tr.getString("nombre");
				tcta=tr.getString(3);
				cantidad=tr.getInt(4);

				datos= datos+ ncta + "*" + name + "*"  + tcta + "*" + cantidad + "\n";
			}

			//3.Cerrar archivo
			//archivoIn.close();
			statement.close();
		}

		catch(SQLException fnfe) {
			datos= "Error: "+ fnfe;
		}

		return datos;
	}

	public String depositar(String nocta, int cantidad){
		String resultado = "";

		//Checar el tipo de cuenta para aumentar o disminuir el saldo
		if(clientedp.getTipo().equals("INVERSION") || clientedp.getTipo().equals("AHORRO")){
			clientedp.setSaldo(clientedp.getSaldo() + cantidad);
		} else {
			clientedp.setSaldo(clientedp.getSaldo() - cantidad);
		}
		//Actualizar el saldo del cliente con ese no cuenta
		//entregar el resultado
		resultado = clientedp.toString();

		return resultado;
	}

}
