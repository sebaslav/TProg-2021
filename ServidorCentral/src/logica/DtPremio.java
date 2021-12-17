package logica;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPremio {
	
	private Date fechaSorteo;
	private String descPremio;
	private String nomClase;
	private String nomActividad;

	public DtPremio() {}
	
	public DtPremio(Date fechaSorteo, String descPremio, String nomClase, String nomActividad) {
		this.fechaSorteo = fechaSorteo;
		this.descPremio = descPremio;
		this.nomClase = nomClase;
		this.nomActividad = nomActividad;
	}
	
	public Date getFechaSorteo() {
		return fechaSorteo;
	}
	
	public void setFechaSorteo(Date fechaSorteo) {
		this.fechaSorteo = fechaSorteo;
	}
	
	public String getDescPremio() {
		return descPremio;
	}
	
	public void setDescPremio(String descPremio) {
		this.descPremio = descPremio;
	}
	
	public String getNomClase() {
		return nomClase;
	}
	
	public void setNomClase(String nomClase) {
		this.nomClase = nomClase;
	}
	
	public String getNomActividad() {
		return nomActividad;
	}
	
	public void setNomActividad(String nomActividad) {
		this.nomActividad = nomActividad;
	}
}
