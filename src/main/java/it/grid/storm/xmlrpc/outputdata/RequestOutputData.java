package it.grid.storm.xmlrpc.outputdata;

import it.grid.storm.srm.types.TReturnStatus;

/**
 * @author Michele Dibenedetto
 * 
 */
public class RequestOutputData implements OutputData {

	protected final TReturnStatus status;

	public RequestOutputData(TReturnStatus status)
		throws IllegalArgumentException {

		if (status == null) {
			throw new IllegalArgumentException("Unable to create the object, "
				+ "received null parameters: status=" + status);
		}
		this.status = status;
	}

	@Override
	public boolean isSuccess() {

		return status.isSRM_SUCCESS();
	}

	/**
	 * @return the status
	 */
	public TReturnStatus getStatus() {

		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("RequestOutputData [status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RequestOutputData)) {
			return false;
		}
		RequestOutputData other = (RequestOutputData) obj;
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}

}
