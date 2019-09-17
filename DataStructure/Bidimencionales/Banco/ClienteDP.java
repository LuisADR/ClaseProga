import java.util.StringTokenizer;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.lang.Math;


public class ClienteDP {
	// Atributos, variables de instancia o de clase
	private String nocta, nombre, tipo, dia, hora;
	private int saldo;

	private DateFormat dateFormat;
	private Date date;

	// Constructores
	public ClienteDP(){
			nocta = "";
			nombre = "";
			tipo = "";
			saldo = 0;
			dia = "";
			hora = "";

			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
	}

	public ClienteDP(String datos){
		StringTokenizer st = new StringTokenizer(datos, "_");
		nocta = st.nextToken();
		nombre = st.nextToken();;
		tipo = st.nextToken();;
		saldo = Integer.parseInt(st.nextToken());
		dia = st.nextToken();
		hora = st.nextToken();

		dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
	}


	// Metodos: Accesos (geter's) y mulators(seter's)
	public String getNocta(){
		return this.nocta;
	}

	public String getNombre(){
		return this.nombre;
	}

	public String getTipo(){
		return this.tipo;
	}

	public int getSaldo(){
		return this.saldo;
	}

	public String getDia(){
		return this.dia;
	}

	public void setNocta(String cta){
		this.nocta = cta;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public void setTipo(String tipo){
		this.tipo = tipo;
	}

	public void setSaldo(int saldo){
		this.saldo = saldo;
	}

	public String toString(){
		return this.nocta + "*" + this.nombre + "*" + this.tipo + "*" + this.saldo + "*" +
					 this.dia + "*" + this.hora;
	}

	public String toStringText(){
		return this.nocta + "_" + this.nombre + "_" + this.tipo + "_" + this.saldo + "_" +
			 		 this.dia + "_" + this.hora;
	}

	public String toStringOperacion(int cantidad){
		date = new Date();

		return this.nocta + "_" + this.nombre + "_" + (this.saldo + cantidad) + "_" + Math.abs(cantidad) + "_"  + this.saldo +
	 			 	 "_" + dateFormat.format(date);
	}
}
