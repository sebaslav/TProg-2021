<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<title>Inicio de Sesión</title>
</head>
<body>
	<!-- HEADER Y COSTADER -->
	<jsp:include page="/WEB-INF/jsp/header.jsp" />

	<main>
		<section class="container pt-5 pb-5 text-center text-sm-start">
			<div class="row">
				<div class="col">
					<br> <br>
					<h1>Iniciar Sesión</h1>
					<form action="IniciarSesion" method="POST">
						<fieldset>
							<br>
							<legend>Ingrese sus datos:</legend>
							<label for="fname">Ingrese nickname o correo electrónico:</label> <br>
							<input type="text" id="fname" name="login" 
							<%if (request.getAttribute("login") != null) {%>
								value="<%= (String) request.getAttribute("login")%>"
							<%} %>
							required> <br>
							<br> <label for="pwd">Contraseña:</label> <br> <input
								type="password" id="pwd" name="password" required> <br>
							<%
							if (request.getAttribute("error_login") != null) {
							%>
							<div>
								<small class="text-danger">Usuario o contraseña inválidos. Por favor
									inténtelo nuevamente. </small>
							</div>
							<%
							}
							%>
							<br> <input class="btn btn-primary" value="Iniciar sesión"
								type="submit">
							<button class="btn btn-outline-primary"
								onclick="document.location='AltaUsuario'">
								Crear cuenta nueva</button>
						</fieldset>
					</form>
				</div>
				<div class="col">
					<img alt="" src="img/login.jpg" style="width:79vh;height:67vh;">
				</div>
			</div>
		</section>
		
	</main>

	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>