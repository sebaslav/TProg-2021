package servidor;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ColeccionString {
	
	private Set<String> col = null;
	
	public ColeccionString() {}
	
	public ColeccionString(Set<String> col) {
		this.col = col;
	}
	
	public Set<String> getColeccion() {
		return col;
	}
	
	public void setColeccion(Set<String> col) {
		this.col = col;
	}
}
