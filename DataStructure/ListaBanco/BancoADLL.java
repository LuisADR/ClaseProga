public class BancoADLL{

  //Atributos
  private ClienteDP primero, actual, ultimo;

  public String capturar(String datos){

    if(primero == null){
      primero = new ClienteDP(datos);
      ultimo = primero;
      ultimo.setNext(null);
    } else {
      actual = new ClienteDP(datos);
      ultimo.setNext(actual);
      ultimo = actual;
      ultimo.setNext(null);
    }
    return "Nuevo nodo creado: " + datos;
  }

  public String consultar(){
    String datos = "";

    if (primero == null) datos = "Lista Vacia";
    else {
      actual = primero;
      while (actual != null){
        datos = datos + actual.toString();
        actual  = actual.getNext();
      }
    }

    return datos;
  }

  public String consultarTipo (String tcta){
    String datos = "";
    return datos;

    if (primero == null) datos = "Lista Vacia";
    else {
      actual = primero;
      while (actual != null){
        datos = datos + actual.toString();
        actual  = actual.getNext();
      }
    }
  }
}
