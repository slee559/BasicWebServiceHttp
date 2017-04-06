package nz.co.usedCars.domain;

/**
 * 
 * @author slee559
 *
 */
public enum CarType {
	
	MANUAL, AUTOMATIC;
	
	public static CarType fromString(String type) {
		if (type != null) {
			for (CarType g: CarType.values()) {
				if (type.equalsIgnoreCase(g.toString())) {
					return g;
				}
			}
		}
		return null;
	}
}
