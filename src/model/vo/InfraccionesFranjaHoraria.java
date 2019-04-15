package model.vo;


import java.time.LocalTime;

import model.data_structures.IQueue;
import model.data_structures.Queue;

/**
 * Agrupa las infracciones por un rango de fecha/hora segun sea el caso
 */

public class InfraccionesFranjaHoraria extends EstadisticaInfracciones implements Comparable<InfraccionesFranjaHoraria> {	

	@Override
	public String toString() {
		return "InfraccionesFranjaHoraria [hora_Inicial=" + hora_Inicial + ",\n hora_Final=" + hora_Final
				+ ",\n totalInfracciones=" + totalInfracciones + ",\n porcentajeAccidentes=" + porcentajeAccidentes
				+ ",\n porcentajeNoAccidentes=" + porcentajeNoAccidentes + ",\n valorTotal=" + valorTotal + "]\n\n";
	}

	/** The franja horaria inicial. */
	
	private LocalTime hora_Inicial;
	
	/** The franja horaria final. */
	
	private LocalTime hora_Final;
	private Queue<VOMovingViolations> lista;
	
	/**
	 * Instantiates a new franja horaria.
	 * @param lista Cola con las infraciones que pertenecen al rango de horas establecido
	 */
	
	public InfraccionesFranjaHoraria(LocalTime fInicial, LocalTime fFinal, IQueue<VOMovingViolations> lista) {
		super(lista);
		this.lista = (Queue<VOMovingViolations>) lista;
		this.hora_Inicial = fInicial;
		this.hora_Final = fFinal;		
	}

	public void agregarALista(VOMovingViolations nueva)
	{
		lista.enqueue(nueva);
	}
	/**
	 * @return the fechaInicial
	 */
	public LocalTime getFranjaInicial() {
		return hora_Inicial;
	}

	/**
	 * @return the fechaFinal
	 */
	public LocalTime getFranjaFinal() {
		return hora_Final;
	}

	@Override
	public int compareTo(InfraccionesFranjaHoraria o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
