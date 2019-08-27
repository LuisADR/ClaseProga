import javax.swing.JOptionPane;

public class Arreglo8{

  private int numeros1[][], numeros2[][], resultado[][];
  private int renglones, columnas;

  private String desplegarDatos(int array[][], String msg, int r, int c){
    String resultado = msg;

    for (int i = 0; i < r; i++) {
      resultado = resultado + "\n";
      for (int j = 0;j < c; j++) {
        resultado = resultado + " " + array[i][j];
      }
    }

    return resultado;
  }

  private void obtenerDatos(int array[][], int r, int c){

    for (int i = 0; i < r; i++) {
      for (int j = 0;j < c; j++) {
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

  private void multiplicacion(int array1[][], int array2[][]){
    for (Integer i = 0; i < renglones; i++) {
      for (Integer j = 0; j < renglones; j++) {
        for (int k = 0; k < columnas; k++) {
          resultado[i][j] = resultado[i][j] + numeros1[i][k]*numeros2[k][j];
        }
      }
    }
  }

  private void restar(int array1[][], int array2[][]){
    for (int i = 0; i < renglones; i++) {
      for (int j = 0;j < columnas; j++) {
        resultado[i][j] = array1[i][j] - array2[i][j];
      }
    }
  }

  private void operaciones(int no){

    // 1. Obtener el no. de casillas del arreglo
    String strN = JOptionPane.showInputDialog("No. de renglones");
    renglones = Integer.parseInt(strN);

    strN = JOptionPane.showInputDialog("No. de columnas");
    columnas = Integer.parseInt(strN);

    // 2. Crear el arreglo en RAM
    if(no == 1 || no == 2){
      numeros1 = new int[renglones][columnas];
      numeros2 = new int[renglones][columnas];
      resultado = new int[renglones][columnas];
    } else{
      numeros1 = new int[renglones][columnas];
      numeros2 = new int[columnas][renglones];
      resultado = new int[renglones][renglones];
    }

    // 3. Obtener datos y asignarlos al arreglo
    if(no == 1 || no == 2){
      obtenerDatos(numeros1, renglones, columnas);
      JOptionPane.showMessageDialog(null, desplegarDatos(numeros1, "Primera matriz \n", renglones, columnas));
      obtenerDatos(numeros2, renglones, columnas);
      JOptionPane.showMessageDialog(null, desplegarDatos(numeros2, "Segunda matriz \n", renglones, columnas));
    } else {
      obtenerDatos(numeros1, renglones, columnas);
      JOptionPane.showMessageDialog(null, desplegarDatos(numeros1, "Primera matriz \n", renglones, columnas));
      obtenerDatos(numeros2, columnas, renglones);
      JOptionPane.showMessageDialog(null, desplegarDatos(numeros2, "Segunda matriz \n", columnas, renglones));
    }
    // Operaciones
    if(no == 1){
      suma(numeros1, numeros2);
      JOptionPane.showMessageDialog(null, desplegarDatos(resultado, "Resultado \n", renglones, columnas));
    } else if (no == 2){
      restar(numeros1, numeros2);
      JOptionPane.showMessageDialog(null, desplegarDatos(resultado, "Resultado \n", renglones, columnas));
    } else {
      multiplicacion(numeros1, numeros2);
      JOptionPane.showMessageDialog(null, desplegarDatos(resultado, "Resultado \n", renglones, renglones));
    }

  }

  public static void main(String[] args) {
    Arreglo8 objeto = new Arreglo8();
    int opcion = 0;

    do {
      String stOpcion = JOptionPane.showInputDialog("Operacion a realizar: \n 1)Suma Matriz\n2)Resta Matriz\n3)Multiplicacion Matriz\n5)Salir");
      opcion = Integer.parseInt(stOpcion);

      if(opcion == 1 || opcion == 2 || opcion == 3)
      objeto.operaciones(opcion);
    } while (opcion != 5);
  }
}
