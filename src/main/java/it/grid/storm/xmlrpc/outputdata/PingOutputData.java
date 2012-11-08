/*
 *
 *  Copyright (c) Istituto Nazionale di Fisica Nucleare (INFN). 2012
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

package it.grid.storm.xmlrpc.outputdata;

/**
 * @author Michele Dibenedetto
 *
 */
public class PingOutputData implements OutputData {
	/**
	 * 
	 */
	private final String versionInfo;
	private final String beVersion;
	private final String beOs;
	

	/**
	 * @param versionInfo
	 * @param otherInfo
	 * @throws IllegalArgumentException
	 */
	public PingOutputData(String versionInfo, String beVersion, String beOs ) throws IllegalArgumentException
    {
        if (versionInfo == null || beVersion == null || beOs == null)
        {
            throw new IllegalArgumentException("Unable to create the object, received null parameters: versionInfo="
                                                       + versionInfo + " beVersion=" + beVersion + " beOs=" + beOs);
        }
        if (versionInfo.trim().equals("") || beVersion.trim().equals("")  || beOs.trim().equals("") )
        {
            throw new IllegalArgumentException("Unable to create the object, received invalid parameters: versionInfo="
                                                       + versionInfo + " beVersion=" + beVersion + " beOs=" + beOs);
        }
        this.versionInfo = versionInfo;
        this.beVersion = beVersion;
        this.beOs = beOs;
    }

    /**
	 * Get versionInfo.
	 * 
	 * @return String
	 */
	public String getVersionInfo() {
		return this.versionInfo;
	}

    public String getBeVersion()
    {
        return beVersion;
    }
    
    public String getBeOs()
    {
        return beOs;
    }

	@Override
	public boolean isSuccess() {
		return true;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("PingOutputData [versionInfo=");
        builder.append(versionInfo);
        builder.append(", beVersion=");
        builder.append(beVersion);
        builder.append(", beOs=");
        builder.append(beOs);
        builder.append("]");
        return builder.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((beOs == null) ? 0 : beOs.hashCode());
        result = prime * result + ((beVersion == null) ? 0 : beVersion.hashCode());
        result = prime * result + ((versionInfo == null) ? 0 : versionInfo.hashCode());
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
        if (!(obj instanceof PingOutputData))
        {
            return false;
        }
        PingOutputData other = (PingOutputData) obj;
        if (beOs == null)
        {
            if (other.beOs != null)
            {
                return false;
            }
        }
        else
            if (!beOs.equals(other.beOs))
            {
                return false;
            }
        if (beVersion == null)
        {
            if (other.beVersion != null)
            {
                return false;
            }
        }
        else
            if (!beVersion.equals(other.beVersion))
            {
                return false;
            }
        if (versionInfo == null)
        {
            if (other.versionInfo != null)
            {
                return false;
            }
        }
        else
            if (!versionInfo.equals(other.versionInfo))
            {
                return false;
            }
        return true;
    }
	
    

}
