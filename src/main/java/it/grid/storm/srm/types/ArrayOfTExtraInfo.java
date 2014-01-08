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
 * This class represents a TExtraInfoArray
 * 
 * @author EGRID ICTP Trieste / CNAF Bologna
 * @date July, 2006
 * @version 2.0
 */

package it.grid.storm.srm.types;

import java.io.*;
import java.util.*;

import redstone.xmlrpc.XmlRpcArray;
import redstone.xmlrpc.XmlRpcStruct;

public class ArrayOfTExtraInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3396988647933478316L;

	public static String PNAME_STORAGESYSTEMINFO = "storageSystemInfo";

	private final List<TExtraInfo> extraInfoList;

	/**
	 * Constructor that requires a String. If it is null, then an
	 * InvalidArrayOfTExtraInfoAttributeException is thrown.
	 */
	public ArrayOfTExtraInfo(List<TExtraInfo> extraInfoList)
		throws IllegalArgumentException {

		if (extraInfoList == null) {
			throw new IllegalArgumentException(
				"Unable to create the instance, received null arguments: extraInfoList="
					+ extraInfoList);
		}
		this.extraInfoList = extraInfoList;
	}

	public ArrayOfTExtraInfo() {

		extraInfoList = new ArrayList<TExtraInfo>();
	}

	public void add(TExtraInfo info) {

		extraInfoList.add(info);
	}

	public int size() {

		return extraInfoList.size();
	}

	public TExtraInfo[] getElements() {

		return extraInfoList.toArray(new TExtraInfo[extraInfoList.size()]);
	}

	/**
	 * Fills this class using the values found in a structure inside a Hashtable.
	 * The Hashtable may contain different structures inside, all are identifiend
	 * by a name. Used for communication with the FE.
	 * 
	 * @param inputParam
	 *          Hashtable to read.
	 * @param fieldName
	 *          Name that identifies the ArrayOfTExtraInfo structure in the
	 *          Hashtable.
	 * @return A new ArrayOfTExtraInfo instance.
	 * @throws InvalidTExtraInfoAttributeException
	 */
	public static ArrayOfTExtraInfo decode(XmlRpcArray array)
		throws IllegalArgumentException {

		if (array == null) {
			throw new IllegalArgumentException(
				"Unable to build the instance. Received null argument: array=" + array);
		}

		ArrayOfTExtraInfo extraInfoArray = new ArrayOfTExtraInfo();

		for (Object o : array) {
			extraInfoArray.add(TExtraInfo.decode((XmlRpcStruct) o));
		}
		return extraInfoArray;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("ArrayOfTExtraInfo [extraInfoList=");
		builder.append(extraInfoList);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result
			+ ((extraInfoList == null) ? 0 : extraInfoList.hashCode());
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
		ArrayOfTExtraInfo other = (ArrayOfTExtraInfo) obj;
		if (extraInfoList == null) {
			if (other.extraInfoList != null)
				return false;
		} else if (!extraInfoList.equals(other.extraInfoList))
			return false;
		return true;
	}

}
