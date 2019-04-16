package model.vo;

import java.time.LocalDateTime;

import model.data_structures.IQueue;

public class InfraccionesFechaHora extends EstadisticaInfracciones implements Comparable<InfraccionesFechaHora> {

	@Override
	public String toString() {
		return "InfraccionesFechaHora [fecha_hora_inicial=" + fecha_hora_inicial + ",\n totalInfracciones=" + totalInfracciones + ",\n porcentajeAccidentes="
				+ porcentajeAccidentes + ",\n porcentajeNoAccidentes=" + porcentajeNoAccidentes + ",\n valorTotal="
				+ valorTotal + "]\n\n";
	}

	private LocalDateTime fecha_hora_inicial;
	
	
	public InfraccionesFechaHora(LocalDateTime pFechaHoraIni, IQueue<VOMovingViolations> lista) {
		super(lista);
		fecha_hora_inicial = pFechaHoraIni; 
	}

	@Override
	public int compareTo(InfraccionesFechaHora dato) {
		
		return totalInfracciones - dato.totalInfracciones;
	}

}
