package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DtUsuario;


/**
 * Servlet implementation class IniciarSesion
 */
@WebServlet("/IniciarSesion")
public class IniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Index.cargarDatosHeader(request);
		if (request.getSession() != null && request.getSession().getAttribute("usuario_logueado") != null) {
			//ya esta logueado
			response.sendRedirect("Index");
		} else {
		//muestra formulario de inicio de sesion
		request.getRequestDispatcher("/WEB-INF/jsp/iniciarSesion.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		Index.cargarDatosHeader(request);
		request.setCharacterEncoding("UTF-8");
		if (request.getSession().getAttribute("usuario_logueado") != null) {
			//ya esta logueado
			response.sendRedirect("Index");
			return;
		}
		String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || password == null) {
        	//muestra formulario de inicio de sesion
        	request.getRequestDispatcher("/WEB-INF/jsp/iniciarSesion.jsp").forward(request, response);
        	return;
        }
        if (port.iniciarSesion(login, password)) {
        	//inicio de sesion correcto
        	DtUsuario dtUser = port.getDatosUsuario(login);
        	request.getSession().setAttribute("usuario_logueado", dtUser.getNickname());
        	
        	response.sendRedirect("Index");
        } else {
        //inicio de sesion incorrecto
        request.setAttribute("error_login", true);
        request.setAttribute("login", login);
        request.getRequestDispatcher("/WEB-INF/jsp/iniciarSesion.jsp").forward(request, response);
        }
	}

}
