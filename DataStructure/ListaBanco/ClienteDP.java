import java.util.StringTokenizer;

public class ClienteDP
{
    // Atributos: Varaibles de Instancia o de Clase
    private String nocta, nombre, tipo;
    private int saldo;

    private ClienteDP next;

    // Constructores
    public ClienteDP()
    {
        nocta  = "";
        nombre = "";
        tipo   = "";
        saldo  = 0;
    }

    public ClienteDP(String datos)
    {
        StringTokenizer st = new StringTokenizer(datos,"_");

        nocta  = st.nextToken();
        nombre = st.nextToken();
        tipo   = st.nextToken();
        saldo  = Integer.parseInt(st.nextToken());
    }

    // Metodos: Accesors (geter's) y Mutators (seter's)
    // Accesors
    public String getNocta()
    {
        return this.nocta;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public String getTipo()
    {
        return this.tipo;
    }

    public int getSaldo()
    {
        return this.saldo;
    }

    public ClienteDP getNext(){
      return this.next;
    }

    // Mutators
    public void setNocta(String cta)
    {
        this.nocta = cta;
    }

    public void setNombre(String name)
    {
        this.nombre = name;
    }

    public void setTipo(String tcta)
    {
        this.tipo = tcta;
    }

    public void setSaldo(int cantidad)
    {
        this.saldo = cantidad;
    }

    public void setNext(ClienteDP nodo){
        this.next = nodo;
    }

    public String toString()
    {
        return this.nocta+"_"+this.nombre+"_"+this.tipo+"_"+this.saldo;
    }

    public String toStringOperacion()
    {
        return this.nocta+"_"+this.tipo+"_"+this.saldo;
    }
}
