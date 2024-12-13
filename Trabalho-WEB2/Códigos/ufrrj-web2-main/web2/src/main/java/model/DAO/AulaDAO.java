package model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Aula;
import model.Turma;

public class AulaDAO {

    private EntityManagerFactory emf;

    public AulaDAO() {
        emf = Persistence.createEntityManagerFactory("sistema-trabalho");
    }

    public List<Aula> getAll() {
        EntityManager em = emf.createEntityManager();

        try {
            String jpql = "SELECT a FROM Aula a";
            TypedQuery<Aula> query = em.createQuery(jpql, Aula.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Aula> getAllByTurma(Integer idTurma) {
        EntityManager em = emf.createEntityManager();

        try {
            String jpql = "SELECT a FROM Aula a WHERE a.turma.idTurma = :idTurma";
            TypedQuery<Aula> query = em.createQuery(jpql, Aula.class);
            query.setParameter("idTurma", idTurma);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public String create(String data, Turma turma) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

			Aula aula = new Aula(null, data, null, turma);

			em.persist(aula);

			transaction.commit();

			return "Aula adicionada!";
		} catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return "Erro ao adicionar aula: " + e.getMessage();
        } finally {
			em.close();
		}
	}
    
    public Aula find(Integer idAula) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Aula.class, idAula);
        } finally {
            em.close();
        }
    }
    
    public String persist(Aula aula) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

			em.persist(aula);

			transaction.commit();

			return "Aula adicionada!";
		} catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return "Erro ao adicionar aula: " + e.getMessage();
        } finally {
			em.close();
		}
	}
}