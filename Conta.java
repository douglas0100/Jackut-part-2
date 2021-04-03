public class Conta {
    
    
    private String nome;
    private String login;
    private String senha;
    private Chat[] caixaDeEntrada;
    private int qtdMensagens;
    //atributos abaixo são de perfil, deviam ser movidas para outra classe depois talvez
    //---------------------------------------
    //Informações básicas
    private int diaNascimento;
    private String mesNascimento; 
    private int anoNascimento;
    private String genero;          //sexo da pessoa

    //Informações de status atual
    private String cidadeAtual;
    private String estadoCivil;

    //Informações de estudo/emprego
    private String instituicaoEnsinoMedio;
    private String instituicaoFaculdade;
    private String ocupacaoTrabalho;

    //Informações para Contato
    private String contatoEmail;
    private String contatoCelular;  //celular apenas, pelo que vi Face não tem telefone
    //-----------------------------------------------------------

    public Conta(String nome, String login, String senha){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.caixaDeEntrada = new Chat[100];
        this.qtdMensagens = 0;
    }
    
    
    
    //-----------------------------------------------
    
    public int getQtdMensagens() {
        return this.qtdMensagens;
    }
 
    
    public Chat getCaixaDeEntrada(int Id) {
        return this.caixaDeEntrada[Id];
    }
    
    //-----------------------------------------------
    
    
    
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
    
    public boolean enviaMensagem(Chat mensagem){
        boolean retorno = false;
        if(mensagem != null && this.qtdMensagens < this.caixaDeEntrada.length){
            this.caixaDeEntrada[this.qtdMensagens] = mensagem;
            this.qtdMensagens = this.qtdMensagens + 1;
            retorno = true;
        }
        return retorno;
    }

    

    @Override
    public String toString() {
        return "Nome: "     + this.nome +
                "\nLogin: "    + this.login;
    }

    //deixando separado assim para quando formos adicionar mais informações para o perfil
    //estética fica mais agradável do que um return só para tudo
    public String InfoBasicaToString() {
        return "Perfil de "     + this.nome +
        "\n******Informações Básicas******"+
               "\nNascimento: "    + this.diaNascimento+ " de " + this.mesNascimento + " de " + this.anoNascimento +
                
               "\nSexo: "    + this.genero;
    }

    public String StatusAtualToString() {
        return "\n******Status Atual******"    +
        "\nCidade de Residência: "    + this.cidadeAtual+
        "\nEstado Civil: "    + this.estadoCivil;
    }

    public String InfoProfissionToString() {
        return "\n******Informações Profissionais******"    +
        "\nInstituição do Ensino Médio: "    + this.instituicaoEnsinoMedio+
        "\nInstituição de Ensino Superior: "    + this.instituicaoFaculdade+
        "\nTrabalha em:  "    + this.ocupacaoTrabalho;
    }

    public String ContatoToString() {
        return "\n******Contato******"    +
        "\nE-Mail:" + this.contatoEmail+
        "\nCelular:  "    + this.contatoCelular;
    }




}
