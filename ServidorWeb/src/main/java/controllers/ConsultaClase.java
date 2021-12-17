package controllers;

import java.io.IOException;
import java.util.Set;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DtClase;
import servidor.DtSocio;
import servidor.DtUsuario;
import servidor.DtProfesor;


/**
 * Servlet implementation class ConsultaClase
 */
@WebServlet("/ConsultaClase")
public class ConsultaClase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaClase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		Index.cargarDatosHeader(request);
		String usuario_logueado = (String) request.getSession().getAttribute("usuario_logueado");
		DtUsuario usrLogueado = (DtUsuario) request.getAttribute("datos_usuario_logueado");
		
		String nomClase = request.getParameter("nombre");
		if(nomClase == null) {
			response.sendRedirect("Index");
			return;
		}
		
		DtClase dtClase = port.getDatosDeClase(nomClase);
		if (dtClase == null) {
			response.sendError(404);
			return;
		}
		
		request.setAttribute("datos_clase", dtClase);
		
		if (usrLogueado != null && usrLogueado instanceof DtSocio) {
			Set<String> cups = new HashSet<>(port.getCuponerasDisponibles(usuario_logueado, dtClase.getActividad()).getCol());
			request.setAttribute("cups_disponibles", cups);
		}
		if (usrLogueado != null && usrLogueado instanceof DtProfesor && dtClase.getProfesor().equals(usuario_logueado) && 
				dtClase.getFechaHora().toGregorianCalendar().getTime().before(new Date()) && dtClase.getFechaSorteo() == null && dtClase.getCantPremios() > 0 && dtClase.getCantInscriptos() > 0) {
			request.setAttribute("puedoSortear", true);
		}
		if (usrLogueado != null && usrLogueado instanceof DtProfesor && dtClase.getProfesor().equals(usuario_logueado) && dtClase.getFechaSorteo() != null && dtClase.getCantPremios() > 0 && dtClase.getCantInscriptos() > 0) {
			request.setAttribute("puedoVerGanadores", true);
		}
		for (DtClase dt : port.getClasesVigentes(dtClase.getActividad()).getCol()) {
			if (dt.getNombre().equals(nomClase)) {
				request.setAttribute("clase_vigente", true);
				break;
			}
		}
		request.getRequestDispatcher("/WEB-INF/jsp/consultaClase.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

	