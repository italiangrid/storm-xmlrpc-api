package it.grid.storm.xmlrpc.encoders;


import it.grid.storm.xmlrpc.remote.XmlRpcParameters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurlRequestEncoder
{
    
    private static final SurlRequestEncoder instance = new SurlRequestEncoder();

    protected SurlRequestEncoder()
    {
    }


    public static SurlRequestEncoder getInstance()
    {
        return instance;
    }

    public Map<String, Object> encode(String userDN, List<String> userFQANS, String surl)
            throws IllegalArgumentException
    {
        EncodingUtils.checkDN(userDN);
        EncodingUtils.checkFQANS(userFQANS);
        EncodingUtils.checkSurl(surl);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        encoding.put(XmlRpcParameters.SURL_KEY, surl);
        return encoding;
    }
}
