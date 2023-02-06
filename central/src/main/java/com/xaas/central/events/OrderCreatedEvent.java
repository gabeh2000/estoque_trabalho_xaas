package com.xaas.central.event;


public class OrderCreatedEvent {
    private String id;
    private int qtd;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(String id, int qtd) {
        this.id = id;
        this.qtd = qtd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQtd() {
        return qtd;
    }

    public void setNome(int qtd) {
        this.qtd = qtd;
    }
}
