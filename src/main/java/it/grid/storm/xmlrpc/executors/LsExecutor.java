package it.grid.storm.xmlrpc.executors;

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

import it.grid.storm.srm.types.RecursionLevel;
import it.grid.storm.xmlrpc.ApiException;
import it.grid.storm.xmlrpc.decoders.DecodingException;
import it.grid.storm.xmlrpc.decoders.LsDecoder;
import it.grid.storm.xmlrpc.encoders.LsEncoder;
import it.grid.storm.xmlrpc.outputdata.LsOutputData;
import it.grid.storm.xmlrpc.remote.synchcall;
import java.util.List;
import java.util.Map;
import redstone.xmlrpc.XmlRpcFault;

/**
 * @author Michele Dibenedetto
 * 
 */
public class LsExecutor {

	public static LsOutputData execute(synchcall storm, String userDN,
		List<String> userFQANS, List<String> surls) throws ApiException {

		if (storm == null || userDN == null || userFQANS == null
			|| userFQANS.isEmpty() || surls == null || surls.isEmpty()) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " userFQANS=" + userFQANS + " surls=" + surls);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encode(userDN, userFQANS, surls);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData execute(synchcall storm, String userDN,
		List<String> surls) throws ApiException {

		if (storm == null || userDN == null || surls == null || surls.isEmpty()) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " surls=" + surls);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encode(userDN, surls);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData execute(synchcall storm, List<String> surls)
		throws ApiException {

		if (storm == null || surls == null || surls.isEmpty()) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " surls=" + surls);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encode(surls);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData executeDetailed(synchcall storm, String userDN,
		List<String> userFQANS, List<String> surls) throws ApiException {

		if (storm == null || userDN == null || userFQANS == null
			|| userFQANS.isEmpty() || surls == null || surls.isEmpty()) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " userFQANS=" + userFQANS + " surls=" + surls);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encodeDetailed(userDN, userFQANS,
				surls);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData executeDetailed(synchcall storm, String userDN,
		List<String> surls) throws ApiException {

		if (storm == null || userDN == null || surls == null || surls.isEmpty()) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " surls=" + surls);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encodeDetailed(userDN, surls);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData executeDetailed(synchcall storm, List<String> surls)
		throws ApiException {

		if (storm == null || surls == null || surls.isEmpty()) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " surls=" + surls);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encodeDetailed(surls);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData execute(synchcall storm, String userDN,
		List<String> userFQANS, List<String> surls, RecursionLevel recursion)
		throws ApiException {

		if (storm == null || userDN == null || userFQANS == null
			|| userFQANS.isEmpty() || surls == null || surls.isEmpty()
			|| recursion == null) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " userFQANS=" + userFQANS + " surls=" + surls + " recursion="
					+ recursion);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encode(userDN, userFQANS, surls,
				recursion);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData execute(synchcall storm, String userDN,
		List<String> surls, RecursionLevel recursion) throws ApiException {

		if (storm == null || userDN == null || surls == null || surls.isEmpty()
			|| recursion == null) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " surls=" + surls + " recursion=" + recursion);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encode(userDN, surls, recursion);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData execute(synchcall storm, List<String> surls,
		RecursionLevel recursion) throws ApiException {

		if (storm == null || surls == null || surls.isEmpty() || recursion == null) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " surls=" + surls
					+ " recursion=" + recursion);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encode(surls, recursion);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData executeDetailed(synchcall storm, String userDN,
		List<String> userFQANS, List<String> surls, RecursionLevel recursion)
		throws ApiException {

		if (storm == null || userDN == null || userFQANS == null
			|| userFQANS.isEmpty() || surls == null || surls.isEmpty()
			|| recursion == null) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " userFQANS=" + userFQANS + " surls=" + surls + " recursion="
					+ recursion);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encodeDetailed(userDN, userFQANS,
				surls, recursion);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData executeDetailed(synchcall storm, String userDN,
		List<String> surls, RecursionLevel recursion) throws ApiException {

		if (storm == null || userDN == null || surls == null || surls.isEmpty()
			|| recursion == null) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " surls=" + surls + " recursion=" + recursion);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encodeDetailed(userDN, surls,
				recursion);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData executeDetailed(synchcall storm,
		List<String> surls, RecursionLevel recursion) throws ApiException {

		if (storm == null || surls == null || surls.isEmpty() || recursion == null) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " surls=" + surls
					+ " recursion=" + recursion);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encodeDetailed(surls, recursion);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData execute(synchcall storm, String userDN,
		List<String> userFQANS, List<String> surls, Integer count)
		throws ApiException {

		if ((storm == null ? "null" : "not null") == null || userDN == null
			|| userFQANS == null || userFQANS.isEmpty() || surls == null
			|| surls.isEmpty() || count == null || count <= 0) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm=" + storm
					+ " userDN=" + userDN + " userFQANS=" + userFQANS + " surls=" + surls
					+ " count=" + count);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encode(userDN, userFQANS, surls,
				count);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData execute(synchcall storm, String userDN,
		List<String> surls, Integer count) throws ApiException {

		if ((storm == null ? "null" : "not null") == null || userDN == null
			|| surls == null || surls.isEmpty() || count == null || count <= 0) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm=" + storm
					+ " userDN=" + userDN + " surls=" + surls + " count=" + count);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encode(userDN, surls, count);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData execute(synchcall storm, List<String> surls,
		Integer count) throws ApiException {

		if ((storm == null ? "null" : "not null") == null || surls == null
			|| surls.isEmpty() || count == null || count <= 0) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm=" + storm
					+ " surls=" + surls + " count=" + count);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encode(surls, count);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData executeDetailed(synchcall storm, String userDN,
		List<String> userFQANS, List<String> surls, Integer count)
		throws ApiException {

		if (storm == null || userDN == null || userFQANS == null
			|| userFQANS.isEmpty() || surls == null || surls.isEmpty()
			|| count == null || count <= 0) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " userFQANS=" + userFQANS + " surls=" + surls + " count=" + count);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encodeDetailed(userDN, userFQANS,
				surls, count);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData executeDetailed(synchcall storm, String userDN,
		List<String> surls, Integer count) throws ApiException {

		if (storm == null || userDN == null || surls == null || surls.isEmpty()
			|| count == null || count <= 0) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " surls=" + surls + " count=" + count);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encodeDetailed(userDN, surls, count);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData executeDetailed(synchcall storm,
		List<String> surls, Integer count) throws ApiException {

		if (storm == null || surls == null || surls.isEmpty() || count == null
			|| count <= 0) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " surls=" + surls
					+ " count=" + count);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encodeDetailed(surls, count);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData execute(synchcall storm, String userDN,
		List<String> userFQANS, List<String> surls, RecursionLevel recursion,
		Integer count) throws ApiException {

		if (storm == null || userDN == null || userFQANS == null
			|| userFQANS.isEmpty() || surls == null || surls.isEmpty()
			|| recursion == null || count == null || count <= 0) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " userFQANS=" + userFQANS + " surls=" + surls + " recursion="
					+ recursion + " count=" + count);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encode(userDN, userFQANS, surls,
				recursion, count);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData execute(synchcall storm, String userDN,
		List<String> surls, RecursionLevel recursion, Integer count)
		throws ApiException {

		if (storm == null || userDN == null || surls == null || surls.isEmpty()
			|| recursion == null || count == null || count <= 0) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " surls=" + surls + " recursion=" + recursion + " count=" + count);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encode(userDN, surls, recursion,
				count);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData execute(synchcall storm, List<String> surls,
		RecursionLevel recursion, Integer count) throws ApiException {

		if (storm == null || surls == null || surls.isEmpty() || recursion == null
			|| count == null || count <= 0) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " surls=" + surls
					+ " recursion=" + recursion + " count=" + count);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encode(surls, recursion, count);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData executeDetailed(synchcall storm, String userDN,
		List<String> userFQANS, List<String> surls, RecursionLevel recursion,
		Integer count) throws ApiException {

		if (storm == null || userDN == null || userFQANS == null
			|| userFQANS.isEmpty() || surls == null || surls.isEmpty()
			|| recursion == null || count == null || count <= 0) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " userFQANS=" + userFQANS + " surls=" + surls + " recursion="
					+ recursion + " count=" + count);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encodeDetailed(userDN, userFQANS,
				surls, recursion, count);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData executeDetailed(synchcall storm, String userDN,
		List<String> surls, RecursionLevel recursion, Integer count)
		throws ApiException {

		if (storm == null || userDN == null || surls == null || surls.isEmpty()
			|| recursion == null || count == null || count <= 0) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " userDN=" + userDN
					+ " surls=" + surls + " recursion=" + recursion + " count=" + count);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encodeDetailed(userDN, surls,
				recursion, count);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	public static LsOutputData executeDetailed(synchcall storm,
		List<String> surls, RecursionLevel recursion, Integer count)
		throws ApiException {

		if (storm == null || surls == null || surls.isEmpty() || recursion == null
			|| count == null || count <= 0) {
			throw new IllegalArgumentException(
				"Unable to call ls command. Received null arguments: storm="
					+ (storm == null ? "null" : "not null") + " surls=" + surls
					+ " recursion=" + recursion + " count=" + count);
		}
		Map<String, Object> parameters;
		try {
			parameters = LsEncoder.getInstance().encodeDetailed(surls, recursion,
				count);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to encode ls parameters. IllegalArgumentException: "
					+ e.getMessage());
		}
		return doIt(storm, parameters);
	}

	private static LsOutputData doIt(synchcall storm,
		Map<String, Object> parameters) throws ApiException {

		Map<String, Object> output;
		try {
			output = storm.ls(parameters);
		} catch (XmlRpcFault e) {
			throw new ApiException("Unable to perform ls call. XmlRpcFault: "
				+ e.getMessage());
		}
		try {
			return LsDecoder.getInstance().decode(output);
		} catch (IllegalArgumentException e) {
			throw new ApiException(
				"Unable to decode ls call output. IllegalArgumentException: "
					+ e.getMessage());
		} catch (DecodingException e) {
			throw new ApiException(
				"Unable to decode ls call output. DecodingException: " + e.getMessage());
		}
	}

}
