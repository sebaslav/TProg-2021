package logica;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ManejadorUsuarios {
	private Map<String, Usuario> usuarios;
	private static ManejadorUsuarios instancia = null;
	
	private ManejadorUsuarios() {
		usuarios = new HashMap<String, Usuario>();
	}
	
	public static ManejadorUsuarios getInstance() {
		if (instancia == null) {
			instancia = new ManejadorUsuarios();
		}
		return instancia;
	}
	
	public void add(Usuario user) {
		usuarios.put(user.getNickname(), user);
	}

	public boolean existsNickname(String nick) {
		return usuarios.get(nick) != null;
	}
	
	public boolean existsEmail(String email) {
		for (Map.Entry<String, Usuario> entry : usuarios.entrySet()){
			if (entry.getValue().getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	public Map<String, Usuario> getUsuarios() {
		return usuarios;
	}
	
	public Usuario find(String nickEmail) {
		Usuario usuario = null;
		if (!nickEmail.contains("@")) {
			usuario = usuarios.get(nickEmail);
		} else {
			Iterator<Usuario> iter = usuarios.values().iterator();
			while (iter.hasNext()) {
				Usuario user = iter.next();
				if (user.getEmail().equals(nickEmail)) {
					usuario = user;
					break;
				}
			}
		}
		return usuario;
	}
}
