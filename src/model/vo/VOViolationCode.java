package model.vo;

public class VOViolationCode implements Comparable<VOViolationCode>{

	// Atributos ---------------------------------------------------------------------
	
	/**
	 * Representa el código del tipo de infracción
	 */
	private String violationCode;
	
	/**
	 * Representa la cantidad de infracciones. 
	 */
	private int cantidad;
	
	// Constructor -------------------------------------------------------------------
	
	public VOViolationCode(String pViolationCode, int pCantidad) {
		
		violationCode = pViolationCode;
		cantidad = pCantidad;
	}
	
	/**
	 * Retrona el código del tipo de infracción
	 * @return
	 */
	public String getViolationCode() {
		
		return violationCode;
	}
	
	/**
	 * @return la cantidad de infracciones que son de este tipo
	 */
	public int getCantidad() {
		
		return cantidad;
	}
	
	public String toString() {
		
		return violationCode;
	}
	
	public int compareTo(VOViolationCode pViolationCode) {
		
		return cantidad - pViolationCode.getCantidad();
	}
}
