package exception;

public class AnimalsException extends Exception {
	
	public AnimalsException(String message) {
		super(message);
	}
	
	public AnimalsException(Exception e) {
		super(e);
	}
	
}
