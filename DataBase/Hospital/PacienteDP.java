import java.util.StringTokenizer;

public class PacienteDP {

  private String clave, nombre, direccion;
  private int telefono;

  public PacienteDP(String datos){
    StringTokenizer st = new StringTokenizer(datos, "_");
		clave = st.nextToken();
		nombre = st.nextToken();
		direccion = st.nextToken();
		telefono = Integer.parseInt(st.nextToken());
  }

  public PacienteDP(){
		clave = "";
		nombre = "";
		direccion = "";
		telefono = 0;
  }

  public String getNombre(){
    return this.nombre;
  }

  public String getClave(){
    return this.clave;
  }

  public String getDireccion(){
    return this.direccion;
  }

  public int getTelefono(){
    return this.telefono;
  }

  public void setNombre(String nombre){
    this.nombre = nombre;
  }

  public void setClave(String clave){
    this.clave = clave;
  }

  public void setDireccion(String direccion){
    this.direccion = direccion;
  }

  public void setTelefono(int telefono){
    this.telefono = telefono;
  }

  public String toString(){
    return this.clave + "_" + this.nombre + "_" + this.direccion + "_" + this.telefono;
  }

  public String toStringSQL(){
    return "'" + this.clave + "','" + this.nombre + "','" + this.direccion + "'," + this.telefono;
  }
}
