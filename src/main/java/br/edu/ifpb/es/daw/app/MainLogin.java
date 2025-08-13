package br.edu.ifpb.es.daw.app;

import br.edu.ifpb.es.daw.app.service.MenuCoordenador;
import br.edu.ifpb.es.daw.app.service.MenuProfessor;
import br.edu.ifpb.es.daw.app.service.UsuarioLogado;
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

            UsuarioLogado usuario = loginService.login(email, senha);

            if (usuario == null) {
                System.out.println("❌ Login inválido!");
            } else if (usuario.getTipo().equals("COORDENADOR")) {
                System.out.println("✅ Login como Coordenador realizado com sucesso!");
                MenuCoordenador menu = new MenuCoordenador();
                menu.exibirMenu();
            } else if (usuario.getTipo().equals("PROFESSOR")) {
                System.out.println("✅ Login como Professor realizado com sucesso!");
                MenuProfessor menu = new MenuProfessor(usuario.getId());
                menu.exibirMenu();
            }

        } catch (PersistenciaDawException e) {
            e.printStackTrace();
        }
    }
}