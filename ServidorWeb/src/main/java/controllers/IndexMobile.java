package controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexMobile
 */
@WebServlet("/IndexMobile")
public class IndexMobile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexMobile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void cargarDatosHeader(HttpServletRequest req) {
		servidor.PublicadorService service = new servidor.PublicadorService();
		servidor.Publicador port = service.getPublicadorPort();

		String usuario_logueado = (String) req.getSession().getAttribute("usuario_logueado_mobile");
		if (usuario_logueado != null) {
			servidor.DtUsuario dtUser = port.getDatosUsuario(usuario_logueado);
			req.setAttribute("datos_usuario_logueado_mobile", dtUser);
		}

		List<String> listaCats = port.getCategorias().getCol();
		Set<String> setCats = new HashSet<>(listaCats);
		req.setAttribute("categorias_header", setCats);

		List<String> listaInsts = port.getInstituciones().getCol();
		Set<String> setInsts = new HashSet<>(listaInsts);
		req.setAttribute("instituciones_header", setInsts);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute("usuario_logueado_mobile") != null) {
			cargarDatosHeader(request);
			request.getRequestDispatcher("/WEB-INF/jsp/indexMobile.jsp").forward(request, response);
		} else
			response.sendRedirect("IniciarSesionMobile");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
