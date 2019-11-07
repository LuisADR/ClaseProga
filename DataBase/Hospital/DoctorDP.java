import java.util.StringTokenizer;

public class DoctorDP{

  private String clave, nombre, direccion, especialidad;
  private int telefono;

  public DoctorDP(String datos){
    StringTokenizer st = new StringTokenizer(datos, "_");
    clave = st.nextToken();
    nombre = st.nextToken();
    direccion = st.nextToken();
    especialidad = st.nextToken();
    telefono = Integer.parseInt(st.nextToken());
  }

  public DoctorDP(){
		clave = "";
		nombre = "";
		direccion = "";
    especialidad = "";
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

  public String getEspecialidad(){
    return this.especialidad;
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

  public void setEspecialidad(String especialidad){
    this.especialidad = especialidad;
  }

  public void setTelefono(int telefono){
    this.telefono = telefono;
  }

  public String toString(){
    return this.clave + "_" + this.nombre + "_" + this.direccion + "_"+ this.especialidad + "_" + this.telefono;
  }

  public String toStringSQL(){
    return "'" + this.clave + "','" + this.nombre + "','" + this.direccion + "','" + this.especialidad +"'," + this.telefono;
  }
}
