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

package it.grid.storm.xmlrpc.decoders;

import java.util.Map;

import redstone.xmlrpc.XmlRpcArray;

import it.grid.storm.srm.types.ArrayOfTExtraInfo;
import it.grid.storm.srm.types.TExtraInfo;
import it.grid.storm.xmlrpc.outputdata.PingOutputData;

/**
 * @author Michele Dibenedetto
 * 
 */
public class PingDecoder implements OutputDecoder {

	public static final String VERSIONINFO_KEY = "versionInfo";
	public static final String BE_VERSION_KEY = "BE-Version";
	public static final String BE_OS_KEY = "BE-OS-Distribution";

	private static volatile PingDecoder instance = new PingDecoder();

	private PingDecoder() {

	}

	public static synchronized PingDecoder getInstance() {

		return instance;
	}

	/**
	 * @param outputParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public PingOutputData decode(Map<String, Object> output)
		throws IllegalArgumentException, DecodingException {

		if (output == null) {
			throw new IllegalArgumentException(
				"Unable to decode the output. Received null argument: output=" + output);
		}
		if (output.get(ArrayOfTExtraInfo.PNAME_STORAGESYSTEMINFO) == null
			|| output.get(VERSIONINFO_KEY) == null) {
			throw new IllegalArgumentException(
				"Unable to decode the output. Missing mandatory arguments: output="
					+ output);
		}
		ArrayOfTExtraInfo extraInfos;
		try {
			extraInfos = ArrayOfTExtraInfo.decode((XmlRpcArray) output
				.get(ArrayOfTExtraInfo.PNAME_STORAGESYSTEMINFO));
		} catch (IllegalArgumentException e) {
			throw new DecodingException(
				"Unable to decode the storageSystemInfo. IllegalArgumentException: "
					+ e.getMessage());
		}
		String beVersion = null, beOs = null;
		for (TExtraInfo extraInfo : extraInfos.getElements()) {
			if (extraInfo.getKey().equals(BE_VERSION_KEY)) {
				beVersion = extraInfo.getValue();
				if (beVersion == null) {
					throw new DecodingException(
						"Unable to decode the output, received null beVersion");
				}
			} else {
				if (extraInfo.getKey().equals(BE_OS_KEY)) {
					beOs = extraInfo.getValue();
					if (beOs == null) {
						throw new DecodingException(
							"Unable to decode the output, received null BackEnd operating system");
					}
				}
			}
		}
		if (beVersion == null || beOs == null) {
			throw new DecodingException(
				"Unable to decode the output, missing  mandatory arguments: storageSystemInfo="
					+ extraInfos);
		}
		return new PingOutputData((String) output.get(VERSIONINFO_KEY), beVersion,
			beOs);
	}
}
