
package logica;

import java.util.Set;

import excepciones.PremioYaGanadoException;

import java.util.Iterator;
import java.util.Map;
import java.util.HashSet;


import java.util.Date;
import java.util.HashMap;

public class Socio extends Usuario {
	
	private Set<Cuponera> cuponeras;
	private Set<Inscripcion> inscripciones;
	private Set<Actividad> actividadesFavoritas; // nombre actividad,Actividad 
	
	public Socio(DtUsuario data) {
		super(data);
		this.cuponeras = new HashSet<Cuponera>();
		this.inscripciones = new HashSet<Inscripcion>();
		this.actividadesFavoritas = new HashSet<Actividad>();
	}
	
	/**
	 * Devuelve un DtUsuario con los datos basicos del
	 * socio.
	 */
	public DtUsuario getDatosUsuario() {
		

		Map<String, Integer> valoracionesClases = new HashMap<String, Integer>(); // nombreClase, valoracion
		for (Inscripcion ins : inscripciones) {
			valoracionesClases.put(ins.getNombreClase(), ins.getValoracion()); // meter par (nomClase, valoracion) en mapa
		}
		Set<String> nombreClases = valoracionesClases.keySet();
		
		// Cuponeras del socio
		Set<String> nombreCuponeras = new HashSet<String>();
		for (Cuponera cup : cuponeras) {
			nombreCuponeras.add(cup.getNombre());
		}
		
		Set<String> activsFavoritas = getFavoritas(); // actividades favoritas
		
		
		return new DtSocio(getNickname(), getNombre(), getApellido(), getEmail(), getFechaNacimiento(), getContrasenia(), getImagen(), getSeguidos(), getSeguidores(), nombreClases, nombreCuponeras, activsFavoritas, valoracionesClases);
	}
	
	public void editarDatos(DtUsuario data) {
		this.setNombre(data.getNombre());
		this.setApellido(data.getApellido());
		this.setFechaNacimiento(data.getFechaDeNacimiento());
	}
	
	public Set<String> getCuponerasDisponibles(String nomActividad) {
		Set<String>	res = new HashSet<String>();
		for (Cuponera cup : cuponeras) {
			if (cup.tieneClasesDisponibles(nomActividad) && cup.esVigente())
				res.add(cup.getNombre());
		}
		return res;	
	}
	
	public void registrarInscripcion(String nomCuponera, String nomAct, Date fechaReg, Clase clase) {
		Inscripcion ins = new Inscripcion(fechaReg, clase);
		inscripciones.add(ins);
		if (nomCuponera != null) {
			Iterator<Cuponera> iter = this.cuponeras.iterator(); 
			boolean encontrado = false;
			while (iter.hasNext() & !encontrado) {
				Cuponera elem = iter.next();
				if (elem.getNombre().equals(nomCuponera)) {
					encontrado = true;
					elem.gastarClase(nomAct);
					ins.setCuponera(elem);
				}
			}
		}
	}
	
	public boolean estaInscriptoAClase(String nomClase) { 
		Iterator<Inscripcion> iter = this.inscripciones.iterator();
		boolean encontrado = false;
		while (iter.hasNext() & !encontrado) {
			if (iter.next().getNombreClase().equals(nomClase)) 
				encontrado = true;
		}
		return encontrado;
	}
	
	public boolean tieneCuponera(String nombreCup) {
		Iterator<Cuponera> iter = this.cuponeras.iterator();
		boolean encontrado = false;
		while (iter.hasNext() & !encontrado) {
			if (iter.next().getNombre().equals(nombreCup)) 
				encontrado = true;
		}
		return encontrado;
	}
	
	public void comprarCuponera(Date fechaCompra, String nombreCup) {
		Cuponera cup = new Cuponera(fechaCompra, nombreCup);
		cuponeras.add(cup);
	}
	
	public Set<DtPremio> consultarPremios() {
		Set<DtPremio> res = new HashSet<DtPremio>();
		for (Inscripcion ins: inscripciones) {
			if (ins.tienePremio()) {
				res.add(ins.getDtPremio());
			}
		}
		return res;
	}
	
	public void ganarPremio(String nomClase) {
		Iterator<Inscripcion> iter = this.inscripciones.iterator();
		boolean encontrado = false;
		Inscripcion aux = null;
		while (iter.hasNext() & !encontrado) {
			aux = iter.next();
			if (aux.getNombreClase().equals(nomClase))
				encontrado = true;
		}
		try {
			aux.ganarPremio();
		} catch (PremioYaGanadoException e) {
			e.printStackTrace();
		}
	}
	
	public Set<String> getFavoritas(){
		Set<String> res = new HashSet<>();
		for (Actividad act : actividadesFavoritas) {
			res.add(act.getNombre());
		}
		return res;
	}
	
	public boolean cambiarEstadoFavorita(Actividad actividad){
		if (!actividadesFavoritas.contains(actividad)) {
			actividad.sumarFavoritas();
			actividadesFavoritas.add(actividad);
			return true;
		} else {
			actividad.restarFavoritas();
			actividadesFavoritas.remove(actividad);
			return false;
		}
	}
	
	/**
	 * Retorna la valoracion dada una clase (entero entre 1 y 5).
	 * En caso de no haber valoracion, devuelve 0.
	 * 
	 * @param nomClase nombre que identifica a la clase
	 * @return valoracion dada a la clase
	 */
	public int getValoracion(String nomClase) {
		int resu = 0;
		// Buscar la inscripcion a huevo
		for (Inscripcion insc : this.inscripciones) {
			if (insc.getNombreClase().equals(nomClase)) {
				resu = insc.getValoracion();
				break; // ya se encontro inscripcion
			}
		}
		return resu;
	}
	
	public void valorarClase(String nomClase, int valoracion) {
		for (Inscripcion ins : inscripciones) {
			if (ins.getNombreClase().equals(nomClase)) {
				ins.setValoracion(valoracion);
				break;
			}
		}
	}
}
