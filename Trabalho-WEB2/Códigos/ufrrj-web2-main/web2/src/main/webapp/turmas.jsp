<%@page import="model.DAO.TurmaDAO"%>
<%@page import="model.Turma"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
    TurmaDAO turmaDAO = new TurmaDAO();
    List<Turma> turmas = turmaDAO.getAll();
    request.setAttribute("turmas", turmas);
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">
<script src="script.js"></script>
<title>Turmas</title>
</head>
<body>
	<div class="titulo">
		<a href="./index.jsp">VOLTAR</a>
	</div>

	<c:forEach var="turma" items="${turmas}">
		<div class="box">
			<div class="metade">${turma.ano}.${turma.periodo} - ${turma.disciplina}</div>
			<div class="metade direita">
				<a href="./VerTurma?idTurma=${turma.idTurma}">VER</a>
			</div>
		</div>
	</c:forEach>


	<button class="box button" onclick="redirect('./cadastrarturma.jsp')">CADASTRAR
		TURMA</button>
</body>
</html>