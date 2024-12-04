package exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = -5346211657090581645L;

	public DAOException(String string) {
		super(string);
	}
	
	public DAOException(Exception e) {
		super(e);
	}

	@Override
	public String getMessage() {
		System.out.println("An error has occurred in the DAO operation.");
		return super.getMessage();
	}
	
}
