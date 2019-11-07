import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class AutosADjdbc{

  private Connection conexion;
	private Statement statement;

  private AutoDP autodp;
  private VentaDP ventadp;

  public AutosADjdbc(){
    try{

      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conexion = DriverManager.getConnection("jdbc:mysql://localhost/chrysler?user=root&password=Ocma_08Jvaa");

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

  public String consultarClientes(){
    String datos = "";
    String strQuery = "";
    ResultSet tr;
    String resultado ="";

    strQuery = "SELECT * FROM auto";

    autodp = new AutoDP();

    try {
      //Abrir la DB
      statement = conexion.createStatement();

      tr = statement.executeQuery(strQuery);
      while(tr.next()){
        autodp.setClave(tr.getString("clave"));
				autodp.setMarca(tr.getString("marca"));
				autodp.setTipo(tr.getString("tipo"));
				autodp.setPrecio(Float.parseFloat(tr.getString("precio")));

        datos = datos + autodp.toString() + "\n";
      }

      //Cerramos la Conexion
      statement.close();
      System.out.println(strQuery);
    } catch(SQLException ioe) {
      datos = "Error: "+ ioe;
    }

    System.out.println(resultado);
		return datos;
  }

  public String consultarClave(String clave){
		String datos = "NULL";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM auto WHERE clave = " + clave;

		autodp = new AutoDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);

			while(tr.next()){
				autodp.setClave(tr.getString("clave"));
				autodp.setMarca(tr.getString("marca"));
				autodp.setTipo(tr.getString("tipo"));
				autodp.setPrecio(Float.parseFloat(tr.getString("precio")));

				datos =  autodp.toString() + "\n";
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

  public String capturar(String datos){
		String status = "";
		String strInsert = "";

    autodp = new AutoDP(datos);
    strInsert = "INSERT INTO auto VALUES("+ autodp.toStringSQL() +")";

		try {

			System.out.println(strInsert);

			//Creamos la conexion
			statement =conexion.createStatement();

			//Realizamos el update
			statement.executeUpdate(strInsert);

			//Cerramos la conexion
			statement.close();

			System.out.println(autodp.toStringSQL());
			status = "Captura exitosa";

		} catch(SQLException ioe) {
			status = "Error: "+ ioe;
		}

		return status;
	}
  public String consultarMarca(String marca){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM auto WHERE marca = '" + marca + "'";

		autodp = new AutoDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);

			while(tr.next()){
				autodp.setClave(tr.getString("clave"));
				autodp.setMarca(tr.getString("marca"));
				autodp.setTipo(tr.getString("tipo"));
				autodp.setPrecio(Float.parseFloat(tr.getString("precio")));

				datos = datos + autodp.toString() + "\n";
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

  public String consultar(){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM auto";

		autodp = new AutoDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);

			while(tr.next()){
				autodp.setClave(tr.getString("clave"));
				autodp.setMarca(tr.getString("marca"));
				autodp.setTipo(tr.getString("tipo"));
				autodp.setPrecio(Float.parseFloat(tr.getString("precio")));

				datos = datos + autodp.toString() + "\n";
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

  public String consultarVentas(){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM venta";

		ventadp = new VentaDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);

			while(tr.next()){
				ventadp.setClave(tr.getString("clave"));
				ventadp.setMarca(tr.getString("marca"));
				ventadp.setTipo(tr.getString("tipo"));
				ventadp.setPrecio(Float.parseFloat(tr.getString("precio")));
        ventadp.setPlazo(Integer.parseInt(tr.getString("plazo")));
        ventadp.setInteres(Integer.parseInt(tr.getString("interes")));
        ventadp.setPrecioTotal(Float.parseFloat(tr.getString("precioTotal")));
        ventadp.setMensualidad(Float.parseFloat(tr.getString("mensualidad")));

				datos = datos + ventadp.toString() + "\n";
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

  public String cotizar(){
		String datos = "";
		String strQuery = "";
		ResultSet tr;
		String resultado ="";

		strQuery = "SELECT * FROM auto WHERE clave = '" + autodp.getClave() + "'";
		System.out.println(strQuery);
		ventadp = new VentaDP();

		try {
			//Abrir la DB
			statement = conexion.createStatement();

			tr = statement.executeQuery(strQuery);
			while(tr.next()){
				ventadp.setClave(tr.getString("clave"));
				ventadp.setMarca(tr.getString("marca"));
				ventadp.setTipo(tr.getString("tipo"));
				ventadp.setPrecio(Float.parseFloat(tr.getString("precio")));
			}

			//Cerramos la Conexion
			statement.close();

      //Preguntamos plazo
      ventadp.setPlazo(Integer.parseInt(JOptionPane.showInputDialog("Plazo a cotizar")));

      //Generamos interes, precioTotal y mensualidad
      if(ventadp.getPlazo() == 12) {
        ventadp.setInteres(10);
        ventadp.setPrecioTotal((float)1.10 * (float)ventadp.getPrecio());
        ventadp.setMensualidad(ventadp.getPrecioTotal() / 12);
      }
      if(ventadp.getPlazo() == 24) {
        ventadp.setInteres(15);
        ventadp.setPrecioTotal((float)1.15 * (float)ventadp.getPrecio());
        ventadp.setMensualidad(ventadp.getPrecioTotal() / 24);
      }
      if(ventadp.getPlazo() == 48) {
        ventadp.setInteres(20);
        ventadp.setPrecioTotal((float)1.20 * (float)ventadp.getPrecio());
        ventadp.setMensualidad(ventadp.getPrecioTotal() / 48);
      }

		} catch(SQLException ioe) {
			datos = "Error: "+ ioe;
		}

		System.out.println(resultado);
		return ventadp.toStringConsulta();
	}

  public String capturarVenta(){
		String status = "";
		String strInsert = "";
    cotizar();
    System.out.println(ventadp.toStringSQL());
   	strInsert = "INSERT INTO venta VALUES("+ ventadp.toStringSQL() +")";


		try {

			System.out.println(strInsert);

			//Creamos la conexion
			statement =conexion.createStatement();

			//Realizamos el update
			statement.executeUpdate(strInsert);

			//Cerramos la conexion
			statement.close();

			status = "Captura exitosa";

		} catch(SQLException ioe) {
			status = "Error: "+ ioe;
		}

		return status;
	}

}
