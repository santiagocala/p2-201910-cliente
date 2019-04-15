package model.vo;

import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * Representation of a Trip object
 */
public class VOMovingViolations implements Iterable<VOMovingViolations>, Comparable<VOMovingViolations> {


	private int objectId , streetSegId , addressId  ;
	private String location, violationDes, violationCode;
	private String ticketIssue;
	private double fineamt, totalPaid/*, penalty1, penalty2*/,x,y;
	private boolean accidentIndicator;
	
	public VOMovingViolations( int pObjectId, String pLocation, int pAddressId, int pStreetId, double pFine, double pTotal, /*double pPenalty1,String pPenalty2,*/boolean pAccidentIndicator, String pTicketIssue, String pViolationCode, String pViolationDes, double x, double y)
	{
		objectId = pObjectId;
		location = pLocation;
		
		addressId = pAddressId;
		streetSegId = pStreetId;
		
		fineamt = pFine;
		totalPaid = pTotal;
		//penalty1 = pPenalty1;
		//penalty2 = pPenalty2;
		
		accidentIndicator = pAccidentIndicator;
		ticketIssue = pTicketIssue;
		
		violationCode = pViolationCode;		
		violationDes = pViolationDes;
		
		this.x=x;
		this.y=y;
		
	}
	/**
	{
	DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	DateTime dt = dtf.parseDateTime(pTicketIssue);
	ticketIssue = dt.toDate();
	}
	catch (Exception e)
	{
	if (pTicketIssue.indexOf("T")!=-1 && pTicketIssue.indexOf("T") != 0)
	{
	String dateWorkaround = pTicketIssue.substring(0, pTicketIssue.indexOf("T"));
	DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
	DateTime dt = dtf.parseDateTime(dateWorkaround);
	ticketIssue = dt.toDate();
	}
	}
	 */
	/**
	 * @return id - Identificador Ãºnico de la infracciÃ³n
	 */
	public int objectId() {
		return objectId;
	} 
	/**
	 * @return location - DirecciÃ³n en formato de texto.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return date - Fecha cuando se puso la infracciÃ³n .
	 */
	public String getTicketIssueDate() {
		return ticketIssue;
	}
	/**
	 * @return totalPaid - Cuanto dinero efectivamente pagÃ³ el que recibiÃ³ la infracciÃ³n en USD.
	 */
	public double getTotalPaid() {
	
		return totalPaid;
	}
	public double getFine()
	{
		return fineamt;
	}
	/**
	 * @return accidentIndicator - Si hubo un accidente o no.
	 */
	public boolean  getAccidentIndicator() {
		// 
		return accidentIndicator;
	}
	/**
	 * @return description - Descripci�n textual de la infracci�n.
	 */
	public String  getViolationDescription() 
	{
		// 
		return violationDes;
	}
	
	public String getViolationCode()
	{
		return violationCode;
	}
	
	public int getStreetSegId() 
	{
		return streetSegId;
	}
	
	public int getAddressId() 
	{
		return addressId;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}

	/*
	public double getPenalty1()
	{
		return penalty1;
	}
	
	public String getPenalty2() {
		return penalty2;
	}*/
	
	@Override
	public int compareTo(VOMovingViolations o) {
		// 
		if (o.objectId() == this.objectId() )
		{
			return 0;
		}
		else if (o.objectId() < this.objectId()) 
		{
			return 1;
		}
		else
			return -1;
	}


	@Override
	public Iterator<VOMovingViolations> iterator() {
		
		return (Iterator) this;
	}
	public String toString()
	{
		/**
		 * OBJECTID,	LOCATION,	TICKETISSUEDATE,	VIOLATIONCODE	y	FINEAMT
		 */
		return "{id: "+objectId+", location: "+location+" , ticketIssue:"+ticketIssue+" ,ViolationCode:"+violationCode+" , finemat:"+fineamt+ "}"  ;
	}	
//	//---------------------------------------------------------------------------------------------------------------------------------------
//	// Atributos
//	//---------------------------------------------------------------------------------------------------------------------------------------
//	
//	// Identificador único de la infracción
//	private int OBJECT_ID;
//	
//	// Dirección textual del lugar donde se cometió la infracción
//	private String LOCATION;
//	
//	// Identificador del sitio donde se cometió la infracción
//	private int ADRESS_ID;
//	
//	// Identificador de la calle donde se cometió la infracción
//	private String STREETSEGID;
//	
//	// Coordenada x de donde se cometió la infracción.
//	private double XCOORD;
//	
//	// Coordenada y de donde se cometió la infracción.
//	private double YCOORD;
//	
//	// Total del costo de la multa 
//	private int FINEAMT;
//	
//	// La cantidad que se ha pagado hasta el momento de la multa. 
//	private int TOTALPAID;
//	
//	// Cuanto dinero extra tiene que pagar el comductor
//	private int PENALTY1;
//	
//	// Indicador de si hubo accidente o no en la infracción. 
//	private String ACCIDENTINDICATOR;
//	
//	// Hora y Fecha de cuándo se cometió la infracción. 
//	private LocalDateTime TICKETISSUEDATE;
//	
//	// Código del tipo de infracción que se cometió. 
//	private String VIOLATIONCODE;
//	
//	// Descripción textual del tipo de infracción. 
//	private String VIOLATIONDESC;
//	
//	//---------------------------------------------------------------------------------------------------------------------------------------
//	// Constructor
//	//---------------------------------------------------------------------------------------------------------------------------------------
//	
//	public VOMovingViolations(int pObjectID, String pLocation, int pAdressID, String pStreetSegID, double pXCoord, double pYCoord, int pFineAmt, 
//			int pTotalPaid, int pPentalty1, String pAccidentInidicator, LocalDateTime pTicketIssueDate, String pViolationCode, String pViolationDesc) {
//		
//		OBJECT_ID = pObjectID;
//		LOCATION = pLocation;
//		ADRESS_ID = pAdressID;
//		STREETSEGID = pStreetSegID;
//		XCOORD = pXCoord;
//		YCOORD = pYCoord;
//		FINEAMT = pFineAmt;
//		TOTALPAID = pTotalPaid;
//		PENALTY1 = pPentalty1;
//		ACCIDENTINDICATOR = pAccidentInidicator;
//		TICKETISSUEDATE = pTicketIssueDate;
//		VIOLATIONCODE = pViolationCode;
//		VIOLATIONDESC = pViolationDesc;
//	
//	}
//	
//	//---------------------------------------------------------------------------------------------------------------------------------------
//	// Métodos
//	//---------------------------------------------------------------------------------------------------------------------------------------
//
//	/**
//	 * @return id - Identificador único de la infracción
//	 */
//	public int objectId() {
//		return OBJECT_ID;
//	}	
//	
//	/**
//	 * @return location - Dirección en formato de texto.
//	 */
//	public String getLocation() {
//		return LOCATION;
//	}
//
//	/**
//	 * @return date - Fecha cuando se puso la infracción .
//	 */
//	public LocalDateTime getTicketIssueDate() {
//		return TICKETISSUEDATE;
//	}
//	
//	/**
//	 * @return totalPaid - Cuanto dinero efectivamente pagó el que recibió la infracción en USD.
//	 */
//	public int getTotalPaid() {
//		return TOTALPAID;
//	}
//	
//	/**
//	 * @return accidentIndicator - Si hubo un accidente o no.
//	 */
//	public String  getAccidentIndicator() {
//		return ACCIDENTINDICATOR;
//	}
//		
//	/**
//	 * @return description - Descripción textual de la infracción.
//	 */
//	public String  getViolationDescription() {
//		return VIOLATIONDESC;
//	}
//	
//	/**
//	 * @return streetsegid - identificador del segmento de calle
//	 */
//	public String getStreetSegId() {
//		return STREETSEGID;
//	}
//	
//	/**
//	 * @return  adressID - identificidor de la dirección donde ocurrió la infracción. 
//	 */
//	public int getAddressId() {
//		return ADRESS_ID;
//	}
//	
//	/**
//	 * @return violationcode 
//	 */
//	public String getViolationCode(){
//		return VIOLATIONCODE;
//	}
//	
//	/**
//	 * @return xcoord - retorna la coordenada x de donde se cometió la infracción
//	 */
//	public double getXCoord() {
//		return XCOORD;
//	}
//	
//	/**
//	 * @return ycoord - retorna la coordenada y de donde se cometió la infracción
//	 */
//	public double getYCoord() {
//		return YCOORD;
//	}
//	
//	/**
//	 * @return fineamt - el monto del valor de la multa por la infracción. 
//	 */
//	public int getFineAmt() {
//		return FINEAMT; 
//	}
//	
//	/** 
//	 * @return penalty1= el dinero extra que le toca pagar al que cometió la infracción. 
//	 */
//	public int getPenalty1() {
//		return PENALTY1;
//	}
//	
//	@Override
//	public String toString() {
//		return "VOMovingViolations [objectId()=" + objectId() + ",\n getLocation()=" + getLocation()
//				+ ",\n getTicketIssueDate()=" + getTicketIssueDate() + ",\n getTotalPaid()=" + getTotalPaid()
//				+ ",\n getAccidentIndicator()=" + getAccidentIndicator() + ",\n getViolationDescription()="
//				+ getViolationDescription() + ",\n getStreetSegId()=" + getStreetSegId() + ",\n getAddressId()="
//				+ getAddressId() + "]\n\n";
//	}
//	
//	@Override
//	public int compareTo(VOMovingViolations pViolation) {
//		
//		int comparador = 0; 
//		int id2 = pViolation.objectId();
//		
//		if(OBJECT_ID != id2){
//		
//			if(OBJECT_ID > id2)
//				comparador = 1;
//			else
//				comparador = -1;
//		}
//		
//		return comparador;
//	}
}
