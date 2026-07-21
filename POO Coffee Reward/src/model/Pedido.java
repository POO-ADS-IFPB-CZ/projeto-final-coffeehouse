package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.io.Serializable;

public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private Cliente cliente;
    private Funcionario funcionario;
    private LocalDateTime dataHora;
    private double valorTotal;
    private ArrayList<ItemPedido> itens;
    private String status;

    public Pedido() {
        itens =  new ArrayList<>();
    }

    public Pedido(int id, Cliente cliente, Funcionario funcionario, LocalDateTime dataHora, String status) {

        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.dataHora = dataHora;
        this.status = status;
        this.itens = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime datahora) {
        this.dataHora = datahora;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemPedido> itens) {
        this.itens = itens;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " - " + cliente.getNome();
    }
    
}