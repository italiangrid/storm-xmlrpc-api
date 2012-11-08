package it.grid.storm.xmlrpc.outputdata;

import it.grid.storm.srm.types.ArrayOfTSpaceToken;
import it.grid.storm.srm.types.TCheckSumType;
import it.grid.storm.srm.types.TCheckSumValue;
import it.grid.storm.srm.types.TFileLocality;
import it.grid.storm.srm.types.TFileStorageType;
import it.grid.storm.srm.types.TFileType;
import it.grid.storm.srm.types.TGroupPermission;
import it.grid.storm.srm.types.TLifeTimeInSeconds;
import it.grid.storm.srm.types.TPermissionMode;
import it.grid.storm.srm.types.TRequestToken;
import it.grid.storm.srm.types.TRetentionPolicyInfo;
import it.grid.storm.srm.types.TReturnStatus;
import it.grid.storm.srm.types.TSizeInBytes;
import it.grid.storm.srm.types.TUserPermission;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class LsOutputData extends RequestOutputData  implements OutputData {

    private final ArrayList<SurlInfo> infos;
    private final TRequestToken token;

    public LsOutputData(TReturnStatus status, Collection<SurlInfo> infos) throws IllegalArgumentException
    {
        super(status);
        if(infos == null || infos.isEmpty())
        {
            throw new IllegalArgumentException("Unable to create the object, invalid arguments: infos=" + infos);
        }
        this.infos = new ArrayList<SurlInfo>(infos);
        token = null;
    }

    public LsOutputData(TReturnStatus status, LinkedList<SurlInfo> infos, TRequestToken token)
        throws IllegalArgumentException
    {
        super(status);
        if(infos == null || infos.isEmpty() || token == null)
        {
            throw new IllegalArgumentException("Unable to create the object, invalid arguments: infos="
                    + infos + "token =" + token);
        }
        this.infos = new ArrayList<SurlInfo>(infos);
        this.token = token;
    }
    
    /**
     * @return the infos
     */
    public Collection<SurlInfo> getInfos()
    {
        return infos;
    }

    public boolean hasToken()
    {
        return this.token != null;
    }

    /**
     * @return the token
     */
    public TRequestToken getToken()
    {
        return token;
    }
    
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("LsOutputData [infos=");
        builder.append(infos);
        builder.append(", token=");
        builder.append(token);
        builder.append(", status=");
        builder.append(status);
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
        int result = super.hashCode();
        result = prime * result + ((infos == null) ? 0 : infos.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
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
        if (!super.equals(obj))
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        LsOutputData other = (LsOutputData) obj;
        if (infos == null)
        {
            if (other.infos != null)
            {
                return false;
            }
        }
        else
            if (!infos.equals(other.infos))
            {
                return false;
            }
        if (token == null)
        {
            if (other.token != null)
            {
                return false;
            }
        }
        else
            if (!token.equals(other.token))
            {
                return false;
            }
        return true;
    }



    public static class SurlInfo
    {

        private final String stfn;
        private final TReturnStatus status;
        private TSizeInBytes size = null;
        private Date creationTime = null;
        private Date modificationTime = null;
        private TFileStorageType storageType = null;
        private TRetentionPolicyInfo retentionPolicy = null;
        private TFileLocality locality = null;
        private ArrayOfTSpaceToken spaceTokenArray = null;
        private TFileType type = null;
        private TLifeTimeInSeconds lifetime = null;
        private TLifeTimeInSeconds lifetimeLeft = null;
        private TUserPermission userPermission = null;
        private TGroupPermission groupPermission = null;
        private TPermissionMode otherPermission = null;
        private TCheckSumType checkSumType = null;
        private TCheckSumValue checkSumValue = null;
        private LinkedList<SurlInfo> subpathInfo = new LinkedList<SurlInfo>();

        public SurlInfo(String stfn, TReturnStatus status)
        {
            this.stfn = stfn;
            this.status = status;
        }

        public void setSize(TSizeInBytes size)
        {
            this.size = size;
            
        }

        public void setCreationTime(Date creationTime)
        {
            this.creationTime = creationTime;
            
        }

        public void setModificationTime(Date modificationTime)
        {
            this.modificationTime = modificationTime;
            
        }

        public void setStorageType(TFileStorageType storageType)
        {
            this.storageType = storageType;
            
        }

        public void setRetentionPolicy(TRetentionPolicyInfo retentionPolicy)
        {
            this.retentionPolicy = retentionPolicy;
            
        }

        public void setLocality(TFileLocality locality)
        {
            this.locality = locality;
            
        }

        public void setSpaceTokenArray(ArrayOfTSpaceToken spaceTokenArray)
        {
            this.spaceTokenArray = spaceTokenArray;
            
        }

        public void setType(TFileType type)
        {
            this.type = type;
            
        }

        public void setLifetime(TLifeTimeInSeconds lifetime)
        {
            this.lifetime = lifetime;
            
        }

        public void setLifetimeLeft(TLifeTimeInSeconds lifetimeLeft)
        {
            this.lifetimeLeft = lifetimeLeft;
            
        }

        public void setUserPermission(TUserPermission userPermission)
        {
            this.userPermission = userPermission;
            
        }

        public void setGroupPermission(TGroupPermission groupPermission)
        {
            this.groupPermission = groupPermission;
            
        }

        public void setOtherPermission(TPermissionMode otherPermission)
        {
            this.otherPermission = otherPermission;
            
        }

        public void setCheckSumType(TCheckSumType checkSumType)
        {
            this.checkSumType = checkSumType;
            
        }

        public void setCheckSumValue(TCheckSumValue checkSumValue)
        {
            this.checkSumValue = checkSumValue;
            
        }

        public void addSubpathInfo(SurlInfo subpathInfo)
        {
            this.subpathInfo.add(subpathInfo);
            
        }
        
        /**
         * @return the stfn
         */
        public String getStfn()
        {
            return stfn;
        }

        /**
         * @return the status
         */
        public TReturnStatus getStatus()
        {
            return status;
        }

        /**
         * @return the size
         */
        public TSizeInBytes getSize()
        {
            return size;
        }

        /**
         * @return the creationTime
         */
        public Date getCreationTime()
        {
            return creationTime;
        }

        /**
         * @return the modificationTime
         */
        public Date getModificationTime()
        {
            return modificationTime;
        }

        /**
         * @return the storageType
         */
        public TFileStorageType getStorageType()
        {
            return storageType;
        }

        /**
         * @return the retentionPolicy
         */
        public TRetentionPolicyInfo getRetentionPolicy()
        {
            return retentionPolicy;
        }

        /**
         * @return the locality
         */
        public TFileLocality getLocality()
        {
            return locality;
        }

        /**
         * @return the spaceTokenArray
         */
        public ArrayOfTSpaceToken getSpaceTokenArray()
        {
            return spaceTokenArray;
        }

        /**
         * @return the type
         */
        public TFileType getType()
        {
            return type;
        }

        /**
         * @return the lifetime
         */
        public TLifeTimeInSeconds getLifetime()
        {
            return lifetime;
        }

        /**
         * @return the lifetimeLeft
         */
        public TLifeTimeInSeconds getLifetimeLeft()
        {
            return lifetimeLeft;
        }

        /**
         * @return the userPermission
         */
        public TUserPermission getUserPermission()
        {
            return userPermission;
        }

        /**
         * @return the groupPermission
         */
        public TGroupPermission getGroupPermission()
        {
            return groupPermission;
        }

        /**
         * @return the otherPermission
         */
        public TPermissionMode getOtherPermission()
        {
            return otherPermission;
        }

        /**
         * @return the checkSumType
         */
        public TCheckSumType getCheckSumType()
        {
            return checkSumType;
        }

        /**
         * @return the checkSumValue
         */
        public TCheckSumValue getCheckSumValue()
        {
            return checkSumValue;
        }

        /**
         * @return the subpathInfo
         */
        public Collection<SurlInfo> getSubpathInfo()
        {
            return subpathInfo;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString()
        {
            StringBuilder builder = new StringBuilder();
            builder.append("SurlInfo [stfn=");
            builder.append(stfn);
            builder.append(", status=");
            builder.append(status);
            builder.append(", size=");
            builder.append(size);
            builder.append(", creationTime=");
            builder.append(creationTime);
            builder.append(", modificationTime=");
            builder.append(modificationTime);
            builder.append(", storageType=");
            builder.append(storageType);
            builder.append(", retentionPolicy=");
            builder.append(retentionPolicy);
            builder.append(", locality=");
            builder.append(locality);
            builder.append(", spaceTokenArray=");
            builder.append(spaceTokenArray);
            builder.append(", type=");
            builder.append(type);
            builder.append(", lifetime=");
            builder.append(lifetime);
            builder.append(", lifetimeLeft=");
            builder.append(lifetimeLeft);
            builder.append(", userPermission=");
            builder.append(userPermission);
            builder.append(", groupPermission=");
            builder.append(groupPermission);
            builder.append(", otherPermission=");
            builder.append(otherPermission);
            builder.append(", checkSumType=");
            builder.append(checkSumType);
            builder.append(", checkSumValue=");
            builder.append(checkSumValue);
            builder.append(", subpathInfo=");
            builder.append(subpathInfo);
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
            result = prime * result + ((checkSumType == null) ? 0 : checkSumType.hashCode());
            result = prime * result + ((checkSumValue == null) ? 0 : checkSumValue.hashCode());
            result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
            result = prime * result + ((groupPermission == null) ? 0 : groupPermission.hashCode());
            result = prime * result + ((lifetime == null) ? 0 : lifetime.hashCode());
            result = prime * result + ((lifetimeLeft == null) ? 0 : lifetimeLeft.hashCode());
            result = prime * result + ((locality == null) ? 0 : locality.hashCode());
            result = prime * result + ((modificationTime == null) ? 0 : modificationTime.hashCode());
            result = prime * result + ((otherPermission == null) ? 0 : otherPermission.hashCode());
            result = prime * result + ((retentionPolicy == null) ? 0 : retentionPolicy.hashCode());
            result = prime * result + ((size == null) ? 0 : size.hashCode());
            result = prime * result + ((spaceTokenArray == null) ? 0 : spaceTokenArray.hashCode());
            result = prime * result + ((status == null) ? 0 : status.hashCode());
            result = prime * result + ((stfn == null) ? 0 : stfn.hashCode());
            result = prime * result + ((storageType == null) ? 0 : storageType.hashCode());
            result = prime * result + ((subpathInfo == null) ? 0 : subpathInfo.hashCode());
            result = prime * result + ((type == null) ? 0 : type.hashCode());
            result = prime * result + ((userPermission == null) ? 0 : userPermission.hashCode());
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
            SurlInfo other = (SurlInfo) obj;
            if (checkSumType == null)
            {
                if (other.checkSumType != null)
                {
                    return false;
                }
            }
            else
                if (!checkSumType.equals(other.checkSumType))
                {
                    return false;
                }
            if (checkSumValue == null)
            {
                if (other.checkSumValue != null)
                {
                    return false;
                }
            }
            else
                if (!checkSumValue.equals(other.checkSumValue))
                {
                    return false;
                }
            if (creationTime == null)
            {
                if (other.creationTime != null)
                {
                    return false;
                }
            }
            else
                if (!creationTime.equals(other.creationTime))
                {
                    return false;
                }
            if (groupPermission == null)
            {
                if (other.groupPermission != null)
                {
                    return false;
                }
            }
            else
                if (!groupPermission.equals(other.groupPermission))
                {
                    return false;
                }
            if (lifetime == null)
            {
                if (other.lifetime != null)
                {
                    return false;
                }
            }
            else
                if (!lifetime.equals(other.lifetime))
                {
                    return false;
                }
            if (lifetimeLeft == null)
            {
                if (other.lifetimeLeft != null)
                {
                    return false;
                }
            }
            else
                if (!lifetimeLeft.equals(other.lifetimeLeft))
                {
                    return false;
                }
            if (locality == null)
            {
                if (other.locality != null)
                {
                    return false;
                }
            }
            else
                if (!locality.equals(other.locality))
                {
                    return false;
                }
            if (modificationTime == null)
            {
                if (other.modificationTime != null)
                {
                    return false;
                }
            }
            else
                if (!modificationTime.equals(other.modificationTime))
                {
                    return false;
                }
            if (otherPermission == null)
            {
                if (other.otherPermission != null)
                {
                    return false;
                }
            }
            else
                if (!otherPermission.equals(other.otherPermission))
                {
                    return false;
                }
            if (retentionPolicy == null)
            {
                if (other.retentionPolicy != null)
                {
                    return false;
                }
            }
            else
                if (!retentionPolicy.equals(other.retentionPolicy))
                {
                    return false;
                }
            if (size == null)
            {
                if (other.size != null)
                {
                    return false;
                }
            }
            else
                if (!size.equals(other.size))
                {
                    return false;
                }
            if (spaceTokenArray == null)
            {
                if (other.spaceTokenArray != null)
                {
                    return false;
                }
            }
            else
                if (!spaceTokenArray.equals(other.spaceTokenArray))
                {
                    return false;
                }
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
            if (stfn == null)
            {
                if (other.stfn != null)
                {
                    return false;
                }
            }
            else
                if (!stfn.equals(other.stfn))
                {
                    return false;
                }
            if (storageType == null)
            {
                if (other.storageType != null)
                {
                    return false;
                }
            }
            else
                if (!storageType.equals(other.storageType))
                {
                    return false;
                }
            if (subpathInfo == null)
            {
                if (other.subpathInfo != null)
                {
                    return false;
                }
            }
            else
                if (!subpathInfo.equals(other.subpathInfo))
                {
                    return false;
                }
            if (type == null)
            {
                if (other.type != null)
                {
                    return false;
                }
            }
            else
                if (!type.equals(other.type))
                {
                    return false;
                }
            if (userPermission == null)
            {
                if (other.userPermission != null)
                {
                    return false;
                }
            }
            else
                if (!userPermission.equals(other.userPermission))
                {
                    return false;
                }
            return true;
        }

    }
    
//    public static LsOutputData build(Map output) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
