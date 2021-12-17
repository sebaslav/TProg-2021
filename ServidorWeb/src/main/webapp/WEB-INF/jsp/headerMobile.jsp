<!-- HEADER -->
<header class=mb-3>
	<%@ page
		import="servidor.DtUsuario, servidor.DtSocio, servidor.DtProfesor, java.util.Date, java.util.Base64, java.util.Base64.Encoder, java.util.Set, java.util.HashSet"%>

	<%
	DtUsuario user_log = (DtUsuario) request.getAttribute("datos_usuario_logueado_mobile");
	Set<String> categorias_header = (Set<String>) request.getAttribute("categorias_header");
	Set<String> instituciones_header = (Set<String>) request.getAttribute("instituciones_header");
	String Nickname = user_log.getNickname();
	String Nombre = user_log.getNombre();
	%>
	<nav class="navbar navbar-dark bg-dark fixed-top">
		<div class="container-fluid">
			<div>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
					aria-controls="offcanvasNavbar">
					<span class="navbar-toggler-icon"></span>
				</button>
				<a class="navbar-brand text-light  href="IndexMobile">Entrenamos.uy</a>

			</div>
			<p class="text-light d-inline">
				<%=Nickname%>
			</p>
			<div class="offcanvas offcanvas-start bg-success bg-gradient"
				tabindex="-1" id="offcanvasNavbar"
				aria-labelledby="offcanvasNavbarLabel">
				<div class="offcanvas-header">
					<h3 class="offcanvas-title fs-bold text-light text-center"
						id="offcanvasNavbarLabel">¡Bienvenido!</h3>
					<button type="button" class="btn-close text-reset"
						data-bs-dismiss="offcanvas" aria-label="Close"></button>
				</div>
				<div class="offcanvas-body bg-dark ">

					<ul class="navbar-nav justify-content-end flex-grow-1 pe-3 ">
						<li class=" text-center mb-3"><img class=""
							src="<%if (user_log.getImagen().length != 0) {%>
								data:image;base64, <%=Base64.getEncoder().encodeToString(user_log.getImagen())%>
							 <%} else {%>
							 	img/usuario.png
							 <%}%>
							"
							alt="foto perfil" width="200px" height="200px"></li>
						<li class=" text-center mb-3 text-light"><p>
								<%=Nombre
								%>
							</p></li>
						<li class=" text-center fs-2 text-light"><p>Instituciones</p></li>
						<ul class="list-group text-center fw-bold mb-3">
							<%
							if (instituciones_header != null) {
								for (String ins : instituciones_header) {
							%>
							<li class="list-group-item"><a href="ConsultaActividadesMobile?institucion=<%=ins%>"><%=ins%></a></li>
							<%
							}
							}
							%>
						</ul>
						<li class=" text-center fs-2 text-light"><p>Categorias</p></li>
						<ul class="list-group text-center fw-bold">
						<%if (categorias_header != null) { 
							for (String cat : categorias_header) { %>
							<li class="list-group-item"><a href="ConsultaActividadesMobile?categoria=<%=cat%>"><%=cat%></a></li>
							<%}} %>
						</ul>
					</ul>

					<div class="text-center mt-5 border border-2 border-success">
						<a class="nav-link text-light fs-4 text-align-bottom " href="CerrarSesionMobile">Cerrar
							Sesion</a>
					</div>

				</div>
			</div>
		</div>
	</nav>
</header>
<script src="js/jquery-3.3.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
	crossorigin="anonymous"></script>

