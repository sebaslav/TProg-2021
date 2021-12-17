package controllers;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import servidor.CuponeraYaCompradaException_Exception;


/**
 * Servlet implementation class ComprarCuponera
 */
@WebServlet("/ComprarCuponera")
public class ComprarCuponera extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprarCuponera() {
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
			
			// nombre (identificador) de la cuponera
			String nomCuponera = request.getParameter("nombre_cuponera");
			try {
			
			// Nickname del usuario logueado esta en Sesion
			String nickUsuarioLogueado = (String) request.getSession().getAttribute("usuario_logueado");
		
			if (nickUsuarioLogueado == null) {
				response.sendRedirect("IniciarSesion");
				return;
			}
			
			// Agregarle la cuponera al usuario, con Logica pura y dura.
			Date fecha = new Date();
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(fecha);
			XMLGregorianCalendar fechaCal = null;
			try {
				fechaCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
			port.comprarCuponera(nickUsuarioLogueado, nomCuponera, fechaCal); // se toma la fecha actual del sistema
			
			
			
//			comprarCuponera(String nick, String nomCup, Date fechaCompra)
			
			// DtCuponera para probar
			/*
			Set<Map.Entry<String,Integer>> actividadesPrueba= new HashSet<Map.Entry<String,Integer>>();
			Map.Entry<String,Integer> mapitaPrueba = new AbstractMap.SimpleEntry<String, Integer>("Actividad_prueba", 3);
			actividadesPrueba.add(mapitaPrueba);
			
			Set<String> categoriasPrueba= new HashSet<String>();
			categoriasPrueba.add("Categoria prueba uno");
			categoriasPrueba.add("Categoria prueba dos");
			
			DtCuponera dtcPrueba = new DtCuponera("NombreCuponera", "Esta es una cuponera de prueba. No existe en el programa", new Date(120, 8, 8), new Date(121, 7, 8), 0.2f, new Date(115, 10, 10),actividadesPrueba, categoriasPrueba, 0.451f, null);
			request.setAttribute("datos_cuponera", dtcPrueba);
			*/
			
//			request.setAttribute("comprar_cuponera", true); // para que consultaCuponera.jsp despliege menu compra
			
			response.sendRedirect("ConsultaCuponera?nombre_cuponera=" + URLEncoder.encode(nomCuponera, "UTF-8"));
			
		} catch (CuponeraYaCompradaException_Exception e) {
			response.sendRedirect("ConsultaCuponera?nombre_cuponera=" + nomCuponera);
//			request.getRequestDispatcher("/WEB-INF/jsp/consultaCuponera.jsp").forward(request, response);
		} catch (NullPointerException e) {
			
			// no se selecciono cuponera
//			response.sendError(404); // el usuario no existe
			response.sendError(404);
//			response.sendRedirect("/WEB-INF/jsp/404.jsp");
		}
	}

}
