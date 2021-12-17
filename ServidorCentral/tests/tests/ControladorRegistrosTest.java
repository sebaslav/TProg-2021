package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import excepciones.*;
import logica.*;

import org.junit.jupiter.api.Test;

class ControladorRegistrosTest {
	
	static IControladorUsuarios icu;
	static IControladorInstituciones ici;
	static IControladorCuponeras icm;
	static IControladorRegistros icr;
	
	@BeforeEach
	void init() {
		ici = Fabrica.getInstance().getControladorInstituciones();
		icu = Fabrica.getInstance().getControladorUsuario();
		icm = Fabrica.getInstance().getControladorCuponeras();
		icr = Fabrica.getInstance().getControladorRegistros();
	}
	@AfterEach
	void restart() {
		//Elimina registros 
		//Reinicia el estado del sistema para que los tests sean independientes
		ManejadorRegistros mreg = ManejadorRegistros.getInstance();
		mreg.getRegistros().clear();
	}
	@Test
	void testAgregarRegistro() {
		//preparar datos

		//ejecutar prueba
		icr.agregarRegistro("192.168.1.1", "www.entrenamos.uy", "Firefox", "Windows", new Date(2000,1,1));
		List<DtRegistro> registros = icr.listarRegistros(); 

		//verificar
		assertEquals(1, registros.size());
		assertEquals("192.168.1.1", registros.iterator().next().getIp());
		assertEquals("www.entrenamos.uy", registros.iterator().next().getUrl());
		assertEquals("Windows", registros.iterator().next().getSisOp());
		assertEquals("Firefox", registros.iterator().next().getBrowser());
		assertEquals(new Date(2000,1,1), registros.iterator().next().getFecha());
	}

}
