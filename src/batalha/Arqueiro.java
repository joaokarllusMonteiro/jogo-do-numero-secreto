package batalha;

public class Arqueiro extends Peca {

    public Arqueiro(int posicaoX, int posicaoY, Jogador jogadorDono) {
        super("Arqueiro", 'A', posicaoX, posicaoY, jogadorDono);
    }

    @Override
    public boolean podeMoverPara(int novoX, int novoY) {
        int deltaX = Math.abs(novoX - this.posicaoX);
        int deltaY = Math.abs(novoY - this.posicaoY);

        // Não pode mover para a mesma casa
        if (deltaX == 0 && deltaY == 0) {
            return false;
        }

        // Move até 2 casas nas diagonais
        if (deltaX == deltaY && deltaX <= 2) {
            return true;
        }

        return false;
    }
}
