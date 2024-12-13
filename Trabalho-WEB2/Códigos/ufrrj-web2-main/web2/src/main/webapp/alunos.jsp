<%@page import="model.DAO.AlunoDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.Aluno"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
AlunoDAO alunoDAO = new AlunoDAO();
List<Aluno> alunos = alunoDAO.getAll();
request.setAttribute("alunos", alunos);
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">
<script src="script.js"></script>
<title>Alunos</title>
</head>
<body>
	<div class="titulo">
		<a href="./index.jsp">VOLTAR</a>
	</div>

	<c:forEach var="aluno" items="${alunos}">
		<div class="box">
			<div class="inteira esquerda">${aluno.nome}</div>
		</div>
	</c:forEach>

	<button class="box button" onclick="redirect('./cadastraraluno.jsp')">CADASTRAR
		ALUNO</button>
</body>
</html>