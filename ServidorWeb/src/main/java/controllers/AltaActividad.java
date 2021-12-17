package controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
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
import servidor.DtSocio;
import servidor.ActividadRepetidaException_Exception;
import servidor.ColeccionString;
import servidor.DtProfesor;

/**
 * Servlet implementation class AltaActividad
 */
@MultipartConfig
@WebServlet("/AltaActividad")
public class AltaActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaActividad() {
        super();
        // TODO Auto-generated constructor stub
    }

    boolean checkFormulario(HttpServletRequest req) {
    	return true;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		Index.cargarDatosHeader(request);
		String user = (String) request.getSession().getAttribute("usuario_logueado");
		if (user == null) {
			//no esta logueado
			response.sendRedirect("IniciarSesion");
			return;
		}
		DtUsuario dtUser = port.getDatosUsuario(user);
		if (dtUser instanceof DtSocio) {
			//es un socio
			response.sendRedirect("Index");
			return;
		}
		// recordar institucion para mostrarsela a usuario en el formulario
		String institucionProfeLogueado = ((DtProfesor)dtUser).getInstitucion();
		request.setAttribute("institucion_user", institucionProfeLogueado);
		
		//muestra formulario de alta de actividad
		Set<String> setCats = new HashSet<>(port.getCategorias().getCol());
		request.setAttribute("categorias", setCats);
		request.getRequestDispatcher("/WEB-INF/jsp/altaActividad.jsp").forward(request, response);
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
		DtUsuario dtUser = port.getDatosUsuario(user);
		if (dtUser instanceof DtSocio) {
			//es un socio
			response.sendRedirect("Index");
			return;
		}
		Set<String> categorias = new HashSet<>(port.getCategorias().getCol());
		if (!checkFormulario(request)) {
			//muestra formulario de alta de actividad
			request.setAttribute("categorias", categorias);
        	request.getRequestDispatcher("/WEB-INF/jsp/altaActividad.jsp").forward(request, response);
        	return;
		}
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		int duracion = Integer.parseInt(request.getParameter("duracion"));
		float costo = Float.parseFloat(request.getParameter("costo"));
		Date fecha = new Date();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fecha);
		XMLGregorianCalendar fechaCal = null;
		try {
			fechaCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
		String institucion = ((DtProfesor) dtUser).getInstitucion();
		
		//obtengo la foto y la guardo en "fotoByteArray"
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
    	
    	ColeccionString colCats = new ColeccionString();
		List<String> cats = colCats.getCol();
		for (String cat : categorias) {
			String catPam = request.getParameter("cat_" + cat);
			if (catPam != null && catPam.equals("on")) {
				cats.add(cat);
			}
		}
				
		try {
			//alta de actividad correcto
			port.registrarActividad(institucion, colCats, nombre, user, descripcion, duracion, costo, fechaCal, fotoByteArray);
			response.sendRedirect("ConsultaActividad?nombre=" + nombre); // knock
		} catch (ActividadRepetidaException_Exception e) {
			//nombre de actividad repetido
			request.setAttribute("error_actividad_repetida", e.getMessage());
			request.setAttribute("categorias", categorias);
			request.setAttribute("nombreAltaActividad", nombre);
			request.setAttribute("descripcionAltaActividad", descripcion);
			request.setAttribute("duracionAltaActividad", duracion);
			request.setAttribute("costoAltaActividad", costo);
			request.setAttribute("institucion_user", institucion);
			request.getRequestDispatcher("/WEB-INF/jsp/altaActividad.jsp").forward(request, response);
		}
	}

}
