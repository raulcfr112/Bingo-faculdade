import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Cartelas {
    private final String[] numerosCartela;
    private final int id;

    public Cartelas(String[] numerosCartela, int id) {
        this.numerosCartela = numerosCartela;
        this.id = id;
    }

    public static String[] gerarArrayNumerosCartela(int id) throws IOException {
        int linha = 1;
        String[] numerosCartela = new String[25];
        Path cartelaaux = Paths.get("src/Cartelas/Cartela" + id + ".txt");
        Scanner cartela = new Scanner(cartelaaux.toFile());

        while (cartela.hasNextLine()) {
            if (linha > 1) {
                String data = cartela.nextLine();
                for (String s : data.split(" ")) {
                    for (int i = 0; i < 25; i++) {
                        if (numerosCartela[i] == null) {
                            numerosCartela[i] = s;
                            break;
                        }
                    }
                }
            } else {
                cartela.nextLine();
                linha++;
            }
        }
        return numerosCartela;
    }

    public static void gerarCartelasUsuario(int quantidadeDeCartelas) throws IOException {
        int contagem = 1;
        while (quantidadeDeCartelas >= contagem) {
            Integer[] arrayNumerosSorteados = new Integer[25];
            int contagemArray = 0;
            FileWriter writer = new FileWriter("src/Cartelas/Cartela" + contagem + ".txt");
            writer.write("B  I  N  G  O");
            for (int i = 0; i < 5; i++) {
                int numeroInicial = 1, numeroFinal = 16;
                writer.write("\n");
                for (int j = 0; j < 5; j++) {
                    int aux = Bingo.sortearNumero(numeroInicial, numeroFinal, arrayNumerosSorteados);
                    arrayNumerosSorteados[contagemArray] = aux;
                    contagemArray++;
                    if (i == 2 && j == 2) {
                        writer.write("-- ");
                    } else if (aux >= 10) {
                        writer.write(aux + " ");
                    } else {
                        writer.write("0" + aux + " ");
                    }
                    numeroInicial += 15;
                    numeroFinal += 15;
                }
            }
            writer.close();
            contagem++;
        }
    }

    public String[] getNumerosCartela() {
        return numerosCartela;
    }

    public int getId() {
        return id;
    }

}
