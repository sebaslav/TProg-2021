package controllers;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DtActividad;
import servidor.DtProfesor;
import servidor.DtProfesor.Actividades.Entry;
import servidor.DtUsuario;
import servidor.EstadoActividad;


/**
 * Servlet implementation class ConsultaActividad
 */
@WebServlet("/FinalizarActividad")
public class FinalizarActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizarActividad() {
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
		String nomActiv = request.getParameter("actividad");
		
		if (nomActiv == null) {
			response.sendRedirect("Index");
			return;
		} else {
			port.finalizarActividad(nomActiv);
			response.sendRedirect("ConsultaActividad?nombre=" + URLEncoder.encode(nomActiv, "UTF-8"));
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
