package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import servidor.DtUsuario;
import servidor.DtPremio;
import servidor.DtProfesor;



/**
 * Servlet implementation class GenerarPDF
 */
@WebServlet("/PremioPDF")
public class PremioPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PremioPDF() {
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
		
		DtUsuario dtUser = (DtUsuario) request.getAttribute("datos_usuario_logueado");
		if (dtUser == null) {
			response.sendRedirect("IniciarSesion");
			return;
		}
		if (dtUser instanceof DtProfesor) {
			response.sendRedirect("Index");
			return;
		}
		String nomClase = request.getParameter("nomClase");
		
		List<DtPremio> premios = port.consultarPremios(dtUser.getNickname()).getCol();
		DtPremio premio = null;
		for (DtPremio p : premios) {
			if (p.getNomClase().equals(nomClase)) {
				premio = p;
				break;
			}
		}
		Calendar c= new GregorianCalendar();
		c.setTime(premio.getFechaSorteo().toGregorianCalendar().getTime());
		c.add(Calendar.DATE, 30);
		Date fechaVencimiento = c.getTime();
		String fechaV = new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento);
		
		ServletOutputStream os = response.getOutputStream();
		response.setContentType("application/pdf"); 
		Document doc = new Document();
		Font bfBold10 = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0)); 
		
		try {
			PdfWriter.getInstance(doc, os); 
			doc.open();
			doc.addTitle("Mi Premio");
			doc.add(new Paragraph("Socio: " + dtUser.getNombre() + " " + dtUser.getApellido(), bfBold10));
			doc.add(new Paragraph("Descripcion del premio: " + premio.getDescPremio(), bfBold10));
			doc.add(new Paragraph("Actividad deportiva: " + premio.getNomActividad(), bfBold10));
			doc.add(new Paragraph("Clase: " + premio.getNomClase(), bfBold10));
			doc.add(new Paragraph("Fecha limite para canjear el premio: " + fechaV, bfBold10));
			doc.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}

}
