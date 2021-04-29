import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    static Scanner input = new Scanner(System.in);
    public static final String OPC_INVALIDA = "Opção inválida";

    // Menu de opcoes
    public static String menu() {
        String op;
        boolean isValidOption = false;
        do {

            System.out.println("Escolha uma opcao");
            System.out.println("1: Criar conta");
            System.out.println("2: Login");
            System.out.println("3: Sair");
            op = input.nextLine();

            switch (op) {
            case "1":
                isValidOption = true;
                break;
            case "2":
                isValidOption = true;
                break;
            case "3":
                isValidOption = true;
                break;

            default:
                System.out.println(OPC_INVALIDA);
                break;
            }

        } while (!isValidOption);

        return op;
    }

    // Escolhido a opcao o codigo fara o desejado
    public static String acoesDeCasos(String op, Jackut jackutApp) {
        switch (op) {

        case "1":// Criar conta
            menuCriacaoDeContas(jackutApp);
            break;
        case "2":// Login

            String loginUserInput;
            String loginPasswordInput;

            System.out.println("Digite seu login:");
            loginUserInput = input.nextLine();

            System.out.println("Digite sua senha:");
            loginPasswordInput = input.nextLine();
            int userID = jackutApp.obterEnderecoUsuario(loginUserInput, loginPasswordInput);
            // obterEndereco verifica se login ta na lista, ai verifica a senha
            // retorna -1 se não encontrar, retorna algum número se encontrar
            // o número é o local da conta na lista, vamos usar para editar perfil, etc...
            if (userID != -1) {
                // abrir menu de usuário aqui
                System.out.println("Abrindo menu de usuário...");

                if (jackutApp.getConta(userID) instanceof ContaAdmin) {
                    loggedInAdminMenu(userID, jackutApp);
                } else {
                    loggedInMenu(userID, jackutApp);
                }

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
        String opcaoLogged;

        do {
            System.out.println("Bem vindo/a de volta," + jackutApp.obterNomeUsuario(userID));
            System.out.println("1 - Editar Perfil");
            System.out.println("2 - Amigos");
            System.out.println("3 - Chat");
            System.out.println("4 - Consultar Perfil de Outro Usuário");
            System.out.println("5 - Logout");
            // Nao precisa checar pois o proprio switch default se encarrega disso
            opcaoLogged = input.nextLine();

            switch (opcaoLogged) {

            case "1":
                System.out.println("Editar perfil");
                perfilMenu(userID, jackutApp);
                break;
            case "2":
                amigosMenu(userID, jackutApp);
                break;
            case "3":
                // Chat
                chat(userID, jackutApp);
                break;
            case "4":
                // Consultar perfil - implementado para poder ver mural conforme requisito!
                perfilConsulta(userID, jackutApp);
                break;
            case "5":
                System.out.println("Fazendo logout...");
                break;
            default:
                System.out.println(OPC_INVALIDA);
                break;
            }

        } while (opcaoLogged.compareToIgnoreCase("5") != 0);

    }

    // Codigo de ContaAdmin e alteracoes pra essa implementacao
    public static void menuCriacaoDeContas(Jackut jackutApp) {

        boolean isNew = false; // Se é novo o login
        boolean isCreated = false;
        boolean isValidOption = true;

        String inputLogin;
        String inputSenha;
        String inputNome;
        String inputTipoConta;

        do {
            System.out.println("Digite um login.");
            inputLogin = input.nextLine();
            isNew = jackutApp.isNewLogin(inputLogin);
            if (!isNew) {
                System.out.println("Conta ja existe!");
            }

        } while (!isNew);

        System.out.println("Digite uma senha");
        inputSenha = input.nextLine();
        System.out.println("Digite seu nome");
        inputNome = input.nextLine();

        do {
            System.out.println("Escolha o tipo de conta");
            System.out.println("1: Conta usuario");
            System.out.println("2: Conta admin");
            inputTipoConta = input.nextLine();

            if (inputTipoConta.equals("1")) {
                Conta novaConta = new Conta(inputNome, inputLogin, inputSenha);
                isCreated = jackutApp.criarConta(novaConta);
                isValidOption = true;
            } else if (inputTipoConta.equals("2")) {
                ContaAdmin novaConta = new ContaAdmin(inputNome, inputLogin, inputSenha);
                isCreated = jackutApp.criarContaAdmin(novaConta);
                isValidOption = true;
            } else {
                isValidOption = false;
                System.out.println(OPC_INVALIDA);
            }

        } while (!isValidOption);

        if (isCreated) {
            System.out.println("Conta foi criada");
        } else {
            System.out.println("A lista jackut esta cheia");
        }

    }

    public static void menuDeletarUsuario(int userID, Jackut jackutApp) {
        String inputLoginToDel;

        System.out.println("Digite o login da conta a remover:");
        inputLoginToDel = input.nextLine();

        if (jackutApp.obterIndiceDeUsuario(inputLoginToDel) == userID) {
            System.out.println("Nao é possivel se deletar!");

        } else if (jackutApp.isALogin(inputLoginToDel)) {
            jackutApp.removerConta(inputLoginToDel);
            System.out.println("Usuario deletado!");

        } else {
            System.out.println("Login nao existe!");
        }

    }

    public static void acoesDeEditarUsuario(String login, Jackut jackutApp) {
        String inputOp;
        int indiceDeLogin = jackutApp.obterIndiceDeUsuario(login);

        do {
            System.out.println("Opcoes admin de edicao de conta");
            System.out.println("1: Editar nome");
            System.out.println("2: Editar senha");
            System.out.println("3: Voltar");
            inputOp = input.nextLine();

            switch (inputOp) {
            case "1":
                System.out.println("Digite um novo nome");
                String novoNome = input.nextLine();
                jackutApp.getConta(indiceDeLogin).setNome(novoNome);
                System.out.println("Nome alterado!");
                break;
            case "2":
                System.out.println("Digite uma nova senha");
                String novaSenha = input.nextLine();
                jackutApp.getConta(indiceDeLogin).setSenha(novaSenha);
                System.out.println("Senha alterada!");
                break;
            case "3":
                System.out.println("Voltando");
                break;
            default:
                System.out.println(OPC_INVALIDA);
                break;
            }
        } while (!inputOp.equals("3"));

    }

    public static void menuEditarUsuario(Jackut jackutApp) {
        String inputLoginToEdit;

        System.out.println("Digite o login da conta a editar:");
        inputLoginToEdit = input.nextLine();

        if (jackutApp.isALogin(inputLoginToEdit)) {
            acoesDeEditarUsuario(inputLoginToEdit, jackutApp);
        } else {
            System.out.println("Login nao existe!");
        }

    }

    public static void loggedInAdminMenu(int userID, Jackut jackutApp) {
        String opcaoLogged;

        do {
            System.out.println("Bem vindo/a de volta," + jackutApp.obterNomeUsuario(userID));
            System.out.println("1 - Editar Perfil");
            System.out.println("2 - Amigos");
            System.out.println("3 - Chat");
            System.out.println("4 - Consultar Perfil de Outro Usuário");
            System.out.println("5 - Logout");
            System.out.println("*************");
            System.out.println("Opcoes admin");
            System.out.println("6 - Deletar usuario");
            System.out.println("7 - Editar usuario");

            // Nao precisa checar pois o proprio switch default se encarrega disso
            opcaoLogged = input.nextLine();

            switch (opcaoLogged) {

            case "1":
                System.out.println("Editar perfil");
                perfilMenu(userID, jackutApp);
                break;
            case "2":
                amigosMenu(userID, jackutApp);
                break;
            case "3":
                // Chat
                chat(userID, jackutApp);
                break;
            case "4":
                // Consultar perfil - implementado para poder ver mural conforme requisito!
                perfilConsulta(userID, jackutApp);
                break;
            case "5":
                System.out.println("Fazendo logout...");
                break;
            case "6":
                menuDeletarUsuario(userID, jackutApp);
                break;
            case "7":
                menuEditarUsuario(jackutApp);
                break;
            default:
                System.out.println(OPC_INVALIDA);
                break;
            }

        } while (!opcaoLogged.equals("5"));
    }

    // ************************************************************ */
    // Codigo referente a adicao de amigos
    public static void amigosMenu(int userID, Jackut jackutApp) {
        String op;
        do {
            System.out.format("Menu de amigos %n" + "1: Adicionar amigos %n" + "2: Convites Pendentes %n"
                    + "3: Ver amigos %n" + "4: Voltar menu %n");

            op = input.nextLine();
            switch (op) {
            case "1":
                addAmigos(userID, jackutApp);
                break;
            case "2":
                convitesPendentes(userID, jackutApp);

                break;
            case "3":
                listaDeAmigos(userID, jackutApp);

                break;
            case "4":
                System.out.println("Voltando ao menu principal...");
                break;
            default:
                break;
            }
        } while (!op.equals("4"));

    }

    public static void addAmigos(int userID, Jackut jackutApp) {
        String loginBuscado;
        System.out.println("Digite o login do usuario a ser adicionado.");
        loginBuscado = input.nextLine();

        if (jackutApp.isALogin(loginBuscado)) {// Se existe login do usuario
            if (!jackutApp.getConta(userID).isNewInvite(loginBuscado)) {// O usuario requerente ja fez pedido?
                System.out.println("Pedido de amizade ja existe!");
            } else {
                int indiceContaReceptora = jackutApp.obterIndiceDeUsuario(loginBuscado);// Pegamos o indice da conta que
                                                                                        // recebe convite
                Conta contaReceptora = jackutApp.getConta(indiceContaReceptora); // Pega conta receptora do convite
                jackutApp.getConta(userID).solicitarAmizade(contaReceptora);
                System.out.println("Solicitacao de amizade enviada!");
            }
        } else {
            System.out.println("Usuario inexistente!");
        }
    }

    public static void convitesPendentes(int userID, Jackut jackutApp) {
        int indiceDeConvitesRecebidos = jackutApp.getConta(userID).getIndiceDeConvitesRecebidos();
        String[] listaDeConvitesRecebidos = jackutApp.getConta(userID).getConvitesRecebidos();

        if (indiceDeConvitesRecebidos != 0) {
            for (int i = 0; i < indiceDeConvitesRecebidos; i++) {
                System.out.println("Convite [" + (i + 1) + "]:");
                System.out.println("O usuario " + listaDeConvitesRecebidos[i] + " quer ser seu amigo.");
            }
            menuConvitesPendentes(userID, jackutApp, indiceDeConvitesRecebidos);

        } else {
            System.out.println("Não há convites pendentes!");
        }

    }

    public static void menuConvitesPendentes(int userID, Jackut jackutApp, int indiceDeConvitesRecebidos) {
        String op;

        do {

            System.out.format("Deseja aceitar algum convite?" + "%n1: Sim" + "%n2: Nao %n");
            op = input.nextLine();

            switch (op) {
            case "1":
                System.out.println("Digite o login do usuario a ser aceitado");
                String adicionarLogin = input.nextLine();

                int amigoID = jackutApp.obterIndiceDeUsuario(adicionarLogin);
                Conta novoAmigo = jackutApp.getConta(amigoID);

                boolean isAFriendNow = jackutApp.getConta(userID).aceitarAmizade(novoAmigo);

                if (isAFriendNow) {
                    System.out.println("Contato adicionado!");
                    indiceDeConvitesRecebidos = jackutApp.getConta(userID).getIndiceDeConvitesRecebidos();
                } else {
                    System.out.println("Nao tem solicitacao de amizade desse usuario!");
                }
                break;
            case "2":
                break;
            default:
                System.out.println(OPC_INVALIDA);
                break;
            }
            if (indiceDeConvitesRecebidos == 0) {
                op = "2";
                System.out.println("Não há mais convites pendentes!");
            }

        } while (!op.equals("2"));
    }

    public static void listaDeAmigos(int userID, Jackut jackutApp) {
        Conta contaTemp = jackutApp.getConta(userID);
        int indiceDeAmigos = contaTemp.getIndiceDeAmigos();
        if (indiceDeAmigos != 0) {

            for (int i = 0; i < indiceDeAmigos; i++) {
                System.out.println("\tAmigo[1]:");
                System.out.println(contaTemp.getAmigo(i));
                System.out.println("-------------------------------------");
            }
        } else {
            System.out.println("Voce não tem amigos.");
        }

    }
    // ************************************************************ */

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
            System.out.println("12 - Visualizar MURAL de COMENTARIOS");
            System.out.println("13 - Avaliar COMENTARIOS novos para seu MURAL");
            System.out.println("14 - VOLTAR");
            // Switch default encarrega da chegagem
            opcaoLogged = input.nextLine();

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
            case "12": // ver COMENTARIOS
                System.out.println("Visualizar comentários do seu perfil");
                perfilExibirMuralPublico(userID, jackutApp);
                break;
            case "13":
                System.out.println("Avaliar novos comentarios do seu perfil");
                perfilAnalisarMuralRecebidas(userID, jackutApp);
                break;
            case "14":
                System.out.println("Voltando...");
                break;
            default:
                System.out.println(OPC_INVALIDA);
                break;

            }

        } while (opcaoLogged.compareToIgnoreCase("14") != 0);

    }

    // RELACIONADOS A MURAL, NO PERFIL DO USUÁRIO
    // Visualizar perfil de qualquer outro usuário - mesmo sem ser amigo
    // Requisito da letra D da Prova 1, por Matheus Antônio
    public static void perfilConsulta(int userID, Jackut jackutApp) {
        String opc;
        System.out.println("Digite o nome do usuário que você deseja visualizar perfil:");
        String login = input.nextLine();
        int posicao = buscarIdPeloLogin(jackutApp, login);
        if (posicao == -1) {
            while (posicao == -1) {
                System.out.format("Nome inválido! Tente novamente: %n");
                login = input.nextLine();
                posicao = buscarIdPeloLogin(jackutApp, login);
            }
        } else {

            jackutApp.obterPerfilUsuario(posicao);
            do {
                System.out.println("Digite 1 para ver as mensagens do mural");
                System.out.println("Digite 2 para submeter uma mensagem ao mural");
                System.out.println("Digite 3 para ver perfil novamente");
                System.out.println("Digite 4 para voltar");
                opc = input.nextLine();
                if (opc.compareToIgnoreCase("1") == 0) {
                    perfilExibirMuralPublico(posicao, jackutApp);
                } else if (opc.compareToIgnoreCase("2") == 0) {
                    perfilMensagemMural(userID, posicao, jackutApp);
                } else if (opc.compareToIgnoreCase("3") == 0) {
                    jackutApp.obterPerfilUsuario(posicao);
                } else {
                    System.out.println("Voltando...");
                }

            } while (opc.compareToIgnoreCase("4") != 0);
        }
    }

    // DEBUG MATHEUS
    // Submeter uma mensagem para ser avaliada pelo dono do Mural
    public static void perfilMensagemMural(int userID, int posicao, Jackut jackutApp) {
        String opc;
        do {
            MuralMessageContent mensagem;
            System.out.println("Digite mensagem para submeter ao perfil de " + jackutApp.getConta(posicao).getNome());
            String desc = input.nextLine();
            String nomeToPrint = jackutApp.getConta(userID).getLogin();
            mensagem = new MuralMessageContent(userID, nomeToPrint, desc);
            jackutApp.getConta(posicao).postarMensagemMural(mensagem);
            System.out.println("Mensagem enviada!");
            System.out.println("Gostaria de submeter outra mensagem? (1 - Sim | 2 - Não):");
            opc = input.nextLine();

        } while (opc.compareToIgnoreCase("2") != 0);
    }

    // Tela do usuário ler os comentários que recebeu em seu perfil,
    // pegando First da lista vamos vendo qual aceita para transferir para lista
    // principal
    public static void perfilAnalisarMuralRecebidas(int userID, Jackut jackutApp) {
        String opc;

        if (jackutApp.getConta(userID).muralRecebidosIsEmpty()) {
            System.out.println("Você não tem mensagens para avaliar.");
        } else {
            do {
                System.out.println("Você tem " + jackutApp.getConta(userID).mensagensMuralNaoAvaliadas()
                        + "  mensagens para avaliar antes de serem postadas no seu mural.");
                System.out.println("Exibindo mensagem mais recente...");
                System.out.println(jackutApp.getConta(userID).visualizarMuralPrimeiroRecebido());
                System.out.println("Digite 1 para permitir que essa mensagem apareça no seu mural.");
                System.out.println("Digite 2 para deletar essa mensagem.");

                // Após acessar primeira mensagem recebida, decidir se transfere para mural
                // público ou deleta

                System.out.println("Digite 1 (Aceitar) ou 2 (Deletar):");
                opc = input.nextLine();

                // programa só continuará com opção válida

                switch (opc) {
                case "1":
                    // Caso 1: Mensagem aceita. Transfere mensagem para mural
                    jackutApp.getConta(userID).confirmarMensagemMural();
                    System.out.println("Mensagem aceita e publicada para o seu mural público!");
                    break;
                case "2":
                    // Caso 2: Mensagem rejeitada. Deleta a primeira mensagem da lista de propostas.
                    jackutApp.getConta(userID).deletarMensagemMural();
                    System.out.println("Mensagem rejeitada e não será publicada em seu mural público!");
                    break;
                default:
                    System.out.println(OPC_INVALIDA);
                    break;
                }

                if (jackutApp.getConta(userID).muralRecebidosIsEmpty()) {
                    System.out.println("Não tem mais mensagens para avaliar. Voltando...");
                    break;
                } else {
                    System.out.println("Gostaria de avaliar outra mensagem? (1 - Sim | 2 - Não):");
                    opc = input.nextLine();
                }

            } while (opc.compareToIgnoreCase("2") != 0);
        }

    }

    public static void perfilExibirMuralPublico(int userID, Jackut jackutApp) {
        LinkedList listAux = jackutApp.getConta(userID).returnToPrintLinkedList();
        Iterator iterator = listAux.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    // FIM DA PROVA 1 QUESTÃO D

    // Funções e Procedimentos Editados por Douglas Leite
    // ---------------------------------------------------------

    /*
     * O Procedimento Chat contem um menu de seleção com 3 opções disponiveis
     */

    public static void chat(int userID, Jackut jackutApp) {
        String opc = null;
        do {
            System.out.format("---- Jackut Chat ----%n" 
            + "Selecione uma opção %n" 
            + "1 - Caixa de entrada %n"
            + "2 - Caixa de entrada Secreta %n" 
            + "3 - Nova Mensagem %n" 
            + "4 - Nova Mensagem Secreta %n"
            + "5 - Voltar ao menu de usuario %n");
            opc = input.nextLine();

            switch (opc) {
            case "1":
                caixaDeEntradaSimples(userID, jackutApp);
                break;
            case "2":
                caixaDeEntradaSecreta(userID, jackutApp);
                break;
            case "3":
                novaMensagemSimples(userID, jackutApp);
                break;
            case "4":
                novaMensagemSecreta(userID, jackutApp);
                break;
            case "5":
                System.out.format("voltando ao menu... %n");
                break;
            default:
                System.out.println(OPC_INVALIDA);
                break;
            }
        } while (opc.compareToIgnoreCase("5") != 0);
    }

    /*
     * O Procedimento caixa de entrada verifica se há mensagens dentro do
     * repositorio "caixa de entrada" na classe Conta, a partir do indice do usuario
     * "userID". Caso o indice seja maior que zero, sera impresso todas as mensagens
     * que estão no repositorio.
     */

    public static void caixaDeEntradaSimples(int userID, Jackut jackutApp) {
        if (jackutApp.getConta(userID).getQtdMensagensSimples() == 0) {
            System.out.format("Caixa de entrada vazia! %n");
        } else {
            for (int i = 0; i < jackutApp.getConta(userID).getQtdMensagensSimples(); i = i + 1) {
                System.out.format("%n%d - %s %n", i + 1, jackutApp.getConta(userID).getCaixaDeEntradaSimples(i));
            }
        }
    }

    public static void caixaDeEntradaSecreta(int userID, Jackut jackutApp) {
        if (jackutApp.getConta(userID).getQtdMensagensSecretas() == 0) {
            System.out.format("Caixa de entrada vazia! %n");
        } else {
            for (int i = 0; i < jackutApp.getConta(userID).getQtdMensagensSecretas(); i = i + 1) {
                System.out.format("%n%d - %s %n", i + 1, jackutApp.getConta(userID).getCaixaDeEntradaSecreta(i));
            }

            System.out.format("Gostaria de ler alguma dessas mensagens? %n" 
            + "1 - Sim %n" 
            + "2 - Não %n" 
            + "Digite: ");
            String opc = input.nextLine();

            while(!opc.equals("1") && !opc.equals("2")){
                System.out.format("Opção invalida! %n"
                + "Digite novamente: ");
                opc = input.nextLine();
            }
            if (opc.equals("1")) {
                System.out.format("Digite o numero da mensagem que deseja ler %n" 
                + "Digite: ");
                int mensNum = input.nextInt();
                mensNum = mensNum - 1;
                while (mensNum < 0 || mensNum > jackutApp.getConta(userID).getQtdMensagensSecretas()) {
                    System.out.format("Valor invalido! %n" + "Digite novamente: ");
                    mensNum = input.nextInt();
                }
                input.nextLine();
                System.out.format("Digite a senha : ");
                String senha = input.nextLine();
                while (!jackutApp.getConta(userID).getCaixaDeEntradaSecreta(mensNum).validaSenha(senha)) {
                    System.out.format("Senha invalida! %n"
                     + "Digite novamente: ");
                    senha = input.nextLine();
                }

                MensagemSimples mens = new MensagemSimples(jackutApp.getConta(userID).getCaixaDeEntradaSecreta(mensNum));
                System.out.format("%s ", mens);
            }else{
                System.out.format("Voltando ao menu... %n");
            }
        }
    }

    /*
     * O procedimento nova mensagem recebe o nome do destinatario como paramentro e
     * atravéz da comparação entre nomes de usuarios, feita pelo função
     * "buscar id pelo nome", verifica se o mesmo é valido. Após a validação, é
     * criado um objeto da classe Chat "mensagem", que vai ser armazenado dentro do
     * repositorio de contas do destinatario.
     */

    public static void novaMensagemSimples(int userID, Jackut jackutApp) {
        String opc;
        do {
            MensagemSimples mensagem;
            System.out.format("Login do destinatario %n"
            + "Digite: ");
            String login = input.nextLine();
            int posicao = buscarIdPeloLogin(jackutApp, login);
            if (posicao == -1) {
                while (posicao == -1 || login.equals(jackutApp.getConta(userID).getLogin())) {
                    System.out.format("Login invalido! %n"
                    + "Digite novamente: ");
                    login = input.nextLine();
                    posicao = buscarIdPeloLogin(jackutApp, login);
                }
            } else {
                System.out.format("Mensagem %n" 
                + "Digite: ");
                String desc = input.nextLine();
                login = jackutApp.getConta(userID).getLogin();
                mensagem = new MensagemSimples(login, desc);
                jackutApp.getConta(posicao).enviaMensagemSimples(mensagem);
                System.out.format("Mensagem enviada! %n");
            }
            System.out.format("Gostaria de enviar uma nova mensagem? %n" 
            + "1 - Sim %n" 
            + "2 - Não %n" 
            + "Digite: ");
            opc = input.nextLine();
        } while (opc.compareToIgnoreCase("2") != 0);
    }

    public static void novaMensagemSecreta(int userID, Jackut jackutApp) {
        String opc;
        do {
            MensagemSecreta mensagem;
            System.out.format("Login do destinatario %n" 
            + "Digite: ");
            String login = input.nextLine();
            int posicao = buscarIdPeloLogin(jackutApp, login);
            if (posicao == -1) {
                while (posicao == -1 || login.equals(jackutApp.getConta(userID).getLogin())) {
                    System.out.format("Login invalido! %n"
                    + "Digite novamente: ");
                    login = input.nextLine();
                    posicao = buscarIdPeloLogin(jackutApp, login);
                }
            } else {
                System.out.format("Mensagem %n" 
                + "Digite: ");
                String desc = input.nextLine();
                login = jackutApp.getConta(userID).getLogin();

                System.out.format("Senha %n" 
                + "Digite: ");
                String senha = input.nextLine();

                mensagem = new MensagemSecreta(login, desc, senha);
                jackutApp.getConta(posicao).enviaMensagemSecreta(mensagem);
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
    // "Nao eh necessario o checkString pq o switch default se encarrega da opcao
    // invalida e o dowhile obriga uma opcao valida"

    // ---------------------------------------------------------

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
