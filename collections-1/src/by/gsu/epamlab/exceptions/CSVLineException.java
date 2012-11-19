package by.gsu.epamlab.exceptions;

@SuppressWarnings("serial")
public class CSVLineException extends IllegalArgumentException {
	private final String errorLine;
	private final String message;
	
	public CSVLineException(String errorLine, String message) {
		this.errorLine = errorLine;
		this.message = message;
	}
	
	public String getMessage() {
		return (message + "\n\tLine with error: " + errorLine);
	}
}
