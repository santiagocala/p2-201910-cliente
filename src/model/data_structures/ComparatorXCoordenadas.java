package model.data_structures;

import java.util.Comparator;
import model.vo.VOMovingViolations;

public class ComparatorXCoordenadas implements Comparator<VOMovingViolations> {

	@Override
	public int compare(VOMovingViolations pV1, VOMovingViolations pV2) {

		double primera = pV1.getXCoord();
		double segunda = pV2.getXCoord();
		
		if(primera == segunda) {
			
			primera = pV1.getYCoord();
			segunda = pV2.getYCoord();
			
		}
		
		return (int) (primera - segunda);
	}
}
