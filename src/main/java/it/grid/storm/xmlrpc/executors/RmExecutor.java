package it.grid.storm.xmlrpc.executors;

import it.grid.storm.xmlrpc.ApiException;
import it.grid.storm.xmlrpc.decoders.DecodingException;
import it.grid.storm.xmlrpc.decoders.SympleStatusDecoder;
import it.grid.storm.xmlrpc.encoders.SurlArrayRequestEncoder;
import it.grid.storm.xmlrpc.outputdata.RequestOutputData;
import it.grid.storm.xmlrpc.remote.synchcall;
import java.util.List;
import java.util.Map;
import redstone.xmlrpc.XmlRpcFault;

public class RmExecutor
{
    public static RequestOutputData execute(synchcall storm, String userDN, List<String> userFQANS,
            List<String> surls) throws ApiException
    {
        if (storm == null || userDN == null || userFQANS == null || userFQANS.isEmpty() || surls == null
                || surls.isEmpty())
        {
            throw new IllegalArgumentException("Unable to call rm command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " userFQANS=" + userFQANS + " surls=" + surls);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = SurlArrayRequestEncoder.getInstance().encode(userDN, userFQANS, surls);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode rm parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }
    
    public static RequestOutputData execute(synchcall storm, String userDN, List<String> surls) throws ApiException
    {
        if (storm == null || userDN == null || surls == null
                || surls.isEmpty())
        {
            throw new IllegalArgumentException("Unable to call rm command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " surls=" + surls);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = SurlArrayRequestEncoder.getInstance().encode(userDN, surls);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode rm parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }

    public static RequestOutputData execute(synchcall storm, List<String> surls) throws ApiException
    {
        if (storm == null || surls == null
                || surls.isEmpty())
        {
            throw new IllegalArgumentException("Unable to call rm command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " surls=" + surls);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = SurlArrayRequestEncoder.getInstance().encode(surls);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode rm parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }
    
    private static RequestOutputData doIt(synchcall storm, Map<String, Object> parameters) throws ApiException
    {
        Map<String, Object> output;
        try
        {
            output = storm.rm(parameters);
        } catch(XmlRpcFault e)
        {
            throw new ApiException("Unable to perform rm call. XmlRpcFault: " + e.getMessage());
        }
        try
        {
            return SympleStatusDecoder.getInstance().decode(output);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to decode rm call output. IllegalArgumentException: "
                    + e.getMessage());
        } catch(DecodingException e)
        {
            throw new ApiException("Unable to decode rm call output. DecodingException: " + e.getMessage());
        }
    }
}
