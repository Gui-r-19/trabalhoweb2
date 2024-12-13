package model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Aluno;

public class AlunoDAO {

	private EntityManagerFactory emf;

	public AlunoDAO() {
		emf = Persistence.createEntityManagerFactory("sistema-trabalho");
	}

	public List<Aluno> getAll() {
		EntityManager em = emf.createEntityManager();

		try {
			String jpql = "SELECT a FROM Aluno a";
			TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public List<Aluno> getAllByTurma(Integer idTurma) {
        EntityManager em = emf.createEntityManager();

        try {
        	String jpql = "SELECT DISTINCT a FROM Aluno a JOIN a.turmas t WHERE t.idTurma = :idTurma";
            TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
            query.setParameter("idTurma", idTurma);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

	public String create(String nome) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

			Aluno aluno = new Aluno(null, nome, null);

			em.persist(aluno);

			transaction.commit();

			return "Aluno adicionado!";
		} catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return "Erro ao adicionar aluno: " + e.getMessage();
        } finally {
			em.close();
		}
	}
	
	public Aluno find(Integer idAluno) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Aluno.class, idAluno);
        } finally {
            em.close();
        }
    }
	
	public String persist(Aluno aluno) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

			em.persist(aluno);

			transaction.commit();

			return "Aluno adicionado!";
		} catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return "Erro ao adicionar aluno: " + e.getMessage();
        } finally {
			em.close();
		}
	}
}
