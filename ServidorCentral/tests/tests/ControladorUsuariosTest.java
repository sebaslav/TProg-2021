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
import excepciones.ClaseLlenaException;
import excepciones.ClaseRepetidaException;
import excepciones.CuponeraRepetidaException;
import excepciones.InstitucionRepetidaException;
import excepciones.SocioYaRegistradoException;
import excepciones.UsuarioRepetidoException;
import excepciones.CategoriaRepetidaException;
import excepciones.CuponeraYaCompradaException;
import logica.DtClase;
import logica.DtProfesor;
import logica.DtSocio;
import logica.DtUsuario;
import logica.DtValoracion;
import logica.Fabrica;
import logica.IControladorCuponeras;
import logica.IControladorInstituciones;
import logica.IControladorUsuarios;
import logica.ManejadorCategorias;
import logica.ManejadorCuponeras;
import logica.ManejadorInstituciones;
import logica.ManejadorUsuarios;

class ControladorUsuariosTest {

	static IControladorUsuarios icu;
	static IControladorInstituciones ici;
	static IControladorCuponeras icm;
	
	@BeforeEach
	void init() {
		ici = Fabrica.getInstance().getControladorInstituciones();
		icu = Fabrica.getInstance().getControladorUsuario();
		icm = Fabrica.getInstance().getControladorCuponeras();
		try {
			ici.registrarCategoria("nomCat");
			ici.registrarInstitucionDeportiva("nomInst", "descInst", "urlInst");
			ici.registrarActividad("nomInst", new HashSet<String>(Arrays.asList("nomCat")), "nomAct", null, "descAct", 30, 420, new Date(2000, 1, 1), new byte[] {0});
		} catch (InstitucionRepetidaException | ActividadRepetidaException | CategoriaRepetidaException e) {
			e.printStackTrace();
		}
		
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
	void testRegistrarUsuarioSocioCorrecto() {
		
		//preparar datos
		Date fecha = new Date(1990, 4, 25);
		DtSocio dt = new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", fecha, "contra", new byte[] {0}, null, null, null, null, null, null);
		
		//ejecutar prueba
		try {
			icu.registrarUsuario(dt);
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		
		//verificar
		DtUsuario du = icu.getDatosUsuario("nickSocio");
		assertEquals("nickSocio", du.getNickname());
		assertEquals("nomSocio", du.getNombre());
		assertEquals("apeSocio", du.getApellido());
		assertEquals("socio@socio.com", du.getEmail());
		assertEquals(1990, dt.getFechaDeNacimiento().getYear());
		assertEquals(4, dt.getFechaDeNacimiento().getMonth());
		assertEquals(25, dt.getFechaDeNacimiento().getDate());
	}
	
	@Test
	void testRegistrarUsuarioProfesorCorrecto() {
		
		//preparar datos
		DtProfesor dt = new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null);
		
		//ejecutar prueba
		try {
			icu.registrarUsuario(dt);
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		
		//verificar
		DtUsuario du = icu.getDatosUsuario("nickProf");
		assertEquals("nickProf", du.getNickname());
		assertEquals("nomProf", du.getNombre());
		assertEquals("apeProf", du.getApellido());
		assertEquals("prof@prof.com", du.getEmail());
		assertEquals(2000, dt.getFechaDeNacimiento().getYear());
		assertEquals(0, dt.getFechaDeNacimiento().getMonth());
		assertEquals(13, dt.getFechaDeNacimiento().getDate());
		DtProfesor dp = (DtProfesor) du;
		assertEquals("descProf", dp.getDescripcion());
		assertEquals("nomInst", dp.getInstitucion());
		assertEquals("urlProf", dp.getUrl());
		assertEquals("bio", dp.getBiografia());
	}

	@Test
	void testGetListaUsuarios() {
		
		//preparar datos
		DtSocio dt = new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(1990, 4, 25), "contra", new byte[] {0}, null, null, null, null, null, null);
		try {
			icu.registrarUsuario(dt);
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		DtSocio dt2 = new DtSocio("nickSocio2", "nomSocio", "apeSocio", "socio@socio.com2", new Date(1990, 4, 25), "contra", new byte[] {0}, null, null, null, null, null, null);
		try {
			icu.registrarUsuario(dt2);
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> us = icu.getListaUsuarios();
		
		//verificar
		assertEquals(2, us.size());
		assertTrue(us.contains("nickSocio"));
		assertTrue(us.contains("nickSocio2"));
	}

	@Test
	void testGetDatosUsuarioSocio() {
		
		//preparar datos
		try {
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(1990, 4, 25), "contra", new byte[] {0}, null, null, null, null, null, null));
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
			ici.registrarClase("nomAct", "nomInst", "nickProf", new DtClase("nomClase", new Date(2000, 5, 5, 12, 0), 3, 1, null, "urlClase", new byte[] {0}, "nomAct", "nickProf", "videoURL", "descPremio", 0, null, 0, null, false));
//																				0				1					 2  3 	4	    	5       6        7     8 	         9             10      11  12  13   14                                                
			icu.registrarSocioAClase("nickSocio", null, new Date(2000, 3, 3), "nomClase", "nomAct", "nomInst");
		} catch (UsuarioRepetidoException | ClaseLlenaException | SocioYaRegistradoException | ClaseRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		DtUsuario du = icu.getDatosUsuario("nickSocio");
		
		//verificar
		assertEquals("nickSocio", du.getNickname());
		assertEquals("nomSocio", du.getNombre());
		assertEquals("apeSocio", du.getApellido());
		assertEquals("socio@socio.com", du.getEmail());
		assertEquals(1990, du.getFechaDeNacimiento().getYear());
		assertEquals(4, du.getFechaDeNacimiento().getMonth());
		assertEquals(25, du.getFechaDeNacimiento().getDate());
		DtSocio ds = (DtSocio) du;
		assertTrue(ds.getClases().contains("nomClase"));
		assertEquals(1, ds.getClases().size());
	}
	
	@Test
	void testGetDatosUsuarioProfesor() {
		
		//preparar datos
		try {
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
			ici.registrarClase("nomAct", "nomInst", "nickProf", new DtClase("nomClase", new Date(2000, 5, 5, 12, 0), 3, 1, new Date(2000, 2, 2, 0, 0), "urlClase", new byte[] {0}, "nomActividad", "nombProfesor", "videoUrl", "", 0, new Date(2000, 6, 6, 12, 0), 0, null, false));
		} catch (UsuarioRepetidoException | ClaseRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		DtUsuario du = icu.getDatosUsuario("nickProf");
		
		//verificar
		assertEquals("nickProf", du.getNickname());
		assertEquals("nomProf", du.getNombre());
		assertEquals("apeProf", du.getApellido());
		assertEquals("prof@prof.com", du.getEmail());
		assertEquals(2000, du.getFechaDeNacimiento().getYear());
		assertEquals(0, du.getFechaDeNacimiento().getMonth());
		assertEquals(13, du.getFechaDeNacimiento().getDate());
		DtProfesor dp = (DtProfesor) du;
		assertEquals("nomInst", dp.getInstitucion());
		assertEquals("bio", dp.getBiografia());
		assertEquals("descProf", dp.getDescripcion());
		assertEquals("urlProf", dp.getUrl());
		String key = dp.getClases().keySet().iterator().next();
		assertEquals("nomClase", key);
		assertEquals("nomAct", dp.getClases().get(key));
		assertEquals(1, dp.getClases().size());
	}

	@Test
	void testEditarDatosUsuarioSocio() {
		//preparar datos
		try {
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(1990, 4, 25), "contra", new byte[] {0}, null, null, null, null, null, null));
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		DtUsuario dui = icu.getDatosUsuario("nickSocio");
		dui.setNombre("nomSocio2");
		dui.setApellido("apeSocio2");
		dui.setFechaDeNacimiento(new Date(2000, 8, 30));
		icu.editarDatosUsuario(dui);
		
		
		//verificar
		DtUsuario duf = icu.getDatosUsuario("nickSocio");
		assertEquals("nickSocio", duf.getNickname());
		assertEquals("nomSocio2", duf.getNombre());
		assertEquals("apeSocio2", duf.getApellido());
		assertEquals("socio@socio.com", duf.getEmail());
		assertEquals(2000, duf.getFechaDeNacimiento().getYear());
		assertEquals(8, duf.getFechaDeNacimiento().getMonth());
		assertEquals(30, duf.getFechaDeNacimiento().getDate());
		DtSocio ds = (DtSocio) duf;
		assertEquals(0, ds.getClases().size());
	}
	
	@Test
	void testEditarDatosUsuarioProfesor() {
		//preparar datos
		try {
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		DtUsuario dui = icu.getDatosUsuario("nickProf");
		dui.setNombre("nomProf2");
		dui.setApellido("apeProf2");
		dui.setFechaDeNacimiento(new Date(2000, 8, 30));
		DtProfesor dpi = (DtProfesor) dui;
		dpi.setBiografia("bio2");
		dpi.setDescripcion("descProf2");
		dpi.setUrl("urlProf2");
		icu.editarDatosUsuario(dui);
		
		
		//verificar
		DtUsuario duf = icu.getDatosUsuario("nickProf");
		assertEquals("nickProf", duf.getNickname());
		assertEquals("nomProf2", duf.getNombre());
		assertEquals("apeProf2", duf.getApellido());
		assertEquals("prof@prof.com", duf.getEmail());
		assertEquals(2000, duf.getFechaDeNacimiento().getYear());
		assertEquals(8, duf.getFechaDeNacimiento().getMonth());
		assertEquals(30, duf.getFechaDeNacimiento().getDate());
		DtProfesor dpf = (DtProfesor) duf;
		assertEquals(0, dpf.getClases().size());
		assertEquals("nomInst", dpf.getInstitucion());
		assertEquals("bio2", dpf.getBiografia());
		assertEquals("descProf2", dpf.getDescripcion());
		assertEquals("urlProf2", dpf.getUrl());
	}

	@Test
	void testGetSocios() {
		
		//preparar datos
		try {
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(1990, 4, 25), "contra", new byte[] {0}, null, null, null, null, null, null));
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> socios = icu.getSocios();
		
		//verificar
		assertEquals(1, socios.size());
		assertTrue(socios.contains("nickSocio"));
	}

	@Test
	void testGetCuponerasDisponibles() {
		
		//preparar datos
		try {
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(1990, 4, 25), "contra", new byte[] {0}, null, null, null, null, null, null));
			icm.registrarCuponera("nomCup", "descCup", new Date(121, 1, 1), new Date(121, 11, 11), 0.20f, new Date(121, 0, 1), new byte[] {0});
			ici.aceptarActividad("nomAct", true);
			icm.agregarActividadACuponera("nomInst", "nomAct", "nomCup", 3);
			icu.comprarCuponera("nickSocio", "nomCup", new Date(121, 2, 2));
		} catch (UsuarioRepetidoException | CuponeraRepetidaException | CuponeraYaCompradaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		Set<String> cupsDisp = icu.getCuponerasDisponibles("nickSocio", "nomAct");
		
		//verificar
		assertEquals(1, cupsDisp.size());
		assertTrue(cupsDisp.contains("nomCup"));
	}

	@Test
	void testRegistrarSocioAClase() {
		
		//preparar datos
		try {
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(1990, 4, 25), "contra", new byte[] {0}, null, null, null, null, null, null));
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
			ici.registrarClase("nomAct", "nomInst", "nickProf", new DtClase("nomClase", new Date(2000, 5, 5, 12, 0), 3, 1, null, "urlClase", new byte[] {0}, "nomAct", "nickProf", "videoURL", "descPremio", 0, null, 0, null, false));
//																				0				1					 2  3 	4	    	5       6        7     8 	         9             10      11  12  13   14                                                
		} catch (UsuarioRepetidoException | ClaseRepetidaException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		try {
			icu.registrarSocioAClase("nickSocio", null, new Date(2000, 3, 3), "nomClase", "nomAct");
		} catch (ClaseLlenaException | SocioYaRegistradoException e) {
			e.printStackTrace();
		}
		
		//verificar
		DtUsuario du = icu.getDatosUsuario("nickSocio");
		DtSocio ds = (DtSocio) du;
		assertTrue(ds.getClases().contains("nomClase"));
		assertEquals(1, ds.getClases().size());
	}
	
	@Test
	void testIniciarSesion() {
		
		//preparar datos
		try {
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(1990, 4, 25), "contra", new byte[] {0}, null, null, null, null, null, null));
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		boolean falsa = icu.iniciarSesion("nickSocio", "contraMal");
		boolean correcta = icu.iniciarSesion("nickSocio", "contra");
		boolean userNoExiste = icu.iniciarSesion("nickSocioMal", "contra");
		
		//verificar
		assertEquals(false, falsa);
		assertEquals(true, correcta);
		assertEquals(false, userNoExiste);
	}
	
	@Test
	void testSeguirUsuario() {
		
		//preparar datos
		try {
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(1990, 4, 25), "contra", new byte[] {0}, null, null, null, null, null, null));
			icu.registrarUsuario(new DtSocio("nickSocio2", "nomSocio", "apeSocio", "socio@socio2.com", new Date(1990, 4, 25), "contra", new byte[] {0}, null, null, null, null, null, null));
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		icu.seguirUsuario("nickSocio", "nickSocio2");
		icu.seguirUsuario("nickProf", "nickSocio2");
		icu.seguirUsuario("nickSocio2", "nickSocio");
		icu.seguirUsuario("nickSocio2", "nickProf");
		
		//verificar
		DtUsuario duSocio = icu.getDatosUsuario("nickSocio");
		DtUsuario duSocio2 = icu.getDatosUsuario("nickSocio2");
		DtUsuario duProf = icu.getDatosUsuario("nickProf");
		assertEquals(1, duSocio.getSeguidores().size());
		assertEquals(1, duSocio.getSeguidos().size());
		assertEquals(2, duSocio2.getSeguidores().size());
		assertEquals(2, duSocio2.getSeguidos().size());
		assertEquals(1, duProf.getSeguidores().size());
		assertEquals(1, duProf.getSeguidos().size());
		assertTrue(duSocio.getSeguidores().contains("nickSocio2"));
		assertTrue(duSocio2.getSeguidores().contains("nickSocio"));
		assertTrue(duSocio2.getSeguidores().contains("nickProf"));
		assertTrue(duProf.getSeguidores().contains("nickSocio2"));
		assertTrue(duSocio.getSeguidos().contains("nickSocio2"));
		assertTrue(duProf.getSeguidos().contains("nickSocio2"));
		assertTrue(duSocio2.getSeguidos().contains("nickSocio"));
		assertTrue(duSocio2.getSeguidos().contains("nickProf"));
	}
	
	@Test
	void testDejarDeSeguirUsuario() {
		
		//preparar datos
		try {
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(1990, 4, 25), "contra", new byte[] {0}, null, null, null, null, null, null));
			icu.registrarUsuario(new DtSocio("nickSocio2", "nomSocio", "apeSocio", "socio@socio2.com", new Date(1990, 4, 25), "contra", new byte[] {0}, null, null, null, null, null, null));
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
			icu.seguirUsuario("nickSocio", "nickSocio2");
			icu.seguirUsuario("nickProf", "nickSocio2");
			icu.seguirUsuario("nickSocio2", "nickSocio");
			icu.seguirUsuario("nickSocio2", "nickProf");
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		icu.dejarSeguirUsuario("nickSocio2", "nickSocio");
		icu.dejarSeguirUsuario("nickProf", "nickSocio2");
		
		//verificar
		DtUsuario duSocio = icu.getDatosUsuario("nickSocio");
		DtUsuario duSocio2 = icu.getDatosUsuario("nickSocio2");
		DtUsuario duProf = icu.getDatosUsuario("nickProf");
		assertEquals(0, duSocio.getSeguidores().size());
		assertEquals(1, duSocio.getSeguidos().size());
		assertEquals(1, duSocio2.getSeguidores().size());
		assertEquals(1, duSocio2.getSeguidos().size());
		assertEquals(1, duProf.getSeguidores().size());
		assertEquals(0, duProf.getSeguidos().size());
		assertTrue(duSocio2.getSeguidores().contains("nickSocio"));
		assertTrue(duProf.getSeguidores().contains("nickSocio2"));
		assertTrue(duSocio.getSeguidos().contains("nickSocio2"));
		assertTrue(duSocio2.getSeguidos().contains("nickProf"));
	}

	@Test
	void testAgregarActividadFavorita() {
		
		//preparar datos
		try {
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(1990, 4, 25), "contra", new byte[] {0}, null, null, null, null, null, null));
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		icu.cambiarActividadFavorita("nickSocio", "nomAct");
		Set<String> favs = ((DtSocio) icu.getDatosUsuario("nickSocio")).getFavoritas();

		//verificar
		assertEquals(1, favs.size());
		assertTrue(favs.contains("nomAct"));
	}

	@Test
	void testSacarActividadFavorita() {
		
		//preparar datos
		try {
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(1990, 4, 25), "contra", new byte[] {0}, null, null, null, null, null, null));
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
			icu.cambiarActividadFavorita("nickSocio", "nomAct");
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		
		//ejecutar prueba
		icu.cambiarActividadFavorita("nickSocio", "nomAct");
		Set<String> favs = ((DtSocio) icu.getDatosUsuario("nickSocio")).getFavoritas();

		//verificar
		assertEquals(0, favs.size());
		assertFalse(favs.contains("nomAct"));
	}

	@Test
	void testValorarClase() {
		//preparar datos
		try {
			ici.aceptarActividad("nomAct", true);
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(2000,1,1,12,0), "contra", new byte[] {0}, null, null, null, null, null, null));
			ici.registrarClase("nomAct", "nomInst", "nickProf", new DtClase("nomClase", new Date(2000, 12, 31, 23, 59), 3, 1, new Date(2000, 12, 1, 12, 30), "urlClase", new byte[] {0}, "nomAct", "nickProf", "videoURL", "descPremio", 3, null, 0, null, false));
			icu.registrarSocioAClase("nickSocio", null, new Date(2000,12,2,9,20), "nomClase", "nomAct", "nomInst");
		} catch (ClaseRepetidaException | SocioYaRegistradoException | ClaseLlenaException | UsuarioRepetidoException e) {
			e.printStackTrace();
		}
		//ejecutar prueba
		icu.valorarClase("nickSocio", "nomClase", 5);
		Map<String, Integer> valoracionesSocio = ((DtSocio) icu.getDatosUsuario("nickSocio")).getValoracionesClases();
		DtValoracion valoracionesProf = ((DtProfesor) icu.getDatosUsuario("nickProf")).getValoraciones();

		//verificar
		assertEquals(1, valoracionesSocio.size());
		assertTrue(valoracionesSocio.keySet().contains("nomClase"));
		assertEquals(5, valoracionesSocio.get("nomClase"));

		assertEquals(0, valoracionesProf.getCantValores()[0]);
		assertEquals(0, valoracionesProf.getCantValores()[1]);
		assertEquals(0, valoracionesProf.getCantValores()[2]);
		assertEquals(0, valoracionesProf.getCantValores()[3]);
		assertEquals(0, valoracionesProf.getCantValores()[4]);
		assertEquals(1, valoracionesProf.getCantValores()[5]);
		assertTrue(5.0 == valoracionesProf.getValorPromedio());
	}
	@Test
	void testExisteNicknameSocio() {
		//preparar datos
		try {
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(2000,1,1,12,0), "contra", new byte[] {0}, null, null, null, null, null, null));
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}

		//ejecutar prueba
		boolean res = icu.existeNickname("nickSocio");

		//verificar
		assertTrue(res);
	}

	@Test
	void testExisteNicknameProf() {
		//preparar datos
		try {
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}

		//ejecutar prueba
		boolean res = icu.existeNickname("nickProf");

		//verificar
		assertTrue(res);

	}
	@Test
	void testNoExisteNickname() {
		//preparar datos

		//ejecutar prueba
		boolean res = icu.existeNickname("nickUsuario");

		//verificar
		assertFalse(res);
	}

	@Test
	void testExisteEmailSocio() {
		//preparar datos
		try {
			icu.registrarUsuario(new DtSocio("nickSocio", "nomSocio", "apeSocio", "socio@socio.com", new Date(2000,1,1,12,0), "contra", new byte[] {0}, null, null, null, null, null, null));
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}

		//ejecutar prueba
		boolean res = icu.existeEmail("socio@socio.com");

		//verificar
		assertTrue(res);
	}

	@Test
	void testExisteEmailProf() {
		//preparar datos
		try {
			icu.registrarUsuario(new DtProfesor("nickProf", "nomProf", "apeProf", "prof@prof.com", new Date(2000, 0, 13), "contraProf", new byte[] {0}, null, null, "descProf", "nomInst", "urlProf", "bio", null, null, null));
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		}

		//ejecutar prueba
		boolean res = icu.existeEmail("prof@prof.com");

		//verificar
		assertTrue(res);
	}

	@Test
	void testNoExisteEmail() {
		//preparar datos

		//ejecutar prueba
		boolean res = icu.existeEmail("usr@usr.com");

		//verificar
		assertFalse(res);
	}
}
