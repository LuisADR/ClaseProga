import java.util.StringTokenizer;

public class AutoDP{
  private String clave, marca, tipo;
  private int precio;

  public AutoDP(String datos){
    StringTokenizer st = new StringTokenizer(datos, "_");
    clave = st.nextToken();
    marca = st.nextToken();
    tipo = st.nextToken();
    precio = Integer.parseInt(st.nextToken());
  }

  public AutoDP(){

    clave = "";
    marca = "";
    tipo = "";
    precio = 0;
  }

  public String getClave(){
    return this.clave;
  }

  public String getMarca(){
    return this.marca;
  }

  public String getTipo(){
    return this.tipo;
  }

  public int getPrecio(){
    return this.precio;
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

  public String toString(){
    return this.clave + "*" + this.marca + "*" + this.tipo + "*" + this.precio + "*";
  }
}
