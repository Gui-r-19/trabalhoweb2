package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Aluno;
import model.Turma;
import model.DAO.AlunoDAO;
import model.DAO.TurmaDAO;

@WebServlet("/VerVincularAlunos")
public class VerVincularAlunos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VerVincularAlunos() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idTurma = Integer.parseInt(request.getParameter("idTurma"));

		TurmaDAO turmaDAO = new TurmaDAO();
		Turma turma = turmaDAO.find(idTurma);
		request.setAttribute("turma", turma);

		AlunoDAO alunoDAO = new AlunoDAO();
		List<Aluno> todos = alunoDAO.getAll();
		List<Aluno> alunosTurma = alunoDAO.getAllByTurma(turma.getIdTurma());

		List<Aluno> alunos = new ArrayList<>();
		for (Aluno aluno : todos) {
		    boolean alunoPresenteNaTurma = alunosTurma.stream()
		            .anyMatch(alunoTurma -> alunoTurma.getIdAluno().equals(aluno.getIdAluno()));

		    if (!alunoPresenteNaTurma) {
		        alunos.add(aluno);
		    }
		}

		request.setAttribute("alunos", alunos);
		
		request.getRequestDispatcher("./vincularalunos.jsp").forward(request, response);
	}

}
