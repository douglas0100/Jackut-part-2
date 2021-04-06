public class Chat {
    private int id;
    private String login;
    private String mensagem;

    public Chat(){// Construtor padrao necessario pra criacao do filho?

    }
    
    public Chat(String login, String mensagem){
        this.login = login;
        this.mensagem = mensagem;
        this.id = 0;
    }
  

    
    public int getId(){
        return this.id;
    }

    public String getLogin(){
        return this.login;
    }

    public void setLogin(String login){
        this.login = login;
    }
    
    @Override
    public String toString(){
        return String.format("------------- %n"
                + "Mensagem de %s %n"
                + "Conteudo: %s %n"
                + "------------- %n", this.login,
                this.mensagem);
    }
    
    
}
