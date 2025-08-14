package br.edu.ifpb.es.daw.app.service;

import br.edu.ifpb.es.daw.dao.*;
import br.edu.ifpb.es.daw.dao.impl.*;
import br.edu.ifpb.es.daw.entities.Aluno;
import br.edu.ifpb.es.daw.entities.Aula;
import br.edu.ifpb.es.daw.entities.ProfessorTurma;
import br.edu.ifpb.es.daw.entities.Turma;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuProfessor {
    private Scanner sc = new Scanner(System.in);
    private Long idProfessor;

    private ProfessorTurmaDAO professorTurmaDAO = new ProfessorTurmaDAOImpl();
    private AulaDAO aulaDAO = new AulaDAOImpl();
    private AlunoDAO alunoDAO = new AlunosDAOImpl();
    private TurmaDAO turmaDAO = new TurmaDAOImpl();
    private AlunoAulaDAO alunosAulaDAO = new AlunoAulaDAOImpl();

    public MenuProfessor(Long idProfessor) {
        this.idProfessor = idProfessor;
        System.out.println("Professor logado com ID: " + idProfessor);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n===== MENU PROFESSOR =====");
            System.out.println("1 - Fazer Upload do Material");
            System.out.println("2 - Registrar Aula");
            System.out.println("3 - Registrar Frequencia por Aula");
            System.out.println("4 - Gerenciar notas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> fazerUploadMaterial();
                case 2 -> registrarAula(idProfessor);
                case 3 -> registrarFrequenciaPorAula(idProfessor);
                case 4 -> gerenciarNotas();
                case 0 -> System.out.println("Saindo do menu...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void registrarAula(Long idProfessor) {
        try {
            // 1. Buscar associações professor-turma
            List<ProfessorTurma> profTurmas = professorTurmaDAO.findByProfessorId(idProfessor);

            // 2. Transformar em lista de Turma
            List<Turma> turmas = new ArrayList<>();
            for (ProfessorTurma pt : profTurmas) {
                Turma t = turmaDAO.findById(pt.getTurmaId()); // buscar turma real
                if (t != null) turmas.add(t);
            }

            if (turmas.isEmpty()) {
                System.out.println("Você não possui turmas cadastradas.");
                return;
            }

            // 3. Mostrar turmas
            System.out.println("Turmas disponíveis:");
            for (Turma t : turmas) {
                System.out.println(t.getId() + " - " + t.getNome());
            }

            // 4. Escolher turma
            System.out.print("Digite o ID da turma para registrar a aula: ");
            Long turmaId = Long.parseLong(sc.nextLine());

            // 5. Criar aula
            Aula aula = new Aula();
            aula.setId_turma(turmaId);
            aula.setIdProfessor(idProfessor); // registrar professor

            // Perguntar quantidade mínima de falta
            System.out.print("Digite a quantidade mínima de falta para esta aula: ");
            int qtdFalta = Integer.parseInt(sc.nextLine());
            aula.setQuantidadeFalta(qtdFalta);

            // Perguntar data da aula
            System.out.print("Digite a data da aula (dd/MM/yyyy): ");
            String dataStr = sc.nextLine();
            aula.setData(dataStr);

            // Perguntar conteúdo
            System.out.print("Digite o conteúdo da aula: ");
            aula.setConteudo(sc.nextLine());

            // 6. Salvar aula e obter ID
            Long aulaId = aulaDAO.saveAndReturnId(aula);
            System.out.println("✅ Aula registrada com sucesso! ID: " + aulaId);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao registrar aula.");
        }

    }

    private void registrarFrequenciaPorAula(Long idProfessor) {
        try {
            // 1. Buscar todas as aulas do professor
            List<Aula> aulas = aulaDAO.buscarPorProfessor(idProfessor);
            if (aulas.isEmpty()) {
                System.out.println("Nenhuma aula registrada para este professor.");
                return;
            }

            // 2. Mostrar lista de aulas
            System.out.println("Aulas registradas:");
            for (Aula a : aulas) {
                System.out.println(a.getId() + " - Data: " + a.getData() + ", Turma ID: " + a.getId_turma() + ", Conteúdo: " + a.getConteudo());
            }

            // 3. Escolher aula
            System.out.print("Digite o ID da aula para registrar a frequência: ");
            Long aulaId = Long.parseLong(sc.nextLine());

            // 4. Buscar a aula escolhida
            Aula aulaEscolhida = null;
            for (Aula a : aulas) {
                if (a.getId().equals(aulaId)) {
                    aulaEscolhida = a;
                    break;
                }
            }

            if (aulaEscolhida == null) {
                System.out.println("Aula não encontrada.");
                return;
            }

            Long turmaId = aulaEscolhida.getId_turma();

            // 5. Buscar alunos da turma
            List<Aluno> alunos = alunoDAO.buscarPorTurma(turmaId);
            if (alunos.isEmpty()) {
                System.out.println("Nenhum aluno encontrado para esta turma.");
                return;
            }

            System.out.println("Alunos da turma:");
            for (Aluno a : alunos) {
                System.out.println(a.getId() + " - " + a.getNome());
            }

            // 6. Registrar faltas
            System.out.println("Digite as faltas no formato idAluno/qtdFaltas separados por espaço:");
            String entrada = sc.nextLine(); // ex: "1/2 3/1 4/0"
            String[] registros = entrada.split(" ");

            for (String reg : registros) {
                String[] partes = reg.split("/");
                if (partes.length != 2) {
                    System.out.println("Formato inválido: " + reg);
                    continue;
                }

                try {
                    Long alunoId = Long.parseLong(partes[0]);
                    int faltas = Integer.parseInt(partes[1]);

                    if (faltas < 0) {
                        System.out.println("Faltas não podem ser negativas. Ignorado para aluno " + alunoId);
                        continue;
                    }

                    alunosAulaDAO.inserir(alunoId, aulaId, faltas);

                } catch (NumberFormatException e) {
                    System.out.println("Erro ao ler ID ou quantidade de faltas: " + reg);
                }
            }

            System.out.println("✅ Frequência registrada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao registrar frequência.");
        }
    }


    private void gerenciarNotas() {
        // implementar depois
    }



    private void fazerUploadMaterial() {

    }

}

