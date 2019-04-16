package model.vo;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FranjaFechaHora implements Comparable<FranjaFechaHora> {
	
	// Atributos --------------------------------------------------------
	
	public LocalDateTime hora;
	
	
	// Constructor -------------------------------------------------------
	
	public FranjaFechaHora(LocalDateTime pHoraInicial) {
		
		hora = pHoraInicial;
		
	}
	
	// Métodos ------------------------------------------------------------
	
//	/**
//	 * Método que le agrega a 
//	 * @param pHoraInicial
//	 * @return
//	 */
//	public LocalDateTime calcularHoraFinal(LocalDateTime pHoraInicial) {
//		
//		return pHoraInicial.plusMinutes(59);
//	}
	
	public void cambiarHora(LocalDateTime pHora) {
		hora = pHora;
	}

	@Override
	public int compareTo(FranjaFechaHora o) {
		int comparador = hora.compareTo(o.hora);
		if(hora.getHour() == o.hora.getHour() && hora.toLocalDate() == o.hora.toLocalDate()) {
			comparador = 0;
		}
		return comparador;
	}

}
