package model.data_structures;

import java.util.Comparator;

import model.vo.VOMovingViolations;

public class ComparatorXDesc implements Comparator<VOMovingViolations> {

	@Override
	public int compare(VOMovingViolations pV1, VOMovingViolations pV2) {
		
		String desc1 = pV1.getViolationDescription();
		
		System.out.println(pV1.getViolationDescription() + " y la otra " + pV2.getViolationDescription());
		
		String desc2 = pV2.getViolationDescription();
		
		System.out.println(desc1.compareTo(desc2));
		
		return desc1.compareTo(desc2);
	}

} 
