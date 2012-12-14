package it.grid.storm.xmlrpc.executors;

import it.grid.storm.srm.types.TRequestToken;
import it.grid.storm.xmlrpc.ApiException;
import it.grid.storm.xmlrpc.decoders.DecodingException;
import it.grid.storm.xmlrpc.decoders.SurlArrayStatusDecoder;
import it.grid.storm.xmlrpc.encoders.ManageFileTransferEncoder;
import it.grid.storm.xmlrpc.outputdata.SurlArrayRequestOutputData;
import it.grid.storm.xmlrpc.remote.synchcall;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import redstone.xmlrpc.XmlRpcFault;

public class SurlRequestStatusExecutor
{
    
    public enum SurlRequestType{
        PTP,PTG
    }

    public static SurlArrayRequestOutputData execute(synchcall storm, String userDN, List<String> userFQANS, String surl,
            TRequestToken requestToken, SurlRequestType requestType) throws ApiException
    {
        if (storm == null || userDN == null || userFQANS == null || userFQANS.isEmpty() || surl == null
                || surl.trim().isEmpty() || requestToken == null || requestToken.getValue().trim().isEmpty() || requestType == null)
        {
            throw new IllegalArgumentException(
                                               "Unable to call ptp status command. Received null arguments: storm="
                                                       + (storm == null ? "null" : "not null") + " userDN="
                                                       + userDN + " userFQANS=" + userFQANS + " surl=" + surl
                                                       + " requestToken=" + requestToken
                                                       + " requestType=" + requestType);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = ManageFileTransferEncoder.getInstance().encodeWithSurls(userDN, userFQANS, Arrays.asList(new String[]{surl}), requestToken);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptp status parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters, requestType);
    }

    public static SurlArrayRequestOutputData execute(synchcall storm, String userDN, List<String> userFQANS, String surl, SurlRequestType requestType) throws ApiException
    {
        if (storm == null || userDN == null || userFQANS == null || userFQANS.isEmpty() || surl == null
                || surl.trim().isEmpty() || requestType == null)
        {
            throw new IllegalArgumentException("Unable to call ptp status command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " userFQANS=" + userFQANS + " surl=" + surl + " requestType=" + requestType);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = ManageFileTransferEncoder.getInstance().encode(userDN, userFQANS, Arrays.asList(new String[]{surl}));
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptp status parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters, requestType);
    }

    public static SurlArrayRequestOutputData execute(synchcall storm, String userDN, String surl,
            TRequestToken requestToken, SurlRequestType requestType) throws ApiException
    {
        if (storm == null || userDN == null || surl == null
                || surl.trim().isEmpty() || requestToken == null || requestToken.getValue().trim().isEmpty() || requestType == null)
        {
            throw new IllegalArgumentException("Unable to call ptp status command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " surl=" + surl + " requestToken=" + requestToken + " requestType=" + requestType);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = ManageFileTransferEncoder.getInstance().encodeWithSurls(userDN, Arrays.asList(new String[]{surl}),requestToken);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptp status parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters, requestType);
    }

    public static SurlArrayRequestOutputData execute(synchcall storm, String userDN, String surl, SurlRequestType requestType) throws ApiException
    {
        if (storm == null || userDN == null || surl == null
                || surl.trim().isEmpty() || requestType == null)
        {
            throw new IllegalArgumentException("Unable to call ptp status command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " surl=" + surl + " requestType=" + requestType);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = ManageFileTransferEncoder.getInstance().encode(userDN, Arrays.asList(new String[]{surl}));
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptp status parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters, requestType);
    }

    public static SurlArrayRequestOutputData execute(synchcall storm, String surl, TRequestToken requestToken, SurlRequestType requestType) throws ApiException
    {
        if (storm == null || surl == null
                || surl.trim().isEmpty() || requestToken == null || requestToken.getValue().trim().isEmpty() || requestType == null)
        {
            throw new IllegalArgumentException("Unable to call ptp status command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " surl=" + surl + " requestToken=" + requestToken + " requestType=" + requestType);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = ManageFileTransferEncoder.getInstance().encodeWithSurls(Arrays.asList(new String[]{surl}), requestToken);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptp status parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters, requestType);
    }

    public static SurlArrayRequestOutputData execute(synchcall storm, String surl, SurlRequestType requestType) throws ApiException
    {
        if (storm == null || surl == null
                || surl.trim().isEmpty() || requestType == null)
        {
            throw new IllegalArgumentException("Unable to call ptp status command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " surl=" + surl + " requestType=" + requestType);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = ManageFileTransferEncoder.getInstance().encode(Arrays.asList(new String[]{surl}));
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptp status parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters, requestType);
    }
    
    private static SurlArrayRequestOutputData doIt(synchcall storm, Map<String, Object> parameters, SurlRequestType requestType) throws ApiException
    {
        Map<String, Object> output;
        try
        {
            switch (requestType)
            {
                case PTP:
                    output = storm.prepareToPutStatus(parameters);
                    break;
                case PTG:
                    output = storm.prepareToGetStatus(parameters);
                    break;
                default:
                    throw new ApiException("Unable to perform Surl Status call, unknown SurlRequestType: " + requestType);
            }
            
        } catch(XmlRpcFault e)
        {
            throw new ApiException("Unable to perform ptp status call. XmlRpcFault: " + e.getMessage());
        }
        try
        {
            return SurlArrayStatusDecoder.getInstance().decode(output);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to decode ptp status call output. IllegalArgumentException: "
                    + e.getMessage());
        } catch(DecodingException e)
        {
            throw new ApiException("Unable to decode ptp status call output. DecodingException: " + e.getMessage());
        }
    }

}
