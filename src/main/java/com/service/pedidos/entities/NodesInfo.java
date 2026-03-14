package com.service.pedidos.entities;

public class NodesInfo {

    private int id;
    private String url;

    public NodesInfo(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
