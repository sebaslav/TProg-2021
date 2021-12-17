package logica;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;


public class Profesor extends Usuario {
	private String descripcion;
	private String biografia;
	private String link;
	private Set<Clase> clases;
	private Set<Actividad> actividades;
	
	public Profesor(DtUsuario data) {
		super(data);
		DtProfesor dtp = (DtProfesor) data;
		this.descripcion = dtp.getDescripcion();
		this.biografia = dtp.getBiografia();
		this.link = dtp.getUrl();
		this.clases = new HashSet<Clase>();
		this.actividades = new HashSet<Actividad>();
		
	}
	
	public DtUsuario getDatosUsuario() {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Iterator<Institucion> iterInstitucion = mins.getInstituciones().values().iterator();
		boolean encontrado = false;
		String institucion = null;
		Map<String, String> nombresClasesActividad = new HashMap<>();
		while (iterInstitucion.hasNext() & !encontrado) {
			Institucion its = iterInstitucion.next();
			if (its.existeProfesor(getNickname())) {
				for (Clase clase : clases) {
					String nomC = clase.getNombre();
					String nomA = its.getActividadDeClase(nomC);
					nombresClasesActividad.put(nomC, nomA);
				}
				institucion = its.getNombre();
				encontrado = true;
			}
		}
		Map<String, EstadoActividad> actsEstados = new HashMap<>();
		for (Actividad act : actividades) {
			String nomAct = act.getNombre();
			EstadoActividad est = act.getEstado();
			actsEstados.put(nomAct, est);
		}
		
		int[] contadorValoraciones = new int[]{ 0, 0, 0, 0, 0, 0 };
		for (Clase cla : clases) {
			int[] valsClase = cla.getValoraciones().getCantValores();
			for (int i=0; i<6; i++) {
				contadorValoraciones[i] += valsClase[i];
			}
		}
		DtValoracion valoraciones = new DtValoracion(contadorValoraciones);
		
		return new DtProfesor(getNickname(), getNombre(), getApellido(), getEmail(), getFechaNacimiento(), getContrasenia(), getImagen(), getSeguidos(), getSeguidores(), descripcion, institucion, link, biografia, nombresClasesActividad, actsEstados, valoraciones);
	}		
		
	public void editarDatos(DtUsuario data) {
		this.setNombre(data.getNombre());
		this.setApellido(data.getApellido());
		this.setFechaNacimiento(data.getFechaDeNacimiento());
		DtProfesor dtp = (DtProfesor) data;
		this.descripcion = dtp.getDescripcion();
		this.biografia = dtp.getBiografia();
		this.link = dtp.getUrl();
	}
	
	public void agregarClase(Clase data) {
		this.clases.add(data);
	} 
	
	public void agregarActiv(Actividad act) {
		this.actividades.add(act);
	}
	
}
