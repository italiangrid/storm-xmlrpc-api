package it.grid.storm.xmlrpc.decoders;

/**
 * @author Michele Dibenedetto
 *
 */
public class DecodingException extends Exception
{

    private static final long serialVersionUID = 1513260022173901636L;

    public DecodingException()
    {
    }

    public DecodingException(String message)
    {
        super(message);
    }

    public DecodingException(Throwable cause)
    {
        super(cause);
    }

    public DecodingException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
