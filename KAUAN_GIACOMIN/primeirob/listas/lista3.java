package KAUAN_GIACOMIN.primeirob.listas;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SistemaVendas {
    private static final int MESES_NO_ANO = 12;
    private static final int DIAS_NO_MES = 30;

    private static final Map<Integer, Map<Integer, Integer>> vendasPorMes = new HashMap<>();

    static {
        for (int mes = 1; mes <= MESES_NO_ANO; mes++) {
            Map<Integer, Integer> vendasDoMes = new HashMap<>();
            for (int dia = 1; dia <= DIAS_NO_MES; dia++) {
                vendasDoMes.put(dia, 0);
            }
            vendasPorMes.put(mes, vendasDoMes);
        }
    }

    public static void salvarVendas(int mes, int dia, int quantidadeVendas) {
        if (mes < 1 || mes > MESES_NO_ANO || dia < 1 || dia > DIAS_NO_MES) {
            System.out.println("Data inválida.");
            return;
        }
        vendasPorMes.get(mes).put(dia, quantidadeVendas);
        System.out.println("Vendas salvas com sucesso.");
    }

    public static int buscarVendas(int mes, int dia) {
        if (mes < 1 || mes > MESES_NO_ANO || dia < 1 || dia > DIAS_NO_MES) {
            System.out.println("Data inválida.");
            return -1;
        }
        return vendasPorMes.get(mes).get(dia);
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Salvar vendas do dia");
            System.out.println("2. Buscar vendas do dia");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o mês (1-12): ");
                    int mesSalvar = scanner.nextInt();
                    System.out.print("Digite o dia (1-30): ");
                    int diaSalvar = scanner.nextInt();
                    System.out.print("Digite a quantidade de vendas: ");
                    int quantidadeVendas = scanner.nextInt();
                    salvarVendas(mesSalvar, diaSalvar, quantidadeVendas);
                    break;
                case 2:
                    System.out.print("Digite o mês (1-12): ");
                    int mesBuscar = scanner.nextInt();
                    System.out.print("Digite o dia (1-30): ");
                    int diaBuscar = scanner.nextInt();
                    int vendas = buscarVendas(mesBuscar, diaBuscar);
                    if (vendas != -1) {
                        System.out.println("Quantidade total de vendas: " + vendas);
                    }
                    break;
                case 3:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void main(String[] args) {
        menu();
    }
}
