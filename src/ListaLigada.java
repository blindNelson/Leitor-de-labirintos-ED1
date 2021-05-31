public class ListaLigada<Gen> {

    /*================================CLASSES PRIVADAS=======================================*/

    //{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{

    private class Node {

        private Gen info;
        private Node proximo;

        public Node(Gen info, Node proximo)throws IllegalArgumentException{
            if(info==null)
                throw new IllegalArgumentException("proximo recebe null");

            this.info = info;
            this.proximo = proximo;
        }

        public Node(Gen info)throws IllegalArgumentException{
            if(info==null)
                throw new IllegalArgumentException("proximo recebe null");

            this.info = info;
            this.proximo = null;
        }

        //-------------------------------------------------------------------





        public void setProximo(Node prox)throws IllegalArgumentException{
            if(prox.info==null)
                throw new IllegalArgumentException("proximo recebe null");

            this.proximo = prox;
        }

        public Gen getInfo() {return info;}
        public Node getProximo() {return proximo;}

        //--------------------------------------------------------------------




        @Override
        public String toString(){ return info.toString();}
    }

    //}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}
    //fecha classe










    /*================================DECLARAÇÃO DE VARIAVEIS=======================================*/

    private Node primeiro, ultimo;










    /*======================================CONSTRUTORES===========================================*/

    public ListaLigada(){}

    //construtor de cópias
    public ListaLigada(ListaLigada modelo)throws Exception{
        if(modelo==null)
            throw new Exception();

        Node atual = getPrimeiro();
        while (atual!=null){
            modelo.insiraNoUltimo(atual.getInfo());
            atual=atual.getProximo();
        }
    }











    /*====================================METODOS PUBLICOS===========================================*/



    //metodos principais do código: InsiraNoInicio() e insiraNoUltimo()
    //metodos foram passados pelo maligno para poder iniciar uma lista, sem eles,
    // a lista não funciona
    //-----------------------------------------------------------



    public void insiraNoInicio(Gen info)throws IllegalArgumentException{
        try{
            this.primeiro = new Node(info, this.primeiro);

            if (this.ultimo==null)
                this.ultimo=this.primeiro;
        }
        catch(IllegalArgumentException err){
            throw new IllegalArgumentException("primeiro nó invalido");
        }
    }



    public void insiraNoUltimo(Gen info)throws Exception{
        try{
            if (this.primeiro==null) {
                this.ultimo = new Node(info);
                this.primeiro = this.ultimo;
                return;
            }
            this.ultimo.setProximo(new Node(info));
            this.ultimo = ultimo.getProximo();
        }
        catch (IllegalArgumentException err){
            throw new Exception("valor do ultimo nó invalido");
        }
    }

    //julguei que este metodo também era nescessário
    //passarei a posição de um nó de parametro, junto de uma informação,
    // sendo a posição um contada do inicio até o fim da lista
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void insiraNaPosicao(Gen info, int pos)throws Exception{

            if(pos<=0)
                throw new Exception();

            Node nozinho = posicao(getPrimeiro(), pos-1, 1);

            if(nozinho==null)
                throw new Exception("posição invalida");

            if(nozinho==primeiro) {
                insiraNoInicio(info);
                return;
            }
            if(nozinho==ultimo) {
                insiraNoUltimo(info);
                return;
            }

            nozinho.setProximo(new Node(info,nozinho.getProximo()));
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



    //metodo para remoção de nós
    //creio que maligno ira nos instruir a construilos, então os farei preveamente
    //-----------------------------------------------------------

    public void removerPrimeiro()throws Exception{
        if (primeiro==null)
            throw new Exception();
        if(primeiro.getProximo()==null) {
            primeiro = null;
            return;
        }

        primeiro=primeiro.getProximo();
    }



    public void removerUltimo()throws Exception{
        if (primeiro==null)
            throw new Exception();
        if(primeiro==ultimo)
            ultimo=null;

        Node nozinho = getPrimeiro();
        while (nozinho.getProximo()!=ultimo)
            nozinho=nozinho.getProximo();

        ultimo=nozinho;
        nozinho.proximo = null;
    }

    public void removerItemIndicado(Gen ind)throws Exception{

        if(ind==null)
            throw new Exception("valor indicado invalido");
        if (primeiro==null)
            throw new Exception();

        removeIndicadoAux(ind, getPrimeiro());

        if(getPrimeiro().getInfo().equals(ind))
            removerPrimeiro();
    }



    //metodos de pesquisa na lista
    //-----------------------------------------------------------

    public Boolean tem(Gen ind)throws Exception{

        if(getPrimeiro()==null)
            throw new Exception("lista nula");
        if(ind==null)
            throw new Exception("valor invalido");

        Node nozinho=getPrimeiro();

        while (nozinho!=null){
            if (nozinho.getInfo().equals(ind))
                return true;
            nozinho = nozinho.getProximo();
        }
        return false;
    }

    public boolean isVazia(){
        if(primeiro==null)
            return true;
        return false;
    }



    //geters
    //-------------------------------------------//

    public Node getPrimeiro()throws Exception {
        if(primeiro==null)
            throw new Exception("lista nula");
        return primeiro;
    }
    public Node getUltimo() throws Exception{
        if(ultimo==null)
            throw new Exception("lista nula");
        return ultimo;
    }

    public int getQuantidade()throws Exception{
        if(primeiro==null)
            throw new Exception();
        return contaLista(getPrimeiro());
    }
    public Gen getInfoPrimeiro()throws Exception{
        if (primeiro==null)
            throw new Exception("Lista vazia");
        return primeiro.getInfo();
    }
    public Gen getInfoUltimo()throws Exception{
        if (primeiro==null)
            throw new Exception("Lista vazia");
        return ultimo.getInfo();
    }

    //-------------------------------------------\\










    /*===================================METODOS PRIVADOS========================================*/

    private int contaLista(Node nozinho){
        if (nozinho!=null)
            return contaLista(nozinho.proximo)+1;
        return 0;
    }

    private Node posicao(Node nozinho, int pos, int cont)throws Exception{
        if(cont<pos)
            return posicao(nozinho.getProximo(), pos, cont+1);
        return nozinho;
    }

    private void removeIndicadoAux(Gen ind, Node nozinho)throws Exception{
        if(nozinho.getProximo()!=ultimo)
            removeIndicadoAux(ind, nozinho.getProximo());

        if(nozinho.getProximo()!=ultimo){

            if(nozinho.getProximo().getInfo().equals(ind))
                nozinho.setProximo(nozinho.getProximo().getProximo());

            return;
        }

        if(ultimo.getInfo().equals(ind))
            removerUltimo();
    }










    /*================================METODOS OBRIGATORIOS=======================================*/

    @Override
    public String toString() {
        Node lista = primeiro;
        String ret = lista.toString();

        lista=lista.getProximo();

        while(lista!=null) {
            ret += "," + lista.getInfo();
            lista = lista.getProximo();
        }

        return "["+ret+"]";
    }



    @Override
    public boolean equals(Object obj){
        try{
            if(obj==this)
                return true;

            if(obj==null||obj.getClass()!=ListaLigada.class)
                return false;

            ListaLigada listaObj = (ListaLigada) obj;

            Node nodeAtual = primeiro;
            Node nodeOBJ = listaObj.getPrimeiro();

            while (nodeAtual != null && nodeOBJ !=null) {

                if (!nodeAtual.getInfo().equals(nodeOBJ.getInfo()))
                    return false;

                nodeAtual = nodeAtual.getProximo();
                nodeOBJ = nodeOBJ.getProximo();
            }

            if(nodeAtual !=null|| nodeOBJ !=null)
                return false;

            return true;
        }
        catch (Exception err){
            return false;
        }
    }



    @Override
    public int hashCode(){
        int ret = 2345;
        Node atual = primeiro;

        while(atual!=null){
            ret = 13*ret + atual.getInfo().hashCode();
            atual=atual.getProximo();
        }

        if(ret<0) ret=-ret;

        return ret;
    }



    @Override
    public Object clone(){
        ListaLigada ret=null;

        try{
            ret = new ListaLigada(this);
        }
        catch (Exception ignored){}

        return ret;
    }

}