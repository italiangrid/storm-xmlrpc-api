package it.grid.storm.xmlrpc.encoders;

import it.grid.storm.xmlrpc.remote.XmlRpcDefaults;
import it.grid.storm.xmlrpc.remote.XmlRpcParameters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PtGEncoder
{
    
    private static final PtGEncoder instance = new PtGEncoder();

    protected PtGEncoder()
    {
    }


    public static PtGEncoder getInstance()
    {
        return instance;
    }

    public Map<String, Object> encode(String userDN, List<String> userFQANS, String surl)
    {
        EncodingUtils.checkDN(userDN);
        EncodingUtils.checkFQANS(userFQANS);
        EncodingUtils.checkSurl(surl);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        encoding.put(XmlRpcParameters.SURL_KEY, surl);
        encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, XmlRpcDefaults.TURL_PREFIX);
        return encoding;
    }

    public Map<String, Object> encode(String userDN, List<String> userFQANS, String surl,
            List<String> transferProtocols)
    {
        EncodingUtils.checkDN(userDN);
        EncodingUtils.checkFQANS(userFQANS);
        EncodingUtils.checkSurl(surl);
        EncodingUtils.checkTransferProtocols(transferProtocols);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        encoding.put(XmlRpcParameters.SURL_KEY, surl);
        encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, transferProtocols.toArray());
        return encoding;
    }

}
