package logica;

import java.util.HashMap;
import java.util.Map;

// Singleton
public class ManejadorInstituciones {
	
	// Singleton
	private static ManejadorInstituciones instancia = null;
	
	private Map<String, Institucion> colInstituciones; // nombre institucion, Institucion
	
	// Singleton
	private ManejadorInstituciones() {
		colInstituciones = new HashMap<>();
	}
	
	// Singleton
	public static ManejadorInstituciones getInstance() {
		if (instancia == null) {
			instancia = new ManejadorInstituciones();
		}
		return instancia;
	}
	
	
	// operaciones sobre coleccion
	
	/**
	 * Agrega una nueva institucion a la coleccion del manejador 
	 * 
	 * @param inst instancia de institucion
	 */
	public void add(Institucion inst) {
		String llave = inst.getNombre();
		colInstituciones.putIfAbsent(llave, inst);
	}
	
	/**
	 * @param nomInst nombre de la institucion
	 * @return true sii institucion existe en coleccion del manejador
	 */
	public boolean exists(String nomInst) {
		return colInstituciones.containsKey(nomInst);
	}
	
	
	/**
	 * Devuelve la coleccion manejada por ManejadorInstituciones
	 * 
	 * @return coleccion de instituciones del manejador
	 */
	public Map<String, Institucion> getInstituciones() {
		return colInstituciones;
	} 
	
	
	/**
	 * Devuelve una institucion en particular del manejador.
	 * Si no existe tal institucion, devuelve null.
	 * 
	 * @param nomInst nombre de la institucion
	 * @return institucion de nombre especificado
	 */
	public Institucion find(String nomInst) {
		return colInstituciones.get(nomInst);
	}
}
