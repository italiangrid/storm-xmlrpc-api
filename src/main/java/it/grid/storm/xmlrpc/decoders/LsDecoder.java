package it.grid.storm.xmlrpc.decoders;


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
import it.grid.storm.srm.types.TStatusCode;
import it.grid.storm.srm.types.TUserPermission;
import it.grid.storm.xmlrpc.outputdata.LsOutputData;
import it.grid.storm.xmlrpc.outputdata.LsOutputData.SurlInfo;
import it.grid.storm.xmlrpc.remote.XmlRpcParameters;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Michele Dibenedetto
 */
public class LsDecoder implements OutputDecoder
{

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final LsDecoder instance = new LsDecoder();
    private final ArrayList<TStatusCode> detailRequireingStatuses = new ArrayList<TStatusCode>();

    private LsDecoder()
    {
        detailRequireingStatuses.add(TStatusCode.SRM_SUCCESS);
        detailRequireingStatuses.add(TStatusCode.SRM_PARTIAL_SUCCESS);
    }

    public static LsDecoder getInstance()
    {
        return instance;
    }

    public LsOutputData decode(Map<String, Object> output) throws IllegalArgumentException, DecodingException
    {
        if (output == null)
        {
            throw new IllegalArgumentException("Unable to build the instance. "
                    + "Received null argument: output=" + output);
        }
        if (output.get(TReturnStatus.PNAME_RETURNSTATUS) == null)
        {
            throw new DecodingException("Unable to decode the output. Missing mandatory arguments: output="
                    + output);
        }
        TReturnStatus decodedStatus;
        try
        {
            decodedStatus = TReturnStatus.decode((Map<String, Object>) output.get(TReturnStatus.PNAME_RETURNSTATUS));
        } catch(IllegalArgumentException e)
        {
            throw new DecodingException("Unable to decode the Return status \'"
                    + output.get(TReturnStatus.PNAME_STATUS) + "\'. IllegalArgumentException: "
                    + e.getMessage());
        }
        if(detailRequireingStatuses.contains(decodedStatus.getStatusCode()) && output.get(XmlRpcParameters.LS_DETAILS_KEY) == null)
        {
            throw new DecodingException("Unable to decode the output. Missing mandatory arguments: output="
                    + output);
        }
        TRequestToken token = null;
        if (output.get(TRequestToken.PNAME_REQUESTOKEN) != null)
        {
            try
            {
                token = TRequestToken.decode(output, TRequestToken.PNAME_REQUESTOKEN);    
            } catch(IllegalArgumentException e)
            {
                throw new DecodingException("Unable to decode the Request Token \'"
                                            + output.get(TRequestToken.PNAME_REQUESTOKEN) + "\'. IllegalArgumentException: "
                                            + e.getMessage());
            }
            
        }
        
        Collection<Map<String,Object>> surlsInfo = (Collection<Map<String, Object>>) output.get(XmlRpcParameters.LS_DETAILS_KEY);
        LinkedList<SurlInfo> infos = new LinkedList<SurlInfo>();
        for (Map<String, Object> surlInfo : surlsInfo)
        {
            try
            {
                infos.add(decodeSurlInfo(surlInfo));
            } catch(IllegalArgumentException e)
            {
                throw new DecodingException("Unable to decode the Surl Info Token \'"
                                            + surlInfo + "\'. IllegalArgumentException: "
                                            + e.getMessage());
            }
            
        }
        if(token != null)
        {
            return new LsOutputData(decodedStatus, infos, token);
        }
        else
        {
            return new LsOutputData(decodedStatus, infos);    
        }
    }
    
    private SurlInfo decodeSurlInfo(Map<String, Object> surlInfo) throws DecodingException, IllegalArgumentException
    {
        if(surlInfo == null)
        {
            throw new IllegalArgumentException("Unable to perform decoding, received null argument: surlInfo=" + surlInfo);
        }
        if (surlInfo.get(XmlRpcParameters.LS_FILE_PATH_KEY) == null
                || surlInfo.get(TReturnStatus.PNAME_STATUS) == null)
        {
            throw new DecodingException("Unable to decode the output. Missing mandatory fields: surlInfo="
                    + surlInfo);
        }
        String stfn = (String) surlInfo.get(XmlRpcParameters.LS_FILE_PATH_KEY);
        TReturnStatus status;
        try
        {
            status = TReturnStatus.decode((Map<String, Object>) surlInfo.get(TReturnStatus.PNAME_STATUS));
        } catch(IllegalArgumentException e)
        {
            throw new DecodingException("Unable to decode the Return status \'"
                    + surlInfo.get(TReturnStatus.PNAME_STATUS) + "\'. IllegalArgumentException: "
                    + e.getMessage());
        }
        SurlInfo info = new SurlInfo(stfn, status);
        
        if (surlInfo.containsKey(TSizeInBytes.PNAME_SIZE)) 
        {
            if(surlInfo.get(TSizeInBytes.PNAME_SIZE) == null)
            {
                throw new DecodingException("Unable to decode the surlInfo. Null " + TSizeInBytes.PNAME_SIZE + " attribute");
            }
            info.setSize(TSizeInBytes.decode(surlInfo, TSizeInBytes.PNAME_SIZE));
        }
        if (surlInfo.containsKey(XmlRpcParameters.LS_FILE_CREATION_TIME_KEY)) 
        {
            String creationTime = (String) surlInfo.get(XmlRpcParameters.LS_FILE_CREATION_TIME_KEY);
            if(creationTime == null)
            {
                throw new DecodingException("Unable to decode the surlInfo. Null " + XmlRpcParameters.LS_FILE_CREATION_TIME_KEY + " attribute");
            }
            try
            {
                info.setCreationTime(dateFormat.parse(creationTime));
            } catch(ParseException e)
            {
                throw new DecodingException("Unable to decode the CreationTime. ParseException: " + e.getMessage());
            }
        }
        if (surlInfo.containsKey(XmlRpcParameters.LS_FILE_MODIFICATION_TIME_KEY)) 
        {
            String modificationTime = (String) surlInfo.get(XmlRpcParameters.LS_FILE_MODIFICATION_TIME_KEY);
            if(modificationTime == null)
            {
                throw new DecodingException("Unable to decode the surlInfo. Null " + XmlRpcParameters.LS_FILE_MODIFICATION_TIME_KEY + " attribute");
            }
            try
            {
                info.setModificationTime(dateFormat.parse(modificationTime));
            } catch(ParseException e)
            {
                throw new DecodingException("Unable to decode the ModificationTime. ParseException: " + e.getMessage());
            }
        }
        if (surlInfo.containsKey(TFileStorageType.PNAME_FILESTORAGETYPE)) 
        {
            if(surlInfo.get(TFileStorageType.PNAME_FILESTORAGETYPE) == null)
            {
                throw new DecodingException("Unable to decode the surlInfo. Null " + TFileStorageType.PNAME_FILESTORAGETYPE + " attribute");
            }
            info.setStorageType(TFileStorageType.decode(surlInfo, TFileStorageType.PNAME_FILESTORAGETYPE));
        }
        if (surlInfo.containsKey(TRetentionPolicyInfo.PNAME_retentionPolicyInfo)) 
        {
            if(surlInfo.get(TRetentionPolicyInfo.PNAME_retentionPolicyInfo) == null)
            {
                throw new DecodingException("Unable to decode the surlInfo. Null " + TRetentionPolicyInfo.PNAME_retentionPolicyInfo + " attribute");
            }
            info.setRetentionPolicy(TRetentionPolicyInfo.decode(surlInfo, TRetentionPolicyInfo.PNAME_retentionPolicyInfo));
        }
        if (surlInfo.containsKey(TFileLocality.PNAME_FILELOCALITY)) 
        {
            if(surlInfo.get(TFileLocality.PNAME_FILELOCALITY) == null)
            {
                throw new DecodingException("Unable to decode the surlInfo. Null " + TFileLocality.PNAME_FILELOCALITY + " attribute");
            }
            info.setLocality(TFileLocality.decode(surlInfo, TFileLocality.PNAME_FILELOCALITY));
        }
        if (surlInfo.containsKey(ArrayOfTSpaceToken.PNAME_ARRAYOFSPACETOKENS)) 
        {
            if(surlInfo.get(ArrayOfTSpaceToken.PNAME_ARRAYOFSPACETOKENS) == null)
            {
                throw new DecodingException("Unable to decode the surlInfo. Null " + ArrayOfTSpaceToken.PNAME_ARRAYOFSPACETOKENS + " attribute");
            }
            info.setSpaceTokenArray(ArrayOfTSpaceToken.decode(surlInfo, ArrayOfTSpaceToken.PNAME_ARRAYOFSPACETOKENS));
        }
        if (surlInfo.containsKey(TFileType.PNAME_TYPE)) 
        {
            if(surlInfo.get(TFileType.PNAME_TYPE) == null)
            {
                throw new DecodingException("Unable to decode the surlInfo. Null " + TFileType.PNAME_TYPE + " attribute");
            }
            info.setType(TFileType.decode(surlInfo, TFileType.PNAME_TYPE));
        }
        if (surlInfo.containsKey(TLifeTimeInSeconds.PNAME_LIFETIMEASSIGNED)) 
        {
            if(surlInfo.get(TLifeTimeInSeconds.PNAME_LIFETIMEASSIGNED) == null)
            {
                throw new DecodingException("Unable to decode the surlInfo. Null " + TLifeTimeInSeconds.PNAME_LIFETIMEASSIGNED + " attribute");
            }
            info.setLifetime(TLifeTimeInSeconds.decode(surlInfo, TLifeTimeInSeconds.PNAME_LIFETIMEASSIGNED));
        }
        if (surlInfo.containsKey(TLifeTimeInSeconds.PNAME_LIFETIMELEFT)) 
        {
            if(surlInfo.get(TLifeTimeInSeconds.PNAME_LIFETIMELEFT) == null)
            {
                throw new DecodingException("Unable to decode the surlInfo. Null " + TLifeTimeInSeconds.PNAME_LIFETIMELEFT + " attribute");
            }
            info.setLifetimeLeft(TLifeTimeInSeconds.decode(surlInfo, TLifeTimeInSeconds.PNAME_LIFETIMELEFT));
        }
        if (surlInfo.containsKey(TUserPermission.PNAME_OWNERPERMISSION)) 
        {
            if(surlInfo.get(TUserPermission.PNAME_OWNERPERMISSION) == null)
            {
                throw new DecodingException("Unable to decode the surlInfo. Null " + TUserPermission.PNAME_OWNERPERMISSION + " attribute");
            }
            info.setUserPermission(TUserPermission.decode(surlInfo, TUserPermission.PNAME_OWNERPERMISSION));
        }
        if (surlInfo.containsKey(TGroupPermission.PNAME_GROUPPERMISSION)) 
        {
            if(surlInfo.get(TGroupPermission.PNAME_GROUPPERMISSION) == null)
            {
                throw new DecodingException("Unable to decode the surlInfo. Null " + TGroupPermission.PNAME_GROUPPERMISSION + " attribute");
            }
            info.setGroupPermission(TGroupPermission.decode(surlInfo, TGroupPermission.PNAME_GROUPPERMISSION));
        }
        if (surlInfo.containsKey(TPermissionMode.PNAME_OTHERPERMISSION)) 
        {
            if(surlInfo.get(TPermissionMode.PNAME_OTHERPERMISSION) == null)
            {
                throw new DecodingException("Unable to decode the surlInfo. Null " + TPermissionMode.PNAME_OTHERPERMISSION + " attribute");
            }
            info.setOtherPermission(TPermissionMode.decode(surlInfo, TPermissionMode.PNAME_OTHERPERMISSION));
        }
        if (surlInfo.containsKey(TCheckSumType.PNAME_CHECKSUMTYPE)) 
        {
            if(surlInfo.get(TCheckSumType.PNAME_CHECKSUMTYPE) == null)
            {
                throw new DecodingException("Unable to decode the surlInfo. Null " + TCheckSumType.PNAME_CHECKSUMTYPE + " attribute");
            }
            info.setCheckSumType(TCheckSumType.decode(surlInfo, TCheckSumType.PNAME_CHECKSUMTYPE));
        }
        if (surlInfo.containsKey(TCheckSumValue.PNAME_CHECKSUMVALUE)) 
        {
            if(surlInfo.get(TCheckSumValue.PNAME_CHECKSUMVALUE) == null)
            {
                throw new DecodingException("Unable to decode the surlInfo. Null " + TCheckSumValue.PNAME_CHECKSUMVALUE + " attribute");
            }
            info.setCheckSumValue(TCheckSumValue.decode(surlInfo, TCheckSumValue.PNAME_CHECKSUMVALUE));
        }

        Collection<Map<String,Object>> subpathsInfo = (Collection<Map<String, Object>>) surlInfo.get(XmlRpcParameters.LS_SUBPATH_DETAILS_KEY);
        if(subpathsInfo != null)
        {
            for (Map<String, Object> subpathInfo : subpathsInfo)
            {
                info.addSubpathInfo(decodeSurlInfo(subpathInfo));
            }
        }
        return info;
    }

}
