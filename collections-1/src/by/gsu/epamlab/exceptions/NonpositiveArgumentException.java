package by.gsu.epamlab.exceptions;

@SuppressWarnings("serial")
public class NonpositiveArgumentException extends IllegalStateException {
	private final String place;
	private final int nonpositiveValue;
	
	public NonpositiveArgumentException(int nonpositiveValue, String place) {
		this.nonpositiveValue = nonpositiveValue;
		this.place = place;
	};
	
	public String getMessage() {
		return ("Nonpositive value " + nonpositiveValue + " at " + place);
	}
}
