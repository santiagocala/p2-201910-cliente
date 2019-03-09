package model.vo;

import model.data_structures.IQueue;

/**  
 * Agrupa las infracciones por location 
 */

public class InfraccionesLocalizacion extends Infracciones {

	private double xcoord;
	
	private double ycoord;
	
	private String adressID;
	
	private String streetID;
	
	
	/**
	 * Instantiates a new infracciones localizacion.
	 * @param lista the lista
	 */
	
	public InfraccionesLocalizacion(IQueue<VOMovingViolations> lista) {
		super(lista);
	}


	/**
	 * Gets the xcoord.
	 *
	 * @return the xcoord
	 */
	public double getXcoord() {
		return xcoord;
	}


	/**
	 * Sets the xcoord.
	 *
	 * @param xcoord the xcoord to set
	 */
	public void setXcoord(double xcoord) {
		this.xcoord = xcoord;
	}


	/**
	 * Gets the ycoord.
	 *
	 * @return the ycoord
	 */
	public double getYcoord() {
		return ycoord;
	}


	/**
	 * Sets the ycoord.
	 *
	 * @param ycoord the ycoord to set
	 */
	public void setYcoord(double ycoord) {
		this.ycoord = ycoord;
	}


	/**
	 * Gets the adress ID.
	 *
	 * @return the adressID
	 */
	public String getAdressID() {
		return adressID;
	}


	/**
	 * Sets the adress ID.
	 *
	 * @param adressID the adressID to set
	 */
	public void setAdressID(String adressID) {
		this.adressID = adressID;
	}


	/**
	 * Gets the street ID.
	 *
	 * @return the streetID
	 */
	public String getStreetID() {
		return streetID;
	}


	/**
	 * Sets the street ID.
	 *
	 * @param streetID the streetID to set
	 */
	public void setStreetID(String streetID) {
		this.streetID = streetID;
	}
}
