package it.grid.storm.xmlrpc.decoders;

import it.grid.storm.srm.types.TLifeTimeInSeconds;
import it.grid.storm.srm.types.TRequestToken;
import it.grid.storm.srm.types.TReturnStatus;
import it.grid.storm.srm.types.TSizeInBytes;
import it.grid.storm.xmlrpc.outputdata.PtGOutputData;
import it.grid.storm.xmlrpc.remote.XmlRpcParameters;
import java.util.Map;

public class PtGDecoder implements OutputDecoder {

	private static volatile PtGDecoder instance = new PtGDecoder();

	private PtGDecoder() {

	}

	public static synchronized PtGDecoder getInstance() {

		return instance;
	}

	public PtGOutputData decode(Map<String, Object> output)
		throws DecodingException, IllegalArgumentException {

		if (output == null) {
			throw new IllegalArgumentException("Unable to build the instance. "
				+ "Received null argument: output=" + output);
		}
		if (output.get(XmlRpcParameters.SURL_KEY) == null
			|| output.get(XmlRpcParameters.TURL_KEY) == null
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
		String surl = (String) output.get(XmlRpcParameters.SURL_KEY);
		if (surl == null) {
			throw new DecodingException("Unable to decode the SURL. Null "
				+ XmlRpcParameters.SURL_KEY + " attribute");
		}
		String turl = (String) output.get(XmlRpcParameters.TURL_KEY);
		if (turl == null) {
			throw new DecodingException("Unable to decode the TURL. Null "
				+ XmlRpcParameters.TURL_KEY + " attribute");
		}
		TRequestToken token;
		try {
			token = TRequestToken.decode(output, TRequestToken.PNAME_REQUESTOKEN);
		} catch (IllegalArgumentException e) {
			throw new DecodingException(
				"Unable to decode the token. IllegalArgumentException : "
					+ e.getMessage());
		}
		PtGOutputData data = new PtGOutputData(surl, turl, decodedStatus, token);

		if (output.containsKey(TSizeInBytes.PNAME_SIZE)) {
			if (output.get(TSizeInBytes.PNAME_SIZE) == null) {
				throw new DecodingException("Unable to decode the output. Null "
					+ TSizeInBytes.PNAME_SIZE + " attribute");
			}
			data.setSize(TSizeInBytes.decode(output, TSizeInBytes.PNAME_SIZE));
		}
		if (output.containsKey(TLifeTimeInSeconds.PNAME_PINLIFETIME)) {
			if (output.get(TLifeTimeInSeconds.PNAME_PINLIFETIME) == null) {
				throw new DecodingException("Unable to decode the output. Null "
					+ TLifeTimeInSeconds.PNAME_PINLIFETIME + " attribute");
			}
			data.setPinLifetime(TLifeTimeInSeconds.decode(output,
				TLifeTimeInSeconds.PNAME_PINLIFETIME));
		}
		return data;
	}

}
