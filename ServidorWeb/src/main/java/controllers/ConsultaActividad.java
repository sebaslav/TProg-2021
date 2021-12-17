package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import servidor.DtActividad;
import servidor.DtClase;
import servidor.DtProfesor;
import servidor.DtProfesor.Actividades.Entry;
import servidor.DtUsuario;
import servidor.EstadoActividad;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;



/**
 * Servlet implementation class ConsultaActividad
 */
@WebServlet("/ConsultaActividad")
public class ConsultaActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaActividad() {
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
		
		String nomActiv = request.getParameter("nombre");
		if (nomActiv == null) {
			response.sendRedirect("Index");
			return;
		}
		DtActividad dtActiv= port.getDtActividad(nomActiv);
		if (dtActiv == null) {
			response.sendError(404);
			return;
		}
		request.setAttribute("datos_activ", dtActiv);
		boolean finalizable = true;
		Iterator<String> it = dtActiv.getClases().iterator();
		while (it.hasNext() && finalizable) {
			String clase = it.next();
			DtClase claseInfo = port.getDatosDeClase(clase);
			Date actual = new Date();
			GregorianCalendar calActual = new GregorianCalendar();
			calActual.setTime(actual);
					
			if (claseInfo.getFechaHora().toGregorianCalendar().compareTo(calActual) > 0) {
				finalizable = false;
			}
		}
		if (finalizable && dtActiv.getEstado() == EstadoActividad.ACEPTADA) 
			request.setAttribute("finalizable",true);
		
		DtUsuario dtUser = (DtUsuario) request.getAttribute("datos_usuario_logueado");
		if (dtUser != null && dtUser instanceof DtProfesor) {
			DtProfesor dtProf = (DtProfesor) dtUser;
			for (Entry entry : dtProf.getActividades().getEntry()) {
				if (entry.getKey().equals(nomActiv)) {
					request.setAttribute("del_profesor_logueado", true);
					break;
				}
			}
			if (port.getInstitucionDeActividad(nomActiv).equals(dtProf.getInstitucion())) {
				request.setAttribute("puede_crear_clase", true);
			}
		}
		
		
		if (dtActiv.getEstado() == EstadoActividad.ACEPTADA || request.getAttribute("del_profesor_logueado") != null) {
			request.getRequestDispatcher("/WEB-INF/jsp/consultaActividad.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("Index");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
