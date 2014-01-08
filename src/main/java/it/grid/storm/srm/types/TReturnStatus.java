/*
 * 
 * Copyright (c) Istituto Nazionale di Fisica Nucleare (INFN). 2006-2010.
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

/**
 * This class represents the TReturnStatus value in SRM request. It is composed
 * by a TStatusCode and an explanetion String
 * 
 * @author Magnoni Luca
 * @author CNAF - INFN Bologna
 * @date Avril, 2005
 * @version 1.0
 */
package it.grid.storm.srm.types;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TReturnStatus implements Serializable {

	private static final long serialVersionUID = 7214229096835156070L;
	private TStatusCode statusCode = null;
	private String explanation = null;
	private static final String UNDEFINED_EXPLANATION = "undefined";

	public static final String PNAME_RETURNSTATUS = "returnStatus";
	public static final String PNAME_STATUS = "status";

	public static final String PNAME_RETURNSTATUS_CODE = "statusCode";
	public static final String PNAME_RETURNSTATUS_EXPLANATIONS = "explanation";

	/**
	 * Default constructor that makes a TReturnStatus with SRM_CUSTOM_STATUS, and
	 * explanation String "undefined".
	 * 
	 * @throws InvalidTReturnStatusAttributeException
	 */
	public TReturnStatus() {

		this(TStatusCode.SRM_CUSTOM_STATUS);
	}

	public static TReturnStatus getInitialValue() {

		return new TReturnStatus(TStatusCode.SRM_CUSTOM_STATUS, "Initial status..");
	}

	/**
	 * Can be Explanation String a null value?
	 */
	public TReturnStatus(TStatusCode statusCode, String explanation)
		throws IllegalArgumentException {

		this(statusCode);
		if (explanation != null) {
			this.explanation = explanation;
		}
	}

	public TReturnStatus(TStatusCode statusCode) throws IllegalArgumentException {

		if (statusCode == null) {
			throw new IllegalArgumentException(
				"Unable to create the object, received null parameter");
		}
		this.statusCode = statusCode;
		this.explanation = UNDEFINED_EXPLANATION;
	}

	public static TReturnStatus decode(Map<String, Object> fields)
		throws IllegalArgumentException {

		if (fields == null) {
			throw new IllegalArgumentException(
				"Unable to create the object, received null parameter");
		}
		return new TReturnStatus(TStatusCode.fromValue((String) fields
			.get(PNAME_RETURNSTATUS_CODE)),
			(String) fields.get(PNAME_RETURNSTATUS_EXPLANATIONS));
	}

	/**
	 * Returns the status code
	 * 
	 * @return TStatusCode
	 */
	public TStatusCode getStatusCode() {

		return statusCode;
	}

	/**
	 * Set explanation string
	 * 
	 * @param expl
	 *          String
	 */
	public void setExplanation(String explanationString) {

		explanation = explanationString;
	}

	/**
	 * Returns the explanation string
	 * 
	 * @return String
	 */
	public String getExplanation() {

		return explanation;
	}

	public boolean isSRM_SUCCESS() {

		return statusCode.equals(TStatusCode.SRM_SUCCESS);
	}

	/**
	 * This metod encode a TReturnStatus Object into an Hashtable used for xmlrpc
	 * comunication.
	 */
	public void encode(Map outputParam, String name) {

		HashMap<String, String> globalStatus = new HashMap<String, String>();
		globalStatus.put(PNAME_RETURNSTATUS_CODE, this.getStatusCode().getValue());
		globalStatus.put(PNAME_RETURNSTATUS_EXPLANATIONS, this.getExplanation());
		outputParam.put(name, globalStatus);
	}

	public String toString() {

		return statusCode + ": " + explanation;
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
		result = prime * result
			+ ((explanation == null) ? 0 : explanation.hashCode());
		result = prime * result
			+ ((statusCode == null) ? 0 : statusCode.hashCode());
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		TReturnStatus other = (TReturnStatus) obj;
		if (explanation == null) {
			if (other.explanation != null) {
				return false;
			}
		} else if (!explanation.equals(other.explanation)) {
			return false;
		}
		if (statusCode == null) {
			if (other.statusCode != null) {
				return false;
			}
		} else if (!statusCode.equals(other.statusCode)) {
			return false;
		}
		return true;
	}
}
