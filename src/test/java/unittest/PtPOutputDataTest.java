package unittest;

import static org.junit.Assert.*;
import it.grid.storm.srm.types.TRequestToken;
import it.grid.storm.srm.types.TReturnStatus;
import it.grid.storm.srm.types.TStatusCode;
import it.grid.storm.xmlrpc.outputdata.FileTransferOutputData;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Michele Dibenedetto
 *
 */
public class PtPOutputDataTest
{
    private static final String surl = "srm://omii005-vm03.cnaf.infn.it:8444/atlas/myFile";
    private static final String turl = "https://omii005-vm03.cnaf.infn.it:8443/storage/atlas/myFile";
    private static final TStatusCode statusCode = TStatusCode.SRM_SUCCESS;
    private static final TRequestToken token = TRequestToken.getRandom(); 
    private static final String explanation = "All ok";
    private static final TReturnStatus status = new TReturnStatus(statusCode, explanation);
    
    private FileTransferOutputData instance = null;

    @Before
    public void setUp() throws Exception
    {
        instance = new FileTransferOutputData(surl, turl, status, token);
    }

    @Test
    public final void testPtPOutputData()
    {
        assertNotNull(new FileTransferOutputData(surl, turl, status, token));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testPingOutputDataNullArgs()
    {
        new FileTransferOutputData(null, null, null, null);
    }
    
    
    @Test
    public final void testIsSuccess()
    {
        assertTrue(instance.isSuccess());
    }

    @Test
    public final void testIsSuccessFailure()
    {
        assertFalse(new FileTransferOutputData(surl, turl, new TReturnStatus(TStatusCode.SRM_DUPLICATION_ERROR), token).isSuccess());
    }
    
    @Test
    public final void testGetSurl()
    {
        assertEquals(surl, instance.getSurl());
    }

    @Test
    public final void testGetTurl()
    {
        assertEquals(turl, instance.getTurl());
    }

    @Test
    public final void testGetStatus()
    {
        assertEquals(status, instance.getStatus());
    }
}
