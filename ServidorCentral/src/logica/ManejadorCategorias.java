package logica;

import java.util.HashMap;
import java.util.Map;

public class ManejadorCategorias {
	
	private Map<String, Categoria> categorias;
    private static ManejadorCategorias instancia = null;

    private ManejadorCategorias() {
    	categorias = new HashMap<String, Categoria>();
    }

    public static ManejadorCategorias getInstance() {
        if (instancia == null)
            instancia = new ManejadorCategorias();
        return instancia;
    }
    
    public void add(Categoria cat) {
    	categorias.put(cat.getNombre(), cat);
    }
    
    public boolean exists(String cat) {
    	return categorias.containsKey(cat);
    }
    
    public Map<String, Categoria> getCategorias() {
    	return categorias;
    }
    
    public Categoria find(String cat) {
    	return categorias.get(cat);
    }
}
