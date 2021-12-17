package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DtActividad;
import servidor.DtProfesor;
import servidor.DtUsuario;
import servidor.EstadoActividad;
import servidor.DtProfesor.Actividades.Entry;

/**
 * Servlet implementation class ConsultaActividadMobile
 */
@WebServlet("/ConsultaActividadMobile")
public class ConsultaActividadMobile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaActividadMobile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("usuario_logueado_mobile") != null) {
			servidor.PublicadorService service = new servidor.PublicadorService();
	        servidor.Publicador port = service.getPublicadorPort();
			IndexMobile.cargarDatosHeader(request);
			
			String nomActiv = request.getParameter("nombre");
			if (nomActiv == null) {
				response.sendRedirect("IndexMobile");
				return;
			}
			DtActividad dtActiv= port.getDtActividad(nomActiv);
			if (dtActiv == null) {
				//response.sendError(404);
				response.sendRedirect("IndexMobile");
				return;
			}
			request.setAttribute("datos_activ", dtActiv);
			if (dtActiv.getEstado() == EstadoActividad.ACEPTADA) {
				request.getRequestDispatcher("/WEB-INF/jsp/ConsultaActividadMobile.jsp").forward(request, response);
				return;
			}
			response.sendRedirect("IndexMobile");
		}
		else
			response.sendRedirect("IniciarSesionMobile");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
