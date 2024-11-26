package KAUAN_GIACOMIN.segundob.listas.listaUm;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainDois {
    public static void main(String[] args) {
        List<String> options = new ArrayList<>(List.of("+", "-", "x", "/"));
        String option = (String) JOptionPane.showInputDialog(
                null,
                "Escolha um opção",
                "Atividade 4",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options.toArray(),
                options.toArray()[0]
        );

        Double num1;
        Double num2;
        try {
            String numStr1 = JOptionPane.showInputDialog("Numero 1");
            String numStr2 = JOptionPane.showInputDialog("Numero 2");

            num1 = Double.parseDouble(numStr1);
            num2 = Double.parseDouble(numStr2);
        } catch (NumberFormatException | NullPointerException e) {
            throw new UmErroQualquerException("Para calcular deve ser inserido um numero valido!");
        }

        double total = 0;
        switch (option) {
            case "+" -> total = num1 + num2;
            case "-" -> total = num1 - num2;
            case "x" -> total = num1 * num2;
            case "/" -> total = num1 / num2;
        }

        StringBuilder msg = new StringBuilder();
        msg.append("A operação: ").append(num1).append(" ").append(option).append(" ").append(num2).append(" = ").append(total);
        JOptionPane.showMessageDialog(null, msg, "Atv 06", JOptionPane.INFORMATION_MESSAGE);
    }
}