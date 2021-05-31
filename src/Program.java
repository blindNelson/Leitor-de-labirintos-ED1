import java.util.Scanner;
import java.io.File;

public class Program {

    public static void main(String[] args) {
        while(program()){ }
    }

    private static boolean program(){
        var teclado = new Scanner(System.in);
        try {

            System.out.print("\n\nnome do arquivo: ");
            String nomeArquivo = teclado.next();
            System.out.println('\n');

            var labirinto = new Labirinto(DiretorioAtual()+"\\src\\labirintos\\" + nomeArquivo);

            Caminho caminho = new Caminho(labirinto);
            System.out.println(caminho);
            System.out.println(caminho.getCaminho());

            labirinto = null;
        }
        catch(NullPointerException err){
            System.err.println("pasta não encontrada");
        }
        catch (Exception Ex) {
            System.out.println("erro:"+Ex.getMessage());
        }
        System.out.println("\ndeseja continuar? (digite [s])");
        return teclado.next().equals("s");
    }

    private static String DiretorioAtual(){
        File caminho = new File("");

        return caminho.getAbsolutePath();
    }
}