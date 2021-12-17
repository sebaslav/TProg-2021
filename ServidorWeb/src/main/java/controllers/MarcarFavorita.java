package controllers;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DtProfesor;
import servidor.DtUsuario;

/**
 * Servlet implementation class MarcarFavorita
 */
@WebServlet("/MarcarFavorita")
public class MarcarFavorita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarcarFavorita() {
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
		
		DtUsuario dtUser = (DtUsuario) request.getAttribute("datos_usuario_logueado");
		if (dtUser == null) {
			response.sendRedirect("IniciarSesion");
			return;
		}
		
		if (dtUser instanceof DtProfesor) {
			response.sendRedirect("Index");
			return;
		}
		
		String nomAct = request.getParameter("nomAct");
		port.cambiarActividadFavorita(dtUser.getNickname(), nomAct);
		response.sendRedirect("ConsultaActividad?nombre=" + URLEncoder.encode(nomAct, "UTF-8"));
	}

}
