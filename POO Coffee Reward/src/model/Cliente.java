package model;

public class Cliente extends Pessoa {

    private String email;
    private int pontos;

    public Cliente() {

    }
    
    public Cliente(int id, String nome, String cpf, String telefone, String email, int pontos) {

        super(id, nome, cpf, telefone);

        this.email = email;
        this.pontos = pontos;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    @Override
    public String toString() {
        return getNome() + " - Pontos: " + pontos;
    }

}