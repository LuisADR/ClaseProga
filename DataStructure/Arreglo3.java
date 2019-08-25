import javax.swing.JOptionPane;

public class Arreglo3{

  private int numero;
  private int numeros[];

  private void principal(){

    numeros = new int[3];
    numero = 5;
    numeros[0] = numero*4;

    System.out.println(numero + "  " + numeros[0]);
    JOptionPane.showMessageDialog(null, numero + "  " + numeros[0]);
  }

  public static void main (String args[]){

    Arreglo3 objeto = new Arreglo3();
    objeto.principal();

  }
}
