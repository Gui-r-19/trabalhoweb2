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
<title>Aula</title>
</head>
<body>
	<div class="titulo">
		<a href="./turmas.jsp">VOLTAR</a>
	</div>
	<div class="box">
		<div class="metade">${aula.data} - ${aula.turma.disciplina}</div>
		<div class="metade direita">${aula.turma.ano}.${aula.turma.periodo}</div>
	</div>
	<div class="titulo">ALUNOS</div>
	<form action="RegistrarPresenca" method="POST">
		<c:forEach var="aluno" items="${alunos}">
			<div class="box">
				<div class="metade">${aluno.nome}</div>
				<div class="metade direita">
					<span>AUSENTE</span> <input type="radio" name="${aluno.idAluno}" value="0" ${alunosPresentes.contains(aluno) ? '' : 'checked'}>
					<span class="esp">PRESENTE</span> <input type="radio" name="${aluno.idAluno}" value="1" ${alunosPresentes.contains(aluno) ? 'checked' : ''}>
				</div>
			</div>
		</c:forEach>
		<input type="hidden" name="idAula" value="${aula.idAula}">
		<input type="submit" class="box button" value="REGISTRAR PRESENÇA">
	</form>
</body>
</html>