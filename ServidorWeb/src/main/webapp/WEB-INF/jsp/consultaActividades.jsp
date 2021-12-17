<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="servidor.DtUsuario, servidor.DtSocio, servidor.DtActividad, java.util.Set, java.util.Base64"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<title>Consulta de actividades</title>
</head>
<body>

	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	
	<main class="container py-5">
		
		<div class="row">
			<h1><%=request.getAttribute("title")%></h1>
		</div>
		<div id="listaActividades" class="row" title="usrList">
			<ul class="list-group con-marco">
				<%
				DtUsuario dtUser = (DtUsuario) request.getAttribute("datos_usuario_logueado");
				Set<DtActividad> datosActividades = (Set<DtActividad>) request.getAttribute("datosActividades");
				 if (datosActividades.isEmpty()) {%>
					<div class="alert alert-dark" role="alert">
					  No hay actividades para la institucion o categoria
					</div>
				<%} 
				for (DtActividad actividad : datosActividades) {
				%>
				<li class="list-group-item">
					<div class="row">
						<div class="col-auto">
							<a href="ConsultaActividad?nombre=<%=actividad.getNombre()%>">
								<img class="fotarda" 
								src="<%if (actividad.getImagen() != null && actividad.getImagen().length != 0) {%>
										data:image;base64, <%=Base64.getEncoder().encodeToString(actividad.getImagen())%>
									 <%} else {%>
									 	img/noImageAvailable.png
									 <%}%>
									" align="left" height="100" width="100">
							</a>
						</div>
						<div class="col">
							<h3><%=actividad.getNombre()%></h3>
							
							<%
							boolean soc = dtUser != null && dtUser instanceof DtSocio;		
							boolean esFav = false;
							if (soc) {
								esFav = ((DtSocio) dtUser).getFavoritas().contains(actividad.getNombre());
							} %>
								<span id="heartFav" class="bi 
									<%if (esFav) { %>
										bi-heart-fill
									<%} else { %>
										bi-heart
									<%} %>
								 text-danger" style="font-size: 20px;"> <%= actividad.getCantFavoritas() %></span>
							
						</div>
					</div>
				</li>
				<%
				}
				%>
			</ul>
		</div>
	</main>

	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>