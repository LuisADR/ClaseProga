import javax.swing.JOptionPane;

public class Arreglo6{

    private int numero;
    private String nombres[];
    private String nombres2[];

    private void obtenerDatos()
    {
        String strN;

        for(int i=0; i<nombres.length; i++)
            nombres[i] = JOptionPane.showInputDialog("Casilla["+i+"] =");
    }


    private void desplegarDatos(int arreglo[]){
        String valores = "";
        for(int i=0; i<arreglo.length; i++){
            valores = valores + "Casiila["+i+"] = "+arreglo[i]+"\n";
        }

        JOptionPane.showMessageDialog(null,valores);
    }


    private void principal(){
        //1. Obtener el tamaño del arreglo
        String strN = JOptionPane.showInputDialog("No. casillas del Arreglo:");
        numero = Integer.parseInt(strN);

        //2. Crear el arreglo en RAM
        numero = new int[numero];
        nombres2 = new String[nombre];

        //3. Obtener y asignar datos al arreglo
        obtenerDatos();

        //4. Desplegar datos
        desplegarDatos(numeros);


    }

    public static void main(String args[]){
        Arreglo6 objeto = new Arreglo6();

        objeto.principal();
    }
}
