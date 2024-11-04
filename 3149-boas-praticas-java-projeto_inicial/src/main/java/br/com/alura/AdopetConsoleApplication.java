package br.com.alura;

import br.com.alura.presentation.*;

import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {
        CommandExecutor executor = new CommandExecutor();

        MensagemDeBoasVindas.mostrarMensagem();

        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {
                exibirMenu();

                String textoDigitado = new Scanner(System.in).nextLine();
                opcaoEscolhida = Integer.parseInt(textoDigitado);
                try {
                    if (opcaoEscolhida > 0 && opcaoEscolhida <= 5)
                        executor.executeCommand(executor.getCommand(opcaoEscolhida));
                } catch (Exception ignored) {
                    System.out.println("Houve um problema ao realizar a operação desejada.");
                }
            }
            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exibirMenu() {
        System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
        System.out.println("1 -> Listar abrigos cadastrados");
        System.out.println("2 -> Cadastrar novo abrigo");
        System.out.println("3 -> Listar pets do abrigo");
        System.out.println("4 -> Importar pets do abrigo");
        System.out.println("5 -> Sair");
    }
}
