package view;

import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaProdutos extends JFrame {

    // Labels
    private JLabel lbNome;
    private JLabel lbCategoria;
    private JLabel lbPreco;
    private JLabel lbEstoque;

    // Campos de texto
    private JTextField txtNome;
    private JTextField txtCategoria;
    private JTextField txtPreco;
    private JTextField txtEstoque;

    // Botões
    private JButton btCadastrar;
    private JButton btEditar;
    private JButton btExcluir;
    private JButton btLimpar;

    // Tabela
    private JTable tabela;
    private DefaultTableModel modelo;

    // DAO
    private ProdutoDAO produtoDAO;

    public TelaProdutos() {

        produtoDAO = new ProdutoDAO();

        setTitle("Cadastro de Produtos");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        // Labels
        lbNome = new JLabel("Nome:");
        lbCategoria = new JLabel("Categoria:");
        lbPreco = new JLabel("Preço:");
        lbEstoque = new JLabel("Estoque:");

        // Campos
        txtNome = new JTextField();
        txtCategoria = new JTextField();
        txtPreco = new JTextField();
        txtEstoque = new JTextField();

        // Botões
        btCadastrar = new JButton("Cadastrar");
        btEditar = new JButton("Editar");
        btExcluir = new JButton("Excluir");
        btLimpar = new JButton("Limpar");

        // Posição dos Labels
        lbNome.setBounds(30,30,100,25);
        lbCategoria.setBounds(30,70,100,25);
        lbPreco.setBounds(30,110,100,25);
        lbEstoque.setBounds(30,150,100,25);

        // Posição dos Campos
        txtNome.setBounds(130,30,250,25);
        txtCategoria.setBounds(130,70,250,25);
        txtPreco.setBounds(130,110,120,25);
        txtEstoque.setBounds(130,150,120,25);

        // Posição dos Botões
        btCadastrar.setBounds(450,30,120,30);
        btEditar.setBounds(450,70,120,30);
        btExcluir.setBounds(450,110,120,30);
        btLimpar.setBounds(450,150,120,30);

        // Modelo da tabela
        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Categoria");
        modelo.addColumn("Preço");
        modelo.addColumn("Estoque");

        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);

        scroll.setBounds(30,230,720,300);

        // Adicionando componentes
        add(lbNome);
        add(lbCategoria);
        add(lbPreco);
        add(lbEstoque);

        add(txtNome);
        add(txtCategoria);
        add(txtPreco);
        add(txtEstoque);

        add(btCadastrar);
        add(btEditar);
        add(btExcluir);
        add(btLimpar);

        add(scroll);

    btCadastrar.addActionListener(e -> {

        Produto produto = new Produto();

        produto.setId(produtoDAO.listar().size() + 1);
        produto.setNome(txtNome.getText());
        produto.setCategoria(txtCategoria.getText());
        produto.setPreco(Double.parseDouble(txtPreco.getText()));
        produto.setEstoque(Integer.parseInt(txtEstoque.getText()));

        produtoDAO.inserir(produto);

        atualizarTabela();

        limparCampos();

    });
}
    private void atualizarTabela(){

        modelo.setRowCount(0);

        for(Produto produto : produtoDAO.listar()){

            modelo.addRow(new Object[]{

                produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getPreco(),
                produto.getEstoque()

            });

        }

    }

    private void limparCampos() {

    txtNome.setText("");
    txtCategoria.setText("");
    txtPreco.setText("");
    txtEstoque.setText("");

    }

}
