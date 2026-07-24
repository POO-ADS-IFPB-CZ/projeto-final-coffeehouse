package view;

import dao.RecompensaDAO;
import model.Recompensa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaRecompensas extends JFrame {

    private JLabel lbNome;
    private JLabel lbDescricao;
    private JLabel lbPontos;

    private JTextField txtNome;
    private JTextField txtDescricao;
    private JTextField txtPontos;

    private JButton btCadastrar;
    private JButton btEditar;
    private JButton btExcluir;
    private JButton btLimpar;

    private JTable tabela;
    private DefaultTableModel modelo;

    private RecompensaDAO recompensaDAO;

    public TelaRecompensas(){

        recompensaDAO = new RecompensaDAO();

        setTitle("Cadastro de Recompensas");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        lbNome = new JLabel("Nome:");
        lbDescricao = new JLabel("Descrição:");
        lbPontos = new JLabel("Pontos:");

        txtNome = new JTextField();
        txtDescricao = new JTextField();
        txtPontos = new JTextField();

        btCadastrar = new JButton("Cadastrar");
        btEditar = new JButton("Editar");
        btExcluir = new JButton("Excluir");
        btLimpar = new JButton("Limpar");

        lbNome.setBounds(30,30,100,25);
        lbDescricao.setBounds(30,70,100,25);
        lbPontos.setBounds(30,110,100,25);

        txtNome.setBounds(130,30,250,25);
        txtDescricao.setBounds(130,70,250,25);
        txtPontos.setBounds(130,110,120,25);

        btCadastrar.setBounds(450,30,120,30);
        btEditar.setBounds(450,70,120,30);
        btExcluir.setBounds(450,110,120,30);
        btLimpar.setBounds(450,150,120,30);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Descrição");
        modelo.addColumn("Pontos");

        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(30,230,720,300);

        add(lbNome);
        add(lbDescricao);
        add(lbPontos);

        add(txtNome);
        add(txtDescricao);
        add(txtPontos);

        add(btCadastrar);
        add(btEditar);
        add(btExcluir);
        add(btLimpar);

        add(scroll);

        btCadastrar.addActionListener(e -> {

            Recompensa recompensa = new Recompensa();

            recompensa.setId(recompensaDAO.listar().size() + 1);
            recompensa.setNome(txtNome.getText());
            recompensa.setDescricao(txtDescricao.getText());
            recompensa.setPontosNecessarios(Integer.parseInt(txtPontos.getText()));

            recompensaDAO.inserir(recompensa);

            atualizarTabela();
            limparCampos();

        });

        atualizarTabela();

    }

    private void atualizarTabela(){

        modelo.setRowCount(0);

        for(Recompensa recompensa : recompensaDAO.listar()){

            modelo.addRow(new Object[]{

                recompensa.getId(),
                recompensa.getNome(),
                recompensa.getDescricao(),
                recompensa.getPontosNecessarios()

            });

        }

    }

    private void limparCampos(){

        txtNome.setText("");
        txtDescricao.setText("");
        txtPontos.setText("");

    }

}