package KAUAN_GIACOMIN.primeirob.listas;

import java.util.ArrayList;
import java.util.List;

class Vendedor {
    private String nome;
    private int idade;
    private String loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;
    private List<Double> salariosRecebidos;

    public Vendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        this.salariosRecebidos = new ArrayList<>();
    }

    public void apresentarSe() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
    }

    public void adicionarSalarioRecebido(double salario) {
        if (salariosRecebidos.size() < 3) {
            salariosRecebidos.add(salario);
        } else {
            System.out.println("Você já adicionou os 3 salários.");
        }
    }

    public double calcularMedia() {
        if (salariosRecebidos.isEmpty()) {
            System.out.println("Nenhum salário recebido foi registrado.");
            return 0.0;
        }

        double soma = 0.0;
        for (double salario : salariosRecebidos) {
            soma += salario;
        }
        return soma / salariosRecebidos.size();
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}

class Cliente {
    private String nome;
    private int idade;
    private String cidade;
    private String bairro;
    private String rua;

    public Cliente(String nome, int idade, String cidade, String bairro, String rua) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    public void apresentarSe() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
    }
}

class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;
    private List<Vendedor> vendedores;
    private List<Cliente> clientes;

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.vendedores = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public void apresentarSe() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + bairro + ", " + cidade);
    }

    public void adicionarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public int contarClientes() {
        return clientes.size();
    }

    public int contarVendedores() {
        return vendedores.size();
    }
}

public class lista5 {

    public static void main(String[] args) {
        Loja loja = criarLoja();
        Vendedor vendedor1 = criarVendedor1();
        Vendedor vendedor2 = criarVendedor2();
        Cliente cliente1 = criarCliente();

        loja.adicionarVendedor(vendedor1);
        loja.adicionarVendedor(vendedor2);
        loja.adicionarCliente(cliente1);

        loja.apresentarSe();

        System.out.println("Quantidade de clientes: " + loja.contarClientes());
        System.out.println("Quantidade de vendedores: " + loja.contarVendedores());
    }

    public static Loja criarLoja() {
        return new Loja("Myy Plant", "Myy Plant LTDA", "12.345.678/0001-90", "Centro", "Bairro X", "Rua Y");
    }

    public static Vendedor criarVendedor1() {
        Vendedor vendedor1 = new Vendedor("Gabrielinha", 45, "Myy Plant", "Centro", "Bairro X", "Rua Y", 5000.0);
        vendedor1.adicionarSalarioRecebido(5200.0);
        vendedor1.adicionarSalarioRecebido(5300.0);
        vendedor1.adicionarSalarioRecebido(5400.0);
        return vendedor1;
    }

    public static Vendedor criarVendedor2() {
        Vendedor vendedor2 = new Vendedor("João", 35, "Myy Plant", "Centro", "Bairro X", "Rua Y", 4500.0);
        vendedor2.adicionarSalarioRecebido(4600.0);
        vendedor2.adicionarSalarioRecebido(4700.0);
        vendedor2.adicionarSalarioRecebido(4800.0);
        return vendedor2;
    }

    public static Cliente criarCliente() {
        return new Cliente("Carlos", 30, "São Paulo", "Bairro X", "Rua Y");
    }
}



