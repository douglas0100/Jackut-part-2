public class Conta {
    private String nome;
    private String login;
    private String senha;
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
    }
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

    

    @Override
    public String toString() {
        return "Nome: "     + this.nome +
               "\nLogin: "    + this.login;
    }


}
