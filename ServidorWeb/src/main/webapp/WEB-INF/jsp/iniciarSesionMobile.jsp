<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Inicio de Sesión</title>
</head>
<body>
	<main>
		<section class="container py-5">
			<div class="row">
				<h2 class="fw-bold text-center">Iniciar Sesión</h2>
				<form action="IniciarSesionMobile" method="POST">
					<%
					if (request.getAttribute("error_login") != null) {
					%>
					<div class="py-2">
						<small class="text-danger">Usuario o contraseña inválidos.
							Por favor inténtelo nuevamente. </small>
					</div>
					<%
					}
					%>
					<%
					if (request.getAttribute("error_prof") != null) {
					%>
					<div class="py-2">
						<small class="text-danger">Solo socios pueden loguearse en nuestra version Movil</small>
					</div>
					<%
					}
					%>
					<div class="form-group py-2">
						<label for="emailUsuarioMobile">Email o Nombre de Usuario</label>
						<input type="text" class="form-control" id="emailUsuarioMobile" name="login"
							placeholder="Ingrese email o nombre de usuario" 
							<%if (request.getAttribute("login") != null) {%>
								value="<%= (String) request.getAttribute("login")%>"
							<%} %> 
							required>
					</div>
					
					<div class="form-group py-2">
						<label for="passMobile">Contraseña</label> <input type="password"
							class="form-control" id="passMobile" name="password"
							<%if (request.getAttribute("pass") != null) {%>
								value="<%= (String) request.getAttribute("pass")%>"
							<%} %> 
							placeholder="Ingresar contraseña" required>
					</div>
					
					<div class="form-group py-2">
						<input class="form-check-input" id="rememberMe" type="checkbox" name="rememberMe"
						<%if (request.getAttribute("pass") != null) {%>
								checked
						<%} %> >
						<label class="form-check-label" for="rememberMe" > Recordarme</label>
					</div>

					<div class="d-grid gap-2 py-3 d-md-block">
						<button type="submit" class="btn btn-success">Iniciar
							Sesion</button>
					</div>
				</form>
			</div>
		</section>
	</main>

	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>