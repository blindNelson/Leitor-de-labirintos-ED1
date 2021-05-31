public class Fila <Gen>
{
    private ListaLigada<Gen> elementos;

    public Fila ()
    {
        this.elementos = new ListaLigada<Gen> ();
    }

    public void guardeUmItem (Gen x) throws Exception
    {
        if (x==null)
            throw new Exception ("Falta o que guardar");

        this.elementos.insiraNoUltimo (x);
    }

    public Gen recupereUmItem () throws Exception
    {
        if (this.elementos.isVazia())
            throw new NullPointerException("Nada a recuperar");

        return this.elementos.getInfoPrimeiro();
    }

    public void removaUmItem () throws Exception
    {
        if (this.elementos.isVazia())
            throw new Exception ("Nada a remover");

        this.elementos.removerPrimeiro();
    }

    public boolean isVazia ()
    {
        return this.elementos.isVazia();
    }

    public String toString()
    {
        String ret="";

        try
        {
            ret = this.elementos.getQuantidade() + " elementos";

            ret += ", sendo o primeiro "+this.elementos.getInfoPrimeiro();
        }
        catch (Exception erro)
        {} // se der erro, nao quero nada acrescentar ao ret

        return ret;
    }

    public boolean equals (Object obj)
    {
        if(this==obj)
            return true;

        if(obj==null)
            return false;

        if(this.getClass()!=obj.getClass())
            return false;

        Fila<Gen> fil = (Fila<Gen>) obj;

        return this.elementos.equals (fil.elementos);
    }

    //public void esvaziar

    public int hashCode ()
    {
        int ret=666/*qualquer positivo*/;

        ret = ret*7/*primo*/ + this.elementos.hashCode();

        if (ret<0)
            ret=-ret;

        return ret;
    }

    public Fila(Fila<Gen> modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("modelo ausente");

        this.elementos = new ListaLigada<Gen> (modelo.elementos);
    }

    public Object clone()
    {
        Fila<Gen> ret = null;

        try
        {
            ret = new Fila(this);
        }
        catch(Exception erro)
        {}

        return ret;
    }
}

