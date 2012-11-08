package it.grid.storm.xmlrpc.encoders;

import it.grid.storm.xmlrpc.remote.XmlRpcParameters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurlArrayRequestEncoder
{
    private static final SurlArrayRequestEncoder instance = new SurlArrayRequestEncoder();

    protected SurlArrayRequestEncoder()
    {
    }


    public static SurlArrayRequestEncoder getInstance()
    {
        return instance;
    }

    public Map<String, Object> encode(String userDN, List<String> userFQANS, List<String> surls)
            throws IllegalArgumentException
    {
        EncodingUtils.checkDN(userDN);
        EncodingUtils.checkFQANS(userFQANS);
        EncodingUtils.checkSurls(surls);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        encoding.put(XmlRpcParameters.ARRAYOF_SURLS_KEY, surls.toArray(new String[surls.size()]));
        return encoding;
    }
}
