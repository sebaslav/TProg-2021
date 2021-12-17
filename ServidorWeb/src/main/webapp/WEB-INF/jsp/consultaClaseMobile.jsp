<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import=" java.util.Date,servidor.DtSocio.ValoracionesClases.Entry, servidor.DtUsuario, servidor.DtClase, servidor.DtSocio, java.util.Set, java.util.List, java.text.SimpleDateFormat, java.util.Base64, java.net.URLEncoder"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clase</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="/WEB-INF/jsp/head.jsp" />
</head>
<body class="bg-light">
	<jsp:include page="/WEB-INF/jsp/headerMobile.jsp" />
	<%DtClase dtClase = (DtClase) request.getAttribute("datos_clase");%>
	<div class="container">
		<div class="row py-3 " style="place-content: center;" id="imagen">
			<img class="img-fluid border-dark bg-light"
				src="<%if (dtClase.getImagen() != null && dtClase.getImagen().length != 0) {%>
							data:image;base64, <%=Base64.getEncoder().encodeToString(dtClase.getImagen())%>
						 <%} else {%>
						 	img/noImageAvailable.png
						 <%}%>
						"
				alt="foto perfil" style="width: 200px; height: 200px; padding: 5px;">
		</div>
		<div class="row border border-dark border-1 rounded m-2 bg-light"
			id="dtClase">
			<h2>Clase:
					<%= dtClase.getNombre() %></h2>
				<dl>
					<dt>Actividad Deportiva:</dt>
					<dd><a href="ConsultaActividadMobile?nombre=<%=dtClase.getActividad()%>"><%=dtClase.getActividad()%></a></dd>
					
					<dt>Dictada por:</dt>
					<dd><%=dtClase.getProfesor()%></dd>
					
					<dt>Fecha dictado:</dt>
					<dd><%=new SimpleDateFormat("dd/MM/yyyy").format(dtClase.getFechaHora().toGregorianCalendar().getTime())%></dd>
					
					<dt>Hora:</dt>
					<dd><%=new SimpleDateFormat("HH:mm").format(dtClase.getFechaHora().toGregorianCalendar().getTime())%></dd>
					
					<dt>Mínima cantidad de participantes:</dt>
					<dd><%=dtClase.getMinSocios()%></dd>
					
					<dt>Máxima cantidad de participantes:</dt>
					<dd><%=dtClase.getMaxSocios()%></dd>
					
					<dt>URL:</dt>
					<dd><%=dtClase.getUrl()%></dd>
					
					<dt>Fecha de registro:</dt>
					<dd><%=new SimpleDateFormat("dd/MM/yyyy").format(dtClase.getFechaDeRegistro().toGregorianCalendar().getTime())%></dd>
					
					<dt>Cantidad de premios:</dt>
					<dd><%=dtClase.getCantPremios()%></dd>
					
					<dt>Descripcion del premio:</dt>
					<dd><%=dtClase.getDescPremio()%></dd>
				</dl>
		</div>
	</div>
</body>
</html>