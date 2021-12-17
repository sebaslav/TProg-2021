package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DtSocio;
import servidor.DtUsuario;


/**
 * Servlet implementation class ConsultaCuponera
 */
@WebServlet("/ConsultaCuponera")
public class ConsultaCuponera extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultaCuponera() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		
		try {
			
			// nombre (identificador) de la cuponera
			String nomCuponera = request.getParameter("nombre_cuponera");
			if (nomCuponera == null) {
				response.sendRedirect("Index");
				return;
			}
			
			// Cargar datos de sesion al header y de instancias a costader
			Index.cargarDatosHeader(request);
			
			if (!port.getEspCuponeras().getCol().contains(nomCuponera)) {
				response.sendError(404);
				return;
			}
			
			// Index.cargatDatosHeader(...) carga en request DtUsuario con los datos
			// del usuario logueado.
			// Usamos esto para averiguar si es un Socio y si ya compro cuponera.
			DtUsuario datosUsrLogueado = (DtUsuario) request.getAttribute("datos_usuario_logueado");
			
			boolean haySocioLogueado = datosUsrLogueado != null
					&& datosUsrLogueado instanceof DtSocio;

			boolean socioNoComproCuponera = haySocioLogueado 
					&& !((DtSocio)datosUsrLogueado).getCuponeras().contains(nomCuponera);
			

			// DtCuponera sacada desde logica
			
			request.setAttribute("datos_cuponera", port.getDatosCuponera(nomCuponera));
	
			// DtCuponera para probar
			/*
			Set<Map.Entry<String, Integer>> actividadesPrueba = new HashSet<Map.Entry<String, Integer>>();
			Map.Entry<String, Integer> mapitaPrueba = new AbstractMap.SimpleEntry<String, Integer>("Actividad_prueba", 3);
			actividadesPrueba.add(mapitaPrueba);
	
			Set<String> categoriasPrueba = new HashSet<String>();
			categoriasPrueba.add("Categoria prueba uno");
			categoriasPrueba.add("Categoria prueba dos");
	
			DtCuponera dtcPrueba = new DtCuponera("NombreCuponera", "Esta es una cuponera de prueba. No existe en el programa", new Date(120, 8, 8), new Date(121, 7, 8), 0.2f, new Date(115, 10, 10),actividadesPrueba, categoriasPrueba, 0.451f, null);
			request.setAttribute("datos_cuponera", dtcPrueba);
			*/
			
			
			// chequear si hay un SOCIO logueado para mostrarle boton
			// de COMPRA de cuponera
			/*
			String usuarioLogueado = (String) request.getSession().getAttribute("usuario_logueado");
			
			if (usuarioLogueado != null) {
				IControladorUsuarios icu = Fabrica.getInstance().getControladorUsuario();
				DtUsuario dtUsr = icu.getDatosUsuario(usuarioLogueado);
				
				// chequear si es socio
				if (dtUsr instanceof DtSocio) {
					request.setAttribute("comprar_cuponera", true);
					System.out.println("mandioca");
				}
			}*/
			
			// Permitir compra de cuponera solo a aquellos socios
			request.setAttribute("usuario_logueado_es_socio", haySocioLogueado);
			request.setAttribute("usuario_logueado_puede_comprar", socioNoComproCuponera);
			/*
			if (haySocioLogueado) {
				System.out.println("mandioca");
			}
			*/
			
			request.getRequestDispatcher("/WEB-INF/jsp/consultaCuponera.jsp").forward(request, response);
			
		} catch (NullPointerException e) {
			
//			response.sendError(404); 
			response.sendError(404);
//			response.sendRedirect("/WEB-INF/jsp/404.jsp");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
