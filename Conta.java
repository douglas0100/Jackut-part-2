public class Conta {
    private String nome;
    private String login;
    private String senha;

    public Conta(String nome, String login, String senha){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getLogin() { // Único que não alterará.
        return login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Nome: "     + this.nome +
               "\nLogin: "    + this.login;
    }

}
