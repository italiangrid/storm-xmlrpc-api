package it.grid.storm.xmlrpc.decoders;

import it.grid.storm.srm.types.TReturnStatus;
import it.grid.storm.xmlrpc.outputdata.RequestOutputData;
import java.util.Map;

public class SympleStatusDecoder implements OutputDecoder {

	private static volatile SympleStatusDecoder instance = new SympleStatusDecoder();

	private SympleStatusDecoder() {

	}

	public static synchronized SympleStatusDecoder getInstance() {

		return instance;
	}

	public RequestOutputData decode(Map<String, Object> output)
		throws DecodingException, IllegalArgumentException {

		if (output == null) {
			throw new IllegalArgumentException("Unable to build the instance. "
				+ "Received null argument: output=" + output);
		}
		if (output.get(TReturnStatus.PNAME_RETURNSTATUS) == null) {
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
		return new RequestOutputData(decodedStatus);
	}
}
