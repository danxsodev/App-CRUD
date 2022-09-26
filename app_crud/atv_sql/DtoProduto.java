package com.example.atv_sql;

import androidx.annotation.NonNull;

public class DtoProduto {
    private int id;
    public String nome,fabricante;
    public double preco;

    public DtoProduto(String nome, String fabricante, double preco) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.preco = preco;
    }

    public DtoProduto(){

    }
    @NonNull
    @Override
    public String toString() {
        return nome + " - " + fabricante + " - " + preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
