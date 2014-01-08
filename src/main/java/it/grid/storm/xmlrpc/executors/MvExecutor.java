package it.grid.storm.xmlrpc.executors;

import it.grid.storm.xmlrpc.ApiException;
import it.grid.storm.xmlrpc.decoders.DecodingException;
import it.grid.storm.xmlrpc.decoders.SympleStatusDecoder;
import it.grid.storm.xmlrpc.encoders.MvEncoder;
import it.grid.storm.xmlrpc.outputdata.OutputData;
import it.grid.storm.xmlrpc.outputdata.RequestOutputData;
import it.grid.storm.xmlrpc.remote.synchcall;
import java.util.List;
import java.util.Map;
import redstone.xmlrpc.XmlRpcFault;

public class MvExecutor {

	public static RequestOutputData execute(synchcall storm, String userDN,
		List<String> userFQANS, String fromSurl, String toSurl) throws ApiException {

		if (storm == null || userDN == null || userFQANS == null
			|| userFQANS.isEmpty() || fromSurl == null || toSurl == null) {
			throw new IllegalArgumentException(
				"Unable to call mv command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " userFQANS=" + userFQANS + " fromSurl=" + fromSurl + " toSurl="
					+ toSurl);
		}
		Map<String, Object> parameters;
		try {
			parameters = MvEncoder.getInstance().encode(userDN, userFQANS, fromSurl,
				toSurl);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode mv parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static RequestOutputData execute(synchcall storm, String userDN,
		String fromSurl, String toSurl) throws ApiException {

		if (storm == null || userDN == null || fromSurl == null || toSurl == null) {
			throw new IllegalArgumentException(
				"Unable to call mv command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " fromSurl=" + fromSurl + " toSurl=" + toSurl);
		}
		Map<String, Object> parameters;
		try {
			parameters = MvEncoder.getInstance().encode(userDN, fromSurl, toSurl);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode mv parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static RequestOutputData execute(synchcall storm, String fromSurl,
		String toSurl) throws ApiException {

		if (storm == null || fromSurl == null || toSurl == null) {
			throw new IllegalArgumentException(
				"Unable to call mv command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " fromSurl=" + fromSurl
					+ " toSurl=" + toSurl);
		}
		Map<String, Object> parameters;
		try {
			parameters = MvEncoder.getInstance().encode(fromSurl, toSurl);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode mv parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	private static RequestOutputData doIt(synchcall storm,
		Map<String, Object> parameters) throws ApiException {

		Map<String, Object> output;
		try {
			output = storm.mv(parameters);
		} catch (XmlRpcFault e) {
			throw new ApiException("Unable to perform mv call. XmlRpcFault: "
				+ e.getMessage());
		}
		try {
			return SympleStatusDecoder.getInstance().decode(output);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to decode mv call output. IllegalArgumentException: "
					+ e.getMessage());
		} catch (DecodingException e) {
			throw new ApiException(
				"Unable to decode mv call output. DecodingException: " + e.getMessage());
		}
	}

}
