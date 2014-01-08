package it.grid.storm.xmlrpc.encoders;

import it.grid.storm.srm.types.RecursionLevel;
import it.grid.storm.xmlrpc.remote.XmlRpcParameters;
import java.util.List;
import java.util.Map;

public class LsEncoder extends SurlArrayRequestEncoder {

	private static final LsEncoder instance = new LsEncoder();

	protected LsEncoder() {

	}

	public static LsEncoder getInstance() {

		return instance;
	}

	public Map<String, Object> encodeDetailed(String userDN,
		List<String> userFQANS, List<String> surls) {

		Map<String, Object> encoding = super.encode(userDN, userFQANS, surls);
		setDetailed(encoding);
		return encoding;
	}

	public Map<String, Object> encodeDetailed(String userDN, List<String> surls) {

		Map<String, Object> encoding = super.encode(userDN, surls);
		setDetailed(encoding);
		return encoding;
	}

	public Map<String, Object> encodeDetailed(List<String> surls) {

		Map<String, Object> encoding = super.encode(surls);
		setDetailed(encoding);
		return encoding;
	}

	public Map<String, Object> encode(String userDN, List<String> userFQANS,
		List<String> surls, RecursionLevel recursion) {

		Map<String, Object> encoding = super.encode(userDN, userFQANS, surls);
		enrich(encoding, recursion);
		return encoding;
	}

	public Map<String, Object> encode(String userDN, List<String> surls,
		RecursionLevel recursion) {

		Map<String, Object> encoding = super.encode(userDN, surls);
		enrich(encoding, recursion);
		return encoding;
	}

	public Map<String, Object> encode(List<String> surls, RecursionLevel recursion) {

		Map<String, Object> encoding = super.encode(surls);
		enrich(encoding, recursion);
		return encoding;
	}

	public Map<String, Object> encodeDetailed(String userDN,
		List<String> userFQANS, List<String> surls, RecursionLevel recursion) {

		Map<String, Object> encoding = super.encode(userDN, userFQANS, surls);
		enrich(encoding, recursion);
		setDetailed(encoding);
		return encoding;
	}

	public Map<String, Object> encodeDetailed(String userDN, List<String> surls,
		RecursionLevel recursion) {

		Map<String, Object> encoding = super.encode(userDN, surls);
		enrich(encoding, recursion);
		setDetailed(encoding);
		return encoding;
	}

	public Map<String, Object> encodeDetailed(List<String> surls,
		RecursionLevel recursion) {

		Map<String, Object> encoding = super.encode(surls);
		enrich(encoding, recursion);
		setDetailed(encoding);
		return encoding;
	}

	public Map<String, Object> encode(String userDN, List<String> userFQANS,
		List<String> surls, Integer maxReturnedEntries) {

		Map<String, Object> encoding = super.encode(userDN, userFQANS, surls);

		enrich(encoding, maxReturnedEntries);
		return encoding;
	}

	public Map<String, Object> encode(String userDN, List<String> surls,
		Integer maxReturnedEntries) {

		Map<String, Object> encoding = super.encode(userDN, surls);

		enrich(encoding, maxReturnedEntries);
		return encoding;
	}

	public Map<String, Object> encode(List<String> surls,
		Integer maxReturnedEntries) {

		Map<String, Object> encoding = super.encode(surls);

		enrich(encoding, maxReturnedEntries);
		return encoding;
	}

	public Map<String, Object> encodeDetailed(String userDN,
		List<String> userFQANS, List<String> surls, Integer maxReturnedEntries) {

		Map<String, Object> encoding = super.encode(userDN, userFQANS, surls);

		enrich(encoding, maxReturnedEntries);
		setDetailed(encoding);
		return encoding;
	}

	public Map<String, Object> encodeDetailed(String userDN, List<String> surls,
		Integer maxReturnedEntries) {

		Map<String, Object> encoding = super.encode(userDN, surls);

		enrich(encoding, maxReturnedEntries);
		setDetailed(encoding);
		return encoding;
	}

	public Map<String, Object> encodeDetailed(List<String> surls,
		Integer maxReturnedEntries) {

		Map<String, Object> encoding = super.encode(surls);

		enrich(encoding, maxReturnedEntries);
		setDetailed(encoding);
		return encoding;
	}

	public Map<String, Object> encode(String userDN, List<String> userFQANS,
		List<String> surls, RecursionLevel recursion, Integer maxReturnedEntries) {

		Map<String, Object> encoding = super.encode(userDN, userFQANS, surls);

		enrich(encoding, recursion, maxReturnedEntries);
		return encoding;
	}

	public Map<String, Object> encode(String userDN, List<String> surls,
		RecursionLevel recursion, Integer maxReturnedEntries) {

		Map<String, Object> encoding = super.encode(userDN, surls);

		enrich(encoding, recursion, maxReturnedEntries);
		return encoding;
	}

	public Map<String, Object> encode(List<String> surls,
		RecursionLevel recursion, Integer maxReturnedEntries) {

		Map<String, Object> encoding = super.encode(surls);

		enrich(encoding, recursion, maxReturnedEntries);
		return encoding;
	}

	public Map<String, Object> encodeDetailed(String userDN,
		List<String> userFQANS, List<String> surls, RecursionLevel recursion,
		Integer maxReturnedEntries) {

		Map<String, Object> encoding = super.encode(userDN, userFQANS, surls);

		enrich(encoding, recursion, maxReturnedEntries);
		setDetailed(encoding);
		return encoding;
	}

	public Map<String, Object> encodeDetailed(String userDN, List<String> surls,
		RecursionLevel recursion, Integer maxReturnedEntries) {

		Map<String, Object> encoding = super.encode(userDN, surls);

		enrich(encoding, recursion, maxReturnedEntries);
		setDetailed(encoding);
		return encoding;
	}

	public Map<String, Object> encodeDetailed(List<String> surls,
		RecursionLevel recursion, Integer maxReturnedEntries) {

		Map<String, Object> encoding = super.encode(surls);

		enrich(encoding, recursion, maxReturnedEntries);
		setDetailed(encoding);
		return encoding;
	}

	private void enrich(Map<String, Object> encoding, RecursionLevel recursion,
		Integer maxReturnedEntries) {

		enrich(encoding, recursion);
		enrich(encoding, maxReturnedEntries);
	}

	private void enrich(Map<String, Object> encoding, Integer maxReturnedEntries) {

		EncodingUtils.checkAmount(maxReturnedEntries);
		encoding.put(XmlRpcParameters.LS_MAX_RETURNED_ENTRIES_KEY,
			maxReturnedEntries);
	}

	private void enrich(Map<String, Object> encoding, RecursionLevel recursion) {

		EncodingUtils.checkRecursionLevel(recursion);

		encoding.put(XmlRpcParameters.LS_FULL_RECURSIVE_KEY,
			!recursion.isRecursionLimited());
		if (recursion.isRecursionAllowed() && recursion.isRecursionLimited()) {
			encoding.put(XmlRpcParameters.LS_RECURSION_LEVELS_KEY,
				recursion.getRecursionLevel());
		}
		if (recursion.isRecursionAllowed() && recursion.hasStartingDepth()) {
			encoding.put(XmlRpcParameters.LS_STARTING_RECURSION_DEPTH_KEY,
				recursion.getStartingDepth());
		}
	}

	private void setDetailed(Map<String, Object> encoding) {

		encoding.put(XmlRpcParameters.LS_DETAILED_KEY, Boolean.TRUE);
	}

}
