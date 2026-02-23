package Entidades;

public class QuantidadeEstado {
    private String nomeEstado;
    private int quantidade;

    public QuantidadeEstado(String nomeEstado, int quantidade) {
        this.nomeEstado = nomeEstado;
        this.quantidade = quantidade;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "quantidadeEstado{" +
                "nomeEstado='" + nomeEstado + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}
