import java.util.StringTokenizer;

public class AutoDP{
  private String clave;
  private String marca;
  private String tipo;
  private float precio;

  public AutoDP(){
    clave = "";
    marca = "";
    tipo = "";
    precio = 0;
  }

  public AutoDP(String datos){
    StringTokenizer st = new StringTokenizer(datos, "_");
		clave = st.nextToken();
		marca = st.nextToken();
		tipo = st.nextToken();
		precio = Float.parseFloat(st.nextToken());
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

  public String toString(){
		return this.clave + "_" + this.marca + "_" + this.tipo + "_" + this.precio;
	}

  public String toStringSQL(){
		return "'" + this.clave + "','" + this.marca + "','" + this.tipo + "'," + this.precio;
	}

}
