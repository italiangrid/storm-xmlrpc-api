package it.grid.storm.xmlrpc.executors;

import it.grid.storm.xmlrpc.ApiException;
import it.grid.storm.xmlrpc.decoders.DecodingException;
import it.grid.storm.xmlrpc.decoders.PtGDecoder;
import it.grid.storm.xmlrpc.encoders.PtGEncoder;
import it.grid.storm.xmlrpc.outputdata.PtGOutputData;
import it.grid.storm.xmlrpc.remote.synchcall;
import java.util.List;
import java.util.Map;
import redstone.xmlrpc.XmlRpcFault;

public class PtGExecutor
{

    public static PtGOutputData execute(synchcall storm, String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        if (storm == null || userDN == null || userFQANS == null || userFQANS.isEmpty() || surl == null
                || surl.trim().isEmpty())
        {
            throw new IllegalArgumentException("Unable to call ptg command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " userFQANS=" + userFQANS + " surl=" + surl);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = PtGEncoder.getInstance().encode(userDN, userFQANS, surl);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptg parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }

    public static PtGOutputData execute(synchcall storm, String userDN, List<String> userFQANS, String surl,
            List<String> transferProtocols) throws ApiException
    {
        if (storm == null || userDN == null || userFQANS == null || userFQANS.isEmpty() || surl == null
                || surl.trim().isEmpty() || transferProtocols == null || transferProtocols.isEmpty())
        {
            throw new IllegalArgumentException("Unable to call ptg command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " userFQANS=" + userFQANS + " surl=" + surl
                    + " transferProtocols=" + transferProtocols);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = PtGEncoder.getInstance().encode(userDN, userFQANS, surl, transferProtocols);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptg parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }

    private static PtGOutputData doIt(synchcall storm, Map<String, Object> parameters) throws ApiException
    {
        Map<String, Object> output;
        try
        {
            output = storm.prepareToGet(parameters);
        } catch(XmlRpcFault e)
        {
            throw new ApiException("Unable to perform ptg call. XmlRpcFault: " + e.getMessage());
        }
        try
        {
            return PtGDecoder.getInstance().decode(output);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to decode ptg call output. IllegalArgumentException: "
                    + e.getMessage());
        } catch(DecodingException e)
        {
            throw new ApiException("Unable to decode ptg call output. DecodingException: " + e.getMessage());
        }
    }
    
}
