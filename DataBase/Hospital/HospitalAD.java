import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.StringTokenizer;

public class HospitalAD {

  private Connection conexion;
	private Statement statement;

  private PacienteDP paciente;
  private DoctorDP doctor;
  private AnalisisDP analisis;
  private AsignarDP asignar;

  public HospitalAD(){
    try{

      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conexion = DriverManager.getConnection("jdbc:mysql://localhost/hospital?user=root&password=Ocma_08Jvaa");

      System.out.println("Conexion Exitosa a la DB ...");

    } catch(ClassNotFoundException cnfe){
      System.out.println("Error1 al conectar: " + cnfe);
    } catch (InstantiationException ie){
      System.out.println("Error2 al conectar: " + ie);
    } catch (IllegalAccessException iae){
    System.out.println("Error3 al conectar: " + iae);
    } catch (SQLException sqle){
    System.out.println("Error4 al conectar: " + sqle);
    }
  }

  public String asignarDoctor(String datos){
    String status = "";
    String strInsert = "";

    asignar = new AsignarDP(datos);
    strInsert = "INSERT INTO Atiende VALUES(" + asignar.toStringSQL() + ")";


  try {

      System.out.println(strInsert);

      //Creamos la conexion
      statement =conexion.createStatement();

      //Realizamos el update
      statement.executeUpdate(strInsert);

      //Cerramos la conexion
      statement.close();

      System.out.println(asignar.toStringSQL());
      status = "Doctor Asignado Correctamente";

    } catch(SQLException ioe) {
      status = "Error: "+ ioe;
    }

    return status;

  }

  public String capturarPaciente(String datos){
    String status = "";
    String strInsert = "";

    paciente = new PacienteDP(datos);
    strInsert = "INSERT INTO Paciente VALUES(" + paciente.toStringSQL() + ")";


  try {

      System.out.println(strInsert);

      //Creamos la conexion
      statement =conexion.createStatement();

      //Realizamos el update
      statement.executeUpdate(strInsert);

      //Cerramos la conexion
      statement.close();

      System.out.println(paciente.toStringSQL());
      status = "Captura exitosa de Paciente";

    } catch(SQLException ioe) {
      status = "Error: "+ ioe;
    }

    return status;

  }


  public String capturarDoctor(String datos){
    String status = "";
    String strInsert = "";

    doctor = new DoctorDP(datos);
    strInsert = "INSERT INTO Doctor VALUES(" + doctor.toStringSQL() + ")";


  try {

      System.out.println(strInsert);

      //Creamos la conexion
      statement =conexion.createStatement();

      //Realizamos el update
      statement.executeUpdate(strInsert);

      //Cerramos la conexion
      statement.close();

      System.out.println(doctor.toStringSQL());
      status = "Captura exitosa de Doctor";

    } catch(SQLException ioe) {
      status = "Error: "+ ioe;
    }

    return status;

  }

  public String capturarAnalisis(String datos){
    String status = "";
    String strInsert = "";

    analisis = new AnalisisDP(datos);
    strInsert = "INSERT INTO Analisis VALUES(" + analisis.toStringSQL() + ")";


  try {

      System.out.println(strInsert);

      //Creamos la conexion
      statement =conexion.createStatement();

      //Realizamos el update
      statement.executeUpdate(strInsert);

      //Cerramos la conexion
      statement.close();

      System.out.println(analisis.toStringSQL());
      status = "Captura exitosa de Doctor";

    } catch(SQLException ioe) {
      status = "Error: "+ ioe;
    }

    return status;

  }

  public String consultarPacientes(){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM Paciente";

		paciente = new PacienteDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);

			while(tr.next()){
				paciente.setClave(tr.getString("Clave"));
				paciente.setNombre(tr.getString("Nombre"));
				paciente.setDireccion(tr.getString("Direccion"));
				paciente.setTelefono(Integer.parseInt(tr.getString("Telefono")));

				datos = datos + paciente.toString() + "\n";
			}

			//Cerramos la Conexion
			statement.close();
			System.out.println(strQuery);
		} catch(SQLException ioe) {
			System.out.println(ioe);
		}

		System.out.println(resultado);
		return datos;
	}

  public String consultarAtiende(){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM Atiende";

		asignar = new AsignarDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);

			while(tr.next()){
				asignar.setDoctor(tr.getString("Dclave"));
				asignar.setPaciente(tr.getString("Pclave"));
				asignar.setFecha(tr.getString("Fecha"));
				asignar.setDiagnostico(tr.getString("Diagnostico"));
        asignar.setTratamiento(tr.getString("Tratamiento"));

				datos = datos + asignar.toString() + "\n";
			}

			//Cerramos la Conexion
			statement.close();
			System.out.println(strQuery);
		} catch(SQLException ioe) {
			System.out.println(ioe);
		}

		System.out.println(resultado);
		return datos;
	}

  public String consultarDoctores(){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM Doctor";

		doctor = new DoctorDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);

			while(tr.next()){
				doctor.setClave(tr.getString("Clave"));
				doctor.setNombre(tr.getString("Nombre"));
				doctor.setDireccion(tr.getString("Direccion"));
        doctor.setEspecialidad(tr.getString("Especialidad"));
				doctor.setTelefono(Integer.parseInt(tr.getString("Telefono")));

				datos = datos + doctor.toString() + "\n";
			}

			//Cerramos la Conexion
			statement.close();
			System.out.println(strQuery);
		} catch(SQLException ioe) {
			System.out.println(ioe);
		}

		System.out.println(resultado);
		return datos;
	}

  public String consultarAnalisis(){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM Analisis";

		analisis = new AnalisisDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);

			while(tr.next()){
				analisis.setPclave(tr.getString("Pclave"));
				analisis.setTipo(tr.getString("Tipo"));
				analisis.setFechaAplicacion(tr.getString("Fecha_aplic"));
        analisis.setFechaEntrega(tr.getString("Fecha_Entrega"));
				analisis.setDescripcion(tr.getString("Descripcion"));
        analisis.setDiagnostico(tr.getString("Diagnostico"));

				datos = datos + analisis.toString() + "\n";
			}

			//Cerramos la Conexion
			statement.close();
			System.out.println(strQuery);
		} catch(SQLException ioe) {
			System.out.println(ioe);
		}

		System.out.println(resultado);
		return datos;
	}

  public String consultarClave(String clave){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM Paciente WHERE Clave = '" + clave + "'";

		paciente = new PacienteDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);

			while(tr.next()){
				paciente.setClave(tr.getString("Clave"));
				paciente.setNombre(tr.getString("Nombre"));
				paciente.setDireccion(tr.getString("Direccion"));
				paciente.setTelefono(Integer.parseInt(tr.getString("Telefono")));

				datos = datos + paciente.toString() + "\n";
			}

			//Cerramos la Conexion
			statement.close();
			System.out.println(strQuery);
		} catch(SQLException ioe) {
			System.out.println(ioe);
		}

		System.out.println(resultado);
    if(datos.equals("")) return "NOT_FOUND";
		return datos;
	}

  public String consultarClaveDoctor(String clave){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM Doctor WHERE Clave = '" + clave + "'";

		doctor = new DoctorDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);

			while(tr.next()){
        doctor.setClave(tr.getString("Clave"));
				doctor.setNombre(tr.getString("Nombre"));
				doctor.setDireccion(tr.getString("Direccion"));
        doctor.setEspecialidad(tr.getString("Especialidad"));
				doctor.setTelefono(Integer.parseInt(tr.getString("Telefono")));

				datos = datos + doctor.toString() + "\n";
			}

			//Cerramos la Conexion
			statement.close();
			System.out.println(strQuery);
		} catch(SQLException ioe) {
			System.out.println(ioe);
		}

		System.out.println(resultado);
    if(datos.equals("")) return "NOT_FOUND";
		return datos;
	}

  public String consultarClaveAnalisis(String clave){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM Analisis WHERE Pclave = '" + clave + "'";

		analisis = new AnalisisDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);

			while(tr.next()){
        analisis.setPclave(tr.getString("Pclave"));
        analisis.setTipo(tr.getString("Tipo"));
        analisis.setFechaAplicacion(tr.getString("Fecha_aplic"));
        analisis.setFechaEntrega(tr.getString("Fecha_Entrega"));
        analisis.setDescripcion(tr.getString("Descripcion"));
        analisis.setDiagnostico(tr.getString("Diagnostico"));

        datos = datos + analisis.toString() + "\n";
			}

			//Cerramos la Conexion
			statement.close();
			System.out.println(strQuery);
		} catch(SQLException ioe) {
			System.out.println(ioe);
		}

		System.out.println(resultado);
    if(datos.equals("")) return "NOT_FOUND";
		return datos;
	}

  public String consultarClaves(String doctor, String paciente){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM Atiende WHERE Dclave = '" + doctor + "' AND Pclave = + '"+paciente+"'";

		asignar = new AsignarDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);

			while(tr.next()){
				asignar.setDoctor(tr.getString("Dclave"));
				asignar.setPaciente(tr.getString("Pclave"));
				asignar.setFecha(tr.getString("Fecha"));
				asignar.setDiagnostico(tr.getString("Diagnostico"));
        asignar.setTratamiento(tr.getString("Tratamiento"));

				datos = datos + asignar.toString() + "\n";
			}

			//Cerramos la Conexion
			statement.close();
			System.out.println(strQuery);
		} catch(SQLException ioe) {
			System.out.println(ioe);
		}

		System.out.println(resultado);
    if(datos.equals("")) return "NOT_FOUND";
		return datos;
	}

  public String actualizarPaciente(String datos){
    String clave, nombre, direccion;
    int telefono;
    StringTokenizer st = new StringTokenizer(datos, "_");
    String strQuery = "";
    String status = "";

    clave = st.nextToken();
    nombre = st.nextToken();
    direccion = st.nextToken();
    telefono = Integer.parseInt(st.nextToken());

    if(!clave.equals(paciente.getClave()))
      strQuery = "UPDATE Paciente SET Clave = '"+clave+"' WHERE Clave = '"+paciente.getClave()+"'";

    if(!nombre.equals(paciente.getNombre()))
      strQuery = "UPDATE Paciente SET Nombre = '"+nombre+"' WHERE Clave = '"+paciente.getClave()+"'";

    if(!direccion.equals(paciente.getDireccion()))
      strQuery = "UPDATE Paciente SET Direccion = '"+direccion+"' WHERE Clave = '"+paciente.getClave()+"'";

    if(paciente.getTelefono() != telefono)
      strQuery = "UPDATE Paciente SET Telefono = '"+telefono+"' WHERE Clave = '"+paciente.getClave()+"'";

    try {

        System.out.println(strQuery);

        //Creamos la conexion
        statement =conexion.createStatement();

        //Realizamos el update
        statement.executeUpdate(strQuery);

        //Cerramos la conexion
        statement.close();

        System.out.println(paciente.toStringSQL());
        status = "Datos Actualizados";

      } catch(SQLException ioe) {
        status = "Error: "+ ioe;
      }

      return status;
  }

  public String actualizarDoctor(String datos){
    String clave, nombre, direccion, especialidad;
    int telefono;
    StringTokenizer st = new StringTokenizer(datos, "_");
    String strQuery = "";
    String status = "";

    clave = st.nextToken();
    nombre = st.nextToken();
    direccion = st.nextToken();
    especialidad = st.nextToken();
    telefono = Integer.parseInt(st.nextToken());

    if(!clave.equals(doctor.getClave()))
      strQuery = "UPDATE Doctor SET Clave = '"+clave+"' WHERE Clave = '"+doctor.getClave()+"'";

    if(!nombre.equals(doctor.getNombre()))
      strQuery = "UPDATE Doctor SET Nombre = '"+nombre+"' WHERE Clave = '"+doctor.getClave()+"'";

    if(!direccion.equals(doctor.getDireccion()))
      strQuery = "UPDATE Doctor SET Direccion = '"+direccion+"' WHERE Clave = '"+doctor.getClave()+"'";

    if(!especialidad.equals(doctor.getEspecialidad()))
      strQuery = "UPDATE Doctor SET Especialidad = '"+especialidad+"' WHERE Clave = '"+doctor.getClave()+"'";

    if(doctor.getTelefono() != telefono)
      strQuery = "UPDATE Doctor SET Telefono = '"+telefono+"' WHERE Clave = '"+doctor.getClave()+"'";

    try {

        System.out.println(strQuery);

        //Creamos la conexion
        statement =conexion.createStatement();

        //Realizamos el update
        statement.executeUpdate(strQuery);

        //Cerramos la conexion
        statement.close();

        System.out.println(doctor.toStringSQL());
        status = "Datos Actualizados";

      } catch(SQLException ioe) {
        status = "Error: "+ ioe;
      }

      return status;
  }

  public String actualizarAnalisis(String datos){
    String clave, tipo, fecha_entrega, fecha_aplicacion, descripcion, diagnostico;
    int telefono;
    StringTokenizer st = new StringTokenizer(datos, "_");
    String strQuery = "";
    String status = "";

    clave = st.nextToken();
    tipo = st.nextToken();
    fecha_aplicacion = st.nextToken();
    fecha_entrega = st.nextToken();
    descripcion = st.nextToken();
    diagnostico = st.nextToken();

    if(!clave.equals(analisis.getPclave()))
      strQuery = "UPDATE Analisis SET Pclave = '"+clave+"' WHERE Pclave = '"+analisis.getPclave()+"'";

    if(!tipo.equals(analisis.getTipo()))
      strQuery = "UPDATE Analisis SET Tipo = '"+tipo+"' WHERE Pclave = '"+analisis.getPclave()+"'";

    if(!fecha_aplicacion.equals(analisis.getFechaAplicacion()))
      strQuery = "UPDATE Analisis SET Fecha_aplic = '"+fecha_aplicacion+"' WHERE Pclave = '"+analisis.getPclave()+"'";

    if(!fecha_entrega.equals(analisis.getFechaEntrega()))
      strQuery = "UPDATE Analisis SET Fecha_Entrega = '"+fecha_entrega+"' WHERE Pclave = '"+analisis.getPclave()+"'";

    if(!descripcion.equals(analisis.getDescripcion()))
      strQuery = "UPDATE Analisis SET Descripcion = '"+descripcion+"' WHERE Pclave = '"+analisis.getPclave()+"'";

    if(!diagnostico.equals(analisis.getDiagnostico()))
      strQuery = "UPDATE Analisis SET Diagnostico = '"+diagnostico+"' WHERE Pclave = '"+analisis.getPclave()+"'";

    try {

        System.out.println(strQuery);

        //Creamos la conexion
        statement =conexion.createStatement();

        //Realizamos el update
        statement.executeUpdate(strQuery);

        //Cerramos la conexion
        statement.close();

        System.out.println(analisis.toStringSQL());
        status = "Datos Actualizados";

      } catch(SQLException ioe) {
        status = "Error: "+ ioe;
      }

      return status;
  }

  public String joinPaciente(String dato){
    String datos = "";
    String doctor = "\n";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM Paciente INNER JOIN Atiende ON Paciente.Clave = Atiende.Pclave WHERE Clave = '"+dato+"'";

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);


			while(tr.next()){

        datos = "Clave Paciente: " + tr.getString("Clave") + "\n" +
               "Nombre: " + tr.getString("Nombre") + "\n" +
               "Diagnostico: " + tr.getString("Diagnostico") + "\n" +
               "Tratamiento: " + tr.getString("Tratamiento") + "\n" +
               "Telefono: " + tr.getString("Telefono") + "\n" +
               "Direccion: " + tr.getString("Direccion");

        doctor = doctor + "Doctor clave: " + tr.getString("Dclave") + "\n";

			}

      datos = datos + doctor;

			//Cerramos la Conexion
			statement.close();
			System.out.println(strQuery);
		} catch(SQLException ioe) {
			System.out.println(ioe);
		}

    if(datos.equals("\n")) return "No se encontro la clave";
		return datos;
  }

  public String joinDoctor(String dato){
    String datos = "";
    String doctor = "\n";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM Doctor INNER JOIN Atiende ON Doctor.Clave = Atiende.Dclave WHERE Clave = '"+dato+"'";

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);


			while(tr.next()){

        datos = "Clave Doctor: " + tr.getString("Clave") + "\n" +
               "Nombre: " + tr.getString("Nombre") + "\n" +
               "Especialidad: " + tr.getString("Especialidad") + "\n" +
               "Telefono: " + tr.getString("Telefono") + "\n" +
               "Direccion: " + tr.getString("Direccion");

        doctor = doctor + "Paciente clave: " + tr.getString("Pclave") + "\n";

			}

      datos = datos + doctor;

			//Cerramos la Conexion
			statement.close();
			System.out.println(strQuery);
		} catch(SQLException ioe) {
			System.out.println(ioe);
		}

    if(datos.equals("\n")) return "No se encontro la clave";
		return datos;
  }
}
