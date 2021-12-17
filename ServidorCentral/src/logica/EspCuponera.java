package logica;

import java.text.Normalizer;
import java.util.AbstractMap;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class EspCuponera {
	
	private String nombre;
	private String descripcion;
	private Date validoDesde;
	private Date validoHasta;
	private float descuento;
	private Date fechaRegistro;
	private Set<EspCuponeraActividad> espCuponeraActividades;
	private boolean fueComprada;

	public EspCuponera(String nombre, String descripcion, Date inicio, Date fin, float descuento, Date fechaRegistro, byte[] imagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.validoDesde = inicio;
		this.validoHasta = fin;
		this.descuento = descuento;
		this.fechaRegistro = fechaRegistro;
		(new ManejadorImagenes()).ingresarImagen("cuponera_" + nombre, imagen);
		fueComprada = false;
		espCuponeraActividades = new HashSet<EspCuponeraActividad>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public boolean tieneActividad(String activ) {
		Iterator<EspCuponeraActividad> itr = espCuponeraActividades.iterator();
		boolean tieneAct = false;
		while (itr.hasNext() && !tieneAct) {
			if (activ.equals(itr.next().getNombreActividad()))
				tieneAct = true;
		}
		return tieneAct;
	}
	
	public Set<String> getNombresActividades() {
		Set<String> res = new HashSet<String>();
		for (EspCuponeraActividad eca : espCuponeraActividades)
			res.add(eca.getNombreActividad());
		return res;
	}
	
	public void agregarActividad(Actividad act, int cantClases) {
		EspCuponeraActividad eca = new EspCuponeraActividad(act, cantClases);
		espCuponeraActividades.add(eca);
	}
	
	public DtCuponera getDatosCuponera() {
		Map<String, Integer> actividades = new HashMap<>();
		for (EspCuponeraActividad eca : espCuponeraActividades) {
			AbstractMap.SimpleImmutableEntry<String, Integer> dato = eca.getDatoEspCuponeraActividad();
			actividades.put(dato.getKey(), dato.getValue());
		}
		Set<String> categorias = getCategorias();
		float costo = getCosto();
		byte[] imagen = (new ManejadorImagenes()).obtenerImagen("cuponera_" + nombre);
		return new DtCuponera(nombre, descripcion, validoDesde, validoHasta, descuento, fechaRegistro, actividades, categorias, costo, imagen);
	}
	
	public boolean getFueComprada() {
		return fueComprada;
	}
	
	public boolean esVigente() {
		Date fechaActual = new Date();
		return validoHasta.after(fechaActual) && fechaActual.after(validoDesde);
	}
	
	public void setFueComprada(boolean fueComprada) {
		this.fueComprada = fueComprada;
	}
	
	public Set<EspCuponeraActividad> getEspCuponerasActividades() {
		return espCuponeraActividades;
	}
	
	public float getCosto() {
		float suma = 0;
		for (EspCuponeraActividad eca : espCuponeraActividades)
			suma += eca.getCosto();
		return suma * descuento;
	}
	
	public Set<String> getCategorias() {
		Set<String> res = new HashSet<String>();
		for (EspCuponeraActividad eca : espCuponeraActividades) {
			Set<String> catsEca = eca.getCategorias();
			for (String cat : catsEca) {
				res.add(cat);
			}
		}
		return res;
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
	
}
