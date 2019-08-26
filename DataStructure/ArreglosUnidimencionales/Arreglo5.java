import javax.swing.JOptionPane;

public class Arreglo5{

  private int numero;
  private int numeros[];
  private int numeros2[];
  private int datosOrdenados[];

  private void obtenerDatos(){
    String str;

    for (int i = 0; i < numeros.length; i++) {
      str = JOptionPane.showInputDialog("Casilla ["+ i +"] = ");
      numero = Integer.parseInt(str);
      numeros[i] = numero;
      //comentario 
    }

  }

  private void desplegarDatos( int arreglo[], String mensaje){
    String str = mensaje + ": \n";

    for (int i = 0; i < arreglo.length; i++) {
      str = str + arreglo[i] + "\n";
    }

    JOptionPane.showMessageDialog(null, str);
  }

  private void intercambiarDatos(){

    int j = numeros.length - 1;

    for (Integer i = 0; i < numeros.length; i++) {
      numeros2[j] = numeros[i];
      j --;
    }
  }

  /* Tarea: Ordenar los datos
     Los datos se Ordenan de manera ascendente.
     El proceso consiste en cuatro pasos:
     1- Comparar que el dato sea mayor que el siguiente (array[i] > array[i+1])
     2- Inventir los datos
     3- Repetir el proceso hasta que la condicional no entre en un ciclo
     4- Guardar nuestros datos
  */

  private void ordenarDatos(int arreglo[]){

    // Creamos e inicializamos nuestros valores
    int temporal;
    boolean ordenado = false;

    while(!ordenado){
      /* Cambiamos el valor a true, de este modo si no entra en el condicional
      saldra del siclo while.*/
      ordenado = true;

      for (Integer i = 0; i < arreglo.length - 1; i++) {

        if(arreglo[i] > arreglo[i+1]){
          // Guardamos la variable para no perderla
          temporal = arreglo[i];
          // Asignamos el valor de la posicion i+1 a i
          arreglo[i] = arreglo[i+1];
          // Sustituimos i+1 por el valor anteriormente guardado
          arreglo[i+1] = temporal;
          ordenado = false;
        }

      }

    }

    // Almacenamos el resultado en nuetra variable
    for (Integer i = 0; i < arreglo.length; i++) {
      datosOrdenados[i] = arreglo[i];
    }
  }

  private void principal(){

    // Obtener el numero de casillas|
    String str = JOptionPane.showInputDialog("No de casillas del arreglo");

    // Crear el arreglo en ram
    numero = Integer.parseInt(str);
    numeros = new int[numero];
    numeros2 = new int[numero];
    datosOrdenados = new int[numero];

    // Obtener datos y asignarlos al Arreglo
    obtenerDatos();

    // Desplegar los datos del arreglo
    desplegarDatos(numeros, "Datos Ingresados");

    // Intercambiar datos de un arreglo a otro
    intercambiarDatos();

    // Desplegar datos del arrelgo2
    desplegarDatos(numeros2, "Datos invertidos");

    // Ordenar los datos del arreglo
    ordenarDatos(numeros);

    // Desplegamos los datosOrdenados
    desplegarDatos(datosOrdenados, "Datos Ordenados");
  }

  public static void main (String args[]){

    Arreglo5 objeto = new Arreglo5();
    objeto.principal();

  }
}
