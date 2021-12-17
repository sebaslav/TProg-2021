<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="servidor.DtCuponera, servidor.DtCuponera.Actividades, java.util.List, java.util.Base64, java.util.Date, java.text.SimpleDateFormat, java.util.Set, java.util.Map"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<title>Ver Cuponera</title>

</head>
<body>

	<!-- CABECERA -->
	<jsp:include page="/WEB-INF/jsp/header.jsp" />

	<!-- CONTENIDO PRINCIPAL -->
	<main class="container">
		<section class="container pt-5 pb-5 text-center text-sm-start">
			<div class="row">

				<div class="col-sm px-5">
					<%
					// dtCup no es nulo, eso se lo chequea desde el servlet
					DtCuponera dtCup = (DtCuponera) request.getAttribute("datos_cuponera");
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					%>
					<img class="img-fluid w-10"
							src="<%if (dtCup.getImagen() != null && dtCup.getImagen().length != 0) {%>
									data:image;base64, <%=Base64.getEncoder().encodeToString(dtCup.getImagen())%>
								 <%} else {%>
								 	img/noImageAvailable.png
								 <%}%>
								"
							alt="foto perfil"
							style="width: 400px; height: 400px; padding: 10px;">
				</div>
				<div class="col-sm px-5" id="info-area">
					<h2>
						Cuponera:
						<%=dtCup.getNombre()%>
					</h2>
					<dl>

						<dt>Descripción:</dt>
						<dd><%=dtCup.getDescripcion()%></dd>

						<dt>Costo:</dt>
						<dd>
							$<%=String.valueOf(dtCup.getCosto())%></dd>

						<dt>Descuento:</dt>
						<dd><%=String.valueOf(100-100 * dtCup.getDescuento())%>%
						</dd>


						<dt>Período de Validez:</dt>
						<dd>
							Desde:
							<%=sdf.format(dtCup.getValidoDesde().toGregorianCalendar().getTime())%></dd>
						<dd>
							Hasta:
							<%=sdf.format(dtCup.getValidoHasta().toGregorianCalendar().getTime())%></dd>
					</dl>
					
					<!-- SI USUARIO LOGUEADO ES SOCIO Y NO COMPRO LA CUPONERA, PERMITIR COMPRARLA -->
					<%
					// Primero chequeamos que sea un Socio
					if (request.getAttribute("usuario_logueado_es_socio") != null 
					&& (boolean)request.getAttribute("usuario_logueado_es_socio") == true) {
					
						// Si es Socio, chequeamos si ya compro la cuponera.
						if (request.getAttribute("usuario_logueado_puede_comprar") != null 
								&& (boolean)request.getAttribute("usuario_logueado_puede_comprar") == true) {
					%>
						<!-- BOTON COMPRAR CUPONERA -->
						<form action="ComprarCuponera" method="POST">
							<input name="nombre_cuponera" value="<%= dtCup.getNombre()%>" style="Display:none;">
							<button type="submit" class="btn btn-primary">Comprar	cuponera</button>
						</form>
					<%
					} else { 
					%>
						<!-- AVISO DE QUE YA COMPRO LA CUPONERA -->
						<div>
							<button class="btn btn-lg btn-outline-danger" disabled>
							Ya tiene esta cuponera
							</button>
						</div>
					<%
						}
					}
					%>
				</div>
			</div>
			<div class="row-1"></div>
			<div class="row">
				<div id="info-area" class="container col-sm px-5 my-3 py-2 text-center" >
					<!-- 			<div class="subcolumn"> -->
					<!-- 			<div> -->
					<h3>Actividades deportivas asociadas</h3>
					<div id="listaActividadesCuponera"
						style="float: bottom; width: 95%; height: 63%;">
						<ul class="list-group text-start">
							<%
							Actividades activs = dtCup.getActividades();
							for (Actividades.Entry ent : activs.getEntry()) {
							%>
							<li class="list-group-item"><a href="ConsultaActividad?nombre=<%=ent.getKey()%>"><%=ent.getKey()%></a>
							</li>
							<%
							}
							%>
						</ul>
					</div>
				</div>
				
				<div class="col-auto"></div>
				
				<div id="info-area" class="container col-sm px-5 my-3 py-2 text-center">
				
					<h3>Categorias asociadas</h3>
					<div id="listaCategoriasCuponera"
						style="float: bottom; width: 95%; height: 66%;">
						<ul class="list-group text-start">
							<%
							List<String> cups = dtCup.getCategorias();
							for (String nomCup : cups) {
							%>
							<li class="list-group-item"><a href="ConsultaActividades?categoria=<%=nomCup%>"><%=nomCup%></a>
							</li>
							<%
							}
							%>
						</ul>
					</div>
				</div>
			</div>
		</section>
	</main>

	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>