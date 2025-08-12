package batalha;

public class Guerreiro extends Peca {

    public Guerreiro(int posicaoX, int posicaoY, Jogador jogadorDono) {
        super("Guerreiro", 'G', posicaoX, posicaoY, jogadorDono);
    }

    @Override
    public boolean podeMoverPara(int novoX, int novoY) {
        int deltaX = Math.abs(novoX - this.posicaoX);
        int deltaY = Math.abs(novoY - this.posicaoY);

        // Não pode mover para a mesma casa
        if (deltaX == 0 && deltaY == 0) {
            return false;
        }

        // Move até 3 casas na vertical ou horizontal
        if ((deltaX > 0 && deltaY == 0 && deltaX <= 3) || (deltaY > 0 && deltaX == 0 && deltaY <= 3)) {
            return true;
        }

        return false;
    }
}
