package logica;

import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtActividad {
	private String nombre;
	private String descripcion;
	private int duracion; // cantidad de minutos
	private float costo;
	private Date fechaRegistro;
	private EstadoActividad estado;
	private byte[] imagen;
	
	private Set<String> clases;
	private Set<String> cuponeras;
	private Set<String> categorias;
	private String institucion;
	private int cantFavoritas;
	
	public DtActividad() {}
	
	/**
	 *@param nombre nombre de la descripcion
	 *@param descripcion descripcion de la actividad
	 *@param duracion duracion en minutos de la activdad
	 *@param costo costo de la actividad
	 *@param fechaRegistro fecha de registro de la actividad
	 *@param cuponeras set de Strings, nombres de las cuponeras
	 *@param clases set de Strings, nombres de las clases
	 */
	public DtActividad(String nombre, String descripcion, int duracion, float costo, Date fechaRegistro, Set<String> clases, Set<String> cuponeras, EstadoActividad estado, Set<String> categorias, byte[] imagen, String institucion, int cantFavoritas) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.costo = costo;
		this.fechaRegistro = fechaRegistro;
		this.estado = estado;
		this.imagen = imagen;
		
		this.clases = clases;
		this.cuponeras = cuponeras;
		this.categorias = categorias;
		this.institucion = institucion;
		this.cantFavoritas = cantFavoritas;
	}

	/**
	 * @return nombre de la actividad
	 */
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return descripcion de la actividad
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	/**
	 * @return duracion de la actividad
	 */
	public int getDuracion() {
		return duracion;
	}
	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}


	/**
	 * @return costo de la actividad
	 */
	public float getCosto() {
		return costo;
	}
	
	public void setCosto(float costo) {
		this.costo = costo;
	}


	/**
	 * @return la fecha de registro de la cuponera
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	
	/**
	 * @return set de strings con los nombres de las clases
	 */
	public Set<String> getClases() {
		return clases;
	}
	
	public void setClases(Set<String> clases) {
		this.clases = clases;
	}
	
	/**
	 * @return set de strings con los nombres de las cuponeras
	 */
	public Set<String> getCuponeras() {
		return cuponeras;
	}
	
	public void setCuponeras(Set<String> cuponeras) {
		this.cuponeras = cuponeras;
	}
	
	public EstadoActividad getEstado() {
		return estado;
	}
	
	public void setEstado(EstadoActividad estado) {
		this.estado = estado;
	}
	
	public Set<String> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(Set<String> categorias) {
		this.categorias = categorias;
	}
	
	public byte[] getImagen() {
		return imagen;
	}
	
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	public String getInstitucion() {
		return institucion;
	}
	
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
	
	public int getCantFavoritas() {
		return cantFavoritas;
	}
	
	public void setCantFavoritas(int cantFavoritas) {
		this.cantFavoritas = cantFavoritas;
	}
}
