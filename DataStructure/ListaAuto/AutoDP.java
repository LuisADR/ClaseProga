import java.util.StringTokenizer;

public class AutoDP
{
    // Atributos
    private String clave, marca, tipo;
    private int precio;

    // Constructors
    public AutoDP()
    {
        this.clave  = "";
        this.marca  = "";
        this.tipo   = "";
        this.precio = 0;
    }

    public AutoDP(String datos)
    {
        StringTokenizer st = new StringTokenizer(datos,"_");

        this.clave  = st.nextToken();
        this.marca  = st.nextToken();
        this.tipo   = st.nextToken();
        this.precio = Integer.parseInt(st.nextToken());
    }
    
    // Accesors
    public String getClave()
    {
        return this.clave;
    }
    
    public String getMarca()
    {
        return this.marca;
    }
    
    public String getTipo()
    {
        return this.tipo;
    }
    
    public int getPrecio()
    {
        return this.precio;
    }
    
    // Mutators
    public void setClave(String cve)
    {
        this.clave = cve;
    }
    
    public void setMarca(String mca)
    {
        this.marca = mca;
    }
    
    public void setTipo(String tpo)
    {
        this.tipo = tpo;
    }
    
    public void setPrecio(int pre)
    {
        this.precio = pre;
    }
    
    public String toString()
    {
        return this.clave+"_"+this.marca+"_"+this.tipo+"_"+this.precio;
    }
}
