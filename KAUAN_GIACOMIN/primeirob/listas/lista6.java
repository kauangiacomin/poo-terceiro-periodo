package KAUAN_GIACOMIN.primeirob.listas;


import java.util.Date;
import java.util.Scanner;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;

class Endereco {
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;

    public Endereco(String estado, String cidade, String bairro, String rua, int numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        System.out.println(rua + ", " + numero + ", " + bairro + ", " + cidade + " - " + estado + " " + complemento);
    }
}


class Item {
    private int id;
    private String nome;
    private String tipo;
    private double valor;

    public Item(int id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public void gerarDescricao() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + tipo);
        System.out.println("Valor: R$ " + valor);
    }

    public double getValor() {
        return valor;
    }
}

class Pedido {
    private int id;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataVencimentoReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private String loja;
    private Item[] itens;

    public Pedido(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, Cliente cliente, Vendedor vendedor, String loja, Item[] itens) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
    }

    public double calcularValorTotal() {
        double valorTotal = 0.0;
        for (Item item : itens) {
            valorTotal += item.getValor();
        }
        return valorTotal;
    }

    public void gerarDescricaoVenda() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Data de Criação do Pedido: " + sdf.format(dataCriacao));
        System.out.println("Valor Total do Pedido: R$ " + calcularValorTotal());
    }

    public int getId() {
        return id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public Date getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }
}

class Clientee {
    private String nome;
    private int idade;
    private Endereco endereco;

    public Clientee(String nome, int idade, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }
}

class Vendedorr {
    private String nome;
    private int idade;
    private Endereco endereco;
    private double salarioBase;

    public Vendedorr(String nome, int idade, Endereco endereco, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.salarioBase = salarioBase;
    }

    public double getSalarioBase() {
        return salarioBase;
    }
}

class ProcessaPedido {

    public Pedido processar(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, Cliente cliente, Vendedor vendedor, String loja, Item[] itens) {
        return new Pedido(id, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja, itens);
    }

    public boolean confirmarPagamento(Pedido pedido) {
        if (verificarValidadeReserva(pedido.getDataVencimentoReserva())) {
            System.out.println("Pagamento confirmado para o pedido ID: " + pedido.getId());
            return true;
        } else {
            System.out.println("Reserva vencida. Pagamento não pode ser confirmado para o pedido ID: " + pedido.getId());
            return false;
        }
    }

    private boolean verificarValidadeReserva(Date dataVencimentoReserva) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataVencimentoReserva);
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        Date dataVencimentoReservaFinal = calendar.getTime();
        Date dataAtual = new Date();
        return dataAtual.before(dataVencimentoReservaFinal);
    }
}

public class lista6 {

    private static Scanner scanner = new Scanner(System.in);
    private static ProcessaPedido processaPedido = new ProcessaPedido();

    public static void main(String[] args) {
        Endereco endereco = new Endereco("SP", "São Paulo", "Jardins", "Rua A", 100, "Apto 101");
        Cliente cliente = new Cliente("João", 35, endereco);
        Vendedor vendedor = new Vendedor("Ana", 30, endereco, 2500.0);

        Item item1 = new Item(1, "Cadeira", "Mobiliário", 250.0);
        Item item2 = new Item(2, "Mesa", "Mobiliário", 450.0);
        Item[] itens = {item1, item2};

        String loja = "Loja Centro";

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Criar Pedido");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarPedido(cliente, vendedor, loja, itens);
                    break;
                case 2:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void criarPedido(Cliente cliente, Vendedor vendedor, String loja, Item[] itens) {
        System.out.print("Informe o ID do pedido: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Informe a data de criação do pedido (formato: yyyy-MM-dd): ");
        Date dataCriacao = lerData();

        System.out.print("Informe a data de pagamento do pedido (formato: yyyy-MM-dd): ");
        Date dataPagamento = lerData();

        System.out.print("Informe a data de vencimento da reserva do pedido (formato: yyyy-MM-dd): ");
        Date dataVencimentoReserva = lerData();

        Pedido pedido = processaPedido.processar(id, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja, itens);

        if (processaPedido.confirmarPagamento(pedido)) {
            System.out.println("Pedido criado com sucesso!");
            pedido.gerarDescricaoVenda();
        }
    }

    private static Date lerData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        while (true) {
            try {
                String dataStr = scanner.nextLine();
                return sdf.parse(dataStr);
            } catch (ParseException e) {
                System.out.println("Data inválida. Por favor, insira a data no formato correto (yyyy-MM-dd): ");
            }
        }
    }
}
