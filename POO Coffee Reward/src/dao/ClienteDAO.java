package dao;

import model.Cliente;
import util.ArquivoUtil;
import java.util.ArrayList;

public class ClienteDAO {
    private static final String ARQUIVO = "dados/clientes.dat";
    private ArrayList<Cliente> clientes;

    @SuppressWarnings("unchecked")
    public ClienteDAO() {
        Object dados = ArquivoUtil.carregar(ARQUIVO);
        if (dados != null) {
            this.clientes = (ArrayList<Cliente>) dados;
        } else {
            this.clientes = new ArrayList<>();
        }
    }

    private void salvar() {
        ArquivoUtil.salvar(clientes, ARQUIVO);
    }

    public void inserir(Cliente cliente) {
        clientes.add(cliente);
        salvar();
    }

    public Cliente buscar(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public boolean atualizar(Cliente cliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == cliente.getId()) {
                clientes.set(i, cliente);
                salvar();
                return true;
            }
        }
        return false;
    }

    public boolean excluir(int id) {
        boolean removido = clientes.removeIf(c -> c.getId() == id);
        if (removido) {
            salvar();
        }
        return removido;
    }

    public ArrayList<Cliente> listar() {
    return clientes;
    }
}