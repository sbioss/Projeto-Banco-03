import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Extrato {
    private String nome;
    private List<String> informacoes;

    public Extrato(String nome) {
        this.nome = nome;
        this.informacoes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<String> getInformacoes() {
        return informacoes;
    }

    public void adicionarInformacao(String informacao) {
        informacoes.add(informacao);
    }
}