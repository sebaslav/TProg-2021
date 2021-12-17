package logica;

import java.util.Date;
import java.util.List;

public class ControladorRegistros implements IControladorRegistros {
	
	public ControladorRegistros() {
	}
	
	public void agregarRegistro(String dirIp, String url, String browser, String sisOp, Date fecha) {
		ManejadorRegistros mreg = ManejadorRegistros.getInstance();
		mreg.add(new DtRegistro(dirIp, url, browser, sisOp, fecha));
	}
	
	public List<DtRegistro> listarRegistros() {
		ManejadorRegistros mreg = ManejadorRegistros.getInstance();
		return mreg.getRegistros();
	}
	
}
