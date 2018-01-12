<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset="UTF-8"">
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />

<script src="<c:url value="/resources/js/bootstrap.min.js"/>" /></script>

<style type="text/css">
body {
	padding: 60px 0px;
}
</style>

<title>Insert title here</title>

</head>
<body>



	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">HOME</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${s:mvcUrl('TC#listar').build()}">Lista de
							Trabalhos</a></li>
					<li><a href="${s:mvcUrl('TC#form').build()}">Cadastro de
							Trabalhos</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
	</nav>

	<div class="container">
		<h1>Lista de Trabalhos</h1>
		<p>${sucesso}</p>
		<p>${message}</p>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Título</th>
				<th>Descrição</th>
				<th>Páginas</th>
				<th>Tipo</th>
			</tr>
			<tbody>
				<c:forEach items="${trabalhos}" var="trabalho">
					<tr>
						<td><a
							href="${s:mvcUrl('TC#detalhe').arg(0, trabalho.id).build()}">${trabalho.titulo}</a></td>
						<td>${trabalho.descricao}</td>
						<td>${trabalho.paginas}</td>
						<td>${trabalho.tipoModalidadeTrabalho}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>