import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BingoApp {
    static int contagem = 0;
    static Scanner n = new Scanner(System.in);

    public static void main(String[] args) {
        int escolhaMenu = 0, quantidadeDeCartelas = 0, opcaoSimOuNao;
        int escolhaDoJogo = 0;
        boolean cartelasGeradas = false;

        do {
            while (escolhaMenu == 0) {
                System.out.println("""                 
                        _________________________________________________________________________
                                                                                                 |
                                                     *-- MENU --*                                |
                                                   Digite sua opcao:                             |
                                                                                                 |
                        1. Gerar cartelas: gera aleatoriamente uma quantidade x de cartelas!     |
                        2. Iniciar Jogo: posiciona o globo para comecar a rodada!                |
                        3. Sair: termina a execucao do jogo!                                     |
                         ||                                                                      |
                         vv                                                                      |
                            _____________________________________________________________________|""");
                try {
                    escolhaMenu = n.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Informe um inteiro!");
                    n.nextLine();
                    continue;
                }
                if (escolhaMenu > 3 || escolhaMenu == 0) {
                    System.out.println("Informe uma opcao valida!");
                    escolhaMenu = 0;
                }

            }
            switch (escolhaMenu) {
                case 1:
                    if (quantidadeDeCartelas > 0) {
                        while (true) {
                            System.out.println("Voce ja gerou " + quantidadeDeCartelas + " cartelas.\nDeseja mudar?\n1 -> Sim.\n2 -> Nao.");
                            try {
                                opcaoSimOuNao = n.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Informe um inteiro como opcao!");
                                n.nextLine();
                                continue;
                            }
                            if (opcaoSimOuNao == 2) {
                                break;
                            } else if (opcaoSimOuNao == 1) {
                                quantidadeDeCartelas = 0;
                                break;
                            } else {
                                System.out.println("Informe uma opcao correta!");
                            }
                        }

                    }
                    while (quantidadeDeCartelas == 0) {
                        System.out.println("Informe a quantidade de cartelas a ser gerada:");
                        try {
                            quantidadeDeCartelas = n.nextInt();
                            Cartelas.gerarCartelasUsuario(quantidadeDeCartelas);
                        } catch (InputMismatchException f) {
                            System.out.println("Erro! Informe um valor inteiro!");
                            n.nextLine();
                            continue;
                        } catch (IOException e) {
                            System.out.println("Erro na geração de cartelas, diretorio inexistente!");
                            quantidadeDeCartelas = 0;
                        }
                        if (quantidadeDeCartelas < 1) {
                            System.out.println("Erro! informe um valor maior que 0!");
                            quantidadeDeCartelas = 0;
                        }
                    }

                    System.out.println(quantidadeDeCartelas + " cartelas geradas!");
                    cartelasGeradas = true;
                case 2:
                    if (!cartelasGeradas) {
                        System.out.println("Antes de jogar, inicie as cartelas!");
                        escolhaMenu = 0;
                        break;
                    } else {
                        Globo g1 = Bingo.iniciarJogo(quantidadeDeCartelas);
                        while (escolhaDoJogo == 0) {
                            System.out.println("""
                                                        
                                    _____________________________________________________________________________________
                                                                                                                         |
                                                                 *-- MENU --*                                            |
                                                               Digite sua opcao:                                         |
                                                                                                                         |
                                    1. Linha -> Da vitoria a cartela que concluir a linha.                               |
                                    2. Coluna -> Da vitoria a cartela que concluir a coluna.                             |
                                    3. Janelao -> Da a vitoria a cartela que completar as linhas e colunas dos extremos. |
                                    4. Cheia -> Da a vitoria a cartela que completar todos os numeros.                   |
                                    5. Menu -> Retorna ao menu principal.                                                |
                                     ||                                                                                  |
                                     vv                                                                                  |
                                        _________________________________________________________________________________|""");
                            try {
                                escolhaDoJogo = n.nextInt();
                            } catch (InputMismatchException f) {
                                System.out.println("Informe um valor inteiro!");
                                n.nextLine();
                                continue;
                            }
                            if (escolhaDoJogo == 5) {
                                break;
                            }
                            if (escolhaDoJogo > 4 || escolhaDoJogo < 1) {
                                System.out.println("Informe uma opcao valida!");
                                escolhaDoJogo = 0;
                            }
                        }
                        if (escolhaDoJogo == 5) {
                            escolhaDoJogo = 0;
                            escolhaMenu = 0;
                            break;
                        }
                        System.out.println("""
                                Jogo iniciado!
                                Abra as cartelas no diretorio do computador, escolha a sua e let's play!
                                Para sortear novos numeros, aperte ENTER!
                                """);
                        n.nextLine();
                        do {
                            String enter = n.nextLine();
                            Bingo.menuBingo(g1, contagem, enter);
                        } while (!EstilosDeJogo.verificarBingo(g1, contagem, quantidadeDeCartelas, escolhaDoJogo));
                        System.out.println("Bingo!!!, cartela " + EstilosDeJogo.cartelaBingada + "!\nRetornando ao menu!");
                    }
                case 3:
                    System.exit(0);
            }
        } while (true);
    }
}
