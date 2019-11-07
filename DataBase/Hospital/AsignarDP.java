import java.util.StringTokenizer;

public class AsignarDP{

  private String doctor, paciente, fecha, diagnostico, tratamiento;

  public AsignarDP(String datos){
    StringTokenizer st = new StringTokenizer(datos, "_");
    doctor = st.nextToken();
    paciente = st.nextToken();
    fecha = st.nextToken();
    diagnostico = st.nextToken();
    tratamiento = st.nextToken();
  }

  public AsignarDP(){
    doctor = "";
    paciente = "";
    fecha = "";
    diagnostico = "";
    tratamiento = "";
  }

  public String getDoctor(){
    return this.doctor;
  }

  public String getPaciente(){
    return this.paciente;
  }

  public String getFecha(){
    return this.fecha;
  }

  public String getDiagnostico(){
    return this.diagnostico;
  }

  public String getTratamiento(){
    return this.tratamiento;
  }

  public void setDoctor(String dato){
    this.doctor = dato;
  }

  public void setPaciente(String dato){
    this.paciente = dato;
  }

  public void setFecha(String dato){
    this.fecha = dato;
  }

  public void setDiagnostico(String dato){
    this.diagnostico = dato;
  }

  public void setTratamiento(String dato){
    this.tratamiento = dato;
  }

  public String toString(){
    return this.doctor + "_" + this.paciente + "_" + this.fecha + "_"+ this.diagnostico + "_" + this.tratamiento;
  }

  public String toStringSQL(){
    return "'" + this.doctor + "','" + this.paciente + "','" + this.fecha + "','" + this.diagnostico +"','" + this.tratamiento + "'";
  }

}
