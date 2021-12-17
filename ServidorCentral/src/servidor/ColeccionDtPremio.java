package servidor;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import logica.DtPremio;

@XmlAccessorType(XmlAccessType.FIELD)
public class ColeccionDtPremio {
	
	private Set<DtPremio> col = null;
	
	public ColeccionDtPremio() {}
	
	public ColeccionDtPremio(Set<DtPremio> col) {
		this.col = col;
	}
	
	public Set<DtPremio> getColeccion() {
		return col;
	}
	
	public void setColeccion(Set<DtPremio> col) {
		this.col = col;
	}
}
