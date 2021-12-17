package logica;

import java.util.Date;

import excepciones.PremioYaGanadoException;

public class Inscripcion {
	
	private Date fechaInscripcion;
	private Clase claseAsociada;
	private Cuponera cuponera = null;
	private boolean tienePremio = false;
	private int valoracion = 0; //Inicialmente se le asigna un valor invalido

	public Inscripcion(Date fechaInscripcion, Clase claseAsociada){
		this.fechaInscripcion = fechaInscripcion;
		this.claseAsociada = claseAsociada;		
	}
	
	public String getNombreClase() {
		return claseAsociada.getNombre();
	}

	public void setCuponera(Cuponera cuponera){
		this.cuponera = cuponera; 
	}
	
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	
	public Cuponera getCuponera() {
		return cuponera;
	}
	
	/**
	 * @return valoracion asignada (entre 1 y 5). Devuelve 0 en caso de no haber puntuado la clase.
	 */
	public int getValoracion() {
		return valoracion;
	}

	/**
	 * Cambia el valor de la valoracion (je) de la Clase asociada.
	 * PRECOND: El valor tiene que estar entre 0 y 5 inclusives.
	 * 
	 * @param valor Nuevo puntaje para la clase (entero entre 0 y 5 inclusives)
	 */
	public void setValoracion(int valor) {
		valoracion = valor > 0? valor < 5? valor : 5 : 0; // valor entre 0 y 5 sino puede causar excepcion ArrayOutOfBounds
	}

	public boolean tienePremio() {
		return this.tienePremio;
	}
	
	public DtPremio getDtPremio() {
		DtClase datosClase = claseAsociada.getDatos();
		return new DtPremio(datosClase.getFechaSorteo(), datosClase.getDescPremio(), datosClase.getNombre(), datosClase.getActividad());		
	}
	public void ganarPremio() throws PremioYaGanadoException {
		if (!tienePremio)
			tienePremio = true;
		else
			throw new PremioYaGanadoException("Este premio ya fue ganado por el socio");		
	}
}
