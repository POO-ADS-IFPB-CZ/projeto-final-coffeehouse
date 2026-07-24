package dao;

import model.Produto;
import util.ArquivoUtil;
import java.util.ArrayList;

public class ProdutoDAO {
    private static final String ARQUIVO = "dados/produtos.dat";
    private ArrayList<Produto> produtos;

    @SuppressWarnings("unchecked")
    public ProdutoDAO() {
        Object dados = ArquivoUtil.carregar(ARQUIVO);
        if (dados != null) {
            this.produtos = (ArrayList<Produto>) dados;
        } else {
            this.produtos = new ArrayList<>();
        }
    }

    private void salvar() {
        ArquivoUtil.salvar(produtos, ARQUIVO);
    }

    public void inserir(Produto produto) {
        produtos.add(produto);
        salvar();
    }

    public Produto buscar(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean atualizar(Produto produto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == produto.getId()) {
                produtos.set(i, produto);
                salvar();
                return true;
            }
        }
        return false;
    }

    public boolean excluir(int id) {
        boolean removido = produtos.removeIf(p -> p.getId() == id);
        if (removido) {
            salvar();
        }
        return removido;
    }

    public ArrayList<Produto> listar() {
    return produtos;
}

}