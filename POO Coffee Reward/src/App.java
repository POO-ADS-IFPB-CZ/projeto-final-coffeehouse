import model.*;

import javax.swing.SwingUtilities;

import dao.ClienteDAO;
import view.TelaPrincipal;

public class App{

    public static void main(String[] args) throws Exception {

         SwingUtilities.invokeLater(() -> {

            new TelaPrincipal().setVisible(true);

        });

        ClienteDAO clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();

        cliente.setId(1);

        cliente.setNome("Adonay");

        cliente.setCpf("100");

        cliente.setTelefone("0930");

        cliente.setEmail("Adonaydva@email.com");

        cliente.setPontos(45);

        clienteDAO.inserir(cliente);

        for(Cliente c : clienteDAO.listar()){

            System.out.println(c);

        }

    }
    
}