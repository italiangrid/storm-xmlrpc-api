package it.grid.storm.xmlrpc.encoders;

import it.grid.storm.xmlrpc.remote.XmlRpcParameters;
import java.util.List;
import java.util.Map;

public class RmdirRequestEncoder extends SurlRequestEncoder
{
    
    private static final RmdirRequestEncoder instance = new RmdirRequestEncoder();

    protected RmdirRequestEncoder()
    {
    }


    public static RmdirRequestEncoder getInstance()
    {
        return instance;
    }

    public Map<String, Object> encodeRecursive(String userDN, List<String> userFQANS, String surl)
    {
        Map<String, Object> encoding = super.encode(userDN, userFQANS, surl);

        encoding.put(XmlRpcParameters.RMDIR_RECURSIVE_KEY, Boolean.TRUE);
        return encoding;
    }

}
