package logica;

import java.util.HashSet;
import java.util.Set;

public class Categoria {
	
	private String nombre;
	private Set<Actividad> actividades;
	
	public Categoria(String nombre) {
		this.nombre = nombre;
		actividades = new HashSet<>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void agregarActiv(Actividad act) {
		actividades.add(act);
	}
	
	public Set<String> getNombreActividades() {
		Set<String> resu = new HashSet<>();
		for (Actividad act : actividades) {
			resu.add(act.getNombre());
		}
		return resu;
	}
	
	public Set<String> getNombreActividadesAceptadas() {
		Set<String> resu = new HashSet<>();
		for (Actividad act : actividades) {
			if (act.getEstado().equals(EstadoActividad.Aceptada))
				resu.add(act.getNombre());
		}
		return resu;
	}
	
}
