package batalha;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Jogo {
    private Peca[][] tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador jogadorAtual;
    private Scanner scanner;

    public Jogo() {
        this.tabuleiro = new Peca[8][8];
        this.scanner = new Scanner(System.in);
        this.jogador1 = new Jogador("Jogador 1");
        this.jogador2 = new Jogador("Jogador 2");
        this.jogadorAtual = jogador1;
        inicializarPecas();
    }

    private void inicializarPecas() {
        // Peças do Jogador 1 (Brancas/Inferior)
        jogador1.adicionarPeca(new Guerreiro(0, 0, jogador1));
        jogador1.adicionarPeca(new Guerreiro(0, 2, jogador1));
        jogador1.adicionarPeca(new Guerreiro(0, 4, jogador1));
        jogador1.adicionarPeca(new Guerreiro(0, 6, jogador1));
        jogador1.adicionarPeca(new Arqueiro(1, 1, jogador1));
        jogador1.adicionarPeca(new Arqueiro(1, 5, jogador1));
        jogador1.adicionarPeca(new Cavaleiro(1, 3, jogador1));
        jogador1.adicionarPeca(new Cavaleiro(1, 7, jogador1));


        // Peças do Jogador 2 (Pretas/Superior)
        jogador2.adicionarPeca(new Guerreiro(7, 0, jogador2));
        jogador2.adicionarPeca(new Guerreiro(7, 2, jogador2));
        jogador2.adicionarPeca(new Guerreiro(7, 4, jogador2));
        jogador2.adicionarPeca(new Guerreiro(7, 6, jogador2));
        jogador2.adicionarPeca(new Arqueiro(6, 1, jogador2));
        jogador2.adicionarPeca(new Arqueiro(6, 5, jogador2));
        jogador2.adicionarPeca(new Cavaleiro(6, 3, jogador2));
        jogador2.adicionarPeca(new Cavaleiro(6, 7, jogador2));

        atualizarTabuleiro();
    }

    private void atualizarTabuleiro() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabuleiro[i][j] = null;
            }
        }
        for (Peca peca : jogador1.getPecas()) {
            tabuleiro[peca.getPosicaoX()][peca.getPosicaoY()] = peca;
        }
        for (Peca peca : jogador2.getPecas()) {
            tabuleiro[peca.getPosicaoX()][peca.getPosicaoY()] = peca;
        }
    }


    public void iniciar() {
        while (true) {
            imprimirTabuleiro();
            System.out.println("Turno do " + jogadorAtual.getNome());
            listarPecasDoJogador(jogadorAtual);

            Peca pecaEscolhida = escolherPeca();
            if (pecaEscolhida == null) continue;

            System.out.print("Informe a nova posição (x y): ");
            try {
                int novoX = scanner.nextInt();
                int novoY = scanner.nextInt();

                if (movimentoValido(pecaEscolhida, novoX, novoY)) {
                    moverPeca(pecaEscolhida, novoX, novoY);
                    if (verificarFimDeJogo()) {
                        imprimirTabuleiro();
                        System.out.println("Fim de jogo! O " + jogadorAtual.getNome() + " venceu!");
                        break;
                    }
                    trocarJogador();
                } else {
                    System.out.println("Movimento inválido. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira números para as coordenadas.");
                scanner.next(); // Limpar buffer do scanner
            }
        }
        scanner.close();
    }

    private void listarPecasDoJogador(Jogador jogador) {
        System.out.println("Suas peças:");
        List<Peca> pecas = jogador.getPecas();
        for (int i = 0; i < pecas.size(); i++) {
            Peca p = pecas.get(i);
            System.out.println(i + ": " + p.getNome() + " em (" + p.getPosicaoX() + ", " + p.getPosicaoY() + ")");
        }
    }

    private Peca escolherPeca() {
        System.out.print("Escolha o número da peça que deseja mover: ");
        try {
            int indice = scanner.nextInt();
            List<Peca> pecas = jogadorAtual.getPecas();
            if (indice >= 0 && indice < pecas.size()) {
                return pecas.get(indice);
            } else {
                System.out.println("Número da peça inválido.");
                return null;
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
            scanner.next(); // Limpar buffer do scanner
            return null;
        }
    }

    private boolean movimentoValido(Peca peca, int novoX, int novoY) {
        if (novoX < 0 || novoX >= 8 || novoY < 0 || novoY >= 8) {
            return false; // Fora do tabuleiro
        }

        if (!peca.podeMoverPara(novoX, novoY)) {
            return false; // Regra de movimento da peça
        }

        Peca pecaDestino = tabuleiro[novoX][novoY];
        if (pecaDestino != null && pecaDestino.getJogadorDono() == jogadorAtual) {
            return false; // Não pode mover para uma casa ocupada por uma peça sua
        }

        return true;
    }

    private void moverPeca(Peca peca, int novoX, int novoY) {
        Peca pecaDestino = tabuleiro[novoX][novoY];
        if (pecaDestino != null) {
            // Ataque
            Jogador adversario = (jogadorAtual == jogador1) ? jogador2 : jogador1;
            adversario.removerPeca(pecaDestino);
            System.out.println(peca.getNome() + " atacou e removeu " + pecaDestino.getNome() + " do adversário!");
        }

        tabuleiro[peca.getPosicaoX()][peca.getPosicaoY()] = null;
        peca.moverPara(novoX, novoY);
        tabuleiro[novoX][novoY] = peca;
    }

    private boolean verificarFimDeJogo() {
        Jogador adversario = (jogadorAtual == jogador1) ? jogador2 : jogador1;
        return adversario.semPecas();
    }

    private void trocarJogador() {
        jogadorAtual = (jogadorAtual == jogador1) ? jogador2 : jogador1;
    }

    private void imprimirTabuleiro() {
        System.out.println("  0 1 2 3 4 5 6 7 (Y)");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                if (tabuleiro[i][j] != null) {
                    System.out.print(tabuleiro[i][j].getSimbolo() + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println("(X)");
    }
}
