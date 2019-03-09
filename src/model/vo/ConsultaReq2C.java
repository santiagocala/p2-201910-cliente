package model.vo;

import model.data_structures.IQueue;

/**
 * Brinda la información del requerimiento 2C
 */

public class ConsultaReq2C {
	
	/**
	 * Permite agrupar las infracciones pertenecientes al rango de fechas/hora y obtener estadisticas.
	 */
	
	private Infracciones infRango;
	
	/**
	 * Agrupa las infracciones del rango de fecha por ViolationCode y muestra sus estadisticas
	 */
	
	private IQueue<InfraccionesViolationCode> infViolationCode;

	/**
	 * Instantiates a new consulta req 2 C.
	 *
	 * @param infRango the inf rango
	 * @param infViolationCode the inf violation code
	 */
	
	public ConsultaReq2C(Infracciones infRango, IQueue<InfraccionesViolationCode> infViolationCode) {		
		this.infRango = infRango;
		this.infViolationCode = infViolationCode;
	}

	/**
	 * @return the infRango
	 */
	public Infracciones getInfRango() {
		return infRango;
	}

	/**
	 * @param infRango the infRango to set
	 */
	public void setInfRango(Infracciones infRango) {
		this.infRango = infRango;
	}

	/**
	 * @return the infViolationCode
	 */
	public IQueue<InfraccionesViolationCode> getInfViolationCode() {
		return infViolationCode;
	}

	/**
	 * @param infViolationCode the infViolationCode to set
	 */
	public void setInfViolationCode(IQueue<InfraccionesViolationCode> infViolationCode) {
		this.infViolationCode = infViolationCode;
	}
}
