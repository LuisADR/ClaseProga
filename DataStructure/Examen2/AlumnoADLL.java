import java.io.*;

public class AlumnoADLL{

  private AlumnoDP primero, actual, ultimo;
	private AlumnoDP primeroP, actualP, ultimoP;



  public AlumnoADLL(){
    datosArchivoListaAlumnos();
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

  public String consultarListaPromedios(){
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

  public void captura(String datos){

    if(primero == null){
      primero = new AlumnoDP(datos);
      ultimo = primero;
      ultimo.setNext(null);
    } else {
      actual = new AlumnoDP(datos);
      ultimo.setNext(actual);
      ultimo = actual;
      ultimo.setNext(null);
    }

  }

  public void capturaPromedio(String datos){

    if(primeroP == null){
      primeroP = new AlumnoDP(datos);
      ultimoP = primeroP;
      ultimoP.setNext(null);
    } else {
      actualP = new AlumnoDP(datos);
      ultimoP.setNext(actualP);
      ultimoP = actualP;
      ultimoP.setNext(null);
    }

  }


  public String generarListaPromedios(){
    float promedio;
    String datos;

    if(primero == null){
      return "No hay Alumnos registrados";
    } else {

      actual = primero;

      while(actual != null){

        promedio = (float)actual.getCal1() + (float)actual.getCal2() + (float)actual.getCal3();
        promedio = promedio / (float)3;

        actual.setPromedio(promedio);
        datos = actual.toString();
        System.out.println(promedio + "  " + datos);

        if (actual.getPromedio() >= 90) capturaPromedio(datos);
        actual = actual.getNext();
      }
    }

    return "Lista de Promedios Calculados";
  }

  public String calcularPromedios(){
    float promedio;
    String datos;

    if(primero == null){
      return "No hay Alumnos registrados";
    } else {

      actual = primero;

      while(actual != null){

        promedio = (float)actual.getCal1() + (float)actual.getCal2() + (float)actual.getCal3();
        promedio = promedio / (float)3;

        actual.setPromedio(promedio);
        datos = actual.toString();
        System.out.println(promedio + "  " + datos);

        actual = actual.getNext();
      }
    }

      return "Promedios Calculados";
  }

  public void datosArchivoListaAlumnos(){
		try{
			BufferedReader archivoIn = new BufferedReader(new FileReader("Alumnos.txt"));
			while(archivoIn.ready())
				captura(archivoIn.readLine());

				archivoIn.close();
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("Error: "+fnfe);
		}
		catch(IOException ioe)
		{
			System.out.println("Error: "+ioe);
		}


	}
}
