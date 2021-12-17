<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Set"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/head.jsp"/>
	<title>Alta de Actividad</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<main class="container py-5">
	<section class="container pt-5 pb-5 text-center text-sm-start">
		<!-- Formulario -->
		<div>
			
			<form action="AltaActividad" method="POST" enctype="multipart/form-data">
				<div class="form-group">
					<fieldset class="formulario">
						<legend>Nueva actividad deportiva</legend>
						
						<%
						if (request.getAttribute("error_actividad_repetida") != null) {%>
							<div>
								<small class="text-danger"><%= (String) request.getAttribute("error_actividad_repetida") %></small>
							</div>
						<%} %>
						
						<div class="row my-2">
							<div class="col-4">
								<label for="nombre">Nombre:</label>
								<input type="text" class="form-control" placeholder="nombre de la actividad"  id="nombre" name="nombre" 
									<%if (request.getAttribute("nombreAltaActividad") != null) {%>
										value="<%=  (String) request.getAttribute("nombreAltaActividad")%>"
									<%} %>
									required>
								<small class="text-muted">Este nombre debe ser único e identificará a la actividad.</small>
							</div>
							<div class="col-4">
								<label for="institucion">Institución:</label>
								<input type="text" class="form-control" id="institucion" name="institucion" value="<%= (String)request.getAttribute("institucion_user") %>" readonly>
							</div>
						</div>
						
						<div class="row mt-3">
							<div class="col-8">
							<label for="descripcion">Descripción:</label>
							<textarea class="form-control" placeholder="descripción de la actividad" id="descripcion" name="descripcion" style="resize:none" required><%if (request.getAttribute("descripcionAltaActividad") != null) {%><%=(String) request.getAttribute("descripcionAltaActividad")%><%} %></textarea><br>
							</div>
						</div>
						
						<div class="row mt-3">
							<div class="col-auto">
								<label for="duracion">Duracion:</label>
								<input type="number" class="form-control" id="duracion" name="duracion" min="1" 
									<%if (request.getAttribute("duracionAltaActividad") != null) {%>
										value="<%= (int) request.getAttribute("duracionAltaActividad")%>"
									<%} %>
								required>
								<small class="text-muted">
									(duracion en minutos)
								</small>
							</div>
							<div class="col-auto">
								<label for="costo">Costo:</label>
								<input type="number" class="form-control" id="costo" name="costo" min="0" step=".02" pattern="^\d*(\.\d{0,2})?$" 
									<%if (request.getAttribute("costoAltaActividad") != null) {%>
										value="<%= (float) request.getAttribute("costoAltaActividad")%>"
									<%} %>
								required>
							</div>
						</div>
						
						<div class="row mt-4">
							<div class="col-auto">
							<label for="fotoUpload">Foto <small>(opcional)</small>:</label><br>
							<input type="file" class="form-control" id="fotoUpload" name="fotoUpload" accept=".png, .jpg"><br>
							</div>
						</div>
						
						
						<!-- Lista de categorias -->
						<div class="row my-5">
							<%
							Set<String> cats = (Set<String>) request.getAttribute("categorias");
							if (cats == null || cats.isEmpty()) {%>
							<label>No hay categorias para elegir.</label>
							<%
							} else { %>
							 
							<label for="categoria"><h5>Categorias</h5></label>
							<ul class="form-check" id="lista_cats_alta_actividad" style="list-style-type:none;">
							<%
							for (String cat : cats) {%>
								<li>
								<input class="form-check-input" type="checkbox" id="cat_<%= cat %>" name="cat_<%= cat %>">
								<label class="form-check-label" for="cat_<%= cat %>"><%= cat %></label>
								</li>
							<%
							} %>
							</ul>
							<%
							} %>
						</div>
						<input type="submit" class="btn btn-primary" value="Registrar" onclick="return checkAltaActividadForm()">
						<input type="button" class="btn btn-outline-primary" onclick="document.location='Index'" value="Cancelar">
				
					</fieldset>
				</div>
				
			</form>
			
		</div>
		<script>
		function checkAltaActividadForm() {
			var cats = document.getElementById("lista_cats_alta_actividad").getElementsByTagName("input");
			var cat_seleccionada = false;
			
			for (var i = 0; i < cats.length; ++i) {
				if (cats[i].checked) {
					cat_seleccionada = true;
				}
			}
			
			if (!cat_seleccionada) {
				window.alert("Se debe seleccionar al menos una categoria");
				return false;
			}
			
		}
	</script>
		
	</section>
</main>
	<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>