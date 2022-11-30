import java.io.IOException;

public class Globo {
    private final Bolinha[] bolinhasSorteadas;
    private final Cartelas[] cartelasGeradas;

    public Globo(Bolinha[] bolinhasSorteadas, Cartelas[] cartelasGeradas) {
        this.bolinhasSorteadas = bolinhasSorteadas;
        this.cartelasGeradas = cartelasGeradas;
    }

    public static void preencherArrayBolinhaGlobo(Integer[] arrayGlobo, Globo g1) {
        int aux;
        for (int i = 0; i < arrayGlobo.length; i++) {
            aux = Bingo.sortearNumero(1, 76, arrayGlobo);
            Bolinha b1 = new Bolinha(aux);
            arrayGlobo[i] = aux;
            g1.getBolinhasSorteadas()[i] = b1;
        }
        g1.getBolinhasSorteadas();
    }

    public static void preencherArrayCartelaGlobo(Globo g1, int quantidadeDeCartelas) throws IOException {
        int aux = 1;
        for (int i = 0; i < quantidadeDeCartelas; i++) {
            Cartelas cartela = new Cartelas(Cartelas.gerarArrayNumerosCartela(aux), aux);
            g1.getCartelasGeradas()[i] = cartela;
            aux++;
        }
        g1.getCartelasGeradas();
    }

    public static void desenhoGlobo() {
        System.out.println("""
                                                                                         \s
                                                                                         \s
                                                                                         \s
                                                 ..                                      \s
                                           .(,  .   .,*                                  \s
                                       ...                /,                             \s
                                   .,  .,, * .  /   .     ./ /,                          \s
                                 .. ,   . .  .  *       (  . ./ .                        \s
                               #  %,( .. *  .      . .. ,,      /#                       \s
                              %# * # .% .   %      /  /    .     *%.                     \s
                             %  # .  ,  %          /         ,**(, .                     \s
                             .,            .   (   (     ,% ./     .&                    \s
                            &% # *  ,      #   %  ,/     ** (   &*# #                    \s
                         &( #/ # #  %  %   %   *  .,..   (. ,  /#* /& /&                 \s
                ,,,,,,,,*#*%.  ,******/%**********,****,,*(,,*,***,*/./@,,,,*%           \s
                         &( &/         /.***,,,,,,,,***,,***** *#. ,* (@      (          \s
                         &/  .,   /    *          .  ./   * .%  %(#   *@      *          \s
                         %#  (   ,   * ,   /   .#.,  *.           (,  *@                 \s
                         %%   .   %.,    .  **.,  ,  .   ..*,,....,.  ,&                 \s
                         #%    .*//*.//. .,*(,..*****,/*/, .  *.,.    /&                 \s
                         #%     .,//(*,*,,*(**.*,&@(/,./,%/**,*.,     (%                 \s
                         (%       ,#*(((*//*/****,@/*//,,**(//*       (%                 \s
                         (/          //*(/#(@/##((&/#(*/**,*,         *(                 \s
                         **              (/(&@@@/@%((*@(,             ,*                 \s
                         .,.                 //((#/.                  ,*                 \s
                         .,.                 **(((*.                  .,                 \s
                          ,.                 */(/(//#  ,              ,,                 \s
                          ,,                .*/(((*(##((##(//         .,                 \s
                          ,.                .*/(/(*                   ,,                 \s
                          ,,                                          /.  \s""".indent(6));
    }

    public Bolinha[] getBolinhasSorteadas() {
        return bolinhasSorteadas;
    }

    public Cartelas[] getCartelasGeradas() {
        return cartelasGeradas;
    }


}
