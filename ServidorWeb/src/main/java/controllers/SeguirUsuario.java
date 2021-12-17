package controllers;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DtUsuario;


/**
 * Servlet implementation class SeguirUsuario
 */
@WebServlet("/SeguirUsuario")
public class SeguirUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeguirUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Index");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		Index.cargarDatosHeader(request);
		request.setCharacterEncoding("UTF-8");
		String user = (String) request.getSession().getAttribute("usuario_logueado");
		if (user == null) {
			//no esta logueado
			response.sendRedirect("IniciarSesion");
			return;
		}
		String seguido = request.getParameter("seguido");
		if (seguido == null || seguido.equals(user)) {
			response.sendRedirect("Index");
			return;
		}
		DtUsuario dtUser = port.getDatosUsuario(user);
		if (!port.getListaUsuarios().getCol().contains(seguido)) {
			//usuario seguido no existe
			request.setAttribute("error_no_encontrado", "El usuario " + seguido + " no se encuentra en el sistema");
			request.setAttribute("usuario_logueado", dtUser);
			request.getRequestDispatcher("/WEB-INF/jsp/404.jsp").forward(request, response);
			return;
		}
		if (dtUser.getSeguidos().contains(seguido)) {
			port.dejarSeguirUsuario(user, seguido);
		} else {
			port.seguirUsuario(user, seguido);
		}
		response.sendRedirect("ConsultaUsuario?nickname=" + URLEncoder.encode(seguido, "UTF-8"));
	}

}
