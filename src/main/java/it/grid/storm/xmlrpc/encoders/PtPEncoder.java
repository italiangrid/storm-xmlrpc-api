package it.grid.storm.xmlrpc.encoders;


import it.grid.storm.srm.types.TLifeTimeInSeconds;
import it.grid.storm.srm.types.TOverwriteMode;
import it.grid.storm.xmlrpc.remote.XmlRpcDefaults;
import it.grid.storm.xmlrpc.remote.XmlRpcParameters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michele Dibenedetto
 */
public class PtPEncoder
{
    
    private static final PtPEncoder instance = new PtPEncoder();

    protected PtPEncoder()
    {
    }


    public static PtPEncoder getInstance()
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
        encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, XmlRpcDefaults.TURL_PREFIX);
        return encoding;
    }

    public Map<String, Object> encodeOverwrite(String userDN, List<String> userFQANS, String surl)
            throws IllegalArgumentException
    {
        EncodingUtils.checkDN(userDN);
        EncodingUtils.checkFQANS(userFQANS);
        EncodingUtils.checkSurl(surl);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        encoding.put(XmlRpcParameters.SURL_KEY, surl);
        encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, XmlRpcDefaults.TURL_PREFIX);
        encoding.put(XmlRpcParameters.PTP_OVERWRITE_KEY, TOverwriteMode.ALWAYS.getValue());
        return encoding;
    }

    public Map<String, Object> encode(String userDN, List<String> userFQANS, String surl,
            List<String> transferProtocols) throws IllegalArgumentException
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

    public Map<String, Object> encodeOverwrite(String userDN, List<String> userFQANS, String surl,
            List<String> transferProtocols) throws IllegalArgumentException
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
        encoding.put(XmlRpcParameters.PTP_OVERWRITE_KEY, TOverwriteMode.ALWAYS.getValue());
        return encoding;
    }

    public Map<String, Object> encode(String userDN, List<String> userFQANS, String surl,
            TLifeTimeInSeconds desiredFileLifetime) throws IllegalArgumentException
    {
        EncodingUtils.checkDN(userDN);
        EncodingUtils.checkFQANS(userFQANS);
        EncodingUtils.checkSurl(surl);
        EncodingUtils.checkTime(desiredFileLifetime);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        encoding.put(XmlRpcParameters.SURL_KEY, surl);
        encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, XmlRpcDefaults.TURL_PREFIX);
        desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
        return encoding;
    }

    public Map<String, Object> encodeOverwrite(String userDN, List<String> userFQANS, String surl,
            TLifeTimeInSeconds desiredFileLifetime) throws IllegalArgumentException
    {
        EncodingUtils.checkDN(userDN);
        EncodingUtils.checkFQANS(userFQANS);
        EncodingUtils.checkSurl(surl);
        EncodingUtils.checkTime(desiredFileLifetime);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        encoding.put(XmlRpcParameters.SURL_KEY, surl);
        encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, XmlRpcDefaults.TURL_PREFIX);
        desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
        encoding.put(XmlRpcParameters.PTP_OVERWRITE_KEY, TOverwriteMode.ALWAYS.getValue());
        return encoding;
    }

    public Map<String, Object> encode(String userDN, List<String> userFQANS, String surl,
            List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime)
            throws IllegalArgumentException
    {
        EncodingUtils.checkDN(userDN);
        EncodingUtils.checkFQANS(userFQANS);
        EncodingUtils.checkSurl(surl);
        EncodingUtils.checkTime(desiredFileLifetime);
        EncodingUtils.checkTransferProtocols(transferProtocols);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        encoding.put(XmlRpcParameters.SURL_KEY, surl);
        encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, transferProtocols.toArray());
        desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
        return encoding;
    }

    public Map<String, Object> encodeOverwrite(String userDN, List<String> userFQANS, String surl,
            List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime)
            throws IllegalArgumentException
    {
        EncodingUtils.checkDN(userDN);
        EncodingUtils.checkFQANS(userFQANS);
        EncodingUtils.checkSurl(surl);
        EncodingUtils.checkTime(desiredFileLifetime);
        EncodingUtils.checkTransferProtocols(transferProtocols);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        encoding.put(XmlRpcParameters.SURL_KEY, surl);
        encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, transferProtocols.toArray());
        desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
        encoding.put(XmlRpcParameters.PTP_OVERWRITE_KEY, TOverwriteMode.ALWAYS.getValue());
        return encoding;
    }

    public Map<String, Object> encode(String userDN, List<String> userFQANS, String surl,
            TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime)
            throws IllegalArgumentException
    {
        EncodingUtils.checkDN(userDN);
        EncodingUtils.checkFQANS(userFQANS);
        EncodingUtils.checkSurl(surl);
        EncodingUtils.checkTime(desiredFileLifetime);
        EncodingUtils.checkTime(desiredPinLifetime);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        encoding.put(XmlRpcParameters.SURL_KEY, surl);
        encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, XmlRpcDefaults.TURL_PREFIX);
        desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
        desiredPinLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_PINLIFETIME);
        return encoding;
    }

    public Map<String, Object> encodeOverwrite(String userDN, List<String> userFQANS, String surl,
            TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime)
            throws IllegalArgumentException
    {
        EncodingUtils.checkDN(userDN);
        EncodingUtils.checkFQANS(userFQANS);
        EncodingUtils.checkSurl(surl);
        EncodingUtils.checkTime(desiredFileLifetime);
        EncodingUtils.checkTime(desiredPinLifetime);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        encoding.put(XmlRpcParameters.SURL_KEY, surl);
        encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, XmlRpcDefaults.TURL_PREFIX);
        desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
        desiredPinLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_PINLIFETIME);
        encoding.put(XmlRpcParameters.PTP_OVERWRITE_KEY, TOverwriteMode.ALWAYS.getValue());
        return encoding;
    }

    public Map<String, Object> encode(String userDN, List<String> userFQANS, String surl,
            List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime,
            TLifeTimeInSeconds desiredPinLifetime) throws IllegalArgumentException
    {
        EncodingUtils.checkDN(userDN);
        EncodingUtils.checkFQANS(userFQANS);
        EncodingUtils.checkSurl(surl);
        EncodingUtils.checkTransferProtocols(transferProtocols);
        EncodingUtils.checkTime(desiredFileLifetime);
        EncodingUtils.checkTime(desiredPinLifetime);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        encoding.put(XmlRpcParameters.SURL_KEY, surl);
        encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, transferProtocols.toArray());
        desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
        desiredPinLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_PINLIFETIME);
        return encoding;
    }

    public Map<String, Object> encodeOverwrite(String userDN, List<String> userFQANS, String surl,
            List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime,
            TLifeTimeInSeconds desiredPinLifetime) throws IllegalArgumentException
    {
        EncodingUtils.checkDN(userDN);
        EncodingUtils.checkFQANS(userFQANS);
        EncodingUtils.checkSurl(surl);
        EncodingUtils.checkTransferProtocols(transferProtocols);
        EncodingUtils.checkTime(desiredFileLifetime);
        EncodingUtils.checkTime(desiredPinLifetime);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        encoding.put(XmlRpcParameters.SURL_KEY, surl);
        encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, transferProtocols.toArray());
        desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
        desiredPinLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_PINLIFETIME);
        encoding.put(XmlRpcParameters.PTP_OVERWRITE_KEY, TOverwriteMode.ALWAYS.getValue());
        return encoding;
    }

}
