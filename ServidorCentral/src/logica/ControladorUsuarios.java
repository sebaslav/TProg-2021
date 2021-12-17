
package logica;

import java.util.Set;

import excepciones.ClaseLlenaException;
import excepciones.CuponeraYaCompradaException;
import excepciones.SocioYaRegistradoException;
import excepciones.UsuarioRepetidoException;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;


public class ControladorUsuarios implements IControladorUsuarios {
	
	public ControladorUsuarios() {
	}
	
	public void registrarUsuario(DtUsuario data) throws UsuarioRepetidoException {
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		if (muser.existsNickname(data.getNickname()))
				throw new UsuarioRepetidoException("El nickname " + data.getNickname() + " ya esta registrado");
		if (muser.existsEmail(data.getEmail()))
				throw new UsuarioRepetidoException("El email " + data.getEmail() + " ya esta registrado");
		Usuario user;
		if (data instanceof DtSocio) {
			user = new Socio(data);
		} else {
			user = new Profesor(data);
			ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
			Institucion inst = mins.find(((DtProfesor) data).getInstitucion());
			inst.agregarProfesor((Profesor) user);
		}
		muser.add(user);
	}
	
	public Set<String> getListaUsuarios() {
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		Iterator<Usuario> iter = muser.getUsuarios().values().iterator();
		Set<String> res = new HashSet<String>();
		while (iter.hasNext()) {
			res.add(iter.next().getNickname());
		}
		return res;
	}
		
	
	public DtUsuario getDatosUsuario(String nickname) {
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		Usuario user = muser.find(nickname);
		if (user != null)
			return user.getDatosUsuario();
		else
			return null;
	}
	
	public void editarDatosUsuario(DtUsuario data) {
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		Usuario user = muser.find(data.getNickname());
		user.editarDatos(data);
	}
	
	public Set<String> getSocios() {
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		Iterator<Usuario> iter = muser.getUsuarios().values().iterator();
		Set<String> res = new HashSet<String>();
		while (iter.hasNext()) {
			Usuario elem = iter.next();
			if (elem instanceof Socio)
				res.add(elem.getNickname());
		}	
		return res;
	}
	
	public Set<String> getCuponerasDisponibles(String nickname, String nomActividad) {
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		Socio user = (Socio) muser.find(nickname);
		return user.getCuponerasDisponibles(nomActividad);
	}
	
	public void registrarSocioAClase(String nickname, String nomCuponera, Date fechaReg, String nomClase, String nomActividad, String nomInst) throws SocioYaRegistradoException, ClaseLlenaException {
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		Socio user = (Socio) (muser.find(nickname));
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Institucion ins = mins.find(nomInst);
		Clase clase = ins.getClase(nomActividad, nomClase);
		if (clase.estaLlena()) {
			throw new ClaseLlenaException("La clase " + nomClase + " se encuentra llena, por favor seleccione otra");
		} else if (user.estaInscriptoAClase(nomClase)) {
			throw new SocioYaRegistradoException("El socio " + nickname + " ya se encuentra registrado para esta clase, por favor selecione otro");
		} else {
			user.registrarInscripcion(nomCuponera, nomActividad, fechaReg, clase);
			clase.inscribirSocio();
		}
	}
	
	public void registrarSocioAClase(String nickname, String nomCuponera, Date fechaReg, String nomClase, String nomActividad) throws SocioYaRegistradoException, ClaseLlenaException {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Map<String, Institucion> ins = mins.getInstituciones();
		Iterator<Institucion> itr = ins.values().iterator();
		boolean encontrado = false;
		String nomInst = null;
		while (itr.hasNext() && !encontrado) {
			Institucion its = itr.next();
			if (its.tieneActiv(nomActividad)) {
				nomInst = its.getNombre();
				encontrado = true;
			}
		}
		registrarSocioAClase(nickname, nomCuponera, fechaReg, nomClase, nomActividad, nomInst);
	}
	
	public boolean iniciarSesion(String nickEmail, String contrasenia) {
		boolean res = false;
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		Usuario user = muser.find(nickEmail);
		if (user != null)
			res = user.getContrasenia().equals(contrasenia);
		return res;
	}
	
	public void comprarCuponera(String nick, String nomCup, Date fechaCompra) throws CuponeraYaCompradaException {
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		Socio user = (Socio) muser.find(nick);
		if (user.tieneCuponera(nomCup))
			throw new CuponeraYaCompradaException("La cuponera " + nomCup + " ya fue comprada por el socio " + nick);
		user.comprarCuponera(fechaCompra, nomCup);
	}
	
	public void seguirUsuario(String nomSeguidor, String nomTarget) {
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		Usuario useg = muser.find(nomSeguidor);
		Usuario utar = muser.find(nomTarget);
		useg.seguir(utar);
		utar.teSigue(useg);
	}
	
	public void dejarSeguirUsuario(String nomSeguidor, String nomTarget) {
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		Usuario useg = muser.find(nomSeguidor);
		Usuario utar = muser.find(nomTarget);
		useg.noSeguir(nomTarget);
		utar.noTeSigue(nomSeguidor);
	}
	
	public Set<DtPremio> consultarPremios(String nickSocio) {
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		Socio user = (Socio) muser.find(nickSocio);
		return user.consultarPremios();
	}
	
	public boolean cambiarActividadFavorita(String nickSocio, String nomActividad) {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Map<String, Institucion> ins = mins.getInstituciones();
		Actividad actFav = null;
		for (Institucion inst : ins.values()) {
			if (inst.tieneActiv(nomActividad)) {
				actFav = inst.getActividad(nomActividad);
			}
		}
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		Socio user = (Socio) muser.find(nickSocio);
		return user.cambiarEstadoFavorita(actFav);
	}
	
	public void valorarClase(String nickSocio, String nomClase, int valoracion) {
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		Socio socio = (Socio) muser.find(nickSocio);
		socio.valorarClase(nomClase, valoracion);
	}
	
	public boolean existeNickname(String nickname) {
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		return muser.existsNickname(nickname);
	}
	
	public boolean existeEmail(String email) {
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		return muser.existsEmail(email);
	}


}
