/**
 * 
 */
package unittest;

import static org.junit.Assert.*;
//import java.util.HashMap;
//import it.grid.storm.srm.types.ArrayOfTExtraInfo;
//import it.grid.storm.srm.types.TExtraInfo;
//import it.grid.storm.xmlrpc.decoders.PingDecoder;
import it.grid.storm.xmlrpc.outputdata.PingOutputData;
import org.junit.Before;
import org.junit.Test;
//import redstone.xmlrpc.XmlRpcArray;
//import redstone.xmlrpc.XmlRpcStruct;

/**
 * @author Michele Dibenedetto
 *
 */
public class PingOutputDataTest
{
    
    private final String versionInfo = "StoRM - SRM Version 2.2";
//    private final String KEY_KEY = "key";
//    private final String VALUE_KEY = "value";
    private final String beVersion_VALUE = "1.11.0-1";
    private final String beOs_VALUE = "Scientific Linux release 5.8 (Boron)";
    private final String beVersion = "1.11.0-1";
    private final String beOs = "Scientific Linux release 5.8 (Boron)";
//    private TExtraInfo beVersion = null;
//    private TExtraInfo beOs = null;
//    private  ArrayOfTExtraInfo extraInfo = null;
    
    private PingOutputData instance = null;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
//        beVersion = new TExtraInfo(PingDecoder.BE_VERSION_KEY, beVersion_VALUE);
//        beOs = new TExtraInfo(PingDecoder.BE_OS_KEY, beOs_VALUE);
//        extraInfo = new ArrayOfTExtraInfo();
//        extraInfo.add(beVersion);
//        extraInfo.add(beOs);
//        instance = new PingOutputData(versionInfo, extraInfo);
        instance = new PingOutputData(versionInfo, beVersion, beOs);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testPingOutputDataNullArgs()
    {
        new PingOutputData(null, null, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public final void testPingOutputDataNullArg1()
    {
        new PingOutputData(null, beVersion, beOs);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public final void testPingOutputDataNullArg2()
    {
        new PingOutputData(versionInfo, null, beOs);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public final void testPingOutputDataNullArg3()
    {
        new PingOutputData(versionInfo, beVersion, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public final void testPingOutputDataEmptyArgs()
    {
        new PingOutputData("", "", "");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public final void testPingOutputDataEmptyArg1()
    {
        new PingOutputData("", beVersion, beOs);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public final void testPingOutputDataEmptyArg2()
    {
        new PingOutputData(versionInfo, "", beOs);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public final void testPingOutputDataEmptyArg3()
    {
        new PingOutputData(versionInfo, beVersion, "");
    }
    
    
//    /**
//     * Test method for {@link it.grid.storm.xmlrpc.outputdata.PingOutputData#build(java.util.Map)}.
//     */
//    @Test
//    public final void testBuild()
//    {
//        HashMap parameters = new HashMap();
//        parameters.put(PingOutputData.VERSIONINFO_KEY, versionInfo);
//        XmlRpcArray array = new XmlRpcArray();
//        XmlRpcStruct struct = new XmlRpcStruct();
//        struct.put(KEY_KEY, PingOutputData.BE_VERSION_KEY);
//        struct.put(VALUE_KEY, beVersion_VALUE);
//        array.add(struct);
//        XmlRpcStruct struct2 = new XmlRpcStruct();
//        struct2.put(KEY_KEY, PingOutputData.BE_OS_KEY);
//        struct2.put(VALUE_KEY, beOs_VALUE);
//        array.add(struct2);
//        parameters.put(ArrayOfTExtraInfo.PNAME_STORAGESYSTEMINFO, array);
//        assertNotNull(PingOutputData.build(parameters));
//    }

//    /**
//     * Test method for {@link it.grid.storm.xmlrpc.outputdata.PingOutputData#build(java.util.Map)}.
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public final void testBuildNullArg()
//    {
//        PingOutputData.build(null);
//    }
    /**
     * Test method for {@link it.grid.storm.xmlrpc.outputdata.PingOutputData#getVersionInfo()}.
     */
    @Test
    public final void testGetVersionInfo()
    {
        assertEquals(versionInfo, instance.getVersionInfo());
    }

//    /**
//     * Test method for {@link it.grid.storm.xmlrpc.outputdata.PingOutputData#getExtraInfoArray()}.
//     */
//    @Test
//    public final void testGetExtraInfoArray()
//    {
//        assertEquals(extraInfo, instance.getExtraInfoArray());
//    }

    @Test
    public final void testGetBeVersion()
    {
        assertEquals(beVersion_VALUE, instance.getBeVersion());
    }

    @Test
    public final void testGetBeOs()
    {
        assertEquals(beOs_VALUE, instance.getBeOs());
    }
    
    /**
     * Test method for {@link it.grid.storm.xmlrpc.outputdata.PingOutputData#isSuccess()}.
     */
    @Test
    public final void testIsSuccess()
    {
        assertTrue(instance.isSuccess());
    }
    
    @Test
    public void testHashcode()
    {
        assertTrue(instance.hashCode() == new PingOutputData(versionInfo, beVersion, beOs).hashCode());
    }

    @Test
    public void testEquals()
    {
        assertTrue(instance.equals(new PingOutputData(versionInfo, beVersion, beOs)));
    }
    
    @Test
    public final void testToString()
    {
        assertNotNull(instance.toString());
    }

}
