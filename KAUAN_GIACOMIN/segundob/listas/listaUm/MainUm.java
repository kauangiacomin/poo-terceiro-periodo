package KAUAN_GIACOMIN.segundob.listas.listaUm;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainUm {
    public static void main(String[] args) {
        atv01();
        atv02();
        atv03();
        atv04();
    }

    private static void atv01() {
        JOptionPane.showMessageDialog(null, "Olá, Mundo!", "Atividae 01", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void atv02() {
        String nome = JOptionPane.showInputDialog("Qual seu nome?");
        JOptionPane.showMessageDialog(null, "Seja bem vindo " + nome, "Atividade 2", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void atv03() {
        List<String> options = new ArrayList<>(List.of("Opção 1", "Opção 2", "Opção 3"));
        String option = (String) JOptionPane.showInputDialog(
                null,
                "Escolha um opção",
                "Atividade 4",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options.toArray(),
                options.toArray()[0]
        );
        switch (option) {
            case "Opção 1" -> JOptionPane.showMessageDialog(null, "Opção 1 escolhida");
            case "Opção 2" -> JOptionPane.showMessageDialog(null, "Opção 2 escolhida");
            case "Opção 3" -> JOptionPane.showMessageDialog(null, "Opção 3 escolhida");
        }
    }

    private static void atv04() {
        try {
            throw new UmErroQualquerException("Algo de errado aconteceu! );");
        } catch (UmErroQualquerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Atividade 05", JOptionPane.ERROR_MESSAGE);
        }
    }
}
