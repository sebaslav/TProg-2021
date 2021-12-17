package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import excepciones.ActividadRepetidaException;
import excepciones.CategoriaRepetidaException;
import excepciones.ClaseRepetidaException;
import excepciones.InstitucionRepetidaException;

public class ControladorInstituciones implements IControladorInstituciones {
	
	public ControladorInstituciones() {
	}
	
	public Set<String> getInstituciones() {
		Set<String> res = new HashSet<String>();
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Map<String, Institucion> ins = mins.getInstituciones();
		for (Institucion inst : ins.values()) {
			res.add(inst.getNombre());
		}
		return res;
	}
	
	public Set<String> getCategorias() {
		Set<String> res = new HashSet<String>();
		ManejadorCategorias mcat = ManejadorCategorias.getInstance();
		Map<String, Categoria> cats = mcat.getCategorias();
		for (Categoria cat : cats.values()) {
			res.add(cat.getNombre());
		}
		return res;
	}
	
	public void registrarActividad(String inst, Set<String> categorias, String activ, String nomProf, String descripcion, int duracion, float costo, Date fechaRegistro, byte[] imagen) throws ActividadRepetidaException {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Map<String, Institucion> its = mins.getInstituciones();
		for (Institucion ins : its.values()) {
			if (ins.tieneActiv(activ))
				throw new ActividadRepetidaException("Ya exite una actividad con nombre \"" + activ + "\" en el sistema");
		}
		Institucion institucion = mins.find(inst);
		ManejadorCategorias mcat = ManejadorCategorias.getInstance();
		Set<Categoria> cats = new HashSet<>();
		for (String nomCat : categorias) {
			cats.add(mcat.find(nomCat));
		}
		Actividad act = new Actividad(activ, cats, descripcion, duracion, costo, fechaRegistro, imagen);
		institucion.agregarActiv(act);
		if (nomProf != null) {
			Profesor prof = institucion.getProfesor(nomProf);
			prof.agregarActiv(act);
		}
	}
	
	public Set<String> getActividadesDeInstitucion(String inst) {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Institucion its = mins.find(inst);
		return its.getNombreActividades(); // viktor->Consultar Kickboxing-> BOOM
	}
	
	public Set<String> getProfesoresDeInstitucion(String inst) {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Institucion its = mins.find(inst);
		return its.getProfesores();
	}
	
	public DtActividad getDtActividad(String inst, String activ) {
		if (inst == null) {
			return null;
		}
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Institucion its = mins.find(inst);
		return its.getDatosActividad(activ);
	}
	
	public void registrarClase(String activ, String inst, String prof, DtClase datos) throws ClaseRepetidaException {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Map<String, Institucion> ins = mins.getInstituciones();
		Iterator<Institucion> itr = ins.values().iterator();
		while (itr.hasNext()) {
			Institucion its = itr.next();
			if (its.existeClase(datos.getNombre()))
				throw new ClaseRepetidaException("La clase " + datos.getNombre() + " ya esta registrada");
		}
		Institucion institucion = mins.find(inst);
		institucion.crearClase(datos, activ, prof);
	}
	
	public Set<String> getClasesDeActividad(String activ, String inst) {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Institucion its = mins.find(inst);
		return its.getClasesDeActividad(activ);
	}
	
	public DtClase getDatosDeClase(String clase, String act, String inst) {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Institucion its = mins.find(inst);
		return its.getDatosDeClase(act, clase);
	}
	
	public Set<DtClase> getClasesVigentes(String inst, String activ) {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Institucion its = mins.find(inst);
		return its.getClasesVigentes(activ);
	}
	
	public void registrarInstitucionDeportiva(String inst, String desc, String url) throws InstitucionRepetidaException {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		if (mins.exists(inst))
			throw new InstitucionRepetidaException("La institucion " + inst + " ya esta registrada");
		Institucion its = new Institucion(inst, desc, url);
		mins.add(its);
	}
	
	public DtClase getDatosDeClase(String nomClase) {
		String nomAct = null;
		String nomInst = null;
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Map<String, Institucion> ins = mins.getInstituciones();
		Iterator<Institucion> itr = ins.values().iterator();
		boolean encontrado = false;
		while (itr.hasNext() && !encontrado) {
			Institucion its = itr.next();
			nomAct = its.getActividadDeClase(nomClase);
			if (nomAct != null) {
				nomInst = its.getNombre();
				encontrado = true;
			}
		}
		if (encontrado) {
			return getDatosDeClase(nomClase, nomAct, nomInst);
		} else {
			return null;
		}
	}
	
	public Set<String> getActividadesAceptadasDeInstitucion(String nomInst) {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Institucion its = mins.find(nomInst);
		return its.getNombreActividadesAceptadas();
	}
	
	public DtActividad getDtActividad(String nomAct) {
		String nomInst = getInstitucionDeActividad(nomAct);
		return getDtActividad(nomInst, nomAct);
	}
	
	public String getInstitucionDeActividad(String nomAct) {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Map<String, Institucion> ins = mins.getInstituciones();
		Iterator<Institucion> itr = ins.values().iterator();
		boolean encontrado = false;
		String res = null;
		while (itr.hasNext() && !encontrado) {
			Institucion its = itr.next();
			if (its.tieneActiv(nomAct)) {
				res = its.getNombre();
				encontrado = true;
			}
		}
		return res;
	}
	
	public Set<String> getActividadesDeCategoria(String nomCat) {
		ManejadorCategorias mcat = ManejadorCategorias.getInstance();
		Categoria cat = mcat.find(nomCat);
		return cat.getNombreActividades();
	}
	
	public Set<String> getActividadesAceptadasDeCategoria(String nomCat) {
		ManejadorCategorias mcat = ManejadorCategorias.getInstance();
		Categoria cat = mcat.find(nomCat);
		return cat.getNombreActividadesAceptadas();
	}
	
	public Set<String> getClasesDeActividad(String nomAct) {
		String nomInst = getInstitucionDeActividad(nomAct);
		return getClasesDeActividad(nomAct, nomInst);
	}
	
	public Set<DtClase> getClasesVigentes(String nomAct) {
		String nomInst = getInstitucionDeActividad(nomAct);
		return getClasesVigentes(nomInst, nomAct);
	}
	
	public void registrarCategoria(String nomCat) throws CategoriaRepetidaException {
		ManejadorCategorias mcat = ManejadorCategorias.getInstance();
		if (mcat.exists(nomCat))
			throw new CategoriaRepetidaException("La categoria " + nomCat + " ya esta registrada");
		Categoria cat = new Categoria(nomCat);
		mcat.add(cat);
	}
	
	public Set<String> getActividadesIngresadas() {
		Set<String> res = new HashSet<>();
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Map<String, Institucion> ins = mins.getInstituciones();
		for (Institucion its : ins.values()) {
			Set<String> actsIng = its.getNombreActividadesIngresadas();
			res.addAll(actsIng);
		}
		return res;
	}
	
	public void aceptarActividad(String nomAct, boolean acepta) {
		String nomInst = getInstitucionDeActividad(nomAct);
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Institucion inst = mins.find(nomInst);
		Actividad act = inst.getActividad(nomAct);
		act.aceptar(acepta);
	}
	public void finalizarActividad(String nomAct) {
		String nomInst = getInstitucionDeActividad(nomAct);
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Institucion inst = mins.find(nomInst);
		Actividad act = inst.getActividad(nomAct);
		act.finalizar();
	}
	public Set<DtActividad> buscarActividad(String texto) {
		Set<DtActividad> res = new HashSet<>();
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Map<String, Institucion> ins = mins.getInstituciones();
		for (Institucion its : ins.values()) {
			Set<String> acts = its.getNombreActividadesAceptadas();
			for (String act : acts) {
				Actividad actividad = its.getActividad(act);
				if (actividad.contieneTexto(texto)) {
					res.add(actividad.getDatos());
				}
			}
		}
		return res;
	}
	
	/**
	 * Busca una clase por su nombre. En caso de no encontrarla,
	 * devuelve null.
	 * 
	 * @param nomClase nombre que identifica a la clase
	 * @return referencia (objeto) Clase. Null si no se encontro.
	 */
	private Clase buscarClase(String nomClase) {
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Map<String, Institucion> ins = mins.getInstituciones();
		Clase clase = null;
		for (Institucion its : ins.values()) {
			if (its.tieneClase(nomClase)) {
				clase = its.getClase(its.getActividadDeClase(nomClase), nomClase);
				break;
			}
		}
		return clase;
	}
	
	/**
	 * PRECOND. fechaSorteo de clase == NULL
	 */
	public void sortearPremios(String nomClase) {
		/*
		ManejadorInstituciones mins = ManejadorInstituciones.getInstance();
		Map<String, Institucion> ins = mins.getInstituciones();
		Clase clase = null;
		for (Institucion its : ins.values()) {
			if (its.tieneClase(nomClase)) {
				clase = its.getClase(its.getActividadDeClase(nomClase), nomClase);
				break;
			}
		}
		*/
		
		Clase clase = buscarClase(nomClase);
		clase.realizarSorteo(new Date());
		
		// Metodo original
		//DtClase dtClase = clase.getDatos();
		//int cantPremios = dtClase.getCantPremios();
		
		// Metodo nuevo (mas rapido)
		int cantPremios = clase.getCantPremios();
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		
		Set<String> inscriptos = getInscriptosAClase(nomClase);
		List<String> listaInscriptos = new ArrayList<>(inscriptos);
		while (cantPremios > 0 && listaInscriptos.size() > 0) {
			int numGanador = new Random().nextInt(listaInscriptos.size());
			String ganador = listaInscriptos.get(numGanador);	
			Socio socioGanador = (Socio) muser.find(ganador);
			socioGanador.ganarPremio(nomClase);
			cantPremios--;
			listaInscriptos.remove(numGanador);
		}
	}
	
	
	/**
	 * (SOLO PARA CARGAR DATOS DE PRUEBA!!)
	 * Registra a los socios especificados en la clase.
	 * 
	 * PRECOND. Los socios participan/estan registrados en la clase
	 * PRECOND. nickSocios != NULL
	 * PRECOND. nickSocios.size() es menor o igual a la cantidad de premios de la clase.
	 * 
	 * @param nomClase Nombre de la clase
	 * @param nickSocios Nickname de los socios a premiar. Tienen que ser menor a cantPremios 
	 */
	public void sortearPremioForzado(String nomClase, Set<String> nickSocios, Date fechaSorteo) {
			
		// Getear objeto Clase y setearle fecha de sorteo
		Clase clase = buscarClase(nomClase);
		clase.realizarSorteo(fechaSorteo);
		
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		for (String ganador : nickSocios ) {
			Socio socioGanador = (Socio) muser.find(ganador);
			socioGanador.ganarPremio(clase.getNombre());
		}
	}
	
	public Set<String> verGanadores(String nomClase) {
		Set<String> res = new HashSet<>();
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		Iterator<Usuario> iter = muser.getUsuarios().values().iterator();
		while (iter.hasNext()) {
			Usuario user = iter.next();
			if (user instanceof Socio) {
				Socio socio = (Socio) user;
				Set<DtPremio> premios = socio.consultarPremios();
				for (DtPremio dtPremio : premios) {
					if (dtPremio.getNomClase().equals(nomClase)) {
						res.add(socio.getNickname());
					}
				}
			}
		}
		return res;
	}
	
	public Set<String> getInscriptosAClase(String nomClase) {
		Set<String> res = new HashSet<>();
		ManejadorUsuarios muser = ManejadorUsuarios.getInstance();
		Iterator<Usuario> iter = muser.getUsuarios().values().iterator();
		while (iter.hasNext()) {
			Usuario user = iter.next();
			if (user instanceof Socio) {
				Socio socio = (Socio) user;
				if (socio.estaInscriptoAClase(nomClase)) {
					res.add(socio.getNickname());
				}
			}
		}
		return res;
	}

}
