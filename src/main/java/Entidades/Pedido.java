package Entidades;

public class Pedido {
    private int id;
    private int cliente_id;
    private String data_pedido;
    private double volumeM3;
    private double pesoKg;
    private String status;

    public Pedido(int id, int cliente_id, String data_pedido, double volumeM3, double pesoKg, String status) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.data_pedido = data_pedido;
        this.volumeM3 = volumeM3;
        this.pesoKg = pesoKg;
        this.status = status;
    }

    public Pedido(int cliente_id, String data_pedido, double volumeM3, double pesoKg, String status) {
        this.cliente_id = cliente_id;
        this.data_pedido = data_pedido;
        this.volumeM3 = volumeM3;
        this.pesoKg = pesoKg;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(String data_pedido) {
        this.data_pedido = data_pedido;
    }

    public double getVolumeM3() {
        return volumeM3;
    }

    public void setVolumeM3(double volumeM3) {
        this.volumeM3 = volumeM3;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "pedido{" +
                "id=" + id +
                ", cliente_id=" + cliente_id +
                ", data_pedido='" + data_pedido + '\'' +
                ", volumeM3=" + volumeM3 +
                ", pesoKg=" + pesoKg +
                ", status='" + status + '\'' +
                '}';
    }
}
