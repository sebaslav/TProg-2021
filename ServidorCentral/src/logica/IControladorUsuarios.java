package logica;

import java.util.Date;
import java.util.Set;

import excepciones.ClaseLlenaException;
import excepciones.CuponeraYaCompradaException;
import excepciones.SocioYaRegistradoException;
import excepciones.UsuarioRepetidoException;

public interface IControladorUsuarios {
	/**
	 * @param user datos basicos de un usuario
	 * @throws UsuarioRepetidoException
	 */
	public abstract void registrarUsuario(DtUsuario user) throws UsuarioRepetidoException;

	/**
	 * @param nombre nickname del usuario
	 */
	public abstract DtUsuario getDatosUsuario(String nombre);

	/**
	 * @return nombre de todos los usuarios
	 */
	public abstract Set<String> getListaUsuarios();

	/**
	 * @param user datos basicos de un usuario
	 */
	public abstract void editarDatosUsuario(DtUsuario user);

	/**
	 * @return nickname de todos los socios
	 */
	public abstract Set<String> getSocios();

	/**
	 * @param nickname nombre del socio
	 * @param nomActividad nombre de la actividad
	 * @return nombres de las cuponeras del socio con clases restantes de la actividad
	 */
	public abstract Set<String> getCuponerasDisponibles(String nickname, String nomActividad);

	/**
	 * @param nickname nickname del socio
	 * @param nomCuponera nombre de la cuponera
	 * @param fechaReg fecha con la que se registra
	 * @param nomClas nombre de la clase
	 * @param nomAct nombre de la actividad
	 * @param nomInst nombre de la institucion
	 * @throws SocioYaRegistradoException
	 */
	public abstract void registrarSocioAClase(String nickname, String nomCuponera, Date fechaReg, String nomClas,
			String nomAct, String nomInst) throws SocioYaRegistradoException, ClaseLlenaException;
	
	public abstract void registrarSocioAClase(String nickname, String nomCuponera, Date fechaReg, String nomClas,
			String nomAct) throws SocioYaRegistradoException, ClaseLlenaException;
	
	public abstract boolean iniciarSesion(String nickEmail, String contrasenia);
	
	public abstract void comprarCuponera(String nick, String nomCup, Date fechaCompra) throws CuponeraYaCompradaException;
	
	public abstract void seguirUsuario(String nomSeguidor, String nomTarget);
	
	public abstract void dejarSeguirUsuario(String nomSeguidor, String nomTarget);
	
	public abstract Set<DtPremio> consultarPremios(String nickSocio);
	
	public abstract boolean cambiarActividadFavorita(String nickSocio, String nomActividad);
	
	public abstract void valorarClase(String nickSocio, String nomClase, int valoracion);
	
	public abstract boolean existeNickname(String nickname);
	
	public abstract boolean existeEmail(String email);
	
}



