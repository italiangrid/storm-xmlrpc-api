package it.grid.storm.xmlrpc.outputdata;

/*
 * 
 * Copyright (c) Istituto Nazionale di Fisica Nucleare (INFN). 2012
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
import it.grid.storm.srm.types.TReturnStatus;
import it.grid.storm.xmlrpc.outputdata.util.SurlStatus;
import java.util.HashMap;
import java.util.List;

/**
 * @author Michele Dibenedetto
 * 
 */
public class SurlArrayRequestOutputData extends RequestOutputData implements
	OutputData {

	private HashMap<String, TReturnStatus> surlStatus = new HashMap<String, TReturnStatus>();

	public SurlArrayRequestOutputData(TReturnStatus status,
		List<SurlStatus> statuses) {

		super(status);
		for (SurlStatus surStatus : statuses) {
			surlStatus.put(surStatus.getSurl(), surStatus.getStatus());
		}
	}

	public TReturnStatus getStatus(String surl) {

		return surlStatus.get(surl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("PdOutputData [surlStatus=");
		builder.append(surlStatus);
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
		result = prime * result
			+ ((surlStatus == null) ? 0 : surlStatus.hashCode());
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
		if (!(obj instanceof SurlArrayRequestOutputData)) {
			return false;
		}
		SurlArrayRequestOutputData other = (SurlArrayRequestOutputData) obj;
		if (surlStatus == null) {
			if (other.surlStatus != null) {
				return false;
			}
		} else if (!surlStatus.equals(other.surlStatus)) {
			return false;
		}
		return true;
	}

}
