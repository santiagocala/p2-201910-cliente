package model.vo;

import java.util.Date;

import model.data_structures.IQueue;

/**
 * Agrupa las infracciones por un rango de fecha/hora segun sea el caso
 */

public class FranjaHoraria extends Infracciones {	

	/** The fecha inicial. */
	
	private Date fechaInicial;
	
	/** The fecha final. */
	
	private Date fechaFinal;
	
	/**
	 * Instantiates a new franja horaria.
	 * @param lista Cola con las infraciones que pertenecen al rango de horas establecido
	 */
	
	public FranjaHoraria(Date fInicial, Date fFinal, IQueue<VOMovingViolations> lista) {
		super(lista);
		this.fechaInicial = fInicial;
		this.fechaFinal = fFinal;		
	}

	/**
	 * @return the fechaInicial
	 */
	public Date getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * @return the fechaFinal
	 */
	public Date getFechaFinal() {
		return fechaFinal;
	}
}
