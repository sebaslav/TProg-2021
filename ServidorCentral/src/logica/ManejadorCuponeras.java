package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ManejadorCuponeras {
	
	private Map<String, EspCuponera> cuponeras;
    private static ManejadorCuponeras instancia = null;

    private ManejadorCuponeras() {
        cuponeras = new HashMap<String, EspCuponera>();
    }

    public static ManejadorCuponeras getInstance() {
        if (instancia == null)
            instancia = new ManejadorCuponeras();
        return instancia;
    }
    
    public void add(EspCuponera cup) {
    	cuponeras.put(cup.getNombre(), cup);
    }
    
    public boolean exists(String cup) {
    	return cuponeras.containsKey(cup);
    }
    
    public Set<String> getNombresEspCuponeras() {
    	Set<String> res = new HashSet<String>();
    	for (String cup : cuponeras.keySet())
    		res.add(cup);
    	return res;
    }
    
    public Map<String, EspCuponera> getEspCuponeras() {
    	return cuponeras;
    }
    
    public EspCuponera find(String cup) {
    	return cuponeras.get(cup);
    }
    
    public Set<String> getNombresEspCuponerasNoCompradas() {
    	Set<String> res = new HashSet<String>();
    	for (EspCuponera esp : cuponeras.values()) {
    		if (!esp.getFueComprada())
    			res.add(esp.getNombre());
    	}
    	return res;
    }
}
