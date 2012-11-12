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

import it.grid.storm.srm.types.TRequestToken;
import it.grid.storm.xmlrpc.remote.XmlRpcParameters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michele Dibenedetto
 */
public class FinalizeFileTransferEncoder
{

    private static final FinalizeFileTransferEncoder instance = new FinalizeFileTransferEncoder();

    protected FinalizeFileTransferEncoder()
    {
    }

    public static FinalizeFileTransferEncoder getInstance()
    {
        return instance;
    }

    public Map<String, Object> encode(String userDN, List<String> userFQANS, TRequestToken requestToken)
            throws IllegalArgumentException
    {
        Map<String, Object> encoding = encode(userDN, requestToken);
        EncodingUtils.checkToken(requestToken);

        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        return encoding;
    }
    
    public Map<String, Object> encode(String userDN, TRequestToken requestToken)
    {
        Map<String, Object> encoding = encode(requestToken);
        EncodingUtils.checkDN(userDN);

        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        return encoding;
    }

    public Map<String, Object> encode(TRequestToken requestToken)
    {
        EncodingUtils.checkToken(requestToken);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(TRequestToken.PNAME_REQUESTOKEN, requestToken.toString());
        return encoding;
    }
    
    public Map<String, Object> encodeWithSurls(String userDN, List<String> userFQANS, List<String> surls,
            TRequestToken requestToken) throws IllegalArgumentException
    {
        Map<String, Object> encoding = encodeWithSurls(userDN, surls, requestToken);
        EncodingUtils.checkFQANS(userFQANS);

        encoding.put(XmlRpcParameters.FQANS_KEY, userFQANS.toArray());
        return encoding;
    }
    
    public Map<String, Object> encodeWithSurls(String userDN, List<String> surls, TRequestToken requestToken)
    {
        Map<String, Object> encoding = encodeWithSurls(surls, requestToken);
        EncodingUtils.checkDN(userDN);

        encoding.put(XmlRpcParameters.DN_KEY, userDN);
        return encoding;
    }
    
    public Map<String, Object> encodeWithSurls(List<String> surls, TRequestToken requestToken)
    {
        EncodingUtils.checkSurls(surls);
        EncodingUtils.checkToken(requestToken);

        Map<String, Object> encoding = new HashMap<String, Object>();
        encoding.put(XmlRpcParameters.ARRAYOF_SURLS_KEY, surls.toArray(new String[surls.size()]));
        encoding.put(TRequestToken.PNAME_REQUESTOKEN, requestToken.toString());
        return encoding;
    }
}
