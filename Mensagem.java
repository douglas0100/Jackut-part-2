public abstract class Mensagem {
    private String login;
    private String descricao;

    public Mensagem(String login, String descricao) {
        this.login = login;
        this.descricao = descricao;
    }
    
    
    public String getLogin(){
        return this.login;
    }

    public String getDescricao(){
        return this.descricao;
    }

    protected void setLogin(String login){
        this.login = login;
    }
    
    @Override
    public String toString(){
        return String.format("Mensagem de %s %n"
                + "Conteudo: %s %n"
                + "------------- %n", this.login,
                this.descricao);
    }
    
    
}
