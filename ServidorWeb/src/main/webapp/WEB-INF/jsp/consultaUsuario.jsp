<%@page
	import="org.apache.tomcat.dbcp.dbcp2.datasources.InstanceKeyDataSource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="servidor.DtValoracion, servidor.DtUsuario, servidor.DtSocio, servidor.DtProfesor, servidor.DtProfesor.Clases, servidor.DtClase ,servidor.DtProfesor.Actividades, java.util.HashSet, java.util.List, java.util.Set, java.util.Map, java.util.Base64, java.util.Date, java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<title>Consultar Usuario</title>
</head>
<body>
	<!-- CABECERA -->
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	
	<main id="info-perfil" class="container my-2 border border-dark rounded">
		<%
		//Preparo los datos
		DtUsuario usr = (DtUsuario) request.getAttribute("usuario");
		Set<DtUsuario> seguidos = (Set<DtUsuario>) request.getAttribute("seguidos");
		Set<DtUsuario> seguidores = (Set<DtUsuario>) request.getAttribute("seguidores");
		Set<String> clasesFinalizadas = (Set<String> ) request.getAttribute("clasesFinalizadas");
		Set<String> clasesNoFinalizadas = (Set<String> ) request.getAttribute("clasesNoFinalizadas");
		String logueado = (String) request.getAttribute("logueado");
		boolean esProf = (boolean) request.getAttribute("esProf");
		boolean loSigue = (boolean) request.getAttribute("loSigue");
		%>

			<div class="row align-items-center" id="foto_y_datos">
				<div class="col-auto">
					<img class="img-fluid w-10"
						src="<%if (usr.getImagen() != null && usr.getImagen().length != 0) {%>
								data:image;base64, <%=Base64.getEncoder().encodeToString(usr.getImagen())%>
							 <%} else {%>
							 	img/usuario.png
							 <%}%>
							"
						alt="foto perfil"
						style="width: 200px; height: 200px; padding: 10px;">
				</div>
				<div class="col-2">
					<h3><%=usr.getNickname()%></h3>
					<h6><%=usr.getEmail()%></h6>
					<%
					if (esProf) {
					%>
					<p>Profesor</p>
					
					<%
					} else {
					%>
					<p>Socio</p>
					<%
					}
					if (logueado != null && !logueado.equals(usr.getNickname())) {
					if (!loSigue) {
					%>
					<form action="SeguirUsuario" method="POST">
						<input type="text" name="seguido" value="<%=usr.getNickname()%>" required style="display: none;">
						<button type="submit" class="btn btn-outline-primary">
						<i class="bi bi-plus-square"></i> Seguir
						</button>
					</form>
					
					<%
					} else {
					%>
					<form action="SeguirUsuario" method="POST">
						<input type="text" name="seguido" value="<%=usr.getNickname()%>" required style="display: none;">
						<button type="submit" class="btn btn-outline-primary">
						<i class="bi bi-dash-square"></i> Dejar de seguir
						</button>
					</form>
					
					<%
					}
					}
					%>
				</div>
				
				<%if (usr instanceof DtProfesor) { %>
				
				<div class="col-5">
					<!-- PUNTAJE PROMEDIO -->
					
					<div class="row">
					
						<%
						DtValoracion dtVal = ((DtProfesor) usr).getValoraciones();
						int totalValoraciones = 0;
						for (int i=1; i<=5; i++) {
							totalValoraciones += dtVal.getCantValores().get(i);
						}%>
					
						<div class="col-4 text-center">
							<%float promedio = dtVal.getValorPromedio();%>
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
					
						<div class="col">
						
						<%
						for (int i=5; i>0; i--) {
							float cantParcial = 0;
							if (totalValoraciones > 0) {
								cantParcial = ((float) dtVal.getCantValores().get(i) / totalValoraciones)*100;
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
										
									<div class="mx-2"> <%= dtVal.getCantValores().get(i) %></div>
								</div>
							</div>
							
						<%} %>
								
						</div>
					
					</div>
				</div>
				
				<%} %>
				
			</div>
			<div class="row pt-3" id="pestanias">
				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item " role="presentation">
						<button class="nav-link active" id="perfil-tab"
							data-bs-toggle="tab" data-bs-target="#perfil" type="button"
							role="tab" aria-controls="perfil" aria-selected="true">Perfil</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="clases-tab" data-bs-toggle="tab"
							data-bs-target="#clases" type="button" role="tab"
							aria-controls="clases" aria-selected="false">Clases</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="seguidores-tab" data-bs-toggle="tab"
							data-bs-target="#seguidores" type="button" role="tab"
							aria-controls="seguidores" aria-selected="false">Seguidores</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="seguidos-tab" data-bs-toggle="tab"
							data-bs-target="#seguidos" type="button" role="tab"
							aria-controls="seguidos" aria-selected="false">Seguidos</button>
					</li>
					<%
					if (esProf && logueado != null && logueado.equals(usr.getNickname())) {
					%>
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="actividades-tab" data-bs-toggle="tab"
							data-bs-target="#actividades" type="button" role="tab"
							aria-controls="actividades" aria-selected="false">Actividades</button>
					</li>
					<%
					} else if (logueado != null && logueado.equals(usr.getNickname())) {
					%>
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="cuponeras-tab" data-bs-toggle="tab"
							data-bs-target="#cuponeras" type="button" role="tab"
							aria-controls="cuponeras" aria-selected="false">Cuponeras</button>
					</li>
					<%}%>
				</ul>
			</div>
			<div class="tab-content py-4 px-2" id="myTabContent">
				<div class="tab-pane fade show active" id="perfil" role="tabpanel"
					aria-labelledby="perfil-tab">
					<label id="dato">Nombre:</label> <label><%=usr.getNombre()%></label>
					<br> <label id="dato">Apellido:</label> <label><%=usr.getApellido()%></label>
					<br> <label id="dato">Fecha de nacimiento:</label> <label><%=new SimpleDateFormat("dd/MM/yyyy").format(usr.getFechaDeNacimiento().toGregorianCalendar().getTime())%></label>
					<%
					if (esProf) {
					%>
					<br> <label id="dato">Institucion:</label> <label><%=((DtProfesor) usr).getInstitucion()%></label>
					<br> <label id="dato">Descripcion:</label> <label><%=((DtProfesor) usr).getDescripcion()%></label>
					<br> <label id="dato">Biografia:</label> <label><%=((DtProfesor) usr).getBiografia()%></label>
					<br> <label id="dato">URL:</label> <label><%=((DtProfesor) usr).getUrl()%></label>
					<br>
					<%}%>
					<br> <br>
					<%
					if (logueado != null && logueado.equals(usr.getNickname())) {
					%>
					<a class="btn btn-outline-primary"
						href="ModificarUsuario">
						<i class="bi bi-pencil-square"></i> Editar Datos
					</a>
					<%}%>
				</div>
				<div class="tab-pane fade" id="clases" role="tabpanel"
					aria-labelledby="clases-tab">
					<%
					if (!esProf) {
					%>
					<div class="row">
						<div class="col">
							<h5>Clases activas:</h5>
						</div>
					</div>
					<%
					for (String claseNoFinalizada : clasesNoFinalizadas) {%>
					<div class="col">
						<h6>
							<a href="ConsultaClase?nombre=<%=claseNoFinalizada%>"><%=claseNoFinalizada%></a>
						</h6>
					</div>
					<%
					} if (clasesNoFinalizadas.isEmpty()) { %> 
					<div class="alert alert-dark" role="alert">
					  No hay clases activas
					</div>
				<%}
					if (logueado != null && logueado.equals(usr.getNickname())) {%>
					<div class="row">
						<div class="col">
							<h5>Clases finalizadas:</h5>
						</div>
					</div>
					<%
					for (String claseFinalizada : clasesFinalizadas) {
						
					%>
					<div class="col">
						<h6>
							<a href="ConsultaClase?nombre=<%=claseFinalizada%>"><%=claseFinalizada%></a>
						</h6>
					</div>
					<% }
					}					
					if (clasesFinalizadas.isEmpty()) {%>
					<div class="alert alert-dark" role="alert">
					  No hay clases finalizadas
					</div>
				<%}
					
					} else { //osea si es prof
					%>
					
					<table class="table table-hover">
					    <thead>
					      <tr>
					        <th>Clase</th>
					        <th>Actividad</th>
					      </tr>
					    </thead>
					    <tbody>
					    
					    <%
					    Clases clases = ((DtProfesor) usr).getClases();
						for (Clases.Entry clase : clases.getEntry()) {
						%>
					      <tr>
					        <td><a href="ConsultaClase?nombre=<%=clase.getKey()%>"><%=clase.getKey()%></a></td>
					        <td><a href="ConsultaActividad?nombre=<%=clase.getValue()%>"><%=clase.getValue()%></a></td>
					      </tr>
					    <%
						}
						%>
					    </tbody>
				  </table>
					<%
					if (clases.getEntry().isEmpty()) {%>
					<div class="alert alert-dark" role="alert">
					  No hay clases
					</div>
				<% }
					}
					%>
				</div>
				<div class="tab-pane fade" id="seguidores" role="tabpanel"
					aria-labelledby="seguidores-tab">
					
					<ul class="list-group">
						<%if (seguidores.isEmpty()) {%>
							<div class="alert alert-dark" role="alert">
							  No hay seguidores
							</div>
						<%} 

						for (DtUsuario s : seguidores) {
						%>
						<li class="list-group-item">
							<div class="row">
								<div class="col-auto">
									<a href="ConsultaUsuario?nickname=<%=s.getNickname()%>">
									<img class="fotarda"
										src="<%if (s.getImagen() != null && s.getImagen().length != 0) {%>
										data:image;base64, <%=Base64.getEncoder().encodeToString(s.getImagen())%>
									 <%} else {%>
									 	img/usuario.png
									 <%}%>
									"
										alt="foto perfil"align="left"
											height="100" width="100"></a>
								</div>
		
								<div class="col">
									<h3><%=s.getNickname()%></h3>
									<h6><%=s.getNombre()%> <%=s.getApellido()%></h6>
									<a href="ConsultaUsuario?nickname=<%=s.getNickname()%>">Ver mas</a>		
								</div>
							</div>
						</li>
						<%
						}
						%>
					</ul>
					
				</div>
				<div class="tab-pane fade" id="seguidos" role="tabpanel"
					aria-labelledby="seguidos-tab">
					
					<ul class="list-group">
						<%if (seguidos.isEmpty()) {%>
								<div class="alert alert-dark" role="alert">
								  No hay seguidos
								</div>
						<%} 
						for (DtUsuario s : seguidos) {
						%>
						<li class="list-group-item">
							<div class="row">
						
							<div class="col-auto">
								<a href="ConsultaUsuario?nickname=<%=s.getNickname()%>">
								<img class="fotarda"
									src="<%if (s.getImagen() != null && s.getImagen().length != 0) {%>
									data:image;base64, <%=Base64.getEncoder().encodeToString(s.getImagen())%>
								 <%} else {%>
								 	img/usuario.png
								 <%}%>
								"
									alt="foto perfil"align="left"
										height="100" width="100"></a>
							</div>
	
							<div class="col">
								<h3><%=s.getNickname()%></h3>
								<h6><%=s.getNombre()%> <%=s.getApellido()%></h6>
								<a href="ConsultaUsuario?nickname=<%=s.getNickname()%>">Ver mas</a>		
							</div>
						</div>
						<%
						}
						%>
					</ul>
				</div>
				<%
				if (esProf && logueado != null && logueado.equals(usr.getNickname())) {
				%>
				<div class="tab-pane fade" id="actividades" role="tabpanel"
					aria-labelledby="actividades-tab">
					
					<table class="table table-hover">
					    <thead>
					      <tr>
					        <th>Actividad</th>
					        <th>Estado</th>
					      </tr>
					    </thead>
					    <tbody>
					    <%
					    Actividades activ = ((DtProfesor) usr).getActividades();
						for (Actividades.Entry act : activ.getEntry()) {
						%>
					      <tr>
					        <td><a href="ConsultaActividad?nombre=<%=act.getKey()%>"><%=act.getKey()%></a></td>
					        <td><%=act.getValue()%></td>
					      </tr>
					    <%
						}
						%>
					    </tbody>
				  </table>
					<%  if (activ.getEntry().isEmpty()) {%>
					<div class="alert alert-dark" role="alert">
					  No hay actividades ingresadas
					</div>
				<%} %>
				</div>
				<%
				} else if (logueado != null && logueado.equals(usr.getNickname())) {
				%>
				<div class="tab-pane fade" id="cuponeras" role="tabpanel"
					aria-labelledby="cuponeras-tab">
					<div class="row">
						<h5>Cuponeras</h5>
					</div>
					<%
					List<String> cuponeras = ((DtSocio) usr).getCuponeras();
					 if (cuponeras.isEmpty()) {%>
						<div class="alert alert-dark" role="alert">
						  No hay cuponeras
						</div>
					<%} 
					for (String cupo : cuponeras) {%>
					<div class="row">
						<a href="ConsultaCuponera?nombre_cuponera=<%=cupo%>"><%=cupo%></a>
					</div>
					<%
					}
					%>
				</div>
				<%
				}
				%>
			</div>
		
	</main>

	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>