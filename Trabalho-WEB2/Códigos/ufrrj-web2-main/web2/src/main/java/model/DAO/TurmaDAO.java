	package model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Turma;

public class TurmaDAO {

    private EntityManagerFactory emf;

    public TurmaDAO() {
        emf = Persistence.createEntityManagerFactory("sistema-trabalho");
    }

    public List<Turma> getAll() {
        EntityManager em = emf.createEntityManager();

        try {
            String jpql = "SELECT t FROM Turma t";
            TypedQuery<Turma> query = em.createQuery(jpql, Turma.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public String create(String disciplina, Integer ano, Integer periodo) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

			Turma turma = new Turma(null, disciplina, ano, periodo, null, null);

			em.persist(turma);

			transaction.commit();

			return "Turma adicionada!";
		} catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return "Erro ao adicionar Turma: " + e.getMessage();
        } finally {
			em.close();
		}
	}
    
    public Turma find(Integer idTurma) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Turma.class, idTurma);
        } finally {
            em.close();
        }
    }
    
    public String persist(Turma turma) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

			em.persist(turma);

			transaction.commit();

			return "Turma adicionada!";
		} catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return "Erro ao adicionar Turma: " + e.getMessage();
        } finally {
			em.close();
		}
	}
    
}
