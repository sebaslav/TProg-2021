package controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import servidor.DtUsuario;
import servidor.ClaseRepetidaException_Exception;
import servidor.DtClase;
import servidor.DtProfesor;
import servidor.DtSocio;


/**
 * Servlet implementation class AltaClase
 */
@MultipartConfig
@WebServlet("/AltaClase")
public class AltaClase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaClase() {
        super();
        // TODO Auto-generated constructor stub
    }

    boolean checkFormulario(HttpServletRequest req) {
    	return true;
    }
    
    DtClase generarDtClase(HttpServletRequest request) throws ServletException, IOException{
    	String nombreClase = request.getParameter("nombre");
		String[] fechaNoParseada = request.getParameter("fecha_dictado").split("-");
    	int anio = Integer.parseInt(fechaNoParseada[0]);
    	int mes = Integer.parseInt(fechaNoParseada[1]);
    	int dia = Integer.parseInt(fechaNoParseada[2]);
    	String[] horaInicio = request.getParameter("hora_inicio").split(":");
    	int hora = Integer.parseInt(horaInicio[0]);
    	int minuto = Integer.parseInt(horaInicio[1]);
    	
    	Date fechaHoraInicio = new Date(anio-1900, mes-1, dia, hora, minuto);
		GregorianCalendar InicioCal = new GregorianCalendar();
		InicioCal.setTime(fechaHoraInicio);
		XMLGregorianCalendar fechaHoraInicioCal = null;
		
		Date fechaRegistro = new Date();
		GregorianCalendar RegistroCal = new GregorianCalendar();
		RegistroCal.setTime(fechaRegistro);
		XMLGregorianCalendar fechaRegistroCal = null;
		
		try {
			fechaHoraInicioCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(InicioCal);
			fechaRegistroCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(RegistroCal);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
		int cantMax = Integer.parseInt(request.getParameter("cant_max"));
		int cantMin = Integer.parseInt(request.getParameter("cant_min"));
		String url = request.getParameter("url");
		
		//obtengo la foto y la codifico en "fotoString"
    	Part fotoPart = request.getPart("fotoUpload");
    	InputStream isFoto = fotoPart.getInputStream();
    	ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    	int nRead;
    	byte[] data = new byte[4096];
    	while ((nRead = isFoto.read(data, 0, data.length)) != -1) {
    		buffer.write(data, 0, nRead);
    	}
    	buffer.flush();
    	byte[] fotoByteArray = buffer.toByteArray();
    	
    	String videoURL = request.getParameter("videoURL");
    	String descPremio = request.getParameter("descPremio");
		int cantPremios = Integer.parseInt(request.getParameter("cantPremios"));

    	DtClase dtClase = new DtClase();
    	dtClase.setNombre(nombreClase);
    	dtClase.setFechaHora(fechaHoraInicioCal);
    	dtClase.setMaxSocios(cantMax);
    	dtClase.setMinSocios(cantMin);
    	dtClase.setFechaDeRegistro(fechaRegistroCal);
    	dtClase.setUrl(url);
    	dtClase.setImagen(fotoByteArray);
    	dtClase.setVideoURL(videoURL);
    	dtClase.setDescPremio(descPremio);
    	dtClase.setCantPremios(cantPremios);
    	return dtClase;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		Index.cargarDatosHeader(request);
		
		DtUsuario dtUser = (DtUsuario) request.getAttribute("datos_usuario_logueado");
		if (dtUser == null) {
			//no esta logueado
			response.sendRedirect("IniciarSesion");
			return;
		}
		if (dtUser instanceof DtSocio) {
			//es un socio
			response.sendRedirect("Index");
			return;
		}
		//muestra formulario de alta de clase
		DtProfesor dtProf = (DtProfesor) dtUser;
		Set<String> actividades = new HashSet<>(port.getActividadesAceptadasDeInstitucion(dtProf.getInstitucion()).getCol());
		request.setAttribute("actividades", actividades);
		String actividad = request.getParameter("actividad");
		if (actividad != null && actividades.contains(actividad)) {
			request.setAttribute("actividad", actividad);
		}
		request.getRequestDispatcher("/WEB-INF/jsp/altaClase.jsp").forward(request, response);
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
			//no esta logueado
			response.sendRedirect("IniciarSesion");
			return;
		}
		if (dtUser instanceof DtSocio) {
			//es un socio
			response.sendRedirect("Index");
			return;
		}
		DtProfesor dtProf = (DtProfesor) dtUser;
		Set<String> actividades = new HashSet<>(port.getActividadesAceptadasDeInstitucion(dtProf.getInstitucion()).getCol());
		if (!checkFormulario(request)) {
			//muestra formulario de alta de clase
			request.setAttribute("actividades", actividades);
        	request.getRequestDispatcher("/WEB-INF/jsp/altaClase.jsp").forward(request, response);
        	return;
		}
		String nombreActividad = request.getParameter("actividad");
		String institucion = ((DtProfesor) dtUser).getInstitucion();
		DtClase dtClase = generarDtClase(request);
		try {
			//alta de clase correcto
			port.registrarClase(nombreActividad, institucion, dtUser.getNickname(), dtClase);
			response.sendRedirect("ConsultaClase?nombre=" + dtClase.getNombre());
		} catch (ClaseRepetidaException_Exception e) {
			//nombre de clase repetido
			request.setAttribute("error_clase_repetida", e.getMessage());
			request.setAttribute("actividades", actividades);
			request.setAttribute("actividad", nombreActividad);
			request.setAttribute("dtClaseIngresada", dtClase);
			request.getRequestDispatcher("/WEB-INF/jsp/altaClase.jsp").forward(request, response);
		}
	}

}
