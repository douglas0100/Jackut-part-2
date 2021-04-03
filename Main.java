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


    public static boolean isStringNumbersOnly(String opcao, int length) {
        for (int i = 0; i < length; i++) {
            if (opcao.charAt(i) >= '0' && opcao.charAt(i) <= '9') {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    // Acesso de menu para usuário
    // Editar perfil, adicionar amigos, enviar mensagens e ler mensagens
    public static void loggedInMenu(int userID, Jackut jackutApp) {
        String opcaoLogged;

        do {
            System.out.println("Bem vindo/a de volta," + jackutApp.obterNomeUsuario(userID));
            System.out.println("1 - Editar Perfil");
            System.out.println("2 - Adicionar Amigos");
            System.out.println("3 - Chat");
            System.out.println("4 - Logout");
            boolean checkString;
            do {
                opcaoLogged = input.nextLine();
                checkString = isStringNumbersOnly(opcaoLogged, opcaoLogged.length());
                System.out.println(checkString);
            } while (checkString == false);

            switch (opcaoLogged) {

            case "1":
                System.out.println("Editar perfil");
                perfilMenu(userID, jackutApp);
                break;
            case "2":
                System.out.println("Adicionar amigos");
                System.out.println("NÃO IMPLEMENTADO");
                break;
            case "3":
                // Chat
                chat(userID, jackutApp);
                break;
            case "4":
                System.out.println("Fazendo logout...");
                break;
            default:
                break;
            }

        } while (opcaoLogged.compareToIgnoreCase("4") != 0);

    }

    // Escolher qual atributo alterar do perfil
    public static void perfilMenu(int userID, Jackut jackutApp) {
        String opcaoLogged;
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
            boolean checkString;
            do {
                opcaoLogged = input.nextLine();
                checkString = isStringNumbersOnly(opcaoLogged, opcaoLogged.length());
                //System.out.println(checkString);
            } while (checkString == false);

            switch (opcaoLogged) {
            case "0": // exibir perfil
                jackutApp.obterPerfilUsuario(userID);
                break;
            case "1": // alterar DIA de nascimento
                System.out.println("Digite um dia de nascimento (em NÚMERO):");

                int newDay = input.nextInt(); // sem validação no momento...
                input.nextLine();

                jackutApp.alterarDiaPerfil(newDay, userID);
                break;
            case "2": // alterar MÊS
                System.out.println("Digite um mês de nascimento (em TEXTO):");
                String newMonth = input.nextLine();
                jackutApp.alterarMesPerfil(newMonth, userID);
                break;
            case "3": // alterar ANO
                System.out.println("Digite um ano de nascimento (em NÚMERO):");

                int newYear = input.nextInt(); // sem validação no momento...
                input.nextLine();

                jackutApp.alterarAnoPerfil(newYear, userID);
                break;
            case "4": // alterar GÊNERO/SEXO
                System.out.println("Digite seu sexo (em TEXTO):");
                String newGender = input.nextLine();
                jackutApp.alterarGeneroPerfil(newGender, userID);
                break;
            case "5": // alterar CIDADE DE RESIDÊNCIA
                System.out.println("Digite sua cidade de residência (em TEXTO):");
                String newCity = input.nextLine();
                jackutApp.alterarCidadeAtualPerfil(newCity, userID);
                break;
            case "6": // alterar ESTADO CIVIL
                System.out.println("Digite seu estado civil (em TEXTO):");
                String newCivil = input.nextLine();
                jackutApp.alterarEstadoCivilPerfil(newCivil, userID);
                break;
            case "7": // alterar ENSINO MÉDIO
                System.out.println("Digite onde estuda/estudou o ensino médio (em TEXTO):");
                String newHighSchool = input.nextLine();
                jackutApp.alterarEnsinoMedioPerfil(newHighSchool, userID);
                break;
            case "8": // alterar FACULDADE
                System.out.println("Digite onde estuda/estudou o ensino superior (em TEXTO):");
                String newUni = input.nextLine();
                jackutApp.alterarFaculdadePerfil(newUni, userID);
                break;
            case "9": // alterar TRABALHO
                System.out.println("Digite sua profissão/trabalho (em TEXTO):");
                String newJob = input.nextLine();
                jackutApp.alterarTrabalhoPerfil(newJob, userID);
                break;
            case "10": // alterar EMAIL
                System.out.println("Digite seu e-mail para contato (em TEXTO):");
                String newEmailAddress = input.nextLine();
                jackutApp.alterarEmailPerfil(newEmailAddress, userID);
                break;
            case "11": // alterar CELULAR
                System.out.println("Digite seu número de celular para contato");
                System.out.println("(pode ter espaço incluir '+' antes da região e '-' entre números):");
                String newPhone = input.nextLine();
                jackutApp.alterarCelularPerfil(newPhone, userID);
                break;
            case "12":
                System.out.println("Voltando...");
                break;
            default:
                break;

            }

        } while (opcaoLogged.compareToIgnoreCase("12") != 0);

    }
    
    
    // Funções e Procedimentos Editados por Douglas Leite
    //---------------------------------------------------------
    
    /* O Procedimento Chat contem um menu de seleção
    com 3 opções disponiveis */
    
    public static void chat(int userID, Jackut jackutApp) {
        String opc = null;
        do {
            System.out.format("---- Jackut Chat ----%n"
                    + "Selecione uma opção %n"
                    + "1 - Caixa de entrada %n"
                    + "2 - Nova Mensagem %n"
                    + "3 - Voltar ao menu de usuario %n"
                    + "Digite: ");
            opc = checkString(opc);
            switch (opc) {
                case "1":
                    caixaDeEntrada(userID, jackutApp);
                    break;
                case "2":
                    novaMensagem(userID, jackutApp);
                    break;
                case "3":
                    System.out.format("voltando ao menu... %n");
            }
        } while (opc.compareToIgnoreCase("3") != 0);
    }
    
    /* O Procedimento caixa de entrada verifica se há mensagens dentro
    do repositorio "caixa de entrada" na classe Conta, a partir do indice 
    do usuario "userID". Caso o indice seja maior que zero, sera impresso
    todas as mensagens que estão no repositorio. */
    
    public static void caixaDeEntrada(int userID, Jackut jackutApp) {
        if (jackutApp.getConta(userID).getQtdMensagens() == 0) {
            System.out.format("Caixa de entrada vazia! %n");
        } else {
            for (int i = 0; i < jackutApp.getConta(userID).getQtdMensagens(); i = i + 1) {
                System.out.format("%n %s %n", jackutApp.getConta(userID).getCaixaDeEntrada(i));
            }
        }
    }
    
    /* O procedimento nova mensagem recebe o nome do destinatario 
    como paramentro e atravéz da comparação entre nomes de usuarios,
    feita pelo função "buscar id pelo nome", verifica se o mesmo é valido.
    Após a validação, é criado um objeto da classe Chat "mensagem", que vai
    ser armazenado dentro do repositorio de contas do destinatario. */

    public static void novaMensagem(int userID, Jackut jackutApp) {
        String opc;
        do {
            Chat mensagem;
            System.out.format("Login do destinatario %n"
                    + "Digite: ");
            String login = input.nextLine();
            int posicao = buscarIdPeloLogin(jackutApp, login);
            if (posicao == -1) {
                while (posicao == -1) {
                    System.out.format("Nome invalido! %n");
                    login = input.nextLine();
                    posicao = buscarIdPeloLogin(jackutApp, login);
                }
            } else {
                System.out.format("Mensagem %n"
                        + "Digite: ");
                String desc = input.nextLine();
                login = jackutApp.getConta(userID).getLogin();
                mensagem = new Chat(login, desc);
                jackutApp.getConta(posicao).enviaMensagem(mensagem);
                System.out.format("Mensagem enviada! %n");
            }
            System.out.format("Gostaria de enviar uma nova mensagem? %n"
                    + "1 - Sim %n"
                    + "2 - Não %n"
                    + "Digite: ");
            opc = input.nextLine();
        } while (opc.compareToIgnoreCase("2") != 0);
    }

    public static int buscarIdPeloLogin(Jackut jackutApp, String login) {
        for (int i = 0; i < jackutApp.getIndice(); i = i + 1) {
            if (jackutApp.getConta(i).getLogin().equals(login)) {
                return i;
            }
        }
        return -1;
    }
    
    public static String checkString(String opc) {
        boolean check;
        do {
            opc = input.nextLine();
            check = isStringNumbersOnly(opc, opc.length());
        } while (check == false);
        return opc;
    }
    
    //---------------------------------------------------------
    
    
    
    

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
