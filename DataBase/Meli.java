import javax.swing.JOptionPane;

public class Meli {
  // Ok para empezar hay que recordar que los arreglos se debesn de inicializar con un valor.
  // Para eso tenemos que crear una variable global y despues iniciarlo en alguna funcion o dentro del main
  // Chucho hace esto:
  private String[] array; //Crea un arreglo con la palabra array
  private String array2[]; //Lo mismo pero con otra palabra


  // Despues puedes iniciarlo en una funcion

  public void intercambiarDatos(){
    array = new String[5]; //Aqui creamos un arreglo de 5 casillas
    array2 = new String[array.length]; //Aqui creamos el arreglo con el tamaño del otro arreglo

    /*Si lo quieres ver de esta manera, cuando escribes "new String['Valor entero Int']" de parametro
      Le envias un valor entero y el array.length te entrega un valor entero. Por eso sin importar el tamaño
      De tu otro arreglo puedes crear otro de la misma dimencion*/

  }

  public static void main(String[] args) {

    Meli objeto = new Meli();

  }
