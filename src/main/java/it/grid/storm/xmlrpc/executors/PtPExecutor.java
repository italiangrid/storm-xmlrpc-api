package it.grid.storm.xmlrpc.executors;

import it.grid.storm.srm.types.TLifeTimeInSeconds;
import it.grid.storm.xmlrpc.ApiException;
import it.grid.storm.xmlrpc.decoders.DecodingException;
import it.grid.storm.xmlrpc.decoders.PtPDecoder;
import it.grid.storm.xmlrpc.encoders.PtPEncoder;
import it.grid.storm.xmlrpc.outputdata.FileTransferOutputData;
import it.grid.storm.xmlrpc.remote.synchcall;
import java.util.List;
import java.util.Map;
import redstone.xmlrpc.XmlRpcFault;

public class PtPExecutor
{

    public static FileTransferOutputData execute(synchcall storm, String userDN, List<String> userFQANS,
            String surl) throws ApiException
    {
        if (storm == null || userDN == null || userFQANS == null || userFQANS.isEmpty() || surl == null
                || surl.trim().isEmpty())
        {
            throw new IllegalArgumentException("Unable to call ptp command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " userFQANS=" + userFQANS + " surl=" + surl);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = PtPEncoder.getInstance().encode(userDN, userFQANS, surl);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptp parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }
    
    public static FileTransferOutputData executeOverwrite(synchcall storm, String userDN, List<String> userFQANS,
            String surl) throws ApiException
    {
        if (storm == null || userDN == null || userFQANS == null || userFQANS.isEmpty() || surl == null
                || surl.trim().isEmpty())
        {
            throw new IllegalArgumentException("Unable to call ptp command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " userFQANS=" + userFQANS + " surl=" + surl);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = PtPEncoder.getInstance().encodeOverwrite(userDN, userFQANS, surl);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptp parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }

    public static FileTransferOutputData execute(synchcall storm, String userDN, List<String> userFQANS,
            String surl, List<String> transferProtocols) throws ApiException
    {
        if (storm == null || userDN == null || userFQANS == null || userFQANS.isEmpty() || surl == null
                || surl.trim().isEmpty() || transferProtocols == null || transferProtocols.isEmpty())
        {
            throw new IllegalArgumentException("Unable to call ptp command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " userFQANS=" + userFQANS + " surl=" + surl
                    + " transferProtocols=" + transferProtocols);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = PtPEncoder.getInstance().encode(userDN, userFQANS, surl, transferProtocols);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptp parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }

    public static FileTransferOutputData executeOverwrite(synchcall storm, String userDN, List<String> userFQANS,
            String surl, List<String> transferProtocols) throws ApiException
    {
        if (storm == null || userDN == null || userFQANS == null || userFQANS.isEmpty() || surl == null
                || surl.trim().isEmpty() || transferProtocols == null || transferProtocols.isEmpty())
        {
            throw new IllegalArgumentException("Unable to call ptp command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " userFQANS=" + userFQANS + " surl=" + surl
                    + " transferProtocols=" + transferProtocols);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = PtPEncoder.getInstance().encodeOverwrite(userDN, userFQANS, surl, transferProtocols);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptp parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }

    public static FileTransferOutputData execute(synchcall storm, String userDN, List<String> userFQANS,
            String surl, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        if (storm == null || userDN == null || userFQANS == null || userFQANS.isEmpty() || surl == null
                || surl.trim().isEmpty() || desiredFileLifetime == null)
        {
            throw new IllegalArgumentException("Unable to call ptp command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " userFQANS=" + userFQANS + " surl=" + surl
                    + " desiredFileLifetime=" + desiredFileLifetime);
        }
        Map<String, Object> parameters;
        try
        {
            if(desiredPinLifetime == null || desiredPinLifetime.isEmpty())
            {
                parameters = PtPEncoder.getInstance().encode(userDN, userFQANS, surl, desiredFileLifetime);    
            }
            else
            {
                parameters = PtPEncoder.getInstance().encode(userDN, userFQANS, surl, desiredFileLifetime, desiredPinLifetime);
            }
            
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptp parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }
    
    public static FileTransferOutputData executeOverwrite(synchcall storm, String userDN, List<String> userFQANS,
            String surl, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        if (storm == null || userDN == null || userFQANS == null || userFQANS.isEmpty() || surl == null
                || surl.trim().isEmpty() || desiredFileLifetime == null)
        {
            throw new IllegalArgumentException("Unable to call ptp command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " userFQANS=" + userFQANS + " surl=" + surl
                    + " desiredFileLifetime=" + desiredFileLifetime);
        }
        Map<String, Object> parameters;
        try
        {
            if(desiredPinLifetime == null || desiredPinLifetime.isEmpty())
            {
                parameters = PtPEncoder.getInstance().encodeOverwrite(userDN, userFQANS, surl, desiredFileLifetime);    
            }
            else
            {
                parameters = PtPEncoder.getInstance().encodeOverwrite(userDN, userFQANS, surl, desiredFileLifetime, desiredPinLifetime);
            }
            
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptp parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }

    public static FileTransferOutputData execute(synchcall storm, String userDN, List<String> userFQANS,
            String surl, List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        if (storm == null || userDN == null || userFQANS == null || userFQANS.isEmpty() || surl == null
                || surl.trim().isEmpty() || transferProtocols == null || transferProtocols.isEmpty())
        {
            throw new IllegalArgumentException("Unable to call ptp command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " userFQANS=" + userFQANS + " surl=" + surl
                    + " transferProtocols=" + transferProtocols);
        }
        Map<String, Object> parameters;
        try
        {
            if(desiredPinLifetime == null || desiredPinLifetime.isEmpty())
            {
                parameters = PtPEncoder.getInstance().encode(userDN, userFQANS, surl, transferProtocols, desiredFileLifetime);    
            }
            else
            {
                parameters = PtPEncoder.getInstance().encode(userDN, userFQANS, surl, transferProtocols, desiredFileLifetime, desiredPinLifetime);
            }
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptp parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }
    
    public static FileTransferOutputData executeOverwrite(synchcall storm, String userDN, List<String> userFQANS,
            String surl, List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        if (storm == null || userDN == null || userFQANS == null || userFQANS.isEmpty() || surl == null
                || surl.trim().isEmpty() || transferProtocols == null || transferProtocols.isEmpty())
        {
            throw new IllegalArgumentException("Unable to call ptp command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " userFQANS=" + userFQANS + " surl=" + surl
                    + " transferProtocols=" + transferProtocols);
        }
        Map<String, Object> parameters;
        try
        {
            if(desiredPinLifetime == null || desiredPinLifetime.isEmpty())
            {
                parameters = PtPEncoder.getInstance().encodeOverwrite(userDN, userFQANS, surl, transferProtocols, desiredFileLifetime);    
            }
            else
            {
                parameters = PtPEncoder.getInstance().encodeOverwrite(userDN, userFQANS, surl, transferProtocols, desiredFileLifetime, desiredPinLifetime);
            }
            
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode ptp parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }

    private static FileTransferOutputData doIt(synchcall storm, Map<String, Object> parameters) throws ApiException
    {
        Map<String, Object> output;
        try
        {
            output = storm.prepareToPut(parameters);
        } catch(XmlRpcFault e)
        {
            throw new ApiException("Unable to perform ptp call. XmlRpcFault: " + e.getMessage());
        }
        try
        {
            return PtPDecoder.getInstance().decode(output);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to decode ptp call output. IllegalArgumentException: "
                    + e.getMessage());
        } catch(DecodingException e)
        {
            throw new ApiException("Unable to decode ptp call output. DecodingException: " + e.getMessage());
        }
    }
    
    
}
