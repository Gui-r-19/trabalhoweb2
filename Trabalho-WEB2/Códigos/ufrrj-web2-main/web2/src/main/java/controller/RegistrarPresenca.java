package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import model.Aula;
import model.DAO.AlunoDAO;
import model.DAO.AulaDAO;

@WebServlet("/RegistrarPresenca")
public class RegistrarPresenca extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf;

	public RegistrarPresenca() {
		super();
		emf = Persistence.createEntityManagerFactory("sistema-trabalho");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		String mensagem = "";
		Map<String, String[]> map = new HashMap<>(request.getParameterMap());
		map.remove("idAula");

		if (map.isEmpty()) {
			mensagem = "formulário vazio";
			request.setAttribute("mensagem", mensagem);
			request.getRequestDispatcher("./mensagem.jsp").forward(request, response);
		}

		AlunoDAO alunoDAO = new AlunoDAO();

		Integer idAula = Integer.parseInt(request.getParameter("idAula"));
		AulaDAO aulaDAO = new AulaDAO();

		Map<String, String[]> presencaString = new HashMap<>(request.getParameterMap());
		presencaString.remove("idAula");

		Set<String> idPresencaString = presencaString.keySet();
		List<Integer> idPresenca = new ArrayList<>();
		for (String s : idPresencaString)
			idPresenca.add(Integer.parseInt(s));

		Collection<String[]> valorPresencaString = presencaString.values();
		List<Integer> valorPresenca = new ArrayList<>();
		for (String[] s : valorPresencaString)
			valorPresenca.add(Integer.parseInt(s[0]));

		try {
			
			Aula aula = aulaDAO.find(idAula);

			for (Integer id : idPresenca) {
				Aluno aluno = alunoDAO.find(id);
				Integer index = idPresenca.indexOf(id);
				
				List<Aluno> presentes = aula.getAlunosPresentes();

				if (valorPresenca.get(index) == 1) {
					transaction.begin();
					
					boolean alunoPresenteNaAula = presentes.stream()
							.anyMatch(alunoPresente -> alunoPresente.getIdAluno().equals(aluno.getIdAluno()));

					if (!alunoPresenteNaAula) {
						presentes.add(aluno);
						aula.setAlunosPresentes(presentes);
						aula = em.merge(aula);
					}
					
					transaction.commit();
				}

				else if (valorPresenca.get(index) == 0) {
					transaction.begin();
					
					presentes.removeIf(alunoPresente -> alunoPresente.getIdAluno().equals(aluno.getIdAluno()));
					aula.setAlunosPresentes(presentes);
					aula = em.merge(aula);
					
					transaction.commit();
				}
			}
			mensagem = "Registro Feito";
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			mensagem = "Erro ao registrar: " + e.getMessage();
		} finally {
			em.close();
		}

		request.setAttribute("mensagem", mensagem);
		request.getRequestDispatcher("./mensagem.jsp").forward(request, response);
	}

}
