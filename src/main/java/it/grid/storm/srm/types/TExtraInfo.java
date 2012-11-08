/*
 *
 *  Copyright (c) Istituto Nazionale di Fisica Nucleare (INFN). 2006-2010.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * This class represents the TExtraInfo additional data associated with the SRM request.
 * @author  Magnoni Luca
 * @author  Cnaf -INFN Bologna
 * @date
 * @version 1.0
 */

package it.grid.storm.srm.types;

import redstone.xmlrpc.XmlRpcStruct;

public class TExtraInfo {

	public static String PNAME_EXTRAINFO = "extraInfo";

	private static String PNAME_KEY = "key";
	private static String PNAME_VALUE = "value";

	private final String key;
	private final String value;

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public TExtraInfo(String key, String value) throws IllegalArgumentException {

		if (key == null) {
			throw new IllegalArgumentException(
					"Unable to create the instance, received null arguments: key="
							+ key);
		}
		this.key = key;
		this.value = value;
	}

	/**
	 * @param param
	 * @return
	 * @throws InvalidTExtraInfoAttributeException
	 */
	public static TExtraInfo decode(XmlRpcStruct struct)
			throws IllegalArgumentException {

		if(struct == null)
		{
			throw new IllegalArgumentException("Unable to build the instance. Received null argument: struct=" + struct);
		}
		String k, val;
		try {
			k = struct.getString(TExtraInfo.PNAME_KEY);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Struct element "
					+ TExtraInfo.PNAME_KEY
					+ " is not a String as expected : "
					+ struct.get(TExtraInfo.PNAME_KEY).getClass()
							.getName());
		}
		try {
			val = struct.getString(TExtraInfo.PNAME_VALUE);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Struct element "
					+ TExtraInfo.PNAME_VALUE
					+ " is not a String as expected : "
					+ struct.get(TExtraInfo.PNAME_VALUE).getClass()
							.getName());
		}
		return new TExtraInfo(k, val);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TExtraInfo [key=");
		builder.append(key);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TExtraInfo other = (TExtraInfo) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
