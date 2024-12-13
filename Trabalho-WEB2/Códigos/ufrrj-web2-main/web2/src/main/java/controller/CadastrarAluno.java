package controller;
import model.DAO.AlunoDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CadastrarAluno")
public class CadastrarAluno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CadastrarAluno() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		AlunoDAO alunoDAO = new AlunoDAO();
		
		String nome = request.getParameter("nome");
		
		String mensagem = alunoDAO.create(nome);
		
		request.setAttribute("mensagem", mensagem);
		
		request.getRequestDispatcher("./mensagem.jsp").forward(request, response);
	}

}
