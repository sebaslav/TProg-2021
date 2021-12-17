<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="servidor.DtUsuario, servidor.DtSocio, servidor.DtActividad, servidor.DtCuponera, java.util.Set, java.text.SimpleDateFormat, java.util.Base64"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<title>Buscar</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />

	<main class="container py-3">
			<%
			DtUsuario dtUser = (DtUsuario) request.getAttribute("datos_usuario_logueado");
			Set<DtActividad> actividades = (Set<DtActividad>) request.getAttribute("actividades");
			Set<DtCuponera> cuponeras = (Set<DtCuponera>) request.getAttribute("cuponeras");
			Set<String> categorias_header = (Set<String>) request.getAttribute("categorias_header");
			Set<String> instituciones_header = (Set<String>) request.getAttribute("instituciones_header");
			%>

			<div class="row">
				<div class="row">
					<h3><%=(String) request.getAttribute("title")%></h3>
				</div>
				<div class="row align-items-center">
					<div class="col-md-4">
						<label for="orden_de_busqueda">Ordenar por:</label> <select
							id="orden_de_busqueda">
							<option value="">-- Seleccione una opcion --</option>
							<option value="alfa">Alfabeticamente (A-Z a-z)</option>
							<option value="fecha">Año (descendente)</option>
						</select>
					</div>
					<div class="col-md-4">
						<label for="filtro_categoria">Filtrar por categoria</label> 
						<select
							id="filtro_categoria">
							<option value="">-- Todas --</option>
							<%
							if (categorias_header != null) {
								for (String cat : categorias_header) {
							%>
							<option value="<%=cat%>"><%=cat%></option>
							<%
							}
							}
							%>
						</select>
					</div>
					<div class="col-md-4">
						<label for="filtro_institucion">Filtrar por institucion</label> <select
							id="filtro_institucion">
							<option value="">-- Todas --</option>
							<%
							if (instituciones_header != null) {
								for (String ins : instituciones_header) {
							%>
							<option value="<%=ins%>"><%=ins%></option>
							<%
							}
							}
							%>
						</select>
					</div>
				</div>
			</div>

			<div class="row" >

				

				<ul id="busqueda_lista_acts"  class="list-group con-marco my-3">
					<h2>Actividades</h2>
					
					<%if (actividades.isEmpty()) {%>
						<div class="alert alert-dark" role="alert">
						  No se han encontrado resultados para tu busqueda
						</div>
					<%} 
					for (DtActividad actividad : actividades) {
					%>

					<li class="list-group-item" style="background-color:#dbdbdb;">
						<div class="row">
							
							<div class="col-auto">
								<a href="ConsultaActividad?nombre=<%=actividad.getNombre()%>">
								<img class="fotarda" src="<%if (actividad.getImagen() != null && actividad.getImagen().length != 0) {%>
								data:image;base64, <%=Base64.getEncoder().encodeToString(actividad.getImagen())%>
							 <%} else {%>
							 	img/noImageAvailable.png
							 <%}%>
							"
						alt="foto actividad" align="left"
									height="100" width="100"></a>
								</div>
								
								<div class="col">
								<h3><%=actividad.getNombre()%></h3>
								
								<%
								boolean soc = dtUser != null && dtUser instanceof DtSocio;		
								boolean esFav = false;
								if (soc) {
									esFav = ((DtSocio) dtUser).getFavoritas().contains(actividad.getNombre());
								} %>
									<span id="heartFav" class="bi 
										<%if (esFav) { %>
											bi-heart-fill
										<%} else { %>
											bi-heart
										<%} %>
									 text-danger" style="font-size: 20px;"> <%= actividad.getCantFavoritas() %></span>
								
								
								<p><%=actividad.getDescripcion()%></p>
								<a href="ConsultaActividad?nombre=<%=actividad.getNombre()%>">Ver mas</a>
								<p style="display:none;" class="fechaReg"><%=new SimpleDateFormat("yyyy/MM/dd").format(actividad.getFechaRegistro().toGregorianCalendar().getTime())%></p>
								<p style="display:none;" class="busqueda_inst"><%=actividad.getInstitucion()%></p>
								<ul style="display:none;" class="list-group">
									<%for (String cat : actividad.getCategorias()) {%>
									<li class="list-group-item"><%=cat%></li>
									<%
									}
									%>
								</ul>
								</div>
							
							
						</div>
					</li>
					<%
					}
					%>
				</ul>

				

				<ul id="busqueda_lista_cups" class="list-group con-marco my-3">
					<h2>Cuponeras</h2>
					
					<%if (cuponeras.isEmpty()) {%>
						<div class="alert alert-dark" role="alert">
						  No se han encontrado resultados para tu busqueda
						</div>
					<%} 
					for (DtCuponera cuponera : cuponeras) {
					%>

					<li class="list-group-item">
						<div class="row">
							<div class="col-auto">
								<a href="ConsultaCuponera?nombre_cuponera=<%=cuponera.getNombre()%>">
								<img class="fotarda" src="<%if (cuponera.getImagen() != null && cuponera.getImagen().length != 0) {%>
								data:image;base64, <%=Base64.getEncoder().encodeToString(cuponera.getImagen())%>
							 <%} else {%>
							 	img/noImageAvailable.png
							 <%}%>
							"
						alt="foto perfil" align="left"
									height="100" width="100"></a>
									</div>
									<div class="col">
								<h3><%=cuponera.getNombre()%></h3>
								<p><%=cuponera.getDescripcion()%></p>
								<a href="ConsultaCuponera?nombre_cuponera=<%=cuponera.getNombre()%>">Ver mas</a>
								<p style="display:none;" class="fechaReg"><%=new SimpleDateFormat("yyyy/MM/dd").format(cuponera.getFechaRegistro().toGregorianCalendar().getTime())%></p>
								<ul style="display:none;" class="list-group">
									<%for (String cat : cuponera.getCategorias()) {%>
									<li class="list-group-item"><%=cat%></li>
									<%
									}
									%>
								</ul>
							
							
							</div>
						</div>
					</li>
					<%
					}
					%>
				</ul>


				<script>
					var lista_acts = $("#busqueda_lista_acts > li");
					var lista_cups = $("#busqueda_lista_cups > li");

					function change(link) {
						window.location.href = link;
					}

					var sort_by_name = function(a, b) {
						return a.innerHTML.toLowerCase().localeCompare(
								b.innerHTML.toLowerCase());
					}

					var sort_by_date = function(a, b) {
						return $(b).find(".fechaReg").text().toLowerCase()
								.localeCompare(
										$(a).find(".fechaReg").text()
												.toLowerCase());
					}

					var ordenar = function(lista, sort_type) {
						lista.sort(sort_type);
						for (var i = 0; i < lista.length; i++) {
							lista[i].parentNode.appendChild(lista[i]);
						}
					}

					var aplicar_filtros = function() {

						lista_acts.show();
						lista_cups.show();

						var filtro_cat = $("#filtro_categoria").val();
						var filtro_inst = $("#filtro_institucion").val();

						if (filtro_cat != "") {
							lista_acts.each(function(i) {
								if ($(this).find("li").text().indexOf(
										filtro_cat) == -1) {
									$(this).hide();
								}
							});
							lista_cups.each(function(i) {
								if ($(this).find("li").text().indexOf(
										filtro_cat) == -1) {
									$(this).hide();
								}
							});
						}

						if (filtro_inst != "") {
							lista_acts
									.each(function(i) {
										if ($(this).find(".busqueda_inst")
												.text() != filtro_inst) {
											$(this).hide();
										}
									});
						}
					}

					$("#orden_de_busqueda").change(function() {
						var tipo_orden = $("#orden_de_busqueda").val();

						if (tipo_orden === "alfa") {
							ordenar(lista_acts.get(), sort_by_name);
							ordenar(lista_cups.get(), sort_by_name);
						} else if (tipo_orden === "fecha") {
							ordenar(lista_acts.get(), sort_by_date);
							ordenar(lista_cups.get(), sort_by_date);
						}
					});

					$("#filtro_categoria").change(aplicar_filtros);
					$("#filtro_institucion").change(aplicar_filtros);
				</script>

			</div>

	</main>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>