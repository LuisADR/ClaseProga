import java.util.StringTokenizer;

public class ClienteDP {
	// Atributos, variables de instancia o de clase
	private String nocta, nombre, tipo;
	private float saldo;

	// Constructores
	public ClienteDP(){
			nocta = "";
			nombre = "";
			tipo = "";
			saldo = 0;
	}

	public ClienteDP(String datos){
		StringTokenizer st = new StringTokenizer(datos, "_");
		nocta = st.nextToken();
		nombre = st.nextToken();;
		tipo = st.nextToken();;
		saldo = Float.parseFloat(st.nextToken());
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

	public String toString(){
		return this.nocta + "*" + this.nombre + "*" + this.tipo + "*" + String.format("%.2f", this.saldo);
	}

	public String toStringSQL(){
		return "'" + this.nocta + "', '" + this.nombre + "', '" + this.tipo + "', " + this.saldo ;
	}
}
