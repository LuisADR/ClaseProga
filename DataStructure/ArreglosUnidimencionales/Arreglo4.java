import javax.swing.JOptionPane;

public class Arreglo4{

  private int numero;
  private int numeros[];

  private void principal(){

    // Obtener el numero de casillas
    String str = JOptionPane.showInputDialog("No de casillas del arreglo");

    // Crear el arreglo en ram
    numero = Integer.parseInt(str);
    numeros = new int[numero];


    // Obtener datos y asignarlos al Arreglo
    for (int i = 0; i < numeros.length; i++) {
      str = JOptionPane.showInputDialog("Casilla ["+ i +"] = ");
      numero = Integer.parseInt(str);
      numeros[i] = numero;
    }

    // Desplegar los datos del arreglo
    str = "Contenido de datos: \n";
    for (int i = 0; i < numeros.length; i++) {
      str = str + "Casilla["+i+"] =" + numeros[i] + "\n";
    }

    JOptionPane.showMessageDialog(null, str);
  }

  public static void main (String args[]){

    Arreglo4 objeto = new Arreglo4();
    objeto.principal();

  }
}
