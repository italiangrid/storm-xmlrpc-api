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
        Map<String, Object> encoding = encode(userDN, surl);
        EncodingUtils.checkFQANS(userFQANS);

        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        return encoding;
    }


    public Map<String, Object> encode(String userDN, String surl)
    {
        Map<String, Object> encoding = encode(surl);
        EncodingUtils.checkDN(userDN);
        
        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        return encoding;
    }

    public Map<String, Object> encode(String surl)
    {
        EncodingUtils.checkSurl(surl);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.SURL_KEY, surl);
        return encoding;
    }
}
