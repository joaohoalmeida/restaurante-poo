package restaurante;

import java.util.*;
import classes.Cliente;
import classes.Pedido;
import classes.Prato;

public class Main {
    private static List<Prato> cardapio = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();
    private static double caixaTotal = 0.0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        cardapio.add(new Prato("Lasanha", 45.90, 10));
        cardapio.add(new Prato("Suco de Laranja", 8.00, 20));

        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> realizarPedido();
                case 3 -> mostrarCardapio();
                case 4 -> adicionarPratoAoCardapio(); 
                case 5 -> exibirCaixa();             
                case 6 -> exibirPedidos();
                case 7 -> exibirClientes();
                case 0 -> System.out.println("A encerrar sistema...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n--- RESTAURANTE TADS ---\n");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Realizar Novo Pedido (Venda)");
        System.out.println("3. Ver Cardápio");
        System.out.println("4. Adicionar Novo Prato ao Cardápio");
        System.out.println("5. Ver Total no Caixa");
        System.out.println("6. Consultar Pedido");
        System.out.println("7. Exibir Clientes");
        System.out.println("0. Sair\n");
        System.out.println("Escolha uma opção:\n");
    }

    private static void cadastrarCliente() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        clientes.add(new Cliente(nome));
        System.out.println("Cliente " + nome + " cadastrado!");
    }

    private static void adicionarPratoAoCardapio() {
        
        System.out.print("Nome do prato/bebida: ");
        String nome = scanner.nextLine();

        System.out.print("Preço (ex: 25,50): ");
        double preco = scanner.nextDouble();
        
        System.out.print("Quantidade em estoque: ");
        int qtd = scanner.nextInt();

        cardapio.add(new Prato(nome, preco, qtd));
        System.out.println("Item adicionado ao cardápio com sucesso!");
    }

    private static void exibirPedidos(){

        if (pedidos.isEmpty()){            
            System.out.println("-----------");
            System.out.println("Sem pedidos");
            System.out.println("-----------");
        }

        else{
            System.out.println("\n--- SELECIONAR CLIENTE ---");
                for (int i = 0; i < clientes.size(); i++){
                    System.out.println(i + " - " + clientes.get(i).getNome());
                }
                System.out.print("ID do cliente: ");
                int indexC = scanner.nextInt();
                scanner.nextLine();

                for (Pedido p : pedidos){
                    if (p.getCliente().equals(clientes.get(indexC))){
                        System.out.println(p.toString());
                    }
                    else{
                        System.out.println("--------------------------------");
                        System.out.println("Sem pedidos associado ao cliente");
                        System.out.println("--------------------------------");
                    }
                }
        }

    }

    private static void realizarPedido() {
        if (clientes.isEmpty()) {
            System.out.println("---------------------------------------------------");
            System.out.println("Erro: Cadastre um cliente antes de abrir um pedido.");
            System.out.println("---------------------------------------------------");
            return;
        }

        System.out.println("\n--- SELECIONAR CLIENTE ---");
        for (int i = 0; i < clientes.size(); i++){
            System.out.println(i + " - " + clientes.get(i).getNome());
        }
        System.out.print("ID do cliente: ");
        int indexC = scanner.nextInt();

        Pedido novoPedido = new Pedido(clientes.get(indexC));

        int idPrato = -2;
        while (idPrato != -1) {
            mostrarCardapio();
            System.out.print("ID do prato para adicionar (ou -1 para fechar conta): ");
            idPrato = scanner.nextInt();

            if (idPrato >= 0 && idPrato < cardapio.size()) {
                Prato p = cardapio.get(idPrato);
                if (p.getQntd() > 0) {
                    novoPedido.addPedido(p);
                    System.out.println("-> " + p.getNome() + " adicionado.");
                } else {
                    System.out.println("AVISO: Item sem estoque!");
                }
            }
        }

        pedidos.add(novoPedido);

        System.out.println("\n" + novoPedido.toString());
        caixaTotal += novoPedido.getConta();
        System.out.println("Pedido finalizado e valor registado no caixa.");
    }

    private static void mostrarCardapio() {
        System.out.println("\n--- CARDÁPIO ATUAL ---");

        if (cardapio.isEmpty()){
            System.out.println("Cardápio vazio.");
        }
        
        for (int i = 0; i < cardapio.size(); i++){
            Prato pratoAtual = cardapio.get(i);
            System.out.println(i + " - " + pratoAtual.getNome() + " - R$ " + String.format("%.2f", pratoAtual.getPreco()) + " (" + pratoAtual.getQntd() + " em estoque)" );
        }
    }

        private static void exibirClientes() {
        System.out.println("\n--- CLIENTES ATUAIS ---");

        if (clientes.isEmpty()){
            System.out.println("Sem clientes cadastrados no momento");
            return;
        }
        
        for (int i = 0; i < clientes.size(); i++){
            Cliente clienteAtual = clientes.get(i);
            System.out.println(i + " - " + clienteAtual.getNome());
        }
    }


    private static void exibirCaixa() {
        System.out.println("\n==============================");
        System.out.printf(" TOTAL EM CAIXA: R$ %.2f\n", caixaTotal);
        System.out.println("==============================");
    }
}