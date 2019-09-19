import java.util.StringTokenizer;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.lang.Math;

public class ClienteDP {
	// Atributos, variables de instancia o de clase
	private String nocta, nombre, tipo, fecha, hora;
	private float saldo, operacion;

	private DateFormat dateFormat, horaFormat;
	private Date date;

	// Constructores
	public ClienteDP(){
			nocta = "";
			nombre = "";
			tipo = "";
			saldo = 0;
			fecha= "";
			hora = "";
			operacion = 0;

			dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			horaFormat = new SimpleDateFormat("HH:mm:ss");
	}

	public ClienteDP(String datos){
		StringTokenizer st = new StringTokenizer(datos, "_");
		nocta = st.nextToken();
		nombre = st.nextToken();
		tipo = st.nextToken();
		saldo = Float.parseFloat(st.nextToken());
		fecha = st.nextToken();
		hora = st.nextToken();
		operacion = 0;

		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		horaFormat = new SimpleDateFormat("HH:mm:ss");
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

	public float getSaldo(){
		return this.saldo;
	}

	public String getFecha(){
		return this.fecha;
	}

	public String getHora(){
		return this.hora;
	}

	public float getOperacion(){
		return this.operacion;
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

	public void setSaldo(float saldo){
		this.saldo = saldo;
	}

	public void setFecha(String fecha){
		this.fecha = fecha;
	}

	public void setHora(String hora){
		this.hora = hora;
	}

	public void setOperacion(float saldo){
		this.operacion = saldo;
	}

	public String toString(){
		return this.nocta + "*" + this.nombre + "*" + this.tipo + "*" + String.format("%.2f", this.saldo) +
					 "*" + this.fecha + "*" + this.hora;
	}

	public String toStringSQL(){
		return "'" + this.nocta + "', '" + this.nombre + "', '" + this.tipo + "', '" + String.format("%.2f", this.saldo) + "', '" +
					 this.fecha + "', '" + this.hora + "'";
	}

	public String toStringOperacion(){
		date = new Date();

		return "'" + this.nocta + "', '" + this.nombre + "', '" + String.format("%.2f", this.saldo - this.operacion) + "', '" +
		String.format("%.2f", Math.abs(this.operacion)) + "', '" + String.format("%.2f",this.saldo) + "', '" + 
		dateFormat.format(date) + "', '" + horaFormat.format(date) + "'";
	}
}
