package controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DtActividad;

/**
 * Servlet implementation class ConsultaActividades
 */
@WebServlet("/ConsultaActividades")
public class ConsultaActividades extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultaActividades() {
		super();
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();

		Index.cargarDatosHeader(request);

		String institucion = (String) request.getParameter("institucion");
		String categoria = (String) request.getParameter("categoria");
//		IControladorInstituciones ICI = Fabrica.getInstance().getControladorInstituciones();
		if (institucion != null && categoria == null) {
			if (!port.getInstituciones().getCol().contains(institucion)) {
				response.sendError(404);
				return;
			}
			
			// obtener las actividades de la institucion
			Set<String> nomActividades = new HashSet<>(port.getActividadesAceptadasDeInstitucion(institucion).getCol());
			Set<DtActividad> datosActividades = new HashSet<>();
			for (String actividad : nomActividades) {
				datosActividades.add(port.getDtActividad(actividad));
			}
			request.setAttribute("datosActividades", datosActividades);
			request.setAttribute("title", "Actividades de " + institucion);
			request.getRequestDispatcher("/WEB-INF/jsp/consultaActividades.jsp").forward(request, response);
		} else if (institucion == null && categoria != null) {
			if (!port.getCategorias().getCol().contains(categoria)) {
				response.sendError(404);
				return;
			}
			
			// obtener las actividades de la categoria
			Set<String> actividades = new HashSet<>(port.getActividadesAceptadasDeCategoria(categoria).getCol());
			Set<DtActividad> datosActividades = new HashSet<>();
			for (String actividad : actividades) {
				datosActividades.add(port.getDtActividad(actividad));
			}
			request.setAttribute("datosActividades", datosActividades);
			request.setAttribute("title", "Actividades de la categoría " + categoria);
			request.getRequestDispatcher("/WEB-INF/jsp/consultaActividades.jsp").forward(request, response);
		} else {
			response.sendRedirect("Index");
		}
		/**
		 * } else{ response.sendError(404);
		 * request.getRequestDispatcher("/WEB-INF/jsp/404.jsp"). include(request,
		 * response); }
		 *
		 */
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
