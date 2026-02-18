package Entidades;

public class MotoristaTotalEntregas {
    private String nome;
    private int totalEntregas;

    public MotoristaTotalEntregas(String nome, int totalEntregas) {
        this.nome = nome;
        this.totalEntregas = totalEntregas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTotalEntregas() {
        return totalEntregas;
    }

    public void setTotalEntregas(int totalEntregas) {
        this.totalEntregas = totalEntregas;
    }

    @Override
    public String toString() {
        return "MotoristaTotalEntregas{" +
                "nome='" + nome + '\'' +
                ", totalEntregas=" + totalEntregas +
                '}';
    }
}
