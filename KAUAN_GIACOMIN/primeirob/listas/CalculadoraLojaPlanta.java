package KAUAN_GIACOMIN.primeirob.listas;

import java.util.Scanner;

public class CalculadoraLojaPlanta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bem vindo a loja plantas\nSelecione uma opção:\n" +
                "[1] - Calcular Preço Total\n" +
                "[2] - Calcular Troco    \n" +
                "[3] - Sair\n");
        int recebeResultos = sc.nextInt();

        switch (recebeResultos) {
            case 1:
                System.out.println("Digite o preço da planta:");
                double precoUnitario = sc.nextDouble();

                System.out.println("Digite a quantidade de plantas:");
                int quantidade = sc.nextInt();

                double precoTotal = precoUnitario * quantidade;
                System.out.printf("O preço total é R$ %.2f\n", precoTotal);
                break;
            case 2:
                System.out.println("Digite o valor a ser pago:");
                double valorPago = sc.nextDouble();

                System.out.println("Digite o preço total:");
                double precoTotal2 = sc.nextDouble();

                double troco = valorPago - precoTotal2;
                System.out.printf("O troco é R$ %.2f\n", troco);
                break;
            case 3:
                System.out.println("Até mais!");
                break;
            default:
                System.out.println("Bugou a calculadora!");
        }
        sc.close();
    }
}
