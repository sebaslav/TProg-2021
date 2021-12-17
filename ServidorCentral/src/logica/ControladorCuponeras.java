package logica;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import excepciones.CuponeraRepetidaException;

public class ControladorCuponeras implements IControladorCuponeras{

	
	public ControladorCuponeras() {
	}
	
	public Set<String> getEspCuponeras() {
		ManejadorCuponeras mcup = ManejadorCuponeras.getInstance();
		return mcup.getNombresEspCuponeras();
	}
	
	public void agregarActividadACuponera(String inst, String activ, String cup, int cantClases) {
		ManejadorCuponeras mcup = ManejadorCuponeras.getInstance();
		EspCuponera espc = mcup.find(cup);
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Institucion ins = mins.find(inst);
		Actividad act = ins.getActividad(activ);
		espc.agregarActividad(act, cantClases);
	}
	
	public DtCuponera getDatosCuponera(String cup) {
		ManejadorCuponeras mcup = ManejadorCuponeras.getInstance();
		EspCuponera espc = mcup.find(cup);
		return espc.getDatosCuponera();
	}
	
	public void registrarCuponera(String nombre, String descripcion, Date inicio, Date fin, float descuento, Date fechaRegistro, byte[] imagen) throws CuponeraRepetidaException {
		ManejadorCuponeras mcup = ManejadorCuponeras.getInstance();
		if (mcup.exists(nombre))
			throw new CuponeraRepetidaException("La cuponera " + nombre + " ya esta registrada");
		EspCuponera esp = new EspCuponera(nombre, descripcion, inicio, fin, descuento, fechaRegistro, imagen);
		mcup.add(esp);
	}
	
	public Set<String> getActividadesFaltantes(String inst, String cup) {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Institucion ins = mins.find(inst);
		Set<String> nomActividades = ins.getNombreActividadesAceptadas();
		ManejadorCuponeras mcup = ManejadorCuponeras.getInstance();
		EspCuponera espc = mcup.find(cup);
		Set<String> nomActsCuponera = espc.getNombresActividades();
		nomActividades.removeAll(nomActsCuponera);
		return nomActividades;
	}
	
	public Set<String> getEspCuponerasNoCompradas() {
		ManejadorCuponeras mcup = ManejadorCuponeras.getInstance();
		return mcup.getNombresEspCuponerasNoCompradas();
	}
	
	public Set<String> getEspCuponerasVigentes() {
		Set<String> res = new HashSet<>();
		ManejadorCuponeras mcup = ManejadorCuponeras.getInstance();
		Map<String, EspCuponera> cups = mcup.getEspCuponeras();
		for (EspCuponera cup : cups.values()) {
			if (cup.esVigente())
				res.add(cup.getNombre());
		}
		return res;
	}
	
	public Set<DtCuponera> buscarCuponera(String texto) {
		Set<DtCuponera> res = new HashSet<>();
		ManejadorCuponeras mcup = ManejadorCuponeras.getInstance();
		Map<String, EspCuponera> cups = mcup.getEspCuponeras();
		for (EspCuponera espc : cups.values()) {
			if (espc.contieneTexto(texto)) {
				res.add(espc.getDatosCuponera());
			}
		}
		return res;
	}
	
}
