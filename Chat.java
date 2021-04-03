public class Chat {
    private int id;
    private String nome;
    private String mensagem;

    public Chat(String nome, String mensagem){
        this.nome = nome;
        this.mensagem = mensagem;
        this.id = 0;
    }
    
    public int getId(){
        return this.id;
    }
    
    @Override
    public String toString(){
        return String.format("------------- %n"
                + "Mensagem de %s %n"
                + "Conteudo: %s %n"
                + "------------- %n", this.nome,
                this.mensagem);
    }
    
    
}
