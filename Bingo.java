import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Bingo {
    static Integer[] arrayGlobo;

    public static Globo iniciarJogo(int quantidadeDeCartelas) {
        arrayGlobo = new Integer[75];
        Globo globo = new Globo(new Bolinha[75], new Cartelas[quantidadeDeCartelas]);
        try {
            Globo.preencherArrayCartelaGlobo(globo, quantidadeDeCartelas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Globo.preencherArrayBolinhaGlobo(arrayGlobo, globo);
        return globo;
    }

    public static void menuBingo(Globo g1, int contagem,String enter) {
        if (enter.equals("")) {
            Bolinha.mostrarBolinhasGlobo(g1, contagem);
            BingoApp.contagem++;
        } else {
            System.out.println("Erro!, aperte >ENTER< pra gerar um novo numero!");
        }
    }

    public static int sortearNumero(int valorInicial, int valorFinal, Integer[] array) {
        Random random = new Random();
        int numero = random.nextInt(valorInicial, valorFinal);
        if (verificadorRepeticao(numero, array)) {
            return numero;
        }
        return sortearNumero(valorInicial, valorFinal, array);
    }

    public static boolean verificadorRepeticao(Integer numero, Integer[] arrayGlobo) {
        List<Integer> lista = Arrays.asList(arrayGlobo);
        return !lista.contains(numero);
    }
}
