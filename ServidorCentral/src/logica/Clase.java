package logica;

import java.util.Date;
import java.util.Map;
import java.util.Set;

//Ligado a Opción 2
//import java.time.format.DateTimeFormatter;  
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;

public class Clase {
	private String nombre;
	private Date fechaHoraInicio;
	private int maxSocios;
	private int minSocios;
	private int cantInscriptos;
	private Date fechaRegistro;
	private String url;
	private String videoURL;
	private String descPremio;
	private int cantPremios;
	private Date fechaSorteo;
	private boolean finalizada;
	
	public Clase(DtClase datos){
		this.nombre = datos.getNombre();
		this.fechaHoraInicio = datos.getFechaHora();
		this.maxSocios = datos.getMaxSocios(); 
		this.minSocios = datos.getMinSocios();
		this.cantInscriptos = 0;
		this.fechaRegistro = datos.getFechaDeRegistro();
		this.url = datos.getUrl();
		(new ManejadorImagenes()).ingresarImagen("clase_" + datos.getNombre(), datos.getImagen());
		this.videoURL = datos.getVideoURL();
		if (datos.getDescPremio().equals("")) {
			this.descPremio = "No hay";
		} else {
			this.descPremio = datos.getDescPremio();
		}
		this.cantPremios = datos.getCantPremios();
		fechaSorteo = null;
		finalizada = false;
	}

	public String getNombre() {
		return nombre;
	}
	
	public DtClase getDatos(){
		IControladorUsuarios icu = Fabrica.getInstance().getControladorUsuario();
		Set<String> usrs = icu.getListaUsuarios();
		DtUsuario dtUser;
		DtProfesor dtProf;
		String profesor = null;
		String actividad = null;
		for (String usr : usrs) {
			dtUser = icu.getDatosUsuario(usr);
			if (dtUser instanceof DtProfesor) {
				dtProf = (DtProfesor) dtUser;
				boolean encontre = false;
				Map<String, String> clasesActs = dtProf.getClases();
				for (Map.Entry<String, String> entry : clasesActs.entrySet()){
					if (entry.getKey().equals(nombre)) {
						profesor = dtProf.getNickname();
						actividad = entry.getValue();
						encontre = true;
						break;
					}
				}
				if (encontre) {
					break;
				}
			}
		}
		
		DtValoracion dtVal = getValoraciones();
		byte[] imagen = (new ManejadorImagenes()).obtenerImagen("clase_" + nombre);
		DtClase datos = new DtClase(nombre, fechaHoraInicio, maxSocios, minSocios, fechaRegistro, url, imagen, actividad, profesor, videoURL, descPremio, cantPremios, fechaSorteo, cantInscriptos, dtVal, finalizada);
		return datos;
	}
	
	public boolean esVigente() {
		
//Opción 1
//	    LocalDateTime now = LocalDateTime.now();  
//	    ZonedDateTime zdt = now.atZone(ZoneId.of("America/Buenos_Aires"));
//	    long millis = zdt.toInstant().toEpochMilli();
//		Date date = new Date(millis);
		
//Opción 2
		Date date = new Date();
		return this.fechaHoraInicio.after(date);
		
	}
	
	public boolean estaLlena(){
		return cantInscriptos == maxSocios;
	}
	
	public void inscribirSocio() {
		this.cantInscriptos++;
	}
	
	public void realizarSorteo(Date fechaSorteo) {
		this.fechaSorteo = fechaSorteo;
	}
	
	public DtValoracion getValoraciones() {
		// Contar valoraciones recibidas y ponerlas en un DtValoracion
		IControladorUsuarios icu = Fabrica.getInstance().getControladorUsuario();
		IControladorInstituciones ici = Fabrica.getInstance().getControladorInstituciones();
		Set<String> sociosInscriptos = ici.getInscriptosAClase(this.getNombre());
		
		int[] contadorValoraciones = new int[]{ 0, 0, 0, 0, 0, 0 }; // celdas 0, 1...5 inicializadas en cero
		for (String inscripto : sociosInscriptos) {
			DtSocio dtInscripto = (DtSocio) icu.getDatosUsuario(inscripto);
			Map<String, Integer> valorsInscrpto = dtInscripto.getValoracionesClases();
			
			int valorDeClase = valorsInscrpto.get(this.getNombre());
			if (0 <= valorDeClase && valorDeClase <= 5) {
				contadorValoraciones[valorDeClase]++; // aumentar en 1 contador
			}
		}
		// Creamos el DtValoracion
		return new DtValoracion(contadorValoraciones);
	}

	/**
	 * @return cantidad de premios de la clase
	 */
	public int getCantPremios() {
		return cantPremios;
	}
	
	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

}
