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
import Objects.Horarios;
import Objects.Personal;

public class Main {
	public static void main(String[] args) throws Exception {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("academia");
		EntityManager em = emf.createEntityManager();
		Scanner leia = new Scanner(System.in);
		SimpleDateFormat hora = new SimpleDateFormat("HH:mm");

		int opcao = -1, id, idade;
		String nome, plano, dia;
		double valor;
		Date inicio, fim;

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
			System.out.println("\n//---0---Sair do sistema");
			System.out.print("Opção: ");
			opcao = Integer.parseInt(leia.nextLine());

			switch (opcao) {

			case 1:
				System.out.print("Nome: ");
				nome = leia.nextLine();
				System.out.print("Idade: ");
				idade = Integer.parseInt(leia.nextLine());
				System.out.print("Plano: ");
				plano = leia.nextLine();

				em.getTransaction().begin();
				em.persist(new Aluno(nome, idade, plano));
				em.getTransaction().commit();
				break;

			case 2:
				System.out.print("ID do aluno: ");
				id = Integer.parseInt(leia.nextLine());
				System.out.print("Novo nome: ");
				nome = leia.nextLine();
				System.out.print("Nova idade: ");
				idade = Integer.parseInt(leia.nextLine());
				System.out.print("Novo plano: ");
				plano = leia.nextLine();

				em.getTransaction().begin();
				em.createQuery("update Aluno a set a.nome = :n, a.idade = :i, a.plano = :p where a.id = :id")
						.setParameter("n", nome).setParameter("i", idade).setParameter("p", plano)
						.setParameter("id", id).executeUpdate();
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
				id = Integer.parseInt(leia.nextLine());

				em.getTransaction().begin();
				em.createQuery("delete from Aluno a where a.id = :id").setParameter("id", id).executeUpdate();
				em.getTransaction().commit();
				break;

			case 5:
				System.out.print("Hora de início (HH:mm): ");
				inicio = hora.parse(leia.nextLine());
				System.out.print("Hora de fim (HH:mm): ");
				fim = hora.parse(leia.nextLine());
				System.out.print("Dia da semana: ");
				dia = leia.nextLine();

				em.getTransaction().begin();
				em.persist(new Horarios(inicio, fim, dia));
				em.getTransaction().commit();
				break;

			case 6:
				System.out.print("ID do horário: ");
				id = Integer.parseInt(leia.nextLine());
				System.out.print("Nova hora de início (HH:mm): ");
				inicio = hora.parse(leia.nextLine());
				System.out.print("Nova hora de fim (HH:mm): ");
				fim = hora.parse(leia.nextLine());
				System.out.print("Novo dia da semana: ");
				dia = leia.nextLine();

				em.getTransaction().begin();
				em.createQuery("update Horarios h set h.horaInicio = :i, h.horaFim = :f, h.diaSemana = :d where h.id = :id")
						.setParameter("i", inicio, TemporalType.TIMESTAMP)
						.setParameter("f", fim, TemporalType.TIMESTAMP)
						.setParameter("d", dia).setParameter("id", id).executeUpdate();
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
				id = Integer.parseInt(leia.nextLine());

				em.getTransaction().begin();
				em.createQuery("delete from Horarios h where h.id = :id").setParameter("id", id).executeUpdate();
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
				id = Integer.parseInt(leia.nextLine());
				System.out.print("Novo nome: ");
				nome = leia.nextLine();
				System.out.print("Novo valor da aula: ");
				valor = Double.parseDouble(leia.nextLine());

				em.getTransaction().begin();
				em.createQuery("update Personal p set p.nome = :n, p.valorAula = :v where p.id = :id")
						.setParameter("n", nome).setParameter("v", valor).setParameter("id", id).executeUpdate();
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
				id = Integer.parseInt(leia.nextLine());

				em.getTransaction().begin();
				em.createQuery("delete from Personal p where p.id = :id").setParameter("id", id).executeUpdate();
				em.getTransaction().commit();
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
