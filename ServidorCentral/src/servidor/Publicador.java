package servidor;

import javax.jws.WebService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import excepciones.ActividadRepetidaException;
import excepciones.ClaseLlenaException;
import excepciones.ClaseRepetidaException;
import excepciones.CuponeraRepetidaException;
import excepciones.CuponeraYaCompradaException;
import excepciones.SocioYaRegistradoException;
import excepciones.UsuarioRepetidoException;
import logica.DtActividad;
import logica.DtClase;
import logica.DtCuponera;
import logica.DtProfesor;
import logica.DtSocio;
import logica.DtUsuario;
import logica.Fabrica;


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class Publicador {
	
	private Endpoint endpoint = null;
	
	public Publicador() {}
	
	@WebMethod(exclude = true)
	public void publicar() throws FileNotFoundException, IOException {
		File fileDir = new File("/home/" + System.getProperty("user.name") + "/.entrenamosUy/");     
		if (fileDir.mkdirs()) {
			Properties defaultProps = new Properties();
			defaultProps.setProperty("urlWebService", "http://localhost:9129/publicador");
			defaultProps.setProperty("rutaBaseImagenes", "img/");
			FileOutputStream out = new FileOutputStream("/home/" + System.getProperty("user.name") + "/.entrenamosUy/propiedades.properties");
			defaultProps.store(out, "");
		}
		Properties props = new Properties();
		FileInputStream in = new FileInputStream("/home/" + System.getProperty("user.name") + "/.entrenamosUy/propiedades.properties");
		props.load(in);
		in.close();
		String urlWebService = props.getProperty("urlWebService");
		endpoint = Endpoint.publish(urlWebService, this);
	}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}
	
	/*
	 * Controlador Usuarios
	 */
	/*
	@WebMethod
	public void registrarUsuario(DtUsuario user) throws UsuarioRepetidoException {
		Fabrica.getInstance().getControladorUsuario().registrarUsuario(user);
	}
	*/
	
	@WebMethod
	public void registrarSocio(DtSocio user) throws UsuarioRepetidoException {
		Fabrica.getInstance().getControladorUsuario().registrarUsuario(user);
	}
	
	@WebMethod
	public void registrarProfesor(DtProfesor user) throws UsuarioRepetidoException {
		Fabrica.getInstance().getControladorUsuario().registrarUsuario(user);
	}
	
	@WebMethod
	public DtUsuario getDatosUsuario(String nombre) {
		return Fabrica.getInstance().getControladorUsuario().getDatosUsuario(nombre);
	}
	
	@WebMethod
	public ColeccionString getListaUsuarios() {
		return new ColeccionString(Fabrica.getInstance().getControladorUsuario().getListaUsuarios());
	}
	
	@WebMethod
	public void editarDatosUsuario(DtUsuario user) {
		Fabrica.getInstance().getControladorUsuario().editarDatosUsuario(user);
	}
	
	@WebMethod
	public ColeccionString getSocios() {
		return new ColeccionString(Fabrica.getInstance().getControladorUsuario().getSocios());
	}
	
	@WebMethod
	public ColeccionString getCuponerasDisponibles(String nickname, String nomActividad) {
		return new ColeccionString(Fabrica.getInstance().getControladorUsuario().getCuponerasDisponibles(nickname, nomActividad));
	}
	
	@WebMethod
	public void registrarSocioAClase(String nickname, String nomCuponera, Date fechaReg, String nomClas,
			String nomAct) throws SocioYaRegistradoException, ClaseLlenaException {
		Fabrica.getInstance().getControladorUsuario().registrarSocioAClase(nickname, nomCuponera, fechaReg, nomClas, nomAct);
	}
	
	@WebMethod
	public boolean iniciarSesion(String nickEmail, String contrasenia) {
		return Fabrica.getInstance().getControladorUsuario().iniciarSesion(nickEmail, contrasenia);
	}
	
	@WebMethod
	public void comprarCuponera(String nick, String nomCup, Date fechaCompra) throws CuponeraYaCompradaException {
		Fabrica.getInstance().getControladorUsuario().comprarCuponera(nick, nomCup, fechaCompra);
	}
	
	@WebMethod
	public void seguirUsuario(String nomSeguidor, String nomTarget) {
		Fabrica.getInstance().getControladorUsuario().seguirUsuario(nomSeguidor, nomTarget);
	}
	
	@WebMethod
	public void dejarSeguirUsuario(String nomSeguidor, String nomTarget) {
		Fabrica.getInstance().getControladorUsuario().dejarSeguirUsuario(nomSeguidor, nomTarget);
	}
	
	@WebMethod
	public ColeccionDtPremio consultarPremios(String nickSocio) {
		return new ColeccionDtPremio(Fabrica.getInstance().getControladorUsuario().consultarPremios(nickSocio));
	}
	
	@WebMethod
	public boolean cambiarActividadFavorita(String nickSocio, String nomActividad) {
		return Fabrica.getInstance().getControladorUsuario().cambiarActividadFavorita(nickSocio, nomActividad);
	}
	
	@WebMethod
	public void valorarClase(String nickSocio, String nomClase, int valoracion) {
		Fabrica.getInstance().getControladorUsuario().valorarClase(nickSocio, nomClase, valoracion);
	}
	
	@WebMethod
	public boolean existeNickname(String nickname) {
		return Fabrica.getInstance().getControladorUsuario().existeNickname(nickname);
	}
	
	@WebMethod
	public boolean existeEmail(String email) {
		return Fabrica.getInstance().getControladorUsuario().existeEmail(email);
	}
	
	/*
	 * Controlador Instituciones
	 */
	
	@WebMethod
	public ColeccionString getInstituciones() {
		return new ColeccionString(Fabrica.getInstance().getControladorInstituciones().getInstituciones());
	}
	
	@WebMethod
	public ColeccionString getCategorias() {
		return new ColeccionString(Fabrica.getInstance().getControladorInstituciones().getCategorias());
	}
	
	@WebMethod
	public void registrarActividad(String institucion, ColeccionString categorias, String nombre, String nomProf, String descripcion, int duracion,
			float costo, Date fechaRegistro, byte[] imagen) throws ActividadRepetidaException {
		Fabrica.getInstance().getControladorInstituciones().registrarActividad(institucion, categorias.getColeccion(), nombre, nomProf, descripcion, duracion, costo, fechaRegistro, imagen);
	}
	
	@WebMethod
	public ColeccionString getActividadesDeInstitucion(String nombre) {
		return new ColeccionString(Fabrica.getInstance().getControladorInstituciones().getActividadesDeInstitucion(nombre));
	}
	
	@WebMethod
	public ColeccionString getProfesoresDeInstitucion(String nombre) {
		return new ColeccionString(Fabrica.getInstance().getControladorInstituciones().getProfesoresDeInstitucion(nombre));
	}
	
	@WebMethod
	public void registrarClase(String activ, String inst, String prof, DtClase datos)
			throws ClaseRepetidaException {
		Fabrica.getInstance().getControladorInstituciones().registrarClase(activ, inst, prof, datos);;
	}
	
	@WebMethod
	public DtClase getDatosDeClase(String nomClase) {
		return Fabrica.getInstance().getControladorInstituciones().getDatosDeClase(nomClase);
	}
	
	@WebMethod
	public ColeccionString getActividadesAceptadasDeInstitucion(String nomInst) {
		return new ColeccionString(Fabrica.getInstance().getControladorInstituciones().getActividadesAceptadasDeInstitucion(nomInst));
	}
	
	@WebMethod
	public DtActividad getDtActividad(String nomAct) {
		return Fabrica.getInstance().getControladorInstituciones().getDtActividad(nomAct);
	}
	
	@WebMethod
	public String getInstitucionDeActividad(String nomAct) {
		return Fabrica.getInstance().getControladorInstituciones().getInstitucionDeActividad(nomAct);
	}
	
	@WebMethod
	public ColeccionString getActividadesDeCategoria(String cat) {
		return new ColeccionString(Fabrica.getInstance().getControladorInstituciones().getActividadesDeCategoria(cat));
	}
	
	@WebMethod
	public ColeccionString getActividadesAceptadasDeCategoria(String cat) {
		return new ColeccionString(Fabrica.getInstance().getControladorInstituciones().getActividadesAceptadasDeCategoria(cat));
	}
	
	@WebMethod
	public ColeccionString getClasesDeActividad(String nomAct) {
		return new ColeccionString(Fabrica.getInstance().getControladorInstituciones().getClasesDeActividad(nomAct));
	}
	
	@WebMethod
	public ColeccionDtClase getClasesVigentes(String nomAct) {
		return new ColeccionDtClase(Fabrica.getInstance().getControladorInstituciones().getClasesVigentes(nomAct));
	}
	
	@WebMethod
	public ColeccionString getActividadesIngresadas() {
		return new ColeccionString(Fabrica.getInstance().getControladorInstituciones().getActividadesIngresadas());
	}
	
	@WebMethod
	public ColeccionDtActividad buscarActividad(String texto) {
		return new ColeccionDtActividad(Fabrica.getInstance().getControladorInstituciones().buscarActividad(texto));
	}
	
	@WebMethod
	public void sortearPremios(String nomClase) {
		Fabrica.getInstance().getControladorInstituciones().sortearPremios(nomClase);
	}
	
	@WebMethod
	public ColeccionString verGanadores(String nomClase) {
		return new ColeccionString(Fabrica.getInstance().getControladorInstituciones().verGanadores(nomClase));
	}
	
	@WebMethod
	public ColeccionString getInscriptosAClase(String nomClase) {
		return new ColeccionString(Fabrica.getInstance().getControladorInstituciones().getInscriptosAClase(nomClase));
	}
	
	@WebMethod
	public void  finalizarActividad(String nomAct) {
		Fabrica.getInstance().getControladorInstituciones().finalizarActividad(nomAct);
	}
	
	/*
	 * Controlador Cuponeras
	 */
	
	@WebMethod
	public ColeccionString getEspCuponeras() {
		return new ColeccionString(Fabrica.getInstance().getControladorCuponeras().getEspCuponeras());
	}
	
	@WebMethod
	public DtCuponera getDatosCuponera(String nomCuponera) {
		return Fabrica.getInstance().getControladorCuponeras().getDatosCuponera(nomCuponera);
	}
	
	@WebMethod
	public void registrarCuponera(String nombre, String descripcion, 
			Date inicio, Date fin, float descuento, Date fechaRegistro, byte[] imagen) throws CuponeraRepetidaException {
		Fabrica.getInstance().getControladorCuponeras().registrarCuponera(nombre, descripcion, inicio, fin, descuento, fechaRegistro, imagen);
	}
	
	@WebMethod
	public ColeccionString getActividadesFaltantes(String nomInstituto, String nomCuponera) {
		return new ColeccionString(Fabrica.getInstance().getControladorCuponeras().getActividadesFaltantes(nomInstituto, nomCuponera));
	}
	
	@WebMethod
	public ColeccionString getEspCuponerasNoCompradas() {
		return new ColeccionString(Fabrica.getInstance().getControladorCuponeras().getEspCuponerasNoCompradas());
	}
	
	@WebMethod
	public ColeccionString getEspCuponerasVigentes() {
		return new ColeccionString(Fabrica.getInstance().getControladorCuponeras().getEspCuponerasVigentes());
	}
	
	@WebMethod
	public ColeccionDtCuponera buscarCuponera(String texto) {
		return new ColeccionDtCuponera(Fabrica.getInstance().getControladorCuponeras().buscarCuponera(texto));
	}
	
	/*
	 * Controlador Registros
	 */
	
	@WebMethod
	public void agregarRegistro(String ip, String url, String browser, String sisOp, Date fecha) {
		Fabrica.getInstance().getControladorRegistros().agregarRegistro(ip, url, browser, sisOp, fecha);
	}
	
}
