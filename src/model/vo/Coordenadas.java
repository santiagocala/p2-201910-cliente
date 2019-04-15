package model.vo;

public class Coordenadas implements Comparable<Coordenadas> {
	
	// Atributos -----------------------------------------------------------------------
	
	/**
	 * Atributo que representa la coordenada x
	 */
	private double x;
	
	/**
	 * Atributo que representa la coordenada y
	 */
	private double y;
	
	// Constructor ----------------------------------------------------------------------- 
	
	/**
	 * Construye un objeto Coordenadas con dos valores dados por par{ametro. 
	 * @param pX - valor que representa la coordenada x
	 * @param pY - valor que representa la coordenada y
	 */
	public Coordenadas(double pX, double pY) {
		x = pX;
		y = pY;
	}
	
	// Métodos -----------------------------------------------------------------------
	
	/**
	 * @return el valor de x
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * @return el valor de y
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Cambia el valor de la coordenada x por uno dado por parámetro. 
	 * @param pX
	 */
	public void cambiarX(double pX) {
		x = pX;
	}
	
	/**
	 * Cambia el valor de la coordenada y por uno dado por parámetro. 
	 * @param pY
	 */
	public void cambiarY(double pY) {
		y = pY;
	}

	@Override
	public int compareTo(Coordenadas pCoordenadas) {
		int comparador = 0;
		if(x == pCoordenadas.getX()) {
			comparador = (int) (y - pCoordenadas.getY());
		}
		comparador = (int) (x - pCoordenadas.getX());
		return comparador;
	}
	
	
}
