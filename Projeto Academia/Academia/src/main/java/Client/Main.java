package Client;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Objects.Aluno;
import Objects.Horarios;
import Objects.Personal;

public class Main {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pratica04");
		EntityManager em = emf.createEntityManager();

		Scanner leia = new Scanner(System.in);
		int opcao = -1;

		System.out.println("//-------MENU PRINCIPAL-------\\\\");
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
		opcao = leia.nextInt();

		switch (opcao) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 0:
			break;
		default:
			break;
		}

		System.out.println("");
		leia.close();
	}
}