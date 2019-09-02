import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoADjdbc {

	private BufferedReader archivoIn;
	private PrintWriter archivoOut;

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

		try {

			//1. Abrir el archivo
			archivoOut=new PrintWriter(new FileWriter("Clientes.txt", true));

			//2.Escribir o almacenar los datos en el archivo
			archivoOut.println(datos);

			//3. Cerrar el archivo
			archivoOut.close();
			resultado="Datos capturas: "+ datos;
		}


		catch(IOException ioe) {
			resultado= "Error: "+ ioe;
		}

		return "Datos a capturar: "+datos;
	}

	public String consultarClientes() {

		String datos="";

		try {
			//1. Abrir el archivo
			archivoIn= new BufferedReader(new FileReader ("Clientes.txt"));

			//2.Procesar datos
			while(archivoIn.ready()) {
				datos= datos + archivoIn.readLine() + "\n";
			}

			//3.Cerrar archivo
			archivoIn.close();
		}

		catch(FileNotFoundException fnfe) {
			datos= "Error: "+ fnfe;
		}

		catch(IOException ioe) {
			datos= "Error : "+ioe;
		}

		return datos;
	}
}
