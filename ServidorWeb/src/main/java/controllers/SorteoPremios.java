package controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DtClase;
import servidor.DtSocio;
import servidor.DtUsuario;


/**
 * Servlet implementation class SeguirUsuario
 */
@WebServlet("/SorteoPremios")
public class SorteoPremios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		Index.cargarDatosHeader(request);
		
		DtUsuario dtUser = (DtUsuario) request.getAttribute("datos_usuario_logueado");
		if (dtUser == null) {
			response.sendRedirect("IniciarSesion");
			return;
		}
		
		String nomClase = request.getParameter("nombreClase");
		DtClase dtClase = port.getDatosDeClase(nomClase);
		if (dtClase == null) {
			response.sendError(404);
			return;
		}
		
		if (dtUser instanceof DtSocio || !dtClase.getProfesor().equals(dtUser.getNickname())) {
			response.sendRedirect("Index");
			return;
		}
		
		Date fechaDate = dtClase.getFechaHora().toGregorianCalendar().getTime();
		if (fechaDate.after(new Date())) {
			response.sendRedirect("Index");
			return;
		}
		
		if (dtClase.getFechaSorteo() == null) {
			Set<String> participantes = new HashSet<>(port.getInscriptosAClase(dtClase.getNombre()).getCol());
			Set<DtUsuario> usuarios = new HashSet<DtUsuario>();
			for(String p : participantes) {
				usuarios.add(port.getDatosUsuario(p));
			}
			request.setAttribute("participantes", usuarios);
			request.setAttribute("nombreClase", nomClase);
	    	request.getRequestDispatcher("/WEB-INF/jsp/participantesSorteo.jsp").forward(request, response);
		} else {
			Set<String> ganadores = new HashSet<>(port.verGanadores(dtClase.getNombre()).getCol());
			Set<DtUsuario> usuarios = new HashSet<DtUsuario>();
			for(String g : ganadores) {
				usuarios.add(port.getDatosUsuario(g));
			}
			request.setAttribute("ganadores", usuarios);
			request.setAttribute("nombreClase", nomClase);
	    	request.getRequestDispatcher("/WEB-INF/jsp/participantesSorteo.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		Index.cargarDatosHeader(request);
		
		String nomClase = request.getParameter("nombreClase");
		
		DtClase dtClase = port.getDatosDeClase(nomClase);
		Date fechaDate = dtClase.getFechaHora().toGregorianCalendar().getTime();
		if (fechaDate.after(new Date()) || dtClase.getFechaSorteo() != null) {
			response.sendRedirect("Index");
			return;
		}
		
		port.sortearPremios(nomClase);
		response.sendRedirect("SorteoPremios?nombreClase=" + URLEncoder.encode(nomClase, "UTF-8"));
	}

}
