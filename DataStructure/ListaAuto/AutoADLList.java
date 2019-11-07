import java.io.*;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class AutoADLList{

  private AutoDP primero, actual, ultimo, anterior, posCliente;
  private VentaDP cotizacion;
  private int nodoActual;

  private LinkedList listaCoches = new LinkedList();
  private LinkedList listaVentas = new LinkedList();

  public AutoADLList (){
    datosArchivoListaCoches();
    datosArchivoListaVentas();
  }

  public String capturar(String datos){
    String resultado = "", respuesta ="";
    StringTokenizer st = new  StringTokenizer(datos, "_");
    String clave = st.nextToken();

    respuesta = consultarClave(clave);

    if(respuesta.equals("LISTA_VACIA") || respuesta.equals("NOT_FOUND")){
      resultado = captura(datos);
    } else {
      resultado = "La clave ya existe";
    }

    return resultado;
  }

  public String captura(String datos){
    listaCoches.add(new AutoDP(datos));
    return "Datos Capturados";
  }

  public String consultar(){
    String datos = "";
    int i = 0;

    if(listaCoches.isEmpty()){
      return "No hay coches en la lista";
    } else {
      while(i < listaCoches.size()){
        actual = (AutoDP) listaCoches.get(i);
        datos = datos + actual.toString() + "\n";
        i++;
      }
    }

    return datos;
  }

  public String consultarVentas(){
    String datos = "";
    int i = 0;

    if(listaVentas.isEmpty()){
      return "No hay coches en la lista";
    } else {
      while(i < listaVentas.size()){
        cotizacion = (VentaDP) listaVentas.get(i);
        datos = datos + cotizacion.toString() + "\n";
        i++;
      }
    }

    return datos;
  }

  public String consultarClave(String dato){
    String datos = "";
    boolean encontrado = false;
    int i = 0;

    if(listaCoches.isEmpty()){
      datos = "LISTA_VACIA";
    } else {

      while(i < listaCoches.size() && !encontrado){
        actual = (AutoDP)listaCoches.get(i);

        if(actual.getClave().equals(dato)){
          datos = datos + actual.toString();
          nodoActual = i;
          encontrado = true;
        }
        i++;
      } if(encontrado ==  false){
        datos = "NOT_FOUND";
      }
    }

    return datos;
  }

  public String consultarMarca(String dato){
    String datos = "";
    boolean encontrado = false;
    int i = 0;

    if(listaCoches.isEmpty()){
      datos = "La lista esta vacia";
    } else {

      while(i < listaCoches.size()){
        actual = (AutoDP)listaCoches.get(i);

        if(actual.getMarca().equals(dato)){
          datos = datos + actual.toString() + "\n";
          encontrado = true;
        }
        i++;
      } if(encontrado ==  false){
        datos = "No se encontro la marca";
      }
    }

    return datos;
  }

  public String cotizarAuto(int plazo){

    if(plazo != 12)
      if (plazo != 24)
        if (plazo != 48)
          return "El plazo debe ser 12, 24 o 48";


    String datos = actual.getClave() + "_" + actual.getMarca() + "_" + actual.getTipo() + "_" + actual.getPrecio() + "_" + plazo + "_";

    if(plazo == 12) {

       datos = datos + "10" + "_";

       float precionInt = (float)1.10 * (float)actual.getPrecio();
       datos = datos + precionInt + "_";

       float mensualidad = (float)actual.getPrecio() / 12;
       datos = datos + mensualidad;
    }
    if(plazo == 24) {
       datos = datos + "15" + "_";

       float precionInt = (float)1.15 * (float)actual.getPrecio();
       datos = datos + precionInt + "_";

       float mensualidad = (float)actual.getPrecio() / 24;
       datos = datos + mensualidad;
    }
    if(plazo == 48) {
       datos = datos + "20" + "_";

       float precionInt = (float)1.20 * (float)actual.getPrecio();
       datos = datos + precionInt + "_";

       float mensualidad = (float)actual.getPrecio() / 48;
       datos = datos + mensualidad;
    }

    cotizacion = new VentaDP(datos);
    return cotizacion.toStringConsulta();
  }

  public String venta (int plazo){
    String operacion = cotizarAuto(plazo);

    listaVentas.add(cotizacion);
    return "Venta realizada";
  }

  public void capturarVentas (String datos){
    cotizacion = new VentaDP(datos);

    listaVentas.add(cotizacion);
  }

  public void datosArchivoListaCoches(){
    try{
      BufferedReader archivoIn= new BufferedReader(new FileReader("Coches.txt"));

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

  public void datosArchivoListaVentas(){
    try{
      BufferedReader archivoIn= new BufferedReader(new FileReader("Ventas.txt"));

      while(archivoIn.ready()){
        capturarVentas(archivoIn.readLine());
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

  public void datosListaCocheArchivo(){
    try {

			//1. Abrir el archivo
			PrintWriter archivoOut = new PrintWriter(new FileWriter("Coches.txt", false));

			//2.Escribir o almacenar los datos en el archivo

			for(int i = 0; i < listaCoches.size(); i++){
        actual = (AutoDP)listaCoches.get(i);
        archivoOut.println(actual.toString());
      }

			//3. Cerrar el archivo
			archivoOut.close();

		}	catch(IOException ioe) {
			System.out.println("Error archivo: " + ioe);
		}
  }

  public void datosListaVentasArchivo(){
    try {

			//1. Abrir el archivo
			PrintWriter archivoOut = new PrintWriter(new FileWriter("Ventas.txt", false));

			//2.Escribir o almacenar los datos en el archivo

			for(int i = 0; i < listaVentas.size(); i++){
        cotizacion = (VentaDP)listaVentas.get(i);
        archivoOut.println(cotizacion.toString());
      }

			//3. Cerrar el archivo
			archivoOut.close();

		}	catch(IOException ioe) {
			System.out.println("Error archivo: " + ioe);
		}
  }
}
