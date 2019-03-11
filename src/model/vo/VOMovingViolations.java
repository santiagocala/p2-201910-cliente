package model.vo;

/**
 * Representation of a Trip object
 */
public class VOMovingViolations {

	
	@Override
	public String toString() {
		return "VOMovingViolations [objectId()=" + objectId() + ", getLocation()=" + getLocation()
				+ ", getTicketIssueDate()=" + getTicketIssueDate() + ", getTotalPaid()=" + getTotalPaid()
				+ ", getAccidentIndicator()=" + getAccidentIndicator() + ", getViolationDescription()="
				+ getViolationDescription() + ", getStreetSegId()=" + getStreetSegId() + ", getAddressId()="
				+ getAddressId() + "]";
	}


	/**
	 * @return id - Identificador único de la infracción
	 */
	public int objectId() {
		// TODO Auto-generated method stub
		return 0;
	}	
	
	
	/**
	 * @return location - Dirección en formato de texto.
	 */
	public String getLocation() {
		// TODO Auto-generated method stub
		return "";
	}

	/**
	 * @return date - Fecha cuando se puso la infracción .
	 */
	public String getTicketIssueDate() {
		// TODO Auto-generated method stub
		return "";
	}
	
	/**
	 * @return totalPaid - Cuanto dinero efectivamente pagó el que recibió la infracción en USD.
	 */
	public int getTotalPaid() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return accidentIndicator - Si hubo un accidente o no.
	 */
	public String  getAccidentIndicator() {
		// TODO Auto-generated method stub
		return "";
	}
		
	/**
	 * @return description - Descripción textual de la infracción.
	 */
	public String  getViolationDescription() {
		// TODO Auto-generated method stub
		return "";
	}
	
	public String getStreetSegId() {
		return "";
	}
	
	public String getAddressId() {
		return "";
	}
}
