package br.edu.ifpb.es.daw.app.service;

import java.util.Scanner;

public class MenuProfessor {
    private Scanner sc = new Scanner(System.in);

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n===== MENU PROFESSOR =====");
            System.out.println("1 - Fazer Upload do Material");
            System.out.println("2 - Registrar Aula");
            System.out.println("3 - Gerenciar Gerenciar notas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> fazerUploadMaterial();
                case 2 -> registrarAula();
                case 3 -> gerenciarNotas();
                case 0 -> System.out.println("Saindo do menu...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void gerenciarNotas() {
    }

    private void registrarAula() {
    }

    private void fazerUploadMaterial() {

    }

}

