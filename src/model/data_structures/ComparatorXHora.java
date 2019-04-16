package model.data_structures;

import java.util.Comparator;
import model.vo.VOMovingViolations;

public class ComparatorXHora implements Comparator<VOMovingViolations> {

	@Override
	public int compare(VOMovingViolations pV1, VOMovingViolations pV2) {
		Integer hora = pV1.getTicketIssueDate().getHour();
		return hora.compareTo((Integer)pV2.getTicketIssueDate().getHour());

	}
}
