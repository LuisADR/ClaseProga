import java.util.StringTokenizer;

public class LibroDP
{
    private String titulo;
    private String autor;
    private String editorial;

    private LibroDP next, prev;

    // Constructores
    public LibroDP()
    {
        this.titulo     = "";
        this.autor      = "";
        this.editorial  = "";
    }

    public LibroDP(String datos)
    {
        StringTokenizer st = new StringTokenizer(datos,"_");

        this.titulo     = st.nextToken();
        this.autor      = st.nextToken();
        this.editorial  = st.nextToken();
    }

    // Accesors
    public String getTitulo()
    {
        return this.titulo;
    }

    public String getAutor()
    {
        return this.autor;
    }

    public String getEditorial()
    {
        return this.editorial;
    }

    public LibroDP getNext()
    {
        return this.next;
    }

    public LibroDP getPrev(){
      return this.prev;
    }

    // Mutators
    public void setTitulo(String tit)
    {
        this.titulo = tit;
    }

    public void setAutor(String aut)
    {
        this.autor = aut;
    }

    public void setEditorial(String edit)
    {
        this.editorial = edit;
    }

    public void setNext(LibroDP nodo)
    {
        this.next = nodo;
    }

    public void setPrev(LibroDP nodo){
      this.prev = nodo;
    }

    public String toString()
    {
        return this.titulo+"_"+this.autor+"_"+this.editorial;
    }
}
