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
<title>Turma</title>
</head>
<body>
	<div class="titulo">
		<a href="./turmas.jsp">VOLTAR</a>
	</div>

	<div class="box">
		<div class="metade">${turma.disciplina}</div>
		<div class="metade direita">${turma.ano}.${turma.periodo}</div>
	</div>

	<div class="titulo">AULAS</div>

	<c:forEach var="aula" items="${aulas}">
		<div class="box">
			<div class="metade">${aula.data}</div>
			<div class="metade direita">
				<a href="./VerAula?idAula=${aula.idAula}">REGISTRAR PRESENÇA</a>
			</div>
		</div>
	</c:forEach>

	<button class="box button"
		onclick="redirect('./cadastraraula.jsp?idTurma=${turma.idTurma}')">CADASTRAR
		AULA</button>

	<div class="titulo">ALUNOS</div>

	<c:forEach var="aluno" items="${alunos}">
		<div class="box">
			<div class="inteira esquerda">${aluno.nome}</div>
		</div>
	</c:forEach>


	<button class="box button" onclick="redirect('./VerVincularAlunos?idTurma=${turma.idTurma}')">VINCULAR
		ALUNO(S)</button>
</body>
</html>