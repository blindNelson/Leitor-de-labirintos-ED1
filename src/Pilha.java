public class Pilha <Gen>
{
    private ListaLigada<Gen> elementos;

    public Pilha ()
    {
        this.elementos = new ListaLigada<Gen> ();
    }

    public void guardeUmItem (Gen info) throws Exception
    {
        if (info==null)
            throw new Exception ("Falta o que guardar");

        this.elementos.insiraNoInicio (info);
    }

    public Gen recupereUmItem () throws Exception
    {
        if (this.elementos.isVazia())
            throw new Exception ("Nada a recuperar");

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

    public int getTamanho(){
        try{
            return this.elementos.getQuantidade();
        }
        catch(Exception err){
            return 0;
        }
    }

    public String toString() {
        return this.elementos.toString();
    }

    public boolean equals (Object obj)
    {
        if(this==obj)
            return true;

        if(obj==null)
            return false;

        if(this.getClass()!=obj.getClass())
            return false;

        Pilha<Gen> pil = (Pilha<Gen>) obj;

        return this.elementos.equals (pil.elementos);
    }

    public int hashCode ()
    {
        int ret=666/*qualquer positivo*/;

        ret = ret*7/*primo*/ + this.elementos.hashCode();

        if (ret<0)
            ret=-ret;

        return ret;
    }

    public Pilha(Pilha<Gen> modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("modelo ausente");

        this.elementos = new ListaLigada<Gen> (modelo.elementos);
    }

    public Object clone()
    {
        Pilha<Gen> ret = null;

        try
        {
            ret  = new Pilha(this);
        }
        catch(Exception erro)
        {}

        return ret;
    }
}