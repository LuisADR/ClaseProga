import java.util.*;
import java.io.*;

public class LibroADList
{
    private LibroDP primero, ultimo, actual;


    public String capturar(String datos)
    {
        if(primero == null)
        {
            primero = new LibroDP(datos);
            ultimo = primero;
            ultimo.setNext(null);
            ultimo.setPrev(null);
        }
        else
        {
            actual = new LibroDP(datos);
            ultimo.setNext(actual);  //Enlace de nodos
            actual.setPrev(ultimo);
            ultimo = actual;
            ultimo.setNext(null);
        }


        return "Nodo creado: "+datos;
    }

    /*public String consultar()
    {
        String datos="";

        if(primero == null)
            datos = "Lista vacia...";
        else
        {
            actual = primero;

            while(actual != null)
            {
                datos = datos + actual.toString() + "\n";

                actual = actual.getNext();
            }
        }

        return datos;
    }*/

    public String consultar()
    {
        String datos="";

        if(primero == null)
            datos = "Lista vacia...";
        else
        {
            actual = ultimo;

            while(actual != null)
            {
                datos = datos + actual.toString() + "\n";

                actual = actual.getPrev();
            }
        }

        return datos;
    }

    // public String consultarEditorial(String edit)
    // {
    //     String datos="";
    //     boolean encontrado=false;
    //
    //     if(primero == null)
    //         datos = "Lista vacia...";
    //     else
    //     {
    //         actual = primero;
    //
    //         while(actual != null)
    //         {
    //             if(edit.equals(actual.getEditorial()))
    //             {
    //                 datos = datos + actual.toString() + "\n";
    //                 encontrado = true;
    //             }
    //
    //             actual = actual.getNext();
    //         }
    //
    //         if(!encontrado)
    //             datos = "No se localizo la Editorial: "+edit;
    //     }
    //
    //     return datos;
    // }

    public String consultarEditorial(String edit)
    {
        String datos="";
        boolean encontrado=false;

        if(primero == null)
            datos = "Lista vacia...";
        else
        {
            actual = ultimo;

            while(actual != null)
            {
                if(edit.equals(actual.getEditorial()))
                {
                    datos = datos + actual.toString() + "\n";
                    encontrado = true;
                }

                actual = actual.getPrev();
            }

            if(!encontrado)
                datos = "No se localizo la Editorial: "+edit;
        }

        return datos;
    }

    public String eliminarNodo(String nombre){
      LibroDP nodo1, nodo2;
      boolean encontrado = false;
      String respuesta = "";

      if(primero == null){
        respuesta = "VACIO";
      } else {

        actual = primero;

        while(actual != null && !encontrado){
          if (actual.getTitulo().equals(nombre)){
            encontrado = true;

            nodo1 = actual.getNext();
            nodo2 = actual.getPrev();

            if(nodo1 == null){
              nodo2.setNext(null);
              ultimo = nodo2;
            } else if(nodo2 == null){
              nodo1.setPrev(null);
              primero = nodo1;
            } else if(nodo1 == null && nodo2 == null){
              primero = null;
              ultimo = null;
            } else{
              nodo1.setPrev(nodo2);
              nodo2.setNext(nodo1);
            }

            respuesta = "Nodo Eliminado";
          }

          actual = actual.getNext();
        }
      }

      if(!encontrado) respuesta = "NOT_FOUND";

      return respuesta;
    }
}
