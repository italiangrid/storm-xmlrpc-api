package it.grid.storm.xmlrpc.encoders;

import it.grid.storm.xmlrpc.remote.XmlRpcDefaults;
import it.grid.storm.xmlrpc.remote.XmlRpcParameters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PtGEncoder {

	private static final PtGEncoder instance = new PtGEncoder();

	protected PtGEncoder() {

	}

	public static PtGEncoder getInstance() {

		return instance;
	}

	public Map<String, Object> encode(String userDN, List<String> userFQANS,
		String surl) {

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

	public Map<String, Object> encode(String userDN, List<String> userFQANS,
		String surl, List<String> transferProtocols) {

		Map<String, Object> encoding = encode(userDN, surl, transferProtocols);
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
}
