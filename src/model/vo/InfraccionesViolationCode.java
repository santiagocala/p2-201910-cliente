package model.vo;

import model.data_structures.IQueue;

/**
 * Organiza las infracciones por el el código de la infracción y muestra las estadisticas
 * de las respectivas infracciones que poseen el código en mención.
 */

public class InfraccionesViolationCode extends EstadisticaInfracciones implements Comparable<InfraccionesViolationCode>
{
	
	@Override
	public String toString() {
		return "InfraccionesViolationCode [violationCode=" + violationCode + ",\n totalInfracciones=" + totalInfracciones
				+ ",\n porcentajeAccidentes=" + porcentajeAccidentes + ",\n porcentajeNoAccidentes="
				+ porcentajeNoAccidentes + ",\n valorTotal=" + valorTotal + "]\n\n";
	}

	/**
	 * Codigo de la infracción por las que se van a agrupar las infracciones
	 */
	
	private String violationCode;	
		
	/**
	 * Instantiates a new infracciones violation code.
	 * @param lista Lista de infracciones que poseen el mismo ViolationCode
	 */
	
	public InfraccionesViolationCode(String violationCodeP, IQueue<VOMovingViolations> lista) {
		super(lista);
		this.violationCode = violationCodeP;
	}

	/**
	 * Gets the violation code.
	 *
	 * @return the violationCode
	 */
	
	public String getViolationCode() {
		return violationCode;
	}	
	@Override
	public int compareTo(InfraccionesViolationCode that) {
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
