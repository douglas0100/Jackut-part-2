
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    // Menu de opcoes
    public static String menu() {
        String op;
        do {

            System.out.println("Escolha uma opcao");
            System.out.println("1: Criar conta");
            System.out.println("2: Login");
            System.out.println("3: Sair");
            op = input.nextLine();

            if (!op.equals("1") && !op.equals("2") && !op.equals("3")) {
                System.out.println("Opcao invalida!");
            }

        } while (!op.equals("1") && !op.equals("2") && !op.equals("3"));

        return op;
    }

    // Escolhido a opcao o codigo fara o desejado
    public static String acoesDeCasos(String op, Jackut jackutApp) {
        switch (op) {

        case "1":// Criar conta
            boolean isNew = false; // Se é novo o login
            boolean isCreated = false;
            String inputLogin;
            String inputSenha;
            String inputNome;

            do {
                System.out.println("Digite um login.");
                inputLogin = input.nextLine();
                isNew = jackutApp.buscarLogin(inputLogin);
                if (!isNew) {
                    System.out.println("Conta ja existe!");
                }

            } while (!isNew);

            System.out.println("Digite uma senha");
            inputSenha = input.nextLine();
            System.out.println("Digite seu nome");
            inputNome = input.nextLine();
            Conta novaConta = new Conta(inputNome, inputLogin, inputSenha);
            isCreated = jackutApp.criarConta(novaConta);

            if (isCreated) {
                System.out.println("Conta criada!");
            } else {
                System.out.println("Conta nao foi criada!");
            }

            break;
        case "2":// Login
            // System.out.println("Ainda sera implementado");
            String loginUserInput;
            String loginPasswordInput;

            System.out.println("Digite seu login:");
            loginUserInput = input.nextLine();
            // isNew = jackutApp.buscarLogin(loginUserInput);
            System.out.println("Digite sua senha:");
            loginPasswordInput = input.nextLine();
            int userID = jackutApp.obterEnderecoUsuario(loginUserInput, loginPasswordInput);
            // obterEndereco verifica se login ta na lista, ai verifica a senha
            // retorna -1 se não encontrar, retorna algum número se encontrar
            // o número é o local da conta na lista, vamos usar para editar perfil, etc...
            if (userID != -1) {
                // abrir menu de usuário aqui
                System.out.println("Abrindo menu de usuário...");
                loggedInMenu(userID, jackutApp);
                // menu vai aqui

            } else {
                System.out.println("Informações incorretas de login.");
                System.out.println("Voltando para menu principal...");
            }
            break;
        case "3":// Sair
            System.out.println("Fim do programa.");
            break;
        default:
            break;
        }
        return op;
    }

    // Exibe todos usuarios do Jackut
    public static void exibirUsuarios(Jackut jackut) {
        Conta[] contaTemp = jackut.getListaDeContas();
        int index = jackut.getIndice();

        System.out.println("====================");
        for (int i = 0; i < index; i++) {

            System.out.println("\tUsuario " + (i + 1));
            System.out.println(contaTemp[i]);
            System.out.println("====================");
        }
    }

    // Acesso de menu para usuário
    // Editar perfil, adicionar amigos, enviar mensagens e ler mensagens
    public static void loggedInMenu(int userID, Jackut jackutApp) {
        int opcaoLogged;

        do {
            System.out.println("Bem vindo/a de volta," + jackutApp.obterNomeUsuario(userID));
            System.out.println("1 - Editar Perfil");
            System.out.println("2 - Adicionar Amigos");
            System.out.println("3 - Enviar uma Mensagem");
            System.out.println("4 - Ler Mensagens");
            System.out.println("5 - Logout");
            opcaoLogged = input.nextInt();
            input.nextLine();

            switch (opcaoLogged) {

            case 1:
                System.out.println("Editar perfil");
                perfilMenu(userID, jackutApp);
                break;
            case 2:
                System.out.println("Adicionar amigos");
                System.out.println("NÃO IMPLEMENTADO");
                break;
            case 3:
                System.out.println("Enviar mensagem");
                System.out.println("NÃO IMPLEMENTADO");
                break;
            case 4:
                System.out.println("Ler mensagens");
                System.out.println("NÃO IMPLEMENTADO");
                break;
            case 5:
                System.out.println("Fazendo logout...");
                break;
            default:
                break;
            }

        } while (opcaoLogged < 5);

    }

    // Escolher qual atributo alterar do perfil
    public static void perfilMenu(int userID, Jackut jackutApp) {
        int perfilOp;
        do {
            System.out.println("Opções de perfil:");
            System.out.println("0 - Exibir Perfil");
            System.out.println("1 - Alterar DIA de nascimento");
            System.out.println("2 - Alterar MÊS de nascimento");
            System.out.println("3 - Alterar ANO de nascimento");
            System.out.println("4 - Alterar GÊNERO/SEXO");
            System.out.println("5 - Alterar CIDADE DE RESIDÊNCIA");
            System.out.println("6 - Alterar ESTADO CIVIL");
            System.out.println("7 - Alterar instituição de ENSINO MÉDIO");
            System.out.println("8 - Alterar instituição de ENSINO SUPERIOR");
            System.out.println("9 - Alterar OCUPAÇÃO/TRABALHO");
            System.out.println("10 - Alterar E-MAIL para CONTATO");
            System.out.println("11 - Alterar CELULAR para CONTATO");
            System.out.println("12 - VOLTAR");
            perfilOp = input.nextInt();
            input.nextLine();

            switch (perfilOp) {
            case 0: // exibir perfil
                jackutApp.obterPerfilUsuario(userID);
                break;
            case 1: // alterar DIA de nascimento
                System.out.println("Digite um dia de nascimento (em NÚMERO):");

                int newDay = input.nextInt(); // sem validação no momento...
                input.nextLine();

                jackutApp.alterarDiaPerfil(newDay, userID);
                break;
            case 2: // alterar MÊS
                System.out.println("Digite um mês de nascimento (em TEXTO):");
                String newMonth = input.nextLine();
                jackutApp.alterarMesPerfil(newMonth, userID);
                break;
            case 3: // alterar ANO
                System.out.println("Digite um ano de nascimento (em NÚMERO):");

                int newYear = input.nextInt(); // sem validação no momento...
                input.nextLine();

                jackutApp.alterarAnoPerfil(newYear, userID);
                break;
            case 4: // alterar GÊNERO/SEXO
                System.out.println("Digite seu sexo (em TEXTO):");
                String newGender = input.nextLine();
                jackutApp.alterarGeneroPerfil(newGender, userID);
                break;
            case 5: // alterar CIDADE DE RESIDÊNCIA
                System.out.println("Digite sua cidade de residência (em TEXTO):");
                String newCity = input.nextLine();
                jackutApp.alterarCidadeAtualPerfil(newCity, userID);
                break;
            case 6: // alterar ESTADO CIVIL
                System.out.println("Digite seu estado civil (em TEXTO):");
                String newCivil = input.nextLine();
                jackutApp.alterarEstadoCivilPerfil(newCivil, userID);
                break;
            case 7: // alterar ENSINO MÉDIO
                System.out.println("Digite onde estuda/estudou o ensino médio (em TEXTO):");
                String newHighSchool = input.nextLine();
                jackutApp.alterarEnsinoMedioPerfil(newHighSchool, userID);
                break;
            case 8: // alterar FACULDADE
                System.out.println("Digite onde estuda/estudou o ensino superior (em TEXTO):");
                String newUni = input.nextLine();
                jackutApp.alterarFaculdadePerfil(newUni, userID);
                break;
            case 9: // alterar TRABALHO
                System.out.println("Digite sua profissão/trabalho (em TEXTO):");
                String newJob = input.nextLine();
                jackutApp.alterarTrabalhoPerfil(newJob, userID);
                break;
            case 10: // alterar EMAIL
                System.out.println("Digite seu e-mail para contato (em TEXTO):");
                String newEmailAddress = input.nextLine();
                jackutApp.alterarEmailPerfil(newEmailAddress, userID);
                break;
            case 11: // alterar CELULAR
                System.out.println("Digite seu número de celular para contato");
                System.out.println("(pode ter espaço incluir '+' antes da região e '-' entre números):");
                String newPhone = input.nextLine();
                jackutApp.alterarCelularPerfil(newPhone, userID);
                break;
            case 12:
                System.out.println("Voltando...");
                break;
            default:
                break;

            }

        } while (perfilOp != 12);

    }

    public static void main(String[] args) {
        Jackut jackutApp = new Jackut();
        String op;

        System.out.println("Bem vindo ao Jackut!");
        do {
            System.out.println("");
            op = acoesDeCasos(menu(), jackutApp);
        } while (!op.equals("3"));

        exibirUsuarios(jackutApp);

    }

}
