public class QueueAD{

    private NodoDP primero, actual, ultimo;

    public String push(String nombre){
      if(primero == null){
        primero = new NodoDP(nombre);
        ultimo = primero;

        primero.setNext(null);
        return "Primer Nodo";
      } else {
        actual = new NodoDP(nombre);
        ultimo.setNext(actual);
        ultimo = actual;
        return "Otro Nodo";
      }
    }

    public String consultar(){
      String datos = "";

      actual = primero;
      while(actual != null){
        datos = datos + actual.getNombre() + "\n";
        actual = actual.getNext();
      }

      if(primero == null){
        datos = "No hay cola";
      }

      return datos;
    }

    public String pop(){
      if(primero == null) return "No hay trabajo en espera";

      else{
        if(primero != ultimo){
          primero = primero.getNext();
          return "Nodo terminado";
        }

        else {
          primero = null;
          ultimo = null;
          return "Trabajo terminado";
        }
      }
    }
}
