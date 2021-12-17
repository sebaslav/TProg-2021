package logica;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class Usuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private Date fechaNacimiento;
	private String contrasenia;
	
	private Set<Usuario> seguidos;
	private Set<Usuario> seguidores;
	
	abstract public DtUsuario getDatosUsuario();
	abstract public void editarDatos(DtUsuario data);
	
	public Usuario(DtUsuario data) {
		this.nickname = data.getNickname();
		this.nombre = data.getNombre();
		this.apellido = data.getApellido();
		this.email = data.getEmail();
		this.fechaNacimiento = data.getFechaDeNacimiento();
		this.contrasenia = data.getContrasenia();
		(new ManejadorImagenes()).ingresarImagen("usuario_" + data.getNickname(), data.getImagen());
		
		seguidos = new HashSet<>();
		seguidores = new HashSet<>();
	}
	
	
	public String getNickname() {
		return this.nickname;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;;
	}
	
	public byte[] getImagen() {
		return (new ManejadorImagenes()).obtenerImagen("usuario_" + nickname);
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public Set<String> getSeguidos() {
		Set<String> resu = new HashSet<String>();
		for (Usuario user : seguidos) {
			resu.add(user.getNickname());
		}
		return resu;
	}
	
	public Set<String> getSeguidores() {
		Set<String> resu = new HashSet<String>();
		for (Usuario user : seguidores) {
			resu.add(user.getNickname());
		}
		return resu;
	}
	
	public void seguir(Usuario user) {
		seguidos.add(user);
	}
	
	public void teSigue(Usuario user) {
		seguidores.add(user);
	}
	
	public void noSeguir(String nomUs) {
		for (Usuario user : seguidos) {
			if (user.getNickname().equals(nomUs)) {
				seguidos.remove(user);
				break;
			}
		}
	}
	
	public void noTeSigue(String nomUs) {
		for (Usuario user : seguidores) {
			if (user.getNickname().equals(nomUs)) {
				seguidores.remove(user);
				break;
			}
		}
	}
	
}
