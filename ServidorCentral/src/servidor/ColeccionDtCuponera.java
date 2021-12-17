package servidor;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import logica.DtCuponera;


@XmlAccessorType(XmlAccessType.FIELD)
public class ColeccionDtCuponera {
	
	private Set<DtCuponera> col = null;
	
	public ColeccionDtCuponera() {}
	
	public ColeccionDtCuponera(Set<DtCuponera> col) {
		this.col = col;
	}
	
	public Set<DtCuponera> getColeccion() {
		return col;
	}
	
	public void setColeccion(Set<DtCuponera> col) {
		this.col = col;
	}
}
