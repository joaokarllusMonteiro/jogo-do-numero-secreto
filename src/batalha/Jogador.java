package batalha;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private List<Peca> pecas;

    public Jogador(String nome) {
        this.nome = nome;
        this.pecas = new ArrayList<>();
    }

    public void adicionarPeca(Peca peca) {
        this.pecas.add(peca);
    }

    public void removerPeca(Peca peca) {
        this.pecas.remove(peca);
    }

    public List<Peca> getPecas() {
        return pecas;
    }

    public String getNome() {
        return nome;
    }

    public boolean semPecas() {
        return this.pecas.isEmpty();
    }
}
