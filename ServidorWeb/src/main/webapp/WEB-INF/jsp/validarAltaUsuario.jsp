<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Boolean yaExiste = (Boolean) request.getAttribute("yaExiste");
String nickname = request.getParameter("nickname");
String email = request.getParameter("email");

if (nickname != null && !nickname.equals("")) {
	if (yaExiste) { %>
		<h6 class="text-danger"><i class="bi bi-exclamation-triangle"></i> El nickname no está disponible</h6>
		<input id="okNickname" style="display: none" value="no">
	<%} else { %>
		<h6 class="text-success"><i class="bi bi-check-lg"></i> El nickname está disponible</h6>
		<input id="okNickname" style="display: none" value="ok">
	<%}
}

if (email != null && !email.equals("")) {
	if (yaExiste) { %>
		<h6 class="text-danger"><i class="bi bi-exclamation-triangle"></i> El email no está disponible</h6>
		<input id="okEmail" style="display: none" value="no">
	<%} else { %>
		<h6 class="text-success"><i class="bi bi-check-lg"></i> El email está disponible</h6>
		<input id="okEmail" style="display: none" value="ok">
	<%}
}
%>