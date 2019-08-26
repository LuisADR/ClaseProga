import javax.swing.JOptionPane;

public class Arreglo7{

  private int numeros1[][];
  private int renglones, columnas;

  private String desplegarDatos(){
    String resultado = "Datos:";

    for (int i = 0; i < renglones; i++) {
      resultado = resultado + "\n";
      for (int j = 0;j < columnas; j++) {
        resultado = resultado + " " + numeros1[i][j];
      }
    }

    return resultado;
  }

  private void obtenerDatos(){

    for (int i = 0; i < renglones; i++) {
      for (int j = 0;j < columnas; j++) {
        numeros1[i][j] = Integer.parseInt(JOptionPane.showInputDialog("Casilla["+i+"]["+j+"]"));
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

    // 3. Obtener datos y asignarlos al arreglo
    obtenerDatos();

    // 4. Desplegar los datos del arreglo
    JOptionPane.showMessageDialog(null, desplegarDatos());

  }

  public static void main(String[] args) {
    Arreglo7 objeto = new Arreglo7();
    objeto.principal();
  }
}
