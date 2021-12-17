package logica;

public class Fabrica {

    private static Fabrica instancia;
    private Fabrica() {};

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IControladorUsuarios getControladorUsuario() {
        return new ControladorUsuarios();
    }
    
    public IControladorInstituciones getControladorInstituciones() {
    	return new ControladorInstituciones();
    }
    
    public IControladorCuponeras getControladorCuponeras() {
    	return new ControladorCuponeras();
    }
    
    public IControladorRegistros getControladorRegistros() {
    	return new ControladorRegistros();
    }

}
