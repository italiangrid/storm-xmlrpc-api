package it.grid.storm.xmlrpc.executors;

import it.grid.storm.xmlrpc.ApiException;
import it.grid.storm.xmlrpc.decoders.DecodingException;
import it.grid.storm.xmlrpc.decoders.SympleStatusDecoder;
import it.grid.storm.xmlrpc.encoders.SurlRequestEncoder;
import it.grid.storm.xmlrpc.outputdata.RequestOutputData;
import it.grid.storm.xmlrpc.remote.synchcall;
import java.util.List;
import java.util.Map;
import redstone.xmlrpc.XmlRpcFault;

public class MkdirExecutor
{

    public static RequestOutputData execute(synchcall storm, String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        if (storm == null || userDN == null || userFQANS == null || userFQANS.isEmpty() || surl == null
                || surl.trim().isEmpty())
        {
            throw new IllegalArgumentException("Unable to call mkdir command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " userFQANS=" + userFQANS + " surl=" + surl);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = SurlRequestEncoder.getInstance().encode(userDN, userFQANS, surl);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode mkdir parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }
    
    public static RequestOutputData execute(synchcall storm, String userDN, String surl) throws ApiException
    {
        if (storm == null || userDN == null || surl == null
                || surl.trim().isEmpty())
        {
            throw new IllegalArgumentException("Unable to call mkdir command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " surl=" + surl);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = SurlRequestEncoder.getInstance().encode(userDN, surl);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode mkdir parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }

    public static RequestOutputData execute(synchcall storm, String surl) throws ApiException
    {
        if (storm == null || surl == null
                || surl.trim().isEmpty())
        {
            throw new IllegalArgumentException("Unable to call mkdir command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " surl=" + surl);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = SurlRequestEncoder.getInstance().encode(surl);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode mkdir parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }
    
    private static RequestOutputData doIt(synchcall storm, Map<String, Object> parameters) throws ApiException
    {
        Map<String, Object> output;
        try
        {
            output = storm.mkdir(parameters);
        } catch(XmlRpcFault e)
        {
            throw new ApiException("Unable to perform mkdir call. XmlRpcFault: " + e.getMessage());
        }
        try
        {
            return SympleStatusDecoder.getInstance().decode(output);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to decode mkdir call output. IllegalArgumentException: "
                    + e.getMessage());
        } catch(DecodingException e)
        {
            throw new ApiException("Unable to decode mkdir call output. DecodingException: " + e.getMessage());
        }
    }

}
