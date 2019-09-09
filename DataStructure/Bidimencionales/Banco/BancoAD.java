import java.io.*;
import java.util.StringTokenizer;

public class BancoAD {

	private String archivo = "Clientes.txt";
	private BufferedReader archivoIn;
	private PrintWriter archivoOut;
	private int noClientes;

	private String arregloString[];
	private ClienteDP arregloObjetoDP[];

	public BancoAD (){
		String datos = "";
		try {

			//1. Abrir el archivo
			archivoIn = new BufferedReader(new FileReader(archivo));

			//2.Escribir o almacenar los datos en el archivo
			while(archivoIn.ready()){
				archivoIn.readLine();
				noClientes++;
			}

			// Cerrar el archivo
			archivoIn.close();

		} catch(FileNotFoundException fnfe){
          System.out.print("Error: "+fnfe);
    } catch(IOException ioe){
        System.out.print("Error: "+ioe);
    }
			System.out.println("Strings en el archivo: " + noClientes);
		}

	public String capturar (String datos, boolean rewrite) {

		String resultado="";

		try {

			//1. Abrir el archivo
			archivoOut=new PrintWriter(new FileWriter(archivo, !rewrite));

			//2.Escribir o almacenar los datos en el archivo
			archivoOut.println(datos);

			//3. Cerrar el archivo
			archivoOut.close();
			noClientes++;
			resultado="Datos capturas: "+ datos;

		}	catch(IOException ioe) {
			resultado= "Error: "+ ioe;
		}

		return resultado;
	}

	public String consultarClientes() {

		String datos="";

		try {
			//1. Abrir el archivo
			archivoIn= new BufferedReader(new FileReader (archivo));

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

	public String datosArchArreglos (){

		String respuesta = "";
		int i=0;

		arregloString = new String[noClientes];

		try{

			// Abrir el archivo
			archivoIn = new BufferedReader(new FileReader(archivo));

			// Procesar Datos
			while(archivoIn.ready()){
				arregloString[i] = archivoIn.readLine();
				i++;
			}
			// Cerrar archivo
			archivoIn.close();
			respuesta = "Datos almacenados en arreglo";

		} catch (FileNotFoundException fnfe){
			respuesta = "Error: " + fnfe;
		} catch (IOException ioe){
			respuesta = "Error: " + ioe;
		}

		return respuesta;
	}

	public String consultarArreglo(){
		String datos = "";
		int i = 0;

		if(arregloString == null){
			datos = "Arreglo Vacio";
		} else {
			while(i < arregloString.length){
				datos = datos + arregloString[i] + "\n";
				i++;
			}
		}
		return datos;
	}

	public String datosArchArregloObj(){
		String respuesta = "";
		int i = 0;

		arregloObjetoDP = new ClienteDP[noClientes];

		try {
			// Abrir el archivo
			archivoIn = new BufferedReader(new FileReader(archivo));

			// Procesar datos
			while(archivoIn.ready()){
        arregloObjetoDP[i]= new ClienteDP(archivoIn.readLine());
        i++;
			}
			// Cerrar archivo
      archivoIn.close();
      respuesta= "Datos almacenados en el Arreglo";
		} catch(FileNotFoundException fnfe){
          respuesta = "Error: "+fnfe;
      }
      catch(IOException ioe){
          respuesta = "Error: "+ioe;
      }

    	return respuesta;
	}

	public String consultarArregloObj(){
		String datos = "";
		int i = 0;

		if(arregloObjetoDP == null){
			datos = "Arreglo vacio";
		} else {
			while(i < arregloObjetoDP.length){
				datos = datos + arregloObjetoDP[i].toString() + "\n";
				i++;
			}
		}
		return datos;
	}

	public String consultarTC(String tipo){
		String datos, nocta, nombre, tc;
		int saldo;
		String resultado = "";
		StringTokenizer st;

		try {
			//1. Abrir el archivo
			archivoIn= new BufferedReader(new FileReader (archivo));

			//2.Procesar datos
			while(archivoIn.ready()) {
				datos = archivoIn.readLine();
				st = new StringTokenizer(datos, "_");

				nocta = st.nextToken();
				nombre = st.nextToken();;
				tc = st.nextToken();;

				if(tc.equals(tipo)){
					resultado = resultado + datos + "\n";
				}
			}

			if (resultado.equals("")) resultado = "No se encontro el tipo de cuenta";

			//3.Cerrar archivo
			archivoIn.close();
		}

		catch(FileNotFoundException fnfe) {
			resultado= "Error: "+ fnfe;
		}

		catch(IOException ioe) {
			resultado= "Error : "+ioe;
		}

		return resultado;
	}

	public String consultarTCObj(String tipo){
		String resultado = "";
		StringTokenizer st;

		for (int i = 0; i < arregloObjetoDP.length ; i++) {
			if(tipo.equals(arregloObjetoDP[i].getTipo())) resultado = resultado + arregloObjetoDP[i].toString() + "\n";
		}

		if (resultado.equals("")) resultado = "No se encontro el tipo de cuenta";

		return resultado;
	}

	public String consultarCuenta(String cuenta, int saldoN){
		String datos, nocta, nombre, tc;
		int saldo;
		String resultado = "";
		StringTokenizer st;

		try {
			//1. Abrir el archivo
			archivoIn= new BufferedReader(new FileReader (archivo));

			//2.Procesar datos
			while(archivoIn.ready()) {
				datos = archivoIn.readLine();
				st = new StringTokenizer(datos, "_");

				nocta = st.nextToken();
				nombre = st.nextToken();;
				tc = st.nextToken();;

				if(nocta.equals(cuenta)){
					resultado = datos;
					break;
				}
			}

			if (resultado.equals("")) resultado = "No se encontro el No de cuenta";

			//3.Cerrar archivo
			archivoIn.close();
		}

		catch(FileNotFoundException fnfe) {
			resultado= "Error: "+ fnfe;
		}

		catch(IOException ioe) {
			resultado= "Error : "+ioe;
		}

		return resultado;
	}

	public String consultarCuentaObj(String cuenta, int saldoN){
		String resultado = "";
		StringTokenizer st;
		int i = 0;

		while (i < arregloObjetoDP.length) {
			if(cuenta.equals(arregloObjetoDP[i].getNocta())){
				resultado = arregloObjetoDP[i].toString() + "\n";
				break;
			}
			i ++;
		}
		if(saldoN > 0){
			if(arregloObjetoDP[i].getTipo().equals("AHORRO") || arregloObjetoDP[i].getTipo().equals("INVERSION")){
				arregloObjetoDP[i].setSaldo(arregloObjetoDP[i].getSaldo() + saldoN);
			} else if(arregloObjetoDP[i].getTipo().equals("CREDITO") || arregloObjetoDP[i].getTipo().equals("HIPOTECA")){
				arregloObjetoDP[i].setSaldo(arregloObjetoDP[i].getSaldo() - saldoN);
			}
			resultado = arregloObjetoDP[i].toString();
		}

		if(saldoN < 0){
			if(arregloObjetoDP[i].getTipo().equals("HIPOTECA")){
				resultado = "No se puede hacer retiro";
			} else if(arregloObjetoDP[i].getTipo().equals("AHORRO") || arregloObjetoDP[i].getTipo().equals("INVERSION")){
				arregloObjetoDP[i].setSaldo(arregloObjetoDP[i].getSaldo() + saldoN);
			} else if (arregloObjetoDP[i].getTipo().equals("CREDITO")){
				arregloObjetoDP[i].setSaldo(arregloObjetoDP[i].getSaldo() - saldoN);
			}
			resultado = resultado + "\n" + arregloObjetoDP[i].toString();
		}

		if (resultado.equals("")) resultado = "No se encontro el No de cuenta";

		return resultado;
	}

	public String arregloObjetosDatos(){

		String resultado = "";
		if (arregloObjetoDP == null) return "No existe Arreglo de objetos";
		boolean rewrite = true;

		for (int i  = 0; i < arregloObjetoDP.length ; i++) {
			resultado = capturar(arregloObjetoDP[i].toStringText(), rewrite);
			rewrite = false;
			System.out.println(resultado);
		}

		return "Datos capturados Correctamente!";
	}

}
