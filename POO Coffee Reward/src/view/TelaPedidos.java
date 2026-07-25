package view;

import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.PedidoDAO;
import dao.ProdutoDAO;

import model.Cliente;
import model.Funcionario;
import model.ItemPedido;
import model.Pedido;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.time.LocalDateTime;

public class TelaPedidos extends JFrame {

    private JLabel lbCliente;
    private JLabel lbFuncionario;
    private JLabel lbProduto;
    private JLabel lbQuantidade;
    private JLabel lbValorTotal;

    private JComboBox<Cliente> cbCliente;
    private JComboBox<Funcionario> cbFuncionario;
    private JComboBox<Produto> cbProduto;

    private JTextField txtQuantidade;

    private JButton btAdicionar;
    private JButton btFinalizar;
    private JButton btCancelar;

    private JTable tabela;
    private DefaultTableModel modelo;

    private ClienteDAO clienteDAO;
    private ProdutoDAO produtoDAO;
    private FuncionarioDAO funcionarioDAO;
    private PedidoDAO pedidoDAO;

    private Pedido pedidoAtual;

    private double valorTotal;

    public TelaPedidos() {

        clienteDAO = new ClienteDAO();
        produtoDAO = new ProdutoDAO();
        funcionarioDAO = new FuncionarioDAO();
        pedidoDAO = new PedidoDAO();

        pedidoAtual = new Pedido();

        valorTotal = 0;

        setTitle("Pedidos");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        lbCliente = new JLabel("Cliente:");
        lbFuncionario = new JLabel("Funcionário:");
        lbProduto = new JLabel("Produto:");
        lbQuantidade = new JLabel("Quantidade:");
        lbValorTotal = new JLabel("Valor Total: R$ 0,00");

        cbCliente = new JComboBox<>();
        cbFuncionario = new JComboBox<>();
        cbProduto = new JComboBox<>();

        txtQuantidade = new JTextField();

        btAdicionar = new JButton("Adicionar Produto");
        btFinalizar = new JButton("Finalizar Pedido");
        btCancelar = new JButton("Cancelar");

        modelo = new DefaultTableModel();

        modelo.addColumn("Produto");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Preço");
        modelo.addColumn("Subtotal");

        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);

        lbCliente.setBounds(30,30,100,25);
        cbCliente.setBounds(140,30,220,25);

        lbFuncionario.setBounds(30,70,100,25);
        cbFuncionario.setBounds(140,70,220,25);

        lbProduto.setBounds(30,110,100,25);
        cbProduto.setBounds(140,110,220,25);

        lbQuantidade.setBounds(30,150,100,25);
        txtQuantidade.setBounds(140,150,80,25);

        btAdicionar.setBounds(400,110,170,30);

        scroll.setBounds(30,210,820,250);

        lbValorTotal.setBounds(30,480,250,30);

        btFinalizar.setBounds(550,480,140,30);
        btCancelar.setBounds(710,480,140,30);

        add(lbCliente);
        add(cbCliente);

        add(lbFuncionario);
        add(cbFuncionario);

        add(lbProduto);
        add(cbProduto);

        add(lbQuantidade);
        add(txtQuantidade);

        add(btAdicionar);

        add(scroll);

        add(lbValorTotal);

        add(btFinalizar);
        add(btCancelar);

        for(Cliente cliente : clienteDAO.listar()){
            cbCliente.addItem(cliente);
        }

        for(Funcionario funcionario : funcionarioDAO.listar()){
            cbFuncionario.addItem(funcionario);
        }


        for(Produto produto : produtoDAO.listar()){
            cbProduto.addItem(produto);
        }

        btAdicionar.addActionListener(e -> adicionarProduto());

        btFinalizar.addActionListener(e -> finalizarPedido());

    }

    private void adicionarProduto(){

    if(cbProduto.getSelectedItem() == null){
        JOptionPane.showMessageDialog(this,
                "Selecione um produto.");
        return;
    }

    if(txtQuantidade.getText().isEmpty()){
        JOptionPane.showMessageDialog(this,
                "Informe a quantidade.");
        return;
    }

    Produto produto = (Produto) cbProduto.getSelectedItem();

    int quantidade = Integer.parseInt(txtQuantidade.getText());

    if(quantidade <= 0){
        JOptionPane.showMessageDialog(this,
                "Quantidade inválida.");
        return;
    }

    if(produto.getEstoque() < quantidade){
        JOptionPane.showMessageDialog(this,
                "Estoque insuficiente.");
        return;
    }

    ItemPedido item = new ItemPedido();

    item.setId(pedidoAtual.getItens().size() + 1);
    item.setProduto(produto);
    item.setQuantidade(quantidade);
    item.setPrecoUnitario(produto.getPreco());

    pedidoAtual.adicionarItem(item);

    atualizarTabela();

    calcularTotal();

    txtQuantidade.setText("");

}

    private void atualizarTabela(){

    modelo.setRowCount(0);

    for(ItemPedido item : pedidoAtual.getItens()){

        modelo.addRow(new Object[]{

                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                item.getSubtotal()

        });

    }

}

private void calcularTotal(){

    valorTotal = 0;

    for(ItemPedido item : pedidoAtual.getItens()){

        valorTotal += item.getSubtotal();

    }

    pedidoAtual.setValorTotal(valorTotal);

    lbValorTotal.setText(
            "Valor Total: R$ " +
            String.format("%.2f", valorTotal)
    );

}

private void finalizarPedido(){

    if(pedidoAtual.getItens().isEmpty()){

        JOptionPane.showMessageDialog(this,
                "Adicione pelo menos um produto.");

        return;
    }

    Cliente cliente = (Cliente) cbCliente.getSelectedItem();
    Funcionario funcionario = (Funcionario) cbFuncionario.getSelectedItem();

    pedidoAtual.setId(pedidoDAO.listar().size() + 1);
    pedidoAtual.setCliente(cliente);
    pedidoAtual.setFuncionario(funcionario);
    pedidoAtual.setDataHora(LocalDateTime.now());
    pedidoAtual.setStatus("Finalizado");

    pedidoDAO.inserir(pedidoAtual);

    atualizarPontos(cliente);

    atualizarEstoque();

    JOptionPane.showMessageDialog(this,
            "Pedido realizado com sucesso!");

    limparPedido();

}

private void atualizarPontos(Cliente cliente){

    int pontos = cliente.getPontos();

    for(ItemPedido item : pedidoAtual.getItens()){

        pontos += item.getProduto().getPontosGerados()
                * item.getQuantidade();

    }

    cliente.setPontos(pontos);

    clienteDAO.atualizar(cliente);

}

private void atualizarEstoque(){

    for(ItemPedido item : pedidoAtual.getItens()){

        Produto produto = item.getProduto();

        produto.setEstoque(
                produto.getEstoque()
                - item.getQuantidade());

        produtoDAO.atualizar(produto);

    }

}

private void limparPedido(){

    pedidoAtual = new Pedido();

    valorTotal = 0;

    modelo.setRowCount(0);

    lbValorTotal.setText("Valor Total: R$ 0,00");

    txtQuantidade.setText("");

}

}