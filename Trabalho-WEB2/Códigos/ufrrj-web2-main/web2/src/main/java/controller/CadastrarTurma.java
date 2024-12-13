package controller;
import model.DAO.TurmaDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CadastrarTurma")
public class CadastrarTurma extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CadastrarTurma() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		TurmaDAO TurmaDAO = new TurmaDAO();
		
		String disciplina = request.getParameter("disciplina");
		Integer ano = Integer.parseInt(request.getParameter("ano"));
		Integer periodo = Integer.parseInt(request.getParameter("periodo"));
		
		String mensagem = TurmaDAO.create(disciplina, ano, periodo);
		
		request.setAttribute("mensagem", mensagem);
		
		request.getRequestDispatcher("./mensagem.jsp").forward(request, response);
	}

}
