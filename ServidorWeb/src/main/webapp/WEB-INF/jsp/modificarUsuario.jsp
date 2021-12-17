<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="servidor.DtUsuario, servidor.DtProfesor, java.text.SimpleDateFormat, java.util.Base64"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<title>Editar Datos</title>
</head>
<body>
	<%
	//Preparo los datos
	DtUsuario usr = (DtUsuario) request.getAttribute("datos_usuario_logueado");
	%>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	
	<main class="container py-3">
	
		<form action="ModificarUsuario" method="POST">
		
		<div class="row">
			<img class="img-fluid w-10"
				src="<%if (usr.getImagen() != null && usr.getImagen().length != 0) {%>
						data:image;base64, <%=Base64.getEncoder().encodeToString(usr.getImagen())%>
					 <%} else {%>
					 	img/usuario.png
					 <%}%>
					"
				alt="foto perfil"
				style="width: 200px; height: 200px; padding: 10px;">
				
			<div class="col">
				<div class="row">
					<div class="col-1">
						<label for="inputNickname" class="col-form-label">Nickname:</label>
					</div>
					<div class="col-auto">
						<input type="text" readonly class="form-control-plaintext"
							id="inputNickname" value="<%=usr.getNickname()%>" required>
					</div>
				</div>
				
				<div class="row">
					<div class="col-1">
						<label for="inputEmail" class="col-form-label">Email:</label>
					</div>
					<div class="col-auto">
						<input type="text" readonly class="form-control-plaintext"
							id="inputEmail" value="<%=usr.getEmail()%>" required>
					</div>
				</div>

				<%
				if (usr instanceof DtProfesor) {
				%>
					<div class="row">
						<div class="col-1">
							<label for="inputinstitucion" class="col-form-label">Institucion:</label>
						</div>
						<div class="col-auto">
							<input type="text" readonly class="form-control-plaintext"
								id="inputinstitucion"
								value="<%=((DtProfesor) usr).getInstitucion()%>">
						</div>
					</div>
				<%
				}
				%>
			</div>
		</div>
		
		
		<div class="row my-3">
			<div class="col-2">
				<label class="form-label" for="inputNombre" class="col-form-label">Nombre</label>
				<input type="text" id="inputNombre" class="form-control"
					name="inputNombre" value="<%=usr.getNombre()%>" required>
			</div>
		
			<div class="col-2">
				<label for="inputApellido" class="form-label">Apellido</label>
				<input type="text" id="inputApellido" class="form-control"
					name="inputApellido" value="<%=usr.getApellido()%>" required>
			</div>
		
			<div class="col-2">
				<label for="inputFecha" class="form-label">Fecha de
					Nacimiento</label>
				<%
				String pattern = "yyyy-MM-dd";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String fecha = simpleDateFormat.format(usr.getFechaDeNacimiento().toGregorianCalendar().getTime());
				%>
				<input type="date" id="inputFecha" class="form-control"
					name="inputFecha" value="<%=fecha%>" required>
			</div>
		</div>
		
		<%
		if (usr instanceof DtProfesor) {
		%>
			<div class="row my-3">
				
				<div class="col-3">
					<label for="inputDescripcion" class="form-label">Descripcion</label>
					<textarea id="inputDescripcion" class="form-control"
						name="inputDescripcion"
						required><%=((DtProfesor) usr).getDescripcion()%></textarea>
				</div>
	
				<div class="col-3">
					<label for="inputBiografia" class="form-label">Biografia</label>
					<textarea id="inputBiografia" class="form-control"
						aria-describedby="biografiaHelpInline" name="inputBiografia"><%=((DtProfesor) usr).getBiografia()%></textarea>
					<span id="biografiaHelpInline" class="form-text"><i>campo
							opcional</i></span>
				</div>
				
			</div>
			
			<div class="row my-3">
				<div class="col-auto">
					<label for="inputUrl" class="col-form-label">URL</label>
					<input id="inputUrl" class="form-control"
						aria-describedby="urlHelpInline" name="inputUrl"
						value="<%=((DtProfesor) usr).getUrl()%>">
					<span id="urlHelpInline" class="form-text"><i>campo
							opcional</i></span>
				</div>
			</div>
		<%}%>
		
			<div class="my-5">
				<input type="submit" class="btn btn-primary" value="Editar">
				<input
					type="button" class="btn btn-outline-primary" onclick="document.location='ConsultaUsuario?nickname=<%= usr.getNickname()%>'"
					value="Cancelar">
			</div>
			
		</form>
	
	</main>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
</body>
</html>