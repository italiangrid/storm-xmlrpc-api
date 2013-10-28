package it.grid.storm.xmlrpc.outputdata;

/*
 * Copyright (c) Istituto Nazionale di Fisica Nucleare (INFN). 2012 Licensed
 * under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
import it.grid.storm.srm.types.TRequestToken;
import it.grid.storm.srm.types.TReturnStatus;

/**
 * @author Michele Dibenedetto
 */
public class FileTransferOutputData extends RequestOutputData implements
	OutputData {

	private final String surl;
	private final String turl;
	private final TRequestToken token;

	public FileTransferOutputData(String surl, String turl, TReturnStatus status,
		TRequestToken token) throws IllegalArgumentException {

		super(status);
		if (surl == null || surl.trim().isEmpty() || turl == null
			|| turl.trim().isEmpty() || token == null || token.getValue() == null
			|| token.getValue().trim().isEmpty()) {
			throw new IllegalArgumentException(
				"Unable to create the object, received null parameters: surl=" + surl
					+ " turl=" + turl + " token=" + token);
		}
		this.surl = surl;
		this.turl = turl;
		this.token = token;
	}

	/**
	 * @return the surl
	 */
	public String getSurl() {

		return surl;
	}

	/**
	 * @return the turl
	 */
	public String getTurl() {

		return turl;
	}

	/**
	 * @return the token
	 */
	public TRequestToken getToken() {

		return token;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("FileTransferOutputData [surl=");
		builder.append(surl);
		builder.append(", turl=");
		builder.append(turl);
		builder.append(", token=");
		builder.append(token);
		builder.append(", status=");
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
		int result = super.hashCode();
		result = prime * result + ((surl == null) ? 0 : surl.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((turl == null) ? 0 : turl.hashCode());
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
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FileTransferOutputData other = (FileTransferOutputData) obj;
		if (surl == null) {
			if (other.surl != null) {
				return false;
			}
		} else if (!surl.equals(other.surl)) {
			return false;
		}
		if (token == null) {
			if (other.token != null) {
				return false;
			}
		} else if (!token.equals(other.token)) {
			return false;
		}
		if (turl == null) {
			if (other.turl != null) {
				return false;
			}
		} else if (!turl.equals(other.turl)) {
			return false;
		}
		return true;
	}

}
