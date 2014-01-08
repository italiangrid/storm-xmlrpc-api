package it.grid.storm.xmlrpc.executors;

import it.grid.storm.srm.types.TRequestToken;
import it.grid.storm.xmlrpc.ApiException;
import it.grid.storm.xmlrpc.decoders.DecodingException;
import it.grid.storm.xmlrpc.decoders.SurlArrayStatusDecoder;
import it.grid.storm.xmlrpc.encoders.ManageFileTransferEncoder;
import it.grid.storm.xmlrpc.outputdata.SurlArrayRequestOutputData;
import it.grid.storm.xmlrpc.remote.synchcall;
import java.util.List;
import java.util.Map;
import redstone.xmlrpc.XmlRpcFault;

public class ReleaseFilesExecutor {

	public static SurlArrayRequestOutputData execute(synchcall storm,
		String userDN, List<String> userFQANS, List<String> surls,
		TRequestToken requestToken) throws ApiException {

		if (storm == null || userDN == null || userFQANS == null
			|| userFQANS.isEmpty() || surls == null || surls.isEmpty()
			|| requestToken == null) {
			throw new IllegalArgumentException(
				"Unable to call rf command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " userFQANS=" + userFQANS + " surls=" + surls + " requestToken="
					+ requestToken);
		}
		Map<String, Object> parameters;
		try {
			parameters = ManageFileTransferEncoder.getInstance().encodeWithSurls(
				userDN, userFQANS, surls, requestToken);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode rf parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static SurlArrayRequestOutputData execute(synchcall storm,
		String userDN, List<String> userFQANS, List<String> surls)
		throws ApiException {

		if (storm == null || userDN == null || userFQANS == null
			|| userFQANS.isEmpty() || surls == null || surls.isEmpty()) {
			throw new IllegalArgumentException(
				"Unable to call rf command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " userFQANS=" + userFQANS + " surls=" + surls);
		}
		Map<String, Object> parameters;
		try {
			parameters = ManageFileTransferEncoder.getInstance().encode(userDN,
				userFQANS, surls);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode rf parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static SurlArrayRequestOutputData execute(synchcall storm,
		String userDN, List<String> surls, TRequestToken requestToken)
		throws ApiException {

		if (storm == null || userDN == null || surls == null || surls.isEmpty()
			|| requestToken == null) {
			throw new IllegalArgumentException(
				"Unable to call rf command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " surls=" + surls + " requestToken=" + requestToken);
		}
		Map<String, Object> parameters;
		try {
			parameters = ManageFileTransferEncoder.getInstance().encodeWithSurls(
				userDN, surls, requestToken);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode rf parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static SurlArrayRequestOutputData execute(synchcall storm,
		String userDN, List<String> surls) throws ApiException {

		if (storm == null || userDN == null || surls == null || surls.isEmpty()) {
			throw new IllegalArgumentException(
				"Unable to call rf command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " surls=" + surls);
		}
		Map<String, Object> parameters;
		try {
			parameters = ManageFileTransferEncoder.getInstance()
				.encode(userDN, surls);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode rf parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static SurlArrayRequestOutputData execute(synchcall storm,
		List<String> surls, TRequestToken requestToken) throws ApiException {

		if (storm == null || surls == null || surls.isEmpty()
			|| requestToken == null) {
			throw new IllegalArgumentException(
				"Unable to call rf command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " surls=" + surls
					+ " requestToken=" + requestToken);
		}
		Map<String, Object> parameters;
		try {
			parameters = ManageFileTransferEncoder.getInstance().encodeWithSurls(
				surls, requestToken);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode rf parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static SurlArrayRequestOutputData execute(synchcall storm,
		List<String> surls) throws ApiException {

		if (storm == null || surls == null || surls.isEmpty()) {
			throw new IllegalArgumentException(
				"Unable to call rf command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " surls=" + surls);
		}
		Map<String, Object> parameters;
		try {
			parameters = ManageFileTransferEncoder.getInstance().encode(surls);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode rf parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	private static SurlArrayRequestOutputData doIt(synchcall storm,
		Map<String, Object> parameters) throws ApiException {

		Map<String, Object> output;
		try {
			output = storm.releaseFiles(parameters);
		} catch (XmlRpcFault e) {
			throw new ApiException("Unable to perform rf call. XmlRpcFault: "
				+ e.getMessage());
		}
		try {
			return SurlArrayStatusDecoder.getInstance().decode(output);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to decode rf call output. IllegalArgumentException: "
					+ e.getMessage());
		} catch (DecodingException e) {
			throw new ApiException(
				"Unable to decode rf call output. DecodingException: " + e.getMessage());
		}
	}

}
