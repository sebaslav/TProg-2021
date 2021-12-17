<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="servidor.DtUsuario, java.util.Set , java.util.Iterator, java.util.Map, java.util.Base64 "%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<title>Lista de usuarios</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	
	<main class="container">
	
		<div class="row">
			<h1>Lista de usuarios</h1>
		</div>
	
		<div class="row">
			<ul id="busqueda_lista_acts"  class="list-group con-marco">
				<% Set<DtUsuario> DtUsuarios = (Set<DtUsuario>) request.getAttribute("DtUsuarios");
					for (DtUsuario u : DtUsuarios) { 
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
	</main>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>