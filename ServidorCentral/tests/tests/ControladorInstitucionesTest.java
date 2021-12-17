package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.*;
import logica.DtActividad;
import logica.DtClase;
import logica.DtPremio;
import logica.DtProfesor;
import logica.DtSocio;
import logica.EstadoActividad;
import logica.Fabrica;
import logica.IControladorCuponeras;
import logica.IControladorInstituciones;
import logica.IControladorUsuarios;
import logica.ManejadorCategorias;
import logica.ManejadorCuponeras;
import logica.ManejadorInstituciones;
import logica.ManejadorUsuarios;

class ControladorInstitucionesTest {
	
	static IControladorUsuarios icu;
	static IControladorInstituciones ici;
	static IControladorCuponeras icm;
	
	@BeforeEach
	void init() {
		ici = Fabrica.getInstance().getControladorInstituciones();
		icu = Fabrica.getInstance().getControladorUsuario();
		icm = Fabrica.getInstance().getControladorCuponeras();
		
	}

	@AfterEach
	void restart() {
		//Elimina usuarios, instituciones y espcuponeras
		//Reinicia el estado del sistema para que los tests sean independientes
		ManejadorUsuarios mu = ManejadorUsuarios.getInstance();
		mu.getUsuarios().clear();
		ManejadorInstituciones mi = ManejadorInstituciones.getInstance();
		mi.getInstituciones().clear();
		ManejadorCuponeras mc = ManejadorCuponeras.getInstance();
		mc.getEspCuponeras().clear();
		ManejadorCategorias mcat = ManejadorCategorias.getInstance();
		mcat.getCategorias().clear();
	}
	
	@Test
	void testGetInstituciones() {
		//preparar datos
		try {
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			ici.registrarInstitucionDeportiva("nomInst2", "descInst2", "urlInst2");
		} catch (InstitucionRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> ins = ici.getInstituciones();
		
		//verificar
		assertTrue(ins.contains("nomInst"));
		assertTrue(ins.contains("nomInst2"));
		assertEquals(2, ins.size());
	}

	@Test
	void testRegistrarActividad() {
		//preparar datos
		try {
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
		} catch (InstitucionRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
		} catch (ActividadRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		
		//verificar
		DtActividad dt = ici.getDtActividad("nomAct");
		assertEquals("nomAct", dt.getNombre());
		assertEquals(420, (Float) dt.getCosto());
		assertEquals("descAct", dt.getDescripcion());
		assertEquals(30, dt.getDuracion());
		assertEquals(new Date(2000, 1, 1), dt.getFechaRegistro());
	}

	@Test
	void testGetActividadesDeInstitucion() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct2", null, "descAct2", 50, 5000f, new Date(2001, 2, 2), new byte[] {0});
		} catch (InstitucionRepetidaException | ActividadRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> acts = ici.getActividadesDeInstitucion("nomInst");
		
		//verificar
		assertTrue(acts.contains("nomAct"));
		assertTrue(acts.contains("nomAct2"));
		assertEquals(2, acts.size());
	}

	@Test
	void testGetProfesoresDeInstitucion() {
		//preparar datos
		try {
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
			icu.registrarUsuario(new DtProfesor("nickProf2", "nomProf2", "apeProf2", "prof@prof.com2", new Date(2000, 0, 13), "contraProf2", new byte[] {0}, null, null, "descProf2", "nomInst", "urlProf2", "bio2", null, null, null));
		} catch (InstitucionRepetidaException | UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> profs = ici.getProfesoresDeInstitucion("nomInst");
		
		//verificar
		assertTrue(profs.contains("nickProf"));
		assertTrue(profs.contains("nickProf2"));
		assertEquals(2, profs.size());
	}

	@Test
	void testGetDtActividad() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarClase("nomAct", "nomInst", "nickProf", new DtClase("nomClase", new Date(2000, 5, 5, 12, 0), 3, 1, new Date(2000, 2, 2, 0, 0), "urlClase", new byte[] {0}, null, null, null, "", 0, null, 0, null,false));
			icm.registrarCuponera("nomCup", "descCup", new Date(2000, 1, 1), new Date(2000, 11, 11), 0.20f, new Date(2000, 0, 1), new byte[] {0});
			icm.agregarActividadACuponera("nomInst", "nomAct", "nomCup", 3);
		} catch (InstitucionRepetidaException | ActividadRepetidaException | UsuarioRepetidoException | CuponeraRepetidaException | ClaseRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		DtActividad dt = ici.getDtActividad("nomInst", "nomAct");
		
		//verificar
		assertEquals("nomAct", dt.getNombre());
		assertEquals(420, (Float) dt.getCosto());
		assertEquals("descAct", dt.getDescripcion());
		assertEquals(30, dt.getDuracion());
		assertEquals(new Date(2000, 1, 1), dt.getFechaRegistro());
		assertTrue(dt.getClases().contains("nomClase"));
		assertEquals(1, dt.getClases().size());
		assertTrue(dt.getCuponeras().contains("nomCup"));
		assertEquals(1, dt.getCuponeras().size());
	}

	@Test
	void testRegistrarClase() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
		} catch (InstitucionRepetidaException | ActividadRepetidaException | UsuarioRepetidoException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		try {
			ici.registrarClase("nomAct", "nomInst", "nickProf", new DtClase("nomClase", new Date(2000, 5, 5, 12, 0), 3, 1, new Date(2000, 2, 2, 0, 0), "urlClase", new byte[] {0}, null, null, null, "", 0, null, 0, null, false));
		} catch (ClaseRepetidaException e) {
			e.printStackTrace();
		}
		
		//verificar
		DtClase dt = ici.getDatosDeClase("nomClase", "nomAct", "nomInst");
		assertEquals("nomClase", dt.getNombre());
		assertEquals("urlClase", dt.getUrl());
		assertEquals(1, dt.getMinSocios());
		assertEquals(3, dt.getMaxSocios());
		assertEquals(5, dt.getFechaHora().getDate());
		assertEquals(5, dt.getFechaHora().getMonth());
		assertEquals(2000, dt.getFechaHora().getYear());
		assertEquals(0, dt.getFechaHora().getMinutes());
		assertEquals(12, dt.getFechaHora().getHours());
		
		assertEquals(2, dt.getFechaDeRegistro().getDate());
		assertEquals(2, dt.getFechaDeRegistro().getMonth());
		assertEquals(2000, dt.getFechaDeRegistro().getYear());
	}

	@Test
	void testGetClasesDeActividad() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarClase("nomAct", "nomInst", "nickProf", new DtClase("nomClase", new Date(2000, 5, 5, 12, 0), 3, 1, new Date(2000, 2, 2, 0, 0), "urlClase", new byte[] {0}, "nombActividad","denis","www.laclase.com/1234", "", 0, null, 0, null, false));
//																			   0                       1             2  3                  4                5        6	        7		    8	      	  9		          10  11   12  13  14
			ici.registrarClase("nomAct", "nomInst", "nickProf", new DtClase("nomClase2", new Date(2000, 5, 5, 12, 0), 3, 1, new Date(2000, 2, 2, 0, 0), "urlClase", new byte[] {0},"nombActividad","denis","www.laclase.com/1234", "", 0, null, 0, null, false));
		} catch (InstitucionRepetidaException | ActividadRepetidaException | UsuarioRepetidoException | ClaseRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> clases = ici.getClasesDeActividad("nomAct");
		
		//verificar
		assertTrue(clases.contains("nomClase"));
		assertTrue(clases.contains("nomClase2"));
		assertEquals(2, clases.size());
	}

	@Test
	void testGetDatosDeClase() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarClase("nomAct", "nomInst", "nickProf", new DtClase("nomClase", new Date(2000, 5, 5, 12, 0), 3, 1, new Date(2000, 2, 2, 0, 0), "urlClase", new byte[] {0}, null, null, null, "", 0, null, 0, null, false));
		} catch (InstitucionRepetidaException | ActividadRepetidaException | UsuarioRepetidoException | ClaseRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		DtClase dt = ici.getDatosDeClase("nomClase");
		
		//verificar
		assertEquals("nomClase", dt.getNombre());
		assertEquals("urlClase", dt.getUrl());
		assertEquals(1, dt.getMinSocios());
		assertEquals(3, dt.getMaxSocios());
		assertEquals(5, dt.getFechaHora().getDate());
		assertEquals(5, dt.getFechaHora().getMonth());
		assertEquals(2000, dt.getFechaHora().getYear());
		assertEquals(0, dt.getFechaHora().getMinutes());
		assertEquals(12, dt.getFechaHora().getHours());
		
		assertEquals(2, dt.getFechaDeRegistro().getDate());
		assertEquals(2, dt.getFechaDeRegistro().getMonth());
		assertEquals(2000, dt.getFechaDeRegistro().getYear());
	}

	@Test
	void testGetClasesVigentes() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarClase("nomAct", "nomInst", "nickProf", new DtClase("nomClase", new Date(121, 7, 28, 13, 0), 3, 1, new Date(2000, 1, 2), "urlClase", new byte[] {0}, null, null, null, "", 0, null, 0, null, false));
			ici.registrarClase("nomAct", "nomInst", "nickProf", new DtClase("nomClase2", new Date(121, 11, 28, 13, 0), 3, 1, new Date(2000, 1, 2), "urlClase", new byte[] {0}, null, null, null, "", 0, null, 0, null, false));
		} catch (InstitucionRepetidaException | ActividadRepetidaException | UsuarioRepetidoException | ClaseRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<DtClase> clases = ici.getClasesVigentes("nomAct");
		
		//verificar
		assertEquals(1, clases.size());
		assertEquals("nomClase2", clases.iterator().next().getNombre());
	}

	@Test
	void testRegistrarInstitucionDeportiva() {
		//preparar datos
		
		//ejecutar prueba
		try {
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			ici.registrarInstitucionDeportiva("nomInst2", "descInst2", "urlInst2");
		} catch (InstitucionRepetidaException e) {
			e.printStackTrace();
		}
		
		//verificar
		Set<String> ins = ici.getInstituciones();
		assertEquals(2, ins.size());
		assertTrue(ins.contains("nomInst"));
		assertTrue(ins.contains("nomInst2"));
	}
	
	@Test
	void testGetCategorias() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarCategoria("nomCat2");
		} catch (CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		//ejecutar prueba
		Set<String> cats = ici.getCategorias();
		
		//verificar
		assertEquals(2, cats.size());
		assertTrue(cats.contains("nomCat"));
		assertTrue(cats.contains("nomCat2"));
	}
	
	@Test
	void testGetActividadesAceptadasDeInstitucion() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct2", null, "descAct2", 50, 5000f, new Date(2001, 2, 2), new byte[] {0});
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct3", null, "descAct3", 50, 5000f, new Date(2001, 2, 2), new byte[] {0});
			ici.aceptarActividad("nomAct", true);
			ici.aceptarActividad("nomAct2", false);
		} catch (InstitucionRepetidaException | ActividadRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> acts = ici.getActividadesAceptadasDeInstitucion("nomInst");
		
		//verificar
		assertTrue(acts.contains("nomAct"));
		assertEquals(1, acts.size());
	}
	
	@Test
	void testGetActividadesDeCategoria() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarCategoria("nomCat2");
			ici.registrarCategoria("nomCat3");
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat", "nomCat2")), "nomAct2", null, "descAct2", 50, 5000f, new Date(2001, 2, 2), new byte[] {0});
		} catch (InstitucionRepetidaException | ActividadRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> actsNomCat = ici.getActividadesDeCategoria("nomCat");
		Set<String> actsNomCat2 = ici.getActividadesDeCategoria("nomCat2");
		Set<String> actsNomCat3 = ici.getActividadesDeCategoria("nomCat3");
		
		//verificar
		assertTrue(actsNomCat.contains("nomAct"));
		assertTrue(actsNomCat.contains("nomAct2"));
		assertEquals(2, actsNomCat.size());
		assertTrue(actsNomCat2.contains("nomAct2"));
		assertEquals(1, actsNomCat2.size());
		assertEquals(0, actsNomCat3.size());
	}
	
	@Test
	void testGetActividadesAceptadasDeCategoria() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct2", null, "descAct2", 50, 5000f, new Date(2001, 2, 2), new byte[] {0});
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct3", null, "descAct3", 50, 5000f, new Date(2001, 2, 2), new byte[] {0});
			ici.aceptarActividad("nomAct", true);
			ici.aceptarActividad("nomAct2", false);
		} catch (InstitucionRepetidaException | ActividadRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> acts = ici.getActividadesAceptadasDeCategoria("nomCat");
		
		//verificar
		assertTrue(acts.contains("nomAct"));
		assertEquals(1, acts.size());
	}
	
	@Test
	void testGetActividadesIngresadas() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct2", null, "descAct2", 50, 5000f, new Date(2001, 2, 2), new byte[] {0});
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct3", null, "descAct3", 50, 5000f, new Date(2001, 2, 2), new byte[] {0});
			ici.aceptarActividad("nomAct", true);
			ici.aceptarActividad("nomAct2", false);
		} catch (InstitucionRepetidaException | ActividadRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> acts = ici.getActividadesIngresadas();
		
		//verificar
		assertTrue(acts.contains("nomAct3"));
		assertEquals(1, acts.size());
	}
	
	@Test
	void testPremios() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.aceptarActividad("nomAct", true);
			ici.registrarClase("nomAct", "nomInst", "nickProf", new DtClase("nomClase", new Date(2000, 12, 31, 23, 59), 3, 1, new Date(2000, 12, 1, 12, 30), "urlClase", new byte[] {0}, "nomAct", "nickProf", "videoURL", "descPremio", 3, null, 0, null, false));
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(2000,1,1,12,0), "contra", new byte[] {0}, null, null, null, null, null, null));
			icu.registrarUsuario(new DtSocio("nickSocio2", "nomSocio2", "apeSocio2", "socio2@socio.com", new Date(2000,1,1,12,0), "contra", new byte[] {0}, null, null, null, null, null, null));
			icu.registrarSocioAClase("nickSocio", null, new Date(2000,12,2,9,20), "nomClase", "nomAct", "nomInst");
			icu.registrarSocioAClase("nickSocio2", null, new Date(2000,12,2,9,40), "nomClase", "nomAct", "nomInst");
		} catch (InstitucionRepetidaException | ActividadRepetidaException | CategoriaRepetidaException | ClaseRepetidaException | SocioYaRegistradoException | ClaseLlenaException | UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		ici.sortearPremios("nomClase");
		Set<String> ganadores = ici.verGanadores("nomClase");
		Set<String> inscriptos = ici.getInscriptosAClase("nomClase");
		Set<DtPremio> premios = icu.consultarPremios("nickSocio");
		DtPremio elPremio = null;
		for(DtPremio premio : premios) {
			elPremio = premio;
		}
	
		//verificar
		assertTrue(ganadores.contains("nickSocio"));
		assertTrue(ganadores.contains("nickSocio2"));
		assertTrue(inscriptos.contains("nickSocio"));
		assertTrue(inscriptos.contains("nickSocio2"));
		assertEquals(1, premios.size());
		assertEquals(2, ganadores.size());
		assertEquals(2, inscriptos.size());
		assertEquals(elPremio.getNomClase(), "nomClase");
		assertEquals(elPremio.getNomActividad(), "nomAct");
	}

	@Test
	void testPremiosForzado() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.aceptarActividad("nomAct", true);
			ici.registrarClase("nomAct", "nomInst", "nickProf", new DtClase("nomClase", new Date(2000, 12, 31, 23, 59), 3, 1, new Date(2000, 12, 1, 12, 30), "urlClase", new byte[] {0}, "nomAct", "nickProf", "videoURL", "descPremio", 1, null, 0, null, false));
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(2000,1,1,12,0), "contra", new byte[] {0}, null, null, null, null, null, null));
			icu.registrarUsuario(new DtSocio("nickSocio2", "nomSocio2", "apeSocio2", "socio2@socio.com", new Date(2000,1,1,12,0), "contra", new byte[] {0}, null, null, null, null, null, null));
			icu.registrarSocioAClase("nickSocio", null, new Date(2000,12,2,9,20), "nomClase", "nomAct", "nomInst");
			icu.registrarSocioAClase("nickSocio2", null, new Date(2000,12,2,9,40), "nomClase", "nomAct", "nomInst");
		} catch (InstitucionRepetidaException | ActividadRepetidaException | CategoriaRepetidaException | ClaseRepetidaException | SocioYaRegistradoException | ClaseLlenaException | UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> nickSocios = new HashSet<String>();
		nickSocios.add("nickSocio");
		ici.sortearPremioForzado("nomClase", nickSocios, new Date(2020,1,1,12,30));
		Set<String> ganadores = ici.verGanadores("nomClase");
		Set<String> inscriptos = ici.getInscriptosAClase("nomClase");
		Set<DtPremio> premios = icu.consultarPremios("nickSocio");
		DtPremio elPremio = null;
		for(DtPremio premio : premios) {
			elPremio = premio;
		}
	
		//verificar
		assertTrue(ganadores.contains("nickSocio"));
		assertTrue(inscriptos.contains("nickSocio"));
		assertTrue(inscriptos.contains("nickSocio2"));
		assertEquals(1, ganadores.size());
		assertEquals(2, inscriptos.size());
		assertEquals(1, premios.size());
		assertEquals(elPremio.getNomClase(), "nomClase");
		assertEquals(elPremio.getNomActividad(), "nomAct");
	}
	
	
	@Test
	void testFinalizarActividad() {
		//preparar datos
		try {
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
		} catch (InstitucionRepetidaException e) {
			e.printStackTrace();
		}	
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
		} catch (ActividadRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		//ejecutar prueba
		ici.finalizarActividad("nomAct");

		//verificar
		assertEquals(EstadoActividad.Finalizada, ici.getDtActividad("nomAct").getEstado());
	}

	@Test
	void testBuscarActividad() {
		//preparar datos
		try {
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
		} catch (InstitucionRepetidaException | UsuarioRepetidoException e) {
			e.printStackTrace();
		}	
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", "nickProf", "desctargetaaa", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomtargetaaa", "nickProf", "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct2", "nickProf", "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct3", "nickProf", "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.aceptarActividad("nomAct", true);
			ici.aceptarActividad("nomtargetaaa", true);
			ici.aceptarActividad("nomAct2", true);
		} catch (ActividadRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		//ejecutar prueba
		Set<DtActividad> datosActividades = ici.buscarActividad("target");
		Set<String> nomActividades = new HashSet<>();
		for(DtActividad da : datosActividades) {
			nomActividades.add(da.getNombre());
		}
		
		//verificar
		assertEquals(2, datosActividades.size());
		assertTrue(nomActividades.contains("nomAct"));
		assertTrue(nomActividades.contains("nomtargetaaa"));
	}

	@Test
	void testBuscarTodasLasActividad() {
		//preparar datos
		try {
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
		} catch (InstitucionRepetidaException | UsuarioRepetidoException e) {
			e.printStackTrace();
		}	
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", "nickProf", "desctargetaaa", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct2", "nickProf", "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.aceptarActividad("nomAct", true);
		} catch (ActividadRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		//ejecutar prueba
		Set<DtActividad> datosActividades = ici.buscarActividad("");
		Set<String> nomActividades = new HashSet<>();
		for(DtActividad da : datosActividades) {
			nomActividades.add(da.getNombre());
		}
		
		//verificar
		assertEquals(1, datosActividades.size());
		assertTrue(nomActividades.contains("nomAct"));
	}
}
