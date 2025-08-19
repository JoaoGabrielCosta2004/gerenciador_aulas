package br.edu.ifpb.es.daw.app.service;

import br.edu.ifpb.es.daw.dao.*;
import br.edu.ifpb.es.daw.dao.impl.*;
import br.edu.ifpb.es.daw.entities.*;
import br.edu.ifpb.es.daw.service.MinioService;

import java.io.File;
import java.io.FileInputStream;
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
    private MaterialDAO materialDAO = new MaterialDAOImpl();
    private MinioService minioService = new MinioService();

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
                case 1 -> fazerUploadMaterial(idProfessor);
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
            List<ProfessorTurma> profTurmas = professorTurmaDAO.findByProfessorId(idProfessor);


            List<Turma> turmas = new ArrayList<>();
            for (ProfessorTurma pt : profTurmas) {
                Turma t = turmaDAO.findById(pt.getTurmaId());
                if (t != null) turmas.add(t);
            }

            if (turmas.isEmpty()) {
                System.out.println("Você não possui turmas cadastradas.");
                return;
            }

            System.out.println("Turmas disponíveis:");
            for (Turma t : turmas) {
                System.out.println(t.getId() + " - " + t.getNome());
            }

            System.out.print("Digite o ID da turma para registrar a aula: ");
            Long turmaId = Long.parseLong(sc.nextLine());

            Aula aula = new Aula();
            aula.setId_turma(turmaId);
            aula.setIdProfessor(idProfessor);

            System.out.print("Digite a carga horaria para esta aula: ");
            int qtdFalta = Integer.parseInt(sc.nextLine());
            aula.setQuantidadeFalta(qtdFalta);

            System.out.print("Digite a data da aula (dd/MM/yyyy): ");
            String dataStr = sc.nextLine();
            aula.setData(dataStr);

            System.out.print("Digite o conteúdo da aula: ");
            aula.setConteudo(sc.nextLine());

            Long aulaId = aulaDAO.saveAndReturnId(aula);
            System.out.println("Aula registrada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao registrar aula.");
        }

    }

    private void registrarFrequenciaPorAula(Long idProfessor) {
        try {
            List<Aula> aulas = aulaDAO.buscarPorProfessor(idProfessor);
            if (aulas.isEmpty()) {
                System.out.println("Nenhuma aula registrada para este professor.");
                return;
            }

            System.out.println("Aulas registradas:");
            for (Aula a : aulas) {
                System.out.println(a.getId() + " - Data: " + a.getData() + ", Turma ID: " + a.getId_turma() + ", Conteúdo: " + a.getConteudo());
            }

            System.out.print("Digite o ID da aula para registrar a frequência: ");
            Long aulaId = Long.parseLong(sc.nextLine());

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

            List<Aluno> alunos = alunoDAO.buscarPorTurma(turmaId);
            if (alunos.isEmpty()) {
                System.out.println("Nenhum aluno encontrado para esta turma.");
                return;
            }

            System.out.println("Alunos da turma:");
            for (Aluno a : alunos) {
                System.out.println(a.getId() + " - " + a.getNome());
            }

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

                    if(faltas > aulaEscolhida.getQuantidadeFalta()){
                        System.out.println("Faltas não podem ser maior que a quantidade maxima. Ignorado para aluno " + alunoId);
                        continue;
                    }

                    alunosAulaDAO.inserir(alunoId, aulaId, faltas);

                } catch (NumberFormatException e) {
                    System.out.println("Erro ao ler ID ou quantidade de faltas: " + reg);
                }
            }

            System.out.println("Frequência registrada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao registrar frequência.");
        }
    }


    private void gerenciarNotas() {
        // implementar depois
    }



    private void fazerUploadMaterial(Long idProfessor) {
        try {
            System.out.println("=== Upload de Material ===");

            List<Aula> aulasProfessor = aulaDAO.buscarPorProfessor(idProfessor);
            if (aulasProfessor.isEmpty()) {
                System.out.println("Você não possui aulas cadastradas.");
                return;
            }

            System.out.println("Aulas disponíveis:");
            for (int i = 0; i < aulasProfessor.size(); i++) {
                Aula aula = aulasProfessor.get(i);
                System.out.printf("%d - %s (ID: %d)%n", i + 1, aula.getConteudo(), aula.getId());
            }

            System.out.print("Escolha o número da aula: ");
            int escolhaAula = Integer.parseInt(sc.nextLine()) - 1;
            if (escolhaAula < 0 || escolhaAula >= aulasProfessor.size()) {
                System.out.println("Opção inválida.");
                return;
            }
            Aula aulaEscolhida = aulasProfessor.get(escolhaAula);

            System.out.print("Título do material: ");
            String titulo = sc.nextLine();

            System.out.print("Tipo do material: ");
            String tipo = sc.nextLine();

            System.out.print("É uma avaliação? (S/N): ");
            boolean avaliacao = sc.nextLine().trim().equalsIgnoreCase("S");

            System.out.print("Digite o caminho do arquivo para upload: ");
            String caminhoArquivo = sc.nextLine();
            File arquivo = new File(caminhoArquivo);

            if (!arquivo.exists() || !arquivo.isFile()) {
                System.out.println("Arquivo não encontrado!");
                return;
            }

            String nomeArquivo = arquivo.getName();

            try (FileInputStream fis = new FileInputStream(arquivo)) {
                minioService.uploadFile(fis, nomeArquivo, arquivo.length());
            }

            String urlCompartilhamento = minioService.getPresignedUrl(nomeArquivo);

            Material material = new Material();
            material.setTitulo(titulo);
            material.setTipo(tipo);
            material.setId_aula(aulaEscolhida.getId());
            material.setAvaliacao(avaliacao);
            material.setLink(urlCompartilhamento);

            materialDAO.save(material);

            System.out.println("✅ Material salvo com sucesso!");
            System.out.println("📎 Link de acesso: " + urlCompartilhamento);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao fazer upload do material: " + e.getMessage());
        }
    }
}



