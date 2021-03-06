package it.grid.storm.xmlrpc.executors;

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
import it.grid.storm.xmlrpc.ApiException;
import it.grid.storm.xmlrpc.decoders.DecodingException;
import it.grid.storm.xmlrpc.decoders.SympleStatusDecoder;
import it.grid.storm.xmlrpc.encoders.ManageFileTransferEncoder;
import it.grid.storm.xmlrpc.outputdata.RequestOutputData;
import it.grid.storm.xmlrpc.remote.synchcall;
import java.util.List;
import java.util.Map;
import redstone.xmlrpc.XmlRpcFault;

/**
 * @author Michele Dibenedetto
 */
public class AbortRequestExecutor {

	public static RequestOutputData execute(synchcall storm, String userDN,
		List<String> userFQANS, TRequestToken requestToken) throws ApiException {

		if (storm == null || userDN == null || userFQANS == null
			|| userFQANS.isEmpty() || requestToken == null) {
			throw new IllegalArgumentException(
				"Unable to call pd command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " userFQANS=" + userFQANS + " requestToken=" + requestToken);
		}
		Map<String, Object> parameters;
		try {
			parameters = ManageFileTransferEncoder.getInstance().encode(userDN,
				userFQANS, requestToken);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode abortRequest parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static RequestOutputData execute(synchcall storm, String userDN,
		TRequestToken requestToken) throws ApiException {

		if (storm == null || userDN == null || requestToken == null) {
			throw new IllegalArgumentException(
				"Unable to call pd command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " requestToken=" + requestToken);
		}
		Map<String, Object> parameters;
		try {
			parameters = ManageFileTransferEncoder.getInstance().encode(userDN,
				requestToken);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode abortRequest parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static RequestOutputData execute(synchcall storm,
		TRequestToken requestToken) throws ApiException {

		if (storm == null || requestToken == null) {
			throw new IllegalArgumentException(
				"Unable to call pd command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " requestToken="
					+ requestToken);
		}
		Map<String, Object> parameters;
		try {
			parameters = ManageFileTransferEncoder.getInstance().encode(requestToken);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode abortRequest parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	private static RequestOutputData doIt(synchcall storm,
		Map<String, Object> parameters) throws ApiException {

		Map<String, Object> output;
		try {
			output = storm.abortRequest(parameters);
		} catch (XmlRpcFault e) {
			throw new ApiException(
				"Unable to perform abortRequest call. XmlRpcFault: " + e.getMessage());
		}
		try {
			return SympleStatusDecoder.getInstance().decode(output);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to decode abortRequest call output. IllegalArgumentException: "
					+ e.getMessage());
		} catch (DecodingException e) {
			throw new ApiException(
				"Unable to decode abortRequest call output. DecodingException: "
					+ e.getMessage());
		}
	}
}
