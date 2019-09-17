import java.io.*;
import java.util.StringTokenizer;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BancoAD {

	private String archivo = "Clientes.txt", archivoDep = "Depositos.txt",
								 archivoCli = "Clientes.txt", archivoRet = "Retiros.txt";
	private BufferedReader archivoIn;
	private PrintWriter archivoOut;
	private int noClientes, posicion;

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
			datos= "Archivo no entontrado";
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

	public String consultarCuenta(String cuenta){
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

	public String consultarCuentaObj(String cuenta){
		String resultado = "";
		StringTokenizer st;
		int i = 0;

		while (i < arregloObjetoDP.length) {
			if(cuenta.equals(arregloObjetoDP[i].getNocta())){
				resultado = arregloObjetoDP[i].toString() + "\n";

				posicion = i;

				break;
			}
			i ++;
		}

		if (resultado.equals("")) return "No se encontro el No de cuenta";

		return resultado;
	}

	public String depositar(String cuenta, int saldoN){

		String resultado = "", deposito = "";
		posicion = -1;

		//Realizamos la consulta del Nocta y añadimos el saldo anterior
		resultado = consultarCuentaObj(cuenta) + "\n";

		//Si no se encuentra Nocta, terminamos el proceso
		if (posicion == -1) return "No se encontro el No de cuenta";

		// Cambiar archivo a Retiro
		archivo = archivoDep;

		//Acciones para Clientes de tipo Ahorro, Inversion
		if(arregloObjetoDP[posicion].getTipo().equals("AHORRO") ||
			 arregloObjetoDP[posicion].getTipo().equals("INVERSION")){

			//Realizamos la operacion
			arregloObjetoDP[posicion].setSaldo(arregloObjetoDP[posicion].getSaldo() + saldoN);

			//Obtenemos los valores
			deposito = arregloObjetoDP[posicion].toStringOperacion(-saldoN);

		}

		else if(arregloObjetoDP[posicion].getTipo().equals("CREDITO") ||
						arregloObjetoDP[posicion].getTipo().equals("HIPOTECA")){

			//Realizamos la operacion
			arregloObjetoDP[posicion].setSaldo(arregloObjetoDP[posicion].getSaldo() - saldoN);

			//Obtenemos los valores
			deposito = arregloObjetoDP[posicion].toStringOperacion(saldoN);

		}

		//Almacenamos los datos en el archivo
		capturar(deposito, false);

		//Añadimos el nuevo saldo de la operacion
		resultado = resultado + arregloObjetoDP[posicion].toString();

		// Cambiar archivo a Cliente
		archivo = archivoCli;

		//Regresamos el saldo anterior y el saldo nuevo
		return resultado;
	}

	public String retirar(String cuenta, int saldoN){
		String resultado = "", retiro = "";
		posicion = -1;

		//Realizamos la consulta del Nocta y añadimos el saldo anterior
		resultado = consultarCuentaObj(cuenta) + "\n";

		//Si no se encontro Nocta, terminar el proceso
		if (posicion == -1) return "No se encontro el No de cuenta";

		// Cambiar archivo a Retiro
		archivo = archivoRet;

		if(arregloObjetoDP[posicion].getTipo().equals("HIPOTECA")){
			resultado = "No se puede hacer retiro ";
		}

		//Accion para Clientes de tipo Ahorro e Inversion
		else if(arregloObjetoDP[posicion].getTipo().equals("AHORRO") ||
						arregloObjetoDP[posicion].getTipo().equals("INVERSION")){

			//Realizamos la operacion
			arregloObjetoDP[posicion].setSaldo(arregloObjetoDP[posicion].getSaldo() - saldoN);

			//Obtenemos los valores
			retiro = arregloObjetoDP[posicion].toStringOperacion(-saldoN);
		}

		//Accion para archivos de tipo Credito
		else if (arregloObjetoDP[posicion].getTipo().equals("CREDITO")){

			//Realizamos la operacion
			arregloObjetoDP[posicion].setSaldo(arregloObjetoDP[posicion].getSaldo() + saldoN);

			//Obtenemos los valores
			retiro = arregloObjetoDP[posicion].toStringOperacion(saldoN);
		}

		//Añadimos el nuevo saldo de la operacion
		resultado = resultado + arregloObjetoDP[posicion].toString();

		//Almacenamos los datos en el archivo
		capturar(retiro, false);

		// Cambiar archivo a Cliente
		archivo = archivoCli;

		//Regresamos el saldo anterior y el saldo nuevo
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

	public String consultarDep(){
		//Cambiamos el archivo a leer
		archivo = archivoDep;

		String dato = consultarClientes();

		//Regresamos al archivo cliente
		archivo = archivoCli;

		return dato;
	}

	public String consultarRet(){
		//Cambiamos el archivo a leer
		archivo = archivoRet;

		String dato = consultarClientes();

		//Regresamos al archivo cliente
		archivo = archivoCli;

		return dato;
	}

}
