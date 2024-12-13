package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Aluno;
import model.Turma;
import model.DAO.AlunoDAO;
import model.DAO.TurmaDAO;

@WebServlet("/VincularAlunos")
public class VincularAlunos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf;

	public VincularAlunos() {
		super();
		emf = Persistence.createEntityManagerFactory("sistema-trabalho");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		Integer idTurma = Integer.parseInt(request.getParameter("idTurma"));
		String[] idAlunoString = request.getParameterValues("idAluno");

		List<Integer> idAluno = new ArrayList<>();
		for (String s : idAlunoString)
			idAluno.add(Integer.parseInt(s));

		TurmaDAO turmaDAO = new TurmaDAO();
		Turma turma = turmaDAO.find(idTurma);

		AlunoDAO alunoDAO = new AlunoDAO();
		Aluno aluno = new Aluno();

		String mensagem = "";

		transaction.begin();
		try {
			for (Integer id : idAluno) {
				aluno = alunoDAO.find(id);

				List<Turma> turmas = aluno.getTurmas();
				turmas.add(turma);
				aluno.setTurmas(turmas);
				List<Aluno> alunos = turma.getAlunos();
				alunos.add(aluno);
				turma.setAlunos(alunos);

				em.merge(turma);
				em.merge(aluno);
			}
			transaction.commit();
			mensagem = "Aluno(s) vinculado(s)";
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			mensagem = "Erro ao vincular: " + e.getMessage();
		} finally {
			em.close();
		}
		
		request.setAttribute("mensagem", mensagem);
		request.getRequestDispatcher("./mensagem.jsp").forward(request, response);
	}

}
