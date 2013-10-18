package it.grid.storm.xmlrpc.encoders;

import it.grid.storm.srm.types.RecursionLevel;
import it.grid.storm.srm.types.TLifeTimeInSeconds;
import it.grid.storm.srm.types.TRequestToken;
import java.util.List;

/**
 * @author Michele Dibenedetto
 * 
 */
public class EncodingUtils {

	public static void checkDN(String userDN) throws IllegalArgumentException {

		if (userDN == null || userDN.trim().isEmpty()) {
			throw new IllegalArgumentException("Unable to encode the parameters. "
				+ "Received empty DN argument: userDN=" + userDN);
		}
	}

	public static void checkFQANS(List<String> userFQANS)
		throws IllegalArgumentException {

		if (userFQANS == null || userFQANS.isEmpty()) {
			throw new IllegalArgumentException("Unable to encode the parameters. "
				+ "Received empty FQANS argument: FQANS=" + userFQANS);
		}
		for (String userFQAN : userFQANS) {
			if (userFQAN == null || userFQAN.trim().isEmpty()) {
				throw new IllegalArgumentException("Unable to encode the parameters."
					+ " Received empty fqan element: userFQANS=" + userFQANS);
			}
		}
	}

	public static void checkSurl(String surl) throws IllegalArgumentException {

		if (surl == null || surl.trim().isEmpty()) {
			throw new IllegalArgumentException("Unable to encode the parameters. "
				+ "Received empty SURL argument: SURL=" + surl);
		}
	}

	public static void checkSurls(List<String> surls)
		throws IllegalArgumentException {

		if (surls == null || surls.isEmpty()) {
			throw new IllegalArgumentException("Unable to encode the parameters. "
				+ "Received empty surls argument: surls=" + surls);
		}
		for (String surl : surls) {
			if (surl == null || surl.trim().isEmpty()) {
				throw new IllegalArgumentException("Unable to encode the parameters."
					+ " Received empty surl element: surls=" + surls);
			}
		}
	}

	public static void checkToken(TRequestToken requestToken)
		throws IllegalArgumentException {

		if (requestToken == null || requestToken.getValue() == null
			|| requestToken.getValue().trim().isEmpty()) {
			throw new IllegalArgumentException("Unable to encode the parameters. "
				+ "Received empty Request Token argument: requestToken=" + requestToken);
		}
	}

	public static void checkRecursionLevel(RecursionLevel recursion)
		throws IllegalArgumentException {

		if (recursion == null) {
			throw new IllegalArgumentException("Unable to encode the parameters. "
				+ "Received null Recursion Level argument: recursion=" + recursion);
		}
	}

	public static void checkAmount(Integer amount) {

		if (amount == null || amount <= 0) {
			throw new IllegalArgumentException("Unable to encode the parameters. "
				+ "Received invalid amoun argument: amount=" + amount);
		}
	}

	public static void checkTransferProtocols(List<String> transferProtocols)
		throws IllegalArgumentException {

		if (transferProtocols == null || transferProtocols.isEmpty()) {
			throw new IllegalArgumentException("Unable to encode the parameters. "
				+ "Received empty Transfer Protocols argument: transferProtocols="
				+ transferProtocols);
		}
		for (String transferProtocol : transferProtocols) {
			if (transferProtocol == null || transferProtocol.trim().isEmpty()) {
				throw new IllegalArgumentException("Unable to encode the parameters."
					+ " Received empty Transfer Protocol element: transferProtocol="
					+ transferProtocol);
			}
		}
	}

	public static void checkTime(TLifeTimeInSeconds time)
		throws IllegalArgumentException {

		if (time == null || time.isEmpty()) {
			throw new IllegalArgumentException("Unable to encode the parameters. "
				+ "Received invalid time argument: time=" + time);
		}
	}
}
