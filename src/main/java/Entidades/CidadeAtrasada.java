package Entidades;

public class CidadeAtrasada {
    private String cidade;
    private int quantidade;

    public CidadeAtrasada(String cidade, int quantidade) {
        this.cidade = cidade;
        this.quantidade = quantidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "CidadeAtrasada{" +
                "cidade='" + cidade + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}


