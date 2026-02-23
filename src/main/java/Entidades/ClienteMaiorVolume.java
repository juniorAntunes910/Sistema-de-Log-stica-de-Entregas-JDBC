package Entidades;

public class ClienteMaiorVolume {
    private int id;
    private String nomeCliente;
    private double volume;

    public ClienteMaiorVolume(String nomeCliente, double volume) {
        this.nomeCliente = nomeCliente;
        this.volume = volume;
    }

    public ClienteMaiorVolume(int id, String nomeCliente, double volume) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "ClienteMaiorVolume{" +
                "id=" + id +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", volume=" + volume +
                '}';
    }
}
