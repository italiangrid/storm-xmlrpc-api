package unittest;

import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import it.grid.storm.srm.types.ArrayOfTSpaceToken;
import it.grid.storm.srm.types.SizeUnit;
import it.grid.storm.srm.types.TCheckSumType;
import it.grid.storm.srm.types.TCheckSumValue;
import it.grid.storm.srm.types.TFileLocality;
import it.grid.storm.srm.types.TFileStorageType;
import it.grid.storm.srm.types.TFileType;
import it.grid.storm.srm.types.TGroupID;
import it.grid.storm.srm.types.TGroupPermission;
import it.grid.storm.srm.types.TLifeTimeInSeconds;
import it.grid.storm.srm.types.TPermissionMode;
import it.grid.storm.srm.types.TRequestToken;
import it.grid.storm.srm.types.TRetentionPolicyInfo;
import it.grid.storm.srm.types.TReturnStatus;
import it.grid.storm.srm.types.TSizeInBytes;
import it.grid.storm.srm.types.TSpaceToken;
import it.grid.storm.srm.types.TStatusCode;
import it.grid.storm.srm.types.TUserID;
import it.grid.storm.srm.types.TUserPermission;
import it.grid.storm.srm.types.TimeUnit;
import it.grid.storm.xmlrpc.decoders.DecodingException;
import it.grid.storm.xmlrpc.decoders.LsDecoder;
import it.grid.storm.xmlrpc.outputdata.LsOutputData;
import it.grid.storm.xmlrpc.outputdata.LsOutputData.SurlInfo;
import it.grid.storm.xmlrpc.remote.XmlRpcParameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LsDecoderTest
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    private final HashMap<String, Object> parameters = new HashMap<String, Object>();
    
    private final LinkedList<Map<String, Object>> lsDetails = new LinkedList<Map<String, Object>>();
    private final HashMap<String, Object> file1 = new HashMap<String, Object>();
    private final String stfn1 = "/root/ciao";
    private final HashMap<String, Object> file2 = new HashMap<String, Object>();
    private final String stfn2 = "/root/banane";
    
    private final LinkedList<Map<String, Object>> subpathDetails = new LinkedList<Map<String, Object>>();
    private final HashMap<String, Object> subpathFile = new HashMap<String, Object>();
    private final String stfn3 = "/root/caciotta";
    
    private final TReturnStatus status = new TReturnStatus(TStatusCode.SRM_SUCCESS, "all ok");
    private final TRequestToken token = TRequestToken.getRandom();
    
    private final TSizeInBytes size = TSizeInBytes.make(10, SizeUnit.BYTES);
    private final String fileCreationTime = LsDecoder.dateFormat.format(Calendar.getInstance().getTime());
    private final String fileModificationTime = LsDecoder.dateFormat.format(Calendar.getInstance().getTime());
    private final TFileStorageType storageType = TFileStorageType.PERMANENT;
    private final TRetentionPolicyInfo retentionPolicy = TRetentionPolicyInfo.TAPE0_DISK1_RETENTION_POLICY;
    private final TFileLocality locality = TFileLocality.ONLINE;
    private final ArrayOfTSpaceToken spaceTokenArray = new ArrayOfTSpaceToken(new TSpaceToken[]{TSpaceToken.make("token_1"), TSpaceToken.make("token_2")});
    private final TFileType type = TFileType.FILE;
    private final TLifeTimeInSeconds lifetime = TLifeTimeInSeconds.make(3249834, TimeUnit.SECONDS);
    private final TLifeTimeInSeconds lifetimeLeft = TLifeTimeInSeconds.make(1149834, TimeUnit.SECONDS);
    private final TUserPermission userPermission = new TUserPermission(new TUserID("mimmo"), TPermissionMode.RX);
    private final TGroupPermission groupPermission = new TGroupPermission(new TGroupID("ciccioni"), TPermissionMode.R);
    private final TPermissionMode otherPermission = TPermissionMode.R;
    private final TCheckSumType checkSumType = new TCheckSumType("adler32");
    private final TCheckSumValue checkSumValue = new TCheckSumValue("546789");
    
    
    
    @Before
    public void setUp() throws Exception
    {
        status.encode(parameters, TReturnStatus.PNAME_RETURNSTATUS);
        parameters.put(TRequestToken.PNAME_REQUESTOKEN, token.toString());
        
        status.encode(file1, TReturnStatus.PNAME_STATUS);
        file1.put(XmlRpcParameters.LS_FILE_PATH_KEY, stfn1);
        size.encode(file1, TSizeInBytes.PNAME_SIZE);
        file1.put(XmlRpcParameters.LS_FILE_CREATION_TIME_KEY, fileCreationTime);
        file1.put(XmlRpcParameters.LS_FILE_MODIFICATION_TIME_KEY, fileModificationTime);
        storageType.encode(file1, TFileStorageType.PNAME_FILESTORAGETYPE);
        retentionPolicy.encode(file1, TRetentionPolicyInfo.PNAME_retentionPolicyInfo);
        locality.encode(file1, TFileLocality.PNAME_FILELOCALITY);
        spaceTokenArray.encode(file1, ArrayOfTSpaceToken.PNAME_ARRAYOFSPACETOKENS);
        type.encode(file1, TFileType.PNAME_TYPE);
        lifetime.encode(file1, TLifeTimeInSeconds.PNAME_LIFETIMEASSIGNED);
        lifetimeLeft.encode(file1, TLifeTimeInSeconds.PNAME_LIFETIMELEFT);
        userPermission.encode(file1, TUserPermission.PNAME_OWNERPERMISSION);
        groupPermission.encode(file1, TGroupPermission.PNAME_GROUPPERMISSION);
        otherPermission.encode(file1, TPermissionMode.PNAME_OTHERPERMISSION);
        checkSumType.encode(file1, TCheckSumType.PNAME_CHECKSUMTYPE);
        checkSumValue.encode(file1, TCheckSumValue.PNAME_CHECKSUMVALUE);
        
        lsDetails.add(file1);
        
        status.encode(file2, TReturnStatus.PNAME_STATUS);
        file2.put(XmlRpcParameters.LS_FILE_PATH_KEY, stfn2);

        status.encode(subpathFile, TReturnStatus.PNAME_STATUS);
        subpathFile.put(XmlRpcParameters.LS_FILE_PATH_KEY, stfn3);
        
        subpathDetails.add(subpathFile);
        
        file2.put(XmlRpcParameters.LS_SUBPATH_DETAILS_KEY, subpathDetails);
        
        lsDetails.add(file2);

        parameters.put(XmlRpcParameters.LS_DETAILS_KEY, lsDetails);        
    }

    @Test
    public final void testGetInstance()
    {
        assertNotNull("decoder isntance is null",LsDecoder.getInstance());
    }

    @Test 
    public final void testDecodeNullArgs() throws IllegalArgumentException, DecodingException
    {
        thrown.expect(IllegalArgumentException.class);
        LsDecoder.getInstance().decode(null);
    }

    @Test 
    public final void testDecode() throws IllegalArgumentException, DecodingException 
    {
        LsOutputData data = LsDecoder.getInstance().decode(parameters);
        assertNotNull("decoded data is null",data);
        TReturnStatus decodedStatus = data.getStatus();
        assertNotNull("decoded data returns null return status", decodedStatus);
        TRequestToken decodedToken = data.getToken();
        assertNotNull("decoded data returns null token", decodedToken);
        Collection<SurlInfo> decodedInfos = data.getInfos();
        assertNotNull("decoded data returns null infos", decodedInfos);
        for(SurlInfo decodedInfo : decodedInfos)
        {
            assertNotNull("decoded info stfn is null" , decodedInfo.getStfn());
            assertNotNull("decoded info status is null" , decodedInfo.getStatus());
            assertTrue("Decoded info stfn does not matche the stored value" , decodedInfo.getStfn().equals(stfn1) || decodedInfo.getStfn().equals(stfn2));
            assertTrue("Decoded info status does not matche the stored value" , decodedInfo.getStatus().equals(status));
            if(decodedInfo.getStfn().equals(stfn2))
            {
                assertTrue("decoded info does not contains expected subpath details", decodedInfo.getSubpathInfo() != null && !decodedInfo.getSubpathInfo().isEmpty());
                for(SurlInfo info : decodedInfo.getSubpathInfo())
                {
                    assertNotNull("decoded subpath info stfn is null" , info.getStfn());
                    assertEquals("decoded subpath info stfn is incorrect" , stfn3, info.getStfn());
                    assertNotNull("decoded subpath info status is null" , info.getStatus());
                    assertEquals("decoded subpath info status is incorrect" , status, info.getStatus());
                }
            }
        }
    }
    
}
