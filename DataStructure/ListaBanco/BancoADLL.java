public class BancoADLL{

  //Atributos
  private ClienteDP primero, actual, ultimo;
  private DepositoDP primeroDeposito, ultimoDeposito, actualDeposito;
  private RetiroDP primeroRetiro, ultimoRetiro, actualRetiro;

  //Constructores
  public BancoADLL(){

  }

  public void datosArchivoListaCliente(){
    try{
      BufferedReader archivoIn= new BufferedReader(new FileReader("Clientes.txt"));

      while(archivoIn.ready()){
        capturar(archivoIn.readLine());
      }
      archivoIn.close();
    }

    catch(FileNotFoundException fnfe){
      System.out.println("Error: "+fnfe);
    }

    catch(IOException ioe){
      System.out.println("Error: "+ioe);
    }
  }

  public void datosArchivoListaDeposito(){
    try{
      BufferedReader archivoIn= new BufferedReader(new FileReader("Depositos.txt"));

      while(archivoIn.ready()){
        crearNodoDeposito(archivoIn.readLine());
      }
      archivoIn.close();
    }

    catch(FileNotFoundException fnfe){
      System.out.println("Error: "+fnfe);
    }

    catch(IOException ioe){
      System.out.println("Error: "+ioe);
    }
  }

  public void datosArchivoListaRetiro(){
    try{
      BufferedReader archivoIn= new BufferedReader(new FileReader("Retiros.txt"));

      while(archivoIn.ready()){
        capturar(archivoIn.readLine());
      }
      archivoIn.close();
    }

    catch(FileNotFoundException fnfe){
      System.out.println("Error: "+fnfe);
    }

    catch(IOException ioe){
      System.out.println("Error: "+ioe);
    }
  }
  public String capturar(String datos){

    if(primero == null){
      primero = new ClienteDP(datos);
      ultimo = primero;
      ultimo.setNext(null);
    } else {
      actual = new ClienteDP(datos); //1133
      ultimo.setNext(actual//1133);
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

  public String consultarNocta(String ncta){
    String datos="";
    Boolean encontrado=false;

    if(primero==null){
      datos="LISTA_VACIA";
    }

    else{
      actual=primero;
      while(actual!=null && !encontrado){
        if(actual.getNocta().equals(ncta)){
          datos=datos+actual.toString();
          posCliente=actual;
          encontrado=true;
        }
        actual=actual.getNext();
      }
      if(encontrado==false){
        datos="NOT_FOUND";
      }
    }
    System.out.println(posCliente);
    return datos;
  }

  private String crearNodoDeposito(String datos){
    String respuesta = "";

    if(primeroDeposito == null){
      primeroDeposito = new DepositoDP(datos);
      ultimoDeposito = primeroDeposito;
      ultimoDeposito.setNext(null);
    } else {
      actualDeposito = new DepositoDP(datos);
      ultimoDeposito.setNext(actualDeposito);
      ultimoDeposito = actualDeposito;
      ultimoDeposito.setNext(null);
    }
  }

  private String crearNodoRetiro(String datos){
    String respuesta = "";

    if(primeroRetiro == null){
      primeroRetiro = new RetiroDP(datos);
      ultimoRetiro = primeroRetiro;
      ultimoRetiro.setNext(null);
    } else {
      actualRetiro = new DepositoDP(datos);
      ultimoRetiro.setNext(actualRetiro);
      ultimoRetiro = actualRetiro;
      ultimoRetiro.setNext(null);
    }
  }

  public String consultarDepositos(){
    String datos = "";

    if(primeroDeposito = null){
      datos = "Lista vacia de depositos";
    }

    else {
      actualDeposito = primeroDeposito;

      while(actualDeposito != null){
        datos = datos + actualDeposito.toString() + "\n";
        actualDeposito = actualDeposito.getNext();
      }
    }

    return datos;
  }
}
