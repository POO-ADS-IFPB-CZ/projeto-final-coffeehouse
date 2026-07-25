package view;

import javax.swing.*;

public class TelaPrincipal extends JFrame{

    private JButton btClientes;
    private JButton btProdutos;
    private JButton btFuncionarios;
    private JButton btPedidos;
    private JButton btRecompensas;
    private JButton btSair;

    public TelaPrincipal() {

        setTitle("Coffee Reward");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        btClientes = new JButton("Clientes");
        btProdutos = new JButton("Produtos");
        btFuncionarios = new JButton("Funcionários");
        btPedidos = new JButton("Pedidos");
        btRecompensas = new JButton("Recompensas");
        btSair = new JButton("Sair");

        btFuncionarios.addActionListener(e -> {
            new TelaFuncionarios().setVisible(true);
        });

        btRecompensas.addActionListener(e -> {
            new TelaRecompensas().setVisible(true);
        });

        btClientes.addActionListener(e -> {
            new TelaClientes().setVisible(true);
        });

        btPedidos.addActionListener(e -> {
            new TelaPedidos().setVisible(true);
        });

        btProdutos.addActionListener(e -> {
            new TelaProdutos().setVisible(true);
        });

        btClientes.setBounds(280, 70, 220, 40);
        btProdutos.setBounds(280, 130, 220, 40);
        btFuncionarios.setBounds(280, 190, 220, 40);
        btPedidos.setBounds(280, 250, 220, 40);
        btRecompensas.setBounds(280, 310, 220, 40);
        btSair.setBounds(280, 370, 220, 40);

        add(btClientes);
        add(btProdutos);
        add(btFuncionarios);
        add(btPedidos);
        add(btRecompensas);
        add(btSair);
     }
    
}
