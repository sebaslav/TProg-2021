package controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import servidor.DtProfesor;
import servidor.DtUsuario;


@WebServlet("/ModificarUsuario")
public class ModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModificarUsuario() {
		super();

	}

	

	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		Index.cargarDatosHeader(request);
		// Corroboro que este logueado
		if (request.getSession().getAttribute("usuario_logueado") != null) {
			request.getRequestDispatcher("/WEB-INF/jsp/modificarUsuario.jsp").forward(request, resp);
			
		} else {
			resp.sendRedirect("Index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		Index.cargarDatosHeader(request);
		request.setCharacterEncoding("UTF-8");
		if (request.getSession().getAttribute("usuario_logueado") == null) {
			response.sendRedirect("Index");
			return;
		}
		String nickname = (String) request.getSession().getAttribute("usuario_logueado");
		DtUsuario usr = port.getDatosUsuario(nickname);
		usr.setNombre((String) request.getParameter("inputNombre"));
		usr.setApellido((String) request.getParameter("inputApellido"));
		String[] fechaNoParseada = request.getParameter("inputFecha").split("-");
    	int anio = Integer.parseInt(fechaNoParseada[0]);
    	int mes = Integer.parseInt(fechaNoParseada[1]);
    	int dia = Integer.parseInt(fechaNoParseada[2]);
    	Date fechaNac = new Date(anio-1900, mes-1, dia);
    	GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fechaNac);
		try {
			usr.setFechaDeNacimiento(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
    	
		if (usr instanceof DtProfesor) {
			((DtProfesor) usr).setBiografia((String) request.getParameter("inputBiografia"));
			((DtProfesor) usr).setDescripcion((String) request.getParameter("inputDescripcion"));
			((DtProfesor) usr).setUrl((String) request.getParameter("inputUrl"));
		}
		port.editarDatosUsuario(usr);
		response.sendRedirect("ConsultaUsuario?nickname="+ URLEncoder.encode(nickname, "UTF-8"));
	}

}
