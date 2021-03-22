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