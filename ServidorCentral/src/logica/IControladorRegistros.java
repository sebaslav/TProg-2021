package logica;

import java.util.Date;
import java.util.List;

public interface IControladorRegistros {
	
	public abstract void agregarRegistro(String dirIp, String url, String browser, String sisOp, Date fecha);
	
	public abstract List<DtRegistro> listarRegistros();

}
