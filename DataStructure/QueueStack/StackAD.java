public class StackAD{
  private NodoDP primero, actual, ultimo, prev;

  public String push(String nombre){
    if(primero == null){
      primero = new NodoDP(nombre);
      ultimo = primero;

      primero.setNext(null);
      return "Primer Nodo";
    } else {
      prev = ultimo;
      actual = new NodoDP(nombre);
      ultimo.setNext(actual);
      ultimo = actual;
      return "Otro Nodo";
    }
  }

  public String push2(){
    if(primero == null){
      ultimo = new NodoDP(nombre);
  
      ultimo.setNext(null);
      return "Primer Nodo";
    } else {
      actual = new NodoDP(nombre);
      actual.setNext(ultimo);
      ultimo = actual;
    }
  }

  public String consultar(){
    String datos = "";

    actual = primero;
    while(actual != null){
      datos = datos + actual.getNombre() + "\n";
      prev = actual;
      actual = actual.getNext();
    }

    if(primero == null){
      datos = "No hay cola";
    }

    return datos;
  }

  public String pop(){
    if(primero == null) return "No hay trabajo en espera";

    else if(primero != ultimo){
        actual = primero;
        prev = null;
        while(actual != ultimo){
          if(actual.getNext() == ultimo){
            actual.setNext(null);
            ultimo = actual;
            break;
          }

          actual = actual.getNext();
        }
      System.out.println(actual.getNombre());
      return "Nodo Eliminado";
    }

    else {
      primero = null;
      ultimo = null;
      return "Trabajo terminado";
    }
  }

  public String pop2(){
    if(primero == null) return "No hay trabajo en espera";

    else if(primero != ultimo){
      ultimo = prev;
      prev.setNext(null);

      return "Nodo Eliminado";
    }

    else {
      primero = null;
      ultimo = null;
      return "Trabajo terminado";
    }
  }
}
