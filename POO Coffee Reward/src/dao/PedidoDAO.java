package dao;

import model.Pedido;
import util.ArquivoUtil;
import java.util.ArrayList;

public class PedidoDAO {
    private static final String ARQUIVO = "dados/pedidos.dat";
    private ArrayList<Pedido> pedidos;

    @SuppressWarnings("unchecked")
    public PedidoDAO() {
        Object dados = ArquivoUtil.carregar(ARQUIVO);
        if (dados != null) {
            this.pedidos = (ArrayList<Pedido>) dados;
        } else {
            this.pedidos = new ArrayList<>();
        }
    }

    private void salvar() {
        ArquivoUtil.salvar(pedidos, ARQUIVO);
    }

    public void inserir(Pedido pedido) {
        pedidos.add(pedido);
        salvar();
    }

    public Pedido buscar(int id) {
        for (Pedido pe : pedidos) {
            if (pe.getId() == id) {
                return pe;
            }
        }
        return null;
    }

    public boolean atualizar(Pedido pedido) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId() == pedido.getId()) {
                pedidos.set(i, pedido);
                salvar();
                return true;
            }
        }
        return false;
    }

    public boolean excluir(int id) {
        boolean removido = pedidos.removeIf(pe -> pe.getId() == id);
        if (removido) {
            salvar();
        }
        return removido;
    }
}