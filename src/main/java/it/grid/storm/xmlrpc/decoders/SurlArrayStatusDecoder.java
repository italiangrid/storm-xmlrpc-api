package it.grid.storm.xmlrpc.decoders;

import it.grid.storm.srm.types.TReturnStatus;
import it.grid.storm.xmlrpc.outputdata.SurlArrayRequestOutputData;
import it.grid.storm.xmlrpc.outputdata.util.SurlStatus;
import it.grid.storm.xmlrpc.remote.XmlRpcParameters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Michele Dibenedetto
 */
public class SurlArrayStatusDecoder implements OutputDecoder {

	private static volatile SurlArrayStatusDecoder instance = new SurlArrayStatusDecoder();

	private SurlArrayStatusDecoder() {

	}

	public static synchronized SurlArrayStatusDecoder getInstance() {

		return instance;
	}

	public SurlArrayRequestOutputData decode(Map<String, Object> output)
		throws IllegalArgumentException, DecodingException {

		if (output == null) {
			throw new IllegalArgumentException("Unable to build the instance. "
				+ "Received null argument: output=" + output);
		}
		if (output.get(XmlRpcParameters.ARRAYOF_FILE_STATUSES_KEY) == null
			|| output.get(TReturnStatus.PNAME_RETURNSTATUS) == null) {
			throw new DecodingException(
				"Unable to decode the output. Missing mandatory arguments: output="
					+ output);
		}
		TReturnStatus decodedStatus;
		try {
			decodedStatus = TReturnStatus.decode((Map<String, Object>) output
				.get(TReturnStatus.PNAME_RETURNSTATUS));
		} catch (IllegalArgumentException e) {
			throw new DecodingException("Unable to decode the Return status \'"
				+ output.get(TReturnStatus.PNAME_STATUS)
				+ "\'. IllegalArgumentException: " + e.getMessage());
		}

		List<Map<String, Object>> surlSatuses = (List<Map<String, Object>>) output
			.get(XmlRpcParameters.ARRAYOF_FILE_STATUSES_KEY);
		ArrayList<SurlStatus> statuses = new ArrayList<SurlStatus>(
			surlSatuses.size());
		TReturnStatus decodedFileStatus;
		String decodedSurl;
		for (Map<String, Object> surlSatus : surlSatuses) {
			decodedSurl = (String) surlSatus.get(XmlRpcParameters.SURL_KEY);
			if (decodedSurl == null
				|| surlSatus.get(TReturnStatus.PNAME_STATUS) == null) {
				throw new DecodingException(
					"Unable to decode the surl status output. Missing mandatory fields: surlSatus="
						+ surlSatus);
			}

			try {
				decodedFileStatus = TReturnStatus
					.decode((Map<String, Object>) surlSatus
						.get(TReturnStatus.PNAME_STATUS));
			} catch (IllegalArgumentException e) {
				throw new DecodingException(
					"Unable to decode the surl status Return status \'"
						+ surlSatus.get(TReturnStatus.PNAME_STATUS)
						+ "\'. IllegalArgumentException: " + e.getMessage());
			}

			statuses.add(new SurlStatus(decodedSurl, decodedFileStatus));
		}
		return new SurlArrayRequestOutputData(decodedStatus, statuses);
	}

}
