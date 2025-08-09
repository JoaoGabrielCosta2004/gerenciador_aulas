package br.edu.ifpb.es.daw.app;

import br.edu.ifpb.es.daw.app.service.MenuCoordenador;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.app.service.LoginService;

import java.util.Scanner;

public class MainLogin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoginService loginService = new LoginService();

        try {
            System.out.println("=== Sistema de Login ===");
            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Senha: ");
            String senha = sc.nextLine();

            String tipoUsuario = loginService.login(email, senha);

            if (tipoUsuario == null) {
                System.out.println("❌ Login inválido!");
            } else if (tipoUsuario.equals("COORDENADOR")) {
                System.out.println("✅ Login como Coordenador realizado com sucesso!");
                MenuCoordenador menu = new MenuCoordenador();
                menu.exibirMenu();
            } else if (tipoUsuario.equals("PROFESSOR")) {
                System.out.println("✅ Login como Professor realizado com sucesso!");

            }

        } catch (PersistenciaDawException e) {
            e.printStackTrace();
        }
    }
}