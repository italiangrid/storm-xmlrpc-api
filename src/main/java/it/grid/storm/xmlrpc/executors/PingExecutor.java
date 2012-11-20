package it.grid.storm.xmlrpc.executors;


/*
 * Copyright (c) Istituto Nazionale di Fisica Nucleare (INFN). 2012
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import it.grid.storm.xmlrpc.ApiException;
import it.grid.storm.xmlrpc.decoders.DecodingException;
import it.grid.storm.xmlrpc.decoders.PingDecoder;
import it.grid.storm.xmlrpc.encoders.PingEncoder;
import it.grid.storm.xmlrpc.outputdata.PingOutputData;
import it.grid.storm.xmlrpc.remote.synchcall;
import java.util.List;
import java.util.Map;
import redstone.xmlrpc.XmlRpcFault;

/**
 * @author Michele Dibenedetto
 */
public class PingExecutor
{

    private static final Map<String, Object> DEFAULT_PARAMETERS = PingEncoder.getInstance().encode();

    public static PingOutputData execute(synchcall storm, String userDN, List<String> userFQANS)
            throws ApiException, IllegalArgumentException
    {
        if (storm == null || userDN == null || userFQANS == null || userFQANS.isEmpty())
        {
            throw new IllegalArgumentException("Unable to call ping command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN + " userFQANS=" + userFQANS);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = PingEncoder.getInstance().encode(userDN, userFQANS);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode Ping parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }

    public static PingOutputData execute(synchcall storm, String userDN) throws ApiException
    {
        if (storm == null || userDN == null)
        {
            throw new IllegalArgumentException("Unable to call ping command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null") + " userDN=" + userDN);
        }
        Map<String, Object> parameters;
        try
        {
            parameters = PingEncoder.getInstance().encode(userDN);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to encode Ping parameters. IllegalArgumentException: "
                    + e.getMessage());
        }
        return doIt(storm, parameters);
    }
    
    public static PingOutputData execute(synchcall storm) throws ApiException
    {
        if (storm == null)
        {
            throw new IllegalArgumentException("Unable to call ping command. Received null arguments: storm="
                    + (storm == null ? "null" : "not null"));
        }
        return doIt(storm, DEFAULT_PARAMETERS);
    }

    private static PingOutputData doIt(synchcall storm, Map<String, Object> parameters) throws ApiException
    {
        Map<String, Object> output;
        try
        {
            output = storm.ping(parameters);
        } catch(XmlRpcFault e)
        {
            throw new ApiException("Unable to perform ping call. XmlRpcFault: " + e.getMessage());
        }
        try
        {
            return PingDecoder.getInstance().decode(output);
        } catch(IllegalArgumentException e)
        {
            throw new ApiException("Unable to decode ping call output. IllegalArgumentException: "
                    + e.getMessage());
        } catch(DecodingException e)
        {
            throw new ApiException("Unable to decode ping call output. DecodingException: " + e.getMessage());
        }
    }

}
