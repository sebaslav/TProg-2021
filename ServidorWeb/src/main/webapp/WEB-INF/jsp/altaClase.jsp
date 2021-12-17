<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="servidor.DtClase, java.util.Set, java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/head.jsp"/>
	<title>Alta de Clase</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"/>
	
	<main class="container py-5 mb-30">

	<section class="container pt-5 pb-5 text-center text-sm-start">
		<!-- Formulario -->
		<div>
			
			<%DtClase dc = (DtClase) request.getAttribute("dtClaseIngresada"); %>
			
			<form action="AltaClase" method="POST" enctype="multipart/form-data">
			<fieldset>
				<legend>Ingresar datos de la clase que desea dar de alta</legend>
				
				<% if (request.getAttribute("error_clase_repetida") != null) {%>
					<div>
						<small class="text-danger"><%= (String) request.getAttribute("error_clase_repetida") %></small>
					</div>
				<%} %>
				
				
				<div class="row my-3">
					<div class="col-3">
						<label class="form-label" for="fname">Nombre:</label>
						<input class="form-control" type="text" id="fname" name="nombre" 
						<%if (dc != null) {%>
							value="<%= dc.getNombre() %>"
						<%} %>
						required>
					</div>
					<div class="col-2">
						<label class="form-label" for="dictado">Fecha de dictado:</label>
						<input class="form-control" type="date" id="dictado" name="fecha_dictado" 
						<%if (dc != null) {%>
							value="<%=new SimpleDateFormat("yyyy-MM-dd").format(dc.getFechaHora().toGregorianCalendar().getTime())%>"
						<%} %>
						required>
					</div>
					<div class="col-auto">
						<label class="form-label" for="iHoraInicio">Hora inicio:</label>
						<input class="form-control" type="time" id="iHoraInicio" name="hora_inicio" 
						<%if (dc != null) {%>
							value="<%=new SimpleDateFormat("HH:mm").format(dc.getFechaHora().toGregorianCalendar().getTime())%>"
						<%} %>
						required>
					</div>
				</div>
				
				<div class="row my-3">
					<div class="col-3">
						<label class="form-label" for="iCanMin">Cantidad mínima de participantes:</label>
						<input class="form-control" type="number" id="iCanMin" name="cant_min" min="1" 
						<%if (dc != null) {%>
							value="<%=dc.getMinSocios()%>"
						<%} %>
						required>
					</div>
					<div class="col-3">
						<label class="form-label" for="iCanMax">Cantidad máxima de participantes:</label>
						<input class="form-control" type="number" id="iCanMax" name="cant_max" min="1" 
						<%if (dc != null) {%>
							value="<%=dc.getMaxSocios()%>"
						<%} %>
						required>
					</div>
				</div>
				
				<div class="row my-3">
					<div class="col-3">
						<label class="form-label" for="icantpremios">Cantidad de premios:</label>
						<input class="form-control" type="number" id="icantpremios" name="cantPremios"
						<%if (dc != null) {%>
							value="<%=dc.getCantPremios()%>"
						<%} %>
						required>
					</div>
					<div class="col-3">
						<label for="idescripcion">Descripción del premio:</label>
						<textarea class="form-control" placeholder="descripción del premio" id="idescripcion" name="descPremio" style="resize:none"><%if (dc != null) {%><%=dc.getDescPremio()%><%} %></textarea>
					</div>
				</div>
				
				<div class="row my-3">
					<div class="col-6">
						<label class="form-label" for="ivideo">Video <small>(opcional)</small>:</label>
						<input class="form-control" type="url" id="ivideo" name="videoURL" 
						<%if (dc != null) {%>
							value="<%=dc.getVideoURL()%>"
						<%} %>
						>
					</div>
				</div>
				
				<div class="row my-3">
					<div class="col-6">
						<label class="form-label" for="url">URL:</label>
						<input class="form-control" type="url" id="url" name="url" 
						<%if (dc != null) {%>
							value="<%=dc.getUrl()%>"
						<%} %>
						required>
					</div>
				</div>

				<div class="col-4 my-3">
					<label class="form-label" for="fotoUpload">Foto <small>(opcional)</small>:</label>
					<input
						class="form-control" type="file" class="form-control" id="fotoUpload" name="fotoUpload"
						accept=".png, .jpg">
				</div>
				
				<div class="col-3 my-3">
					<label class="form-label" for="insti_profe">Actividad:</label>
					<select class="form-select" name="actividad" id="actividades" required>
						<option value="">-- Elegir Actividad --</option>
						<% Set<String> acts = (Set<String>) request.getAttribute("actividades");
						for (String act : acts) {%>
	  					<option value="<%= act %>"
	  						<% String act_selec = (String) request.getAttribute("actividad");
	  						if (act_selec != null && act_selec.equals(act)){ %>
	  							selected
	  						<% } %>
	  					><%= act %></option>
	  					<% } %>
					</select>
				</div>
				
				<div class="my-5">
					<input type="submit" class="btn btn-primary" value="Confirmar"
						onclick="return checkAltaClaseForm()">
					<input
						type="button" class="btn btn-outline-primary" onclick="document.location='Index'"
						value="Cancelar">
				</div>
			</fieldset>
			</form>
			
		</div>
	</section>
	
	</main>

	<script>
		function checkAltaClaseForm() {
			var cantMin = parseInt(document.getElementById("iCanMin").value);
			var cantMax = parseInt(document.getElementById("iCanMax").value);
			var cantPre = parseInt(document.getElementById("icantpremios").value);

			if(cantMin > cantMax){
				window.alert("La cantidad minima de socios no puede ser mayor que la cantidad maxima");
				return false;
			}
			
			if(cantPre > cantMax){
				window.alert("La cantidad de premios no puede ser mayor que la cantidad maxima de socios");
				return false;
			}
		}
	</script>

	<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>