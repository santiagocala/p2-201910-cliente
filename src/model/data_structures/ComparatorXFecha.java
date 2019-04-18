package model.data_structures;

import java.util.Comparator;
import model.vo.VOMovingViolations;

public class ComparatorXFecha implements Comparator<VOMovingViolations> {

	@Override
	public int compare(VOMovingViolations pV1, VOMovingViolations pV2) {
		
		//System.out.println("se llega al comparador");
		return pV1.getTicketIssueDate().compareTo(pV2.getTicketIssueDate());

	}
}
