package it.grid.storm.xmlrpc.outputdata.util;

import it.grid.storm.srm.types.TReturnStatus;

/**
 * @author Michele Dibenedetto
 * 
 */
public class SurlStatus {

	private final String surl;
	private final TReturnStatus status;

	public SurlStatus(String surl, TReturnStatus status)
		throws IllegalArgumentException {

		if (surl == null || surl.trim().isEmpty() || status == null) {
			throw new IllegalArgumentException(
				"Unable to create the object, received null parameters: surl=" + surl
					+ " status=" + status);
		}
		this.surl = surl;
		this.status = status;
	}

	/**
	 * @return the surl
	 */
	public String getSurl() {

		return surl;
	}

	/**
	 * @return the status
	 */
	public TReturnStatus getStatus() {

		return status;
	}
}
