package controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DtClase;
import servidor.DtProfesor;
import servidor.DtUsuario;
import servidor.DtSocio;

/**
 * Servlet implementation class ConsultaUsuario
 */
@WebServlet("/ConsultaUsuario")
public class ConsultaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConsultaUsuario() {
		super();
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		Index.cargarDatosHeader(request);
		String usuarioTarget = request.getParameter("nickname");
		if (usuarioTarget != null) {

			String usuarioLogueado = (String) request.getSession().getAttribute("usuario_logueado");

			boolean hayUsuarioLogueado = usuarioLogueado != null;
			
			DtUsuario usr = port.getDatosUsuario(usuarioTarget);
			if (usr == null) {
				resp.sendRedirect("ConsultaUsuarios");
				return;
			}
			
			Set<DtUsuario> seguidores = new HashSet<DtUsuario>();
			Set<DtUsuario> seguidos = new HashSet<DtUsuario>();
			Set<String> nickSeguidores = new HashSet<>(usr.getSeguidores());
			Set<String> nickSeguidos = new HashSet<>(usr.getSeguidos());
			if (usr instanceof DtSocio) {
				List<String> clases = ((DtSocio)usr).getClases();
				Set<String> clasesFinalizadas = new HashSet<>();
				Set<String> clasesNoFinalizadas = new HashSet<>();
				for(String s: clases) {
					DtClase elem = port.getDatosDeClase(s);
					if (elem.isFinalizada()) {
						clasesFinalizadas.add(elem.getNombre());
					} else {
					clasesNoFinalizadas.add(elem.getNombre());
				}
				}
			request.setAttribute("clasesFinalizadas", clasesFinalizadas);
			request.setAttribute("clasesNoFinalizadas", clasesNoFinalizadas);
			}
			
			
			
			boolean esProf = usr instanceof DtProfesor;
			
			boolean loSigue = hayUsuarioLogueado && nickSeguidores.contains(usuarioLogueado);
			for (String s : nickSeguidores) {
				seguidores.add(port.getDatosUsuario(s));
			}
			for (String s : nickSeguidos) {
				seguidos.add(port.getDatosUsuario(s));
			}
			request.setAttribute("seguidores", seguidores);
			request.setAttribute("seguidos", seguidos);
			request.setAttribute("usuario", usr);
			request.setAttribute("logueado", usuarioLogueado);
			request.setAttribute("loSigue", loSigue);
			request.setAttribute("esProf", esProf);
			
			request.setAttribute("datos_usuario_target", usr);
			

			request.getRequestDispatcher("/WEB-INF/jsp/consultaUsuario.jsp").forward(request, resp);
		} else {
			// Si no viene de seleccionar ningun usuario lo redirijo a la consulta de
			// usuarios
			resp.sendRedirect("ConsultaUsuarios");

		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
