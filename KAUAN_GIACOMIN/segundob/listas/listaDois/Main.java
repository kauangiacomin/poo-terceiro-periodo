package KAUAN_GIACOMIN.segundob.listas.listaDois;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    private static String token;
    private static final CelcoinService SERVICE = new CelcoinService();
    public static final JsonUtils JSON_UTILS = new JsonUtils();

    public static void main(String[] args) {
        token = SERVICE.generateToken();

        while (true) {
            String option = menu();
            if (option == null) return;

            switch (option) {
                case "Listar Convenios" -> listarConvenios();
                case "Consultar Boleto" -> consultarBoleto();
                default -> System.out.println("Default");
            }
        }
    }

    private static String menu() {
        List<String> options = new ArrayList<>(List.of("Listar Convenios", "Consultar Boleto"));

        return (String) JOptionPane.showInputDialog(
                null,
                "Escolha um opção",
                "Menu de Opção",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options.toArray(),
                options.toArray()[0]
        );
    }

    private static void listarConvenios() {
        try {
            String result = SERVICE.listAgreements(token);
            exibirInformacoes("Convenios", result);
        } catch (IOException | InterruptedException e) {
            exibirErro("Erro ao listar Convenios");
        }
    }

    private static void consultarBoleto() {
        String linhaDigitavel = obterLinhaDigitavel();

        try {
            String result = SERVICE.consultBill(token, linhaDigitavel);
            Map<String, Object> responseMap = JSON_UTILS.toMap(result);

            exibirInformacoes("Informações do Boleto", createStringResponse(responseMap));
        } catch (IOException | InterruptedException e) {
            exibirErro("Erro ao consultar boleto");
        }
    }

    private static String obterLinhaDigitavel() {
        return JOptionPane.showInputDialog("Qual a linha digitavel?");
    }

    private static void exibirInformacoes(String title, String content) {
        JOptionPane.showMessageDialog(
                null,
                content,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private static void exibirErro(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "Erro!",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private static String createStringResponse(Map<String, Object> response) {
        StringBuilder sb = new StringBuilder();

        response.forEach((k, v) -> {
            sb.append(k).append(": ").append(v).append("\n");
        });

        return sb.toString();
    }
}
