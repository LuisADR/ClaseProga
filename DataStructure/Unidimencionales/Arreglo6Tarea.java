import javax.swing.JOptionPane;

public class Arreglo6Tarea
{
    private String nombre;
    private String nombres[];
    private String nombres2[];



    private void obtenerDatos()
    {
        String strN;

        for(int i=0; i<nombres.length; i++)
            nombres[i] = JOptionPane.showInputDialog("Casilla["+i+"] =");
    }

    private void desplegarDatos(String arreglo[])
    {
        String valores="Contenido del Arreglo:\n";

        for(int i=0; i<arreglo.length; i++)
            valores = valores + "Casilla["+i+"] = "+arreglo[i]+"\n";

        JOptionPane.showMessageDialog(null,valores);
    }

    private void intercambiarDatos()
    {
        int j=nombres.length - 1;

        for(int i=0; i<nombres.length; i++)
        {
            nombres2[i] = nombres[j];
            j--;
        }
    }

    private String busqueda(){

      String respuesta = "Los valores se encuentran en: \n";
      boolean encontrado = false;
      for (int i = 0;i < nombres.length ; i++) {
        if(nombres[i].equals(nombre)){
          int j = i + 1;
          respuesta = respuesta + "Posicion: " + j +"\n";
          encontrado = true;
        }
      }

      if(!encontrado) respuesta = "No se encontro el nombre";

      return respuesta;
    }
    private void principal(String array[])
    {
      if(array.length == 0){
        // 1. Obtener el no. de casillas del arreglo
        String strN = JOptionPane.showInputDialog("No. de casillas del Arreglo =");
        int numero = Integer.parseInt(strN);

        // 2. Crear el arreglo en RAM
        nombres   = new String[numero];
        nombres2 = new String[numero];

        // 3. Obtener datos y asignarlos al arreglo
        obtenerDatos();
      } else {
        nombres = array;
        nombres2 = new String[array.length];
      }
        // 4. Desplegar los datos del arreglo
        desplegarDatos(nombres);

        // 5. Intercambiar datos de un arreglo 1 a un arreglo 2
        intercambiarDatos();

        // 6. Desplegar los datos del arreglo
        desplegarDatos(nombres2);

        // 7. Preguntar por el string al buscador
        nombre = JOptionPane.showInputDialog("Nombre a buscar: ");

        // 8. Efectuar la busqueda del String y desplegar respuesta
        JOptionPane.showMessageDialog(null, busqueda());
    }

    public static void main(String args[])
    {
        Arreglo6Tarea objeto = new Arreglo6Tarea();

        objeto.principal(args);
    }
}
