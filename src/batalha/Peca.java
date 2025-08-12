package batalha;

public abstract class Peca {
    protected String nome;
    protected char simbolo;
    protected int posicaoX;
    protected int posicaoY;
    protected Jogador jogadorDono;

    public Peca(String nome, char simbolo, int posicaoX, int posicaoY, Jogador jogadorDono) {
        this.nome = nome;
        this.simbolo = simbolo;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.jogadorDono = jogadorDono;
    }

    public abstract boolean podeMoverPara(int novoX, int novoY);

    public void moverPara(int novoX, int novoY) {
        this.posicaoX = novoX;
        this.posicaoY = novoY;
    }

    public String getNome() {
        return nome;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public int getPosicaoX() {
        return posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public Jogador getJogadorDono() {
        return jogadorDono;
    }
}
