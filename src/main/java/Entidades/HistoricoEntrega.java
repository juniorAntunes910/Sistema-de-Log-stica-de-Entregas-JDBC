package Entidades;

import java.time.LocalDate;

public class HistoricoEntrega {
    private int id;
    private int entrega_id;
    private LocalDate dataEvento;
    private String descricao;

    public HistoricoEntrega(int id, int entrega_id, LocalDate dataEvento, String descricao) {
        this.id = id;
        this.entrega_id = entrega_id;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
    }

    public HistoricoEntrega(int entrega_id, LocalDate dataEvento, String descricao) {
        this.entrega_id = entrega_id;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntrega_id() {
        return entrega_id;
    }

    public void setEntrega_id(int entrega_id) {
        this.entrega_id = entrega_id;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "historicoEntrega{" +
                "id=" + id +
                ", entrega_id=" + entrega_id +
                ", dataEvento='" + dataEvento + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
