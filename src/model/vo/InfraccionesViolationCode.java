package model.vo;

import model.data_structures.IQueue;

/**
 * Organiza las infracciones por el el código de la infracción y muestra las estadisticas
 * de las respectivas infracciones que poseen el código en mención.
 */

public class InfraccionesViolationCode extends Infracciones{
	
	/**
	 * Codigo de la infracción por las que se van a agrupar las infracciones
	 */
	
	private String violationCode;	
		
	/**
	 * Instantiates a new infracciones violation code.
	 * @param lista Lista de infracciones que poseen el mismo ViolationCode
	 */
	
	public InfraccionesViolationCode(String violationCode, IQueue<VOMovingViolations> lista) {
		super(lista);
		this.violationCode = violationCode;
	}

	/**
	 * Gets the violation code.
	 *
	 * @return the violationCode
	 */
	
	public String getViolationCode() {
		return violationCode;
	}	
}
