package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Institucion {
	
	private String nombre;
	private String descripcion;
	private String url;
	
	private Set<Profesor> colProfesores;
	private Map<String, Actividad> colActividades;
	
	/**
	 * Crea una institucion
	 * 
	 *@param name Nombre de la institucion
	 *@param descr Descripcion de la institucion
	 *@param link URL de la institucion
	 */
	public Institucion(String name, String descr, String link) {
		nombre = name;
		descripcion = descr;
		url = link;
		
		colProfesores = new HashSet<>();
		colActividades = new HashMap<>();
	}
	
	/**
	 * @return nombre de la institucion
	 */
	public String getNombre() {
		return nombre;
	}
	
	
	/**
	 * @return descripcion de la institucion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return url de la institucion
	 */
	public String getUrl() {
		return url;
	}
	
	
	
	/**
	 * Agrega un nuevo profesor a la institucion.
	 * Si el profesor ya figuraba en la institucion,
	 * no hace nada.
	 * 
	 *@param prof Profesor que se va a asociar a la institucion
	 */
	public void agregarProfesor(Profesor prof) {
		colProfesores.add(prof);
	}
	
	/**
	 * Devuelve true si un profesor trabaja en la institucion.
	 * False de lo contrario
	 * 
	 * @param prof Profesor que se desea consultar
	 * @return true sii profesor trabaja en la institucion 
	 */
	/*
	public boolean existeProfesor(Profesor prof) {
		return colProfesores.contains(prof);
	}
	*/
	
	/**
	 * Devuelve true si un profesor trabaja en la institucion.
	 * False de lo contrario
	 * 
	 * @param prof nombre del profesor que se desea consultar
	 * @return true sii profesor trabaja en la institucion  
	 */
	public boolean existeProfesor(String nomProfesor) {
		
		boolean encontrado = false;
		Iterator<Profesor> iter = colProfesores.iterator();
		while (!encontrado && iter.hasNext()) {
			encontrado = nomProfesor.equals(iter.next().getNickname());
		}
		return encontrado;
	}
	
	/**
	 * Devuelve el profesor segun el nombre. Si no existe profe,
	 * se devuelve null.
	 * 
	 * @param nomProfesor nombre del profesor que se desea obtener
	 * @return referencia al profesor  
	 */
	public Profesor getProfesor(String nomProfesor) {
		
		Iterator<Profesor> iter = colProfesores.iterator();
		Profesor itProf = null; // var aux para no avanzar iterador
		
		boolean encontrado = false;
		while (!encontrado && iter.hasNext()) { // no se sale de rango no?
			// revisar si falta castear (String)it.next()
			itProf = (Profesor) iter.next();
			encontrado = nomProfesor.equals(itProf.getNickname()); // primer nombre del profe?
		}
		if (!encontrado)
			itProf = null;
		
		return itProf;
	}
	
	/**
	 *Retorna true si y solo si la institucion ofrece
	 *la actividad especificada.
	 *
	 *@param nomActiv nombre de la actividad
	 *@return true sii institucion ofrece la actividad
	 */
	public boolean tieneActiv(String nomActiv) {
		return colActividades.containsKey(nomActiv);
	}
	
	/**
	 * Agrega una actividad a las ofrecidas por el instituto.
	 * Si ya era ofrecida por el instituto, no hace nada.
	 * 
	 * @param activ Actividad a agregar
	 */
	public void agregarActiv(Actividad activ) {
		colActividades.putIfAbsent(activ.getNombre(), activ);
	}
	
	/**
	 * Devuelve la actividad de nombre especificado.
	 * Si no pertenece a la institucion, se devuelve
	 * null.
	 * 
	 * @param nomActiv nombre de la actividad
	 * @return referencia a actividad (nula si no existe)
	 */
	public Actividad getActividad(String nomActiv) {
		return tieneActiv(nomActiv)? colActividades.get(nomActiv) : null;
	}
	
	/**
	 * Devuelve true si y solo si existe la clase especificada
	 * en alguna actividad de la institucion.
	 * 
	 * @param nomClase nombre de la clase
	 * @return true sii existe una clase en esa institucion
	 */
	public boolean tieneClase(String nomClase) {
		
		boolean encontrada = false;
		for (Map.Entry<String, Actividad> act : colActividades.entrySet()) {
			encontrada = act.getValue().existeClase(nomClase); 
			if (encontrada)
				break; // salir del foreach
		}
		return encontrada;
	}
	
	
	/**
	 * Devuelve true si y solo si existe la clase especificada
	 * en alguna actividad de la institucion.
	 * 
	 * @param nomClase nombre de la clase
	 * @return true sii existe una clase en esa institucion
	 */
	// operacion fachada
	public boolean existeClase(String nomClase) {
		return tieneClase(nomClase);
	}
	
	
	/**
	 * @param nomActividad nombre de la actividad asociada a la clase
	 * @param nomClase nombre de la clase
	 * @return referencia (objeto) Clase. Null en caso contrario.
	 */
	public Clase getClase(String nomActividad, String nomClase ) {
		return getActividad(nomActividad).getClase(nomClase);
//		Actividad act = getActividad(nomActividad);
//		return act.getClase(nomClase);
	}
	
	
	/**
	 * Devuelve el nombre de la actividad de la clase
	 * especificada. Si no se halla la clase dentro de la
	 * institucion, se devuelve null.
	 * 
	 * @param nomClase nombre que identifica la clase
	 * @return nombre de la actividad de la clase
	 */
	public String getActividadDeClase(String nomClase) {
		String resu = null;
		for (Map.Entry<String, Actividad> act : colActividades.entrySet()) {
			if (act.getValue().existeClase(nomClase)) {
				resu = act.getValue().getNombre();
//				resu = act.getKey(); // alternativa acoplada a eleccion de key
				break; // salir del foreach
			}
		}
		return resu;
	}
	
	
	/**
	 *Devuelve datos basicos de la actividad especificada.
	 *En caso de no encontrar actividad dentro de institucion,
	 *se devuelve null.
	 *
	 *@param nomActiv nombre de la actividad
	 *@return datos basicos de la actividad
	 */
	public DtActividad getDatosActividad(String nomActiv) {
		return tieneActiv(nomActiv)? getActividad(nomActiv).getDatos() : null;
	}
	
	
	/**
	 * Devuelve nombres de los profesores que trabajan
	 * en la institucion. En caso de no haber ninguno,
	 * devuelve conjunto vacio.
	 * 
	 * @return Set de strings, nombres de los profesores
	 */
	public Set<String> getNombresProfesores() {
		
		Set<String> resu = new HashSet<String>();
		Iterator<Profesor> iter = colProfesores.iterator();
		while (iter.hasNext()) { // no se sale de rango no?
			// revisar si falta castear (String)it.next()
			resu.add(iter.next().getNickname()); // primer nombre del profe?
		}
		return resu;
	}
	
	
	/**
	 * Devuelve nombres de los profesores que trabajan
	 * en la institucion. En caso de no haber ninguno,
	 * devuelve conjunto vacio.
	 * 
	 * @return Set de strings, nombres de los profesores
	 */
	// operacion fachada
	public Set<String> getProfesores() {
		return getNombresProfesores();
	}
	
	/**
	 * Se crea una nueva clase asociada a la actividad
	 * y al profesor especificados.
	 * 
	 * PRECOND: La actividad y profesor estan asociados a la 
	 * institucion 
	 * PRECOND: Los datos basicos son unicos a la clase a registrar
	 * 
	 * @param datosClase datos basicos de la clase a registrar
	 * @param nomActividad nombre de la actividad a la que pertenece
	 * @param nomProfesor nombre del profesor
	 */
	public void crearClase(DtClase datosClase, String nomActividad, String nomProfesor) {
		
		Actividad act = getActividad(nomActividad);
		boolean trabaja = existeProfesor(nomProfesor);
		
		// chequeo precond.
		if (trabaja && !existeClase(datosClase.getNombre())) { // forall actividades
			
			act.crearClase(datosClase);
			
			Clase clase = act.getClase(datosClase.getNombre());
			Profesor prof = getProfesor(nomProfesor);
			prof.agregarClase(clase);
		}
	}
	
	
	/**
	 * @param nomActividad nombre de la actividad
	 * @return nombres de todas las clases de la actividad
	 */
	public Set<String> getClasesDeActividad(String nomActividad) {
		Actividad act = getActividad(nomActividad);
		return act.getNombresClases();
	}
	
	/**
	 * PRECOND: la clase dada pertenece a la actividad
	 * 
	 * @param nomActividad nombre de la actividad
	 * @param nomClase nombre de la clase
	 * @return datos basicos de la clase
	 */
	public DtClase getDatosDeClase(String nomActividad, String nomClase) {
		return getActividad(nomActividad).getClase(nomClase).getDatos();
//		Actividad act = getActividad(nomActividad);
//		Clase cl = act.getClase(nomClase);
//		return cl.getDatos();
	}
	
	/**
	 * @return datos basicos de las clases vigentes en la actividad seleccionada
	 */
	public Set<DtClase> getClasesVigentes(String activ) {
		Actividad act = colActividades.get(activ);
		return act.getClasesVigentes();
	}
	
	/**
	 * @return nombres de todas las actividades en la institucion
	 */
	public Set<String> getNombreActividades() {
		Set<String> resu = new HashSet<String>();
		for (Map.Entry<String, Actividad> entry : colActividades.entrySet()) {
			resu.add(entry.getValue().getNombre());
		}
//		resu.addAll(colActividades.keySet());	// alternativa acoplada a eleccion de key
		return resu;
	}
	
	public Set<String> getNombreActividadesAceptadas() {
		Set<String> resu = new HashSet<String>();
		for (Map.Entry<String, Actividad> entry : colActividades.entrySet()) {
			if (entry.getValue().getEstado().equals(EstadoActividad.Aceptada))
				resu.add(entry.getValue().getNombre());
		}
		return resu;
	}
	
	public Set<String> getNombreActividadesIngresadas() {
		Set<String> resu = new HashSet<String>();
		for (Map.Entry<String, Actividad> entry : colActividades.entrySet()) {
			if (entry.getValue().getEstado().equals(EstadoActividad.Ingresada))
				resu.add(entry.getValue().getNombre());
		}
		return resu;
	}
	
}
