import java.util.StringTokenizer;

public class AnalisisDP{
  private String pClave, tipo, fecha_aplicacion, fecha_entrega, diagnostico, descripcion;

  public AnalisisDP(String datos){
    StringTokenizer st = new StringTokenizer(datos, "_");
    pClave = st.nextToken();
    tipo = st.nextToken();
    fecha_aplicacion = st.nextToken();
    fecha_entrega = st.nextToken();
    descripcion = st.nextToken();
    diagnostico = st.nextToken();
  }

  public AnalisisDP(){
    pClave = "";
    tipo = "";
    fecha_aplicacion = "";
    fecha_entrega = "";
    descripcion = "";
    diagnostico = "";
  }

  public String getPclave(){
    return this.pClave;
  }

  public String getTipo(){
    return this.tipo;
  }

  public String getFechaAplicacion(){
    return this.fecha_aplicacion;
  }

  public String getFechaEntrega(){
    return this.fecha_entrega;
  }

  public String getDescripcion(){
    return this.descripcion;
  }

  public String getDiagnostico(){
    return this.diagnostico;
  }

  public void setPclave(String dato){
    this.pClave = dato;
  }

  public void setTipo(String dato){
    this.tipo = dato;
  }

  public void setFechaAplicacion(String dato){
    this.fecha_aplicacion = dato;
  }

  public void setFechaEntrega(String dato){
    this.fecha_entrega = dato;
  }

  public void setDescripcion(String dato){
    this.descripcion = dato;
  }

  public void setDiagnostico(String dato){
    this.diagnostico = dato;
  }

  public String toString(){
    return this.pClave+"_"+this.tipo+"_"+this.fecha_aplicacion+"_"+this.fecha_entrega+"_"+this.descripcion+"_"+this.diagnostico;
  }

  public String toStringSQL(){
    return "'" + this.pClave + "','" + this.tipo + "','" + this.fecha_aplicacion + "','" + this.fecha_entrega +"','" + this.descripcion + "','" + this.diagnostico + "'";
  }
}
