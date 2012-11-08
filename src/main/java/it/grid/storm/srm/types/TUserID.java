/*
 * Copyright (c) Istituto Nazionale di Fisica Nucleare (INFN). 2006-2010.
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

/**
 * This class represents the TUserID managed by Srm.
 * 
 * @author Magnoni Luca
 * @author CNAF - INFN Bologna
 * @date Avril, 2005
 * @version 1.0
 */

package it.grid.storm.srm.types;


import java.io.Serializable;
import java.util.Map;

public class TUserID implements Serializable
{

    private static final long serialVersionUID = -7547071983406828938L;
    public static String PNAME_USERID = "userID";
    public static String PNAME_OWNER = "owner";

    private String userID = new String();

    public TUserID(String id) throws IllegalArgumentException
    {
        if ((id == null) || (id.length() == 0))
        {
            throw new IllegalArgumentException("Unable to construct the TUserID. Invalid id=" + id);
        }
        userID = id;
    }

    public static TUserID makeEmpty() throws IllegalStateException
    {
        try
        {
            return new TUserID("Unknown.");
        } catch(IllegalArgumentException e)
        {
            // never thrown
            throw new IllegalStateException("Unexpected IllegalArgumentException: " + e.getMessage());
        }
    }

    public String toString()
    {
        return userID;
    }

    public String getValue()
    {
        return userID;
    }

    public void encode(Map param, String name)
    {
        param.put(name, userID);
    }

    public static TUserID decode(Map param, String name) throws IllegalArgumentException
    {
        String id = (String) param.get(name);
        if (id != null)
        {
            return new TUserID(id);
        }
        else
        {
            return makeEmpty();
        }
    }

}
