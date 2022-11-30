public class Bolinha {
    private final int valor;

    public Bolinha(int valor) {
        this.valor = valor;
    }

    public static void mostrarBolinhasGlobo(Globo g1, int contagem) {
        Globo.desenhoGlobo();
        System.out.println("                                     " + g1.getBolinhasSorteadas()[contagem]);
        System.out.print("\n                Aperte enter parar sortear um novo numero do Bingo!");
        System.out.println("\n             Numero sorteado: " + g1.getBolinhasSorteadas()[contagem] + " | Quantidade de numeros sorteados: " + (contagem + 1));
        System.out.print("      ");
        for (int i = 0; i <= contagem; i++) {
            if (i % 15 == 0 && i != 0) {
                System.out.print("\n      ");
            }
            if (g1.getBolinhasSorteadas()[i].getValor() >= 10) {
                System.out.print(g1.getBolinhasSorteadas()[i] + " | ");
            } else {
                System.out.print("0" + g1.getBolinhasSorteadas()[i] + " | ");
            }
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return String.valueOf(getValor());
    }

    public int getValor() {
        return valor;
    }

}
