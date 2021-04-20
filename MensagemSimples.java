public class MensagemSimples extends Mensagem {

    public MensagemSimples(String login, String descricao){
        super(login, descricao);
    }

    public MensagemSimples(MensagemSecreta mensagem){
        super(mensagem.getLogin(), mensagem.getDescricao());
    }

}