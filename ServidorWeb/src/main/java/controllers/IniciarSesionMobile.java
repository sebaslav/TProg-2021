package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DtProfesor;
import servidor.DtUsuario;

/**
 * Servlet implementation class IniciarSesionMobile
 */
@WebServlet("/IniciarSesionMobile")
public class IniciarSesionMobile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarSesionMobile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Index.cargarDatosHeader(request);
		if (request.getSession() != null && request.getSession().getAttribute("usuario_logueado_mobile") != null) {
			//ya esta logueado
			response.sendRedirect("IndexMobile");
		} else {
			//muestra formulario de inicio de sesion
			Cookie[] cookies = request.getCookies();     // request is an instance of type 
            //HttpServletRequest
			
			for (int i = 0; i < cookies.length; i++) { 
				Cookie c = cookies[i];
				if (c.getName().equals("rememberMe")) {
					String login = c.getValue();
					request.setAttribute("login", login);
				}
				if (c.getName().equals("pass")) {
					String pass = c.getValue();
					request.setAttribute("pass", pass);
				}
			}  
			request.getRequestDispatcher("/WEB-INF/jsp/iniciarSesionMobile.jsp").forward(request, response);
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
		if (request.getSession().getAttribute("usuario_logueado_mobile") != null) {
			//ya esta logueado
			response.sendRedirect("IndexMobile");
			return;
		}
		String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || password == null) {
        	//muestra formulario de inicio de sesion
        	request.getRequestDispatcher("/WEB-INF/jsp/iniciarSesionMobile.jsp").forward(request, response);
        	return;
        }
        if (port.iniciarSesion(login, password)) {
        	//inicio de sesion correcto
        	DtUsuario dtUser = port.getDatosUsuario(login);
        	boolean esProf = (dtUser instanceof DtProfesor);
        	if(!esProf) {
        		request.getSession().setAttribute("usuario_logueado_mobile", dtUser.getNickname());
        		
        		//remember me
        		if(request.getParameter("rememberMe") != null && ((String) request.getParameter("rememberMe")).equals("on")) {
        		    Cookie c = new Cookie("rememberMe", login);
        		    c.setMaxAge(24*60*60);
        		    Cookie c2 = new Cookie("pass", password);
        		    c2.setMaxAge(24*60*60);
        		    response.addCookie(c);
        		    response.addCookie(c2);  // response is an instance of type HttpServletReponse
        		}
        		
        		response.sendRedirect("IndexMobile");
        	}
        	else {
        		request.setAttribute("error_prof", true);
                request.getRequestDispatcher("/WEB-INF/jsp/iniciarSesionMobile.jsp").forward(request, response);
        	}
        } else {
        //inicio de sesion incorrecto
        request.setAttribute("error_login", true);
        request.setAttribute("login", login);
        request.getRequestDispatcher("/WEB-INF/jsp/iniciarSesionMobile.jsp").forward(request, response);
        }
	}

}
