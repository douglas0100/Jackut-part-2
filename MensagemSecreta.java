public class MensagemSecreta extends MensagemSimples {
    private String senha;

    
    public MensagemSecreta(String login, String descricao, String senha) {
        super(login, descricao);
        this.senha = senha;
    }

    public String getSenha(){
        return this.senha;
    }

    public boolean validaSenha(String senha) {
        for (int i = 0; i < this.senha.length(); i = i + 1) {
            if (senha.charAt(i) != this.senha.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        return String.format("------------- %n"
                + "Mensagem de %s %n"
                + "Conteudo: ****** %n"
                + "------------- %n", this.getLogin());
    }

}
