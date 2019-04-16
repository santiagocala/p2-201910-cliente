package model.vo;


import java.time.LocalDateTime;
import java.time.LocalTime;

import model.data_structures.IQueue;
import model.data_structures.Queue;

/**
 * Agrupa las infracciones por un rango de fecha/hora segun sea el caso
 */

public class InfraccionesFranjaHoraria extends EstadisticaInfracciones implements Comparable<InfraccionesFranjaHoraria> {	

	@Override
	public String toString() {
		super.calcularDatos();
		return "InfraccionesFranjaHoraria [hora_Inicial=" + hora_Inicial + ",\n hora_Final=" + hora_Final
				+ ",\n totalInfracciones=" + totalInfracciones + ",\n porcentajeAccidentes=" + porcentajeAccidentes
				+ ",\n porcentajeNoAccidentes=" + porcentajeNoAccidentes + ",\n valorTotal=" + valorTotal + "]\n\n";
	}

	/** The franja horaria inicial. */
	
	private LocalDateTime hora_Inicial;
	
	/** The franja horaria final. */
	
	private LocalDateTime hora_Final;
	private Queue<VOMovingViolations> lista;
	
	/**
	 * Instantiates a new franja horaria.
	 * @param lista Cola con las infraciones que pertenecen al rango de horas establecido
	 */
	
	public InfraccionesFranjaHoraria(LocalDateTime fInicial, LocalDateTime fFinal, IQueue<VOMovingViolations> lista) {
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
	public LocalDateTime getFranjaInicial() {
		return hora_Inicial;
	}

	/**
	 * @return the fechaFinal
	 */
	public LocalDateTime getFranjaFinal() {
		return hora_Final;
	}

	@Override
	public int compareTo(InfraccionesFranjaHoraria that) {
		// TODO Auto-generated method stub
		
		int resp=2;
		if(this==null || that==null)
		{
			return resp;
		}
		
		//Por numero de infracciones
		if(this.totalInfracciones<that.totalInfracciones)
		{
			resp=-1;
		}
		else if(this.totalInfracciones>that.totalInfracciones)
		{
			resp=1;
		}
		else
		{
			resp=0;
		}
		return resp;
	}
	
}
