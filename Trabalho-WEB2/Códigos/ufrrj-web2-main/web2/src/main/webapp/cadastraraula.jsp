<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Integer idTurma = Integer.parseInt(request.getParameter("idTurma"));
request.setAttribute("idTurma", idTurma);
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">
<script src="script.js"></script>
<title>Cadastrar Aula</title>
</head>
<body>
	<div class="titulo">
		<a href="./turmas.jsp">VOLTAR</a>
	</div>
	<form action="CadastrarAula" method="POST">
		<input class="box button" type="text" name="data"
			placeholder="Digite a data da aula" required> <input
			type="hidden" name="idTurma" value="${idTurma}"> <input
			type="submit" class="box button" value="CADASTRAR">
	</form>
</body>
</html>