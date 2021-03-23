public class Jackut {

    private static final int TAM = 100; // Constante do tamanho do array

    private Conta[] listaDeContas = new Conta[TAM]; // Array de Conta
    private int indice = 0; // armazena o indice do array de Conta

    // Verifica se o array de Conta esta vazio
    public boolean isEmpty() {
        return (indice == 0);
    }

    public boolean buscarLogin(String login) {
        boolean isNew = true; // login é novo?
        if (!isEmpty()) {
            for (int i = 0; i < indice; i++) {
                if (listaDeContas[i].getLogin().equals(login)) {
                    isNew = false;
                    break;
                }
            }
            
        }
        return isNew;

    }

    public int obterEnderecoUsuario(String login, String password) {
        if (!isEmpty()) {
            for (int i = 0; i < indice; i++) {
                if (listaDeContas[i].getLogin().equals(login)) {
                    if (listaDeContas[i].getSenha().equals(password)) {
                        return i; //informação correta
                    }
                }
            }
        }
        return -1;
    }

    public String obterNomeUsuario(int id) {
        return listaDeContas[id].getNome();
    }

    public void obterPerfilUsuario(int id) {
        System.out.println("Perfil de " + listaDeContas[id].getNome());
        System.out.println("******Informações Básicas******");
        System.out.println("Nascimento: " + listaDeContas[id].getDiaNascimento() + 
            " de " + listaDeContas[id].getMesNascimento() + " de "+ 
                listaDeContas[id].getAnoNascimento());
        System.out.println("Sexo: " + listaDeContas[id].getGenero());    
        System.out.println("******Status Atual******");
        System.out.println("Cidade de Residência: " + listaDeContas[id].getCidadeAtual());
        System.out.println("Estado Civil: " + listaDeContas[id].getEstadoCivil());
        System.out.println("******Informações Profissionais******");
        System.out.println("Instituição do Ensino Médio: " + listaDeContas[id].getInstituicaoEnsinoMedio());
        System.out.println("Instituição de Ensino Superior: " + listaDeContas[id].getInstituicaoFaculdade());
        System.out.println("Trabalha em: " + listaDeContas[id].getOcupacaoTrabalho());
        System.out.println("******Contato******");
        System.out.println("E-Mail: " + listaDeContas[id].getContatoEmail());
        System.out.println("Celular: " + listaDeContas[id].getContatoCelular());
    }

    public void alterarDiaPerfil(int dia, int id) {
        listaDeContas[id].setDiaNascimento(dia);
    }

    public void alterarMesPerfil(String mes, int id) {
        listaDeContas[id].setMesNascimento(mes);
    }

    public void alterarAnoPerfil(int ano, int id) {
        listaDeContas[id].setAnoNascimento(ano);
    }

    public void alterarGeneroPerfil(String genero, int id) {
        listaDeContas[id].setGenero(genero);
    }

    public void alterarCidadeAtualPerfil(String cidade, int id) {
        listaDeContas[id].setCidadeAtual(cidade);
    }

    public void alterarEstadoCivilPerfil(String estadoCivil, int id) {
        listaDeContas[id].setEstadoCivil(estadoCivil);
    }

    public void alterarEnsinoMedioPerfil(String ensinoMedio, int id) {
        listaDeContas[id].setInstituicaoEnsinoMedio(ensinoMedio);
    }

    public void alterarFaculdadePerfil(String faculdade, int id) {
        listaDeContas[id].setInstituicaoFaculdade(faculdade);
    }

    public void alterarTrabalhoPerfil(String trabalho, int id) {
        listaDeContas[id].setOcupacaoTrabalho(trabalho);
    }

    public void alterarEmailPerfil(String email, int id) {
        listaDeContas[id].setContatoEmail(email);
    }

    public void alterarCelularPerfil(String celular, int id) {
        listaDeContas[id].setContatoCelular(celular);
    }

    public boolean criarConta(Conta novaConta) {
        boolean isAdded = false; // Se foi adicionado

        if (indice < TAM) {
            listaDeContas[indice] = novaConta;
            indice++;
            isAdded = true;
        } else {
            isAdded = false;
        }
        return isAdded;

    }

    public Conta[] getListaDeContas() {
        return listaDeContas;
    }

    public int getIndice() {
        return indice;
    }

}