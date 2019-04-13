package model.vo;

import java.time.LocalDateTime;

/**
 * Representation of a Trip object
 */
public class VOMovingViolations {
	
	//---------------------------------------------------------------------------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------------------------------------------------------------------------
	
	// Identificador único de la infracción
	private int OBJECT_ID;
	
	// Dirección textual del lugar donde se cometió la infracción
	private String LOCATION;
	
	// Identificador del sitio donde se cometió la infracción
	private int ADRESS_ID;
	
	// Identificador de la calle donde se cometió la infracción
	private int STREETSEGID;
	
	// Coordenada x de donde se cometió la infracción.
	private double XCOORD;
	
	// Coordenada y de donde se cometió la infracción.
	private double YCOORD;
	
	// Total del costo de la multa 
	private int FINEAMT;
	
	// La cantidad que se ha pagado hasta el momento de la multa. 
	private int TOTALPAID;
	
	// Cuanto dinero extra tiene que pagar el comductor
	private int PENALTY1;
	
	// Indicador de si hubo accidente o no en la infracción. 
	private boolean ACCIDENTINDICATOR;
	
	// Hora y Fecha de cuándo se cometió la infracción. 
	private LocalDateTime TICKETISSUEDATE;
	
	// Código del tipo de infracción que se cometió. 
	private String VIOLATIONCODE;
	
	// Descripción textual del tipo de infracción. 
	private String VIOLATIONDESC;
	
	//---------------------------------------------------------------------------------------------------------------------------------------
	// Constructor
	//---------------------------------------------------------------------------------------------------------------------------------------
	
	public VOMovingViolations(int pObjectID, String pLocation, int pAdressID, int pStreetSegID, double pXCoord, double pYCoord, int pFineAmt, 
			int pTotalPaid, int pPentalty1, boolean pAccidentInidicator, LocalDateTime pTicketIssueDate, String pViolationCode, String pViolationDesc) {
		
		OBJECT_ID = pObjectID;
		LOCATION = pLocation;
		ADRESS_ID = pAdressID;
		STREETSEGID = pStreetSegID;
		XCOORD = pXCoord;
		YCOORD = pYCoord;
		FINEAMT = pFineAmt;
		TOTALPAID = pTotalPaid;
		PENALTY1 = pPentalty1;
		ACCIDENTINDICATOR = pAccidentInidicator;
		TICKETISSUEDATE = pTicketIssueDate;
		VIOLATIONCODE = pViolationCode;
		VIOLATIONDESC = pViolationDesc;
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------
	// Métodos
	//---------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * @return id - Identificador único de la infracción
	 */
	public int objectId() {
		return OBJECT_ID;
	}	
	
	/**
	 * @return location - Dirección en formato de texto.
	 */
	public String getLocation() {
		return LOCATION;
	}

	/**
	 * @return date - Fecha cuando se puso la infracción .
	 */
	public LocalDateTime getTicketIssueDate() {
		return TICKETISSUEDATE;
	}
	
	/**
	 * @return totalPaid - Cuanto dinero efectivamente pagó el que recibió la infracción en USD.
	 */
	public int getTotalPaid() {
		return TOTALPAID;
	}
	
	/**
	 * @return accidentIndicator - Si hubo un accidente o no.
	 */
	public boolean  getAccidentIndicator() {
		return ACCIDENTINDICATOR;
	}
		
	/**
	 * @return description - Descripción textual de la infracción.
	 */
	public String  getViolationDescription() {
		return VIOLATIONDESC;
	}
	
	/**
	 * @return streetsegid - identificador del segmento de calle
	 */
	public int getStreetSegId() {
		return STREETSEGID;
	}
	
	/**
	 * @return  adressID - identificidor de la dirección donde ocurrió la infracción. 
	 */
	public int getAddressId() {
		return ADRESS_ID;
	}
	
	/**
	 * @return violationcode 
	 */
	public String getViolationCode(){
		return VIOLATIONCODE;
	}
	
	/**
	 * @return xcoord - retorna la coordenada x de donde se cometió la infracción
	 */
	public double getXCoord() {
		return XCOORD;
	}
	
	/**
	 * @return ycoord - retorna la coordenada y de donde se cometió la infracción
	 */
	public double getYCoord() {
		return YCOORD;
	}
	
	/**
	 * @return fineamt - el monto del valor de la multa por la infracción. 
	 */
	public int getFineAmt() {
		return FINEAMT; 
	}
	
	/** 
	 * @return penalty1= el dinero extra que le toca pagar al que cometió la infracción. 
	 */
	public int getPenalty1() {
		return PENALTY1;
	}
	
	@Override
	public String toString() {
		return "VOMovingViolations [objectId()=" + objectId() + ",\n getLocation()=" + getLocation()
				+ ",\n getTicketIssueDate()=" + getTicketIssueDate() + ",\n getTotalPaid()=" + getTotalPaid()
				+ ",\n getAccidentIndicator()=" + getAccidentIndicator() + ",\n getViolationDescription()="
				+ getViolationDescription() + ",\n getStreetSegId()=" + getStreetSegId() + ",\n getAddressId()="
				+ getAddressId() + "]\n\n";
	}
}
