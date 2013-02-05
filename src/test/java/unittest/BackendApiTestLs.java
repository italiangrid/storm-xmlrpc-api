package unittest;


import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import it.grid.storm.srm.types.Recursion;
import it.grid.storm.srm.types.RecursionLevel;
import it.grid.storm.srm.types.TLifeTimeInSeconds;
import it.grid.storm.srm.types.TRequestToken;
import it.grid.storm.srm.types.TStatusCode;
import it.grid.storm.srm.types.TimeUnit;
import it.grid.storm.xmlrpc.ApiException;
import it.grid.storm.xmlrpc.BackendApi;
import it.grid.storm.xmlrpc.outputdata.FileTransferOutputData;
import it.grid.storm.xmlrpc.outputdata.LsOutputData;
import it.grid.storm.xmlrpc.outputdata.LsOutputData.SurlInfo;
import it.grid.storm.xmlrpc.outputdata.RequestOutputData;
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
public class BackendApiTestLs
{

    static final String beHostname = "omii001-vm01.cnaf.infn.it";
    static final String feHostname = "omii001-vm02.cnaf.infn.it";
    static final Long srmPort = new Long(8444);
    static final String srmEndpoint = feHostname + ":" + srmPort;
    static final Long xmlrpcPort = new Long(8080);
    static final String saRoot = "/testers.eu-emi.eu";

    static final String userDN = "/C=IT/O=INFN/OU=Personal Certificate/L=CNAF/CN=Michele Dibenedetto";
    static final String userFQAN = "/testers.eu-emi.eu/Role=NULL/Capability=NULL";
    static final ArrayList<String> userFQANS = new ArrayList<String>();
    static
    {
        userFQANS.add(userFQAN);
    }

    static final String surlEmptyFolder = "srm://" + srmEndpoint + saRoot
            + "/emptyDir";
    static final String surlFilledFolder = "srm://" + srmEndpoint + saRoot;
    static final String surlUnexistentFolder = "srm://" + srmEndpoint + saRoot
            + "/unexistentDir";
    
    
    static final ArrayList<String> mixedSurls = new ArrayList<String>();
    static
    {
        mixedSurls.add(surlEmptyFolder);
        mixedSurls.add(surlFilledFolder);
        mixedSurls.add(surlUnexistentFolder);
    }

    static final ArrayList<String> unexistentSurls = new ArrayList<String>(1);
    static
    {
        unexistentSurls.add(surlUnexistentFolder);
    }

    static final ArrayList<String> existentSurls = new ArrayList<String>(1);
    static
    {
        existentSurls.add(surlEmptyFolder);
        existentSurls.add(surlFilledFolder);
    }

    private BackendApi client;
    
    private static boolean done = false;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        try
        {
            this.client = new BackendApi(beHostname, xmlrpcPort);
        } catch(Exception e)
        {
            fail("Caught Exception: " + e);
        }
        assertNotNull(client);
        if(!done)
        {
            RequestOutputData output = client.mkdir(userDN, userFQANS, surlEmptyFolder);
            assertNotNull("Request status should never be null", output.getStatus());
            done = true;
        }
        
    }

    @Test
    public void testLs() throws ApiException
    {
        RecursionLevel level = new RecursionLevel(Recursion.LIMITED,0); 
        LsOutputData output = client.lsDetailed(userDN, userFQANS, existentSurls, level);
        assertNotNull("Request output should never be null", output);
        assertNotNull("Request status should never be null", output.getStatus());
        assertEquals("request status should be " + TStatusCode.SRM_SUCCESS, TStatusCode.SRM_SUCCESS, output.getStatus().getStatusCode());
        assertNotNull("Request infos should not be null", output.getInfos());
        assertFalse("Request infos should not be empty", output.getInfos().isEmpty());
        for( SurlInfo a : output.getInfos())
        {
            System.out.println(a.toString());
        }
    }
    
    
    @Test
    public void testLsMixed() throws ApiException
    {
        RecursionLevel level = new RecursionLevel(Recursion.LIMITED,0); 
        LsOutputData output = client.lsDetailed(userDN, userFQANS, mixedSurls, level);
        assertNotNull("Request output should never be null", output);
        assertNotNull("Request status should never be null", output.getStatus());
        assertEquals("request status should be " + TStatusCode.SRM_PARTIAL_SUCCESS, TStatusCode.SRM_PARTIAL_SUCCESS, output.getStatus().getStatusCode());
        assertNotNull("Request infos should not be null", output.getInfos());
        assertFalse("Request infos should not be empty", output.getInfos().isEmpty());
        for( SurlInfo a : output.getInfos())
        {
            System.out.println(a.toString());
        }
    }
}
