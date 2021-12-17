package logica;

public class CuponeraActividad {

	private int cantidadClasesRestantes;
	private EspCuponeraActividad espCuponeraActividad;
	
	public CuponeraActividad(EspCuponeraActividad espCuponeraActividad) {
		cantidadClasesRestantes = espCuponeraActividad.getCantidadClases();
		this.espCuponeraActividad = espCuponeraActividad;
	}
	
	public String getNombreActividad() {
		return espCuponeraActividad.getNombreActividad();
	}
	
	public boolean tieneClasesDisponibles() {
		return cantidadClasesRestantes > 0;
	}
	
	public void gastarClase() {
		cantidadClasesRestantes--;
	}
}
