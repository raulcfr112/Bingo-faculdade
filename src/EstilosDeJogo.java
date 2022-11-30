import java.util.Arrays;
import java.util.List;

public class EstilosDeJogo {
    static int cartelaBingada;

    public static Boolean verificarBingo(Globo g1, int contagem, int quantidadeDeCartelas, int escolhaDoJogo) {
        if (escolhaDoJogo < 3) {
            return verificarBingoLinhaColuna(g1, Bingo.arrayGlobo, contagem, quantidadeDeCartelas, escolhaDoJogo);
        } else {
            return verificarBingoJanelaECheia(g1, Bingo.arrayGlobo, contagem, quantidadeDeCartelas, escolhaDoJogo);
        }
    }

    public static Boolean verificarBingoLinhaColuna(Globo g1, Integer[] arrayGlobo, int contagem, int quantidadeDeCartelas, int escolhaDoJogo) {
        List<Integer> lista = Arrays.asList(Arrays.copyOf(arrayGlobo, contagem));
        int auxCartela = 0;
        for (int i = 0; i < quantidadeDeCartelas; i++) {
            int auxLoop = 0, auxPosicao = 0;
            do {
                int bingo = 0;
                String[] numerosLinha = numerosVerificao(g1, auxCartela, auxPosicao, escolhaDoJogo);
                for (String s : numerosLinha) {
                    if (s.equals("--")) {
                        bingo++;
                    } else if (lista.contains(Integer.parseInt(s))) {
                        bingo++;
                    }
                    if (bingo == 5) {
                        cartelaBingada = g1.getCartelasGeradas()[auxCartela].getId();
                        return true;
                    }
                }
                auxPosicao++;
                auxLoop++;
            } while (auxLoop != 5);
            auxCartela++;
        }
        return false;
    }

    public static Boolean verificarBingoJanelaECheia(Globo g1, Integer[] arrayGlobo, int contagem, int quantidadeDeCartelas, int escolhaDoJogo) {
        List<Integer> lista = Arrays.asList(Arrays.copyOf(arrayGlobo, contagem));
        int auxCartela = 0;
        do {
            String[] numerosLinha = numerosVerificao(g1, auxCartela, 0, escolhaDoJogo);
            if (verificadorElementos(g1, auxCartela, numerosLinha, lista, escolhaDoJogo)) {
                return true;
            }
            auxCartela++;
        } while (auxCartela != quantidadeDeCartelas);
        return false;
    }

    public static Boolean verificadorElementos(Globo g1, int auxCartela, String[] numerosLinha, List<Integer> lista, int escolhaDoJogo) {
        int bingo = 0;
        for (String s : numerosLinha) {
            if (s.equals("--")) {
                bingo++;
            } else if (lista.contains(Integer.parseInt(s))) {
                bingo++;
            }
        }
        if (escolhaDoJogo == 3 && bingo == 16) {
            cartelaBingada = g1.getCartelasGeradas()[auxCartela].getId();
            return true;
        } else if (escolhaDoJogo == 4 && bingo == 25) {
            cartelaBingada = g1.getCartelasGeradas()[auxCartela].getId();
            return true;
        } else if (escolhaDoJogo < 3 && bingo == 5) {
            cartelaBingada = g1.getCartelasGeradas()[auxCartela].getId();
            return true;
        }
        return false;
    }

    public static String[] numerosVerificao(Globo g1, int auxCartela, int auxPosicao, int escolhaDoJogo) {
        String[] numerosLinha = new String[5];
        switch (escolhaDoJogo) {
            case 1:
                int auxLinha = 0;
                if (auxPosicao > 0) {
                    for (int i = 0; i < auxPosicao; i++) {
                        auxLinha += 5;
                    }
                    return preencherVetorLinha(g1, auxCartela, auxLinha);
                } else {
                    return preencherVetorLinha(g1, auxCartela, 0);
                }
            case 2:
                return preencherVetorColuna(g1, auxCartela, auxPosicao);
            case 3:
                return preencherVetorJanelao(g1, auxCartela, auxPosicao);
            case 4:
                return preencherVetorCheio(g1, auxCartela);
        }
        return numerosLinha;
    }

    public static String[] preencherVetorLinha(Globo g1, int auxCartela, int auxPosicao) {
        String[] numerosLinha = new String[5];
        int auxContador = 0;
        for (int i = auxPosicao; auxContador < 5; i++) {
            numerosLinha[auxContador] = g1.getCartelasGeradas()[auxCartela].getNumerosCartela()[i];
            auxContador++;
        }
        return numerosLinha;
    }

    public static String[] preencherVetorColuna(Globo g1, int auxCartela, int auxPosicao) {
        String[] numerosColuna = new String[5];
        int auxContador = 0;
        for (int i = 0; i < 5; i++) {
            numerosColuna[auxContador] = g1.getCartelasGeradas()[auxCartela].getNumerosCartela()[auxPosicao];
            auxPosicao += 5;
            auxContador++;
        }
        return numerosColuna;
    }

    public static String[] preencherVetorJanelao(Globo g1, int auxCartela, int auxPosicao) {
        String[] vetorNumerosColuna = preencherVetorColunaSemLinha(g1, auxCartela, 5);
        String[] vetorNumerosLinha = concatenarVetorString(preencherVetorLinha(g1, auxCartela, auxPosicao), preencherVetorLinha(g1, auxCartela, ((auxPosicao)) + 20));

        return concatenarVetorString(vetorNumerosLinha, vetorNumerosColuna);
    }

    public static String[] preencherVetorColunaSemLinha(Globo g1, int auxCartela, int auxPosicao) {
        String[] numerosColuna = new String[6];
        int auxContador = 0;
        do {
            for (int i = 0; i < 3; i++) {
                numerosColuna[auxContador] = g1.getCartelasGeradas()[auxCartela].getNumerosCartela()[auxPosicao];
                auxPosicao += 5;
                auxContador++;
            }
            auxPosicao = 9;
        } while (auxContador != 6);

        return numerosColuna;
    }

    public static String[] preencherVetorCheio(Globo g1, int auxCartela) {
        String[] vetorCheio = new String[25];
        for (int i = 0; i < vetorCheio.length; i++) {
            vetorCheio[i] = g1.getCartelasGeradas()[auxCartela].getNumerosCartela()[i];
        }
        return vetorCheio;
    }

    public static String[] concatenarVetorString(String[] a, String[] b) {
        String[] c = new String[a.length + b.length];
        int aux = 0;
        for (String s : a) {
            c[aux++] = s;
        }
        for (String s : b) {
            c[aux++] = s;
        }
        return c;
    }

}
