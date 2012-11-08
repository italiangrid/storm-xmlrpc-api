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
 * This class represents the TStatusCode of TReturnStatus
 *
 * @author  Magnoni Luca
 * @author  CNAF - INFN  Bologna
 * @date    Avril, 2005
 * @version 1.0
 */

package it.grid.storm.srm.types;

import java.io.Serializable;

public class TStatusCode implements Serializable {

    private static final long serialVersionUID = -1228182137951212768L;
    private String status = null;
    private static java.util.HashMap<String, TStatusCode> _table_ = new java.util.HashMap<String, TStatusCode>();

    public static final TStatusCode EMPTY = new TStatusCode("EMPTY");
    public static final TStatusCode SRM_SUCCESS = new TStatusCode("SRM_SUCCESS");
    public static final TStatusCode SRM_FAILURE = new TStatusCode("SRM_FAILURE");
    public static final TStatusCode SRM_AUTHENTICATION_FAILURE = new TStatusCode("SRM_AUTHENTICATION_FAILURE");
    public static final TStatusCode SRM_AUTHORIZATION_FAILURE = new TStatusCode("SRM_AUTHORIZATION_FAILURE");
    public static final TStatusCode SRM_INVALID_REQUEST = new TStatusCode("SRM_INVALID_REQUEST");
    public static final TStatusCode SRM_INVALID_PATH = new TStatusCode("SRM_INVALID_PATH");
    public static final TStatusCode SRM_FILE_LIFETIME_EXPIRED = new TStatusCode("SRM_FILE_LIFETIME_EXPIRED");
    public static final TStatusCode SRM_SPACE_LIFETIME_EXPIRED = new TStatusCode("SRM_SPACE_LIFETIME_EXPIRED");
    public static final TStatusCode SRM_EXCEED_ALLOCATION = new TStatusCode("SRM_EXCEED_ALLOCATION");
    public static final TStatusCode SRM_NO_USER_SPACE = new TStatusCode("SRM_NO_USER_SPACE");
    public static final TStatusCode SRM_NO_FREE_SPACE = new TStatusCode("SRM_NO_FREE_SPACE");
    public static final TStatusCode SRM_DUPLICATION_ERROR = new TStatusCode("SRM_DUPLICATION_ERROR");
    public static final TStatusCode SRM_NON_EMPTY_DIRECTORY = new TStatusCode("SRM_NON_EMPTY_DIRECTORY");
    public static final TStatusCode SRM_TOO_MANY_RESULTS = new TStatusCode("SRM_TOO_MANY_RESULTS");
    public static final TStatusCode SRM_INTERNAL_ERROR = new TStatusCode("SRM_INTERNAL_ERROR");
    public static final TStatusCode SRM_FATAL_INTERNAL_ERROR = new TStatusCode("SRM_FATAL_INTERNAL_ERROR");
    public static final TStatusCode SRM_NOT_SUPPORTED = new TStatusCode("SRM_NOT_SUPPORTED");
    public static final TStatusCode SRM_REQUEST_QUEUED = new TStatusCode("SRM_REQUEST_QUEUED");
    public static final TStatusCode SRM_REQUEST_INPROGRESS = new TStatusCode("SRM_REQUEST_INPROGRESS");
    public static final TStatusCode SRM_REQUEST_SUSPENDED = new TStatusCode("SRM_REQUEST_SUSPENDED");
    public static final TStatusCode SRM_ABORTED = new TStatusCode("SRM_ABORTED");
    public static final TStatusCode SRM_RELEASED = new TStatusCode("SRM_RELEASED");
    public static final TStatusCode SRM_FILE_PINNED = new TStatusCode("SRM_FILE_PINNED");
    public static final TStatusCode SRM_FILE_IN_CACHE = new TStatusCode("SRM_FILE_IN_CACHE");
    public static final TStatusCode SRM_SPACE_AVAILABLE = new TStatusCode("SRM_SPACE_AVAILABLE");
    public static final TStatusCode SRM_LOWER_SPACE_GRANTED = new TStatusCode("SRM_LOWER_SPACE_GRANTED");
    public static final TStatusCode SRM_DONE = new TStatusCode("SRM_DONE");
    public static final TStatusCode SRM_PARTIAL_SUCCESS = new TStatusCode("SRM_PARTIAL_SUCCESS");
    public static final TStatusCode SRM_REQUEST_TIMED_OUT = new TStatusCode("SRM_REQUEST_TIMED_OUT");
    public static final TStatusCode SRM_LAST_COPY = new TStatusCode("SRM_LAST_COPY");
    public static final TStatusCode SRM_FILE_BUSY = new TStatusCode("SRM_FILE_BUSY");
    public static final TStatusCode SRM_FILE_LOST = new TStatusCode("SRM_FILE_LOST");
    public static final TStatusCode SRM_FILE_UNAVAILABLE = new TStatusCode("SRM_FILE_UNAVAILABLE");
    public static final TStatusCode SRM_CUSTOM_STATUS = new TStatusCode("SRM_CUSTOM_STATUS");

    private TStatusCode(String status) {
        this.status = status;
        _table_.put(status, this);
    }

    /**
     * Facility method to obtain a TStatusCode object from its String representation. An
     * IllegalArgumentExceptin is thrown if the supplied String does not have a TStatusCode counterpart.
     */
    public static TStatusCode fromValue(java.lang.String value) throws java.lang.IllegalArgumentException {
        TStatusCode enumeration = (TStatusCode) _table_.get(value);
        if (enumeration == null)
            throw new java.lang.IllegalArgumentException();
        return enumeration;
    }

    public static TStatusCode fromString(java.lang.String value) throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }

    public String getValue() {
        return status;
    }

    public String toString() {
        return status;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        TStatusCode other = (TStatusCode) obj;
        if (status == null)
        {
            if (other.status != null)
            {
                return false;
            }
        }
        else
            if (!status.equals(other.status))
            {
                return false;
            }
        return true;
    }
}
