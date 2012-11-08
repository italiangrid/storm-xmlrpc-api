/**
 * 
 */
package unittest;


import static org.junit.Assert.*;
import java.lang.reflect.Field;
import it.grid.storm.srm.types.TExtraInfo;
import org.junit.Before;
import org.junit.Test;
import redstone.xmlrpc.XmlRpcStruct;

/**
 * @author baltico
 */
public class TExtraInfoTest
{

    private XmlRpcStruct input = null;
    private String key, value;
    
    private final String keyValue = "myKey", valueValue = "hugeValue";
    private TExtraInfo instance = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        input = new XmlRpcStruct();
        try
        {
            Field keyField = TExtraInfo.class.getDeclaredField("PNAME_KEY");
            keyField.setAccessible(true);
            key = (String) keyField.get(new String());

            Field valueField = TExtraInfo.class.getDeclaredField("PNAME_VALUE");
            valueField.setAccessible(true);
            value = (String) valueField.get(new String());
        } catch(SecurityException e)
        {
            fail("internal error : " + e);
        } catch(NoSuchFieldException e)
        {
            fail("internal error : " + e);
        } catch(IllegalArgumentException e)
        {
            fail("internal error : " + e);
        } catch(IllegalAccessException e)
        {
            fail("internal error : " + e);
        }
        instance = new TExtraInfo(keyValue, valueValue);
    }

    /**
     * Test method for {@link it.grid.storm.srm.types.TExtraInfo#decode(redstone.xmlrpc.XmlRpcStruct)} .
     */
    @Test
    public void testDecode()
    {

        String keyValue = "myKey", valueValue = "hugeValue";
        input.put(key, keyValue);
        input.put(value, valueValue);
        TExtraInfo info = TExtraInfo.decode(input);
        assertEquals("Wrong TExtraInfo key", keyValue, info.getKey());
        assertEquals("Wrong TExtraInfo value", valueValue, info.getValue());
    }

    /**
     * Test method for {@link it.grid.storm.srm.types.TExtraInfo#decode(redstone.xmlrpc.XmlRpcStruct)} .
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDecodeWrongInputKeyType()
    {
        input.put(key, 12);
        TExtraInfo.decode(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecodeWrongInputValueType()
    {
        String keyValue = "myKey";
        input.put(key, keyValue);
        input.put(value, 12);
        TExtraInfo.decode(input);
    }

    /**
     * Test method for {@link it.grid.storm.srm.types.TExtraInfo#decode(redstone.xmlrpc.XmlRpcStruct)} .
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDecodeMissingKey()
    {
        input.put(value, "myValue");
        TExtraInfo.decode(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecodeMissingParameter()
    {
        TExtraInfo.decode(null);
    }

    @Test
    public void testTostring()
    {
        assertNotNull(instance.toString());
    }

    @Test
    public void testHashcodeSameInstance()
    {
        assertEquals(instance.hashCode(), instance.hashCode());
    }

    @Test
    public void testHashcode()
    {
        assertTrue(instance.hashCode() == new TExtraInfo(keyValue, valueValue).hashCode());
    }

    @Test
    public void testEquals()
    {
        assertTrue(instance.equals(new TExtraInfo(keyValue, valueValue)));
    }
    
    @Test
    public void testEqualsSameInstance()
    {
        assertTrue(instance.equals(instance));
    }
    
    @Test
    public void testEqualsDifferentObjects()
    {
        String valueValueDisinct = "smallValue";
        assertFalse(instance.equals(new TExtraInfo(keyValue, valueValueDisinct)));
    }
    
}
