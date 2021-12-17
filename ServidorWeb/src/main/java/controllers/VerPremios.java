package controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidor.DtUsuario;
import servidor.DtProfesor;
import servidor.DtPremio;

/**
 * Servlet implementation class VerPremios
 */
@WebServlet("/VerPremios")
public class VerPremios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerPremios() {
        super();
        // TODO Auto-generated constructor stub
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
			response.sendRedirect("IniciarSesion");
			return;
		}
		
		if (dtUser instanceof DtProfesor) {
			response.sendRedirect("Index");
			return;
		}
		
		List<DtPremio> premios = port.consultarPremios(dtUser.getNickname()).getCol();
		Collections.sort(premios, new Comparator<DtPremio>() {
			@Override
			public int compare(DtPremio p1, DtPremio p2) {
				Date d1 = p1.getFechaSorteo().toGregorianCalendar().getTime();
				Date d2 = p2.getFechaSorteo().toGregorianCalendar().getTime();
				return d2.compareTo(d1);
			}
		});
		request.setAttribute("premios", premios);
    	request.getRequestDispatcher("/WEB-INF/jsp/verPremios.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
