import java.util.*;

public class RetiroDP
{
    private String nocta,tipo;
    private int    saldoAnterior;
    private int    cantidad;
    private int    saldoNuevo;

    private RetiroDP next;

    /*** Constructores ***/
    public RetiroDP()
    {
        this.nocta  = "";
        this.tipo   = "";
        this.saldoAnterior = 0;
        this.cantidad      = 0;
        this.saldoNuevo    = 0;
    }

    public RetiroDP(String datos)
    {
        StringTokenizer st = new StringTokenizer(datos,"_");

        this.nocta  = st.nextToken();;
        this.tipo   = st.nextToken();;
        this.saldoAnterior = Integer.parseInt(st.nextToken());;
        this.cantidad      = Integer.parseInt(st.nextToken());;
        this.saldoNuevo    = Integer.parseInt(st.nextToken());;
    }

    /*** Accesors ***/
    public String getNocta()
    {
        return this.nocta;
    }

    public String getTipo()
    {
        return this.tipo;
    }

    public int getSaldoAnterior()
    {
        return this.saldoAnterior;
    }

    public int getCantidad()
    {
        return this.cantidad;
    }

    public int getSaldoNuevo()
    {
        return this.saldoNuevo;
    }

    public RetiroDP getNext()
    {
        return this.next;
    }


    /*** Mutators ***/
    public void setNocta(String str)
    {
        this.nocta = str;
    }

    public void setTipo(String str)
    {
        this.tipo = str;
    }

    public void setSaldoAnterior(int n)
    {
        this.saldoAnterior = n;
    }

    public void setCantidad(int n)
    {
        this.cantidad = n;
    }

    public void setSaldoNuevo(int n)
    {
        this.saldoNuevo = n;
    }

    public void setNext(RetiroDP nodo)
    {
        this.next = nodo;
    }

    public String toString()
    {
        return this.nocta+"_"+this.tipo+"_"+this.saldoAnterior+"_"+this.cantidad+"_"+this.saldoNuevo;
        
    }
}
