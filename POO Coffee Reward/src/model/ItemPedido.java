package model;

import java.io.Serializable;

public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private Produto produto;
    private int quantidade;
    private double precoUnitario;
    private double subtotal;
    
    public ItemPedido() {

    }

    public ItemPedido(int id, Produto produto, int quantidade, double precoUnitario) {

        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = quantidade * subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.subtotal = quantidade * precoUnitario;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
        this.subtotal = quantidade * precoUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return produto.getNome() + " x" + quantidade + " = R$ " + subtotal;
    }
}