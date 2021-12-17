package logica;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtCuponera {
	
	private String nombre;
	private String descripcion;
	private Date validoDesde;
	private Date validoHasta;
	private float descuento;
	private Date fechaRegistro;
	private Map<String, Integer> actividades;
	private Set<String> categorias;
	private float costo;
	private byte[] imagen;
	
	public DtCuponera() {}
	
	public DtCuponera(String nom, String desc, Date desde, Date hasta, float descuento, Date registro, Map<String, Integer> activ, Set<String> categorias, float costo, byte[] imagen) {
		this.nombre=nom;
		this.descripcion=desc;
		this.validoDesde = desde;
		this.validoHasta = hasta;
		this.descuento = descuento;
		this.fechaRegistro=registro;
		this.actividades=activ;
		this.categorias = categorias;
		this.costo = costo;
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getValidoDesde() {
		return validoDesde;
	}
	
	public void setValidoDesde(Date validoDesde) {
		this.validoDesde = validoDesde;
	}

	public Date getValidoHasta() {
		return validoHasta;
	}
	
	public void setValidoHasta(Date validoHasta) {
		this.validoHasta = validoHasta;
	}

	public float getDescuento() {
		return descuento;
	}
	
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * @return Nombre de Actividad, Cantidad de Clases
	 */
	public Map<String, Integer> getActividades() {
		return actividades;
	}
	
	public void setActividades(Map<String, Integer> actividades) {
		this.actividades = actividades;
	}
	
	public Set<String> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(Set<String> categorias) {
		this.categorias = categorias;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public byte[] getImagen() {
		return imagen;
	}
	
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

}
