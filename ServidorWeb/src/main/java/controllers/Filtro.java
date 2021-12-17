package controllers;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import ua_parser.Client;
import ua_parser.Parser;

/**
 * Servlet Filter implementation class Filtro
 */
@WebFilter("/Filtro")
public class Filtro implements Filter {

    /**
     * Default constructor. 
     */
    public Filtro() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        
        HttpServletRequest req = (HttpServletRequest) request;
        
        String ip = req.getRemoteAddr();
        String url = req.getRequestURL().toString();
        String userAgent = req.getHeader("user-agent");
        
        Parser uaParser = new Parser();
        Client c = uaParser.parse(userAgent);

        String browser = c.userAgent.family;
        String sisOp = c.os.family;
        
        
        Date fecha = new Date();
        GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fecha);
		XMLGregorianCalendar fechaCal = null;
        
		try {
			fechaCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
        port.agregarRegistro(ip, url, browser, sisOp, fechaCal);
        
        /*
        for (Enumeration<?> names = req.getHeaderNames(); names.hasMoreElements(); ) {
            String name = (String) names.nextElement();
            System.out.println(name);
        }
        */
        
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
