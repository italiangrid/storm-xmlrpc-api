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
public class PtPEncoder {

	private static final PtPEncoder instance = new PtPEncoder();

	protected PtPEncoder() {

	}

	public static PtPEncoder getInstance() {

		return instance;
	}

	public Map<String, Object> encode(String userDN, List<String> userFQANS,
		String surl) throws IllegalArgumentException {

		Map<String, Object> encoding = encode(userDN, surl);
		EncodingUtils.checkFQANS(userFQANS);

		encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
		return encoding;
	}

	public Map<String, Object> encode(String userDN, String surl) {

		Map<String, Object> encoding = encode(surl);
		EncodingUtils.checkDN(userDN);

		encoding.put(XmlRpcParameters.DN_KEY, userDN);
		return encoding;
	}

	public Map<String, Object> encode(String surl) {

		EncodingUtils.checkSurl(surl);

		Map<String, Object> encoding = new HashMap<String, Object>();
		encoding.put(XmlRpcParameters.SURL_KEY, surl);
		encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, XmlRpcDefaults.TURL_PREFIX);
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String userDN,
		List<String> userFQANS, String surl) throws IllegalArgumentException {

		Map<String, Object> encoding = encodeOverwrite(userDN, surl);
		EncodingUtils.checkFQANS(userFQANS);

		encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String userDN, String surl) {

		Map<String, Object> encoding = encodeOverwrite(surl);
		EncodingUtils.checkDN(userDN);

		encoding.put(XmlRpcParameters.DN_KEY, userDN);
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String surl) {

		EncodingUtils.checkSurl(surl);

		Map<String, Object> encoding = new HashMap<String, Object>();
		encoding.put(XmlRpcParameters.SURL_KEY, surl);
		encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, XmlRpcDefaults.TURL_PREFIX);
		encoding.put(XmlRpcParameters.PTP_OVERWRITE_KEY,
			TOverwriteMode.ALWAYS.getValue());
		return encoding;
	}

	public Map<String, Object> encode(String userDN, List<String> userFQANS,
		String surl, List<String> transferProtocols)
		throws IllegalArgumentException {

		Map<String, Object> encoding = encode(surl, transferProtocols);
		EncodingUtils.checkFQANS(userFQANS);

		encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
		return encoding;
	}

	public Map<String, Object> encode(String userDN, String surl,
		List<String> transferProtocols) {

		Map<String, Object> encoding = encode(surl, transferProtocols);
		EncodingUtils.checkDN(userDN);

		encoding.put(XmlRpcParameters.DN_KEY, userDN);
		return encoding;
	}

	public Map<String, Object> encode(String surl, List<String> transferProtocols) {

		EncodingUtils.checkSurl(surl);
		EncodingUtils.checkTransferProtocols(transferProtocols);

		Map<String, Object> encoding = new HashMap<String, Object>();
		encoding.put(XmlRpcParameters.SURL_KEY, surl);
		encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, transferProtocols.toArray());
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String userDN,
		List<String> userFQANS, String surl, List<String> transferProtocols)
		throws IllegalArgumentException {

		Map<String, Object> encoding = encodeOverwrite(userDN, surl,
			transferProtocols);
		EncodingUtils.checkFQANS(userFQANS);

		encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String userDN, String surl,
		List<String> transferProtocols) {

		Map<String, Object> encoding = encodeOverwrite(surl, transferProtocols);
		EncodingUtils.checkDN(userDN);

		encoding.put(XmlRpcParameters.DN_KEY, userDN);
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String surl,
		List<String> transferProtocols) {

		EncodingUtils.checkSurl(surl);
		EncodingUtils.checkTransferProtocols(transferProtocols);

		Map<String, Object> encoding = new HashMap<String, Object>();
		encoding.put(XmlRpcParameters.SURL_KEY, surl);
		encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, transferProtocols.toArray());
		encoding.put(XmlRpcParameters.PTP_OVERWRITE_KEY,
			TOverwriteMode.ALWAYS.getValue());
		return encoding;
	}

	public Map<String, Object> encode(String userDN, List<String> userFQANS,
		String surl, TLifeTimeInSeconds desiredFileLifetime)
		throws IllegalArgumentException {

		Map<String, Object> encoding = encode(userDN, surl, desiredFileLifetime);
		EncodingUtils.checkFQANS(userFQANS);

		encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
		return encoding;
	}

	public Map<String, Object> encode(String userDN, String surl,
		TLifeTimeInSeconds desiredFileLifetime) {

		Map<String, Object> encoding = encode(surl, desiredFileLifetime);
		EncodingUtils.checkDN(userDN);

		encoding.put(XmlRpcParameters.DN_KEY, userDN);
		return encoding;
	}

	public Map<String, Object> encode(String surl,
		TLifeTimeInSeconds desiredFileLifetime) {

		EncodingUtils.checkSurl(surl);
		EncodingUtils.checkTime(desiredFileLifetime);

		Map<String, Object> encoding = new HashMap<String, Object>();
		encoding.put(XmlRpcParameters.SURL_KEY, surl);
		encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, XmlRpcDefaults.TURL_PREFIX);
		desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String userDN,
		List<String> userFQANS, String surl, TLifeTimeInSeconds desiredFileLifetime)
		throws IllegalArgumentException {

		Map<String, Object> encoding = encodeOverwrite(userDN, surl,
			desiredFileLifetime);
		EncodingUtils.checkFQANS(userFQANS);

		encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String userDN, String surl,
		TLifeTimeInSeconds desiredFileLifetime) {

		Map<String, Object> encoding = encodeOverwrite(surl, desiredFileLifetime);
		EncodingUtils.checkDN(userDN);

		encoding.put(XmlRpcParameters.DN_KEY, userDN);
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String surl,
		TLifeTimeInSeconds desiredFileLifetime) {

		EncodingUtils.checkSurl(surl);
		EncodingUtils.checkTime(desiredFileLifetime);

		Map<String, Object> encoding = new HashMap<String, Object>();
		encoding.put(XmlRpcParameters.SURL_KEY, surl);
		encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, XmlRpcDefaults.TURL_PREFIX);
		desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
		encoding.put(XmlRpcParameters.PTP_OVERWRITE_KEY,
			TOverwriteMode.ALWAYS.getValue());
		return encoding;
	}

	public Map<String, Object> encode(String userDN, List<String> userFQANS,
		String surl, List<String> transferProtocols,
		TLifeTimeInSeconds desiredFileLifetime) throws IllegalArgumentException {

		Map<String, Object> encoding = encode(userDN, surl, transferProtocols,
			desiredFileLifetime);
		EncodingUtils.checkFQANS(userFQANS);
		encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
		return encoding;
	}

	public Map<String, Object> encode(String userDN, String surl,
		List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime) {

		Map<String, Object> encoding = encode(surl, transferProtocols,
			desiredFileLifetime);
		EncodingUtils.checkDN(userDN);

		encoding.put(XmlRpcParameters.DN_KEY, userDN);
		return encoding;
	}

	public Map<String, Object> encode(String surl,
		List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime) {

		EncodingUtils.checkSurl(surl);
		EncodingUtils.checkTime(desiredFileLifetime);
		EncodingUtils.checkTransferProtocols(transferProtocols);

		Map<String, Object> encoding = new HashMap<String, Object>();
		encoding.put(XmlRpcParameters.SURL_KEY, surl);
		encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, transferProtocols.toArray());
		desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String userDN,
		List<String> userFQANS, String surl, List<String> transferProtocols,
		TLifeTimeInSeconds desiredFileLifetime) throws IllegalArgumentException {

		Map<String, Object> encoding = encodeOverwrite(userDN, surl,
			transferProtocols, desiredFileLifetime);
		EncodingUtils.checkFQANS(userFQANS);

		encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String userDN, String surl,
		List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime) {

		Map<String, Object> encoding = encodeOverwrite(surl, transferProtocols,
			desiredFileLifetime);
		EncodingUtils.checkDN(userDN);

		encoding.put(XmlRpcParameters.DN_KEY, userDN);
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String surl,
		List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime) {

		EncodingUtils.checkSurl(surl);
		EncodingUtils.checkTime(desiredFileLifetime);
		EncodingUtils.checkTransferProtocols(transferProtocols);

		Map<String, Object> encoding = new HashMap<String, Object>();
		encoding.put(XmlRpcParameters.SURL_KEY, surl);
		encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, transferProtocols.toArray());
		desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
		encoding.put(XmlRpcParameters.PTP_OVERWRITE_KEY,
			TOverwriteMode.ALWAYS.getValue());
		return encoding;
	}

	public Map<String, Object> encode(String userDN, List<String> userFQANS,
		String surl, TLifeTimeInSeconds desiredFileLifetime,
		TLifeTimeInSeconds desiredPinLifetime) throws IllegalArgumentException {

		Map<String, Object> encoding = encode(userDN, surl, desiredFileLifetime,
			desiredPinLifetime);
		EncodingUtils.checkFQANS(userFQANS);

		encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
		return encoding;
	}

	public Map<String, Object> encode(String userDN, String surl,
		TLifeTimeInSeconds desiredFileLifetime,
		TLifeTimeInSeconds desiredPinLifetime) {

		Map<String, Object> encoding = encode(surl, desiredFileLifetime,
			desiredPinLifetime);
		EncodingUtils.checkDN(userDN);

		encoding.put(XmlRpcParameters.DN_KEY, userDN);
		return encoding;
	}

	public Map<String, Object> encode(String surl,
		TLifeTimeInSeconds desiredFileLifetime,
		TLifeTimeInSeconds desiredPinLifetime) {

		EncodingUtils.checkSurl(surl);
		EncodingUtils.checkTime(desiredFileLifetime);
		EncodingUtils.checkTime(desiredPinLifetime);

		Map<String, Object> encoding = new HashMap<String, Object>();
		encoding.put(XmlRpcParameters.SURL_KEY, surl);
		encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, XmlRpcDefaults.TURL_PREFIX);
		desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
		desiredPinLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_PINLIFETIME);
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String userDN,
		List<String> userFQANS, String surl,
		TLifeTimeInSeconds desiredFileLifetime,
		TLifeTimeInSeconds desiredPinLifetime) throws IllegalArgumentException {

		Map<String, Object> encoding = encodeOverwrite(userDN, surl,
			desiredFileLifetime, desiredPinLifetime);
		EncodingUtils.checkFQANS(userFQANS);

		encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String userDN, String surl,
		TLifeTimeInSeconds desiredFileLifetime,
		TLifeTimeInSeconds desiredPinLifetime) {

		Map<String, Object> encoding = encodeOverwrite(surl, desiredFileLifetime,
			desiredPinLifetime);
		EncodingUtils.checkDN(userDN);

		encoding.put(XmlRpcParameters.DN_KEY, userDN);
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String surl,
		TLifeTimeInSeconds desiredFileLifetime,
		TLifeTimeInSeconds desiredPinLifetime) {

		EncodingUtils.checkSurl(surl);
		EncodingUtils.checkTime(desiredFileLifetime);
		EncodingUtils.checkTime(desiredPinLifetime);

		Map<String, Object> encoding = new HashMap<String, Object>();
		encoding.put(XmlRpcParameters.SURL_KEY, surl);
		encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, XmlRpcDefaults.TURL_PREFIX);
		desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
		desiredPinLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_PINLIFETIME);
		encoding.put(XmlRpcParameters.PTP_OVERWRITE_KEY,
			TOverwriteMode.ALWAYS.getValue());
		return encoding;
	}

	public Map<String, Object> encode(String userDN, List<String> userFQANS,
		String surl, List<String> transferProtocols,
		TLifeTimeInSeconds desiredFileLifetime,
		TLifeTimeInSeconds desiredPinLifetime) throws IllegalArgumentException {

		Map<String, Object> encoding = encode(userDN, surl, transferProtocols,
			desiredFileLifetime, desiredPinLifetime);
		EncodingUtils.checkFQANS(userFQANS);

		encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
		return encoding;
	}

	public Map<String, Object> encode(String userDN, String surl,
		List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime,
		TLifeTimeInSeconds desiredPinLifetime) {

		Map<String, Object> encoding = encode(surl, transferProtocols,
			desiredFileLifetime, desiredPinLifetime);
		EncodingUtils.checkDN(userDN);

		encoding.put(XmlRpcParameters.DN_KEY, userDN);
		return encoding;
	}

	public Map<String, Object> encode(String surl,
		List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime,
		TLifeTimeInSeconds desiredPinLifetime) {

		EncodingUtils.checkSurl(surl);
		EncodingUtils.checkTransferProtocols(transferProtocols);
		EncodingUtils.checkTime(desiredFileLifetime);
		EncodingUtils.checkTime(desiredPinLifetime);

		Map<String, Object> encoding = new HashMap<String, Object>();
		encoding.put(XmlRpcParameters.SURL_KEY, surl);
		encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, transferProtocols.toArray());
		desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
		desiredPinLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_PINLIFETIME);
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String userDN,
		List<String> userFQANS, String surl, List<String> transferProtocols,
		TLifeTimeInSeconds desiredFileLifetime,
		TLifeTimeInSeconds desiredPinLifetime) throws IllegalArgumentException {

		Map<String, Object> encoding = encodeOverwrite(userDN, surl,
			transferProtocols, desiredFileLifetime, desiredPinLifetime);
		EncodingUtils.checkFQANS(userFQANS);

		encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String userDN, String surl,
		List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime,
		TLifeTimeInSeconds desiredPinLifetime) {

		Map<String, Object> encoding = encodeOverwrite(surl, transferProtocols,
			desiredFileLifetime, desiredPinLifetime);
		EncodingUtils.checkDN(userDN);

		encoding.put(XmlRpcParameters.DN_KEY, userDN);
		return encoding;
	}

	public Map<String, Object> encodeOverwrite(String surl,
		List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime,
		TLifeTimeInSeconds desiredPinLifetime) {

		EncodingUtils.checkSurl(surl);
		EncodingUtils.checkTransferProtocols(transferProtocols);
		EncodingUtils.checkTime(desiredFileLifetime);
		EncodingUtils.checkTime(desiredPinLifetime);

		Map<String, Object> encoding = new HashMap<String, Object>();
		encoding.put(XmlRpcParameters.SURL_KEY, surl);
		encoding.put(XmlRpcParameters.TURL_PREFIX_KEY, transferProtocols.toArray());
		desiredFileLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_FILELIFETIME);
		desiredPinLifetime.encode(encoding, TLifeTimeInSeconds.PNAME_PINLIFETIME);
		encoding.put(XmlRpcParameters.PTP_OVERWRITE_KEY,
			TOverwriteMode.ALWAYS.getValue());
		return encoding;
	}
}
