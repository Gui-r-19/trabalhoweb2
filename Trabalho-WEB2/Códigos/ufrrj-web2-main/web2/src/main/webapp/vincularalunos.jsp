<%@page import="model.DAO.TurmaDAO"%>
<%@page import="model.Turma"%>
<%@page import="model.DAO.AlunoDAO"%>
<%@page import="model.Aluno"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">
<script src="script.js"></script>
<title>Vincular Alunos</title>
</head>
<body>
	<div class="titulo">
		<a href="./turmas.jsp">VOLTAR</a>
	</div>
	<div class="box">
		<div class="metade">${turma.disciplina}</div>
		<div class="metade direita">${turma.ano}.${turma.periodo}</div>
	</div>
	<div class="titulo">ALUNOS</div>
	<form action="VincularAlunos" method="POST">
		<c:forEach var="aluno" items="${alunos}">
			<label for="id${aluno.idAluno}">
				<div class="box">
					<div class="metade">${aluno.nome}</div>
					<div class="metade direita">
						<input type="checkbox" id="id${aluno.idAluno}" name="idAluno"
							value="${aluno.idAluno}">
					</div>
				</div>
			</label>
		</c:forEach>
		<input type="hidden" name="idTurma" value="${turma.idTurma}">
		<input type="submit" class="box button" value="VINCULAR ALUNO(S)">
	</form>
</body>
</html>