package view;

import dao.FuncionarioDAO;
import model.Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaFuncionarios extends JFrame {

    private JLabel lbNome;
    private JLabel lbCpf;
    private JLabel lbTelefone;
    private JLabel lbCargo;
    private JLabel lbSalario;
    private JLabel lbLogin;
    private JLabel lbSenha;

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtTelefone;
    private JTextField txtCargo;
    private JTextField txtSalario;
    private JTextField txtLogin;
    private JTextField txtSenha;

    private JButton btCadastrar;
    private JButton btEditar;
    private JButton btExcluir;
    private JButton btLimpar;

    private JTable tabela;
    private DefaultTableModel modelo;

    private FuncionarioDAO funcionarioDAO;

    public TelaFuncionarios() {

        funcionarioDAO = new FuncionarioDAO();

        setTitle("Cadastro de Funcionários");
        setSize(850,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        lbNome = new JLabel("Nome:");
        lbCpf = new JLabel("CPF:");
        lbTelefone = new JLabel("Telefone:");
        lbCargo = new JLabel("Cargo:");
        lbSalario = new JLabel("Salário:");
        lbLogin = new JLabel("Login:");
        lbSenha = new JLabel("Senha:");

        txtNome = new JTextField();
        txtCpf = new JTextField();
        txtTelefone = new JTextField();
        txtCargo = new JTextField();
        txtSalario = new JTextField();
        txtLogin = new JTextField();
        txtSenha = new JTextField();

        btCadastrar = new JButton("Cadastrar");
        btEditar = new JButton("Editar");
        btExcluir = new JButton("Excluir");
        btLimpar = new JButton("Limpar");

        lbNome.setBounds(30,30,100,25);
        lbCpf.setBounds(30,70,100,25);
        lbTelefone.setBounds(30,110,100,25);
        lbCargo.setBounds(30,150,100,25);
        lbSalario.setBounds(30,190,100,25);
        lbLogin.setBounds(30,230,100,25);
        lbSenha.setBounds(30,270,100,25);

        txtNome.setBounds(130,30,250,25);
        txtCpf.setBounds(130,70,250,25);
        txtTelefone.setBounds(130,110,250,25);
        txtCargo.setBounds(130,150,250,25);
        txtSalario.setBounds(130,190,120,25);
        txtLogin.setBounds(130,230,250,25);
        txtSenha.setBounds(130,270,250,25);

        btCadastrar.setBounds(450,30,120,30);
        btEditar.setBounds(450,70,120,30);
        btExcluir.setBounds(450,110,120,30);
        btLimpar.setBounds(450,150,120,30);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Cargo");
        modelo.addColumn("Salário");

        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(30,330,760,200);

        add(lbNome);
        add(lbCpf);
        add(lbTelefone);
        add(lbCargo);
        add(lbSalario);
        add(lbLogin);
        add(lbSenha);

        add(txtNome);
        add(txtCpf);
        add(txtTelefone);
        add(txtCargo);
        add(txtSalario);
        add(txtLogin);
        add(txtSenha);

        add(btCadastrar);
        add(btEditar);
        add(btExcluir);
        add(btLimpar);

        add(scroll);

        btCadastrar.addActionListener(e -> {

            Funcionario funcionario = new Funcionario();

            funcionario.setId(funcionarioDAO.listar().size() + 1);
            funcionario.setNome(txtNome.getText());
            funcionario.setCpf(txtCpf.getText());
            funcionario.setTelefone(txtTelefone.getText());
            funcionario.setCargo(txtCargo.getText());
            funcionario.setSalario(Double.parseDouble(txtSalario.getText()));
            funcionario.setLogin(txtLogin.getText());
            funcionario.setSenha(txtSenha.getText());

            funcionarioDAO.inserir(funcionario);

            atualizarTabela();
            limparCampos();

        });

        atualizarTabela();

    }

    private void atualizarTabela(){

        modelo.setRowCount(0);

        for(Funcionario funcionario : funcionarioDAO.listar()){

            modelo.addRow(new Object[]{

                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCargo(),
                funcionario.getSalario()

            });

        }

    }

    private void limparCampos(){

        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtCargo.setText("");
        txtSalario.setText("");
        txtLogin.setText("");
        txtSenha.setText("");

    }

}