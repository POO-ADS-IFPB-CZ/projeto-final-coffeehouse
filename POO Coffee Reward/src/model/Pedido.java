package model;

import java.time.LocalDateTime;

public class Pedido {
    private int id;
    private Cliente cliente;
    private Funcionario funcionario;
    private LocalDateTime dataHora;
    private float valorTotal;
    private List<ItemPedido> itens;
    private String status;
    
}