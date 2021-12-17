package controllers;

import java.io.IOException;
import java.util.Set;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DtUsuario;

/**
 * Servlet implementation class ConsultaUsuarios
 */
@WebServlet("/ConsultaUsuarios")
public class ConsultaUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private void processRequest(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
    	servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
    	Index.cargarDatosHeader(request);
    	
		// Consigo la lista de Usuarios y la seteo como atributo
    	
		Set<String> usuarios = new HashSet<>(port.getListaUsuarios().getCol());
		Set<DtUsuario> dtUsuarios = new HashSet<DtUsuario>();
		for (String s : usuarios) {
			dtUsuarios.add(port.getDatosUsuario(s));
		}
		request.setAttribute("DtUsuarios", dtUsuarios);
		request.getRequestDispatcher("/WEB-INF/jsp/consultaUsuarios.jsp").forward(request, resp);
    	
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
