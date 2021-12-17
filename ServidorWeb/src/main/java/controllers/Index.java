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


/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static void cargarDatosHeader(HttpServletRequest req) {
    	servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        
    	String usuario_logueado = (String) req.getSession().getAttribute("usuario_logueado");
    	if (usuario_logueado != null) {
    		servidor.DtUsuario dtUser = port.getDatosUsuario(usuario_logueado);
            req.setAttribute("datos_usuario_logueado", dtUser);
    	}
    	
    	List<String> listaCats = port.getCategorias().getCol();
    	Set<String> setCats = new HashSet<>(listaCats);
    	req.setAttribute("categorias_header", setCats);
    	
    	List<String> listaInsts = port.getInstituciones().getCol();
    	Set<String> setInsts = new HashSet<>(listaInsts);
    	req.setAttribute("instituciones_header", setInsts);
    	
    	List<String> listaCups = port.getEspCuponeras().getCol();
    	Set<String> setCups = new HashSet<>(listaCups);
    	req.setAttribute("cuponeras_header", setCups);
    }
    
    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
    	cargarDatosHeader(req);
    	req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
