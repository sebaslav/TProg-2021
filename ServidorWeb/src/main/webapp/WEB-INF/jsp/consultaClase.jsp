<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="servidor.DtSocio.ValoracionesClases.Entry, servidor.DtUsuario, servidor.DtClase, servidor.DtSocio, java.util.Set, java.util.List, java.text.SimpleDateFormat, java.util.Base64, java.net.URLEncoder"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<title>Consultar Clase</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<main class="container py-5 mb-30">

		<%
		DtClase dtClase = (DtClase) request.getAttribute("datos_clase");
		DtUsuario dtUser = (DtUsuario) request.getAttribute("datos_usuario_logueado");
		Boolean puedoSortear = (Boolean) request.getAttribute("puedoSortear");
		Boolean puedoVerGanadores = (Boolean) request.getAttribute("puedoVerGanadores");
		%>

	<section class="py-5 text-center text-sm-start">

		<div class="row">
			<div class="col-sm px-5">
				<img class="img-fluid w-10"
					src="<%if (dtClase.getImagen() != null && dtClase.getImagen().length != 0) {%>
							data:image;base64, <%=Base64.getEncoder().encodeToString(dtClase.getImagen())%>
						 <%} else {%>
						 	img/noImageAvailable.png
						 <%}%>
						"
					alt="foto clase"
					style="width: 400px; height: 400px; padding: 10px;">
				<%
				if (!dtClase.getVideoURL().equals("")) { 
					String videoURL = dtClase.getVideoURL();
					videoURL = videoURL.replace("watch?v=", "embed/");
				%>
				<iframe class="my-3" width="420" height="315" title="Video de clase"
					src="<%= videoURL %>">
				</iframe> 
				<%} %>
			
			</div>
			
			<div class="col-sm px-5">
				<h2>Clase:
					<%= dtClase.getNombre() %></h2>
				<dl>
					<dt>Actividad Deportiva:</dt>
					<dd><a href="ConsultaActividad?nombre=<%=dtClase.getActividad()%>"><%=dtClase.getActividad()%></a></dd>
					
					<dt>Dictada por:</dt>
					<dd><a href="ConsultaUsuario?nickname=<%=dtClase.getProfesor()%>"><%=dtClase.getProfesor()%></a></dd>
					
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
				
				<%
				String mensajeBoton = null;
				if (dtUser instanceof DtSocio) {
					DtSocio dtSocio = (DtSocio) dtUser;
					if (dtSocio.getClases().contains(dtClase.getNombre())) {
				%>
						<button class="btn btn-lg btn-outline-success" disabled>
							Ya estas registrado a esta clase
						</button>
				<%
					} else if (request.getAttribute("clase_vigente") == null) {
				%>
						<button class="btn btn-lg btn-outline-danger" disabled>
							La clase no esta vigente
						</button>
				<%
					} else if (request.getAttribute("error_clase_llena") != null) {
				%>
						<button class="btn btn-lg btn-outline-danger" disabled>
							<%=(String) request.getAttribute("error_clase_llena")%>
						</button>
				<%	} else {
						Set<String> cups = (Set<String>) request.getAttribute("cups_disponibles");
				%>
						<form action="RegistrarAClase" method="POST">
							<input name="nombre_clase" value="<%=dtClase.getNombre()%>"
								style="display: none"> <input name="nombre_actividad"
								value="<%=dtClase.getActividad()%>" style="display: none"> <label
								for="metPag">Método de Pago: </label> <select class="form-select" name="metPag"
								id="metPag">
								<option value="">General</option>
								<%for (String c : cups) {%>
								<option value="<%=c%>"><%=c%></option>
								<%
								}
								%>
							</select> <br>
							
							<button type="submit" class="btn btn-primary">Registrarse a esta clase</button>
						</form>
				<%
					}
				} else if ((puedoSortear != null) || (puedoVerGanadores != null)){
					if (puedoSortear != null) 
						mensajeBoton = "Sortear premios";
					else 
						mensajeBoton = "Ver ganadores";
				%>
					<a class="btn btn-primary" href="SorteoPremios?nombreClase=<%=URLEncoder.encode(dtClase.getNombre(), "UTF-8")%>"><%= mensajeBoton%></a>
				<% }
				%>

				<%if (request.getAttribute("clase_vigente") == null) { %>

					<!-- MI PUNTAJE -->
					<% 
					if (dtUser != null && dtUser instanceof DtSocio && ((DtSocio) dtUser).getClases().contains(dtClase.getNombre())) { %>
						<div class="my-3">
							<% 
							boolean yaValoro = false;
							int valor = 0;
							List<Entry> entries = ((DtSocio) dtUser).getValoracionesClases().getEntry();  
							for (Entry entry : entries) {
								if (entry.getKey().equals(dtClase.getNombre())) {
									valor = entry.getValue();
									yaValoro = valor > 0;
									break;
								}
							}
							
							if (!yaValoro) {%>
								
								<h6>Valorar clase:</h6>
							<%
								for (int i=1; i<6; i++) {
							%>
									<i id="star<%=i %>" class="bi bi-star bi-star-fill text-success" onclick="submitForm(<%= i %>);"></i>
							<%	
								}
							%>
						
							<form id="formValorar" action="ValorarClase" method="POST" style="display: none;">
								<input id="inputValor" type="text" name="valoracion" value="">
								<input type="text" name="nomClase" value="<%= dtClase.getNombre() %>">
							</form>
							
							<%
							} else {%>
								<h6>Tu puntaje para esta clase:</h6>
							<%
								for (int i=1; i<6; i++) {
									float dif = valor - i;
									if (dif < -0.75) { %>
										<i class="bi bi-star text-success"></i>
									<%} else if (dif < -0.25) { %>
										<i class="bi bi-star-half text-success"></i>
									<%} else {%>
										<i class="bi bi-star-fill text-success"></i>
									<%}
								}
							}
							%>
							
						</div>
					<%} %>
	
					<!-- PUNTAJE PROMEDIO -->
					
					<div class="row">
					
						<%
						int totalValoraciones = 0;
						for (int i=1; i<=5; i++) {
							totalValoraciones += dtClase.getValoraciones().getCantValores().get(i);
						}%>
					
						<div class="col-4 text-center">
							<%float promedio = dtClase.getValoraciones().getValorPromedio();%>
							<h6>Puntaje promedio:</h6>
							<h3><%= String.format("%.1f", promedio)%></h3>
							
							<%
							for (int i=1; i<6; i++) {
								float dif = promedio - i;
								if (dif < -0.75) { %>
									<i class="bi bi-star text-success"></i>
								<%} else if (dif < -0.25) { %>
									<i class="bi bi-star-half text-success"></i>
								<%} else {%>
									<i class="bi bi-star-fill text-success"></i>
								<%}
							} 
							%>
							
							<h6><%= totalValoraciones%></h6>
						</div>
					
						<div class="col-6">
						
						<%
						for (int i=5; i>0; i--) {
							float cantParcial = 0;
							if (totalValoraciones > 0) {
								cantParcial = ((float) dtClase.getValoraciones().getCantValores().get(i) / totalValoraciones)*100;
							}
						%>
							<div class="row">
								
								<div class="progress col">
									
									<div class="mx-2 col-auto"><%= i %> star</div>
								
									<div class="progress-bar 
										<%if (i == 5) { %>
											bg-success
										<%} else if (i == 4) { %>
											bg-success
										<%} else if (i == 3) { %>
											bg-warning
										<%} else if (i == 2) { %>
											bg-danger
										<%} else { %>
											bg-danger
										<%} %>
										" role="progressbar" style="width: <%= cantParcial%>%" aria-valuenow="<%= cantParcial%>" aria-valuemin="0" aria-valuemax="100"></div>
										
									<div class="mx-2"> <%= dtClase.getValoraciones().getCantValores().get(i) %></div>
								</div>
							</div>
							
						<%} %>
								
						</div>
					
						
					
					</div>

				<%} %>

			</div>
		</div>

	</section>

		<script>
			
			function submitForm(valoracion) {
				$('#inputValor').val(valoracion);
				$('#formValorar').submit();
			}
			
			$('#star1').hover(function() {
				for (j=1; j<=1; j++) {
					$('#star' + j).removeClass('bi-star');
				}
			}, function() {
				for (j=1; j<=1; j++) {
					$('#star' + j).addClass('bi-star');
				}
			});
			
			$('#star2').hover(function() {
				for (j=1; j<=2; j++) {
					$('#star' + j).removeClass('bi-star');
				}
			}, function() {
				for (j=1; j<=2; j++) {
					$('#star' + j).addClass('bi-star');
				}
			});
			
			$('#star3').hover(function() {
				for (j=1; j<=3; j++) {
					$('#star' + j).removeClass('bi-star');
				}
			}, function() {
				for (j=1; j<=3; j++) {
					$('#star' + j).addClass('bi-star');
				}
			});
			
			$('#star4').hover(function() {
				for (j=1; j<=4; j++) {
					$('#star' + j).removeClass('bi-star');
				}
			}, function() {
				for (j=1; j<=4; j++) {
					$('#star' + j).addClass('bi-star');
				}
			});
			
			$('#star5').hover(function() {
				for (j=1; j<=5; j++) {
					$('#star' + j).removeClass('bi-star');
				}
			}, function() {
				for (j=1; j<=5; j++) {
					$('#star' + j).addClass('bi-star');
				}
			});
		
		</script>


	</main>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>