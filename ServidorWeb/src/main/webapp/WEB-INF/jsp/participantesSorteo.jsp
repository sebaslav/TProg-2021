<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="servidor.DtUsuario, java.util.Set , java.util.Iterator, java.util.Map, java.util.Base64 "%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<title>Lista de usuarios del sorteo</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	
	<main class="container">
		<%
		String nomClase = (String) request.getAttribute("nombreClase");
		boolean sorteoRealizado = request.getAttribute("ganadores") != null;
		Set<DtUsuario> dtUsuarios;
		if (sorteoRealizado) {
			dtUsuarios = (Set<DtUsuario>) request.getAttribute("ganadores");
		} else {
			dtUsuarios = (Set<DtUsuario>) request.getAttribute("participantes");
		}
		%>
		
		<div class="row">
			<h1><%if (sorteoRealizado) { %>Ganadores del sorteo para la clase <%= nomClase %><%} else { %>Participantes del sorteo para la clase <%= nomClase %><%} %></h1>
		</div>
	
		<div class="row">
			<ul id="busqueda_lista_acts" class="list-group con-marco">
				<% 
					for (DtUsuario u : dtUsuarios) { 
				%>
				
				<li class="list-group-item">
					<div class="row">
						
						<div class="col-auto">
							<a href="ConsultaUsuario?nickname=<%=u.getNickname()%>">
							<img class="fotarda" src="<%if (u.getImagen() != null && u.getImagen().length != 0) {%>
							data:image;base64, <%=Base64.getEncoder().encodeToString(u.getImagen())%>
						 <%} else {%>
						 	img/usuario.png
						 <%}%>
						"
					alt="foto perfil" align="left"
								height="100px" width="100px"></a>
							</div>
							<div class="col" style="">
							<h3><%=u.getNickname()%></h3>
							<h6><%=u.getNombre()%> <%=u.getApellido()%></h6>
							<a href="ConsultaUsuario?nickname=<%=u.getNickname()%>">Ver mas</a>
							</div>
					</div>
				</li>
				<%
				}
				%>
			</ul>
		</div>
		
		<div class="my-5">
		
			<%if (!sorteoRealizado) { %>
				<form action="SorteoPremios" method="POST">
					<input type="text" value="<%=nomClase%>" name="nombreClase" style="display: none;">
					<input type="submit" class="btn btn-primary" value="Confirmar sorteo">
					<a type="button" class="btn btn-outline-primary" href="ConsultaClase?nombre=<%=nomClase%>">Ir a la clase</a>
				</form>
			<%} else {%>	
				<a type="button" class="btn btn-outline-primary" href="ConsultaClase?nombre=<%=nomClase%>">Ir a la clase</a>
			<%} %>
		
		</div>
		
	</main>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>