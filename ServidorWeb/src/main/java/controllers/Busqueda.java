package controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DtActividad;
import servidor.DtCuponera;

/**
 * Servlet implementation class Busqueda
 */
@WebServlet("/Busqueda")
public class Busqueda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Busqueda() {
        super();
    }

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
		Index.cargarDatosHeader(request);
		
		String busqueda = request.getParameter("busqueda");
		if(busqueda == null) {
			response.sendRedirect("Index");
		}else {
			Set<DtActividad> actividades = new HashSet<>(port.buscarActividad(busqueda).getCol());
			Set<DtCuponera> cuponeras = new HashSet<>(port.buscarCuponera(busqueda).getCol());
			request.setAttribute("title", "Resultados de Busqueda para \"" + busqueda + "\"");
			request.setAttribute("actividades", actividades);
			request.setAttribute("cuponeras", cuponeras);
			request.getRequestDispatcher("/WEB-INF/jsp/busqueda.jsp").forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
