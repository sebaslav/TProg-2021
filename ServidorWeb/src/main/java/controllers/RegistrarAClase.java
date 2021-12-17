package controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import servidor.ClaseLlenaException_Exception;
import servidor.DtClase;
import servidor.SocioYaRegistradoException_Exception;


/**
 * Servlet implementation class RegistrarAClase
 */
@WebServlet("/RegistrarAClase")
public class RegistrarAClase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarAClase() {
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
		
		String usuario_logueado = (String) request.getSession().getAttribute("usuario_logueado");
		if (usuario_logueado == null) {
			response.sendRedirect("IniciarSesion");
			return;
		}
		String nomCup = request.getParameter("metPag");
		String nomClase = request.getParameter("nombre_clase");
		String nomAct = request.getParameter("nombre_actividad");
		try {
			Date fecha = new Date();
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(fecha);
			XMLGregorianCalendar fechaCal = null;
			try {
				fechaCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
			port.registrarSocioAClase(usuario_logueado, nomCup, fechaCal, nomClase, nomAct);
			response.sendRedirect("ConsultaClase?nombre=" + URLEncoder.encode(nomClase, "UTF-8"));
		} catch (SocioYaRegistradoException_Exception | ClaseLlenaException_Exception e) {
			//entro al catch solo en ClaseLlenaException
			DtClase dtClase = port.getDatosDeClase(nomClase);
			request.setAttribute("datos_clase", dtClase);
			Set<String> cups = new HashSet<>(port.getCuponerasDisponibles(usuario_logueado, nomAct).getCol());
			request.setAttribute("cups_disponibles", cups);
			request.setAttribute("error_clase_llena", e.getMessage());
			for (DtClase dt : port.getClasesVigentes(dtClase.getActividad()).getCol()) {
				if (dt.getNombre().equals(nomClase)) {
					request.setAttribute("clase_vigente", true);
					break;
				}
			}
			request.getRequestDispatcher("/WEB-INF/jsp/consultaClase.jsp").forward(request, response);
		}
	}

}
