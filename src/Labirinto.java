import java.io.*;
import java.util.Arrays;

public class Labirinto {

    private Coordenada Entrada, Saida; private Byte cod=0;

    private char[][] labirinto;
    private byte altura, tamanho;
    private String path;

    private BufferedReader out;

    //construtor do labirinto
    public Labirinto(String path)throws Exception{
        if(path==null)
            throw new Exception();
        this.path=path;

        try{
            //Inicialização das variaveis
            out = new BufferedReader(new FileReader(path));

            altura  = Byte.parseByte(out.readLine());
            tamanho = Byte.parseByte(out.readLine());

            //criação da matriz
            labirinto = new char[tamanho][altura];

            //variavel linha recebe primeira linha
            String linha = out.readLine();

            //este for repetira linha por linha até que o arquivo acabe, dessa forma
            //guardara em que linha o labirinto está sendo lida.
            for(byte iAlt=0; linha!=null; iAlt++){

                //este for repetira até o fim da linha, guardando dessa forma cada caracter
                //em sua posição na matriz.
                for(byte iTam=0; iTam<linha.length();iTam++){
                    //adiciona os caracteres da linha na matriz labirinto
                    labirinto[iTam][iAlt]=linha.charAt(iTam);

                    //----------------------------------------
                    //verificações do labirinto

                    //verifica se é 'E'
                    if(linha.charAt(iTam)=='E') {
                        Entrada = new Coordenada(iTam, iAlt);
                        cod++;
                    }
                    //verifica se é 'S'
                    if(linha.charAt(iTam)=='S') {
                        Saida = new Coordenada(iTam, iAlt);
                        cod++;
                    }

                    //verifica se há espaços em branco nas bordas do labirinto
                    if(iTam==0||iTam==tamanho-1||iAlt==0||iAlt==altura-1){
                        if(linha.charAt(iTam)==' ')
                            throw new Exception("Sem paredes");
                    }
                }
                //se a linha termina antes do tamanho indicado, há uma parede faltando
                if(linha.length()!=tamanho)
                    throw new Exception("Não há paredes nos extremos do labirinto");
                //lê uma nova linha
                linha = out.readLine();
            }
            //verifica se há o numero certo de Entradas ou Saidas
            if(cod!=2)
                throw new Exception("há saidas e entradas demais ou faltando");

        }
        //devolve exceções
        catch (IndexOutOfBoundsException err){
            // se o tamanho da linha é maior que o passado na matriz, o segundo for dara
            // uma excessão de "tamanho excede o limite"
            throw new IllegalArgumentException("O labirinto excede o tamanho indicado");
        }
        catch(NumberFormatException err){
            // se o numero passado no arquivo é maior que 128, dara exceção de formato de numero
            // indicando que o labirinto é grande demais
            throw new IllegalArgumentException("O labirinto excede o limite permitido");
        }
        catch (Exception err) {
            throw new Exception("Labirinto invalido : " + err.getMessage());
        }
        //fecha a classe leitora de arquivo
        finally { out.close(); }
    }

    //getters

    public char getCharAt(byte x, byte y)throws IndexOutOfBoundsException, NumberFormatException{
        return labirinto[x][y];
    }
    public char[][] getLabirinto() { return labirinto; }

    public Coordenada getEntrada(){ return Entrada; }
    public Coordenada getSaida(){ return Saida; }

    public byte getTamanho() { return tamanho; }
    public byte getAltura() { return altura; }

    //setters

    public void setCharAt(char chr, byte x, byte y)throws IndexOutOfBoundsException, NumberFormatException{
        labirinto[x][y]=chr;
    }

    //métodos obrigatórios

    @Override
    public String toString() {
        String ret="";

        for(byte iAlt = 0; iAlt<altura; iAlt++){
            for(byte iTam = 0; iTam<tamanho; iTam++){
                ret+=labirinto[iTam][iAlt];
            }
            ret+='\n';
        }

        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Labirinto labirintoObj = (Labirinto) obj;
        return Arrays.equals(this.getLabirinto(), labirintoObj.getLabirinto());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(labirinto);
    }

    public Labirinto(Labirinto modelo)throws Exception{
        if(modelo==null)
            throw new Exception("modelo auxente");

        this.altura = modelo.getAltura();
        this.tamanho = modelo.getTamanho();
        this.labirinto = new char[tamanho][altura];

        for(byte x=0; x<modelo.tamanho; x++)
            for(byte y=0; y<modelo.altura; y++)
                this.labirinto[x][y] = modelo.labirinto[x][y];



        this.Entrada = modelo.Entrada.clone();
        this.Saida = modelo.Entrada.clone();
    }

    @Override
    public Object clone(){

        Labirinto ret = null;

        try {
            ret = new Labirinto(this);
        }
        catch(Exception ignored){ }

        return ret;
    }
}