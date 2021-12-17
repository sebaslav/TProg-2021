<!-- HEADER -->
<header class="mb-3">
	<%@ page
		import="servidor.DtUsuario, servidor.DtSocio, servidor.DtProfesor, java.util.Date, java.util.Base64, java.util.Base64.Encoder, java.util.Set, java.util.HashSet"%>
	
	<% 
	DtUsuario usuario_logueado_dt = (DtUsuario) request.getAttribute("datos_usuario_logueado");
	Set<String> categorias_header = (Set<String>) request.getAttribute("categorias_header");
	Set<String> instituciones_header = (Set<String>) request.getAttribute("instituciones_header");
	Set<String> cuponeras_header = (Set<String>) request.getAttribute("cuponeras_header");
	%>
	<nav class="navbar bg-dark navbar-dark fixed-top">
		<div class="container">

			<!-- BOTON DE COSTADER -->
			<div>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="offcanvas" data-bs-target="#costader"
					aria-controls="costader">
					<span class="navbar-toggler-icon"></span>
				</button>
				<a href="Index" class="navbar-brand">Entrenamos.uy</a>
			</div>

			<!-- BARRA DE BUSQUEDA -->
			<div>
				<form class="d-none d-md-flex" action="Busqueda" method="GET">
					<input class="form-control me-2" type="search" placeholder="Actividades, Cuponeras"
						aria-label="Search" name="busqueda">
					<button class="btn btn-success d-inline-flex" type="submit"><i class="bi bi-search me-2"></i>Buscar</button>
				</form>
			</div>


			<%
			if (usuario_logueado_dt != null) {	
			%>
			<!-- DROPDOWN DE SESION LOGUEADO -->
			<div class="dropdown">
				<button class="btn btn-success dropdown-toggle" type="button"
					id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">

					<!-- FOTO PERFIL -->
					
					<img class=""
						src="<%
							 if (usuario_logueado_dt.getImagen().length != 0) {
							 %>
								data:image;base64, <%=Base64.getEncoder().encodeToString(usuario_logueado_dt.getImagen()) %>
							 <%} else {%>
							 	img/usuario.png
							 <%}%>
							" alt="foto perfil"
						srcset="" width="30" height="30">
					
					<%=usuario_logueado_dt.getNickname()%>

				</button>
				<ul class="dropdown-menu dropdown-menu-dark"
					aria-labelledby="dropdownMenuButton1">
					<li>
						<a href="ConsultaUsuario?nickname=<%=usuario_logueado_dt.getNickname()%>" class="nav-link">
							Ver perfil
						</a>
					</li>
					<li>
						<a href="CerrarSesion" class="nav-link">
							Cerrar sesion
						</a>
					</li>
				</ul>
			
			<%
			} else {
				// Visitante
			%>
			<!-- DROPDOWN DE SESION VISITANTE -->
			<div>
				<button class="btn btn-success"
					onclick="document.location='IniciarSesion'">Iniciar
					sesion</button>
				<button class="btn btn-success"
					onclick="document.location='AltaUsuario'">Registrar
					usuario</button>
			</div>
			<%
			}
			%>
			</div>

		</div>


	</nav>


	<!-- COSTADER -->
	<!-- El costader es una barra de navegacion comun a todas las paginas. -->
	<!-- No forma parte del body de cada pagina. -->

	<nav class="navbar navbar-dark bg-dark fixed-left">
		<div class="container-fluid">
			<div class="offcanvas offcanvas-start" tabindex="-1" id="costader"
				aria-labelledby="offcanvasNavbarLabel">
				<div class="offcanvas-header bg-dark">
					<!-- H5 vacio para empujar la X de cerrar hacia el costado -->
					<h5 class="offcanvas-title" id="offcanvasNavbarLabel"></h5>

					<!-- boton X para cerrar costader -->
					<button type="button" class="btn-close text-reset bg-light"
						data-bs-dismiss="offcanvas" aria-label="Close"></button>
				</div>
				<div class="offcanvas-body">
					<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">

						<!-- COSTADER DROPDOWN Categorias -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="offcanvasNavbarDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> Categorias </a>
							<ul class="dropdown-menu"
								aria-labelledby="offcanvasNavbarDropdown">
								<%if (categorias_header != null) { 
									for (String cat : categorias_header) { %>
									<li><a class="dropdown-item" href="ConsultaActividades?categoria=<%=cat%>"><%=cat%></a></li>
								<% } }%>
							</ul></li>

						<!-- COSTADER DROPDOWN Instituciones -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="offcanvasNavbarDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">
								Instituciones </a>
							<ul class="dropdown-menu"
								aria-labelledby="offcanvasNavbarDropdown">
								<%if (instituciones_header != null) { 
									for (String ins : instituciones_header) { %>
									<li><a class="dropdown-item" href="ConsultaActividades?institucion=<%=ins%>"><%=ins%></a></li>
								<% } }%>
							</ul></li>

						<!-- COSTADER DROPDOWN Cuponeras -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="offcanvasNavbarDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> Cuponeras </a>
							<ul class="dropdown-menu"
								aria-labelledby="offcanvasNavbarDropdown">
								<%if (cuponeras_header != null) { 
									for (String cup : cuponeras_header) { %>
									<li><a class="dropdown-item" href="ConsultaCuponera?nombre_cuponera=<%=cup%>"><%=cup%></a></li>
								<% } }%>
							</ul></li>

						<!-- COSTADER LINK Ver usuarios -->
						<li  class="nav-item"><a id="offcanvasNavbarDropdown"class="nav-link" href="ConsultaUsuarios">Ver
								usuarios</a></li>
						
						<!-- COSTADER LINK Registrar actividad -->
						<%if (usuario_logueado_dt !=null && usuario_logueado_dt instanceof DtProfesor) {%>	
						<li class="nav-item"><a id="offcanvasNavbarDropdown" class="nav-link" href="AltaActividad">Registrar actividad</a></li>
						<li class="nav-item"><a id="offcanvasNavbarDropdown" class="nav-link" href="AltaClase">Registrar clase</a></li>
						<%} %>	
						
						<!-- COSTADER LINK Ver Premios -->
						<%if (usuario_logueado_dt !=null && usuario_logueado_dt instanceof DtSocio) {%>	
						<li class="nav-item"><a id="offcanvasNavbarDropdown" class="nav-link" href="VerPremios">Ver mis premios</a></li>
						<%} %>	
						
					</ul>
				</div>
			</div>
		</div>
	</nav>
</header>
