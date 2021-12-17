package logica;
import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtUsuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private Date fechaDeNacimiento;
	private String contrasenia;
	private byte[] imagen;
	private Set<String> seguidos;
	private Set<String> seguidores;
	
	protected DtUsuario() {}
	
	protected DtUsuario(String nick, String nom, String ape, String email, Date fecha, String contrasenia, byte[] imagen, Set<String> seguidos, Set<String> seguidores){
		this.nickname=nick;
		this.nombre=nom;
		this.apellido=ape;
		this.email=email;
		this.fechaDeNacimiento=fecha;
		this.contrasenia = contrasenia;
		this.imagen = imagen;
		this.seguidos = seguidos;
		this.seguidores = seguidores;
		
	}

	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public byte[] getImagen() {
		return imagen;
	}
	
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	public Set<String> getSeguidos() {
		return seguidos;
	}
	
	public void setSeguidos(Set<String> seguidos) {
		this.seguidos = seguidos;
	}
	
	public Set<String> getSeguidores() {
		return seguidores;
	}
	
	public void setSeguidores(Set<String> seguidores) {
		this.seguidores = seguidores;
	}
	
}
