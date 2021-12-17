package servidor;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import logica.DtActividad;


@XmlAccessorType(XmlAccessType.FIELD)
public class ColeccionDtActividad {
	
	private Set<DtActividad> col = null;
	
	public ColeccionDtActividad() {}
	
	public ColeccionDtActividad(Set<DtActividad> col) {
		this.col = col;
	}
	
	public Set<DtActividad> getColeccion() {
		return col;
	}
	
	public void setColeccion(Set<DtActividad> col) {
		this.col = col;
	}
}
