package model.vo;

import java.time.LocalDate;

import model.data_structures.IQueue;

/**  
 * Agrupa las infracciones por fecha 
 */

public class InfraccionesFecha extends EstadisticaInfracciones {

	@Override
	public String toString() {
		return "InfraccionesFecha [fecha=" + fecha + ", totalInfracciones=" + totalInfracciones
				+ ", porcentajeAccidentes=" + porcentajeAccidentes + ", porcentajeNoAccidentes="
				+ porcentajeNoAccidentes + ", valorTotal=" + valorTotal + "]";
	}
	
	private LocalDate fecha;
	/**
	 * Instantiates a new infracciones fecha.
	 * @param lista the lista
	 */
	
	public InfraccionesFecha( IQueue<VOMovingViolations> lista, LocalDate pFecha )
	{
		super(lista);
		fecha = pFecha;
	}


}
