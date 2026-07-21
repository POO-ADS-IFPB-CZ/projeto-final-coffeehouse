package model;

public class Funcionario extends Pessoa {
    private String cargo;
    private double salario;
    private String login;
    private String senha;

    public Funcionario() {

    }

    public Funcionario(int id, String nome, String cpf, String telefone, String cargo, double salario, String login, String senha) {

        super(id, nome, cpf, telefone);

        this.cargo = cargo;
        this.salario = salario;
        this.login = login;
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public voi setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return getNome() + " - " + cargo;
    }
    
}