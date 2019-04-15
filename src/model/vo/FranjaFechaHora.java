package model.vo;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FranjaFechaHora implements Comparable<FranjaFechaHora> {
	
	// Atributos --------------------------------------------------------
	
	public LocalDateTime horaInicial;
	
	public LocalDateTime horaFinal;
	
	// Constructor -------------------------------------------------------
	
	public FranjaFechaHora(LocalDateTime pHoraInicial) {
		
		horaInicial = pHoraInicial;
		horaFinal = calcularHoraFinal(pHoraInicial);
	}
	
	// Métodos ------------------------------------------------------------
	
	/**
	 * Método que le agrega a 
	 * @param pHoraInicial
	 * @return
	 */
	public LocalDateTime calcularHoraFinal(LocalDateTime pHoraInicial) {
		
		return pHoraInicial.plusMinutes(59);
	}

	@Override
	public int compareTo(FranjaFechaHora o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
