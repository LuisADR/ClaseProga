import java.io.*;

public class BancoAD {

	private BufferedReader archivoIn;
	private PrintWriter archivoOut;

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
