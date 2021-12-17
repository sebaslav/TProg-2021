package logica;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Cuponera {
	
	private Date fechaCompra;
	private EspCuponera espCuponera;
	private Set<CuponeraActividad> cuponeraActividades;

	
	public Cuponera(Date fechaCompra, String nomEspCuponera) {
		this.fechaCompra = fechaCompra;
		ManejadorCuponeras mcup = ManejadorCuponeras.getInstance();
		espCuponera = mcup.find(nomEspCuponera);
		espCuponera.setFueComprada(true);
		cuponeraActividades = new HashSet<CuponeraActividad>();
		Set<EspCuponeraActividad> ecas = espCuponera.getEspCuponerasActividades();
		for (EspCuponeraActividad eca : ecas) {
			CuponeraActividad cact = new CuponeraActividad(eca);
			cuponeraActividades.add(cact);
		}
	}
	
	private CuponeraActividad findCuponeraActividad(String nomActividad){
		Iterator<CuponeraActividad> itr = cuponeraActividades.iterator();
		while (itr.hasNext()) { 
			CuponeraActividad current = itr.next(); 
			String nombAct = current.getNombreActividad();
			if (nombAct.equals(nomActividad)) {
				return current;
			}
		}
		return null;
	}
	
	public boolean tieneClasesDisponibles(String nomActividad) {
		CuponeraActividad cact = findCuponeraActividad(nomActividad);
		if (cact != null)
			return cact.tieneClasesDisponibles();
		else
			return false;
	}
	
	public String getNombre() {
		return espCuponera.getNombre();
	}
	
	public void gastarClase(String nomActividad) {
		findCuponeraActividad(nomActividad).gastarClase();
	}
	
	public boolean esVigente() {
		return espCuponera.esVigente();
	}
	
	public Date getFechaCompra() {
		return fechaCompra;
	}
}
