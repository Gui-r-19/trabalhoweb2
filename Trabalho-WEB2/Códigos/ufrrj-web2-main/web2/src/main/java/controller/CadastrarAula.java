package controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Turma;
import model.DAO.AulaDAO;
import model.DAO.TurmaDAO;

@WebServlet("/CadastrarAula")
public class CadastrarAula extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CadastrarAula() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String data = request.getParameter("data");
		Integer idTurma = Integer.parseInt(request.getParameter("idTurma"));
		
		TurmaDAO turmaDAO = new TurmaDAO();
		Turma turma = turmaDAO.find(idTurma);
		
		AulaDAO aulaDAO = new AulaDAO();
		
		String mensagem = aulaDAO.create(data, turma);
		
		request.setAttribute("mensagem", mensagem);
		
		request.getRequestDispatcher("./mensagem.jsp").forward(request, response);
	}

}
