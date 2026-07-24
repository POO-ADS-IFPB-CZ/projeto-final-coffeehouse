package dao;

import model.Funcionario;
import util.ArquivoUtil;
import java.util.ArrayList;

public class FuncionarioDAO {
    private static final String ARQUIVO = "dados/funcionarios.dat";
    private ArrayList<Funcionario> funcionarios;

    @SuppressWarnings("unchecked")
    public FuncionarioDAO() {
        Object dados = ArquivoUtil.carregar(ARQUIVO);
        if (dados != null) {
            this.funcionarios = (ArrayList<Funcionario>) dados;
        } else {
            this.funcionarios = new ArrayList<>();
        }
    }

    private void salvar() {
        ArquivoUtil.salvar(funcionarios, ARQUIVO);
    }

    public void inserir(Funcionario funcionario) {
        funcionarios.add(funcionario);
        salvar();
    }

    public Funcionario buscar(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    public boolean atualizar(Funcionario funcionario) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getId() == funcionario.getId()) {
                funcionarios.set(i, funcionario);
                salvar();
                return true;
            }
        }
        return false;
    }

    public boolean excluir(int id) {
        boolean removido = funcionarios.removeIf(f -> f.getId() == id);
        if (removido) {
            salvar();
        }
        return removido;
    }

    public ArrayList<Funcionario> listar(){
        return funcionarios;
    }
}