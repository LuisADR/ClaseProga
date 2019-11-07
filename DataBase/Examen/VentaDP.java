public class VentaDP{
  private String clave;
  private String marca;
  private String tipo;
  private float precio, precioTotal, mensualidad;
  private int plazo, interes;

  public VentaDP(){
    clave = "";
    marca = "";
    tipo = "";
    precio = 0;
    precioTotal = 0;
    mensualidad = 0;
    plazo = 0;
    interes = 0;
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

  public float getPrecio(){
    return precio;
  }

  public float getPrecioTotal(){
    return precioTotal;
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

  public void setPrecio(float precio){
    this.precio = precio;
  }

  public void setPrecioTotal(float precioTotal){
    this.precioTotal = precioTotal;
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
           "Precio con interes = " + this.precioTotal + "\n" +
           "Mensualidad = " + this.mensualidad;
  }

  public String toStringSQL(){
    return "'" + this.clave + "', '"
           + this.marca + "','"
           + this.tipo + "',"
           + this.precio + ","
           + this.plazo + ",'"
           + this.interes + "',"
           + this.precioTotal + ","
           + this.mensualidad;
  }

  public String toString(){
    return this.clave + "_"
           + this.marca + "_"
           + this.tipo + "_"
           + this.precio + "_"
           + this.plazo + "_"
           + this.interes + "_"
           + this.precioTotal + "_"
           + this.mensualidad;
  }
}
