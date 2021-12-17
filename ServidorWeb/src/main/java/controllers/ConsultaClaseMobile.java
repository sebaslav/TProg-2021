package controllers;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DtClase;
import servidor.DtProfesor;
import servidor.DtSocio;
import servidor.DtUsuario;

/**
 * Servlet implementation class ConsultaClaseMobile
 */
@WebServlet("/ConsultaClaseMobile")
public class ConsultaClaseMobile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultaClaseMobile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("usuario_logueado_mobile") != null) {
			servidor.PublicadorService service = new servidor.PublicadorService();
			servidor.Publicador port = service.getPublicadorPort();
			IndexMobile.cargarDatosHeader(request);

			String nomClase = request.getParameter("nombre");
			if (nomClase == null) {
				response.sendRedirect("IndexMobile");
				return;
			}

			DtClase dtClase = port.getDatosDeClase(nomClase);
			if (dtClase == null) {
				response.sendError(404);
				return;
			}

			request.setAttribute("datos_clase", dtClase);
			for (DtClase dt : port.getClasesVigentes(dtClase.getActividad()).getCol()) {
				if (dt.getNombre().equals(nomClase)) {
					request.setAttribute("clase_vigente", true);
					break;
				}
			}
			request.getRequestDispatcher("/WEB-INF/jsp/consultaClaseMobile.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
