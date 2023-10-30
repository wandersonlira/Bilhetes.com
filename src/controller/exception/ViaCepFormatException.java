package controller.exception;

public class ViaCepFormatException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public ViaCepFormatException(String s) {
        super(s);
    }
}
