public abstract class Mensagem  {
    private String login;
    private String descricao;

    public Mensagem() {
        this.login = login;
        this.descricao = descricao;
    }
    

    public String getLogin(){
        return this.login;
    }

    protected void setLogin(String login){
        this.login = login;
    }
    
    @Override
    public String toString(){
        return String.format("------------- %n"
                + "Mensagem de %s %n"
                + "Conteudo: %s %n"
                + "------------- %n", this.login,
                this.descricao);
    }
    
    
}
