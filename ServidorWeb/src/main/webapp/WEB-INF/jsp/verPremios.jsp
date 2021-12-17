<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="servidor.DtPremio, java.util.List, java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<title>Mis premios</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	
	<main class="container">
	
		<div class="row">
			<h1>Mis premios</h1>
		</div>
	
		<div class="row">
			<ul id="busqueda_lista_acts"  class="list-group con-marco">
				<%
				List<DtPremio> premios = (List<DtPremio>) request.getAttribute("premios");
				 if (premios.isEmpty()) {%>
					<div class="alert alert-dark" role="alert">
					  No hay premios
					</div>
				<%} 
				for (DtPremio premio : premios) { 
				%>
				
				<li class="list-group-item">
					<div class="row">
						<div class="col-auto">
							<p><%= premio.getDescPremio() %></p>
							<p>Clase: <a href="ConsultaClase?nombre=<%= premio.getNomClase()%>"><%= premio.getNomClase()%></a></p>
							<p>Actividad: <a href="ConsultaActividad?nombre=<%= premio.getNomActividad()%>"><%= premio.getNomActividad()%></a></p>
							<p>Fecha del sorteo: <%=new SimpleDateFormat("dd/MM/yyyy").format(premio.getFechaSorteo().toGregorianCalendar().getTime())%></p>
							
							<form action="PremioPDF" method="POST">
								<input type="text" name="nomClase" value="<%= premio.getNomClase() %>" style="display: none;">
								<input type="submit" class="btn btn-primary" value="Comprobante en PDF">
							</form>
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