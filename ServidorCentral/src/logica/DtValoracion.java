package logica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtValoracion {

	private int[] cantValores; // cantidad de valoraciones
	private float valorPromedio; // promedio de valoraciones
	
	public DtValoracion() {}
	
	/**
	 * Recibe un arreglo de enteros de tamaño 6 (celdas 0, 1, ..., 5) donde la celda
	 * i corresponde a la cantidad de valoraciones con puntaje i
	 * 
	 * @param valores arreglo de tamaño 5+1 con las distintas cantidades de valoraciones segun puntaje.
	 */
	public DtValoracion(int[] val) {
		cantValores = val;
		int cantValoraciones = val[1] + val[2] + val[3] + val[4] + val[5];
		if (cantValoraciones > 0) {
			valorPromedio = (float) (((float) val[1] + 2*(float) val[2] + 3*(float) val[3] + 4*(float) val[4] + 5*(float) val[5])/(float) cantValoraciones);
		} else {
			valorPromedio = 0;
		}
		
	}

	/**
	 * Devuelve un arreglo con las distintas cantidades de valoraciones segun puntaje.
	 * 
	 * La celda 1 contiene cuantas valoraciones de 1 punto tiene.
	 * La celda 2 contiene cuantas valoraciones de 2 puntos tiene.
	 * etc.
	 * La celda 0 se ignora.
	 * 
	 * @return valores del 1 al 5
	 */
	public int[] getCantValores() {
		return this.cantValores;
	}
	
	public void setCantValores(int[] cantValores) {
		this.cantValores = cantValores;
	}

	/**
	 * @return the valorPromedio
	 */
	public float getValorPromedio() {
		return this.valorPromedio;
	}
	
	public void setValorPromedio(float valorPromedio) {
		this.valorPromedio = valorPromedio;
	}
	
	
}
