package com.service.pedidos.model;

import java.util.ArrayList;

public class No {
    private int ID;
    private boolean IsAlive;
    private ArrayList<No> _nos;
    private boolean IsCoordenator;


    public No(Integer id) {
        this.ID = id;
    }
    public void aplicarNos(ArrayList<No> nos) {
        this._nos = nos;
    }

    public void IniciarEleicao() {

    }

    public void  EscolhaEleicao(int IdNo) {
        System.out.println("Recebendo ID do nó {" + IdNo + "}");


    }

    public void NovoCoordenador(int newCoordenador) {
        this.IsCoordenator = true;
    }
}
