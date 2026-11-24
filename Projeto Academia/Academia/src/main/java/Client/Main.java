package Client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TemporalType;

import Objects.Aluno;
import Objects.Aluno_personal;
import Objects.Horarios;
import Objects.Personal;

public class Main {
	public static void main(String[] args) throws Exception {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("academia");
		EntityManager em = emf.createEntityManager();
		Scanner leia = new Scanner(System.in);
		SimpleDateFormat hora = new SimpleDateFormat("HH:mm");

		int opcao = -1, id, idade, idPersonal, idAula;
		String nome, plano, dia;
		double valor;
		String inicio, fim;
		
		

		while (opcao != 0) {

			System.out.println("\n//-------MENU PRINCIPAL-------\\\\");
			System.out.println("\n//-------ALUNOS-------\\\\");
			System.out.println("//---1---Cadastrar Aluno");
			System.out.println("//---2---Editar Aluno");
			System.out.println("//---3---Listar Alunos");
			System.out.println("//---4---Apagar Aluno");
			System.out.println("\n//-------HORARIOS-------\\\\");
			System.out.println("//---5---Cadastrar Horario");
			System.out.println("//---6---Editar Horario");
			System.out.println("//---7---Listar Horarios");
			System.out.println("//---8---Apagar Horario");
			System.out.println("\n//-------PERSONAIS-------\\\\");
			System.out.println("//---9---Cadastrar Personal");
			System.out.println("//---10---Editar Personal");
			System.out.println("//---11---Listar Personais");
			System.out.println("//---12---Apagar Personal");
			System.out.println("\n//-------AULAS (ALUNO x PERSONAL)-------\\\\");
			System.out.println("//---13---Cadastrar Aula");
			System.out.println("//---14---Listar Aulas");
			System.out.println("//---15---Apagar Aulas");
			System.out.println("//---16---Alterar horário aula");
			System.out.println("//---17---Listar Horarios com Aluno e Personal");
			System.out.println("\n//---0---Sair do sistema");
			System.out.print("Opção: ");
			opcao = leia.nextInt();

			switch (opcao) {

			case 1:
				System.out.print("Nome: ");
				nome = leia.nextLine();
				System.out.print("Idade: ");
				idade = leia.nextInt();
				System.out.print("Plano: ");
				plano = leia.nextLine();

				em.getTransaction().begin();
				em.persist(new Aluno(nome, idade, plano));
				em.getTransaction().commit();
				break;

			case 2:
				System.out.print("ID do aluno: ");
				id = leia.nextInt();
				System.out.print("Novo nome: ");
				nome = leia.nextLine();
				System.out.print("Nova idade: ");
				idade = leia.nextInt();
				System.out.print("Novo plano: ");
				plano = leia.nextLine();

				em.getTransaction().begin();

				Aluno novosDadosAluno = em.find(Aluno.class, id);
				novosDadosAluno.setNome(nome);
				novosDadosAluno.setIdade(idade);
				novosDadosAluno.setPlano(plano);
				em.getTransaction().commit();
				break;

			case 3:
				List<Object[]> alunos = em.createQuery("select a.id, a.nome, a.idade, a.plano from Aluno a",
						Object[].class).getResultList();
				for (Object[] a : alunos) {
					System.out.println(a[0] + " - " + a[1] + " - " + a[2] + " anos - " + a[3]);
				}
				break;

			case 4:
				System.out.print("ID do aluno: ");
				id = leia.nextInt();

				em.getTransaction().begin();
				Aluno deletarAluno = em.find(Aluno.class, id);
				em.remove(deletarAluno);
				em.getTransaction().commit();
				break;

			case 5:
				System.out.print("Hora de início (HH:mm): ");
				inicio = leia.nextLine();
				System.out.print("Hora de fim (HH:mm): ");
				fim = leia.nextLine();
				System.out.print("Dia da semana: ");
				dia = leia.nextLine();

				em.getTransaction().begin();
				em.persist(new Horarios(inicio, fim, dia));
				em.getTransaction().commit();
				break;

			case 6:
				System.out.print("ID do horário: ");
				id = leia.nextInt();
				System.out.print("Nova hora de início (HH:mm): ");
				inicio = leia.nextLine();
				System.out.print("Nova hora de fim (HH:mm): ");
				fim = leia.nextLine();
				System.out.print("Novo dia da semana: ");
				dia = leia.nextLine();

				Horarios novosDadosHorario = em.find(Horarios.class, id);
				novosDadosHorario.setHoraInicio(inicio);
				novosDadosHorario.setHoraFim(fim);
				novosDadosHorario.setDiaSemana(dia);
				em.getTransaction().commit();
				break;

			case 7:
				List<Object[]> horarios = em.createQuery(
						"select h.id, h.diaSemana, h.horaInicio, h.horaFim from Horarios h", Object[].class)
						.getResultList();
				for (Object[] h : horarios) {
					System.out.println(h[0] + " - " + h[1] + " - " + hora.format(h[2]) + " às " + hora.format(h[3]));
				}
				break;

			case 8:
				System.out.print("ID do horário: ");
				id = leia.nextInt();

				em.getTransaction().begin();
				Horarios deletarHorario = em.find(Horarios.class, id);
				em.remove(deletarHorario);
				em.getTransaction().commit();
				break;

			case 9:
				System.out.print("Nome: ");
				nome = leia.nextLine();
				System.out.print("Valor da aula: ");
				valor = Double.parseDouble(leia.nextLine());

				em.getTransaction().begin();
				em.persist(new Personal(nome, valor));
				em.getTransaction().commit();
				break;

			case 10:
				System.out.print("ID do personal: ");
				id = leia.nextInt();
				System.out.print("Novo nome: ");
				nome = leia.nextLine();
				System.out.print("Novo valor da aula: ");
				valor = Double.parseDouble(leia.nextLine());

				em.getTransaction().begin();
				Personal novosDadosPersonal = em.find(Personal.class, id);
				novosDadosPersonal.setNome(nome);
				novosDadosPersonal.setValorAula(valor);
				em.getTransaction().commit();
				break;

			case 11:
				List<Object[]> personais = em.createQuery("select p.id, p.nome, p.valorAula from Personal p",
						Object[].class).getResultList();
				for (Object[] p : personais) {
					System.out.println(p[0] + " - " + p[1] + " - R$ " + p[2]);
				}
				break;

			case 12:
				System.out.print("ID do personal: ");
				id = leia.nextInt();

				em.getTransaction().begin();
				Personal deletarPersonal = em.find(Personal.class, id);
				em.remove(deletarPersonal);
				em.getTransaction().commit();
				break;

			case 13:
				System.out.print("ID do aluno: ");
				id = leia.nextInt();
				System.out.print("ID do personal: ");
				idPersonal = leia.nextInt();

				Aluno aluno = em.find(Aluno.class, id);
				Personal personal = em.find(Personal.class, idPersonal);
				if (aluno == null || personal == null) {
					System.out.println("Aluno ou personal não encontrado!");
					break;
				}
				em.getTransaction().begin();
				em.persist(new Aluno_personal(aluno, personal));
				em.getTransaction().commit();
				break;

			case 14:
				List<Object[]> aulas = em.createQuery(
						"select ap.id, a.nome, p.nome, p.valorAula "
						+ "from Aluno_personal ap join ap.aluno a join ap.personal p",
						Object[].class).getResultList();
				for (Object[] v : aulas) {
					System.out.println("Vínculo " + v[0] + " - Aluno: " + v[1] + " - Personal: " + v[2] + " - R$ " + v[3]);
				}
				break;

			case 15:
				System.out.print("ID do vínculo: ");
				idAula = leia.nextInt();

				em.getTransaction().begin();
				Aluno_personal deletarAluno_Personal = em.find(Aluno_personal.class, idAula);
				em.remove(deletarAluno_Personal);
				em.getTransaction().commit();
				break;

			case 16:
				System.out.print("ID do horário: ");
				id = leia.nextInt();
				System.out.print("ID do vínculo: ");
				idAula = leia.nextInt();

				Horarios h1 = em.find(Horarios.class, id);
				Aluno_personal vinc = em.find(Aluno_personal.class, idAula);
				if (h1 == null || vinc == null) {
					System.out.println("Horário ou vínculo não encontrado!");
					break;
				}
				em.getTransaction().begin();
				h1.setAluno_Personal(vinc);
				em.getTransaction().commit();
				break;

			case 17:
				List<Object[]> agenda = em.createQuery(
						"select h.diaSemana, h.horaInicio, h.horaFim, a.nome, p.nome "
						+ "from Horarios h join h.aluno_personal ap join ap.aluno a join ap.personal p",
						Object[].class).getResultList();
				for (Object[] r : agenda) {
					System.out.println(r[0] + " " + hora.format(r[1]) + " às " + hora.format(r[2])
							+ " - Aluno: " + r[3] + " - Personal: " + r[4]);
				}
				break;

			case 0:
				System.out.println("Saindo...");
				break;

			default:
				System.out.println("Opção inválida!");
				break;
			}
		}

		leia.close();
		em.close();
		emf.close();
	}
}
