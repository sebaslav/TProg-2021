<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="servidor.DtUsuario, servidor.DtSocio, servidor.DtActividad, servidor.EstadoActividad, java.util.Date, java.text.SimpleDateFormat, java.util.Set, java.util.Base64" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<title>Consultar Actividad</title>
</head>
<body>

	<!-- CABECERA -->
	<jsp:include page="/WEB-INF/jsp/header.jsp" />

	<main class="container py-5 mb-30">

	<%
	DtUsuario dtUser = (DtUsuario) request.getAttribute("datos_usuario_logueado");
	DtActividad datosActividad = (DtActividad) request.getAttribute("datos_activ"); // Object casteado
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // para imprimir Date's
	%>

	<!-- CONTENIDO PRINCIPAL -->
	<section class="py-5 text-center text-sm-start">
	
		<div class="row">
			<div class="col-sm px-5">
				<img class="img-fluid w-10"
					src="<%if (datosActividad.getImagen() != null && datosActividad.getImagen().length != 0) {%>
							data:image;base64, <%=Base64.getEncoder().encodeToString(datosActividad.getImagen())%>
						 <%} else {%>
						 	img/noImageAvailable.png
						 <%}%>
						"
					alt="foto perfil"
					style="width: 400px; height: 400px; padding: 10px;">
			</div>
			
			<div id="info-area" class="col-sm px-5">
				<h2>Actividad:
					<%= datosActividad.getNombre() %></h2>
				<dl>
					<dt>Institución:</dt>
					<dd><%= datosActividad.getInstitucion() %></dd>
					
					<%if (request.getAttribute("del_profesor_logueado") != null) {%>
						<dt>Estado:</dt>
						<dd><%= datosActividad.getEstado() %></dd>
					<%} %>
					
					<dt>Descripción:</dt>
					<dd><%= datosActividad.getDescripcion() %></dd>
					
					<dt>Duración:</dt>
					<dd><%= String.valueOf(datosActividad.getDuracion()) %> minutos</dd>
					
					<dt>Costo:</dt>
					<dd>$<%= String.valueOf(datosActividad.getCosto()) %></dd>
					
					<dt>Fecha de registro:</dt>
					<dd><%=  sdf.format(datosActividad.getFechaRegistro().toGregorianCalendar().getTime()) %></dd>
				</dl>
				
				<%
				boolean soc = dtUser != null && dtUser instanceof DtSocio;		
				boolean esFav = false;
				if (soc) {
					esFav = ((DtSocio) dtUser).getFavoritas().contains(datosActividad.getNombre());
				%>
					<form id="formFav" action="MarcarFavorita" method="POST" style="display: none;">
						<input type="text" name="nomAct" value="<%= datosActividad.getNombre()%>">
					</form>
					
				<%} %>
					<span id="heartFav" class="bi 
						<%if (esFav) { %>
							bi-heart-fill
						<%} else { %>
							bi-heart
						<%} %>
					 text-danger" 
					 	<%if (soc) { %>
					 		onclick="$('#formFav').submit();"
					 	<%} %>	
					 style="font-size: 30px;"> <%= datosActividad.getCantFavoritas() %></span>
					 
				
				<% if (request.getAttribute("puede_crear_clase") != null && datosActividad.getEstado() == EstadoActividad.ACEPTADA) {%>
					<div class="my-3"><a href= "AltaClase?actividad=<%= datosActividad.getNombre()%>" class="btn btn-primary">Dar de alta clase</a></div>
				<%} %>
				<%if (request.getAttribute("del_profesor_logueado") != null && request.getAttribute("finalizable") != null) {%>
					<a href= "FinalizarActividad?actividad=<%= datosActividad.getNombre()%>" class="btn btn-primary">FinalizarActividad</a>
				<%} %>
				
			</div>
		</div>
		
		<div class="row-1"></div>
		
		<div class="row">
			<div id="info-area" class="container col-sm px-5 my-3 py-2 text-center">
				<h3>Cuponeras Asociadas</h3>
				<div id="listaCuponerasActividad"
					style="float: bottom; width: 95%; height: 63%;">
					<ul class="list-group text-start">
						<%
						if (datosActividad.getCuponeras() != null) {
							for (String nomCupo : datosActividad.getCuponeras()) {%>
							<li class="list-group-item">
								<a href="ConsultaCuponera?nombre_cuponera=<%= nomCupo%>"><%= nomCupo%></a>
							</li>
						<%
							}
						}
						%>
					</ul>
				</div>
			</div>
			
			<div class="col-auto"></div>
			
			<div id="info-area" class="container col-sm px-5 my-3 py-2 text-center">
				<h3>Clases de la actividad</h3>
				<div id="listaClasesActividad"
					style="float: bottom; width: 95%; height: 63%;">
					<ul class="list-group text-start">
						<%
						if (datosActividad.getClases() != null) {
							for (String nomClase : datosActividad.getClases()) {%>
							<li class="list-group-item">
								<a href="ConsultaClase?nombre=<%= nomClase%>"><%= nomClase%></a>
							</li>
						<%
							}
						}
						%>
					</ul>
				</div>
			</div>
		</div>

	</section>

		<script>
			
		<%if (soc) { %>
			$('#heartFav').hover(function() {
				$(this).toggleClass('bi-heart');
				$(this).toggleClass('bi-heart-fill');
			}, function() {
				$(this).toggleClass('bi-heart');
				$(this).toggleClass('bi-heart-fill');
			});

		<%} %>
			
		</script>
			
	</main>

	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>