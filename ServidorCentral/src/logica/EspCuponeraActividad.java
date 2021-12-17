package logica;

import java.util.AbstractMap;
import java.util.Set;

public class EspCuponeraActividad {
	
	private int cantidadClases;
	private Actividad actividad;
	
	public EspCuponeraActividad(Actividad actividad, int cantidadClases) {
		setActividad(actividad);
		this.cantidadClases = cantidadClases;
	}
	
	public String getNombreActividad() {
		return actividad.getNombre();
	}
	
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	
	public AbstractMap.SimpleImmutableEntry<String, Integer> getDatoEspCuponeraActividad() {
		return new AbstractMap.SimpleImmutableEntry<>(actividad.getNombre(), cantidadClases);
	}
	
	public Set<String> getCategorias() {
		return actividad.getCategorias();
	}
	
	public int getCantidadClases() {
		return cantidadClases;
	}
	
	public float getCosto() {
		return actividad.getCosto() * cantidadClases;
	}
	
}
