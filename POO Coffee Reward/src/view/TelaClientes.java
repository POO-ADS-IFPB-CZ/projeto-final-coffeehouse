package view;

import dao.ClienteDAO;
import model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaClientes extends JFrame {

    private JLabel lbNome;
    private JLabel lbCpf;
    private JLabel lbTelefone;
    private JLabel lbEmail;
    private JLabel lbPontos;

    
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JTextField txtPontos;

    
    private JButton btCadastrar;
    private JButton btEditar;
    private JButton btExcluir;
    private JButton btLimpar;

    
    private JTable tabela;
    private DefaultTableModel modelo;

    private ClienteDAO clienteDAO;

    public TelaClientes() {

        clienteDAO = new ClienteDAO();

        setTitle("Cadastro de Clientes");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        lbNome = new JLabel("Nome:");
        lbCpf = new JLabel("CPF:");
        lbTelefone = new JLabel("Telefone:");
        lbEmail = new JLabel("Email:");
        lbPontos = new JLabel("Pontos:");

        txtNome = new JTextField();
        txtCpf = new JTextField();
        txtTelefone = new JTextField();
        txtEmail = new JTextField();
        txtPontos = new JTextField();

        btCadastrar = new JButton("Cadastrar");
        btEditar = new JButton("Editar");
        btExcluir = new JButton("Excluir");
        btLimpar = new JButton("Limpar");

        lbNome.setBounds(30, 30, 80, 25);
        lbCpf.setBounds(30, 70, 80, 25);
        lbTelefone.setBounds(30, 110, 80, 25);
        lbEmail.setBounds(30, 150, 80, 25);
        lbPontos.setBounds(30, 190, 80, 25);

        txtNome.setBounds(120, 30, 250, 25);
        txtCpf.setBounds(120, 70, 250, 25);
        txtTelefone.setBounds(120, 110, 250, 25);
        txtEmail.setBounds(120, 150, 250, 25);
        txtPontos.setBounds(120, 190, 100, 25);

        btCadastrar.setBounds(420, 30, 120, 30);
        btEditar.setBounds(420, 70, 120, 30);
        btExcluir.setBounds(420, 110, 120, 30);
        btLimpar.setBounds(420, 150, 120, 30);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Pontos");

        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);

        scroll.setBounds(30, 260, 720, 250);

        add(lbNome);
        add(lbCpf);
        add(lbTelefone);
        add(lbEmail);
        add(lbPontos);

        add(txtNome);
        add(txtCpf);
        add(txtTelefone);
        add(txtEmail);
        add(txtPontos);

        add(btCadastrar);
        add(btEditar);
        add(btExcluir);
        add(btLimpar);

        add(scroll);

        atualizarTabela();

    }

    private void atualizarTabela() {

    modelo.setRowCount(0);

    for (Cliente cliente : clienteDAO.listar()) {

        modelo.addRow(new Object[]{

            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getPontos()

        });

    }

}

}