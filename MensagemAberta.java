public class MensagemAberta extends MensagemSimples {
    public MensagemAberta(MensagemSecreta mensagem){
        super(mensagem.getLogin(), mensagem.getDescricao());
    }
}
