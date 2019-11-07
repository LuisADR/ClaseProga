import java.util.StringTokenizer;

public class AlumnoDP{

  private String matricula, nombre, carrera;
  private int cal1, cal2, cal3;
  private float promedio;

  private AlumnoDP next;

  public AlumnoDP(String datos){
    StringTokenizer st = new StringTokenizer(datos,"_");

    this.matricula  = st.nextToken();
    this.nombre  = st.nextToken();
    this.carrera   = st.nextToken();
    this.cal1 = Integer.parseInt(st.nextToken());
    this.cal2 = Integer.parseInt(st.nextToken());
    this.cal3 = Integer.parseInt(st.nextToken());
    this.promedio = Float.parseFloat(st.nextToken());
  }

  public String getMatricula(){
    return this.matricula;
  }

  public String getNombre(){
    return this.nombre;
  }

  public String getCarrera(){
    return this.carrera;
  }

  public int getCal1(){
    return this.cal1;
  }

  public int getCal2(){
    return this.cal2;
  }

  public int getCal3(){
    return this.cal3;
  }

  public float getPromedio(){
    return this.promedio;
  }

  public AlumnoDP getNext(){
    return this.next;
  }

  public void setMatricula(String dato){
    this.matricula = dato;
  }

  public void setNombre(String dato){
    this.nombre = dato;
  }

  public void setCarrera(String dato){
    this.carrera = dato;
  }

  public void setCal1(int dato){
    this.cal1 = dato;
  }

  public void setCal2(int dato){
    this.cal2 = dato;
  }

  public void setCal3(int dato){
    this.cal3 = dato;
  }

  public void setPromedio(float dato){
    this.promedio = dato;
  }

  public void setNext(AlumnoDP direccion){
    this.next = direccion;
  }

  public String toString(){
    return this.matricula + "_" + this.nombre + "_" + this.carrera + "_" + this.cal1 + "_" + this.cal2 + "_" + this.cal3 + "_" + this.promedio;
  }
}
