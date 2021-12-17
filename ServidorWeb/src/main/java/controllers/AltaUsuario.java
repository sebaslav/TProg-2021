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

import servidor.DtProfesor;
import servidor.DtSocio;
import servidor.DtUsuario;
import servidor.UsuarioRepetidoException_Exception;


/**
 * Servlet implementation class AltaUsuario
 */
@MultipartConfig
@WebServlet("/AltaUsuario")
public class AltaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

    boolean checkFormulario(HttpServletRequest req) {
    	return true;
    }
    
    DtUsuario generarDtUsuario(HttpServletRequest req) throws ServletException, IOException{
    	DtUsuario res;
    	String nombre = req.getParameter("nombre");
    	String apellido = req.getParameter("apellido");
    	String nickname = req.getParameter("nickname");
    	String email = req.getParameter("email");
    	String[] fechaNoParseada = req.getParameter("fecha_nac").split("-");
    	int anio = Integer.parseInt(fechaNoParseada[0]);
    	int mes = Integer.parseInt(fechaNoParseada[1]);
    	int dia = Integer.parseInt(fechaNoParseada[2]);
    	Date fechaNac = new Date(anio-1900, mes-1, dia);
    	GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fechaNac);
    	String password = req.getParameter("passw");
    	
    	//obtengo la foto y la guardo en "fotoByteArray"
    	Part fotoPart = req.getPart("fotoUpload");
    	InputStream isFoto = fotoPart.getInputStream();
    	ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    	int nRead;
    	byte[] data = new byte[4096];
    	while ((nRead = isFoto.read(data, 0, data.length)) != -1) {
    		buffer.write(data, 0, nRead);
    	}
    	buffer.flush();
    	byte[] fotoByteArray = buffer.toByteArray();    	
    	
    	if (req.getParameter("usuario_radio").equals("socio")) {
    		//es un socio
    		res = new DtSocio();
    		
    	} else {
    		//es un profesor
    		String bio_profe = req.getParameter("bio_profe");
        	String desc_profe = req.getParameter("desc_profe");
        	String url_profe = req.getParameter("url_profe");
        	String inst_profe = req.getParameter("instituciones");
        	res = new DtProfesor();
    		((DtProfesor) res).setBiografia(bio_profe);
    		((DtProfesor) res).setDescripcion(desc_profe);
    		((DtProfesor) res).setUrl(url_profe);
    		((DtProfesor) res).setInstitucion(inst_profe);
    	}
    	res.setNickname(nickname);
		res.setNombre(nombre);
		res.setApellido(apellido);
		res.setEmail(email);
		try {
			res.setFechaDeNacimiento(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		res.setContrasenia(password);
		res.setImagen(fotoByteArray);
    	return res;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		Index.cargarDatosHeader(request);
		if (request.getSession().getAttribute("usuario_logueado") != null) {
			//ya esta logueado
			response.sendRedirect("Index");
			return;
		}
		//muestra formulario de alta de usuario
		Set<String> instituciones = new HashSet<>(port.getInstituciones().getCol());
		request.setAttribute("instituciones", instituciones);
        request.getRequestDispatcher("/WEB-INF/jsp/altaUsuario.jsp").forward(request, response);
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
		Set<String> instituciones = new HashSet<>(port.getInstituciones().getCol());
		request.setAttribute("instituciones", instituciones);
		if (!checkFormulario(request)) {
			//muestra formulario de alta de usuario
        	request.getRequestDispatcher("/WEB-INF/jsp/altaUsuario.jsp").forward(request, response);
        	return;
		}
		DtUsuario dtUser = generarDtUsuario(request);
		try {
			//alta de usuario correcto
			if (dtUser instanceof DtSocio) {
				port.registrarSocio((DtSocio) dtUser);
			} else {
				port.registrarProfesor((DtProfesor) dtUser);
			}
			response.sendRedirect("IniciarSesion");
		} catch (UsuarioRepetidoException_Exception e) {
			//nickname o email repetidos
			request.setAttribute("error_user_repetido", e.getMessage());
			request.setAttribute("dtUserIngresado", dtUser);
			request.getRequestDispatcher("/WEB-INF/jsp/altaUsuario.jsp").forward(request, response);
		}
	}

}
