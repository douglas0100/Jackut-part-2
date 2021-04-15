public class Conta {

    private static final int TAM = 100; // Constante do tamanho da lista de contas do Jackut

    private String nome;
    private String login;
    private String senha;
    private Chat[] caixaDeEntrada;

    private String[] convitesEnviados;
    private String[] convitesRecebidos;

    private int indiceDeConvitesEnviados;
    private int indiceDeConvitesRecebidos;

    private Amigo[] listaDeAmigos;
    private int indiceDeAmigos;

    private int qtdMensagens;

    
    // atributos abaixo são de perfil, deviam ser movidas para outra classe depois
    // talvez
    // ---------------------------------------
    // Informações básicas
    private int diaNascimento;
    private String mesNascimento;
    private int anoNascimento;
    private String genero; // sexo da pessoa

    // Informações de status atual
    private String cidadeAtual;
    private String estadoCivil;

    // Informações de estudo/emprego
    private String instituicaoEnsinoMedio;
    private String instituicaoFaculdade;
    private String ocupacaoTrabalho;

    // Informações para Contato
    private String contatoEmail;
    private String contatoCelular; // celular apenas, pelo que vi Face não tem telefone
    // -----------------------------------------------------------

    public Conta(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.caixaDeEntrada = new Chat[TAM];

        this.convitesEnviados = new String[TAM];
        this.convitesRecebidos = new String[TAM];
        this.listaDeAmigos = new Amigo[TAM];
        this.qtdMensagens = 0;
    }

    // ------------------ Metodos -----------------------------
    
    public boolean isNewInvite(String login) {
        boolean isNewInvite = true;
        if (indiceDeConvitesEnviados != 0) {
            for (int i = 0; i < indiceDeConvitesEnviados; i++) {
                if (login.equals(convitesEnviados[i])) { // Se ja foi enviado o convite
                                                         // desse usuario
                    isNewInvite = false;
                    break;
                }
            }
        }

        return isNewInvite;
    }

    // Verifica se ja enviou um convite para o usuario "login"
    public boolean isReceivedInviteReal(String login) {
        boolean isExistent = false;
        for (int i = 0; i < indiceDeConvitesRecebidos; i++) {
            if (login.equals(convitesRecebidos[i])) { // Se ja foi recebido convite
                isExistent = true;
                break;
            }
        }
        return isExistent;
    }

    public void solicitarAmizade(Conta receptora) {

        // pegando indice de convites da conta requerente
        convitesEnviados[indiceDeConvitesEnviados] = receptora.getLogin();
        indiceDeConvitesEnviados++;

        int indiceDeReceptora = receptora.getIndiceDeConvitesRecebidos();// pegando indice de convites recebidos da
                                                                         // conta receptora

        receptora.setConviteRecebido(indiceDeReceptora, login);// Coloca na posicao "indiceDeReceptora"
                                                               // do array "convitesRecebidos" da conta
                                                               // receptora o login do requerente
        receptora.setIndiceDeConvitesRecebidos(indiceDeReceptora + 1);// Incrementa em 1 a posicao do array
                                                                      // "convitesRecebidos" da conta receptora
    }

    // Aceitar convites feito
    public boolean aceitarAmizade(Conta novoAmigo) {
        boolean isAFriendNow = false;

        if (isReceivedInviteReal(novoAmigo.getLogin())) {
            listaDeAmigos[indiceDeAmigos] = new Amigo(novoAmigo.getLogin());
            indiceDeAmigos++;
            isAFriendNow = true;

            // Procura o convite do amigo em "convitesRecebidos"
            for (int i = 0; i < indiceDeConvitesRecebidos; i++) {
                if (convitesRecebidos[i].equals(novoAmigo.getLogin())) {
                    organizarConvitesRecebidos(i);
                    indiceDeConvitesRecebidos--;
                    organizarConvitesEnviadosDoAmigo(novoAmigo);
                    novoAmigo.setIndiceDeConvitesEnviados(novoAmigo.getIndiceDeConvitesEnviados() - 1);
                }
            }
        }
        return isAFriendNow;
    }

    // Remove o convite da posicao indice e coloca os convites das posicoes
    // posteriores uma posicao antes
    public void organizarConvitesRecebidos(int indice) {
        if (indice == 0) {
            convitesRecebidos[indice] = null;
        } else if (indice == TAM) {
            convitesRecebidos[indice] = null;
        } else {
            for (int i = indice + 1; i < indiceDeConvitesRecebidos; i++) {
                convitesRecebidos[i - 1] = convitesRecebidos[i];
            }
            convitesRecebidos[indiceDeConvitesRecebidos] = null;
        }

    }

    // Semelhante a organizarConvitesRecebidos, porem na conta de quem enviou
    public void organizarConvitesEnviadosDoAmigo(Conta novoAmigo) {
        int indiceDeConvitesEnviadosAmigo = novoAmigo.getIndiceDeConvitesEnviados();
        String[] convitesEnviadosDeAmigos = novoAmigo.getConvitesEnviados();

        if (indiceDeConvitesEnviadosAmigo == 0) {
            novoAmigo.setConviteEnviado(indiceDeConvitesEnviadosAmigo, null);
        } else if (indiceDeConvitesEnviadosAmigo == TAM) {
            novoAmigo.setConviteEnviado(indiceDeConvitesEnviadosAmigo, null);
        } else {
            for (int i = 0; i < indiceDeConvitesEnviadosAmigo; i++) {

                if (convitesEnviadosDeAmigos[i].equals(login)) {
                    for (int j = (i + 1); j < indiceDeConvitesEnviadosAmigo; j++) {
                        novoAmigo.setConviteEnviado(j - 1, convitesEnviadosDeAmigos[j]);
                    }
                    novoAmigo.setConviteEnviado(indiceDeConvitesEnviadosAmigo, null);
                }
            }
        }

    }

    public boolean enviaMensagem(Chat mensagem) {
        boolean retorno = false;
        if (mensagem != null && this.qtdMensagens < this.caixaDeEntrada.length) {
            this.caixaDeEntrada[this.qtdMensagens] = mensagem;
            this.qtdMensagens = this.qtdMensagens + 1;
            retorno = true;
        }
        return retorno;
    }

    // -----------------------------------------------

    // ======================================================================================
    // ***************************** Gets & Sets
    // ********************************************

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

    // ------------- get/set amigos ----------------
    public int getIndiceDeAmigos() {
        return this.indiceDeAmigos;
    }

    public Amigo getAmigo(int indice) {
        return this.listaDeAmigos[indice];
    }

    // ------------- get/set mensagens ----------------
    public int getQtdMensagens() {
        return this.qtdMensagens;
    }

    public Chat getCaixaDeEntrada(int id) {
        return this.caixaDeEntrada[id];
    }

    // ------------- get/set convites ----------------
    public String[] getConvitesEnviados() {
        return this.convitesEnviados;
    }

    public void setConviteEnviado(int indice, String loginReceptor) {
        this.convitesEnviados[indice] = loginReceptor;
    }

    public String[] getConvitesRecebidos() {
        return this.convitesRecebidos;
    }

    public void setConviteRecebido(int indice, String loginRequerente) {
        this.convitesRecebidos[indice] = loginRequerente;
    }

    public int getIndiceDeConvitesEnviados() {
        return this.indiceDeConvitesEnviados;
    }

    public void setIndiceDeConvitesEnviados(int indiceDeConvitesEnviados) {
        this.indiceDeConvitesEnviados = indiceDeConvitesEnviados;
    }

    public int getIndiceDeConvitesRecebidos() {
        return this.indiceDeConvitesRecebidos;
    }

    public void setIndiceDeConvitesRecebidos(int indiceDeConvitesRecebidos) {
        this.indiceDeConvitesRecebidos = indiceDeConvitesRecebidos;
    }

    // ---------------------------- Gets & Sets de Perfil
    // ----------------------------------
    public int getDiaNascimento() {
        return this.diaNascimento;
    }

    public void setDiaNascimento(int diaNascimento) {
        this.diaNascimento = diaNascimento;
    }

    public String getMesNascimento() {
        return this.mesNascimento;
    }

    public void setMesNascimento(String mesNascimento) {
        this.mesNascimento = mesNascimento;
    }

    public int getAnoNascimento() {
        return this.anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCidadeAtual() {
        return this.cidadeAtual;
    }

    public void setCidadeAtual(String cidadeAtual) {
        this.cidadeAtual = cidadeAtual;
    }

    public String getEstadoCivil() {
        return this.estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getInstituicaoEnsinoMedio() {
        return this.instituicaoEnsinoMedio;
    }

    public void setInstituicaoEnsinoMedio(String instituicaoEnsinoMedio) {
        this.instituicaoEnsinoMedio = instituicaoEnsinoMedio;
    }

    public String getInstituicaoFaculdade() {
        return this.instituicaoFaculdade;
    }

    public void setInstituicaoFaculdade(String instituicaoFaculdade) {
        this.instituicaoFaculdade = instituicaoFaculdade;
    }

    public String getOcupacaoTrabalho() {
        return this.ocupacaoTrabalho;
    }

    public void setOcupacaoTrabalho(String ocupacaoTrabalho) {
        this.ocupacaoTrabalho = ocupacaoTrabalho;
    }

    public String getContatoEmail() {
        return this.contatoEmail;
    }

    public void setContatoEmail(String contatoEmail) {
        this.contatoEmail = contatoEmail;
    }

    public String getContatoCelular() {
        return this.contatoCelular;
    }

    public void setContatoCelular(String contatoCelular) {
        this.contatoCelular = contatoCelular;
    }

    // ======================================================================================

    // ******************** Metodos toString()
    // ********************************************

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nLogin: " + this.login;
    }

    // deixando separado assim para quando formos adicionar mais informações para o
    // perfil
    // estética fica mais agradável do que um return só para tudo
    public String infoBasicaToString() {
        return "Perfil de " + this.nome + "\n******Informações Básicas******" + "\nNascimento: " + this.diaNascimento
                + " de " + this.mesNascimento + " de " + this.anoNascimento +

                "\nSexo: " + this.genero;
    }

    public String statusAtualToString() {
        return "\n******Status Atual******" + "\nCidade de Residência: " + this.cidadeAtual + "\nEstado Civil: "
                + this.estadoCivil;
    }

    public String infoProfissionToString() {
        return "\n******Informações Profissionais******" + "\nInstituição do Ensino Médio: "
                + this.instituicaoEnsinoMedio + "\nInstituição de Ensino Superior: " + this.instituicaoFaculdade
                + "\nTrabalha em:  " + this.ocupacaoTrabalho;
    }

    public String contatoToString() {
        return "\n******Contato******" + "\nE-Mail:" + this.contatoEmail + "\nCelular:  " + this.contatoCelular;
    }

}
