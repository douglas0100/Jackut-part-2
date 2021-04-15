public class Perfil {
    //criado por Matheus Antônio, 22/03/2021
    //baseado levemente nas opções de perfil Facebook

    //OBS: Ignorar comentário abaixo por enquanto - movendo atributos para
    //classe Conta por motivo de simplicidade por enquanto

    //objeto Perfil deve ser criado em classe "Conta" somente, atribuido na criação dela
    //somente 1 perfil por conta. informações daqui não alteram funcionamento
    //das outras classes (exceto por total de amigos, e lista de amigos)


    //Informações básicas
    
    private int diaNascimento;
    private String mesNascimento; 
    private int anoNascimento;
    private String genero;          //sexo da pessoa

    //Informações de status atual
    private String cidadeNatal;
    private String cidadeAtual;
    private String estadoCivil;

    //Informações de estudo/emprego
    private String instituicaoEnsinoMedio;
    private String instituicaoFaculdade;
    private String ocupacaoTrabalho;

    //Informações para Contato
    private String contatoEmail;
    private String contatoCelular;  //celular apenas, pelo que vi Face não tem telefone

    //Informações que usuário não altera diretamente
    private int totalAmigos;
    //esperando implementação de adição de amigos por Igor
    private int anoConta = 2021;
    //meio desnecessário, mas ainda legal de exibir - assumindo ser 2021 por enquanto

    //Getters e Setters

    //Informações básicas
    //Aniversário, gênero

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


    //Informações de status atual
    //Cidade, Estado Civil


    public String getCidadeNatal() {
        return this.cidadeNatal;
    }

    public void setCidadeNatal(String cidadeNatal) {
        this.cidadeNatal = cidadeNatal;
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

    //Informações de estudo/emprego

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

    public int getTotalAmigos() {
        return this.totalAmigos;
    }

    public void setTotalAmigos(int totalAmigos) {
        this.totalAmigos = totalAmigos;
    }

    public int getAnoConta() {
        return this.anoConta;
    }

    public void setAnoConta(int anoConta) {
        this.anoConta = anoConta;
    }

}
