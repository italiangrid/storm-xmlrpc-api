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
 * This class represents a TTSpace Token
 *
 * @author  EGRID ICTP Trieste / CNAF Bologna
 * @date    March 23rd, 2005
 * @version 2.0
 */

package it.grid.storm.srm.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import it.grid.storm.srm.types.TSpaceToken;
import java.io.Serializable;
import javax.swing.event.ListSelectionEvent;

public class ArrayOfTSpaceToken implements Serializable {

    
    public static final String PNAME_ARRAYOFSPACETOKENS = "arrayOfSpaceTokens";
    
    final List<TSpaceToken> tokenList;

    /**
     * Constructor that requires a String. If it is null, then an
     * InvalidArrayOfTTSpaceTokenAttributeException is thrown.
     */
    public ArrayOfTSpaceToken(TSpaceToken[] tokenArray) throws IllegalArgumentException {

        if (tokenArray == null)
        {
            throw new IllegalArgumentException("Unable to build the object, received null argument");
        }
        tokenList = Arrays.asList(tokenArray);
//        tokenList = new ArrayList<TSpaceToken>(tokenArray.length);
//        for(TSpaceToken token : tokenArray)
//        {
//            tokenList.add(token);
//        }
    } 
	
    public ArrayOfTSpaceToken() {
	    tokenList = new ArrayList<TSpaceToken>();
    }
    
    public static ArrayOfTSpaceToken decode(Map inputParam, String fieldName) throws IllegalArgumentException 
    {
        Vector<String> tokensList = null;
        try {
//        	tokensList = Arrays.asList((Object[]) inputParam.get(fieldName));
            tokensList = (Vector<String>) inputParam.get(fieldName);
        } catch (NullPointerException e ) {
        }
        if (tokensList == null) 
        {
            throw new IllegalArgumentException("Unable to build the object, received null argument");
        }
        
        ArrayOfTSpaceToken arrayOfTSpaceTokens = new ArrayOfTSpaceToken();
        
        for (int i=0; i<tokensList.size(); i++) {
            TSpaceToken token = null;
            try {
                token = TSpaceToken.make((String) tokensList.get(i));
            } catch (IllegalArgumentException e) {
                token = TSpaceToken.makeEmpty();
            }
            arrayOfTSpaceTokens.addTSpaceToken(token);
        }
        
        return arrayOfTSpaceTokens;
    }
   
    
    public TSpaceToken getTSpaceToken(int i) {
        return (TSpaceToken) tokenList.get(i);
	}
    
    public TSpaceToken[] getTSpaceTokenArray() {
        TSpaceToken[] array = new TSpaceToken[0];
        return tokenList.toArray(array);
    }


    public void addTSpaceToken(TSpaceToken token) {
	    tokenList.add(token);
    }

    public int size(){
        return tokenList.size();
    }
    
    /**
     *  Encode method, used to create a structured paramter representing this object, 
     *  for  FE communication.
     * @param outputParam
     * @param name
     */
    public void encode(Map outputParam, String name){ 
        Vector<TSpaceToken> vector = new Vector<TSpaceToken>();
        for(int i=0;i<tokenList.size();i++) {
           ((TSpaceToken)tokenList.get(i)).encode(vector);
        }
        
        outputParam.put(name, vector);
    } 
}
