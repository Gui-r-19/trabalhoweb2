<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">
<script src="script.js"></script>
<title>Cadastrar Aluno</title>
</head>
<body>
	<div class="titulo">
		<a href="./alunos.jsp">VOLTAR</a>
	</div>
	<form action="CadastrarAluno" method="POST">
		<input class="box button" type="text" name="nome"
			placeholder="Digite o nome do aluno" required> <input
			type="submit" class="box button" value="CADASTRAR">
	</form>
</body>
</html>