package it.grid.storm.xmlrpc.encoders;

import it.grid.storm.xmlrpc.remote.XmlRpcParameters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MvEncoder {

	private static final MvEncoder instance = new MvEncoder();

	protected MvEncoder() {

	}

	public static MvEncoder getInstance() {

		return instance;
	}

	public Map<String, Object> encode(String userDN, List<String> userFQANS,
		String fromSurl, String toSurl) throws IllegalArgumentException {

		Map<String, Object> encoding = encode(userDN, fromSurl, toSurl);
		EncodingUtils.checkFQANS(userFQANS);

		encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
		return encoding;
	}

	public Map<String, Object> encode(String userDN, String fromSurl,
		String toSurl) {

		Map<String, Object> encoding = encode(fromSurl, toSurl);
		EncodingUtils.checkDN(userDN);

		encoding.put(XmlRpcParameters.DN_KEY, userDN);
		return encoding;
	}

	public Map<String, Object> encode(String fromSurl, String toSurl) {

		EncodingUtils.checkSurl(fromSurl);
		EncodingUtils.checkSurl(toSurl);

		Map<String, Object> encoding = new HashMap<String, Object>();
		encoding.put(XmlRpcParameters.MV_FROM_SURL_KEY, fromSurl);
		encoding.put(XmlRpcParameters.MV_TO_SURL_KEY, toSurl);
		return encoding;
	}
}
