import java.util.StringTokenizer;

public class ClienteDP {
	// Atributos, variables de instancia o de clase
	private String nocta, nombre, tipo;
	private int saldo;

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
		saldo = Integer.parseInt(st.nextToken());
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

	public String toString(int saldo){
		return this.nocta + "*" + this.nombre + "*" + this.tipo + "*" + this.saldo;
	}
}
