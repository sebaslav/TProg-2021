package servidor;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import logica.DtClase;

@XmlAccessorType(XmlAccessType.FIELD)
public class ColeccionDtClase {
	
	private Set<DtClase> col = null;
	
	public ColeccionDtClase() {}
	
	public ColeccionDtClase(Set<DtClase> col) {
		this.col = col;
	}
	
	public Set<DtClase> getColeccion() {
		return col;
	}
	
	public void setColeccion(Set<DtClase> col) {
		this.col = col;
	}
}
