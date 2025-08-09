package br.edu.ifpb.es.daw.app.service;

import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.ProfessorDAO;
import br.edu.ifpb.es.daw.dao.ProfessorTurmaDAO;
import br.edu.ifpb.es.daw.dao.TurmaDAO;
import br.edu.ifpb.es.daw.dao.impl.ProfessorDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.ProfessorTurmaDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.TurmaDAOImpl;
import br.edu.ifpb.es.daw.entities.Professor;
import br.edu.ifpb.es.daw.entities.ProfessorTurma;
import br.edu.ifpb.es.daw.entities.Turma;

import java.util.List;
import java.util.Scanner;

public class MenuCoordenador {

    private Scanner sc = new Scanner(System.in);

    private ProfessorDAO professorDAO = new ProfessorDAOImpl();
    private TurmaDAO turmaDAO = new TurmaDAOImpl();
    private ProfessorTurmaDAO professorTurmaDAO = new ProfessorTurmaDAOImpl();

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n===== MENU COORDENADOR =====");
            System.out.println("1 - Criar Professor");
            System.out.println("2 - Criar Aluno");
            System.out.println("3 - Criar Turma");
            System.out.println("4 - Vincular professor a turma");
            System.out.println("5 - Desvincular professor de turma");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> criarProfessor();
                case 2 -> criarAluno();
                case 3 -> criarTurma();
                case 4 -> vincularProfessorATurma();
                case 5 -> desvincularProfessorDeTurma();
                case 0 -> System.out.println("Saindo do menu...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void criarProfessor() {
        try {
            System.out.println("\n--- Criar Professor ---");
            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Senha: ");
            String senha = sc.nextLine();

            Professor professor = new Professor();
            professor.setNome(nome);
            professor.setEmail(email);
            professor.setSenha(senha);

            professorDAO.save(professor); // salva e seta o ID no objeto professor

            System.out.println("Professor criado com sucesso! ID: " + professor.getId());

        } catch (PersistenciaDawException e) {
            System.out.println("Erro ao criar professor: " + e.getMessage());
        }
    }

    private void criarAluno() {
        System.out.println("\n--- Criar Aluno ---");
        // Implemente aqui conforme seu projeto
    }

    private void criarTurma() {
        try {
            System.out.println("\n--- Criar Turma ---");
            Turma turma = new Turma();

            System.out.print("Nome: ");
            turma.setNome(sc.nextLine());

            System.out.print("Ano: ");
            turma.setAno(Integer.parseInt(sc.nextLine()));

            turmaDAO.save(turma);
            System.out.println("Turma salva com sucesso!");
        } catch (PersistenciaDawException e) {
            System.out.println("Erro ao salvar turma: " + e.getMessage());
        }
    }

    private void vincularProfessorATurma() {
        try {
            System.out.println("\n--- Vincular Professor a Turma ---");

            List<Professor> professores = professorDAO.getAll();
            if (professores.isEmpty()) {
                System.out.println("Nenhum professor cadastrado.");
                return;
            }
            System.out.println("Professores cadastrados:");
            for (Professor p : professores) {
                System.out.println(p.getId() + " - " + p.getNome());
            }
            System.out.print("Digite o ID do professor: ");
            Long professorId = Long.parseLong(sc.nextLine());

            Professor professor = professorDAO.getByID(professorId);
            if (professor == null) {
                System.out.println("Professor não encontrado.");
                return;
            }

            List<Turma> turmas = turmaDAO.getAll();
            if (turmas.isEmpty()) {
                System.out.println("Nenhuma turma cadastrada.");
                return;
            }
            System.out.println("\nTurmas disponíveis:");
            for (Turma t : turmas) {
                System.out.println(t.getId() + " - " + t.getNome());
            }
            System.out.print("Digite o ID da turma: ");
            Long turmaId = Long.parseLong(sc.nextLine());

            Turma turma = turmaDAO.getByID(turmaId);
            if (turma == null) {
                System.out.println("Turma não encontrada.");
                return;
            }

            if (!professorTurmaDAO.existsAssociation(professorId, turmaId)) {
                ProfessorTurma pt = new ProfessorTurma();
                pt.setProfessorId(professorId);
                pt.setTurmaId(turmaId);
                professorTurmaDAO.save(pt);
                System.out.println("Professor vinculado à turma com sucesso!");
            } else {
                System.out.println("Esse professor já está vinculado a essa turma.");
            }
        } catch (PersistenciaDawException e) {
            System.out.println("Erro ao vincular professor à turma: " + e.getMessage());
        }
    }

    private void desvincularProfessorDeTurma() {
        try {
            System.out.println("\n--- Desvincular Professor de Turma ---");

            List<Professor> professores = professorDAO.getAll();
            if (professores.isEmpty()) {
                System.out.println("Nenhum professor cadastrado.");
                return;
            }
            System.out.println("Professores cadastrados:");
            for (Professor p : professores) {
                System.out.println(p.getId() + " - " + p.getNome());
            }
            System.out.print("Digite o ID do professor: ");
            Long professorId = Long.parseLong(sc.nextLine());

            Professor professor = professorDAO.getByID(professorId);
            if (professor == null) {
                System.out.println("Professor não encontrado.");
                return;
            }

            List<ProfessorTurma> professorTurmas = professorTurmaDAO.findByProfessorId(professorId);
            if (professorTurmas.isEmpty()) {
                System.out.println("Esse professor não está vinculado a nenhuma turma.");
                return;
            }
            System.out.println("\nTurmas vinculadas ao professor:");
            for (ProfessorTurma pt : professorTurmas) {
                Turma turma = turmaDAO.getByID(pt.getTurmaId());
                if (turma != null) {
                    System.out.println(turma.getId() + " - " + turma.getNome());
                }
            }
            System.out.print("Digite o ID da turma para desvincular: ");
            Long turmaId = Long.parseLong(sc.nextLine());

            if (professorTurmaDAO.existsAssociation(professorId, turmaId)) {
                professorTurmaDAO.delete(professorId, turmaId);
                System.out.println("Professor desvinculado da turma com sucesso!");
            } else {
                System.out.println("Não existe vínculo entre esse professor e essa turma.");
            }
        } catch (PersistenciaDawException e) {
            System.out.println("Erro ao desvincular professor da turma: " + e.getMessage());
        }
    }
}
