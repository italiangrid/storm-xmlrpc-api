package it.grid.storm.xmlrpc;

public class ApiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1924121675489993774L;

	public ApiException(String string) {
		super(string);
	}
	
	public ApiException(String string, Throwable e) {
		super(string, e);
	}
	
	public ApiException(Throwable e) {
        super(e);
    }

}
