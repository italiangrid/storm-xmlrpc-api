package unittest;


import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import it.grid.storm.srm.types.TLifeTimeInSeconds;
import it.grid.storm.srm.types.TRequestToken;
import it.grid.storm.srm.types.TStatusCode;
import it.grid.storm.srm.types.TimeUnit;
import it.grid.storm.xmlrpc.ApiException;
import it.grid.storm.xmlrpc.BackendApi;
import it.grid.storm.xmlrpc.outputdata.FileTransferOutputData;
import it.grid.storm.xmlrpc.outputdata.SurlArrayRequestOutputData;
import it.grid.storm.xmlrpc.remote.XmlRpcDefaults;
import org.junit.Before;
import org.junit.Test;

/**
 * @author baltico
 */
/**
 * @author Michele Dibenedetto
 */
public class BackendApiTestDatatransfer
{

    static final String hostname = "omii005-vm03.cnaf.infn.it";
    static final Long xmlrpcPort = new Long(8080);
    static final String saRoot = "/testers.eu-emi.eu";

    static final String userDN = "/C=IT/O=INFN/OU=Personal Certificate/L=CNAF/CN=Michele Dibenedetto";
    static final String userFQAN = "/testers.eu-emi.eu/Role=NULL/Capability=NULL";
    static final ArrayList<String> userFQANS = new ArrayList<String>();
    static
    {
        userFQANS.add(userFQAN);
    }

    static final String surlUnexistentFilePtP = "srm://" + hostname + ":8444" + saRoot
            + "/myUnexistentFilePtP";
    static final String surlUnexistentFilePtPProtocol = "srm://" + hostname + ":8444" + saRoot
            + "/myUnexistentFilePtPProtocol";
    static final String surlUnexistentFilePtPTimes = "srm://" + hostname + ":8444" + saRoot
            + "/myUnexistentFilePtPTimes";
    static final String surlUnexistentFilePtPProtocolTimes = "srm://" + hostname + ":8444" + saRoot
    + "/myUnexistentFilePtPProtocolTimes";
    
    private static String surlPutDoneSpaceAvailable = "srm://" + hostname + ":8444" + saRoot
    + "/myPutDoneSpaceAvailable";
    
    static final ArrayList<String> mixedSurls = new ArrayList<String>();
    static
    {
        mixedSurls.add(surlUnexistentFilePtP);
        mixedSurls.add(surlUnexistentFilePtPProtocol);
        mixedSurls.add(surlUnexistentFilePtPTimes);
        mixedSurls.add(surlUnexistentFilePtPProtocolTimes);
        mixedSurls.add(surlPutDoneSpaceAvailable);
    }

    static final ArrayList<String> unexistentSurls = new ArrayList<String>(1);
    static
    {
        unexistentSurls.add(surlUnexistentFilePtP);
        unexistentSurls.add(surlUnexistentFilePtPProtocol);
        unexistentSurls.add(surlUnexistentFilePtPTimes);
        unexistentSurls.add(surlUnexistentFilePtPProtocolTimes);
    }

    static final ArrayList<String> existentSurls = new ArrayList<String>(1);
    static
    {
        existentSurls.add(surlPutDoneSpaceAvailable);
    }

    static final String protocol = "file";
    static final LinkedList<String> transferProtocols = new LinkedList<String>();
    static
    {
        transferProtocols.add(protocol);
    }

    static final TRequestToken unexistentToken = TRequestToken.getRandom();

    TLifeTimeInSeconds desiredFileLifetime = TLifeTimeInSeconds.make(3600, TimeUnit.SECONDS);
    TLifeTimeInSeconds desiredPinLifetime = TLifeTimeInSeconds.make(3600, TimeUnit.SECONDS);

    private BackendApi client;
    
    private static TRequestToken surlPutDoneSpaceAvailableToken = null;
    private static boolean done = false;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        try
        {
            this.client = new BackendApi(hostname, xmlrpcPort);
        } catch(Exception e)
        {
            fail("Caught Exception: " + e);
        }
        assertNotNull(client);
        if(!done)
        {
            FileTransferOutputData output = client.prepareToPut(userDN, userFQANS, surlPutDoneSpaceAvailable);
            assertNotNull("Request status should never be null", output.getStatus());
            assertNotNull("Request token should never be null", output.getToken());
            surlPutDoneSpaceAvailableToken = output.getToken();
            assertTrue("request status sould be " + TStatusCode.SRM_SPACE_AVAILABLE + " or "
                              + TStatusCode.SRM_FILE_BUSY + " or " + TStatusCode.SRM_DUPLICATION_ERROR ,
                      TStatusCode.SRM_SPACE_AVAILABLE.equals(output.getStatus().getStatusCode()) || 
                      TStatusCode.SRM_FILE_BUSY.equals(output.getStatus().getStatusCode()) ||
                      TStatusCode.SRM_DUPLICATION_ERROR.equals(output.getStatus().getStatusCode()));
            done = true;
        }
        
    }

    @Test
    public void testPtp() throws ApiException
    {
        FileTransferOutputData output = client.prepareToPut(userDN, userFQANS, surlUnexistentFilePtP);
        assertNotNull("Request output should never be null", output);
        assertNotNull("Request surl should never be null", output.getSurl());
        assertEquals("Returned surl should match the sent one", surlUnexistentFilePtP, output.getSurl());
        assertNotNull("Request token should never be null", output.getToken());
        assertNotNull("Request token value should never be null", output.getToken().getValue());
        assertFalse("Token should not be empty", output.getToken().getValue().trim().isEmpty());
        assertNotNull("Request status should never be null", output.getStatus());
        assertEquals("request status should be " + TStatusCode.SRM_SPACE_AVAILABLE, TStatusCode.SRM_SPACE_AVAILABLE, output.getStatus().getStatusCode());
        assertNotNull("Request turl should not be null in this case", output.getTurl());
        assertFalse("Turl should not be empty", output.getTurl().trim().isEmpty());
        assertTrue("Turl should be a " + XmlRpcDefaults.TURL_PREFIX + " turl",
                   output.getTurl().startsWith(XmlRpcDefaults.TURL_PREFIX));
    }

    @Test
    public void testPtpWithProtocols() throws ApiException
    {
        FileTransferOutputData output = client.prepareToPut(userDN, userFQANS, surlUnexistentFilePtPProtocol,
                                                            transferProtocols);
        assertNotNull("Request output should never be null", output);
        assertNotNull("Request surl should never be null", output.getSurl());
        assertEquals("Returned surl should match the sent one", surlUnexistentFilePtPProtocol,
                     output.getSurl());
        assertNotNull("Request token should never be null", output.getToken());
        assertNotNull("Request token value should never be null", output.getToken().getValue());
        assertFalse("Token should not be empty", output.getToken().getValue().trim().isEmpty());
        assertNotNull("Request status should never be null", output.getStatus());
        assertEquals("request status should be " + TStatusCode.SRM_SPACE_AVAILABLE, TStatusCode.SRM_SPACE_AVAILABLE, output.getStatus().getStatusCode());
        assertNotNull("Request turl should not be null in this case", output.getTurl());
        assertFalse("Turl should not be empty", output.getTurl().trim().isEmpty());
        assertTrue("Turl should be a " + transferProtocols.getFirst() + " turl",
                   output.getTurl().startsWith(transferProtocols.getFirst()));
    }

    @Test
    public void testPtpWithTimes() throws ApiException
    {
        FileTransferOutputData output = client.prepareToPut(userDN, userFQANS, surlUnexistentFilePtPTimes,
                                                            desiredFileLifetime, desiredFileLifetime);
        assertNotNull("Request output should never be null", output);
        assertNotNull("Request surl should never be null", output.getSurl());
        assertEquals("Returned surl should match the sent one", surlUnexistentFilePtPTimes,
                     output.getSurl());
        assertNotNull("Request token should never be null", output.getToken());
        assertNotNull("Request token value should never be null", output.getToken().getValue());
        assertFalse("Token should not be empty", output.getToken().getValue().trim().isEmpty());
        assertNotNull("Request status should never be null", output.getStatus());
        assertEquals("request status sould be " + TStatusCode.SRM_SPACE_AVAILABLE, TStatusCode.SRM_SPACE_AVAILABLE, output.getStatus().getStatusCode());
        assertNotNull("Request turl should not be null in this case", output.getTurl());
        assertFalse("Turl should not be empty", output.getTurl().trim().isEmpty());
        assertTrue("Turl should be a " + XmlRpcDefaults.TURL_PREFIX + " turl",
                   output.getTurl().startsWith(XmlRpcDefaults.TURL_PREFIX));
    }

    @Test
    public void testPtpWithProtocolsAndTimes() throws ApiException
    {
        FileTransferOutputData output = client.prepareToPut(userDN, userFQANS, surlUnexistentFilePtPProtocolTimes,
                                                            transferProtocols, desiredFileLifetime,
                                                            desiredFileLifetime);
        assertNotNull("Request output should never be null", output);
        assertNotNull("Request surl should never be null", output.getSurl());
        assertEquals("Returned surl should match the sent one", surlUnexistentFilePtPProtocolTimes,
                     output.getSurl());
        assertNotNull("Request token should never be null", output.getToken());
        assertNotNull("Request token value should never be null", output.getToken().getValue());
        assertFalse("Token should not be empty", output.getToken().getValue().trim().isEmpty());
        assertNotNull("Request status should never be null", output.getStatus());
        assertEquals("request status sould be " + TStatusCode.SRM_SPACE_AVAILABLE, TStatusCode.SRM_SPACE_AVAILABLE, output.getStatus().getStatusCode());
        assertNotNull("Request turl should not be null in this case", output.getTurl());
        assertFalse("Turl should not be empty", output.getTurl().trim().isEmpty());
        assertTrue("Turl should be a " + transferProtocols.getFirst() + " turl",
                   output.getTurl().startsWith(transferProtocols.getFirst()));
    }
    
    @Test
    public void testPtpSpaceAvailableSurl() throws ApiException
    {
        FileTransferOutputData output = client.prepareToPut(userDN, userFQANS, surlPutDoneSpaceAvailable);
        assertNotNull("Request output should never be null", output);
        assertNotNull("Request surl should never be null", output.getSurl());
        assertEquals("Returned surl should match the sent one", surlPutDoneSpaceAvailable, output.getSurl());
        assertNotNull("Request token should never be null", output.getToken());
        assertNotNull("Request token value should never be null", output.getToken().getValue());
        assertFalse("Token should not be empty", output.getToken().getValue().trim().isEmpty());
        assertNotNull("Request status should never be null", output.getStatus());
        assertEquals("request status sould be " + TStatusCode.SRM_FILE_BUSY, TStatusCode.SRM_FILE_BUSY, output.getStatus().getStatusCode());
    }

    @Test
    public void testPutDone()
    {
        SurlArrayRequestOutputData output = null;
        try
        {
            output = client.putDone(userDN, userFQANS, Arrays.asList(new String[]{surlPutDoneSpaceAvailable}), surlPutDoneSpaceAvailableToken);
        } catch(Exception e)
        {
            fail("Caught Exception during ptp call: " + e);
        }
        assertNotNull("Request output should never be null", output);
        assertEquals("request status sould be " + TStatusCode.SRM_SUCCESS, TStatusCode.SRM_SUCCESS, output.getStatus().getStatusCode());
        assertNotNull("Request surl status should never be null", output.getStatus(surlPutDoneSpaceAvailable));
        assertTrue("Surl status should be successfull" , output.getStatus(surlPutDoneSpaceAvailable).isSRM_SUCCESS());
    }
}
