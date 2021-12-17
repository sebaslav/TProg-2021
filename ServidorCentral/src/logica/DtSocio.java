package logica;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtSocio extends DtUsuario {
	
	private Set<String> clases; // redundante con valoracionesClases
	private Set<String> cuponeras;
	private Set<String> favoritas;
	private Map<String, Integer> valoracionesClases; // nombre clase, valoracion
	
	public DtSocio() {}
	
	public DtSocio(String nick, String nom, String ape, String email, Date fecha, String contra, byte[] imagen, Set<String> seguidos, Set<String> seguidores, Set<String> cla, Set<String> cups, Set<String> favoritas, Map<String, Integer> valClases) {
		super(nick, nom, ape, email, fecha, contra, imagen, seguidos, seguidores);
		this.clases=cla;
		this.cuponeras = cups;
		this.favoritas = favoritas;
		this.valoracionesClases = valClases;
	}
	
	public Set<String> getClases() {
		return clases;
	}
	
	public void setClases(Set<String> clases) {
		this.clases = clases;
	}
	
	public Set<String> getCuponeras() {
		return cuponeras;
	}
	
	public void setCuponeras(Set<String> cuponeras) {
		this.cuponeras = cuponeras;
	}
	
	public Set<String> getFavoritas() {
		return favoritas;
	}
	
	public void setFavoritas(Set<String> favoritas) {
		this.favoritas = favoritas;
	}

	/**
	 * @return Devuelve un mapa con los nombres de las clases a las que esta inscripto junto con las valoraciones.
	 */
	public Map<String, Integer> getValoracionesClases() {
		return valoracionesClases;
	}
	
	public void setValoracionesClases(Map<String, Integer> valoracionesClases) {
		this.valoracionesClases = valoracionesClases;
	}
}
