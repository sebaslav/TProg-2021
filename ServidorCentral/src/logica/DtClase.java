package logica;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtClase {
	
	private String nombre;
	private Date fechaHora;
	private int maxSocios;
	private int minSocios;
	private Date fechaDeRegistro;
	private String url;
	private byte[] imagen;
	private String actividad;
	private String profesor;
	private String videoURL;
	private String descPremio;
	private int cantPremios;
	private Date fechaSorteo;
	private int cantInscriptos;
	private DtValoracion valoraciones;
	private boolean finalizada;
	
	public DtClase() {}
	
	/**
	 * @param nom Nombre de la clase
	 * @param fechaHor Fecha en la que se dicta la clase
	 * @param max Cantidad maxima de socios asociados que puede tener la clase
	 * @param min Cantidad minima de socios asociados que puede tener la clase
	 * @param fechaReg Fecha en la que se registra la clase en el sistema
	 * @param url URL de la clase
	 * @param imagen Imagen de la clase, como arreglo de bytes
	 * @param actividad Nombre de la actividad asociada a la clase
	 * @param profesor Nickname del profesor que dicta la clase
	 * @param videoURL URL del video de la clase
	 * @param descPremio Descripcion del(de los) premio(s) de la clase
	 * @param cantPremios Cantidad de premios que se sortean en la clase
	 * @param fechaSorteo Fecha en la que se sortea
	 * @param cantInscriptos Cantidad de inscriptos en la clase
	 * @param valoraciones Cantidad de cada puntaje, del 1 al 5, que recibio la clase
	 */
	public DtClase(String nom, Date fechaHor, int max, int min, Date fechaReg, String url, byte[] imagen, String actividad, String profesor, String videoURL, String descPremio, int cantPremios, Date fechaSorteo, int cantInscriptos, DtValoracion valoraciones, boolean finalizada) {
		this.nombre = nom;
		this.fechaHora = fechaHor;
		this.fechaDeRegistro = fechaReg;
		this.maxSocios = max;
		this.minSocios = min;
		this.url=url;
		this.imagen = imagen;
		this.actividad = actividad;
		this.profesor = profesor;
		this.videoURL = videoURL;
		this.descPremio = descPremio;
		this.cantPremios = cantPremios;
		this.fechaSorteo = fechaSorteo;
		this.cantInscriptos = cantInscriptos;
		
		this.valoraciones = valoraciones;
		this.finalizada = finalizada;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Date getFechaHora() {
		return fechaHora;
	}
	
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	
	public int getMaxSocios() {
		return maxSocios;
	}
	
	public void setMaxSocios(int maxSocios) {
		this.maxSocios = maxSocios;
	}
	
	public int getMinSocios() {
		return minSocios;
	}
	
	public void setMinSocios(int minSocios) {
		this.minSocios = minSocios;
	}
	
	public Date getFechaDeRegistro() {
		return fechaDeRegistro;
	}
	
	public void setFechaDeRegistro(Date fechaDeRegistro) {
		this.fechaDeRegistro = fechaDeRegistro;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public byte[] getImagen() {
		return imagen;
	}
	
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	public String getActividad() {
		return actividad;
	}
	
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	
	/**
	 * @return Devuelve nickname del profesor que dicta la clase
	 */
	public String getProfesor() {
		return profesor;
	}
	
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	
	public String getVideoURL() {
		return videoURL;
	}
	
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
	
	public String getDescPremio() {
		return descPremio;
	}
	
	public void setDescPremio(String descPremio) {
		this.descPremio = descPremio;
	}
	
	public int getCantPremios() {
		return cantPremios;
	}
	
	public void setCantPremios(int cantPremios) {
		this.cantPremios = cantPremios;
	}
	
	public Date getFechaSorteo() {
		return fechaSorteo;
	}
	
	public void setFechaSorteo(Date fechaSorteo) {
		this.fechaSorteo = fechaSorteo;
	}
	
	public int getCantInscriptos() {
		return cantInscriptos;
	}
	
	public void setCantInscriptos(int cantInscriptos) {
		this.cantInscriptos = cantInscriptos;
	}

	/**
	 * @return Devuelve DtValoracion con los distintos puntajes que recibio la clase
	 */
	public DtValoracion getValoraciones() {
		return valoraciones;
	}
	
	public void setValoraciones(DtValoracion valoraciones) {
		this.valoraciones = valoraciones;
	}
	
	public boolean getFinalizada() {
		return finalizada;
	}
	
	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}
}
