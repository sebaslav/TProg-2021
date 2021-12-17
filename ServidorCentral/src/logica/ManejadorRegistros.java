package logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ManejadorRegistros {
	private List<DtRegistro> registros;
	private static ManejadorRegistros instancia = null;
	
	private ManejadorRegistros() {
		registros = new ArrayList<>();
	}
	
	public static ManejadorRegistros getInstance() {
		if (instancia == null) {
			instancia = new ManejadorRegistros();
		}
		return instancia;
	}
	
	public void add(DtRegistro reg) {
		registros.add(reg);
		if (registros.size() > 10000) {
			registros.remove(0);
		}
	}
	
	public List<DtRegistro> getRegistros() {
		while (registros.size() > 0 ) {
			Date fechaReg = registros.get(0).getFecha();
			Calendar cal= new GregorianCalendar();
			cal.setTime(fechaReg);
			cal.add(Calendar.DATE, 30);
			Date fechaV = cal.getTime();
			if (fechaV.before(new Date())) {
				registros.remove(0);
			} else {
				break;
			}
		}
		return new ArrayList<>(registros);
	}
}
