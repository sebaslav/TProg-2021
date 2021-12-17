package logica;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtProfesor extends DtUsuario {
	
	private String institucion;
	private String url;
	private String biografia;
	private String descripcion;
	private Map<String, String> clases;
	private Map<String, EstadoActividad> actividades;
	private DtValoracion valoraciones;
	
	public DtProfesor() {
		super();
	}
	
	public DtProfesor(String nick, String nom, String ape, String email, Date fecha, String contra, byte[] imagen, Set<String> seguidos, Set<String> seguidores, String des, String ins, String url, String bio, Map<String, String> cla, Map<String, EstadoActividad> acts, DtValoracion valoraciones) {
		super(nick, nom, ape, email, fecha, contra, imagen, seguidos, seguidores);
		this.institucion = ins;
		this.url = url;
		this.biografia = bio;
		this.descripcion = des;
		this.clases = cla;
		this.actividades = acts;
		this.valoraciones = valoraciones;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getUrl() {
		return url;
	}

	public String getBiografia() {
		return biografia;
	}
	
	public String getInstitucion() {
		return institucion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public Map<String, String> getClases() {
		return clases;
	}
	
	public void setClases(Map<String, String> clases) {
		this.clases = clases;
	}
	
	public Map<String, EstadoActividad> getActividades() {
		return actividades;
	}
	
	public void setActividades(Map<String, EstadoActividad> actividades) {
		this.actividades = actividades;
	}
	
	public DtValoracion getValoraciones() {
		return valoraciones;
	}
	
	public void setValoraciones(DtValoracion valoraciones) {
		this.valoraciones = valoraciones;
	}

}
