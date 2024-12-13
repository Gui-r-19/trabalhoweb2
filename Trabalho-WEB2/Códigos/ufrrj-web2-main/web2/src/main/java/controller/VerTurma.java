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
import model.DAO.AlunoDAO;
import model.DAO.AulaDAO;
import model.DAO.TurmaDAO;

@WebServlet("/VerTurma")
public class VerTurma extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VerTurma() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idTurma = Integer.parseInt(request.getParameter("idTurma"));
		
		TurmaDAO turmaDAO = new TurmaDAO();
		Turma turma = turmaDAO.find(idTurma);
		request.setAttribute("turma", turma);
		
		AulaDAO aulaDAO = new AulaDAO();
		List<Aula> aulas = aulaDAO.getAllByTurma(idTurma);
		request.setAttribute("aulas", aulas);
		
		AlunoDAO alunoDAO = new AlunoDAO();
		List<Aluno> alunos = alunoDAO.getAllByTurma(idTurma);
		request.setAttribute("alunos", alunos);
		
		request.getRequestDispatcher("./turma.jsp").forward(request, response);
	}

}
