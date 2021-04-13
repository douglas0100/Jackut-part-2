public class Mensagem {
    private int id;
    private String login;
    private String descricao;

    public Mensagem(){
        // Construtor padrao necessario pra criacao do filho?

    }
    
    public Mensagem(String login, String descricao){
        this.login = login;
        this.descricao = descricao;
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
                this.descricao);
    }
    
    
}
