<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="servidor.DtUsuario, servidor.DtSocio, servidor.DtProfesor, java.util.Set, java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<title>Alta de Usuario</title>
</head>
<body>
	<!-- HEADER Y COSTADER -->
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<main class="container">
		<div class="row">
				<!-- Formulario -->
				<div class="col">
					
					<%DtUsuario du = (DtUsuario) request.getAttribute("dtUserIngresado"); %>
					
					<form action="AltaUsuario" method="POST"
						enctype="multipart/form-data">

						<!-- Campos usuario -->
						<fieldset class="formulario">
							<h1>Registrar Usuario</h1>
							<br>
							<legend>Datos basicos</legend>
							
							<%
							if (request.getAttribute("error_user_repetido") != null) {
							%>
								<div class="row">
									<small class="text-danger"><%= (String) request.getAttribute("error_user_repetido") %></small>
								</div>
							<% } %>
							
							<div class="row my-3">
								<div class="col-4">
									<label for="nombre">Nombre:</label> <input type="text"
										class="form-control" id="nombre" name="nombre" 
										<%if (du != null) { %>
											value="<%= du.getNombre() %>"
										<%} %>
										required>
								</div>
								<div class="col-4">
									<label for="apellido">Apellido:</label> <input type="text"
										class="form-control" id="apellido" name="apellido" 
										<%if (du != null) { %>
											value="<%= du.getApellido() %>"
										<%} %>
										required>
								</div>

								<div class="col-4">
									<label for="fecha_nac">Fecha de nacimiento:</label> <input
										type="date" class="form-control" id="fecha_nac" name="fecha_nac" 
										<%if (du != null) { %>
											value="<%=new SimpleDateFormat("yyyy-MM-dd").format(du.getFechaDeNacimiento().toGregorianCalendar().getTime())%>"
										<%} %>
										required>
								</div>
							</div>

							<div class="row my-3">
								<div class="col-6">
									<label for="nickname">Nickname:</label> <input type="text"
										class="form-control" id="nickname" name="nickname" 
										<%if (du != null) { %>
											value="<%= du.getNickname() %>"
										<%} %>
										required><br>
									
									<div id="mensajeNickname"></div>
									
								</div>
								<div class="col-6">
									<label for="email">Email:</label> <input type="email"
										class="form-control" id="email" name="email" 
										<%if (du != null) { %>
											value="<%= du.getEmail() %>"
										<%} %>
										required><br>
										
									<div id="mensajeEmail"></div>
									
								</div>
							</div>

							<div class="row my-3">
								<div class="col-6">
									<label for="passw">Contraseña:</label> <input type="password"
										class="form-control" id="passw" name="passw" required>
								</div>
								<div class="col-6">
									<label for="passw2">Confirmación contraseña:</label> <input
										type="password" class="form-control" id="passw2" name="passw2"
										required>
								</div>
							</div>
							
							<div class="col-auto my-3">
								<label for="fotoUpload">Foto <small>(opcional)</small>:</label><br>
								<input
									class="form-control" type="file" id="fotoUpload" name="fotoUpload"
									accept=".png, .jpg">
							</div>
							
							<!-- Socio vs Profe-->
							<div class="custom-control custom-radio my-4">
							
								<div class="form-check">
									<label class="form-check-label" for="radio_socio">Socio</label>
									<input class="form-check-input" type="radio" id="radio_socio" value="socio" name="usuario_radio"
										onclick="toggleFormProfe()" 
										<%if (du == null || du instanceof DtSocio) {%>
											checked
										<%} %>
										><br>
								</div>
								<div class="form-check">
									<label class="form-check-label" for="radio_profe">Profesor</label>
									<input class="form-check-input" type="radio"
										id="radio_profe" value="prof" name="usuario_radio"
										onclick="toggleFormProfe()"
											<%if (du != null && du instanceof DtProfesor) {%>
											checked
										<%} %>
										>
								</div>
							</div>
						</fieldset>

						<script>
							/*
							Muestra u oculta formulario profe segun
							radio button "radio_profe"
							 */
							function toggleFormProfe() {
								var x = document.getElementById("formProf");
								var radio = document
										.getElementById("radio_profe");
								if (radio.checked) {
									x.style.display = "block";
									document.getElementById("footer").style.clear = "both";
								} else {
									x.style.display = "none";
								}
							}
						</script>

						<div style="vertical-align: middle">

							<!-- Campos profesor -->
							<fieldset id="formProf" class="form-group" style="display: <%if (du == null || du instanceof DtSocio) {%>none<%} else {%>block<%}%>">
								<legend>Datos profesor:</legend>
								
								<div class="row">
									<div class="col-6 my-3">
										<label for="insti_profe">Institucion:</label>
										<select class="form-select" name="instituciones" id="instituciones">
											<option selected>-- Elegir institucion --</option>
											<%
											Set<String> instituciones = (Set<String>) request.getAttribute("instituciones");
											for (String inst : instituciones) {
											%>
											<option value="<%=inst%>"><%=inst%></option>
											<%
											}
											%>
										</select>
									</div>
									
									<div class="col-6 my-3">
										<label for="url_profe">Link <small>(opcional)</small>:</label>
										<input type="url" class="form-control" id="url_profe" name="url_profe"
											<%if (du != null && du instanceof DtProfesor) {%>
												value="<%= ((DtProfesor)du).getUrl() %>"
											<%} %>
										>
									</div>


								</div>

								<div class="row">

									<div class="col-6 my-3">
										<label for="bio_profe">Biografia <small>(opcional)</small>:</label>
										<textarea class="form-control" id="bio_profe" name="bio_profe"
										style="resize: none"><%if (du != null && du instanceof DtProfesor) {%><%= ((DtProfesor)du).getBiografia() %><%} %></textarea>
									</div>

									<div class="col-6 my-3">
										<label for="desc_profe">Descripción:</label>
										<textarea class="form-control" id="desc_profe" name="desc_profe"
										style="resize: none"><%if (du != null && du instanceof DtProfesor) {%><%= ((DtProfesor)du).getDescripcion() %><%} %></textarea>
									</div>

								</div>

							</fieldset>
						</div>
						
						<div class="my-5">

							<input type="submit" class="btn btn-primary" value="Confirmar"
								onclick="return checkAltaUsuarioForm()">
							<input
								type="button" class="btn btn-outline-primary" onclick="document.location='Index'"
								value="Cancelar">
						</div>


					</form>
				</div>
				<div class="col">
					<img class="img-fluid p-5" src="img/registrar.jpg" style="width:74vh;height:74vh;">
				</div>
			</div>

		<script>
			function checkAltaUsuarioForm() {
				var passw = document.getElementById("passw").value;
				var passw2 = document.getElementById("passw2").value;
				if (!(passw === passw2)) {
					window
							.alert("La contraseña y su confirmacion no coinciden");
					return false;
				}

				var esProf = document.getElementById("radio_profe").checked
				var desc = document.getElementById("desc_profe").value
				var inst = document.getElementById("instituciones").selectedIndex

				if (esProf && desc === "") {
					window.alert("La descripcion no puede ser vacia");
					return false;
				}

				if (esProf && inst === 0) {
					window.alert("La institucion no puede ser vacia");
					return false;
				}
				
				var okNickname = document.getElementById("okNickname").value;
				var okEmail = document.getElementById("okEmail").value;
				
				if (!(okNickname === "ok" && okEmail === "ok")) {
					return false;
				}

			}
			
			function validarNickname() {
				const xhttp = new XMLHttpRequest();
				xhttp.onload = function() {
					document.getElementById("mensajeNickname").innerHTML = this.responseText;
				}
				xhttp.open("GET", "ValidarAltaUsuario?nickname=" + document.getElementById("nickname").value, true);
				xhttp.send();
			}
			
			function validarEmail() {
				const xhttp = new XMLHttpRequest();
				xhttp.onload = function() {
					document.getElementById("mensajeEmail").innerHTML = this.responseText;
				}
				xhttp.open("GET", "ValidarAltaUsuario?email=" + document.getElementById("email").value, true);
				xhttp.send();
			}
			
			$('#nickname').keyup(validarNickname);
			$('#email').keyup(validarEmail);
			
		</script>
	</main>

	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>