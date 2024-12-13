package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Aluno;
import model.Aula;
import model.Turma;
import model.DAO.AulaDAO;

@WebServlet("/VerAula")
public class VerAula extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VerAula() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idAula = Integer.parseInt(request.getParameter("idAula"));

		AulaDAO aulaDAO = new AulaDAO();
		Aula aula = aulaDAO.find(idAula);
		Turma turma = aula.getTurma();
		
		List<Aluno> alunos = turma.getAlunos();
		List<Aluno> alunosPresentes = aula.getAlunosPresentes();
		
		request.setAttribute("aula", aula);
		request.setAttribute("alunos", alunos);
		request.setAttribute("alunosPresentes", alunosPresentes);
		
		request.getRequestDispatcher("./aula.jsp").forward(request, response);
	}

}
