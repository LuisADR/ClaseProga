import javax.swing.JOptionPane;

public class Arreglo7Tarea{

  private int numeros1[][], numeros2[][], resultado[][];
  private int renglones, columnas;

  private String desplegarDatos(int array[][], String msg){
    String resultado = msg;

    for (int i = 0; i < renglones; i++) {
      resultado = resultado + "\n";
      for (int j = 0;j < columnas; j++) {
        resultado = resultado + " " + array[i][j];
      }
    }

    return resultado;
  }

  private void obtenerDatos(int array[][]){

    for (int i = 0; i < renglones; i++) {
      for (int j = 0;j < columnas; j++) {
        array[i][j] = Integer.parseInt(JOptionPane.showInputDialog("Casilla["+i+"]["+j+"]"));
      }
    }
  }

  private void suma(int array1[][], int array2[][]){
    for (int i = 0; i < renglones; i++) {
      for (int j = 0;j < columnas; j++) {
        resultado[i][j] = array1[i][j] + array2[i][j];
      }
    }
  }

  private void principal(){

    // 1. Obtener el no. de casillas del arreglo
    String strN = JOptionPane.showInputDialog("No. de renglones");
    renglones = Integer.parseInt(strN);

    strN = JOptionPane.showInputDialog("No. de columnas");
    columnas = Integer.parseInt(strN);

    // 2. Crear el arreglo en RAM
    numeros1 = new int[renglones][columnas];
    numeros2 = new int[renglones][columnas];
    resultado = new int[renglones][columnas];

    // 3. Obtener datos y asignarlos al arreglo
    obtenerDatos(numeros1);

    // 4. Desplegar los datos del arreglo
    JOptionPane.showMessageDialog(null, desplegarDatos(numeros1, "Primera matriz"));

    // 3. Obtener datos y asignarlos al arreglo
    obtenerDatos(numeros2);

    // 4. Desplegar los datos del arreglo
    JOptionPane.showMessageDialog(null, desplegarDatos(numeros2, "Segunda matriz"));

    // Sumar los datos de dos arreglos
    suma(numeros1, numeros2);

    // Desplegar los datos del arreglo
    JOptionPane.showMessageDialog(null, desplegarDatos(resultado, "Resultado"));

  }

  public static void main(String[] args) {
    Arreglo7Tarea objeto = new Arreglo7Tarea();
    objeto.principal();
  }
}
