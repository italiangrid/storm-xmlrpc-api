package it.grid.storm.xmlrpc.decoders;

import it.grid.storm.xmlrpc.outputdata.OutputData;
import java.util.Map;

/**
 * @author Michele Dibenedetto
 *
 */
public interface OutputDecoder
{

    public OutputData decode(Map<String, Object> output) throws IllegalArgumentException, DecodingException;

}
