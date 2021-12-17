<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="servidor.DtActividad, java.util.Set, java.util.Base64, servidor.DtUsuario, servidor.DtSocio"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actividades</title>
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<%
	DtUsuario dtUser = (DtUsuario) request.getAttribute("datos_usuario_logueado_mobile");
	Set<DtActividad> datosActividades = (Set<DtActividad>) request.getAttribute("datosActividades");
	%>
	<jsp:include page="/WEB-INF/jsp/headerMobile.jsp" />
	<main class="container-sm py-5">

		<div class="row text-center">
			<h1><%=request.getAttribute("title")%></h1>
		</div>
		
		<%if (datosActividades.isEmpty()) {%>
				<div class="alert alert-dark" role="alert">No hay actividades
					para la institucion o categoria</div>
			<%}
		for (DtActividad actividad : datosActividades) {%>
		<div class="row border border-dark border-2 rounded m-1 bg-secondary">
		<div class="col-1" style="align-self: center; text-align:center;"> <%		
							boolean esFav = false;
								esFav = ((DtSocio) dtUser).getFavoritas().contains(actividad.getNombre());
							%>
								<span id="heartFav" class="bi 
									<%if (esFav) { %>
										bi-heart-fill
									<%} else { %>
										bi-heart
									<%} %>
								 text-danger text-center" style="font-size: 20px;"> <%= actividad.getCantFavoritas() %></span></div>
			<div class="col-4" style="align-self: center; text-align:center;">
				<a href="ConsultaActividadMobile?nombre=<%=actividad.getNombre()%>">
					<img class="py-1 fotarda" src="<%if (actividad.getImagen() != null && actividad.getImagen().length != 0) {%>
									data:image;base64, <%=Base64.getEncoder().encodeToString(actividad.getImagen())%>
					<%} else {%>
					 img/noImageAvailable.png
					<%}%>"
					 height="80" width="80" align="center">
				</a>
			</div>
			<div class="col border-start border-dark border-2">
				<div class="row border-bottom border-dark border-2 bg-success">
					<h5 class="fw-bold text-center text-align-center"><%=actividad.getNombre() %></h5>
					</div>
					<div class="row bg-light bg-opacity-50">
					<p class="fst-italic text-break " style=""><%=actividad.getDescripcion()%></p>
				</div>
			</div>
		</div>
		<%}%>
	</main>


</body>
</html>