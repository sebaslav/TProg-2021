package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.ActividadRepetidaException;
import excepciones.CategoriaRepetidaException;
import excepciones.CuponeraRepetidaException;
import excepciones.CuponeraYaCompradaException;
import excepciones.InstitucionRepetidaException;
import excepciones.UsuarioRepetidoException;
import logica.DtCuponera;
import logica.DtSocio;
import logica.Fabrica;
import logica.IControladorCuponeras;
import logica.IControladorInstituciones;
import logica.IControladorUsuarios;
import logica.ManejadorCategorias;
import logica.ManejadorCuponeras;
import logica.ManejadorInstituciones;
import logica.ManejadorUsuarios;

class ControladorCuponerasTest {

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
	void testGetEspCuponeras() {
		//preparar datos
		try {
			icm.registrarCuponera("nomCup", "descCup", new Date(2000, 1, 2), new Date(2100, 2, 10), 0.20f, new Date(2000, 0, 1), new byte[] {0});
			icm.registrarCuponera("nomCup2", "descCup2", new Date(2000, 1, 3), new Date(2100, 4, 5), 0.30f, new Date(2000, 0, 4), new byte[] {0});
		} catch (CuponeraRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> esps = icm.getEspCuponeras();
		
		//verificar
		assertTrue(esps.contains("nomCup"));
		assertTrue(esps.contains("nomCup2"));
		assertEquals(2, esps.size());
	}

	@Test
	void testAgregarActividadACuponera() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			icm.registrarCuponera("nomCup", "descCup", new Date(2000, 1, 2), new Date(2100, 2, 10), 0.20f, new Date(2000, 0, 1), new byte[] {0});
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
		} catch (CuponeraRepetidaException | InstitucionRepetidaException | ActividadRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		icm.agregarActividadACuponera("nomInst", "nomAct", "nomCup", 3);
		
		//verificar
		DtCuponera dt = icm.getDatosCuponera("nomCup");
		assertEquals("nomCup", dt.getNombre());
		assertEquals("descCup", dt.getDescripcion());
		assertEquals(0.20f, (Float) dt.getDescuento());
		
		assertEquals(1, dt.getFechaRegistro().getDate());
		assertEquals(0, dt.getFechaRegistro().getMonth());
		assertEquals(2000, dt.getFechaRegistro().getYear());
		
		assertEquals(2, dt.getValidoDesde().getDate());
		assertEquals(1, dt.getValidoDesde().getMonth());
		assertEquals(2000, dt.getValidoDesde().getYear());
		
		assertEquals(10, dt.getValidoHasta().getDate());
		assertEquals(2, dt.getValidoHasta().getMonth());
		assertEquals(2100, dt.getValidoHasta().getYear());
		
		assertEquals(1, dt.getActividades().size());
		String key = dt.getActividades().keySet().iterator().next();
		assertEquals("nomAct", key);
		assertEquals(3, dt.getActividades().get(key));
	}

	@Test
	void testGetDatosCuponera() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			icm.registrarCuponera("nomCup", "descCup", new Date(2000, 1, 2), new Date(2100, 2, 10), 0.20f, new Date(2000, 0, 1), new byte[] {0});
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
			ici.aceptarActividad("nomAct", true);
			icm.agregarActividadACuponera("nomInst", "nomAct", "nomCup", 3);
		} catch (CuponeraRepetidaException | InstitucionRepetidaException | ActividadRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		DtCuponera dt = icm.getDatosCuponera("nomCup");
		
		//verificar
		assertEquals("nomCup", dt.getNombre());
		assertEquals("descCup", dt.getDescripcion());
		assertEquals(0.20f, (Float) dt.getDescuento());
		
		assertEquals(1, dt.getFechaRegistro().getDate());
		assertEquals(0, dt.getFechaRegistro().getMonth());
		assertEquals(2000, dt.getFechaRegistro().getYear());
		
		assertEquals(2, dt.getValidoDesde().getDate());
		assertEquals(1, dt.getValidoDesde().getMonth());
		assertEquals(2000, dt.getValidoDesde().getYear());
		
		assertEquals(10, dt.getValidoHasta().getDate());
		assertEquals(2, dt.getValidoHasta().getMonth());
		assertEquals(2100, dt.getValidoHasta().getYear());
		
		assertEquals(1, dt.getActividades().size());
		String key = dt.getActividades().keySet().iterator().next();
		assertEquals("nomAct", key);
		assertEquals(3, dt.getActividades().get(key));
		
		assertTrue(dt.getCategorias().contains("nomCat"));
		assertEquals(1, dt.getCategorias().size());
		assertTrue(252.0 == dt.getCosto());
	}

	@Test
	void testRegistrarCuponera() {
		//preparar datos
		
		//ejecutar prueba
		try {
			icm.registrarCuponera("nomCup", "descCup", new Date(2000, 1, 2), new Date(2100, 2, 10), 0.20f, new Date(2000, 0, 1), new byte[] {0});
		} catch (CuponeraRepetidaException  e) {
			e.printStackTrace();
		}
		
		//verificar
		DtCuponera dt = icm.getDatosCuponera("nomCup");
		assertEquals("nomCup", dt.getNombre());
		assertEquals("descCup", dt.getDescripcion());
		assertEquals(0.20f, (Float) dt.getDescuento());
		
		assertEquals(1, dt.getFechaRegistro().getDate());
		assertEquals(0, dt.getFechaRegistro().getMonth());
		assertEquals(2000, dt.getFechaRegistro().getYear());
		
		assertEquals(2, dt.getValidoDesde().getDate());
		assertEquals(1, dt.getValidoDesde().getMonth());
		assertEquals(2000, dt.getValidoDesde().getYear());
		
		assertEquals(10, dt.getValidoHasta().getDate());
		assertEquals(2, dt.getValidoHasta().getMonth());
		assertEquals(2100, dt.getValidoHasta().getYear());
		
		assertEquals(0, dt.getActividades().size());
	}

	@Test
	void testGetActividadesFaltantes() {
		//preparar datos
		try {
			ici.registrarCategoria("nomCat");
			icm.registrarCuponera("nomCup", "descCup", new Date(2000, 1, 2), new Date(2100, 2, 10), 0.20f, new Date(2000, 0, 1), new byte[] {0});
			
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			ici.registrarInstitucionDeportiva("nomInst2", "descInst2", "urlInst2");
			
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420f, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct2", null, "descAct2", 30, 420f, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarActividad("nomInst2", new HashSet<String>(Arrays.asList("nomCat")), "nomAct3", null, "descAct3", 30, 420f, new Date(2000, 1, 1), new byte[] {0});
			ici.registrarActividad("nomInst2", new HashSet<String>(Arrays.asList("nomCat")), "nomAct4", null, "descAct4", 30, 420f, new Date(2000, 1, 1), new byte[] {0});
			
			ici.aceptarActividad("nomAct", true);
			ici.aceptarActividad("nomAct2", true);
			ici.aceptarActividad("nomAct3", true);
			ici.aceptarActividad("nomAct4", true);
			
			icm.agregarActividadACuponera("nomInst", "nomAct2", "nomCup", 3);
			icm.agregarActividadACuponera("nomInst2", "nomAct3", "nomCup", 3);
		} catch (CuponeraRepetidaException | InstitucionRepetidaException | ActividadRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> af = icm.getActividadesFaltantes("nomInst", "nomCup");
		Set<String> af2 = icm.getActividadesFaltantes("nomInst2", "nomCup");
		
		//verificar
		assertEquals(1, af.size());
		assertEquals(1, af2.size());
		assertTrue(af.contains("nomAct"));
		assertTrue(af2.contains("nomAct4"));
	}
	
	@Test
	void testGetEspCuponerasNoCompradas() {
		//preparar datos
		try {
			icm.registrarCuponera("nomCup", "descCup", new Date(2000, 1, 2), new Date(2100, 2, 10), 0.20f, new Date(2000, 0, 1), new byte[] {0});
			icm.registrarCuponera("nomCup2", "descCup2", new Date(2000, 1, 3), new Date(2100, 4, 5), 0.30f, new Date(2000, 0, 4), new byte[] {0});
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(1990, 4, 25), "contra", new byte[] {0}, null, null, null, null, null, null));
			icu.comprarCuponera("nickSocio", "nomCup", new Date(2000, 2, 3));
		} catch (UsuarioRepetidoException | CuponeraRepetidaException | CuponeraYaCompradaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> espsNC = icm.getEspCuponerasNoCompradas();
		
		//verificar
		assertTrue(espsNC.contains("nomCup2"));
		assertEquals(1, espsNC.size());
	}
	
	@Test
	void testGetEspCuponerasVigentes() {
		//preparar datos
		try {
			icm.registrarCuponera("nomCup", "descCup", new Date(1900, 1, 2), new Date(1990, 2, 10), 0.20f, new Date(2000, 0, 1), new byte[] {0});
			icm.registrarCuponera("nomCup2", "descCup2", new Date(121, 1, 3), new Date(121, 12, 12), 0.30f, new Date(2000, 0, 4), new byte[] {0});
		} catch (CuponeraRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> espsV = icm.getEspCuponerasVigentes();
		
		//verificar
		assertTrue(espsV.contains("nomCup2"));
		assertEquals(1, espsV.size());
	}

	@Test
	void testBuscarCuponera() {
		//preparar datos
		try {
			icm.registrarCuponera("nomCuptargetaaa", "descCup", new Date(1900, 1, 2), new Date(1990, 2, 10), 0.20f, new Date(2000, 0, 1), new byte[] {0});
			icm.registrarCuponera("nomCup", "descCuptargetaaa", new Date(121, 1, 3), new Date(121, 12, 12), 0.30f, new Date(2000, 0, 4), new byte[] {0});
			icm.registrarCuponera("nomCup2", "descCup", new Date(121, 1, 3), new Date(121, 12, 12), 0.30f, new Date(2000, 0, 4), new byte[] {0});
		} catch (CuponeraRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<DtCuponera> cuponeras = icm.buscarCuponera("target");
		Set<String> nomCuponeras = new HashSet<String>();
		for(DtCuponera dc : cuponeras) {
			nomCuponeras.add(dc.getNombre());
		}
		
		//verificar
		assertEquals(2, cuponeras.size());
		assertTrue(nomCuponeras.contains("nomCuptargetaaa"));
		assertTrue(nomCuponeras.contains("nomCup"));
	}
}
