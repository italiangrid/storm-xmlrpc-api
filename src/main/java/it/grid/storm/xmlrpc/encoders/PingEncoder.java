package it.grid.storm.xmlrpc.encoders;


/*
 * Copyright (c) Istituto Nazionale di Fisica Nucleare (INFN). 2012.
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import it.grid.storm.xmlrpc.remote.XmlRpcDefaults;
import it.grid.storm.xmlrpc.remote.XmlRpcParameters;

/**
 * @author Michele Dibenedetto
 */
public class PingEncoder
{
    
    private static final PingEncoder instance = new PingEncoder();

    protected PingEncoder()
    {
    }


    public static PingEncoder getInstance()
    {
        return instance;
    }

    public Map<String, Object> encode(String userDN, List<String> userFQANS)
            throws IllegalArgumentException
    {
        EncodingUtils.checkDN(userDN);
        EncodingUtils.checkFQANS(userFQANS);
        
        HashMap<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        encoding.put(XmlRpcParameters.AUTHID_KEY, XmlRpcDefaults.PING_AUTHID);
        return encoding;
    }
}
