import java.io.*;
import java.util.StringTokenizer;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BancoADLL{

  //Atributos
  private ClienteDP primero, actual, ultimo, posCliente;
  private DepositoDP primeroDeposito, ultimoDeposito, actualDeposito;
  private RetiroDP primeroRetiro, ultimoRetiro, actualRetiro;

  //Constructores
  public BancoADLL(){
    datosArchivoListaCliente();
    datosArchivoListaDeposito();
    datosArchivoListaRetiro();
  }

  public void listaClienteDatosArchivo(){
    try {

			//1. Abrir el archivo
			PrintWriter archivoOut = new PrintWriter(new FileWriter("Clientes.txt", false));

			//2.Escribir o almacenar los datos en el archivo
      actual = primero;

			while(actual != null){
        archivoOut.println(actual.toString());
        actual = actual.getNext();
      }

			//3. Cerrar el archivo
			archivoOut.close();

		}	catch(IOException ioe) {
			System.out.println("Error archivo: " + ioe);
		}
  }

  public void listaRetiroDatosArchivo(){
    try {

			//1. Abrir el archivo
			PrintWriter archivoOut = new PrintWriter(new FileWriter("Retiros.txt", false));

			//2.Escribir o almacenar los datos en el archivo
      actualRetiro = primeroRetiro;

			while(actualRetiro != null){
        archivoOut.println(actualRetiro.toString());
        actualRetiro = actualRetiro.getNext();
      }

			//3. Cerrar el archivo
			archivoOut.close();

		}	catch(IOException ioe) {
			System.out.println("Error archivo: " + ioe);
		}
  }

  public void listaDepositosDatosArchivo(){
    try {

			//1. Abrir el archivo
			PrintWriter archivoOut = new PrintWriter(new FileWriter("Depositos.txt", false));

			//2.Escribir o almacenar los datos en el archivo
      actualDeposito = primeroDeposito;

			while(actualDeposito != null){
        archivoOut.println(actualDeposito.toString());
        actualDeposito = actualDeposito.getNext();
      }

			//3. Cerrar el archivo
			archivoOut.close();

		}	catch(IOException ioe) {
			System.out.println("Error archivo: " + ioe);
		}
  }

  public void datosArchivoListaCliente(){
    try{
      BufferedReader archivoIn= new BufferedReader(new FileReader("Clientes.txt"));

      while(archivoIn.ready()){
        capturar(archivoIn.readLine());
      }
      archivoIn.close();
    }

    catch(FileNotFoundException fnfe){
      System.out.println("Error: "+fnfe);
    }

    catch(IOException ioe){
      System.out.println("Error: "+ioe);
    }
  }

  public void datosArchivoListaDeposito(){
    try{
      BufferedReader archivoIn= new BufferedReader(new FileReader("Depositos.txt"));

      while(archivoIn.ready()){
        crearNodoDeposito(archivoIn.readLine());
      }
      archivoIn.close();
    }

    catch(FileNotFoundException fnfe){
      System.out.println("Error: "+fnfe);
    }

    catch(IOException ioe){
      System.out.println("Error: "+ioe);
    }
  }

  public void datosArchivoListaRetiro(){
    try{
      BufferedReader archivoIn= new BufferedReader(new FileReader("Retiros.txt"));

      while(archivoIn.ready()){
        crearNodoRetiro(archivoIn.readLine());
      }
      archivoIn.close();
    }

    catch(FileNotFoundException fnfe){
      System.out.println("Error: "+fnfe);
    }

    catch(IOException ioe){
      System.out.println("Error: "+ioe);
    }
  }
  public String capturar(String datos){

    if(primero == null){
      primero = new ClienteDP(datos);
      ultimo = primero;
      ultimo.setNext(null);
    } else {
      actual = new ClienteDP(datos);
      ultimo.setNext(actual);
      ultimo = actual;
      ultimo.setNext(null);
    }
    return "Nuevo nodo creado: " + datos;
  }

  public String consultar(){
    String datos = "";

    if (primero == null) datos = "Lista Vacia";
    else {
      actual = primero;
      while (actual != null){
        datos = datos + actual.toString() + "\n";
        actual  = actual.getNext();
      }
    }
    return datos;
  }

  public String consultarRetiros(){
    String datos = "";

    if (primeroRetiro == null) datos = "Lista Vacia";
    else {
      actualRetiro = primeroRetiro;
      while (actualRetiro != null){
        datos = datos + actualRetiro.toString() + "\n";
        actualRetiro  = actualRetiro.getNext();
      }
    }

    return datos;
  }

  public String consultarDepositos(){
    String datos = "";

    if (primeroDeposito == null) datos = "Lista Vacia";
    else {
      actualDeposito = primeroDeposito;
      while (actualDeposito != null){
        datos = datos + actualDeposito.toString() + "\n";
        actualDeposito  = actualDeposito.getNext();
      }
    }
    return datos;
  }

  public String consultarTipo (String tcta){
    String datos = "";

    if (primero == null) datos = "Lista Vacia";
    else {
      actual = primero;
      while (actual != null){
        if(actual.getTipo().equals(tcta)){
          datos = datos + actual.toString() + "\n";
        }
        actual  = actual.getNext();
      }
    }

    return datos;
  }

  public String consultarNocta(String ncta){
    String datos="";
    Boolean encontrado=false;

    if(primero==null){
      datos="LISTA_VACIA";
    }

    else{
      actual=primero;
      while(actual!=null && !encontrado){
        if(actual.getNocta().equals(ncta)){
          datos=datos+actual.toString();
          posCliente=actual;
          encontrado=true;
        }
        actual=actual.getNext();
      }
      if(encontrado==false){
        datos="NOT_FOUND";
      }
    }
    System.out.println(posCliente);
    return datos;
  }

  private String crearNodoDeposito(String datos){
    String respuesta = "";

    if(primeroDeposito == null){
      primeroDeposito = new DepositoDP(datos);
      ultimoDeposito = primeroDeposito;
      ultimoDeposito.setNext(null);
      respuesta = "Deposito creado";
    } else {
      actualDeposito = new DepositoDP(datos);
      ultimoDeposito.setNext(actualDeposito);
      ultimoDeposito = actualDeposito;
      ultimoDeposito.setNext(null);
      respuesta = "Deposito creado";
    }

    return respuesta;
  }

  private String crearNodoRetiro(String datos){
    String respuesta = "";

    if(primeroRetiro == null){
      primeroRetiro = new RetiroDP(datos);
      ultimoRetiro = primeroRetiro;
      ultimoRetiro.setNext(null);
      respuesta = "Retiro creado";
    } else {
      actualRetiro = new RetiroDP(datos);
      ultimoRetiro.setNext(actualRetiro);
      ultimoRetiro = actualRetiro;
      ultimoRetiro.setNext(null);
      respuesta = "Retiro creado";
    }

    return respuesta;
  }

  public String depositar(int cantidad){
    String datos = "";

    if(posCliente.getTipo().equals("INVERSION") || posCliente.getTipo().equals("AHORRO")){
      int nuevoSaldo = posCliente.getSaldo() + cantidad;

      datos = posCliente.toStringOperacion() + "_" + cantidad + "_" + nuevoSaldo;
      crearNodoDeposito(datos);

      posCliente.setSaldo(nuevoSaldo);
    }

    if(posCliente.getTipo().equals("HIPOTECA") || posCliente.getTipo().equals("CREDITO")){
      int nuevoSaldo = posCliente.getSaldo() - cantidad;

      datos = posCliente.toStringOperacion() + "_" + cantidad + "_" + nuevoSaldo;
      crearNodoDeposito(datos);

      posCliente.setSaldo(nuevoSaldo);
    }
    return datos;
  }

  public String retirar(int cantidad){
    String datos = "";

    if(posCliente.getTipo().equals("INVERSION") || posCliente.getTipo().equals("AHORRO")){
      int nuevoSaldo = posCliente.getSaldo() - cantidad;

      datos = posCliente.toStringOperacion() + "_" + cantidad + "_" + nuevoSaldo;
      crearNodoRetiro(datos);

      posCliente.setSaldo(nuevoSaldo);
    }

    if(posCliente.getTipo().equals("CREDITO")){
      int nuevoSaldo = posCliente.getSaldo() + cantidad;

      datos = posCliente.toStringOperacion() + "_" + cantidad + "_" + nuevoSaldo;
      crearNodoRetiro(datos);

      posCliente.setSaldo(nuevoSaldo);
    }

    if (posCliente.getTipo().equals("HIPOTECA")){
      return "No se puede realizar retiros";
    }

    return datos;
  }
}
