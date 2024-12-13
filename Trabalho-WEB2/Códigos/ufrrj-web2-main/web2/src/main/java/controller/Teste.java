package controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Aluno;
import model.Aula;
import model.Turma;

public class Teste {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistema-trabalho");
		EntityManager em = emf.createEntityManager();

		Aluno aluno1 = new Aluno(null, "Guilherme", null);
		Aluno aluno2 = new Aluno(null, "Carlos", null);

		Turma turma1 = new Turma(null, "SISTEMAS WEB II", 2024, 2, null, null);
		Turma turma2 = new Turma(null, "AED II", 2024, 2, null, null);
		Aula aula1 = new Aula(null, "01/12/2024", null, turma1);
		Aula aula2 = new Aula(null, "02/12/2024", null, turma1);
		Aula aula3 = new Aula(null, "03/12/2024", null, turma2);
		Aula aula4 = new Aula(null, "04/12/2024", null, turma2);

		// ----- Adicionando aluno a turma -----

		List<Turma> turmas1 = new ArrayList<>();
		turmas1.add(turma1);
		aluno1.setTurmas(turmas1);
		List<Aluno> alunos1 = new ArrayList<>();
		alunos1.add(aluno1);
		turma1.setAlunos(alunos1);

		List<Turma> turmas2 = new ArrayList<>();
		turmas2.add(turma2);
		aluno2.setTurmas(turmas2);
		List<Aluno> alunos2 = new ArrayList<>();
		alunos2.add(aluno2);
		turma2.setAlunos(alunos2);

		// --------------------------------------------

		// ----- Adicionando um aluno a uma aula -----
		//List<Aluno> presentes = aula1.getAlunosPresentes();
		//if (!presentes.contains(aluno2)) {
		//	presentes.add(aluno2);
		//	aula1.setAlunosPresentes(presentes);
		//}
		// --------------------------------------------

		try {
			em.getTransaction().begin();
			em.persist(aluno1);
			em.persist(aluno2);
			em.persist(turma1);
			em.persist(turma2);
			em.persist(aula1);
			em.persist(aula2);
			em.persist(aula3);
			em.persist(aula4);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
			emf.close();
		}
	}

}