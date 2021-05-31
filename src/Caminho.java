import java.util.Objects;

public class Caminho {
    private Pilha caminho, possibilidades;
    private Coordenada atual;
    private Labirinto labirinto;

    public Caminho(Labirinto labirinto)throws Exception{
        if(labirinto==null)
            throw new Exception();

        caminho = new Pilha<Coordenada>();
        possibilidades = new Pilha<Fila<Coordenada>>();

        this.labirinto = (Labirinto) labirinto.clone();

        atual = labirinto.getEntrada();

        encontrarCaminho();

        possibilidades = null;atual = null;
    }

    private void encontrarCaminho()throws Exception{
        for(;;){
            Fila direcao = direcoes(atual);//instanciar fila


            progressao:for(;;){
                try{
                    atual = (Coordenada) direcao.recupereUmItem();  //guardar fila em atual
                    direcao.removaUmItem();                         //remover um item da fila
                    caminho.guardeUmItem(atual);                    //guardar atual no caminho
                    possibilidades.guardeUmItem(direcao);           //guardar fila em possibilidades
                    break progressao;
                }
                catch (NullPointerException nEx){
                    try{
                        atual = (Coordenada) caminho.recupereUmItem();
                        caminho.removaUmItem();
                        labirinto.setCharAt(' ', atual.getX(), atual.getY());
                        direcao = (Fila) possibilidades.recupereUmItem();
                        possibilidades.removaUmItem();
                    }
                    catch(Exception ex){
                        throw new Exception("não há caminho para Saida");
                    }
                }
            }

            if(labirinto.getCharAt(atual.getX(), atual.getY())=='S')
                break;

            labirinto.setCharAt('*', atual.getX(), atual.getY());
        }
    }

    private Fila direcoes(Coordenada atual)throws Exception{
        Fila direcao = new Fila<Coordenada>();

        for(byte n=1, i=1; i<=2; n*=-1, i++) {
            try{
                if(labirinto.getCharAt(atual.getX(), (byte) (atual.getY() + n))==' ' ||
                        labirinto.getCharAt(atual.getX(), (byte) (atual.getY() + n))=='S' ) {
                    direcao.guardeUmItem(new Coordenada(atual.getX(), (byte) (atual.getY() + n)));
                }
            }
            catch (Exception err){}

            try{
                if(labirinto.getCharAt((byte)(atual.getX() + n),atual.getY())==' ' ||
                        labirinto.getCharAt((byte)(atual.getX() + n),atual.getY())=='S')
                    direcao.guardeUmItem(new Coordenada((byte) (atual.getX() + n), atual.getY()));
            }
            catch(Exception err){}
        }

        return direcao;
    }

    public Pilha getCaminho()throws Exception{
        if(caminho==null)
            throw new Exception();
        return caminho;
    }

    @Override
    public String toString(){
        return labirinto.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Caminho)) return false;
        Caminho caminho = (Caminho) obj;
        return labirinto.equals(caminho.labirinto);
    }

    @Override
    public int hashCode() {
        return labirinto.hashCode();
    }

    public Caminho(Caminho modelo)throws Exception{
        if(modelo==null) throw new Exception();
        this.labirinto = (Labirinto) modelo.clone();
    }
}