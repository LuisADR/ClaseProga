import java.util.StringTokenizer;

public class VentaDP{

  private String clave, marca, tipo;
  private int precio, plazo, interes;
  private float precionInt, mensualidad;

  public VentaDP(String datos){
    StringTokenizer st = new StringTokenizer(datos, "_");
    clave = st.nextToken();
    marca = st.nextToken();
    tipo = st.nextToken();
    precio = Integer.parseInt(st.nextToken());
    plazo = Integer.parseInt(st.nextToken());
    interes = Integer.parseInt(st.nextToken());
    precionInt = Float.parseFloat(st.nextToken());
    mensualidad = Float.parseFloat(st.nextToken());
  }

  public String getClave(){
    return clave;
  }

  public String getMarca(){
    return marca;
  }

  public String getTipo(){
    return tipo;
  }

  public int getPrecio(){
    return precio;
  }

  public float getPrecioInteres(){
    return precionInt;
  }

  public float getMensualidad(){
    return mensualidad;
  }

  public int getPlazo(){
    return plazo;
  }

  public int getInteres(){
    return interes;
  }

  public void setClave(String clave){
    this.clave = clave;
  }

  public void setMarca(String marca){
    this.marca = marca;
  }

  public void setTipo(String tipo){
    this.tipo = tipo;
  }

  public void setPrecio(int precio){
    this.precio = precio;
  }

  public void setPrecioTotal(float precioTotal){
    this.precionInt = precioTotal;
  }

  public void setMensualidad(float mensualidad){
    this.mensualidad = mensualidad;
  }

  public void setPlazo(int plazo){
    this.plazo = plazo;
  }

  public void setInteres(int interes){
    this.interes = interes;
  }

  public String toStringConsulta(){
    return "Clave auto = " + this.clave + "\n" +
           "Marca = " + this.marca + "\n" +
           "Tipo = " + this.tipo + "\n" +
           "Precio = " + this.precio + "\n" +
           "Plazo = " + this.plazo + "\n" +
           "Interes = " + this.interes + "\n" +
           "Precio con interes = " + this.precionInt + "\n" +
           "Mensualidad = " + this.mensualidad;
  }

  public String toString(){
    return this.clave + "_"
           + this.marca + "_"
           + this.tipo + "_"
           + this.precio + "_"
           + this.plazo + "_"
           + this.interes + "_"
           + this.precionInt + "_"
           + this.mensualidad;
  }
}
