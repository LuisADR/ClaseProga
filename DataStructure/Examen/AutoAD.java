import java.io.*;
import java.util.StringTokenizer;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AutoAD{
  private BufferedReader archivoIn;
	private PrintWriter archivoOut;
	private int noAutos, posicion;

  private AutoDP arregloAutoDP[];

  public AutoAD(){
    String datos = "";

		try {

			//1. Abrir el archivo
			archivoIn = new BufferedReader(new FileReader("Autos.txt"));

			//2.Escribir o almacenar los datos en el archivo
			while(archivoIn.ready()){

				archivoIn.readLine();
				noAutos++;
			}

			// Cerrar el archivo
			archivoIn.close();

      //Creamos el arreglo
      String respuesta = datosArchArregloObj();
      System.out.println(respuesta);

		} catch(FileNotFoundException fnfe){
          System.out.print("Error: "+fnfe);
    } catch(IOException ioe){
        System.out.print("Error: "+ioe);
    }
			System.out.println("Strings en el archivo: " + noAutos);
	}

  public String datosArchArregloObj(){
		String respuesta = "";
		int i = 0;

		arregloAutoDP = new AutoDP[noAutos];

		try {
			// Abrir el archivo
			archivoIn = new BufferedReader(new FileReader("Autos.txt"));

			// Procesar datos
			while(archivoIn.ready()){
        arregloAutoDP[i]= new AutoDP(archivoIn.readLine());
        System.out.println(arregloAutoDP[i].toString());
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

	public String capturar (String datos, String archivo) {

		String resultado="";

		try {

			//1. Abrir el archivo
			archivoOut=new PrintWriter(new FileWriter(archivo, true));

			//2.Escribir o almacenar los datos en el archivo
			archivoOut.println(datos);

			//3. Cerrar el archivo
			archivoOut.close();
			if(archivo.equals("Autos.txt"))noAutos++;
			resultado="Datos capturas: "+ datos;

		}	catch(IOException ioe) {
			resultado= "Error: "+ ioe;
		}

		return resultado;
  }

  public String consultar(String archivo) {

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

  public String consultarMarca(String palabra){
    String resultado = "";

    for (int i = 0; i < noAutos; i++) {
      System.out.println(palabra + " " + arregloAutoDP[i].getMarca());
      if(palabra.equals(arregloAutoDP[i].getMarca())) resultado = resultado + arregloAutoDP[i].toString() + "\n";
    }

    if (resultado.equals("")) resultado = "No se encontro la Marca";
		return resultado;
  }

  public String consultarClave(String palabra){
    String resultado = "";

    for (int i = 0; i < noAutos; i++) {

      if(palabra.equals(arregloAutoDP[i].getClave())){
        resultado = resultado + arregloAutoDP[i].toString() + "\n";
        posicion = i;
        System.out.println(posicion);
      }
    }

    if (resultado.equals("")) resultado = "No se encontro la clave";
		return resultado;
  }

  public String consularArregloObj(){
    String resultado = "";

    for (int i = 0; i < noAutos; i++) {
      resultado = resultado + arregloAutoDP[i].toString() + "\n";
    }

    if (resultado.equals("")) resultado = "Arreglo Vacio";
		return resultado;
  }

  public String cotizarAuto(int plazo){
    String respuesta = "Clave auto = " + arregloAutoDP[posicion].getClave() + "\n" +
                       "Marca = " + arregloAutoDP[posicion].getMarca() + "\n" +
                       "Tipo = " + arregloAutoDP[posicion].getTipo() + "\n" +
                       "Precio = " + arregloAutoDP[posicion].getPrecio() + "\n" +
                       "Plazo = " + plazo + "\n";

     if(plazo == 12) {
       respuesta = respuesta + "Interes = 10\n";

       float precionInt = (float)1.10 * (float)arregloAutoDP[posicion].getPrecio();
       respuesta = respuesta + "Precio con Interes = " + precionInt + "\n";

       float mensualidad = (float)arregloAutoDP[posicion].getPrecio() / 12;
       respuesta = respuesta + "Mensualidad = " + mensualidad + "\n";
     }
     if(plazo == 24) {
       respuesta = respuesta + "Interes = 15\n";

       float precionInt = (float)1.15 * (float)arregloAutoDP[posicion].getPrecio();
       respuesta = respuesta + "Precio con Interes = " + precionInt + "\n";

       float mensualidad = (float)arregloAutoDP[posicion].getPrecio() / 24;
       respuesta = respuesta + "Mensualidad = " + mensualidad + "\n";
     }
     if(plazo == 48) {
       respuesta = respuesta + "Interes = 20\n";

       float precionInt = (float)1.20 * (float)arregloAutoDP[posicion].getPrecio();
       respuesta = respuesta + "Precio con Interes = " + precionInt + "\n";

       float mensualidad = (float)arregloAutoDP[posicion].getPrecio() / 48;
       respuesta = respuesta + "Mensualidad = " + mensualidad + "\n";

     }

     if(plazo == 12 || plazo == 24 || plazo == 48){
       return respuesta;
     }
     return "El plazo debe ser 12, 24 o 48";
  }

  public String venta(int plazo){
    String respuesta = arregloAutoDP[posicion].getClave() + "_" +
                       arregloAutoDP[posicion].getMarca() + "_" +
                       arregloAutoDP[posicion].getTipo() + "_" +
                       arregloAutoDP[posicion].getPrecio() + "_" +
                       plazo + "_";

     if(plazo == 12) {
       respuesta = respuesta + "10_";

       float precionInt = (float)1.10 * (float)arregloAutoDP[posicion].getPrecio();
       respuesta = respuesta + precionInt + "_";

       float mensualidad = (float)arregloAutoDP[posicion].getPrecio() / 12;
       respuesta = respuesta + mensualidad;
     }
     if(plazo == 24) {
       respuesta = respuesta + "15_";

       float precionInt = (float)1.15 * (float)arregloAutoDP[posicion].getPrecio();
       respuesta = respuesta + precionInt + "_";

       float mensualidad = (float)arregloAutoDP[posicion].getPrecio() / 24;
       respuesta = respuesta + mensualidad;
     }
     if(plazo == 48) {
       respuesta = respuesta + "20_";

       float precionInt = (float)1.20 * (float)arregloAutoDP[posicion].getPrecio();
       respuesta = respuesta + precionInt + "_";

       float mensualidad = (float)arregloAutoDP[posicion].getPrecio() / 48;
       respuesta = respuesta + mensualidad;

     }

     if(plazo == 12 || plazo == 24 || plazo == 48){
       capturar(respuesta, "Ventas.txt");
       return "Venta realizada correctamente";
     }

     return "El plazo debe ser 12, 24 o 48";
  }
}
