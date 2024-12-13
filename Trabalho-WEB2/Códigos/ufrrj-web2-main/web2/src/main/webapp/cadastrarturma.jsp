<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">
<script src="script.js"></script>
<title>Cadastrar Turma</title>
</head>
<body>
	<div class="titulo">
		<a href="./turmas.jsp">VOLTAR</a>
	</div>
	<form action="CadastrarTurma" method="POST">
		<input class="box button" type="text" name="disciplina"
			placeholder="Digite o nome da disciplina" required> <input
			class="box button" type="text" name="ano"
			placeholder="Digite o ano da turma" required> <input
			class="box button" type="text" name="periodo"
			placeholder="Digite o número do semestre" required> <input
			type="submit" class="box button" value="CADASTRAR">
	</form>
</body>
</html>