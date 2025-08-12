package batalha;

public class Cavaleiro extends Peca {

    public Cavaleiro(int posicaoX, int posicaoY, Jogador jogadorDono) {
        super("Cavaleiro", 'C', posicaoX, posicaoY, jogadorDono);
    }

    @Override
    public boolean podeMoverPara(int novoX, int novoY) {
        int deltaX = Math.abs(novoX - this.posicaoX);
        int deltaY = Math.abs(novoY - this.posicaoY);

        // Não pode mover para a mesma casa
        if (deltaX == 0 && deltaY == 0) {
            return false;
        }

        // Move em L (2x1 ou 1x2)
        if ((deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1)) {
            return true;
        }

        return false;
    }
}
