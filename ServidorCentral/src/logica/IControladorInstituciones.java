package logica;

import excepciones.ActividadRepetidaException;
import excepciones.CategoriaRepetidaException;
import excepciones.ClaseRepetidaException;
import excepciones.InstitucionRepetidaException;
import java.util.Set;
import java.util.Date;

public interface IControladorInstituciones {

	/**
	 * @return nombres de todas las instituciones
	 */
	public abstract Set<String> getInstituciones();
	
	public abstract Set<String> getCategorias();

	/**
	 * @param institucion nombre de la instituciones
	 * @param nombre nombre de la actividad
	 * @param descripcion descripcion de la actividad
	 * @param duracion duracion (en minutos) de la actividad
	 * @param costo costo de la actividad
	 * @param fechaRegistro fecha de registro
	 * @throws ActividadRepetidaException
	 */
	public abstract void registrarActividad(String institucion, Set<String> categorias, String nombre, String nomProf, String descripcion, int duracion,
			float costo, Date fechaRegistro, byte[] imagen) throws ActividadRepetidaException;

	/**
	 * @param nombre nombre ed la actividad
	 * @return nombre de las actividades de instituciones
	 */
	public abstract Set<String> getActividadesDeInstitucion(String nombre);

	/**
	 * @param nombre nombre profesor
	 * @return
	 */
	public abstract Set<String> getProfesoresDeInstitucion(String nombre);

	public abstract DtActividad getDtActividad(String nomInst, String activ);

	public abstract void registrarClase(String activ, String inst, String prof, DtClase datos)
			throws ClaseRepetidaException;

	public abstract Set<String> getClasesDeActividad(String nombreAct, String nombreInst);

	public abstract DtClase getDatosDeClase(String nombreClase, String nombreAct, String nombreInst);

	public abstract Set<DtClase> getClasesVigentes(String nomInst, String nomAct);

	public abstract void registrarInstitucionDeportiva(String nomInst, String descrip, String url)
			throws InstitucionRepetidaException;

	public abstract DtClase getDatosDeClase(String nomClase);
	
	public abstract Set<String> getActividadesAceptadasDeInstitucion(String nomInst);
	
	public abstract DtActividad getDtActividad(String nomAct);
	
	public abstract String getInstitucionDeActividad(String nomAct);
	
	public abstract Set<String> getActividadesDeCategoria(String cat);
	
	public abstract Set<String> getActividadesAceptadasDeCategoria(String cat);
	
	public abstract Set<String> getClasesDeActividad(String nomAct);
	
	public abstract Set<DtClase> getClasesVigentes(String nomAct);
	
	public abstract void registrarCategoria(String cat) throws CategoriaRepetidaException;

	public abstract Set<String> getActividadesIngresadas();
	
	public abstract void aceptarActividad(String nomAct, boolean acepta);
	
	public abstract void finalizarActividad(String nomAct);
	
	public abstract Set<DtActividad> buscarActividad(String texto);
	
	public abstract void sortearPremios(String nomClase);
	
	
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
	public void sortearPremioForzado(String nomClase, Set<String> nickSocios, Date fechaSorteo);
	
	public abstract Set<String> verGanadores(String nomClase);
	
	public abstract Set<String> getInscriptosAClase(String nomClase);
	
}
