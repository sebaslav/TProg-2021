package logica;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern; // sacar tildes en busqueda
import java.text.Normalizer; // sacar tildes en busqueda


public class Actividad {
	
	private String nombre;
	private String descripcion;
	private int duracion; // cantidad de minutos
	private float costo;
	private Date fechaRegistro;
	private EstadoActividad estado;
	private int cantFavoritas;
	
	private Map<String, Clase> colClases;
	private Set<Categoria> categorias;
	

	/**
	 * Crea una nueva instancia de actividad
	 * 
	 * @param name nombre de la actividad
	 * @param descr descripcion de la actividad
	 * @param dura duracion en minutos de una sesión de la actividad
	 * @param cost costo de la actividad
	 * @param fechita fecha en la que se da de alta
	 */
	public Actividad(String name, Set<Categoria> cats, String descr, int dura, float cost, Date fechita, byte[] imagen) {
		nombre = name;
		descripcion = descr;
		duracion = dura;
		costo = cost;
		fechaRegistro = fechita;
		(new ManejadorImagenes()).ingresarImagen("actividad_" + name, imagen);
		estado = EstadoActividad.Ingresada;
		cantFavoritas = 0;
		
		categorias = new HashSet<>();
		categorias.addAll(cats);
		colClases = new HashMap<>();
		
		for (Categoria c : cats) {
			c.agregarActiv(this);
		}
	}
	
	/**
	 * @return nombre de la actividad
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @return descripcion de la actividad
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * @return duracion de la actividad
	 */
	public int getDuracion() {
		return duracion;
	}
	
	/**
	 * @return costo de la actividad
	 */
	public float getCosto() {
		return costo;
	}
	
	/**
	 * @return fechaRegistro de la actividad
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	
	/**
	 * Retorna true si y solo existe una clase asociada
	 * a la actividad con el nombre especificado.
	 * 
	 * @param nomClase nombre de la clase
	 * @return true sii existe clase con ese nombre
	 */
	public boolean existeClase(String nomClase) {
		return colClases.containsKey(nomClase);
	}
	
	/**
	 * Devuelve la clase especificada. En caso
	 * de no existir la clase, se devuelve null.
	 * 
	 * 
	 * @param nomClase nombre de la clase
	 * @return referencia a clase de nombre 'nomClase'
	 */
	public Clase getClase(String nomClase) {
		return existeClase(nomClase)? colClases.get(nomClase) : null;
	}
	
	/**
	 * @return DtActividad con los datos de la actividad
	 */
	public DtActividad getDatos() {
		Set<String> clases = getNombresClases();
		Set<String> cats = getCategorias();
		Set<String> cuponeras = new HashSet<String>();
		ManejadorCuponeras mcup = ManejadorCuponeras.getInstance();
		Map<String, EspCuponera> esps = mcup.getEspCuponeras();
		for (Map.Entry<String, EspCuponera> esp : esps.entrySet()) {
			if (esp.getValue().tieneActividad(nombre))
				cuponeras.add(esp.getKey());
		}
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Map<String, Institucion> ins = mins.getInstituciones();
		String nomInst = null;
		for (Institucion its : ins.values()) {
			if (its.tieneActiv(nombre)) {
				nomInst = its.getNombre();
				break;
			}
		}
		byte[] imagen = (new ManejadorImagenes()).obtenerImagen("actividad_" + nombre);
		DtActividad resu = new DtActividad(nombre, descripcion, duracion, costo, fechaRegistro, clases, cuponeras, estado, cats, imagen, nomInst, cantFavoritas);
		return resu;
	}
	
	/**
	 * Instancia una nueva clase y la asocia a la
	 * actividad.
	 * 
	 * @param dt datos de la clase a instanciar
	 */
	public void crearClase(DtClase dtclase) {
		Clase clase = new Clase(dtclase);
		colClases.put(clase.getNombre(), clase);
	}
	
	
	/**
	 * Devuelve conjunto con los nombres de las
	 * clases de la actividad.
	 * 
	 * @return Set de Strings con nombres de las clases
	 */
	public Set<String> getNombresClases() {
		Set<String> resu = new HashSet<>();
		for (Map.Entry<String, Clase> cl : colClases.entrySet()) {
			resu.add(cl.getKey());
		}
		return resu;
	}
	
	/**
	 * Devuelve datos básicos de la clase especificada. Si
	 * la clase no existe, se devuelve null.
	 * 
	 * @param nomClase nombre de la clase
	 * @return DtClase con datos basicos de la clase
	 */
	public DtClase getDatosDeClase(String nomClase) {
		return existeClase(nomClase)? getClase(nomClase).getDatos() : null;
	}
	
	/**
	 * Devuelve los datos básicos de cada clase
	 * vigente ofrecida por la institucion.
	 * En caso de no haberlas, se devuelve conjunto
	 * vacio.
	 * 
	 *@return datos basicos de clases vigentes
	 */
	// operacion fachada
	public Set<DtClase> getClasesVigentes() {
		Set<DtClase> resu = new HashSet<DtClase>();
		for (Map.Entry<String, Clase> cl : colClases.entrySet()) {
			if (cl.getValue().esVigente())
				resu.add(cl.getValue().getDatos());
		}
		return resu;
	}
	
	public EstadoActividad getEstado() {
		return estado;
	}
	
	public Set<String> getCategorias() {
		Set<String> resu = new HashSet<>();
		for (Categoria cat : categorias) {
			resu.add(cat.getNombre());
		}
		return resu;
	}
	
	public void aceptar(boolean acepta) {
		if (acepta)
			estado = EstadoActividad.Aceptada;
		else
			estado = EstadoActividad.Rechazada;
	}
	
	public void finalizar() {
		estado = EstadoActividad.Finalizada;
		for (Clase clase : colClases.values()) {
			clase.setFinalizada(true);
		}
	}
	
	/**
	 * Le saca tildes a un texto
	 * 
	 * @param texto Texto en UTF-8 al que hay que sacarle tildes
	 * @return El texto original sin tildes
	 */
	private String sacarTildes(String texto) {
		
		// PASO 1: Separar tildes de letras
		// por ej. á --> a´
		String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
		
		// PASO 2: Nuevo patron que identifique los tildes (los diacritical marks)
		Pattern patron = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		
		// PASO 3: Reemplazar los tildes ya separados con string nulo.
		// por ej. a´ --> a
		return patron.matcher(textoNormalizado).replaceAll("");
	}
	
	/**
	 * @param texto Texto a buscar en el nombre y descripcion de la actividad.
	 * @return True si y solo si la tira de texto se encuentra en el nombre o en la descripcion de la actividad.
	 */
	public boolean contieneTexto(String texto) {
		
		// Le sacamos tildes a los textos y lo pasamos todo a minuscula.
		String textoFacil = sacarTildes(texto).toLowerCase();
		String nombreFacil = sacarTildes(nombre).toLowerCase();
		String descripcionFacil = sacarTildes(descripcion).toLowerCase();
		
		// Revisamos si contienen el texto
		return nombreFacil.contains(textoFacil) || descripcionFacil.contains(textoFacil);
	}
	
	public void sumarFavoritas() {
		cantFavoritas++;
	}
	
	public void restarFavoritas() {
		cantFavoritas--;
	}
}
