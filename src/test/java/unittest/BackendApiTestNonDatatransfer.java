package unittest;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import it.grid.storm.srm.types.Recursion;
import it.grid.storm.srm.types.RecursionLevel;
import it.grid.storm.srm.types.TRequestToken;
import it.grid.storm.xmlrpc.ApiException;
import it.grid.storm.xmlrpc.BackendApi;
import it.grid.storm.xmlrpc.outputdata.LsOutputData;
import it.grid.storm.xmlrpc.outputdata.LsOutputData.SurlInfo;
import it.grid.storm.xmlrpc.outputdata.FileTransferOutputData;
import it.grid.storm.xmlrpc.outputdata.PingOutputData;
import it.grid.storm.xmlrpc.outputdata.RequestOutputData;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author baltico
 *
 */
/**
 * @author Michele Dibenedetto
 *
 */
public class BackendApiTestNonDatatransfer {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
//    static final String hostname = "etics-06-vm03.cnaf.infn.it";
    static final String hostname = "omii005-vm03.cnaf.infn.it";
    
    static final String saRoot = "/testers.eu-emi.eu";
//    static final String saRoot = "/dteam";
    
	static final Long xmlrpcPort = new Long(8080);
	static final String userDN = "/C=IT/O=INFN/OU=Personal Certificate/L=CNAF/CN=Michele Dibenedetto";
	static final String userFQAN = "/testers.eu-emi.eu/Role=NULL/Capability=NULL";
	
//	static final String userDN = "/C=IT/O=INFN/OU=Personal Certificate/L=CNAF/CN=Enrico Vianello";
//	static final String userFQAN = "/dteam/Role=NULL/Capability=NULL";
//	static final String userFQAN2 = "/dteam/NGI_IT/Role=NULL/Capability=NULL";
	
	static final ArrayList<String> userFQANS = new ArrayList<String>();
	static
	{
	    userFQANS.add(userFQAN);
//	    userFQANS.add(userFQAN2);   
	}

    static final String surlUnexistentFile = "srm://" + hostname + ":8444" + saRoot + "/myFile";
    static final String surlUnexistentFolder = "srm://" + hostname + ":8444" + saRoot + "/myFolder/";
    static final String surlSaRoot = "srm://" + hostname + ":8444" + saRoot;
    static final String surlExistentDetailedFolder = "srm://" + hostname + ":8444" + saRoot
    + "/detailedFolder/"; 
    static final String surlExistentDetailed = surlExistentDetailedFolder + "detaled.txt";
    
    static final String surlExistentFolderRmdir = "srm://" + hostname + ":8444" + saRoot + "/myFolderRmdir";
    static final String surlUnexistentFolderRmdir = "srm://" + hostname + ":8444" + saRoot + "/myFolderRmdirUnexistent";
    
    static final String surlExistentFolderMkdir = "srm://" + hostname + ":8444" + saRoot + "/myFolderMkdir";
    static final String surlUnexistentFolderMkdir = "srm://" + hostname + ":8444" + saRoot + "/myFolderMkdirUnexistent";
    
    static final String surlExistentFileRm = "srm://" + hostname + ":8444" + saRoot + "/myFileRm2";
    static final String surlUnexistentFileRm = "srm://" + hostname + ":8444" + saRoot + "/myFileRmUnexistent";
    
    static final ArrayList<String> mixedSurls = new ArrayList<String>();
    static
    {
        mixedSurls.add(surlSaRoot);
        mixedSurls.add(surlExistentDetailedFolder);
        mixedSurls.add(surlExistentDetailed);
        mixedSurls.add(surlExistentFolderRmdir);
        mixedSurls.add(surlExistentFolderMkdir);
        mixedSurls.add(surlExistentFileRm);
        mixedSurls.add(surlUnexistentFile);        
        mixedSurls.add(surlUnexistentFolder);
        mixedSurls.add(surlUnexistentFolderRmdir);
        mixedSurls.add(surlUnexistentFolderMkdir);
        mixedSurls.add(surlUnexistentFileRm);
    }
    
    static final ArrayList<String> unexistentSurls = new ArrayList<String>(1);
    static{
        unexistentSurls.add(surlUnexistentFile);
        unexistentSurls.add(surlUnexistentFolder);
        unexistentSurls.add(surlUnexistentFolderRmdir);
        unexistentSurls.add(surlUnexistentFolderMkdir);
        unexistentSurls.add(surlUnexistentFileRm);
    }
    
    static final ArrayList<String> existentSurls = new ArrayList<String>(1);
    static{
        existentSurls.add(surlSaRoot);
        existentSurls.add(surlExistentDetailed);
        existentSurls.add(surlExistentDetailedFolder);
        existentSurls.add(surlExistentFolderRmdir);
        existentSurls.add(surlExistentFolderMkdir);
        existentSurls.add(surlExistentFileRm);
    }
    
	static final TRequestToken unexistentToken = TRequestToken.getRandom(); 
	
	private static boolean rmTestDone = false;
	private static boolean firstRun = true;
	
	private BackendApi client;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {
			this.client = new BackendApi(hostname, xmlrpcPort);
		} catch (Exception e) {
			fail("Caught Exception: " + e);
		}
		assertNotNull(client);
		if(firstRun)
		{
		    FileTransferOutputData output = client.prepareToPut(userDN, userFQANS, surlExistentFileRm);
		    client.putDone(userDN, userFQANS, Arrays.asList(new String[]{surlExistentFileRm}), output.getToken());
		    client.mkdir(userDN, userFQANS, surlExistentFolderRmdir);
		    client.mkdir(userDN, userFQANS, surlExistentFolderMkdir);
		    firstRun = false;
		}
		if(!rmTestDone)
		{
		    
		    LsOutputData output = client.ls(userDN, userFQANS, Arrays.asList(surlExistentFileRm));
	        assertTrue("surlExistentFileRm does not exist, create \'" + surlExistentFileRm + "\'" , output.getStatus().isSRM_SUCCESS());		    
		}
	}

    /**
     * Test method for {@link it.grid.storm.xmlrpc.BackendApi#ping()}.
     */
    @Test
    public final void testPing() throws ApiException 
    {
        PingOutputData output = client.ping(userDN, userFQANS);
        assertNotNull("PingOutputData should not be null", output);
        assertNotNull("versioninfo should not be null", output.getVersionInfo());
        assertFalse("versioninfo should not be empty", output.getVersionInfo().trim().isEmpty());
        assertNotNull("OS should not be null", output.getBeOs());
        assertFalse("OS should not be empty", output.getBeOs().trim().isEmpty());
        assertNotNull("Version should not be null", output.getBeVersion());
        assertFalse("Version should not be empty", output.getBeVersion().trim().isEmpty());
    }
    
    @Test
    public final void testPingEmptyFQUANS() throws ApiException, IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        client.ping(userDN, new LinkedList<String>());
    }

    @Test
    public final void testLsExistentSurl() throws ApiException
    {

        LsOutputData output = client.ls(userDN, userFQANS, existentSurls);
        assertNotNull("LsOutputData should not be null", output);
        assertNotNull("Status should not be null", output.getStatus());
        assertNotNull("Infos should not be null", output.getInfos());
        assertTrue("Request status should be successfull", output.getStatus().isSRM_SUCCESS());
        assertEquals("Infos should contain " + existentSurls.size() + " element, not " + output.getInfos().size(), existentSurls.size(), output.getInfos().size());
        SurlInfo info = output.getInfos().iterator().next();
        assertTrue("SurlInfo status should be successfull", info.getStatus().isSRM_SUCCESS());
        if(info.getStfn().endsWith("/"))
        {
            assertTrue("Returned stfn " + info.getStfn() + " does not matches sent surl : " + surlSaRoot,
                       surlSaRoot.contains(info.getStfn().subSequence(0, info.getStfn().length() - 1)));    
        }
        else
        {
            assertTrue("Returned stfn " + info.getStfn() + " does not matches sent surl : " + surlSaRoot,
                       surlSaRoot.contains(info.getStfn().subSequence(0, info.getStfn().length())));
        }
        
        assertFalse("ls should not produce a token" , output.hasToken());
    }
    
    @Test
    public void testLsUnexistentSurl() throws ApiException
    {

        LsOutputData output = client.ls(userDN, userFQANS, unexistentSurls);
        assertNotNull("LsOutputData should not be null", output);
        assertNotNull("Status should not be null", output.getStatus());
        assertNotNull("Infos should not be null", output.getInfos());
        assertTrue("Request status not should be successfull", !output.getStatus().isSRM_SUCCESS());
        assertEquals("Infos should contain " + unexistentSurls.size() + " elements, not " + output.getInfos().size(), unexistentSurls.size(), output.getInfos().size());
        SurlInfo info = output.getInfos().iterator().next();
        assertTrue("SurlInfo status not should be successfull", !info.getStatus().isSRM_SUCCESS());
        if(info.getStfn().endsWith("/"))
        {
            assertTrue("Returned stfn does not matches sent surl : " + info.getStfn(),
                       surlUnexistentFile.contains(info.getStfn().subSequence(0, info.getStfn().length() - 1)));
        }
        else
        {
            assertTrue("Returned stfn does not matches sent surl : " + info.getStfn(),
                       surlUnexistentFile.contains(info.getStfn().subSequence(0, info.getStfn().length())));
        }
        
        
        assertFalse("ls should not produce a token" , output.hasToken());
    }
    
    @Test
    public void testLsMixedSurl() throws ApiException
    {

        LsOutputData output = client.ls(userDN, userFQANS, mixedSurls);
        assertNotNull("LsOutputData should not be null", output);
        assertNotNull("Status should not be null", output.getStatus());
        assertNotNull("Infos should not be null", output.getInfos());
        assertTrue("Request status not should be successfull", !output.getStatus().isSRM_SUCCESS());
        assertEquals("Infos should contain " + mixedSurls.size() + " elements, not output.getInfos().size()", mixedSurls.size(), output.getInfos().size());
        boolean foundExistent = false, foundUnexistent = false;
        for(SurlInfo info: output.getInfos())
        {
            boolean isSurlSaRoot;
            if(info.getStfn().endsWith("/"))
            {
                isSurlSaRoot = surlSaRoot.contains(info.getStfn().subSequence(0, info.getStfn().length() - 1));
            }
            else
            {
                isSurlSaRoot = surlSaRoot.contains(info.getStfn().subSequence(0, info.getStfn().length()));
            }
            if(isSurlSaRoot)
            {
                foundExistent = true;
                assertTrue("SurlInfo status should be successfull", info.getStatus().isSRM_SUCCESS());
            }
            else
            {
                boolean isSurlUnexistentFile;
                if(info.getStfn().endsWith("/"))
                {
                    isSurlUnexistentFile = surlUnexistentFile.contains(info.getStfn().subSequence(0, info.getStfn().length() - 1));
                }
                else
                {
                    isSurlUnexistentFile = surlUnexistentFile.contains(info.getStfn().subSequence(0, info.getStfn().length()));
                }
                if(isSurlUnexistentFile)
                {
                    foundUnexistent = true;
                    assertTrue("SurlInfo status not should be successfull", !info.getStatus().isSRM_SUCCESS());
                }
            }
        }
        assertTrue("Not all the sent surl have been returned", foundExistent && foundUnexistent);
        assertFalse("ls should not produce a token" , output.hasToken());
    }
   
   @Test
   public void testLsRecursiveLimited() throws ApiException {
       LsOutputData output = client.ls(userDN, userFQANS, existentSurls, new RecursionLevel(Recursion.LIMITED, 3));
       assertNotNull("LsOutputData should not be null", output);
       assertNotNull("Status should not be null", output.getStatus());
       assertNotNull("Infos should not be null", output.getInfos());
       assertTrue("Request status should be successfull", output.getStatus().isSRM_SUCCESS());
       assertFalse("ls should not produce a token" , output.hasToken());
   }
   
   @Test
   public void testLsDetailed() throws ApiException {
       LsOutputData output = client.lsDetailed(userDN, userFQANS, existentSurls);
       assertNotNull("LsOutputData should not be null", output);
       assertNotNull("Status should not be null", output.getStatus());
       assertNotNull("Infos should not be null", output.getInfos());
       assertTrue("Request status should be successfull", output.getStatus().isSRM_SUCCESS());
       SurlInfo info = output.getInfos().iterator().next();
       assertNotNull("Info user permission should not be null", info.getUserPermission());
       assertNotNull("Info subpatInfo should not be null", info.getSubpathInfo());
       assertFalse("ls should not produce a token" , output.hasToken());
   }
   
   @Test
   public void testLsDetailedRecursiveLimited() throws ApiException {
       LsOutputData output = client.lsDetailed(userDN, userFQANS, existentSurls, new RecursionLevel(Recursion.LIMITED, 3));
       assertNotNull("LsOutputData should not be null", output);
       assertNotNull("Status should not be null", output.getStatus());
       assertNotNull("Infos should not be null", output.getInfos());
       assertTrue("Request status should be successfull", output.getStatus().isSRM_SUCCESS());
       assertNotNull("Info user permission should not be null", output.getInfos().iterator().next().getUserPermission());
       boolean found = false;
       for(SurlInfo info : output.getInfos())
       {
           if(surlExistentDetailedFolder.contains(info.getStfn().subSequence(0, info.getStfn().length())) && !surlSaRoot.contains(info.getStfn().subSequence(0, info.getStfn().length() - 1)))
           {
               assertNotNull("surlExistentDetailedFolder subpatInfo should not be null", info.getSubpathInfo());
               assertFalse("The folder surlExistentDetailedFolder should not be empty" , info.getSubpathInfo().isEmpty());
               found  = true;
               for(SurlInfo subInfo : info.getSubpathInfo())
               {
                   if(surlExistentDetailed.contains(subInfo.getStfn().subSequence(0, subInfo.getStfn().length())))
                   {
                       assertNotNull("surlExistentDetailed should have a not null checksum", subInfo.getCheckSumValue());
                       assertFalse("surlExistentDetailed should have a not empty checksum", subInfo.getCheckSumValue().getValue().trim().isEmpty());
                   }
               }
           }
       }
       assertTrue("surlExistentDetailedFolder not returned" , found);
       assertFalse("ls should not produce a token" , output.hasToken());
   }
   
   @Test
   public void testMkdir() throws ApiException
   {

       RequestOutputData output = client.mkdir(userDN, userFQANS, surlUnexistentFolderMkdir);
       assertNotNull("RequestOutputData should not be null", output);
       assertNotNull("Status should not be null", output.getStatus());
       assertTrue("Request status should be successfull", output.getStatus().isSRM_SUCCESS());
   }
   
   @Test
   public void testMkdirExistentDirectory() throws ApiException
   {
       RequestOutputData output = client.mkdir(userDN, userFQANS, surlExistentFolderMkdir);
       assertNotNull("RequestOutputData should not be null", output);
       assertNotNull("Status should not be null", output.getStatus());
       assertFalse("Request status should not be successfull", output.getStatus().isSRM_SUCCESS());
   }
   
   @Test
   public void testRmdir() throws ApiException
   {

       RequestOutputData output = client.rmdir(userDN, userFQANS, surlExistentFolderRmdir);
       assertNotNull("RequestOutputData should not be null", output);
       assertNotNull("Status should not be null", output.getStatus());
       assertTrue("Request status should be successfull", output.getStatus().isSRM_SUCCESS());
   }
   
   @Test
   public void testRmdirUnexistentDirectory() throws ApiException
   {

       RequestOutputData output = client.rmdir(userDN, userFQANS, surlUnexistentFolderRmdir);
       assertNotNull("RequestOutputData should not be null", output);
       assertNotNull("Status should not be null", output.getStatus());
       assertFalse("Request status should not be successfull", output.getStatus().isSRM_SUCCESS());
   }
   
   @Test
   public void testRm() throws ApiException
   {

       RequestOutputData output = client.rm(userDN, userFQANS, Arrays.asList(surlExistentFileRm));
       assertNotNull("RequestOutputData should not be null", output);
       assertNotNull("Status should not be null", output.getStatus());
       assertTrue("Request status should be successfull", output.getStatus().isSRM_SUCCESS());
       rmTestDone = true;
   }
   
   @Test
   public void testRmUnexistentDirectory() throws ApiException
   {

       RequestOutputData output = client.rm(userDN, userFQANS, Arrays.asList(surlUnexistentFileRm));
       assertNotNull("RequestOutputData should not be null", output);
       assertNotNull("Status should not be null", output.getStatus());
       assertFalse("Request status should not be successfull", output.getStatus().isSRM_SUCCESS());
   }
   
   @After
   public void tearDown() throws ApiException {
       client.rmdir(userDN, userFQANS, surlUnexistentFolderRmdir);
       client.rmdir(userDN, userFQANS, surlUnexistentFolderMkdir);
       client.rm(userDN, userFQANS, Arrays.asList(surlUnexistentFileRm));
   }
}
