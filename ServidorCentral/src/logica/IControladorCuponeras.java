package logica;

import java.util.Date;
import java.util.Set;
import excepciones.CuponeraRepetidaException;

public interface IControladorCuponeras {
	
	public abstract Set<String> getEspCuponeras();
	
	public abstract void agregarActividadACuponera(String nomInstituto, String nomActividad, String nomCuponera, int cantClases);
	
	public abstract DtCuponera getDatosCuponera(String nomCuponera);
	
	public abstract void registrarCuponera(String nombre, String descripcion, 
			Date inicio, Date fin, float descuento, Date fechaRegistro, byte[] imagen) throws CuponeraRepetidaException;
	
	public abstract Set<String> getActividadesFaltantes(String nomInstituto, String nomCuponera);
	
	public abstract Set<String> getEspCuponerasNoCompradas();
	
	public abstract Set<String> getEspCuponerasVigentes();
	
	public abstract Set<DtCuponera> buscarCuponera(String texto);
	
}
