package br.edu.ifpb.es.daw.app.service;

import br.edu.ifpb.es.daw.dao.*;
import br.edu.ifpb.es.daw.dao.impl.*;
import br.edu.ifpb.es.daw.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuCoordenador {

    private Scanner sc = new Scanner(System.in);

    private ProfessorDAO professorDAO = new ProfessorDAOImpl();
    private TurmaDAO turmaDAO = new TurmaDAOImpl();
    private ProfessorTurmaDAO professorTurmaDAO = new ProfessorTurmaDAOImpl();
    private AlunoDAO alunoDAO = new AlunosDAOImpl();
    private DisciplinaDAO disciplinaDAO = new DisciplinaDAOImpl();

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n===== MENU COORDENADOR =====");
            System.out.println("1 - Criar Professor");
            System.out.println("2 - Criar Aluno");
            System.out.println("3 - Criar Turma");
            System.out.println("4 - Vincular professor a disciplina");
            System.out.println("5 - Vincular professor a turma");
            System.out.println("6 - Desvincular professor de turma");
            System.out.println("7 - Gerar Boletim da turma");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> criarProfessor();
                case 2 -> criarAluno();
                case 3 -> criarTurma();
                case 4 -> vincularProfessorADisciplina();
                case 5 -> vincularProfessorATurma();
                case 6 -> desvincularProfessorDeTurma();
                case 7 -> gerarBoletimTurma();
                case 0 -> System.out.println("Saindo do menu...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void vincularProfessorADisciplina() {
        try {
            // 1️⃣ Listar todos os professores
            List<Professor> professores = professorDAO.getAll();
            if (professores.isEmpty()) {
                System.out.println("Não há professores cadastrados.");
                return;
            }

            System.out.println("Professores disponíveis:");
            for (Professor p : professores) {
                System.out.println(p.getId() + " - " + p.getNome());
            }

            // 2️⃣ Escolher professor
            System.out.print("Digite o ID do professor: ");
            Long professorId = Long.parseLong(sc.nextLine());

            // 3️⃣ Listar todas as disciplinas
            List<Disciplina> disciplinas = disciplinaDAO.getAll();
            if (disciplinas.isEmpty()) {
                System.out.println("Não há disciplinas cadastradas.");
                return;
            }

            System.out.println("Disciplinas disponíveis:");
            for (Disciplina d : disciplinas) {
                System.out.println(d.getId() + " - " + d.getNome());
            }

            // 4️⃣ Escolher disciplina
            System.out.print("Digite o ID da disciplina: ");
            Long disciplinaId = Long.parseLong(sc.nextLine());

            // 5️⃣ Vincular
            disciplinaDAO.linkProfessorDisciplina(professorId, disciplinaId);

            System.out.println("✅ Professor vinculado à disciplina com sucesso!");

        } catch (PersistenciaDawException e) {
            System.out.println("Erro ao vincular professor à disciplina: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite apenas números.");
        }
    }

    private void gerarBoletimTurma() {
        try {
            System.out.println("\n--- Gerar Boletim ---");

            List<Turma> turmas = turmaDAO.getAll();
            if (turmas.isEmpty()) {
                System.out.println("Nenhuma turma cadastrada. Cadastre uma turma primeiro.");
                return;
            }

            System.out.println("Turmas disponíveis:");
            for (Turma t : turmas) {
                System.out.println(t.getId() + " - " + t.getNome());
            }

            System.out.print("Digite o ID da turma para gerar o boletim: ");
            Long turmaId = Long.parseLong(sc.nextLine());
            Turma turma = turmaDAO.getByID(turmaId);

            if (turma == null) {
                System.out.println("Turma não encontrada.");
                return;
            }

            Connection conn = Conexao.getConexao();

            String sql = "SELECT * FROM mostrar_boletim_formatado_turma(?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setLong(1, turmaId);

                try (ResultSet rs = stmt.executeQuery()) {
                    System.out.println("\n--- Boletim da Turma ---\n");
                    while (rs.next()) {
                        String linha = rs.getString("linha");
                        System.out.println(linha);
                    }
                }
            }

        } catch (PersistenciaDawException e) {
            System.out.println("Erro ao buscar turmas: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao executar a função no banco: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
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
        try {
            System.out.println("\n--- Criar Aluno ---");


            List<Turma> turmas = turmaDAO.getAll();
            if (turmas.isEmpty()) {
                System.out.println("Nenhuma turma cadastrada. Cadastre uma turma primeiro.");
                return;
            }

            System.out.println("Turmas disponíveis:");
            for (Turma t : turmas) {
                System.out.println(t.getId() + " - " + t.getNome());
            }


            System.out.print("Digite o ID da turma para vincular o aluno: ");
            Long turmaId = Long.parseLong(sc.nextLine());
            Turma turma = turmaDAO.getByID(turmaId);

            if (turma == null) {
                System.out.println("Turma não encontrada.");
                return;
            }

            Aluno aluno = new Aluno();

            System.out.print("Nome: ");
            aluno.setNome(sc.nextLine());

            System.out.print("Data de Nascimento (AAAA-MM-DD): ");
            aluno.setDataNascimento(LocalDate.parse(sc.nextLine()));

            System.out.print("Matrícula: ");
            aluno.setMatricula(sc.nextLine());

            alunoDAO.save(aluno);

            System.out.println("Aluno criado com sucesso! ID: " + aluno.getId());

        } catch (PersistenciaDawException e) {
            System.out.println("Erro ao criar aluno: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
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
