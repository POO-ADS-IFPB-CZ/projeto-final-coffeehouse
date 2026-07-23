package dao;

import model.Recompensa;
import util.ArquivoUtil;
import java.util.ArrayList;

public class RecompensaDAO {
    private static final String ARQUIVO = "dados/recompensas.dat";
    private ArrayList<Recompensa> recompensas;

    @SuppressWarnings("unchecked")
    public RecompensaDAO() {
        Object dados = ArquivoUtil.carregar(ARQUIVO);
        if (dados != null) {
            this.recompensas = (ArrayList<Recompensa>) dados;
        } else {
            this.recompensas = new ArrayList<>();
        }
    }

    private void salvar() {
        ArquivoUtil.salvar(recompensas, ARQUIVO);
    }

    public void inserir(Recompensa recompensa) {
        recompensas.add(recompensa);
        salvar();
    }

    public Recompensa buscar(int id) {
        for (Recompensa r : recompensas) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public boolean atualizar(Recompensa recompensa) {
        for (int i = 0; i < recompensas.size(); i++) {
            if (recompensas.get(i).getId() == recompensa.getId()) {
                recompensas.set(i, recompensa);
                salvar();
                return true;
            }
        }
        return false;
    }

    public boolean excluir(int id) {
        boolean removido = recompensas.removeIf(r -> r.getId() == id);
        if (removido) {
            salvar();
        }
        return removido;
    }
}