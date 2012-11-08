package it.grid.storm.xmlrpc.encoders;


import it.grid.storm.srm.types.RecursionLevel;
import it.grid.storm.xmlrpc.remote.XmlRpcParameters;
import java.util.List;
import java.util.Map;

public class LsEncoder extends SurlArrayRequestEncoder
{
    
    private static final LsEncoder instance = new LsEncoder();

    protected LsEncoder()
    {
    }


    public static LsEncoder getInstance()
    {
        return instance;
    }

//    public Map<String, Object> encode(String userDN, List<String> userFQANS, List<String> surls)
//    {
//        EncodingUtils.checkDN(userDN);
//        EncodingUtils.checkFQANS(userFQANS);
//        EncodingUtils.checkSurls(surls);
//
//        Map<String, Object> encoding = new HashMap<String, Object>();
//        encoding.put(XmlRpcParameters.DN_KEY, userDN);
//        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
//        encoding.put(XmlRpcParameters.ARRAYOF_SURLS_KEY, surls.toArray(new String[surls.size()]));
//        return encoding;
//    }

    public Map<String, Object> encodeDetailed(String userDN, List<String> userFQANS, List<String> surls)
    {
        Map<String, Object> encoding = super.encode(userDN, userFQANS, surls);
//        EncodingUtils.checkDN(userDN);
//        EncodingUtils.checkFQANS(userFQANS);
//        EncodingUtils.checkSurls(surls);

//        Map<String, Object> encoding = new HashMap<String, Object>();
//        encoding.put(XmlRpcParameters.DN_KEY, userDN);
//        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
//        encoding.put(XmlRpcParameters.ARRAYOF_SURLS_KEY, surls.toArray(new String[surls.size()]));
        encoding.put(XmlRpcParameters.LS_DETAILED_KEY, Boolean.TRUE);
        return encoding;
    }

    public Map<String, Object> encode(String userDN, List<String> userFQANS, List<String> surls,
            RecursionLevel recursion)
    {
//        EncodingUtils.checkDN(userDN);
//        EncodingUtils.checkFQANS(userFQANS);
//        EncodingUtils.checkSurls(surls);
        Map<String, Object> encoding = super.encode(userDN, userFQANS, surls);
        EncodingUtils.checkRecursionLevel(recursion);

//        Map<String, Object> encoding = new HashMap<String, Object>();
//        encoding.put(XmlRpcParameters.DN_KEY, userDN);
//        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
//        encoding.put(XmlRpcParameters.ARRAYOF_SURLS_KEY, surls.toArray(new String[surls.size()]));
        encoding.put(XmlRpcParameters.LS_FULL_RECURSIVE_KEY, !recursion.isRecursionLimited());
        if (recursion.isRecursionAllowed() && recursion.isRecursionLimited())
        {
            encoding.put(XmlRpcParameters.LS_RECURSION_LEVELS_KEY, recursion.getRecursionLevel());
        }
        if (recursion.isRecursionAllowed() && recursion.hasStartingDepth())
        {
            encoding.put(XmlRpcParameters.LS_STARTING_RECURSION_DEPTH_KEY, recursion.getStartingDepth());
        }
        return encoding;
    }

    public Map<String, Object> encodeDetailed(String userDN, List<String> userFQANS,
            List<String> surls, RecursionLevel recursion)
    {
//        EncodingUtils.checkDN(userDN);
//        EncodingUtils.checkFQANS(userFQANS);
//        EncodingUtils.checkSurls(surls);
        Map<String, Object> encoding = super.encode(userDN, userFQANS, surls);
        EncodingUtils.checkRecursionLevel(recursion);

//        Map<String, Object> encoding = new HashMap<String, Object>();
//        encoding.put(XmlRpcParameters.DN_KEY, userDN);
//        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
//        encoding.put(XmlRpcParameters.ARRAYOF_SURLS_KEY, surls.toArray(new String[surls.size()]));
        encoding.put(XmlRpcParameters.LS_FULL_RECURSIVE_KEY, !recursion.isRecursionLimited());
        if (recursion.isRecursionAllowed() && recursion.isRecursionLimited())
        {
            encoding.put(XmlRpcParameters.LS_RECURSION_LEVELS_KEY, recursion.getRecursionLevel());
        }
        if (recursion.isRecursionAllowed() && recursion.hasStartingDepth())
        {
            encoding.put(XmlRpcParameters.LS_STARTING_RECURSION_DEPTH_KEY, recursion.getStartingDepth());
        }
        encoding.put(XmlRpcParameters.LS_DETAILED_KEY, Boolean.TRUE);
        return encoding;
    }

    public Map<String, Object> encode(String userDN, List<String> userFQANS, List<String> surls,
            Integer maxReturnedEntries)
    {
//        EncodingUtils.checkDN(userDN);
//        EncodingUtils.checkFQANS(userFQANS);
//        EncodingUtils.checkSurls(surls);
        Map<String, Object> encoding = super.encode(userDN, userFQANS, surls);
        EncodingUtils.checkAmount(maxReturnedEntries);

//        Map<String, Object> encoding = new HashMap<String, Object>();
//        encoding.put(XmlRpcParameters.DN_KEY, userDN);
//        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
//        encoding.put(XmlRpcParameters.ARRAYOF_SURLS_KEY, surls.toArray(new String[surls.size()]));
        encoding.put(XmlRpcParameters.LS_MAX_RETURNED_ENTRIES_KEY, maxReturnedEntries);
        return encoding;
    }

    public Map<String, Object> encodeDetailed(String userDN, List<String> userFQANS,
            List<String> surls, Integer maxReturnedEntries)
    {
//        EncodingUtils.checkDN(userDN);
//        EncodingUtils.checkFQANS(userFQANS);
//        EncodingUtils.checkSurls(surls);
        Map<String, Object> encoding = super.encode(userDN, userFQANS, surls);
        EncodingUtils.checkAmount(maxReturnedEntries);

//        Map<String, Object> encoding = new HashMap<String, Object>();
//        encoding.put(XmlRpcParameters.DN_KEY, userDN);
//        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
//        encoding.put(XmlRpcParameters.ARRAYOF_SURLS_KEY, surls.toArray(new String[surls.size()]));
        encoding.put(XmlRpcParameters.LS_MAX_RETURNED_ENTRIES_KEY, maxReturnedEntries);
        encoding.put(XmlRpcParameters.LS_DETAILED_KEY, Boolean.TRUE);
        return encoding;
    }

    public Map<String, Object> encode(String userDN, List<String> userFQANS, List<String> surls,
            RecursionLevel recursion, Integer maxReturnedEntries)
    {
//        EncodingUtils.checkDN(userDN);
//        EncodingUtils.checkFQANS(userFQANS);
//        EncodingUtils.checkSurls(surls);
        Map<String, Object> encoding = super.encode(userDN, userFQANS, surls);
        EncodingUtils.checkRecursionLevel(recursion);
        EncodingUtils.checkAmount(maxReturnedEntries);

//        Map<String, Object> encoding = new HashMap<String, Object>();
//        encoding.put(XmlRpcParameters.DN_KEY, userDN);
//        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
//        encoding.put(XmlRpcParameters.ARRAYOF_SURLS_KEY, surls.toArray(new String[surls.size()]));
        encoding.put(XmlRpcParameters.LS_FULL_RECURSIVE_KEY, !recursion.isRecursionLimited());
        if (recursion.isRecursionAllowed() && recursion.isRecursionLimited())
        {
            encoding.put(XmlRpcParameters.LS_RECURSION_LEVELS_KEY, recursion.getRecursionLevel());
        }
        if (recursion.isRecursionAllowed() && recursion.hasStartingDepth())
        {
            encoding.put(XmlRpcParameters.LS_STARTING_RECURSION_DEPTH_KEY, recursion.getStartingDepth());
        }
        encoding.put(XmlRpcParameters.LS_MAX_RETURNED_ENTRIES_KEY, maxReturnedEntries);
        return encoding;
    }

    public Map<String, Object> encodeDetailed(String userDN, List<String> userFQANS,
            List<String> surls, RecursionLevel recursion, Integer maxReturnedEntries)
    {
//        EncodingUtils.checkDN(userDN);
//        EncodingUtils.checkFQANS(userFQANS);
//        EncodingUtils.checkSurls(surls);
        Map<String, Object> encoding = super.encode(userDN, userFQANS, surls);
        EncodingUtils.checkRecursionLevel(recursion);
        EncodingUtils.checkAmount(maxReturnedEntries);

//        Map<String, Object> encoding = new HashMap<String, Object>();
//        encoding.put(XmlRpcParameters.DN_KEY, userDN);
//        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
//        encoding.put(XmlRpcParameters.ARRAYOF_SURLS_KEY, surls.toArray(new String[surls.size()]));
        encoding.put(XmlRpcParameters.LS_FULL_RECURSIVE_KEY, !recursion.isRecursionLimited());
        if (recursion.isRecursionAllowed() && recursion.isRecursionLimited())
        {
            encoding.put(XmlRpcParameters.LS_RECURSION_LEVELS_KEY, recursion.getRecursionLevel());
        }
        if (recursion.isRecursionAllowed() && recursion.hasStartingDepth())
        {
            encoding.put(XmlRpcParameters.LS_STARTING_RECURSION_DEPTH_KEY, recursion.getStartingDepth());
        }
        encoding.put(XmlRpcParameters.LS_MAX_RETURNED_ENTRIES_KEY, maxReturnedEntries);
        encoding.put(XmlRpcParameters.LS_DETAILED_KEY, Boolean.TRUE);
        return encoding;
    }
}
