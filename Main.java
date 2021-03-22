
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
    public static String acoesDeCasos(String op, Jackut jackutApp){
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
                }else{
                    System.out.println("Conta nao foi criada!");
                }
    
                break;
            case "2":// Login
                System.out.println("Ainda sera implementado");
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
            
            System.out.println("\tUsuario " + (i+1));
            System.out.println(contaTemp[i]);
            System.out.println("====================");
        }
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
