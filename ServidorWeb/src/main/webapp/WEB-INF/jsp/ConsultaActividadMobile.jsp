<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="servidor.DtUsuario, servidor.DtSocio, servidor.DtActividad, servidor.EstadoActividad, java.util.Date, java.text.SimpleDateFormat, java.util.Set, java.util.Base64"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Actividad</title>
</head>
<body class="bg-light">
	<jsp:include page="/WEB-INF/jsp/headerMobile.jsp" />
	<%
	DtSocio dtUser = (DtSocio) request.getAttribute("datos_usuario_logueado_mobile");
	DtActividad datosActividad = (DtActividad) request.getAttribute("datos_activ"); // Object casteado
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // para imprimir Date's
	%>
	<div class="container">
		<div class="row py-3 " style="place-content: center;" id="imagen">
			<img class="img-fluid border-dark bg-light"
				src="<%if (datosActividad.getImagen() != null && datosActividad.getImagen().length != 0) {%>
							data:image;base64, <%=Base64.getEncoder().encodeToString(datosActividad.getImagen())%>
						 <%} else {%>
						 	img/noImageAvailable.png
						 <%}%>
						"
				alt="foto perfil" style="width: 200px; height: 200px; padding: 5px;">
		</div>
		<div class="row border border-dark border-1 rounded m-2 bg-light"
			id="datosActividad">
			<dl class="row ">
				<dt class="col-sm-3">Actividad</dt>
				<dd class="col-sm-9"><%=datosActividad.getNombre()%></dd>
				<dt class="col-sm-3">Descripcion</dt>
				<dd class="col-sm-9"><%=datosActividad.getDescripcion()%></dd>
				<dt class="col-sm-3">Duracion</dt>
				<dd class="col-sm-9"><%=datosActividad.getDuracion()%></dd>
				<dt class="col-sm-3">Costo</dt>
				<dd class="col-sm-9"><%=datosActividad.getCosto()%></dd>
				<dt class="col-sm-3">Fecha De Registro</dt>
				<dd class="col-sm-9"><%=sdf.format(datosActividad.getFechaRegistro().toGregorianCalendar().getTime())%></dd>
			</dl>
			<%
			boolean esFav = false;
			esFav = dtUser.getFavoritas().contains(datosActividad.getNombre());
			%>
			
			<span id="heartFav"
				class="bi 
						<%if (esFav) {%>
							bi-heart-fill
						<%} else {%>
							bi-heart
							<%}%>text-danger"
				style="font-size: 30px;"> <%=datosActividad.getCantFavoritas()%></span>
		</div>
		<div class="row border border-dark border-1 rounded m-2 bg-light">
		<h2>Clases</h2>
			<ul class="list-group">
				<%
				if (datosActividad.getClases() != null) {
					for (String nomClase : datosActividad.getClases()) {
				%>
				<li class="list-group-item"><a
					href="ConsultaClaseMobile?nombre=<%=nomClase%>"><%=nomClase%></a>
				</li>
				<%
				}
				}
				%>
			</ul>
		</div>
		<div class="row border border-dark border-1 rounded m-2 bg-light"
			id="cuponerasAsocidas">
			<h2>Cuponeras</h2>
			<ul class="list-group text-start">
				<%
				if (datosActividad.getCuponeras() != null) {
					for (String nomCupo : datosActividad.getCuponeras()) {
				%>
				<li class="list-group-item"><%=nomCupo%></li>
				<%
				}
				}
				%>
			</ul>
		</div>
		<div class="row border border-dark border-1 rounded m-2 bg-light"
			id="categorias">
			<h2>Categorias</h2>
			<ul class="list-group text-start">
				<%
				if (datosActividad.getCategorias() != null) {
					for (String cate : datosActividad.getCategorias()) {
				%>
				<li class="list-group-item"><%=cate%></li>
				<%
				}
				}
				%>
			</ul>
		</div>
	</div>
</body>
</html>