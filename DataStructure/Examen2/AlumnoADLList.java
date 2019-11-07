import java.util.LinkedList;
import java.io.*;

public class AlumnoADLList{

  private AlumnoDP actual;

  private LinkedList listaAlumno = new LinkedList();
  private LinkedList listaAlumnoPromedio = new LinkedList();
  private LinkedList listaAlumnoCalPromedio = new LinkedList();

  public AlumnoADLList(){
    datosArchivoListaAlumnos();
  }

  public String consultar(){
    String datos = "";
    int i = 0;

    if(listaAlumno.isEmpty()){
      return "No hay Alumnos registrados";
    } else {
      while(i < listaAlumno.size()){
        actual = (AlumnoDP) listaAlumno.get(i);
        datos = datos + actual.toString() + "\n";
        i++;
      }
    }

    return datos;
  }

  public String consultarListaPromedios(){
    String datos = "";
    int i = 0;

    if(listaAlumnoPromedio.isEmpty()){
      return "No se ha generado promedios";
    } else {
      while(i < listaAlumnoPromedio.size()){
        actual = (AlumnoDP) listaAlumnoPromedio.get(i);
        datos = datos + actual.toString() + "\n";
        i++;
      }
    }

    return datos;
  }

  public String calcularPromedios(){
    float promedio;
    String datos;
    int i = 0;

    if(listaAlumno.isEmpty()){
      return "No hay Alumnos registrados";
    } else {
      while(i < listaAlumno.size()){
        actual = (AlumnoDP) listaAlumno.get(i);
        promedio = (float)actual.getCal1() + (float)actual.getCal2() + (float)actual.getCal3();
        promedio = promedio / (float)3;

        actual.setPromedio(promedio);
        datos = actual.toString();
        System.out.println(promedio + "  " + datos);

        listaAlumnoCalPromedio.add(new AlumnoDP(datos));
        i++;
      }

    }

    listaAlumno = listaAlumnoCalPromedio;
    return "Promedios Calculados";
  }

  public String generarListaPromedios(){
    float promedio;
    String datos;
    int i = 0;

    if(listaAlumno.isEmpty()){
      return "No hay Alumnos registrados";
    } else {
      while(i < listaAlumno.size()){
        actual = (AlumnoDP) listaAlumno.get(i);
        promedio = (float)actual.getCal1() + (float)actual.getCal2() + (float)actual.getCal3();
        promedio = promedio / (float)3;

        actual.setPromedio(promedio);
        datos = actual.toString();
        System.out.println(promedio + "  " + datos);
        if(actual.getPromedio() >= 90) listaAlumnoPromedio.add(new AlumnoDP(datos));
        i++;
      }

    }

    return "Lista de Promedios Calculados";
  }

  public void datosArchivoListaAlumnos(){
    try{
      BufferedReader archivoIn= new BufferedReader(new FileReader("Alumnos.txt"));

      while(archivoIn.ready()){
        listaAlumno.add(new AlumnoDP(archivoIn.readLine()));
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

}
