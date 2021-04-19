public class MensagemSecreta extends MensagemSimples {
    private String senha;

    public MensagemSecreta(String senha) {
        super();
        this.senha = senha;
    }

    public String getSenha(){
        return this.senha;
    }

    public static boolean validaSenha(String senha, MensagemSecreta mensagem) {
        for (int i = 0; i < senha.length(); i = i + 1) {
            if (senha.charAt(i) != mensagem.getSenha().charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
